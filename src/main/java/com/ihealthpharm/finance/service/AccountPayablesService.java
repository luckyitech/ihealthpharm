package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.AccountPayablesModel;
import com.ihealthpharm.stock.model.InvoiceModel;

public interface AccountPayablesService
{

	AccountPayablesModel saveAccountPayablesData(AccountPayablesModel accountPayables);

	List<AccountPayablesModel> updateAccountPayablesData(List<AccountPayablesModel> accountPayables);

	List<AccountPayablesModel> updateAccountsPayablesData(List<AccountPayablesModel> accountsPayables);

	List<AccountPayablesModel> findAllAccountsPayables();

	AccountPayablesModel findAccountPayablesById(Integer accountPayablesId);

	void deleteAccountPayablesById(Integer accountPayablesIds);

	void deleteAccountsPayablesById(Integer[] accountPayablesIds);

	List<InvoiceModel> getAllInvoicesBySupplierId(Integer supplierId);

	List<InvoiceModel> getAllInvoicesBySearch(String invoiceNo);

	List<AccountPayablesModel> getAllAccountPayables();

	List<AccountPayablesModel> getAllCustomersBasedOnName(String customerName);

	List<AccountPayablesModel> getAllSuppliersBasedonSupplierName(String supplierName);

	List<AccountPayablesModel> getAllSuppliersForAccountPayables();

	Integer getCountOfPending();

	Integer getCountPartiallyPaid();

	Integer getCountPaid();


	// List<InvoiceModel> getAllCustomersByCustomerId(Integer customerId);

	List<String> findPaymentNoINAP(String PNo);

	List<String> findAllPaymentNosINAP();

	List<AccountPayablesModel> getAllPayablesBasedOnSuppliers(String supplierName);

	List<String> findSupplierNamesINAP(String searchTerm);

	List<String> findAllSupplierNamesINAP();

	List<String> findPaymentStatusINAP(String searchTerm);

	List<String> findAllPaymentStatusINAP();

	List<AccountPayablesModel> getAllAccountPayablesBasedOnInvoice(String invoiceNo);

}