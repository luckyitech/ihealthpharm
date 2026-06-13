package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountPayablesInvoicesModel;

public interface AccountPayablesInvoicesService
{
    
	AccountPayablesInvoicesModel saveAccountPayablesInvoicesData(AccountPayablesInvoicesModel accountPayablesInvoices);

	AccountPayablesInvoicesModel updateAccountPayablesInvoicesData(AccountPayablesInvoicesModel accountPayablesInvoices);
	
	 List<AccountPayablesInvoicesModel> updateAccountsPayablesInvoicesData(List<AccountPayablesInvoicesModel> accountsPayablesInvoices);
	
	 List<AccountPayablesInvoicesModel> findAllAccountsPayablesInvoices();
	
	 AccountPayablesInvoicesModel findAccountPayablesInvoicesById(Integer accountPayablesInvoicesId);
	
	 void deleteAccountPayablesInvoicesById(Integer accountPayablesInvoicesIds);
	
	 void deleteAccountsPayablesInvoicesById(Integer[] accountPayablesInvoicesIds);
	 


}