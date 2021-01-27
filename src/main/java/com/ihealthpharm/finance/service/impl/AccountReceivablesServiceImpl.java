package com.ihealthpharm.finance.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.AccountReceivablesRepository;
import com.ihealthpharm.finance.dao.CreditNoteRepository;
import com.ihealthpharm.finance.dao.DebitNoteRepository;
import com.ihealthpharm.finance.dto.AccRecievablesCustomerDTO;
import com.ihealthpharm.finance.dto.RecieptMoneyCalDTO;
import com.ihealthpharm.finance.helper.AccountReceivablesHelper;
import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.finance.model.CreditNoteModel;
import com.ihealthpharm.finance.model.DebitNoteModel;
import com.ihealthpharm.finance.service.AccountReceivablesService;
import com.ihealthpharm.masters.dao.CustomerInsuranceRepository;
import com.ihealthpharm.masters.dao.MasterAccountRepository;
import com.ihealthpharm.masters.model.MasterAccountModel;
import com.ihealthpharm.masters.service.MasterAccountService;
import com.ihealthpharm.sales.dao.SalesRepository;
import com.ihealthpharm.sales.model.SalesModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccountReceivablesServiceImpl implements AccountReceivablesService {

	@Autowired
	AccountReceivablesRepository accountReceivablesRepository;

	@Autowired
	SalesRepository salesRepository;

	@Autowired
	CustomerInsuranceRepository customerInsuranceRepository;

	@Autowired
	AccountReceivablesHelper accountReceivablesHelper;

	@Autowired
	MasterAccountService masterService;

	@Autowired
	MasterAccountRepository masterAccRepo;

	@Autowired
	CreditNoteRepository creditNoteRepo;

	@Autowired
	DebitNoteRepository debitNoteRepo;

	@Override
	public AccountReceivablesModel saveAccountReceivablesData(AccountReceivablesModel accountReceivables) {
		AccountReceivablesModel accountReceivablesRes = accountReceivablesRepository.save(accountReceivables);
		log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId()
				+ " Saved succesfully");
		return accountReceivablesRes;
	}

	@Override
	public AccountReceivablesModel updateAccountReceivablesData(AccountReceivablesModel accountReceivables) {
		AccountReceivablesModel accountReceivablesRes = getValidAccountsReceivables(
				accountReceivables.getAccountReceivablesId());
		if (!Objects.nonNull(accountReceivablesRes)) {
			throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(),
					HttpStatus.NOT_FOUND);
		}

		accountReceivablesRes = accountReceivablesRepository.save(accountReceivables);
		log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId()
				+ " updated succesfully");
		return accountReceivablesRes;
	}

	@Override
	public List<AccountReceivablesModel> updateAccountsReceivablesData(
			List<AccountReceivablesModel> accountsReceivables) {

		for (AccountReceivablesModel accountReceivables : accountsReceivables) {
			AccountReceivablesModel accountReceivablesRes = getValidAccountsReceivables(
					accountReceivables.getAccountReceivablesId());
			if (!Objects.nonNull(accountReceivablesRes)) {
				throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(),
						HttpStatus.NOT_FOUND);
			}

			accountReceivablesRes = accountReceivablesRepository.save(accountReceivables);

			// to update credit and debit note payment status
			if (accountReceivables.getSourceType().contains("Credit Note")) {
				CreditNoteModel c = creditNoteRepo.getCreditNoteDataById(accountReceivables.getSource());
				c.setPaymentStatus("Paid");
				creditNoteRepo.save(c);
			} else if (accountReceivables.getSourceType().contains("Debit Note")) {
				DebitNoteModel d = debitNoteRepo.getDebitNoteDataById(accountReceivables.getSource());
				d.setPaymentStatus("Paid");
				debitNoteRepo.save(d);
			}

			// to update the sales billing by using sales return credit note
			if (accountReceivablesRes.getPaymentStatus().equalsIgnoreCase("Paid")) {
				System.out.println(accountReceivablesRes.getPaymentType() + " ././../...");

				// below if condition is for partial payment of sales bill with only credit note
				// payment type
				if (accountReceivables.getPartiallyPaid() == null) {
					if (accountReceivablesRes.getPaymentType().equals("Credit Note")) {

						if (Objects.nonNull(accountReceivablesRes.getSalesBillId())) {
							SalesModel salesRecord = salesRepository
									.getSalesRecordById(accountReceivablesRes.getSalesBillId());
							System.out.println(salesRecord);
							Double creditAmount = Objects.nonNull(salesRecord.getCreditAmount())
									? salesRecord.getCreditAmount()
									: 0;
							System.out.println(
									"????????????????????????????????????????????????????????????????????????????????????????????");
							System.out.println("in sales fully paid with crrteedeffbfvhjfvfdvkdvkjdkjvv");
							System.out.println(accountReceivablesRes.getAmountReceived());

							double finalAmt = salesRecord.getNetAmount()
									- (-1 * accountReceivablesRes.getAmountReceived());
							DecimalFormat df = new DecimalFormat(".##");
							double balAmt = Double.parseDouble(df.format(finalAmt));
							if (balAmt != 0) {
								System.out.println("in if bal is zeeto :" + balAmt);
								salesRecord.setBalanceAmount((float) balAmt);
								salesRecord.setPaymentStatus("Partially Paid");
								salesRecord.setPaidAmount(
										salesRecord.getPaidAmount() != null && salesRecord.getPaidAmount() != 0
												? (float) (salesRecord.getPaidAmount() + balAmt)
												: (float) balAmt);
								String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
								salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								double creditAmt = salesRecord.getCreditAmount()
										- (-1 * accountReceivablesRes.getAmountReceived());
								System.out.println(creditAmt);
								salesRecord.setCreditAmount((Double.parseDouble(df.format(creditAmt))));
								salesRecord.setCreditNoteAmount((double) Double
										.parseDouble(df.format(-1 * accountReceivablesRes.getAmountReceived())));
								salesRecord.setSalesCreditRefNo(accountReceivables.getSourceRef());
							} else {
								System.out.println("in if bal is zeeto :" + balAmt);
								salesRecord.setBalanceAmount((float) 0);
								salesRecord.setPaymentStatus("Paid");
								salesRecord
										.setCreditNoteAmount((double) (-1 * accountReceivablesRes.getAmountReceived()));
								salesRecord.setSalesCreditRefNo(accountReceivables.getSourceRef());
								salesRecord.setCreditAmount((double) 0);
								String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
								salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								salesRecord.setPaidAmount(Objects.nonNull(salesRecord.getPaidAmount())
										? salesRecord.getPaidAmount() + (-1 * accountReceivablesRes.getAmountReceived())
										: (-1 * accountReceivablesRes.getAmountReceived()));

							}
							System.out.println("<>>?<<?<>>////<><>");

							salesRepository.save(salesRecord);

							MasterAccountModel masterAccObj = masterService
									.getDataByMasterAccNumber(accountReceivables.getCreditNumber());
							if (Objects.nonNull(masterAccObj)) {
								Double amount = creditAmount + masterAccObj.getCreditLimitLeft();
								masterAccRepo.updateMasterAccountCustomerAmount(amount.intValue(),
										masterAccObj.getMasterAccountId());
								log.info("Master Account Data Updated Successfully");
							}
						} else {
							if (accountReceivablesRes.getPaymentType().equals("Credit Note")) {
								System.out.println(accountReceivablesRes.getAmountReceived() + " :::::;;;;;;");

								if (Objects.nonNull(accountReceivablesRes.getBillRefNo())
										|| Objects.nonNull(accountReceivablesRes.getSalesBillId())) {
									SalesModel salesRecord = accountReceivablesRepository
											.getSalesByBillCode(accountReceivablesRes.getBillRefNo());
									Double creditAmount = Objects.nonNull(salesRecord.getCreditAmount())
											? salesRecord.getCreditAmount()
											: 0;
									double finalAmt = salesRecord.getNetAmount()
											- (-1 * accountReceivablesRes.getAmountReceived());
									DecimalFormat df = new DecimalFormat(".##");
									double balAmt = Double.parseDouble(df.format(finalAmt));
									salesRecord.setBalanceAmount((float) balAmt);
									salesRecord.setPaymentStatus("Partially Paid");
									salesRecord.setPaidAmount(
											salesRecord.getPaidAmount() != null && salesRecord.getPaidAmount() != 0
													? (float) (salesRecord.getPaidAmount() + balAmt)
													: (float) balAmt);
									String lastUpdatedUserId = Integer
											.toString(accountReceivablesRes.getLastUpdateUser());
									salesRecord.setLastUpdateUserId(lastUpdatedUserId);
									double creditAmt = salesRecord.getCreditAmount()
											- (-1 * accountReceivablesRes.getAmountReceived());
									System.out.println(creditAmt);
									salesRecord.setCreditAmount((Double.parseDouble(df.format(creditAmt))));
									salesRecord.setCreditNoteAmount((double) Double
											.parseDouble(df.format(-1 * accountReceivablesRes.getAmountReceived())));
									salesRecord.setSalesCreditRefNo(accountReceivables.getSourceRef());
									salesRepository.save(salesRecord);

									MasterAccountModel masterAccObj = masterService
											.getDataByMasterAccNumber(accountReceivables.getCreditNumber());
									if (Objects.nonNull(masterAccObj)) {
										Double amount = creditAmount + masterAccObj.getCreditLimitLeft();
										masterAccRepo.updateMasterAccountCustomerAmount(amount.intValue(),
												masterAccObj.getMasterAccountId());
										log.info("Master Account Data Updated Successfully");
									}

								}
							}
						}

					}
				} else {
					System.out.println("skipped paid with credut note");
				}

			}

			// if payment is partially paid
			if (accountReceivables.getSourceType().equalsIgnoreCase("Sales Billing")
					&& accountReceivables.getPaymentStatus().equalsIgnoreCase("Partially Paid")) {

				System.out.println("acc rec source tyep and seconf if in for loop");

				SalesModel salesRes = salesRepository.getSalesRecordByNo(accountReceivables.getSourceRef());

				salesRes.setBalanceAmount(salesRes.getBalanceAmount() - accountReceivables.getPartialAmt());
				System.out.println(accountReceivablesRes.getPaymentType());
				if (accountReceivables.getPaymentType() != null) {
					System.out.println("if payment is not nukl");

					if (accountReceivables.getPaymentType().equals("Card")) {

						salesRes.setCreditCardAmount(
								(salesRes.getCreditCardAmount() != null && salesRes.getCreditCardAmount() > 0)
										? salesRes.getCreditCardAmount() + accountReceivables.getPartialAmt()
										: accountReceivables.getPartialAmt());
						salesRes.setCreditAccountNo(accountReceivables.getCreditCardNo());
						salesRes.setCreditCardAuthNo(accountReceivables.getCardAuthCode());

					} else if (accountReceivables.getPaymentType().equals("Cash")) {

						salesRes.setCashAmount((salesRes.getCashAmount() != null && salesRes.getCashAmount() > 0)
								? salesRes.getCashAmount() + accountReceivables.getPartialAmt()
								: accountReceivables.getPartialAmt());

					} else if ((accountReceivables.getPaymentType().equals("MPesa"))) {

						salesRes.setUpiAmount((salesRes.getUpiAmount() != null && salesRes.getUpiAmount() > 0)
								? salesRes.getUpiAmount() + accountReceivables.getPartialAmt()
								: accountReceivables.getPartialAmt());
						salesRes.setUpiPhoneNo(salesRes.getUpiPhoneNo());
						salesRes.setUpiTransactionId(accountReceivables.getUpiAuthCode());

					} else if ((accountReceivables.getPaymentType().equals("Cheque"))) {

						salesRes.setChequeAmount((salesRes.getChequeAmount() != null && salesRes.getChequeAmount() > 0)
								? salesRes.getChequeAmount() + accountReceivables.getPartialAmt()
								: accountReceivables.getPartialAmt());
						LocalDate chequeDt = accountReceivables.getChequeDate();
						salesRes.setChequeDate(chequeDt.toString());
						salesRes.setChequeNumber(accountReceivables.getChequeNumber());

					} else if (accountReceivables.getPaymentType().equals("Credit Note")) {
						System.out.println("????????????.......................");
						System.out.println(salesRes.getBalanceAmount());
						System.out.println(accountReceivables.getPartialAmt());
						// salesRes.setBalanceAmount(salesRes.getBalanceAmount() -
						// accountReceivables.getPartialAmt());
						salesRes.setPaidAmount(accountReceivables.getPartialAmt());
						salesRes.setCreditNoteAmount((double) accountReceivables.getPartialAmt());

						salesRes.setSalesCreditRefNo(accountReceivables.getSourceRef());
						/*
						 * salesRes.setCreditAmount(salesRes.getCreditAmount() -
						 * accountReceivables.getPartialAmt()); salesRepository.save(salesRes);
						 */
					}
					salesRes.setCreditAmount(salesRes.getCreditAmount() - accountReceivables.getPartialAmt());
					System.out.println(salesRes.getBalanceAmount() + " bal amt     +:" + salesRes.getCreditAmount());
					if (salesRes.getBalanceAmount() > 0) {
						System.out.println("in bal if");
						salesRes.setPaymentStatus("Partially Paid");

					} else if (salesRes.getBalanceAmount() == 0) {
						System.out.println("in else of bal");
						salesRes.setPaymentStatus("Paid");
					}
					SalesModel sales = salesRepository.save(salesRes);

					Double creditAmount = Objects.nonNull(sales.getCreditAmount()) ? sales.getCreditAmount() : 0;

					MasterAccountModel masterAccObj = masterService
							.getDataByMasterAccNumber(accountReceivables.getCreditNumber());
					if (Objects.nonNull(masterAccObj)) {
						Double amount = creditAmount + masterAccObj.getCreditLimitLeft();
						masterAccRepo.updateMasterAccountCustomerAmount(amount.intValue(),
								masterAccObj.getMasterAccountId());
						log.info("Master Account Data Updated Successfully");
					}

				}
			}

			if (accountReceivablesRes.getSourceType().equalsIgnoreCase("Sales Billing")
					&& Objects.nonNull(accountReceivablesRes.getSourceRef())
					&& accountReceivablesRes.getPaymentStatus().equalsIgnoreCase("Paid")
					&& Objects.nonNull(accountReceivablesRes.getCreditNumber())) {
				System.out.println(
						"........................................................................in 1st for loop");
				System.out.println(accountReceivables);
				SalesModel salesRecord = accountReceivablesRepository
						.getSalesByBillCode(accountReceivablesRes.getSourceRef());
				Double creditAmount = Objects.nonNull(salesRecord.getCreditAmount()) ? salesRecord.getCreditAmount()
						: 0;
				System.out.println(creditAmount + ":crrrredit amt");
				if (creditAmount > 0) {
					System.out.println("in if of credit amont gretaer than zero");

					if (Objects.nonNull(accountReceivablesRes.getCashAmount())) {
						System.out.println("if payment type cash");
						salesRecord.setPaymentStatus("Paid");
						Float cashAmount = Objects.nonNull(salesRecord.getCashAmount()) ? salesRecord.getCashAmount()
								: 0;

						if (salesRecord.getBalanceAmount() != 0) {

							if (salesRecord.getCreditNoteAmount() != null) {
								Float paidAmount = (float) (cashAmount + creditAmount);
								salesRecord.setCashAmount((float) paidAmount);
								String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
								salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								System.out.println(paidAmount + " : just now paid");
								System.out.println(salesRecord.getCreditNoteAmount() + " :credita mt");
								double amt = paidAmount + salesRecord.getCreditNoteAmount();
								System.out.println(salesRecord.getNetAmount());
								if (amt == salesRecord.getNetAmount()) {
									System.out.println(
											"ammmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
									salesRecord.setPaymentStatus("Paid");
									salesRecord.setCreditAmount((double) 0);
									salesRecord.setBalanceAmount((float) 0);
									salesRecord.setPaidAmount((float) amt);
								} else {
								double bal = salesRecord.getBalanceAmount() - salesRecord.getPaidAmount();
								System.out.println(bal);
								if (bal == 0) {
									salesRecord.setPaymentStatus("Paid");
									salesRecord.setCreditAmount((double) 0);
								} else {
									System.out.println(salesRecord.getCreditAmount() + " cedit amt");
									salesRecord.setPaymentStatus("Partially Paid");
									salesRecord.setCreditAmount((double) bal);
								}
								salesRecord.setBalanceAmount((float) bal);

								if (salesRecord.getCreditNoteAmount() != null) {
									double amtPaid = salesRecord.getCreditNoteAmount() + paidAmount;
									salesRecord.setPaidAmount((float) amtPaid);
								}

								System.out.println(salesRecord.getBalanceAmount());
								}
							} else {
								System.out.println(accountReceivablesRes);
								Float paidAmount = (float) (cashAmount + creditAmount);
								System.out.println(paidAmount);
								salesRecord.setPaymentStatus("Paid");
								salesRecord.setCashAmount((float) paidAmount);
								salesRecord.setCreditAmount((double) 0);
								salesRecord.setPaidAmount((float) paidAmount);
								salesRecord.setBalanceAmount((float) 0);
								String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
								salesRecord.setLastUpdateUserId(lastUpdatedUserId);
							}
						}

					} else if (Objects.nonNull(accountReceivablesRes.getChequeAmount())) {
						System.out.println("cheque amt");
						System.out.println(salesRecord.getBalanceAmount());

						Double chequeAmount = Objects.nonNull(salesRecord.getChequeAmount())
								? salesRecord.getChequeAmount()
								: 0;
						if (salesRecord.getBalanceAmount() != 0) {

							if (salesRecord.getCreditNoteAmount() != null) {
								System.out.println(chequeAmount + " cheque amt and credit :" + creditAmount);
								Float paidAmount = (float) (chequeAmount + creditAmount);
								System.out.println(paidAmount);
								salesRecord.setChequeAmount((double) paidAmount);
								LocalDate chequeDt = accountReceivablesRes.getChequeDate();
								salesRecord.setChequeDate(chequeDt.toString());
								salesRecord.setChequeNumber(accountReceivablesRes.getChequeNumber());
								String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
								salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								System.out.println(paidAmount + " : just now paid");
								System.out.println(salesRecord.getCreditNoteAmount() + " :credita mt");
								double amt = paidAmount + salesRecord.getCreditNoteAmount();
								System.out.println(salesRecord.getNetAmount());
								if (amt == salesRecord.getNetAmount()) {
									System.out.println(
											"ammmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
									salesRecord.setPaymentStatus("Paid");
									salesRecord.setCreditAmount((double) 0);
									salesRecord.setBalanceAmount((float) 0);
									salesRecord.setPaidAmount((float) amt);
								} else {
								double bal = salesRecord.getBalanceAmount() - salesRecord.getPaidAmount();
								System.out.println(bal);
								if (bal == 0) {
									salesRecord.setPaymentStatus("Paid");
									salesRecord.setCreditAmount((double) 0);
								} else {
									System.out.println(salesRecord.getCreditAmount() + " cedit amt");
									salesRecord.setPaymentStatus("Partially Paid");
									salesRecord.setCreditAmount((double) bal);
								}
								salesRecord.setBalanceAmount((float) bal);

								if (salesRecord.getCreditNoteAmount() != null) {
									double amtPaid = salesRecord.getCreditNoteAmount() + paidAmount;
									salesRecord.setPaidAmount((float) amtPaid);
								}
								}

							} else {
								System.out.println(accountReceivablesRes.getSourceType() + " ..,.,..  "
										+ salesRecord.getCreditNoteAmount());
								if (accountReceivablesRes.getSourceType().equals("Credit Note")) {
									System.out.println("insdhsdidsb");
									Float paidAmount = (float) (chequeAmount + creditAmount);
									System.out.println(paidAmount + " : paid amnfj");
									salesRecord.setPaymentStatus("Paid");
									double paidAmt = salesRecord.getNetAmount()
											- (salesRecord.getCreditNoteAmount() != null
													? salesRecord.getCreditNoteAmount()
													: 0);
									System.out.println(paidAmt + " double value");
									salesRecord.setChequeAmount((double) paidAmt);
									salesRecord.setCreditAmount((double) 0);
									salesRecord.setPaidAmount((float) paidAmount);
									salesRecord.setSalesCreditRefNo(accountReceivablesRes.getSourceRef());
									salesRecord.setBalanceAmount((float) 0);
									LocalDate chequeDt = accountReceivablesRes.getChequeDate();
									salesRecord.setChequeDate(chequeDt.toString());
									salesRecord.setChequeNumber(accountReceivablesRes.getChequeNumber());
									String lastUpdatedUserId = Integer
											.toString(accountReceivablesRes.getLastUpdateUser());
									salesRecord.setLastUpdateUserId(lastUpdatedUserId);

								} else {
									System.out.println("else case of cheque amt");
									Float paidAmount = (float) (chequeAmount + creditAmount);
									System.out.println(paidAmount);
									salesRecord.setPaymentStatus("Paid");
									salesRecord.setChequeAmount((double) paidAmount);
									salesRecord.setCreditAmount((double) 0);
									salesRecord.setPaidAmount((float) paidAmount);
									salesRecord.setBalanceAmount((float) 0);
									LocalDate chequeDt = accountReceivablesRes.getChequeDate();
									salesRecord.setChequeDate(chequeDt.toString());
									salesRecord.setChequeNumber(accountReceivablesRes.getChequeNumber());
									String lastUpdatedUserId = Integer
											.toString(accountReceivablesRes.getLastUpdateUser());
									salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								}
							}
						}
					} else if (Objects.nonNull(accountReceivablesRes.getCreditCardAmount())) {
						System.out.println("credit card");

						if (salesRecord.getBalanceAmount() != 0) {
							Float creditCardAmount = Objects.nonNull(salesRecord.getCreditCardAmount())
									? salesRecord.getCreditCardAmount()
									: 0;
							if (salesRecord.getCreditNoteAmount() != null) {

								Float paidAmount = (float) (creditCardAmount + creditAmount);
								salesRecord.setCreditCardAmount(paidAmount);
								salesRecord.setCreditCardNo(accountReceivablesRes.getCreditCardNo());
								salesRecord.setCreditCardAuthNo(accountReceivablesRes.getCardAuthCode());
								String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
								salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								// after doing partial amt again paying the full amt with any payment type
								System.out.println(paidAmount + " : just now paid");
								System.out.println(salesRecord.getCreditNoteAmount() + " :credita mt");
								double amt = paidAmount + salesRecord.getCreditNoteAmount();
								System.out.println(salesRecord.getNetAmount());
								if (amt == salesRecord.getNetAmount()) {
									System.out.println(
											"ammmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
									salesRecord.setPaymentStatus("Paid");
									salesRecord.setCreditAmount((double) 0);
									salesRecord.setBalanceAmount((float) 0);
									salesRecord.setPaidAmount((float) amt);
								} else {

									double bal = salesRecord.getBalanceAmount() - salesRecord.getPaidAmount();
									System.out.println(bal);
									if (bal == 0) {
										salesRecord.setPaymentStatus("Paid");
										salesRecord.setCreditAmount((double) 0);
									} else {
										System.out.println(salesRecord.getCreditAmount() + " cedit amt");
										salesRecord.setPaymentStatus("Partially Paid");
										salesRecord.setCreditAmount((double) bal);
									}
									salesRecord.setBalanceAmount((float) bal);

									if (salesRecord.getCreditNoteAmount() != null) {
										double amtPaid = salesRecord.getCreditNoteAmount() + paidAmount;
										salesRecord.setPaidAmount((float) amtPaid);
									}
								}
								System.out.println(salesRecord.getBalanceAmount());
							} else {
								Float paidAmount = (float) (creditCardAmount + creditAmount);
								System.out.println(paidAmount);
								salesRecord.setPaymentStatus("Paid");
								salesRecord.setCreditCardAmount((float) paidAmount);
								salesRecord.setCreditCardNo(accountReceivablesRes.getCreditCardNo());
								salesRecord.setCreditCardAuthNo(accountReceivablesRes.getCardAuthCode());
								salesRecord.setCreditAmount((double) 0);
								salesRecord.setPaidAmount((float) paidAmount);
								salesRecord.setBalanceAmount((float) 0);
								String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
								salesRecord.setLastUpdateUserId(lastUpdatedUserId);
							}
						}

					} else if (Objects.nonNull(accountReceivablesRes.getUpiAmount())) {
						System.out.println("upi amount ...//");
						Float upiAmount = Objects.nonNull(salesRecord.getUpiAmount()) ? salesRecord.getUpiAmount() : 0;

						if (salesRecord.getCreditNoteAmount() != null) {

							Float paidAmount = (float) (upiAmount + creditAmount);
							String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
							salesRecord.setLastUpdateUserId(lastUpdatedUserId);
							salesRecord.setUpiAmount(paidAmount);
							salesRecord.setUpiPhoneNo(accountReceivablesRes.getUpiPhoneNo());
							salesRecord.setUpiTransactionId(accountReceivablesRes.getUpiAuthCode());

							System.out.println(paidAmount + " : just now paid");
							System.out.println(salesRecord.getCreditNoteAmount() + " :credita mt");
							double amt = paidAmount + salesRecord.getCreditNoteAmount();
							System.out.println(salesRecord.getNetAmount());
							if (amt == salesRecord.getNetAmount()) {
								System.out.println(
										"ammmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
								salesRecord.setPaymentStatus("Paid");
								salesRecord.setCreditAmount((double) 0);
								salesRecord.setBalanceAmount((float) 0);
								salesRecord.setPaidAmount((float) amt);
							} else {
							
							double bal = salesRecord.getBalanceAmount() - salesRecord.getPaidAmount();
							System.out.println(bal);
							if (bal == 0) {
								salesRecord.setPaymentStatus("Paid");
								salesRecord.setCreditAmount((double) 0);
							} else {
								System.out.println(salesRecord.getCreditAmount() + " cedit amt");
								salesRecord.setPaymentStatus("Partially Paid");
								salesRecord.setCreditAmount((double) bal);
							}
							salesRecord.setBalanceAmount((float) bal);

							if (salesRecord.getCreditNoteAmount() != null) {
								double amtPaid = salesRecord.getCreditNoteAmount() + paidAmount;
								salesRecord.setPaidAmount((float) amtPaid);
							}
							}

						} else {
							Float paidAmount = (float) (upiAmount + creditAmount);
							System.out.println(paidAmount);
							salesRecord.setPaymentStatus("Paid");
							salesRecord.setUpiAmount((float) paidAmount);
							salesRecord.setUpiPhoneNo(accountReceivablesRes.getUpiPhoneNo());
							salesRecord.setUpiTransactionId(accountReceivablesRes.getUpiAuthCode());
							salesRecord.setCreditAmount((double) 0);
							salesRecord.setPaidAmount((float) paidAmount);
							salesRecord.setBalanceAmount((float) 0);
							String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
							salesRecord.setLastUpdateUserId(lastUpdatedUserId);
						}

					}
					salesRepository.save(salesRecord);

					MasterAccountModel masterAccObj = masterService
							.getDataByMasterAccNumber(accountReceivables.getCreditNumber());
					if (Objects.nonNull(masterAccObj)) {
						Double amount = creditAmount + masterAccObj.getCreditLimitLeft();
						masterAccRepo.updateMasterAccountCustomerAmount(amount.intValue(),
								masterAccObj.getMasterAccountId());
						log.info("Master Account Data Updated Successfully");
					}
				}
			}

			log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId()
					+ " updated succesfully");

		}

		for (int i = 0; i < accountsReceivables.size(); i++) {
			for (int j = 0; j < accountsReceivables.size(); j++) {
				System.out.println("in 2 for loops");
				if (i != j) {
					if (Objects.nonNull(accountsReceivables.get(i).getBillRefNo())) {
						if (accountsReceivables.get(i).getBillRefNo()
								.equals(accountsReceivables.get(j).getSourceRef())) {
							if (accountsReceivables.get(i).getSourceType().toLowerCase().contains("credit note")) {

								System.out.println("if only credit note and multi for loop and if confi");
								Double creditNoteAmount = -1
										* accountsReceivables.get(i).getAmountReceived().doubleValue();
								String billNo = accountsReceivables.get(i).getBillRefNo();
								SalesModel salesRecord = accountReceivablesRepository
										.getSalesByBillCode(accountsReceivables.get(i).getBillRefNo());
								if (salesRecord.getBalanceAmount() == 0) {
									System.out.println("in ig of paid");
									String paymentStatus = "Paid";
									Integer res = salesRepository.updateUpiAmountBasedOnId(creditNoteAmount,
											paymentStatus, billNo);
								} else {
									System.out.println("elsddsfbddshvdhb sjkvbdkjb");
									Integer res = salesRepository.updateUpiAmountBasedOnStatusId(creditNoteAmount,
											billNo);
								}

								if (salesRecord.getNetAmount().doubleValue() == creditNoteAmount) {
									System.out
											.println("if both amounts are equalmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
									if (Objects.nonNull(salesRecord.getCashAmount())) {
										salesRecord.setCashAmount(null);
									} else if (Objects.nonNull(salesRecord.getUpiAmount())) {
										salesRecord.setUpiAmount(null);
										salesRecord.setUpiPhoneNo(null);
										salesRecord.setUpiTransactionId(null);
									} else if (Objects.nonNull(salesRecord.getCreditCardAmount())) {
										salesRecord.setCreditCardAuthNo(null);
										salesRecord.setCreditCardAmount(null);
										salesRecord.setCreditCardNo(null);
									} else if (Objects.nonNull(salesRecord.getChequeAmount())) {
										salesRecord.setChequeAmount(null);
										salesRecord.setChequeDate(null);
										salesRecord.setChequeNumber(null);
									} else if (accountsReceivables.get(i).getPaymentType()
											.equalsIgnoreCase("Credit Note")) {
										System.out.println("<><><>_+_+++++++++++++++++++++++++");
										salesRecord.setBalanceAmount((float) 0);
										String lastUpdatedUserId = Integer
												.toString(accountsReceivables.get(i).getLastUpdateUser());
										salesRecord.setLastUpdateUserId(lastUpdatedUserId);
										salesRecord.setCreditNoteAmount(
												(double) (accountsReceivables.get(i).getAmountReceived()));
										salesRecord.setSalesCreditRefNo(accountsReceivables.get(i).getSourceRef());
										salesRecord.setCreditAmount((double) 0);
										System.out.println("<>>?<<?<>><><>");
										System.out.println(salesRecord.getBalanceAmount());
										System.out.println(salesRecord);
									}

									salesRecord.setCreditAmount((double) 0);
									salesRecord.setBalanceAmount((float) 0);
									salesRecord.setPaidAmount(creditNoteAmount.floatValue());
									System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
									System.out.println(salesRecord.getBalanceAmount());
									if (salesRecord.getBalanceAmount() > 0) {
										System.out.println("in 2 for lopps of if");
										salesRecord.setPaymentStatus("Partially Paid");
									} else if (salesRecord.getBalanceAmount() == 0) {
										System.out.println("in 2 for lopps of if else");
										salesRecord.setPaymentStatus("Paid");
									}
									salesRecord.setCreditNoteAmount(creditNoteAmount);
									salesRecord.setSalesCreditRefNo(accountsReceivables.get(i).getSourceRef());
									SalesModel salesData = salesRepository.save(salesRecord);

									Double creditAmount = Objects.nonNull(salesData.getCreditAmount())
											? salesData.getCreditAmount()
											: 0;

									MasterAccountModel masterAccObj = masterService
											.getDataByMasterAccNumber(accountsReceivables.get(i).getCreditNumber());
									if (Objects.nonNull(masterAccObj)) {
										Double amount = creditAmount + masterAccObj.getCreditLimitLeft();
										masterAccRepo.updateMasterAccountCustomerAmount(amount.intValue(),
												masterAccObj.getMasterAccountId());
										log.info("Master Account Data Updated Successfully");
									}

								} else {
									System.out.println("in if dfvdfkjffsdhdsdsfdhsudhsshsdkwcice");
									System.out.println(accountsReceivables.get(i).getPaymentType());
									if (accountsReceivables.get(i).getPaymentType().equals("Cash")) {

										salesRecord.setCashAmount(
												(salesRecord.getCashAmount() != null && salesRecord.getCashAmount() > 0)
														? salesRecord.getCashAmount() - creditNoteAmount.floatValue()
														: null);
									} else if (accountsReceivables.get(i).getPaymentType().equals("Card")) {
										salesRecord.setCreditCardAmount((salesRecord.getCreditCardAmount() != null
												&& salesRecord.getCreditCardAmount() > 0)
														? salesRecord.getCreditCardAmount()
																- creditNoteAmount.floatValue()
														: null);

									} else if (accountsReceivables.get(i).getPaymentType().equals("MPesa")) {
										salesRecord.setUpiAmount(
												(salesRecord.getUpiAmount() != null && salesRecord.getUpiAmount() > 0)
														? salesRecord.getUpiAmount() - creditNoteAmount.floatValue()
														: null);

									} else if (accountsReceivables.get(i).getPaymentType().equals("Cheque")) {
										System.out.println(salesRecord.getChequeAmount() + " :c hew amty");
										System.out.println(creditNoteAmount);
										System.out
												.println(salesRecord.getChequeAmount() - creditNoteAmount.floatValue());
										salesRecord.setChequeAmount((salesRecord.getChequeAmount() != null
												&& salesRecord.getChequeAmount() > 0)
														? salesRecord.getChequeAmount() - creditNoteAmount.floatValue()
														: null);

									} else {
										System.out.println("both amounts elser cvxcfffvffdfdfdff");
									}
									salesRecord.setCreditNoteAmount(creditNoteAmount);
									System.out.println(salesRecord);
									salesRepository.save(salesRecord);
								}
							}
						}
					}
				}
			}
		}

		return accountsReceivables;
	}

	@Override
	public List<AccountReceivablesModel> findAllAccountsReceivables() {
		return accountReceivablesRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public AccountReceivablesModel findAccountReceivablesById(Integer accountReceivablesId) {
		AccountReceivablesModel accountReceivablesRes = getValidAccountsReceivables(accountReceivablesId);
		if (!Objects.nonNull(accountReceivablesRes)) {
			throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId()
				+ " retrieved succesfully");
		return accountReceivablesRes;
	}

	@Override
	public void deleteAccountReceivablesById(Integer accountReceivablesId) {
		AccountReceivablesModel accountReceivablesRes = accountReceivablesRepository.getOne(accountReceivablesId);
		if (!Objects.nonNull(accountReceivablesRes)) {
			throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("AccountReceivables data with ID : " + accountReceivablesRes.getAccountReceivablesId()
				+ " Deleted succesfully");
		accountReceivablesRepository.delete(accountReceivablesRes);
	}

	@Override
	public void deleteAccountsReceivablesById(Integer[] accountReceivablesIds) {
		AccountReceivablesModel accountReceivablesRes;
		for (int accountReceivables : accountReceivablesIds) {
			accountReceivablesRes = getValidAccountsReceivables(accountReceivables);
			if (!Objects.nonNull(accountReceivablesRes)) {
				throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(),
						HttpStatus.NOT_FOUND);
			}
			accountReceivablesRepository.delete(accountReceivablesRes);
			log.info("AccountReceivables data with ID: " + accountReceivablesRes.getAccountReceivablesId()
					+ " deleted succesfully");
		}

	}

	public AccountReceivablesModel getValidAccountsReceivables(Integer accountReceivablesId) {
		AccountReceivablesModel accountReceivablesRes = null;

		try {
			accountReceivablesRes = accountReceivablesRepository.findById(accountReceivablesId).get();
			return accountReceivablesRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(accountReceivablesHelper.getNotFoundAccountReceivablesMessage(),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<SalesModel> getAllBillsByCustomerId(Integer customerId) {
		log.info("given  id :" + customerId);
		List<SalesModel> res = accountReceivablesRepository.getAllBillsByCustomerId(customerId);
		return res;
	}

	@Override
	public List<SalesModel> getAllCustomersByCustomerId(Integer customerId) {
		log.info("given  id :" + customerId);
		List<SalesModel> res = accountReceivablesRepository.getAllCustomersByCustomerId(customerId);
		return res;
	}

	@Override
	public List<AccountReceivablesModel> findAccountReceivablesByBillId(Integer billId) {

		List<AccountReceivablesModel> response = accountReceivablesRepository.getAccountRecievablesBillId(billId);
		return response;
	}

	@Override
	public List<SalesModel> getAllSalesBySearch(String billCode) {
		return accountReceivablesRepository.getSalesBasedOnSalesSearch(billCode);
	}

	@Override
	public List<AccountReceivablesModel> getAllByBillCodeSearch(String billCode, String customerName) {
		return accountReceivablesRepository.getAllAccRecievablesBySearchBillCode(billCode, customerName);
	}

	@Override
	public List<AccountReceivablesModel> getAllAccountPayables() {
		return accountReceivablesRepository.getAllAccountPayables();
	}

	@Override
	public List<AccountReceivablesModel> getAllCustomersBasedonCustomerName(String customerName) {
		return accountReceivablesRepository.getAllCustomersBasedOnName(customerName);
	}

	@Override
	public List<String> findReceiptNobysearchAR(String searchTerm) {
		return accountReceivablesRepository.findReceiptNoBySearch(searchTerm);
	}

	@Override
	public List<String> findallReceiptNoAR() {
		return accountReceivablesRepository.findAllReceiptNoINAR();
	}

	@Override
	public List<AccountReceivablesModel> getAllRecievablesCustomerNameSearch(String customerName) {
		return accountReceivablesRepository.getAllRecievablesCustNames(customerName);
	}

	@Override
	public List<String> findallCustNamesAR() {
		return accountReceivablesRepository.findAllCustomersNamesINAR();
	}

	@Override
	public List<String> findCustNamesbysearchAR(String searchTerm) {
		return accountReceivablesRepository.findCustomersNamesBySearch(searchTerm);
	}

	// popup searches

	@Override
	public List<AccountReceivablesModel> searchInAccRecievables(String paymentStatus, String paymentStartDate,
			String paymentEndDate, String sourceRef, Integer pageNumber, Integer pageSize, String customerName) {

		Pageable limit = new PageRequest(pageNumber, pageSize);

		List<AccountReceivablesModel> response = null;

		if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))
				&& ((paymentStartDate != null && !paymentStartDate.equals("undefined")
						&& !paymentStartDate.equals("null"))
						&& (paymentEndDate != null && !paymentEndDate.equals("undefined")
								&& !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccReceivablesSearchByStatusSearchDate(start, end, sourceRef,
					customerName, paymentStatus, limit);
		}

		else if ((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))
				&& ((paymentStartDate != null && !paymentStartDate.equals("undefined")
						&& !paymentStartDate.equals("null"))
						&& (paymentEndDate != null && !paymentEndDate.equals("undefined")
								&& !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievableSearchByStatusSearchDateAndSourceRef(start, end,
					sourceRef, customerName, limit);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusSearchDatesAndStatus(paymentStatus,
					start, end, customerName, limit);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievableSearchByStatusSearchDates(start, end, customerName,
					limit);
		}

		else if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusSearchBasedonStatusAndSourceRef(
					paymentStatus, sourceRef, customerName, limit);
		}

		else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusSearchStatusAndSourceNumber(
					paymentStatus, sourceRef, customerName, limit);
		}

		else if ((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchBySourceRef(sourceRef, customerName, limit);
		} else if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchByStatus(paymentStatus, customerName,
					limit);
		}

		return response;
	}

	@Override
	public Integer searchInAccRecievablesForCount(String paymentStatus, String paymentStartDate, String paymentEndDate,
			String sourceRef, Integer pageNumber, Integer pageSize, String customerName) {
		Integer response = 0;

		if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))
				&& ((paymentStartDate != null && !paymentStartDate.equals("undefined")
						&& !paymentStartDate.equals("null"))
						&& (paymentEndDate != null && !paymentEndDate.equals("undefined")
								&& !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccReceivablesSearchByStatusSearchDateCount(start, end,
					sourceRef, customerName, paymentStatus);
		}

		else if ((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))
				&& ((paymentStartDate != null && !paymentStartDate.equals("undefined")
						&& !paymentStartDate.equals("null"))
						&& (paymentEndDate != null && !paymentEndDate.equals("undefined")
								&& !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievableSearchByStatusSearchDateAndSourceRefCount(start,
					end, sourceRef, customerName);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository
					.findAccRecievablesSearchByStatusSearchDatesAndStatusCount(paymentStatus, start, end, customerName);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievableSearchByStatusSearchDatesCount(start, end,
					customerName);
		}

		else if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository
					.findAccRecievablesSearchByStatusSearchBasedonStatusAndSourceRefCount(paymentStatus, sourceRef,
							customerName);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusSearchStatusAndSourceNumberCount(
					paymentStatus, sourceRef, customerName);
		}

		else if ((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchBySourceRefCount(sourceRef, customerName);
		} else if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusCount(paymentStatus, customerName);
		}

		return response;
	}

	@Override
	public List<AccRecievablesCustomerDTO> getAllAccountPayablesData() {
		return accountReceivablesRepository.getAllAccountPayablesData();

	}

	@Override
	public List<AccountReceivablesModel> searchInAccRecievablesForAccounts(String paymentStatus,
			String paymentStartDate, String paymentEndDate, String sourceRef, Integer pageNumber, Integer pageSize,
			String creditNumber) {
		Pageable limit = new PageRequest(pageNumber, pageSize);

		List<AccountReceivablesModel> response = null;

		if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))
				&& ((paymentStartDate != null && !paymentStartDate.equals("undefined")
						&& !paymentStartDate.equals("null"))
						&& (paymentEndDate != null && !paymentEndDate.equals("undefined")
								&& !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccReceivablesSearchByStatusSearchDateForAccount(start, end,
					sourceRef, creditNumber, paymentStatus, limit);
		}

		else if ((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))
				&& ((paymentStartDate != null && !paymentStartDate.equals("undefined")
						&& !paymentStartDate.equals("null"))
						&& (paymentEndDate != null && !paymentEndDate.equals("undefined")
								&& !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievableSearchByStatusSearchDateAndSourceRefForAcc(start,
					end, sourceRef, creditNumber, limit);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusSearchDatesAndStatusForAcc(
					paymentStatus, start, end, creditNumber, limit);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievableSearchByStatusSearchDatesForAcc(start, end,
					creditNumber, limit);
		}

		else if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository
					.findAccRecievablesSearchByStatusSearchBasedonStatusAndSourceRefForAcc(paymentStatus, sourceRef,
							creditNumber, limit);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusSearchStatusAndSourceNumberForAcc(
					paymentStatus, sourceRef, creditNumber, limit);
		}

		else if ((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchBySourceRefForAcc(sourceRef, creditNumber,
					limit);
		} else if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusForAcc(paymentStatus, creditNumber,
					limit);
		}
		return response;
	}

	@Override
	public Integer searchInAccRecievablesForCountForAccounts(String paymentStatus, String paymentStartDate,
			String paymentEndDate, String sourceRef, Integer pageNumber, Integer pageSize, String creditNumber) {
		Integer response = 0;

		if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))
				&& ((paymentStartDate != null && !paymentStartDate.equals("undefined")
						&& !paymentStartDate.equals("null"))
						&& (paymentEndDate != null && !paymentEndDate.equals("undefined")
								&& !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccReceivablesSearchByStatusSearchDateCountForAccounts(start,
					end, sourceRef, creditNumber, paymentStatus);
		}

		else if ((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))
				&& ((paymentStartDate != null && !paymentStartDate.equals("undefined")
						&& !paymentStartDate.equals("null"))
						&& (paymentEndDate != null && !paymentEndDate.equals("undefined")
								&& !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository
					.findAccRecievableSearchByStatusSearchDateAndSourceRefCountForAccount(start, end, sourceRef,
							creditNumber);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusSearchDatesAndStatusCountForAccount(
					paymentStatus, start, end, creditNumber);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))) {
			LocalDate start = LocalDate.parse(paymentStartDate);
			LocalDate end = LocalDate.parse(paymentEndDate);
			response = accountReceivablesRepository.findAccRecievableSearchByStatusSearchDatesCountForAccounts(start,
					end, creditNumber);
		}

		else if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository
					.findAccRecievablesSearchByStatusSearchBasedonStatusAndSourceRefCountForAcc(paymentStatus,
							sourceRef, creditNumber);
		} else if (((paymentStartDate != null && !paymentStartDate.equals("undefined")
				&& !paymentStartDate.equals("null"))
				&& (paymentEndDate != null && !paymentEndDate.equals("undefined") && !paymentEndDate.equals("null")))
				&& (sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository
					.findAccRecievablesSearchByStatusSearchStatusAndSourceNumberCountForAcc(paymentStatus, sourceRef,
							creditNumber);
		}

		else if ((sourceRef != null && !sourceRef.equals("undefined") && !sourceRef.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchBySourceRefCountForAcc(sourceRef,
					creditNumber);
		} else if ((paymentStatus != null && !paymentStatus.equals("undefined") && !paymentStatus.equals("null"))) {
			response = accountReceivablesRepository.findAccRecievablesSearchByStatusCountForAcc(paymentStatus,
					creditNumber);
		}

		return response;
	}

	@Override
	public JSONObject doCalculations(List<RecieptMoneyCalDTO> json) {
		int billPartialAmt = 0;
		Double billTotalDebit = 0.0;
		Double billTotalCredit = 0.0;
		JSONObject res = new JSONObject();
		for (int i = 0; i < json.size(); i++) {
			if (json.get(i).getPayment().equals("Partial")) {
				if (json.get(i).getPartialAmt() != null && json.get(i).getPartialAmt() > 0) {
					System.out.println(
							"//////////////////////////////////////////////////////////////////////////////////");
					System.out.println(json.get(i).getPartialAmt());
					billPartialAmt += json.get(i).getPartialAmt();

					if (json.get(i).getAmountReceived() != null && json.get(i).getAmountReceived() < 0.0) {
						billTotalDebit += json.get(i).getAmountReceived() != null ? 0
								: -1 * json.get(i).getAmountReceived();
					} else {
						billTotalCredit += json.get(i).getAmountReceived() != null ? json.get(i).getAmountReceived()
								: 0;
					}

					if (json.get(i).getAmountToBeReceived() != null) {
						if (json.get(i).getAmountToBeReceived() < 0.0) {
							billTotalDebit += json.get(i).getAmountToBeReceived() != null ? 0
									: -1 * json.get(i).getPartialAmt();
						} else {
							if (json.get(i).getPayment().equals("Partial")) {
								billTotalCredit += json.get(i).getAmountToBeReceived() != null
										? json.get(i).getPartialAmt()
										: 0;
							} else {
								billTotalCredit += json.get(i).getAmountToBeReceived() != null ? 0 : 0;
							}
						}

					}

				}
			} else if (json.get(i).getPayment().equals("Full")) {
				billPartialAmt += json.get(i).getAmountToBeReceived();
				if (json.get(i).getAmountReceived() != null && json.get(i).getAmountReceived() < 0.0) {
					billTotalDebit += json.get(i).getAmountReceived() != null ? 0
							: -1 * json.get(i).getAmountReceived();
				} else {
					billTotalCredit += json.get(i).getAmountReceived() != null ? json.get(i).getAmountReceived() : 0;
				}

				if (json.get(i).getAmountToBeReceived() != null) {
					if (json.get(i).getAmountToBeReceived() < 0.0) {
						billTotalDebit += json.get(i).getAmountToBeReceived() != null ? 0
								: -1 * json.get(i).getAmountToBeReceived() + json.get(i).getPartialAmt();
					} else {
						if (json.get(i).getPayment().equals("Full")) {
							billTotalCredit += json.get(i).getAmountToBeReceived() != null
									? json.get(i).getAmountToBeReceived()
									: 0;
						} else {
							billTotalCredit += json.get(i).getAmountToBeReceived() != null
									? json.get(i).getAmountToBeReceived() + json.get(i).getPartialAmt()
									: 0;
						}
					}
				}

			}

		}

		res.put("totalAmount", billPartialAmt);
		res.put("totalCredit", billTotalCredit);
		res.put("totalBill", billTotalCredit - billTotalDebit);

		return res;
	}

}
