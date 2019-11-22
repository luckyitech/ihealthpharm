package com.ihealthpharm.finance.service;

import java.util.List;

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
}