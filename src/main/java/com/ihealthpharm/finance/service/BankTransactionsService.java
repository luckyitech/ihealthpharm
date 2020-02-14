package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.BankTransactionsModel;

public interface BankTransactionsService {
	
	List<BankTransactionsModel> findAllBankTransactions();

}
