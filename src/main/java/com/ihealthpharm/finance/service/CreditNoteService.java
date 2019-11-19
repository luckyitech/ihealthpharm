package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.CreditNoteModel;

public interface CreditNoteService
{
    
    void deleteCreditById(int creditNoteId);
    
    void deleteMultipleCredit(int[] creditNoteId);
 
    List<CreditNoteModel> findAllCredit();
    
    CreditNoteModel findCreditById(int creditNoteId);

    CreditNoteModel saveCreditData(CreditNoteModel creditNoteModel);

    CreditNoteModel updateCreditData(CreditNoteModel creditNoteModel);
    
    List<CreditNoteModel> updateListOfCreditData(List<CreditNoteModel> creditNoteModel);
    
   
       
    
    
	
}