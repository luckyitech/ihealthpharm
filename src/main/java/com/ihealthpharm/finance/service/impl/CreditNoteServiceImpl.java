package com.ihealthpharm.finance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.finance.dao.CreditNoteRepository;
import com.ihealthpharm.finance.model.CreditNoteModel;
import com.ihealthpharm.finance.service.CreditNoteService;

@Service
public class CreditNoteServiceImpl implements CreditNoteService {
	
	@Autowired
	private CreditNoteRepository creditNoteRepository;
	

	@Override
	public void deleteCreditById(int creditNoteId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMultipleCredit(int[] creditNoteId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CreditNoteModel> findAllCredit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditNoteModel findCreditById(int creditNoteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditNoteModel saveCreditData(CreditNoteModel creditNoteModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditNoteModel updateCreditData(CreditNoteModel creditNoteModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CreditNoteModel> updateListOfCreditData(List<CreditNoteModel> creditNoteModel) {
		// TODO Auto-generated method stub
		return null;
	}
    
   
}