package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.stock.model.InvoiceModel;

public interface AccountPayablesService
{
    
	AccountPayablesModel saveAccountPayablesData(AccountPayablesModel accountPayables);

	AccountPayablesModel updateAccountPayablesData(AccountPayablesModel accountPayables);
	
	 List<AccountPayablesModel> updateAccountsPayablesData(List<AccountPayablesModel> accountsPayables);
	
	 List<AccountPayablesModel> findAllAccountsPayables();
	
	 AccountPayablesModel findAccountPayablesById(Integer accountPayablesId);
	
	 void deleteAccountPayablesById(Integer accountPayablesIds);
	
	 void deleteAccountsPayablesById(Integer[] accountPayablesIds);
	 
	 List<InvoiceModel> getAllInvoicesBySupplierId(Integer supplierId);
	 
	 
	
}