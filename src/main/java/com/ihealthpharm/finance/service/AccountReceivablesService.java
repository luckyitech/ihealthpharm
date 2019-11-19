package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountReceivablesModel;
import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.stock.model.InvoiceModel;

public interface AccountReceivablesService
{
    
	AccountReceivablesModel saveAccountReceivablesData(AccountReceivablesModel accountReceivables);

	AccountReceivablesModel updateAccountReceivablesData(AccountReceivablesModel accountReceivables);
	
	 List<AccountReceivablesModel> updateAccountsReceivablesData(List<AccountReceivablesModel> accountsReceivables);
	
	 List<AccountReceivablesModel> findAllAccountsReceivables();
	
	 AccountReceivablesModel findAccountReceivablesById(int accountReceivablesId);
	
	 void deleteAccountReceivablesById(int accountReceivablesIds);
	
	 void deleteAccountsReceivablesById(int[] accountReceivablesIds);
	 
	// List<SalesModel> getAllBillsByCustomerId(CustomerInsuranceModel customerId);
	 
	 List<SalesModel> getAllBillsByCustomerId(Integer customerId);

	AccountReceivablesModel findAccountReceivablesByBillId(Integer billId);
	 
}