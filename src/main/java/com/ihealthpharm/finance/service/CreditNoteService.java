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
	
}