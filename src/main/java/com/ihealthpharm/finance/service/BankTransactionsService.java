package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.BankTransactionsModel;
import com.ihealthpharm.finance.model.GeneralLedgerModel;

public interface BankTransactionsService {
	
	List<BankTransactionsModel> findAllBankTransactions();
    
	BankTransactionsModel saveTransaction(BankTransactionsModel bankTransactionsModel);
}
