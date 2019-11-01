package com.ihealthpharm.finance.service;

import java.util.List;

import com.ihealthpharm.finance.model.DebitNoteModel;

public interface DebitNoteService
{
    DebitNoteModel saveDebitData(DebitNoteModel debitNoteModel);
    
    DebitNoteModel findDebitById(int debitNoteId);
    
    List<DebitNoteModel> findAllDebit();
   
    DebitNoteModel updateDebitData(DebitNoteModel debitNoteModel);
    
    List<DebitNoteModel> updateMultipleDebit(List<DebitNoteModel> debitNoteModel);
    
    void deleteDebitById(int debitNoteId);
    
    void deleteMultipleDebit(int[] debitNoteId);
}