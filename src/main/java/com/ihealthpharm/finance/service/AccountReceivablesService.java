package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.sales.model.SalesModel;

public interface AccountReceivablesService
{
    
	AccountReceivablesModel saveAccountReceivablesData(AccountReceivablesModel accountReceivables);

	AccountReceivablesModel updateAccountReceivablesData(AccountReceivablesModel accountReceivables);
	
	 List<AccountReceivablesModel> updateAccountsReceivablesData(List<AccountReceivablesModel> accountsReceivables);
	
	 List<AccountReceivablesModel> findAllAccountsReceivables();
	
	 AccountReceivablesModel findAccountReceivablesById(Integer accountReceivablesId);
	
	 void deleteAccountReceivablesById(Integer accountReceivablesIds);
	
	 void deleteAccountsReceivablesById(Integer[] accountReceivablesIds);
	 
	// List<SalesModel> getAllBillsByCustomerId(CustomerInsuranceModel customerId);
	 
	 List<SalesModel> getAllBillsByCustomerId(Integer customerId);
	 
	// List<SalesModel> getAllCustomersByCustomerId(Integer customers);

	List<AccountReceivablesModel> findAccountReceivablesByBillId(Integer billId);
	 
}