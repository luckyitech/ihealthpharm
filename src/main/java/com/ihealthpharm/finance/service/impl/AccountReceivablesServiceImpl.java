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

		Boolean creditNoteTransaction = false;
		String CustomerName="";

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

			// if it is credit note record
			if (accountReceivables.getPaymentType().equals("Credit Note")) {
				creditNoteTransaction = true;
			} else if (Objects.nonNull(accountReceivables.getPartiallyPaid())) {
				if (accountReceivables.getPartiallyPaid().equals("Credit Note")) {
					CustomerName=accountReceivables.getCustomerName();
					creditNoteTransaction = true;
				}
			}
		}

		if (creditNoteTransaction) {
			Double amountRecived = 0.0;
			String creditNoteRefNo = "";
			SalesModel salesRecord = null;
			Double anotherModePayments = 0.0;
			DecimalFormat df = new DecimalFormat(".##");
			for (AccountReceivablesModel accountReceivables : accountsReceivables) {

				if (!accountReceivables.getSourceType().contains("Sales Billing")) {

					if (accountReceivables.getSourceType().contains("Credit")) {
						if (accountReceivables.getAmountReceived() > 0) {
							amountRecived += accountReceivables.getAmountReceived();
						} else if (accountReceivables.getAmountReceived() < 0) {
							amountRecived += -1 * accountReceivables.getAmountReceived();
						}

						if (creditNoteRefNo.equals("")) {
							creditNoteRefNo = accountReceivables.getSourceRef();
						} else {
							creditNoteRefNo += "," + accountReceivables.getSourceRef();
						}
					}
				} else {
					if (Objects.nonNull(accountReceivables.getSourceRef())) {
						salesRecord = accountReceivablesRepository
								.getSalesByBillCode(accountReceivables.getSourceRef().trim());
						String lastUpdatedUserId = Integer.toString(accountReceivables.getLastUpdateUser());
						salesRecord.setLastUpdateUserId(lastUpdatedUserId);
					}
				}

				if (Objects.nonNull(salesRecord)) {
					if (accountReceivables.getPaymentType().equals("Card")) {
						anotherModePayments = (double) accountReceivables.getCreditCardAmount();
						salesRecord.setCreditCardAmount(accountReceivables.getCreditCardAmount());
						salesRecord.setCreditCardNo(accountReceivables.getCreditCardNo());
						salesRecord.setCreditCardAuthNo(accountReceivables.getCardAuthCode());

					} else if (accountReceivables.getPaymentType().equals("Cash")) {
						anotherModePayments = (double) accountReceivables.getCashAmount();
						salesRecord.setCashAmount(accountReceivables.getCashAmount());

					} else if ((accountReceivables.getPaymentType().equals("MPesa"))) {
						anotherModePayments = (double) accountReceivables.getUpiAmount();
						salesRecord.setUpiAmount(accountReceivables.getUpiAmount());
						salesRecord.setUpiPhoneNo(accountReceivables.getUpiPhoneNo());
						salesRecord.setUpiTransactionId(accountReceivables.getUpiAuthCode());

					} else if ((accountReceivables.getPaymentType().equals("Cheque"))) {
						anotherModePayments = (double) accountReceivables.getChequeAmount();
						salesRecord.setChequeAmount(accountReceivables.getChequeAmount());
						LocalDate chequeDt = accountReceivables.getChequeDate();
						salesRecord.setChequeDate(chequeDt.toString());
						salesRecord.setChequeNumber(accountReceivables.getChequeNumber());
					}

					double bal = salesRecord.getNetAmount() - Double.parseDouble(df.format(amountRecived));
					System.out.println(bal + "balancerfdfcdvd");
					Double balance = 0.0;
					System.out.println(balance + "balancerfdfcdvd");
					if (Objects.nonNull(anotherModePayments)) {
						balance = ((double) salesRecord.getNetAmount())
								- (Double.parseDouble(df.format(anotherModePayments))
										+ Double.parseDouble(df.format(amountRecived)));
					} else {
						balance = (double) bal;
					}
					System.out.println(balance + "final bal");
					if (balance > 0) {
						salesRecord.setBalanceAmount((float) bal);
						salesRecord.setPaymentStatus("Partially Paid");
						salesRecord
								.setPaidAmount((float) Double.parseDouble(df.format(salesRecord.getNetAmount() - bal)));
						double creditAmt = salesRecord.getCreditAmount()
								- (Double.parseDouble(df.format(amountRecived)));
						System.out.println(creditAmt);
						salesRecord.setCreditAmount((Double.parseDouble(df.format(creditAmt))));
						salesRecord.setCreditNoteAmount((double) Double.parseDouble(df.format(amountRecived)));
						salesRecord.setSalesCreditRefNo(creditNoteRefNo);
					} else {
						salesRecord.setBalanceAmount((float) 0);
						salesRecord.setPaymentStatus("Paid");
						salesRecord.setCreditNoteAmount((double) Double.parseDouble(df.format(amountRecived)));
						salesRecord.setSalesCreditRefNo(creditNoteRefNo);
						salesRecord.setCreditAmount((double) 0);
						salesRecord.setPaidAmount(salesRecord.getNetAmount());
					}

					SalesModel salesRes = salesRepository.save(salesRecord);
					System.out.println(salesRes.getBillCode());
					if (Objects.nonNull(salesRes)) {
						MasterAccountModel masterAccObj=null;
						if(Objects.nonNull(salesRes.getCreditAccountNo())) {
							if(Objects.nonNull(salesRes.getCreditAccountNo())) {
						 masterAccObj = masterAccRepo
								.getDataByMasterCreditNumber(salesRes.getCreditAccountNo());
							}
						}
						if (Objects.nonNull(masterAccObj)) {
							System.out.println(masterAccObj.getCreditLimitLeft());
							Double creditAmountUpdate=0.0;
							if(salesRes.getPaymentStatus().equals("Paid")) {
								System.out.println("if case for sales billing");
								creditAmountUpdate=(double) Double.parseDouble(df.format(salesRes.getNetAmount()));
								System.out.println(creditAmountUpdate);
							}else {
								System.out.println("else case for sales billing");
								creditAmountUpdate=(double) Double.parseDouble(df.format(amountRecived));
								System.out.println(creditAmountUpdate);
							}
							double amount= (double) Double.parseDouble(df.format(creditAmountUpdate+(double)masterAccObj.getCreditLimitLeft()));
							 masterAccObj.setCreditLimitLeft((int)amount);
							masterAccRepo.save(masterAccObj);

						}
					}

				}else {
					if(!CustomerName.equals("")) {
						System.out.println(CustomerName+"....,,,,,,nnnnnnnnnnnnnnyyyyyyyyyyyyyy");
						MasterAccountModel masterAccObj =null;
						SalesModel salesResponse=null;
								if(Objects.nonNull(accountReceivables.getBillRefNo())) {
									salesResponse=accountReceivablesRepository.getSalesByBillCode(accountReceivables.getBillRefNo());
									System.out.println(salesResponse.getCreditAccountNo() +"creditr acc no");
									if(Objects.nonNull(salesResponse.getCreditAccountNo())) {
									masterAccObj=masterAccRepo.getDataByMasterCreditNumber(salesResponse.getCreditAccountNo());
									System.out.println(masterAccObj.getCreditDaysLeft());
									}
								}else {
									salesResponse=salesRepository.getSalesRecordById(accountReceivables.getSalesBillId());
									if(Objects.nonNull(salesResponse.getCreditAccountNo())) {
									masterAccObj=masterAccRepo.getDataByMasterCreditNumber(salesResponse.getCreditAccountNo());
									}
								}
								
								if(Objects.nonNull(masterAccObj)) {
									System.out.println("master accc obj  not null");
									if(salesResponse.getPaymentStatus().equalsIgnoreCase("Partially Paid")) {
										//double amt=(double) Double.parseDouble(df.format(salesResponse.getPaidAmount()));  
										double amt=(double) Double.parseDouble(df.format(-1*accountReceivables.getAmountReceived()));
										double amount= (double) Double.parseDouble(df.format(amt+(double)masterAccObj.getCreditLimitLeft()));
										 masterAccObj.setCreditLimitLeft((int)amount);
									}else if(salesResponse.getPaymentStatus().equalsIgnoreCase("Paid")) {
										//double amt=(double) Double.parseDouble(df.format(salesResponse.getNetAmount()));
										double amt=(double) Double.parseDouble(df.format(-1*accountReceivables.getAmountReceived()));
										double amount= (double) Double.parseDouble(df.format(amt+(double)masterAccObj.getCreditLimitLeft()));
										masterAccObj.setCreditLimitLeft((int)amount);
									}else {
										System.out.println("credit note else case ");
										System.out.println(accountReceivables.getAmountReceived());
										double amt=(double) Double.parseDouble(df.format(-1*accountReceivables.getAmountReceived()));
										double amount= (double) Double.parseDouble(df.format(amt+(double)masterAccObj.getCreditLimitLeft()));
										masterAccObj.setCreditLimitLeft((int)amount);
									}
									masterAccRepo.save(masterAccObj);
								}
					}
				}
			}
		} else {
			System.out.println("[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]][]]]]]]]]]]]]]]]]]]]]]]]]]]]]]");
			for (AccountReceivablesModel accountReceivables : accountsReceivables) {

				// to update the sales billing by using sales return credit note
				if (accountReceivables.getPaymentStatus().equalsIgnoreCase("Paid")) {
					System.out.println(accountReceivables.getPaymentType() + " ././../...");

					// Below condition is for partial payment of sales bill with credit note payment
					// type
					if (accountReceivables.getPartiallyPaid() == null) {
						// from accont recievables the bill is completely paid
						// if the payment type is credit note
						if (accountReceivables.getPaymentType().equals("Credit Note")) {

							DecimalFormat df = new DecimalFormat(".##");

						} else if (accountReceivables.getSourceType().equals("Sales Returns - Credit Note")
								&& accountReceivables.getPaymentStatus().equals("Paid")) {
							System.out.println("skipped paid with credut note");
							SalesModel salesRecord = null;
							Double creditAmount = 0.0;
							if (Objects.nonNull(accountReceivables.getSalesBillId())) {
								salesRecord = salesRepository.getSalesRecordById(accountReceivables.getSalesBillId());
								creditAmount = Objects.nonNull(salesRecord.getCreditAmount())
										? salesRecord.getCreditAmount()
										: 0;
							} else {
								salesRecord = accountReceivablesRepository
										.getSalesByBillCode(accountReceivables.getBillRefNo().trim());
								creditAmount = Objects.nonNull(salesRecord.getCreditAmount())
										? salesRecord.getCreditAmount()
										: 0;
							}

							System.out.println(creditAmount + " :sales bill credit amt 3rd");
							double finalAmt = salesRecord.getNetAmount()
									- (-1 * accountReceivables.getAmountReceived());
							DecimalFormat df = new DecimalFormat(".##");
							double balAmt = Double.parseDouble(df.format(finalAmt));
							System.out.println(balAmt);
							if (balAmt != 0) {
								salesRecord.setBalanceAmount((float) balAmt);
								salesRecord.setPaymentStatus("Partially Paid");
								salesRecord.setPaidAmount(salesRecord.getNetAmount() - salesRecord.getBalanceAmount());
								String lastUpdatedUserId = Integer.toString(accountReceivables.getLastUpdateUser());
								salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								double creditAmt = balAmt; /*
															 * (salesRecord.getCreditAmount() != null ?
															 * salesRecord.getCreditAmount() : 0) - (-1 *
															 * accountReceivablesRes.getAmountReceived());
															 */
								System.out.println(creditAmt);
								salesRecord.setCreditAmount((Double.parseDouble(df.format(creditAmt))));
								System.out.println(-1 * accountReceivables.getAmountReceived());

								System.out.println("else casre of partial pay");
								if (accountReceivables.getPaymentcreditRefNo() != null) {
									System.out.println("in cred'it ref of if 1");
									salesRecord.setSalesCreditRefNo(accountReceivables.getPaymentcreditRefNo());
									salesRecord.setCreditNoteAmount((double) Double
											.parseDouble(df.format(-1 * accountReceivables.getAmountReceived())));
								} else {
									System.out.println("in else of... 3");
									if (salesRecord.getBillCode().equals(accountReceivables.getSourceRef().trim())) {
										System.out.println(salesRecord.getSalesCreditRefNo() != null
												? salesRecord.getSalesCreditRefNo()
												: "..88..s");
										if (Objects.isNull(salesRecord.getSalesCreditRefNo())) {
											salesRecord.setChequeAmount((double) Double.parseDouble(
													df.format(-1 * accountReceivables.getAmountReceived())));
											salesRecord.setChequeNumber(accountReceivables.getReceiptNumber());
											System.out.println("dwswdwegvv re");
											salesRecord.setCreditNoteAmount((double) 0);
											LocalDate chequeDt = LocalDate.now();
											salesRecord.setChequeDate(chequeDt.toString());
										}
									} else {
										salesRecord.setSalesCreditRefNo(accountReceivables.getSourceRef());
										salesRecord.setCreditNoteAmount((double) Double
												.parseDouble(df.format(-1 * accountReceivables.getAmountReceived())));
									}

								}
								System.out.println(salesRecord.getCreditNoteAmount() + "credit npoegu g amhyh");
							} else {
								salesRecord.setBalanceAmount((float) 0);
								salesRecord.setPaymentStatus("Paid");

								if (accountReceivables.getPaymentcreditRefNo() != null) {
									System.out.println("in credit ref of if 2");
									salesRecord.setSalesCreditRefNo(accountReceivables.getPaymentcreditRefNo());
									salesRecord.setCreditNoteAmount(
											(double) (-1 * accountReceivables.getAmountReceived()));
								} else {
									System.out.println("in else of... 2");
									if (salesRecord.getBillCode().equals(accountReceivables.getSourceRef().trim())) {
										System.out.println(salesRecord.getSalesCreditRefNo() != null
												? salesRecord.getSalesCreditRefNo()
												: "223..88..s");
										if (Objects.isNull(salesRecord.getSalesCreditRefNo())) {
											salesRecord.setChequeAmount((double) Double.parseDouble(
													df.format(-1 * accountReceivables.getAmountReceived())));
											salesRecord.setChequeNumber(accountReceivables.getReceiptNumber());
											System.out.println("eskej i cnien");
											salesRecord.setCreditNoteAmount((double) 0);
											LocalDate chequeDt = LocalDate.now();
											salesRecord.setChequeDate(chequeDt.toString());
										}
									} else {
										salesRecord.setSalesCreditRefNo(accountReceivables.getSourceRef());
										salesRecord.setCreditNoteAmount(
												(double) (-1 * accountReceivables.getAmountReceived()));
									}

								}

								// salesRecord.setSalesCreditRefNo(accountReceivables.getSourceRef());
								salesRecord.setCreditAmount((double) 0);
								String lastUpdatedUserId = Integer.toString(accountReceivables.getLastUpdateUser());
								salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								salesRecord.setPaidAmount(Objects.nonNull(salesRecord.getPaidAmount())
										? salesRecord.getPaidAmount() + (-1 * accountReceivables.getAmountReceived())
										: (-1 * accountReceivables.getAmountReceived()));

							}
							System.out.println(
									salesRecord.getCreditNoteAmount() != null ? salesRecord.getCreditNoteAmount()
											: " no lllll");
							salesRepository.save(salesRecord);
						} else if (accountReceivables.getSourceType().equals("Credit Note")
								&& accountReceivables.getPaymentStatus().equals("Paid")) {
							System.out.println("new if conditijbjjb");
							SalesModel salesData = null;
							Double creditAmount = 0.0;
							if (Objects.nonNull(accountReceivables.getSalesBillId())) {
								salesData = salesRepository.getSalesRecordById(accountReceivables.getSalesBillId());
								creditAmount = Objects.nonNull(salesData.getCreditAmount())
										? salesData.getCreditAmount()
										: 0;
							} else {
								System.out.println(accountReceivables.getBillRefNo());
								salesData = accountReceivablesRepository
										.getSalesByBillCode(accountReceivables.getBillRefNo().trim());
								System.out.println(salesData);
								creditAmount = Objects.nonNull(salesData.getCreditAmount())
										? salesData.getCreditAmount()
										: 0;
							}

							System.out.println(creditAmount + " :sales bill credit ,.ambad 3rd");
							double recievedAmt = -1 * accountReceivables.getAmountReceived();

							double paidAmt = (double) (salesData.getPaidAmount() + recievedAmt);
							DecimalFormat df = new DecimalFormat(".##");
							System.out.println("////");
							String lastUpdatedUserId = Integer.toString(accountReceivables.getLastUpdateUser());
							salesData.setLastUpdateUserId(lastUpdatedUserId);
							if (salesData.getBalanceAmount() == recievedAmt) {
								System.out.println(salesData.getPaidAmount() + " paid amt");
								System.out.println(recievedAmt + "... recieved am");
								salesData.setBalanceAmount((float) 0);
								salesData.setPaymentStatus("Paid");
								salesData.setPaidAmount((float) Double.parseDouble(df.format(paidAmt)));
								double creditAmt = (salesData.getCreditAmount() != null ? salesData.getCreditAmount()
										: 0) - (recievedAmt);
								salesData.setCreditAmount((Double.parseDouble(df.format(creditAmt))));
								// doubt here struck
								double creditNoteAmt = salesData.getCreditNoteAmount() != null
										? salesData.getCreditNoteAmount()
										: 0;
								System.out.println(creditNoteAmt);
								double creditNOteAmttoFixed = (double) Double.parseDouble(df.format(creditNoteAmt));
								double d = (double) Double.parseDouble(df.format(recievedAmt));
								double amt = (double) (creditNOteAmttoFixed + d);
								System.out.println(amt + "final credit note amt" + Double.parseDouble(df.format(amt)));
								// salesData.setCreditNoteAmount((double) Double.parseDouble(df.format(amt)));
								String salesCreditRef = salesData.getSalesCreditRefNo() != null
										? salesData.getSalesCreditRefNo()
										: " ";
								salesData.setSalesCreditRefNo(salesCreditRef + "," + accountReceivables.getSourceRef());
							} else {
								System.out.println("in else casdejnjjjnbjb amm");
								if (Objects.nonNull(accountReceivables.getBillRefNo())) {
									if (accountReceivables.getBillRefNo().equals(salesData.getBillCode())) {
										salesData.setSalesCreditRefNo(accountReceivables.getSourceRef());
										salesData.setCreditNoteAmount(
												(double) Double.parseDouble(df.format(recievedAmt)));
									}
								}

							}
							salesRepository.save(salesData);
						}
					}

					if (accountReceivables.getSourceType().equalsIgnoreCase("Sales Billing")
							&& Objects.nonNull(accountReceivables.getSourceRef())
							&& accountReceivables.getPaymentStatus().equalsIgnoreCase("Paid")
							&& Objects.nonNull(accountReceivables.getCreditNumber())) {
						System.out.println(
								"........................................................................in 1st for loop");
						DecimalFormat df = new DecimalFormat(".##");
						System.out.println(accountReceivables);
						SalesModel salesRecord = accountReceivablesRepository
								.getSalesByBillCode(accountReceivables.getSourceRef().trim());
						Double creditAmount = Objects.nonNull(salesRecord.getCreditAmount())
								? salesRecord.getCreditAmount()
								: 0;
						System.out.println(creditAmount + ":crrrredit amt");
						if (creditAmount > 0) {
							System.out.println("in if of credit amont gretaer than zero");

							if (Objects.nonNull(accountReceivables.getCashAmount())) {
								System.out.println("if payment type cash");
								salesRecord.setPaymentStatus("Paid");
								Float cashAmount = Objects.nonNull(salesRecord.getCashAmount())
										? salesRecord.getCashAmount()
										: 0;
								System.out.println(salesRecord.getBalanceAmount() + "balance antedcd");
								if (salesRecord.getBalanceAmount() != 0) {

									if (salesRecord.getCreditNoteAmount() != null) {
										Float paidAmount = (float) (cashAmount + creditAmount);
										salesRecord.setCashAmount((float) paidAmount);
										String lastUpdatedUserId = Integer
												.toString(accountReceivables.getLastUpdateUser());
										salesRecord.setLastUpdateUserId(lastUpdatedUserId);

										double amt = Double.parseDouble(df.format(paidAmount))
												+ +salesRecord.getCreditNoteAmount();
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
												salesRecord.setPaidAmount((float) salesRecord.getNetAmount());
												salesRecord.setCreditAmount((double) 0);
											} else {
												System.out.println(salesRecord.getCreditAmount() + " cedit amt");
												salesRecord.setPaymentStatus("Partially Paid");
												salesRecord.setPaidAmount(
														(float) Double.parseDouble(df.format((salesRecord.getNetAmount()
																- salesRecord.getBalanceAmount()))));
												salesRecord.setCreditAmount((double) bal);
											}
											salesRecord.setBalanceAmount((float) bal);

											/*
											 * if (salesRecord.getCreditNoteAmount() != null) { double amtPaid =
											 * salesRecord.getCreditNoteAmount() + paidAmount;
											 * salesRecord.setPaidAmount((float) amtPaid); }
											 */

											System.out.println(salesRecord.getBalanceAmount());
										}
									} else {
										System.out.println(accountReceivables);
										Float paidAmount = (float) (cashAmount + creditAmount);
										System.out.println(paidAmount);
										salesRecord.setPaymentStatus("Paid");
										salesRecord.setCashAmount((float) paidAmount);
										salesRecord.setCreditAmount((double) 0);
										salesRecord.setPaidAmount((float) salesRecord.getNetAmount());
										salesRecord.setBalanceAmount((float) 0);
										String lastUpdatedUserId = Integer
												.toString(accountReceivables.getLastUpdateUser());
										salesRecord.setLastUpdateUserId(lastUpdatedUserId);
									}
								} else {
									System.out.println("eksefeucgugug");
									salesRecord.setPaymentStatus("Paid");
									salesRecord.setCreditAmount((double) 0);
									salesRecord.setPaidAmount((float) salesRecord.getNetAmount());
									salesRecord.setBalanceAmount((float) 0);
									String lastUpdatedUserId = Integer.toString(accountReceivables.getLastUpdateUser());
									salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								}

							} else if (Objects.nonNull(accountReceivables.getChequeAmount())) {
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
										LocalDate chequeDt = accountReceivables.getChequeDate();
										salesRecord.setChequeDate(chequeDt.toString());
										salesRecord.setChequeNumber(accountReceivables.getChequeNumber());
										String lastUpdatedUserId = Integer
												.toString(accountReceivables.getLastUpdateUser());
										salesRecord.setLastUpdateUserId(lastUpdatedUserId);
										System.out.println(paidAmount + " : just now paid");
										System.out.println(salesRecord.getCreditNoteAmount() + " :credita mt");
										double amt = Double.parseDouble(df.format(paidAmount))
												+ +salesRecord.getCreditNoteAmount();
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
												salesRecord.setPaidAmount(
														salesRecord.getNetAmount() - salesRecord.getBalanceAmount());
											} else {
												System.out.println(salesRecord.getCreditAmount() + " cedit amt");
												salesRecord.setPaymentStatus("Partially Paid");
												salesRecord.setCreditAmount((double) bal);
											}
											salesRecord.setBalanceAmount((float) bal);

											/*
											 * if (salesRecord.getCreditNoteAmount() != null) { double amtPaid =
											 * salesRecord.getCreditNoteAmount() + paidAmount;
											 * salesRecord.setPaidAmount((float) amtPaid); }
											 */
										}

									} else {
										System.out.println(accountReceivables.getSourceType() + " ..,.,..  "
												+ salesRecord.getCreditNoteAmount());
										if (accountReceivables.getSourceType().equals("Credit Note")) {
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

											salesRecord.setSalesCreditRefNo(accountReceivables.getSourceRef());
											salesRecord.setBalanceAmount((float) 0);
											LocalDate chequeDt = accountReceivables.getChequeDate();
											salesRecord.setChequeDate(chequeDt.toString());
											salesRecord.setChequeNumber(accountReceivables.getChequeNumber());
											String lastUpdatedUserId = Integer
													.toString(accountReceivables.getLastUpdateUser());
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
											LocalDate chequeDt = accountReceivables.getChequeDate();
											salesRecord.setChequeDate(chequeDt.toString());
											salesRecord.setChequeNumber(accountReceivables.getChequeNumber());
											String lastUpdatedUserId = Integer
													.toString(accountReceivables.getLastUpdateUser());
											salesRecord.setLastUpdateUserId(lastUpdatedUserId);
										}
									}
								} else {
									salesRecord.setPaymentStatus("Paid");
									salesRecord.setCreditAmount((double) 0);
									salesRecord.setPaidAmount((float) salesRecord.getNetAmount());
									salesRecord.setBalanceAmount((float) 0);
									String lastUpdatedUserId = Integer.toString(accountReceivables.getLastUpdateUser());
									salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								}
							} else if (Objects.nonNull(accountReceivables.getCreditCardAmount())) {
								System.out.println("credit card");

								if (salesRecord.getBalanceAmount() != 0) {
									Float creditCardAmount = Objects.nonNull(salesRecord.getCreditCardAmount())
											? salesRecord.getCreditCardAmount()
											: 0;
									if (salesRecord.getCreditNoteAmount() != null) {

										Float paidAmount = (float) (creditCardAmount + creditAmount);
										salesRecord.setCreditCardAmount(paidAmount);
										salesRecord.setCreditCardNo(accountReceivables.getCreditCardNo());
										salesRecord.setCreditCardAuthNo(accountReceivables.getCardAuthCode());
										String lastUpdatedUserId = Integer
												.toString(accountReceivables.getLastUpdateUser());
										salesRecord.setLastUpdateUserId(lastUpdatedUserId);
										// after doing partial amt again paying the full amt with any payment type
										System.out.println(paidAmount + " : just now paid");
										System.out.println(salesRecord.getCreditNoteAmount() + " :credita mt");
										double amt = Double.parseDouble(df.format(paidAmount))
												+ +salesRecord.getCreditNoteAmount();
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
										salesRecord.setCreditCardNo(accountReceivables.getCreditCardNo());
										salesRecord.setCreditCardAuthNo(accountReceivables.getCardAuthCode());
										salesRecord.setCreditAmount((double) 0);
										salesRecord.setPaidAmount((float) paidAmount);
										salesRecord.setBalanceAmount((float) 0);
										String lastUpdatedUserId = Integer
												.toString(accountReceivables.getLastUpdateUser());
										salesRecord.setLastUpdateUserId(lastUpdatedUserId);
									}
								} else {
									salesRecord.setPaymentStatus("Paid");
									salesRecord.setCreditAmount((double) 0);
									salesRecord.setPaidAmount((float) salesRecord.getNetAmount());
									salesRecord.setBalanceAmount((float) 0);
									String lastUpdatedUserId = Integer.toString(accountReceivables.getLastUpdateUser());
									salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								}

							} else if (Objects.nonNull(accountReceivables.getUpiAmount())) {
								System.out.println("upi amount ...//");
								Float upiAmount = Objects.nonNull(salesRecord.getUpiAmount())
										? salesRecord.getUpiAmount()
										: 0;

								if (salesRecord.getCreditNoteAmount() != null) {

									Float paidAmount = (float) (upiAmount + creditAmount);
									String lastUpdatedUserId = Integer.toString(accountReceivables.getLastUpdateUser());
									salesRecord.setLastUpdateUserId(lastUpdatedUserId);
									salesRecord.setUpiAmount(paidAmount);
									salesRecord.setUpiPhoneNo(accountReceivables.getUpiPhoneNo());
									salesRecord.setUpiTransactionId(accountReceivables.getUpiAuthCode());

									System.out.println(paidAmount + " : just now paid");
									System.out.println(salesRecord.getCreditNoteAmount() + " :credita mt");

									double amt = Double.parseDouble(df.format(paidAmount))
											+ salesRecord.getCreditNoteAmount();
									System.out.println(amt);
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
									salesRecord.setUpiPhoneNo(accountReceivables.getUpiPhoneNo());
									salesRecord.setUpiTransactionId(accountReceivables.getUpiAuthCode());
									salesRecord.setCreditAmount((double) 0);
									salesRecord.setPaidAmount((float) paidAmount);
									salesRecord.setBalanceAmount((float) 0);
									String lastUpdatedUserId = Integer.toString(accountReceivables.getLastUpdateUser());
									salesRecord.setLastUpdateUserId(lastUpdatedUserId);
								}

							}
							SalesModel salesData= salesRepository.save(salesRecord);
							
							
							if (Objects.nonNull(salesData)) {
								MasterAccountModel masterAccObj=null;
								if(Objects.nonNull(salesData.getCreditAccountNo())) {
									if(Objects.nonNull(salesData.getCreditAccountNo())) {
								 masterAccObj = masterAccRepo
										.getDataByMasterCreditNumber(salesData.getCreditAccountNo());
									}
								}
								if (Objects.nonNull(masterAccObj)) {
									System.out.println(masterAccObj.getCreditLimitLeft());
									Double creditAmountUpdate=0.0;
									
										System.out.println("sales billing");
										creditAmountUpdate=(double) Double.parseDouble(df.format(creditAmount));
									
									double amount= (double) Double.parseDouble(df.format(creditAmountUpdate+(double)masterAccObj.getCreditLimitLeft()));
									 masterAccObj.setCreditLimitLeft((int)amount);
									masterAccRepo.save(masterAccObj);

								}
							}
							
							
						}
					}

					log.info("AccountReceivables data with ID : " + accountReceivables.getAccountReceivablesId()
							+ " updated succesfully");

				}

				System.out.println("???????????????????????????????????????????");
				// if payment is partially paid
				if (accountReceivables.getSourceType().equalsIgnoreCase("Sales Billing")
						&& accountReceivables.getPaymentStatus().equalsIgnoreCase("Partially Paid")) {

					System.out.println("acc rec source tyep and seconf if in for loop");

					SalesModel salesRes = salesRepository.getSalesRecordByNo(accountReceivables.getSourceRef().trim());

					salesRes.setBalanceAmount(salesRes.getBalanceAmount() - accountReceivables.getPartialAmt());
					Double creditAmount = Objects.nonNull(salesRes.getCreditAmount()) ? salesRes.getCreditAmount() : 0;
					System.out.println(creditAmount + " :sales bill credit amt 4th");
					if (accountReceivables.getPaymentType() != null) {
						System.out.println("if payment is not nukl");
						DecimalFormat df = new DecimalFormat(".##");
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

							salesRes.setChequeAmount(
									(salesRes.getChequeAmount() != null && salesRes.getChequeAmount() > 0)
											? salesRes.getChequeAmount() + accountReceivables.getPartialAmt()
											: accountReceivables.getPartialAmt());
							LocalDate chequeDt = accountReceivables.getChequeDate();
							salesRes.setChequeDate(chequeDt.toString());
							salesRes.setChequeNumber(accountReceivables.getChequeNumber());

						}
						if (accountReceivables.getAmountToBeReceived() != null) {
							System.out.println(salesRes.getCreditAmount());
							System.out.println(accountReceivables.getAmountReceived());

							salesRes.setCreditAmount((double) Double.parseDouble(
									df.format((salesRes.getCreditAmount() - accountReceivables.getAmountReceived()))));
							salesRes.setBalanceAmount(
									(float) Double.parseDouble(df.format((salesRes.getCreditAmount()))));
							salesRes.setPaidAmount((float) Double
									.parseDouble(df.format((salesRes.getNetAmount() - salesRes.getBalanceAmount()))));
							double bal = salesRes.getBalanceAmount();
							if (bal > 0) {
								salesRes.setPaymentStatus("Partially Paid");
							} else {
								salesRes.setPaymentStatus("Paid");
							}
						} else {
							salesRes.setCreditAmount(salesRes.getCreditAmount() - accountReceivables.getPartialAmt());
							System.out.println(
									salesRes.getBalanceAmount() + " bal amt     +:" + salesRes.getCreditAmount());
							if (salesRes.getBalanceAmount() > 0) {
								System.out.println("in bal if");
								salesRes.setPaymentStatus("Partially Paid");

							} else if (salesRes.getBalanceAmount() == 0) {
								System.out.println("in else of bal");
								salesRes.setPaymentStatus("Paid");
							}
						}
						System.out.println(salesRes.getCreditNoteAmount() != null ? salesRes.getCreditNoteAmount()
								: " no nillllllll");
						salesRepository.save(salesRes);
					}
				}

				/*
				 * for (int i = 0; i < accountsReceivables.size(); i++) { for (int j = 0; j <
				 * accountsReceivables.size(); j++) { if (i != j) { DecimalFormat df = new
				 * DecimalFormat(".##"); if
				 * (Objects.nonNull(accountsReceivables.get(i).getBillRefNo())) { if
				 * (accountsReceivables.get(i).getBillRefNo()
				 * .equals(accountsReceivables.get(j).getSourceRef().trim())) { if
				 * (accountsReceivables.get(i).getSourceType().toLowerCase().
				 * contains("credit note")) {
				 * 
				 * Double creditNoteAmount = -1
				 * accountsReceivables.get(i).getAmountReceived().doubleValue(); String billNo =
				 * accountsReceivables.get(i).getBillRefNo(); SalesModel salesRecord =
				 * accountReceivablesRepository
				 * .getSalesByBillCode(accountsReceivables.get(i).getBillRefNo().trim());
				 * 
				 * if (salesRecord.getBalanceAmount() == 0) { String paymentStatus = "Paid";
				 * Integer res = salesRepository.updateUpiAmountBasedOnId(creditNoteAmount,
				 * paymentStatus, billNo); } else { Integer res =
				 * salesRepository.updateUpiAmountBasedOnStatusId(creditNoteAmount, billNo); }
				 * 
				 * if (salesRecord.getNetAmount().doubleValue() == creditNoteAmount) {
				 * System.out.println(
				 * "if both amounts are equalmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"); if
				 * (Objects.nonNull(salesRecord.getCashAmount())) {
				 * salesRecord.setCashAmount(null); } else if
				 * (Objects.nonNull(salesRecord.getUpiAmount())) {
				 * salesRecord.setUpiAmount(null); salesRecord.setUpiPhoneNo(null);
				 * salesRecord.setUpiTransactionId(null); } else if
				 * (Objects.nonNull(salesRecord.getCreditCardAmount())) {
				 * salesRecord.setCreditCardAuthNo(null); salesRecord.setCreditCardAmount(null);
				 * salesRecord.setCreditCardNo(null); } else if
				 * (Objects.nonNull(salesRecord.getChequeAmount())) {
				 * salesRecord.setChequeAmount(null); salesRecord.setChequeDate(null);
				 * salesRecord.setChequeNumber(null); } else if
				 * (accountsReceivables.get(i).getPaymentType()
				 * .equalsIgnoreCase("Credit Note")) {
				 * System.out.println("<><><>_+_+++++++++++++++++++++++++");
				 * salesRecord.setBalanceAmount((float) 0); String lastUpdatedUserId = Integer
				 * .toString(accountsReceivables.get(i).getLastUpdateUser());
				 * salesRecord.setLastUpdateUserId(lastUpdatedUserId); double d =
				 * accountsReceivables.get(i).getAmountReceived();
				 * salesRecord.setCreditNoteAmount(Double.parseDouble(df.format(d)));
				 * salesRecord.setSalesCreditRefNo(accountsReceivables.get(i).getSourceRef());
				 * salesRecord.setCreditAmount((double) 0);
				 * System.out.println("<>>?<<?<>><><>");
				 * System.out.println(salesRecord.getBalanceAmount());
				 * System.out.println(salesRecord); }
				 * 
				 * salesRecord.setCreditAmount((double) 0); salesRecord.setBalanceAmount((float)
				 * 0); salesRecord.setPaidAmount(creditNoteAmount.floatValue());
				 * System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
				 * System.out.println(salesRecord.getBalanceAmount()); if
				 * (salesRecord.getBalanceAmount() > 0) {
				 * System.out.println("in 2 for lopps of if");
				 * salesRecord.setPaymentStatus("Partially Paid"); } else if
				 * (salesRecord.getBalanceAmount() == 0) {
				 * System.out.println("in 2 for lopps of if else");
				 * salesRecord.setPaymentStatus("Paid"); } salesRecord.setCreditNoteAmount(
				 * (double) Double.parseDouble(df.format(creditNoteAmount)));
				 * salesRecord.setSalesCreditRefNo(accountsReceivables.get(i).getSourceRef());
				 * 
				 * } else {
				 * 
				 * if (accountsReceivables.get(i).getPaymentType().equals("Cash")) {
				 * 
				 * salesRecord.setCashAmount((salesRecord.getCashAmount() != null &&
				 * salesRecord.getCashAmount() > 0) ? salesRecord.getCashAmount() -
				 * creditNoteAmount.floatValue() : null); } else if
				 * (accountsReceivables.get(i).getPaymentType().equals("Card")) {
				 * salesRecord.setCreditCardAmount((salesRecord.getCreditCardAmount() != null &&
				 * salesRecord.getCreditCardAmount() > 0) ? salesRecord.getCreditCardAmount() -
				 * creditNoteAmount.floatValue() : null);
				 * 
				 * } else if (accountsReceivables.get(i).getPaymentType().equals("MPesa")) {
				 * salesRecord.setUpiAmount((salesRecord.getUpiAmount() != null &&
				 * salesRecord.getUpiAmount() > 0) ? salesRecord.getUpiAmount() -
				 * creditNoteAmount.floatValue() : null);
				 * 
				 * } else if (accountsReceivables.get(i).getPaymentType().equals("Cheque")) {
				 * 
				 * salesRecord.setChequeAmount((salesRecord.getChequeAmount() != null &&
				 * salesRecord.getChequeAmount() > 0) ? salesRecord.getChequeAmount() -
				 * creditNoteAmount.floatValue() : null);
				 * 
				 * } salesRecord.setCreditNoteAmount( (double)
				 * Double.parseDouble(df.format(creditNoteAmount)));
				 * 
				 * } salesRepository.save(salesRecord);
				 * 
				 * } } } } } }
				 */

				/*
				 * for (int i = 0; i < accountsReceivables.size(); i++) {
				 * AccountReceivablesModel accRecModel = accountReceivablesRepository
				 * .getAccRecDataById(accountsReceivables.get(i).getAccountReceivablesId());
				 * System.out.println(
				 * "???????credit limit updation?????????????????????????????????/////////////////////////////////////////////////////////////////////////////////////"
				 * ); double amount = 0; MasterAccountModel masterAccObj = null; String BillNo =
				 * "";
				 * 
				 * if (accRecModel.getPaymentType().equals("Credit Note")) { if
				 * (accountsReceivables.get(i).getAmountToBeReceived() == 0) {
				 * System.out.println("in if oa amt to ne zero"); amount = -1 *
				 * accountsReceivables.get(i).getAmountReceived(); } else { amount =
				 * accountsReceivables.get(i).getAmountReceived(); } if
				 * (accountsReceivables.get(i).getAmountToBeReceived() == 0 &&
				 * accountsReceivables.get(i).getSourceType().equals("Sales Billing")) {
				 * System.out.println("in sales bukl"); amount =
				 * accountsReceivables.get(i).getAmountReceived(); } else if
				 * (accountsReceivables.get(i).getAmountToBeReceived() == 0 &&
				 * accountsReceivables.get(i).getSourceType().
				 * equals("Sales Returns - Credit Note")) {
				 * System.out.println("in expected case"); amount = (double) 0; }
				 * System.out.println(amount + ": final for loop credit amount");
				 * 
				 * } else if (accRecModel.getPaymentType().equals("Cheque") ||
				 * accRecModel.getPaymentType().equals("MPesa") ||
				 * accRecModel.getPaymentType().equals("Cash") ||
				 * accRecModel.getPaymentType().equals("Card")) { if
				 * (Objects.nonNull(accRecModel.getCashAmount())) { amount =
				 * accRecModel.getCashAmount(); } else if
				 * (Objects.nonNull(accRecModel.getCreditCardAmount())) { amount =
				 * accRecModel.getCreditCardAmount(); } else if
				 * (Objects.nonNull(accRecModel.getChequeAmount())) { amount =
				 * accRecModel.getChequeAmount(); } else if
				 * (Objects.nonNull(accRecModel.getUpiAmount())) { amount =
				 * accRecModel.getUpiAmount(); } System.out.println(amount + "amttttttttttttt");
				 * }
				 * 
				 * if (accRecModel.getCreditNumber() != null &&
				 * !accRecModel.getCreditNumber().isEmpty()) { System.out.println("in if od css"
				 * + accRecModel.getCreditNumber()); masterAccObj =
				 * masterAccRepo.getDataByMasterCreditNumber(accRecModel.getCreditNumber().trim(
				 * ));
				 * 
				 * if (Objects.nonNull(masterAccObj)) { System.out.println(amount +
				 * ":final credit amotjjkk paying");
				 * System.out.println(masterAccObj.getCreditLimitLeft()); DecimalFormat df = new
				 * DecimalFormat(".##");
				 * 
				 * Double creditAmountLimit = Double.parseDouble(df.format(amount)) +
				 * masterAccObj.getCreditLimitLeft(); System.out.println(creditAmountLimit +
				 * ": wlsd sk lopps amfeiucjjb");
				 * masterAccObj.setCreditLimitLeft(creditAmountLimit.intValue());
				 * masterAccRepo.save(masterAccObj); }
				 * 
				 * } else if (accRecModel.getBillRefNo() != null &&
				 * !accRecModel.getBillRefNo().isEmpty()) { System.out.println("eslwdxsxc");
				 * 
				 * SalesModel salesRecord = accountReceivablesRepository
				 * .getSalesByBillCode(accRecModel.getBillRefNo().trim());
				 * 
				 * if (Objects.nonNull(salesRecord)) { BillNo =
				 * salesRecord.getCreditAccountNo(); System.out.println(BillNo + "oen bill"); }
				 * else { SalesModel salesData = salesRepository
				 * .getSalesRecordById(accountsReceivables.get(i).getSalesBillId());
				 * 
				 * if (Objects.nonNull(salesData)) { BillNo = salesData.getCreditAccountNo();
				 * System.out.println(BillNo + "3rf bill"); } } masterAccObj =
				 * masterAccRepo.getDataByMasterCreditNumber(BillNo); if
				 * (accountsReceivables.get(i).getPartiallyPaid() != null) {
				 * System.out.println("in if oa partiallu =p");
				 * 
				 * } else { if (Objects.nonNull(masterAccObj)) { System.out.println(amount +
				 * ":final credit amotjjkk paying");
				 * System.out.println(masterAccObj.getCreditLimitLeft()); DecimalFormat df = new
				 * DecimalFormat(".##"); Double creditAmountUpdate =
				 * Double.parseDouble(df.format(amount)) + masterAccObj.getCreditLimitLeft();
				 * System.out.println(creditAmountUpdate + ": wlsd sk lopps amfeiucjjb");
				 * masterAccObj.setCreditLimitLeft(creditAmountUpdate.intValue());
				 * masterAccRepo.save(masterAccObj); } } amount = (double) 0; } else if
				 * (accRecModel.getSalesBillId() != null) { SalesModel salesData =
				 * salesRepository.getSalesRecordById(accRecModel.getSalesBillId()); if
				 * (Objects.nonNull(salesData)) { BillNo = salesData.getCreditAccountNo() !=
				 * null ? salesData.getCreditAccountNo() : " "; System.out.println(BillNo +
				 * "3rf billbbvnbnbvnbn"); }
				 * 
				 * masterAccObj = masterAccRepo.getDataByMasterCreditNumber(BillNo); if
				 * (Objects.nonNull(masterAccObj)) { System.out.println(amount +
				 * ":final credit amotjjkk paying");
				 * System.out.println(masterAccObj.getCreditLimitLeft()); DecimalFormat df = new
				 * DecimalFormat(".##"); Double creditAmountUpdate =
				 * Double.parseDouble(df.format(amount)) + masterAccObj.getCreditLimitLeft();
				 * System.out.println(creditAmountUpdate + ": wlsd sk lopps amfeiucjjb");
				 * masterAccObj.setCreditLimitLeft(creditAmountUpdate.intValue());
				 * masterAccRepo.save(masterAccObj); } }
				 * 
				 * }
				 */

				// without distubing existed things to make sales return and sales bill with
				// two payments of fully paid
				/*
				 * for (int i = 0; i < accountsReceivables.size(); i++) { for (int j = i; j <
				 * accountsReceivables.size(); j++) { if (i != j) {
				 * System.out.println("in last for loop"); if
				 * (accountsReceivables.get(i).getSourceType().
				 * equals("Sales Returns - Credit Note") &&
				 * accountsReceivables.get(i).getPaymentType().equals("Credit Note") &&
				 * accountsReceivables.get(i).getSalesBillId() != null) {
				 * 
				 * if (accountsReceivables.get(i).getPartiallyPaid() == null) { if
				 * (accountsReceivables.get(j).getPartiallyPaid() == null) { System.out.println(
				 * ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=============================================="
				 * ); SalesModel salesModel = salesRepository
				 * .getSalesRecordById(accountsReceivables.get(i).getSalesBillId());
				 * 
				 * if (salesModel.getPaymentStatus().equals("Paid") &&
				 * salesModel.getCreditNoteAmount() != null) { double amount = 0; if
				 * (Objects.nonNull(accountsReceivables.get(j).getCashAmount())) { amount =
				 * accountsReceivables.get(j).getCashAmount(); } else if
				 * (Objects.nonNull(accountsReceivables.get(j).getCreditCardAmount())) { amount
				 * = accountsReceivables.get(j).getCreditCardAmount(); } else if
				 * (Objects.nonNull(accountsReceivables.get(j).getChequeAmount())) { amount =
				 * accountsReceivables.get(j).getChequeAmount(); } else if
				 * (Objects.nonNull(accountsReceivables.get(j).getUpiAmount())) { amount =
				 * accountsReceivables.get(j).getUpiAmount(); } System.out.println(amount +
				 * ":?>?>>??"); double netAmt = salesModel.getNetAmount(); double amt = netAmt -
				 * amount; if (accountsReceivables.get(j).getCreditNumber() != null) {
				 * MasterAccountModel masterAccObj = masterAccRepo.getDataByMasterCreditNumber(
				 * accountsReceivables.get(j).getCreditNumber().trim());
				 * System.out.println(masterAccObj.getCreditNumber()); if
				 * (Objects.nonNull(masterAccObj)) { System.out.println(amt +
				 * ":final credit amotjjkk paying");
				 * System.out.println(masterAccObj.getCreditLimitLeft()); DecimalFormat df = new
				 * DecimalFormat(".##"); Double creditAmountUpdate =
				 * Double.parseDouble(df.format(amt)) + masterAccObj.getCreditLimitLeft();
				 * System.out.println(creditAmountUpdate + ": wlsd sk lopps amfeiucjjb");
				 * masterAccObj.setCreditLimitLeft(creditAmountUpdate.intValue());
				 * masterAccRepo.save(masterAccObj); } } } } } }
				 * 
				 * } } }
				 */

				// to update credit note amount if it goes for multiple creditnotes
				for (int i = 0; i < accountsReceivables.size(); i++) {
					SalesModel salesRecord = null;
					DecimalFormat df = new DecimalFormat(".##");
					if (Objects.nonNull(accountsReceivables.get(i).getBillRefNo())) {
						salesRecord = accountReceivablesRepository
								.getSalesByBillCode(accountsReceivables.get(i).getBillRefNo().trim());
					} else {
						salesRecord = salesRepository.getSalesRecordById(accountsReceivables.get(i).getSalesBillId());
					}

					if (Objects.nonNull(salesRecord)) {
						System.out.println("in side of sales updation condyi");
						if (salesRecord.getPaymentStatus().equals("Paid") && salesRecord.getBalanceAmount() == 0) {
							if (Objects.isNull(accountsReceivables.get(i).getCashAmount())
									&& Objects.isNull(accountsReceivables.get(i).getCreditCardAmount())
									&& Objects.isNull(accountsReceivables.get(i).getChequeAmount())
									&& Objects.isNull(accountsReceivables.get(i).getUpiAmount())) {

								System.out.println("inside of if");
								salesRecord.setCreditNoteAmount(
										(double) Double.parseDouble(df.format(salesRecord.getPaidAmount())));

							}
						} else if (Objects.isNull(salesRecord.getSalesCreditRefNo())
								&& salesRecord.getPaymentStatus().equals("Partially Paid")
								&& salesRecord.getChequeNumber().contains("DPAR")) {
							System.out.println("updating credit note amt");
							salesRecord.setCreditNoteAmount(null);
						}
						salesRepository.save(salesRecord);
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
		Double billPartialAmt = 0.0;
		Double billTotalDebit = 0.0;
		Double billTotalCredit = 0.0;
		JSONObject res = new JSONObject();
		for (int i = 0; i < json.size(); i++) {
			if (Objects.nonNull(json.get(i).getPayment())) {
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
					System.out.println("else ddddfdvdf" + json.get(i).getAmountToBeReceived());
					billPartialAmt += json.get(i).getAmountToBeReceived();
					System.out.println(billPartialAmt + "/lkuhh");
					if (json.get(i).getAmountReceived() != null && json.get(i).getAmountReceived() < 0.0) {
						System.out.println(json.get(i).getAmountReceived() + "/////...,,.,,,,mmmmmm");
						billTotalDebit += json.get(i).getAmountReceived() != null ? 0
								: -1 * json.get(i).getAmountReceived();
					} else {
						System.out.println(json.get(i).getAmountReceived() + "eslecs");
						billTotalCredit += json.get(i).getAmountReceived() != null ? json.get(i).getAmountReceived()
								: 0;
					}

					if (json.get(i).getAmountToBeReceived() != null) {
						if (json.get(i).getAmountToBeReceived() < 0.0) {
							System.out.println(json.get(i).getAmountReceived() + "not nullssc");
							billTotalDebit += json.get(i).getAmountToBeReceived() != null ? 0
									: -1 * json.get(i).getAmountToBeReceived() + json.get(i).getPartialAmt();
						} else {
							if (json.get(i).getPayment().equals("Full")) {
								System.out.println(json.get(i).getAmountReceived() + "full//");
								billTotalCredit += json.get(i).getAmountToBeReceived() != null
										? json.get(i).getAmountToBeReceived()
										: 0;
							} else {
								System.out.println(json.get(i).getAmountReceived() + "{{}{}{}{{{}{{{");
								billTotalCredit += json.get(i).getAmountToBeReceived() != null
										? json.get(i).getAmountToBeReceived() + json.get(i).getPartialAmt()
										: 0;
							}
						}
					}

				}
			}
		}
		DecimalFormat df = new DecimalFormat(".##");
		System.out.println(billPartialAmt + "mntrfgvhj");
		res.put("totalAmount", Double.parseDouble(df.format(billPartialAmt)));
		res.put("totalCredit", Double.parseDouble(df.format(billTotalCredit)));
		res.put("totalBill", Double.parseDouble(df.format(billTotalCredit - billTotalDebit)));

		return res;
	}

}
