package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.dto.CreditCustomerDTO;
import com.ihealthpharm.finance.model.DebitNoteModel;

public interface DebitNoteService
{
    DebitNoteModel saveDebitData(DebitNoteModel debitNoteModel);
    
    DebitNoteModel findDebitById(Integer debitNoteId);
    
    List<DebitNoteModel> findAllDebit();
   
    DebitNoteModel updateDebitData(DebitNoteModel debitNoteModel);
    
    List<DebitNoteModel> updateMultipleDebit(List<DebitNoteModel> debitNoteModel);
    
    void deleteDebitById(Integer debitNoteId);
    
    void deleteMultipleDebit(Integer[] debitNoteId);

	List<CreditCustomerDTO> getAllCustomersMappedWithDebit();
	
	List<String> findDebitNoINDN(String DNNo);
	
	List<String> findAllDebitNosINDN();

	List<String> findAllSuppliers();

	List<String> findAllSuppliersBySearch(String spName);

	List<String> findAllInvoiceNo();

	List<String> findAllInvoiceNoBySearch(String invoiceNo);

	List<String> findAllIReturnTypes();
}