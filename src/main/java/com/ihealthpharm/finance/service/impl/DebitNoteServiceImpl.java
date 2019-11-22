package com.ihealthpharm.finance.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.DebitNoteRepository;
import com.ihealthpharm.finance.helper.DebitNoteHelper;
import com.ihealthpharm.finance.model.DebitNoteModel;
import com.ihealthpharm.finance.service.DebitNoteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class DebitNoteServiceImpl implements DebitNoteService{
	
	@Autowired
	private DebitNoteRepository debitNoteRepo;
	
	@Autowired
	private DebitNoteHelper debitNoteHelper;

	@Override
	public DebitNoteModel saveDebitData(DebitNoteModel debitNoteModel) {
		DebitNoteModel debitNoteRes = debitNoteRepo.save(debitNoteModel);
		log.info("DebitNote data" +debitNoteRes.getDebitNoteId() + "saved successfully" );
		return debitNoteRes;
	}

	@Override
	public DebitNoteModel findDebitById(Integer debitNoteId) {

		DebitNoteModel debitNoteRes = getValidDebitNote(debitNoteId);
		if(!Objects.nonNull(debitNoteRes)) {
			throw new IHealthPharmException(debitNoteHelper.getNotFoundDebitNoteMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("DebitNote with ID:" +debitNoteRes.getDebitNoteId()+ "retrived successfully");
		return debitNoteRes;
	}

	@Override
	public List<DebitNoteModel> findAllDebit() {
		return debitNoteRepo.findAll();
	}

	@Override
	public DebitNoteModel updateDebitData(DebitNoteModel debitNoteModel) {
		DebitNoteModel debitNoteRes = getValidDebitNote(debitNoteModel.getDebitNoteId());
		if (!Objects.nonNull(debitNoteRes)) {
			throw new IHealthPharmException(debitNoteHelper.getNotFoundDebitNoteMessage(), HttpStatus.NOT_FOUND);
		}
		debitNoteRes = debitNoteRepo.save(debitNoteModel);
		log.info("DebitNote data with ID : " + debitNoteRes.getDebitNoteId() + " updated succesfully");
		return debitNoteRes;
	}

	@Override
	public List<DebitNoteModel> updateMultipleDebit(List<DebitNoteModel> debitNoteModel) {
		debitNoteModel = debitNoteRepo.saveAll(debitNoteModel);
		log.info("Multiple DebitNote data  updated succesfully");
		return debitNoteModel;
	}

	@Override
	public void deleteDebitById(Integer debitNoteId) {
		DebitNoteModel debitNoteRes = getValidDebitNote(debitNoteId);
		if (!Objects.nonNull(debitNoteRes)) {
			throw new IHealthPharmException(debitNoteHelper.getNotFoundDebitNoteMessage(), HttpStatus.NOT_FOUND);
		}
		debitNoteRepo.delete(debitNoteRes);
		log.info("DebitNote data with ID: " + debitNoteRes.getDebitNoteId()+  "Deleted Successfully");

	}

	@Override
	public void deleteMultipleDebit(Integer[] debitNoteId) {
		DebitNoteModel debitNoteRes;
		for (int debitNote : debitNoteId) {
			debitNoteRes = getValidDebitNote(debitNote);
			if (!Objects.nonNull(debitNoteRes)) {
				throw new IHealthPharmException(debitNoteHelper.getNotFoundDebitNoteMessage(), HttpStatus.NOT_FOUND);
			}
			debitNoteRepo.delete(debitNoteRes);
			log.info("debitNote data with ID: " + debitNoteRes.getDebitNoteId() + " deleted succesfully");
		}				
	}
    
	private DebitNoteModel getValidDebitNote(int debitNoteId) {
		DebitNoteModel debitNoteRes = null;
		try {
			debitNoteRes = debitNoteRepo.findById(debitNoteId).get();
			return debitNoteRes;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(debitNoteHelper.getNotFoundDebitNoteMessage(), HttpStatus.NOT_FOUND);
		}
				
	}
}