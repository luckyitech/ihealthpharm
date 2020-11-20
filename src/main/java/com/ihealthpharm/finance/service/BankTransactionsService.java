package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.dto.BankTransactionDTO;
import com.ihealthpharm.finance.model.BankTransactionsModel;

public interface BankTransactionsService {

	List<BankTransactionsModel> findAllBankTransactions();

	BankTransactionsModel saveTransaction(BankTransactionsModel bankTransactionsModel);

	List<BankTransactionDTO> findAllTransactionId(String transactionId);

	List<String> getBySearchPartyDetails(String searchTerm);

	List<String> getAllPartyDetails();

	List<String> getBySearchCounterPartyDetails(String searchTerm);

	List<String> getAllCounterPartyDetails();

	List<BankTransactionsModel> findAllBankTransactionsByRefNo(String referenceNo);

	List<BankTransactionsModel> findAllBankTransactionsBySearch(String refNo, String fromDate, 
			String toDate,String party,String counterParty);

	BankTransactionsModel findBankTxnDetailsById(Integer bankTransactionId);

	void updateChartOfAccountBal(String party, String counterParty, String amount);
}
