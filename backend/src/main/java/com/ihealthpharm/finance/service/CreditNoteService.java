package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.dto.CreditCustomerDTO;
import com.ihealthpharm.finance.model.CreditNoteModel;

public interface CreditNoteService
{
    
    void deleteCreditById(Integer creditNoteId);
    
    void deleteMultipleCredit(Integer[] creditNoteId);
 
    List<CreditNoteModel> findAllCredit();
    
    CreditNoteModel findCreditById(Integer creditNoteId);

    CreditNoteModel saveCreditData(CreditNoteModel creditNoteModel);

    CreditNoteModel updateCreditData(CreditNoteModel creditNoteModel);
    
    List<CreditNoteModel> updateListOfCreditData(List<CreditNoteModel> creditNoteModel);

	List<CreditCustomerDTO> getAllCustomersFromCreditNotes();
	//Credit Note
	List<String> findCreditNoteNobysearchCN(String searchTerm);
	
	List<String> findallCreditNoteNoCN();

	List<String> findAllCustomers();

	List<String> findAllCustomersBySearch(String customer);
	
	List<String> getCreditNoteByBillType(String searchTerm);

	List<CreditNoteModel> getAllCreditNotes();

	List<CreditNoteModel> getAllCreditNotesBySearch(String searchTerm, String searchValue);

	List<String> getAllCreditNotesPaymentStatus();

	CreditNoteModel getCreditNote(Integer creditNoteId);

	CreditNoteModel updateCreditNoteById(Integer creditNoteId, String paymentStatus);

	CreditNoteModel updateCreditNoteRemarks(String remarks, String crNo);

	List<CreditNoteModel> findAllCreditNotesByCustomerId(Integer customerId);

	CreditNoteModel updateCreditNoteRemarks(Float leftAmount, String prevCreditNoteNo, 
			String newCreditNoteNo,String billCode,Float netAmount);

    List<CreditNoteModel> getCreditNoteDataByInvoiceNo(String invoiceNo);
}