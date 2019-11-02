package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountReceivablesModel;

public interface AccountReceivablesService
{
    
	AccountReceivablesModel saveAccountReceivablesData(AccountReceivablesModel accountReceivables);

	AccountReceivablesModel updateAccountReceivablesData(AccountReceivablesModel accountReceivables);
	
	 List<AccountReceivablesModel> updateAccountsReceivablesData(List<AccountReceivablesModel> accountsReceivables);
	
	 List<AccountReceivablesModel> findAllAccountsReceivables();
	
	 AccountReceivablesModel findAccountReceivablesById(int accountReceivablesId);
	
	 void deleteAccountReceivablesById(int accountReceivablesIds);
	
	 void deleteAccountsReceivablesById(int[] accountReceivablesIds);
	 

}