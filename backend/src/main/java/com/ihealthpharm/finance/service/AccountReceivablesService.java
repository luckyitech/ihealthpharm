package com.ihealthpharm.finance.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ihealthpharm.finance.dto.AccRecievablesAccountsDTO;
import com.ihealthpharm.finance.dto.AccRecievablesCustomerDTO;
import com.ihealthpharm.finance.dto.RecieptMoneyCalDTO;
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
	 
	 List<SalesModel> getAllCustomersByCustomerId(Integer customerId);

	List<AccountReceivablesModel> findAccountReceivablesByBillId(Integer billId);
	
	List<SalesModel> getAllSalesBySearch(String billCode);

	List<AccountReceivablesModel> getAllAccountPayables();

	List<AccountReceivablesModel> getAllCustomersBasedonCustomerName(String customerName);
	
	//Account Receivables 
	List<String> findReceiptNobysearchAR(String searchTerm);
	
	List<String> findallReceiptNoAR();

	List<AccountReceivablesModel> getAllRecievablesCustomerNameSearch(String customerName);
	
	List<String> findallCustNamesAR();
	
	List<String> findCustNamesbysearchAR(String searchTerm);

	List<AccountReceivablesModel> getAllByBillCodeSearch(String billCode,String customerName);

	List<AccountReceivablesModel> searchInAccRecievables(String paymentStatus, String paymentStartDate,
			String paymentEndDate, String sourceRef, Integer pageNumber, Integer pageSize, String customerName);

	Integer searchInAccRecievablesForCount(String paymentStatus, String paymentStartDate, String paymentEndDate,
			String sourceRef, Integer pageNumber, Integer pageSize, String customerName);

	List<AccRecievablesCustomerDTO> getAllAccountPayablesData();

	List<AccountReceivablesModel> searchInAccRecievablesForAccounts(String paymentStatus, String paymentStartDate,
			String paymentEndDate, String sourceRef, Integer pageNumber, Integer pageSize, String creditNumber);

	Integer searchInAccRecievablesForCountForAccounts(String paymentStatus, String paymentStartDate,
			String paymentEndDate, String sourceRef, Integer pageNumber, Integer pageSize, String creditNumber);

	JSONObject doCalculations(List<RecieptMoneyCalDTO> json);
	
}