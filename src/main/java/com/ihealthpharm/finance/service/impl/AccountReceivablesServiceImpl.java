package com.ihealthpharm.finance.service.impl;

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

			// to update amount for different payment inputs
			/*
			 * if(accountReceivables.getCashAmount()!=null ||
			 * accountReceivables.getUpiAmount() !=null ||
			 * accountReceivables.getChequeAmount() !=null ||
			 * accountReceivables.getCreditCardAmount()) {
			 * 
			 * }
			 */

			/*
			 * if(accountReceivablesRes.getPaymentStatus().equalsIgnoreCase("Paid")) {
			 * SalesModel salesData =
			 * accountReceivablesRepository.getSalesByBillCode(accountReceivablesRes.
			 * getSourceRef()); salesData.setCashAmount(salesData.getCashAmount()); }
			 */
			if (accountReceivables.getSourceType().contains("Credit Note")) {
				CreditNoteModel c = creditNoteRepo.getCreditNoteDataById(accountReceivables.getSource());
				c.setPaymentStatus("Paid");
				creditNoteRepo.save(c);
			} else if (accountReceivables.getSourceType().contains("Debit Note")) {
				DebitNoteModel d = debitNoteRepo.getDebitNoteDataById(accountReceivables.getSource());
				d.setPaymentStatus("Paid");
				debitNoteRepo.save(d);
			}

			if (accountReceivablesRes.getSourceType().equalsIgnoreCase("Sales Billing")
					&& Objects.nonNull(accountReceivablesRes.getSourceRef())
					&& accountReceivablesRes.getPaymentStatus().equalsIgnoreCase("Paid")
					&& Objects.nonNull(accountReceivablesRes.getCreditNumber())) {
				System.out.println(
						"........................................................................in 1st for loop");
				System.out.println("IF bill type sales-billing and acc rec status = paid");

				SalesModel salesRecord = accountReceivablesRepository
						.getSalesByBillCode(accountReceivablesRes.getSourceRef());
				Double creditAmount = Objects.nonNull(salesRecord.getCreditAmount()) ? salesRecord.getCreditAmount()
						: 0;

				if (creditAmount > 0) {
					System.out.println("in if of credit amont gretaer than zero");
					System.out.println(accountReceivablesRes.getPaymentType());
					if (Objects.nonNull(accountReceivablesRes.getCashAmount())) {
						System.out.println("if payment type cash");
						salesRecord.setPaymentStatus("Paid");
						Float cashAmount = Objects.nonNull(salesRecord.getCashAmount()) ? salesRecord.getCashAmount()
								: 0;

						Float paidAmount = (float) (cashAmount + creditAmount);
						System.out.println(paidAmount);
						salesRecord.setCashAmount(paidAmount);
						salesRecord.setPaidAmount(paidAmount);
						salesRecord.setBalanceAmount((float) 0);
						String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
						salesRecord.setLastUpdateUserId(lastUpdatedUserId);
						salesRecord.setCreditAmount((double) 0);
					} else if (Objects.nonNull(accountReceivablesRes.getChequeAmount())) {
						System.out.println("cheque amt");
						salesRecord.setPaymentStatus("Paid");
						Double chequeAmount = Objects.nonNull(salesRecord.getChequeAmount())
								? salesRecord.getChequeAmount()
								: 0;

						Float paidAmount = (float) (chequeAmount + creditAmount);
						salesRecord.setChequeAmount((double) paidAmount);
						LocalDate chequeDt = accountReceivablesRes.getChequeDate();
						salesRecord.setChequeDate(chequeDt.toString());
						salesRecord.setChequeNumber(accountReceivablesRes.getChequeNumber());
						salesRecord.setPaidAmount(paidAmount);
						salesRecord.setBalanceAmount((float) 0);
						String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
						salesRecord.setLastUpdateUserId(lastUpdatedUserId);
						salesRecord.setCreditAmount((double) 0);
					} else if (Objects.nonNull(accountReceivablesRes.getCreditCardAmount())) {
						System.out.println("credit card");
						salesRecord.setPaymentStatus("Paid");
						Float creditCardAmount = Objects.nonNull(salesRecord.getCreditCardAmount())
								? salesRecord.getCreditCardAmount()
								: 0;

						Float paidAmount = (float) (creditCardAmount + creditAmount);

						salesRecord.setCreditCardAmount(paidAmount);
						salesRecord.setCreditCardNo(accountReceivablesRes.getCreditCardNo());
						salesRecord.setCreditCardAuthNo(accountReceivablesRes.getCardAuthCode());
						salesRecord.setPaidAmount(paidAmount);
						salesRecord.setBalanceAmount((float) 0);
						String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
						salesRecord.setLastUpdateUserId(lastUpdatedUserId);
						salesRecord.setCreditAmount((double) 0);
					} else if (Objects.nonNull(accountReceivablesRes.getUpiAmount())) {
						System.out.println("upi amount ...//");
						salesRecord.setPaymentStatus("Paid");
						Float upiAmount = Objects.nonNull(salesRecord.getUpiAmount()) ? salesRecord.getUpiAmount() : 0;

						Float paidAmount = (float) (upiAmount + creditAmount);

						salesRecord.setUpiAmount(paidAmount);
						salesRecord.setUpiPhoneNo(accountReceivablesRes.getUpiPhoneNo());
						salesRecord.setUpiTransactionId(accountReceivablesRes.getUpiAuthCode());
						salesRecord.setPaidAmount(paidAmount);
						salesRecord.setBalanceAmount((float) 0);
						String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
						salesRecord.setLastUpdateUserId(lastUpdatedUserId);
						salesRecord.setCreditAmount((double) 0);
					}

					else if (accountReceivablesRes.getPaymentType().equalsIgnoreCase("Credit Note")) {
						System.out.println(
								"???????????????????????????????????????????????????????????????????????????????????????????????????????????");
						System.out.println("in sales fully paid with crrteedeffbfvhjfvfdvkdvkjdkjvv");
						System.out.println(accountReceivablesRes.getAmountReceived());
						salesRecord.setPaymentStatus("Paid");

						salesRecord.setBalanceAmount((float) 0);
						String lastUpdatedUserId = Integer.toString(accountReceivablesRes.getLastUpdateUser());
						salesRecord.setLastUpdateUserId(lastUpdatedUserId);
						salesRecord.setCreditNoteAmount((double) (accountReceivablesRes.getAmountReceived()));
						salesRecord.setSalesCreditRefNo(accountReceivables.getSourceRef());
						salesRecord.setCreditAmount((double) 0);
						salesRecord.setPaidAmount(Objects.nonNull(salesRecord.getPaidAmount())
								? salesRecord.getPaidAmount() + accountReceivablesRes.getAmountReceived()
								: accountReceivablesRes.getAmountReceived());
						System.out.println("<>>?<<?<>><><>");
						System.out.println(salesRecord.getBalanceAmount());
						System.out.println(salesRecord);

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
					System.out.println(salesRes.getBalanceAmount() + " bal amt     +:");
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

		}

		for (int i = 0; i < accountsReceivables.size(); i++) {
			for (int j = 0; j < accountsReceivables.size(); j++) {
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
									System.out.println(res);
								} else {
									System.out.println("elsddsfbddshvdhb sjkvbdkjb");
									Integer res = salesRepository.updateUpiAmountBasedOnStatusId(creditNoteAmount,
											billNo);
									System.out.println(res);
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
