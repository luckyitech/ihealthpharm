package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountReceivablesModel;

public interface AccountReceivablesService
{
    
	AccountReceivablesModel saveAccountReceivables(AccountReceivablesModel accountReceivables);

	AccountReceivablesModel updateAccountReceivablesData(AccountReceivablesModel accountReceivables);
	
	 List<AccountReceivablesModel> updateAccountsReceivablesData(List<AccountReceivablesModel> accountsReceivables);
	
	 List<AccountReceivablesModel> findAllAccountsReceivables();
	
	 AccountReceivablesModel findAccountReceivablesById(int accountReceivablesId);
	
	 void deleteAccountReceivablesById(int accountReceivablesIds);
	
	 void deleteAccountsReceivablesById(int[] accountReceivablesIds);
	 

	List<AccountReceivablesModel> findAccountReceivablesByActive();
}