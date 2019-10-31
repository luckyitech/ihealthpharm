package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountPayablesModel;

public interface AccountPayablesService
{
    
	AccountPayablesModel saveAccountPayables(AccountPayablesModel accountPayables);

	AccountPayablesModel updateAccountPayablesData(AccountPayablesModel accountPayables);
	
	 List<AccountPayablesModel> updateAccountsPayablesData(List<AccountPayablesModel> accountsPayables);
	
	 List<AccountPayablesModel> findAllAccountsPayables();
	
	 AccountPayablesModel findAccountPayablesById(int accountPayablesId);
	
	 void deleteAccountPayablesById(int accountPayablesIds);
	
	 void deleteAccountsPayablesById(int[] accountPayablesIds);
	 

	List<AccountPayablesModel> findAccountPayablesByActive();
}