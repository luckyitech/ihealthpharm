package com.ihealthpharm.finance.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.finance.dao.CreditNoteRepository;
import com.ihealthpharm.finance.dto.CreditCustomerDTO;
import com.ihealthpharm.finance.helper.CreditNoteHelper;
import com.ihealthpharm.finance.model.CreditNoteModel;
import com.ihealthpharm.finance.service.CreditNoteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CreditNoteServiceImpl implements CreditNoteService {
	
	@Autowired
	private CreditNoteRepository creditNoteRepo;
	
	@Autowired
	private CreditNoteHelper creditNoteHelper;
	

	@Override
	public void deleteCreditById(Integer creditNoteId) {
		
		CreditNoteModel creditNoteRes = getValidCreditNote(creditNoteId);
		if (!Objects.nonNull(creditNoteRes)) {
			throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(), HttpStatus.NOT_FOUND);
		}
		creditNoteRepo.delete(creditNoteRes);
		log.info("CreditNote data with ID: " + creditNoteRes.getCreditNoteId()+  "Deleted Successfully");

	}

	@Override
	public void deleteMultipleCredit(Integer[] creditNoteId) {
		CreditNoteModel creditNoteRes;
		for (int creditNote : creditNoteId) {
			creditNoteRes = getValidCreditNote(creditNote);
			if (!Objects.nonNull(creditNoteRes)) {
				throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(), HttpStatus.NOT_FOUND);
			}
			creditNoteRepo.delete(creditNoteRes);
			log.info("creditNote data with ID: " + creditNoteRes.getCreditNoteId() + " deleted succesfully");
		}				
	}

	@Override
	public List<CreditNoteModel> findAllCredit() {
		return creditNoteRepo.findAll();
	}

	@Override
	public CreditNoteModel findCreditById(Integer creditNoteId) {
		
		CreditNoteModel creditNoteRes = getValidCreditNote(creditNoteId);
		
		if(!Objects.nonNull(creditNoteRes)) {
			throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("CreditNote with ID:" +creditNoteRes.getCreditNoteId()+ "retrived successfully");
		
		return creditNoteRes;
	}

	@Override
	public CreditNoteModel saveCreditData(CreditNoteModel creditNoteModel) {
		
		CreditNoteModel creditNoteRes = creditNoteRepo.save(creditNoteModel);
		log.info("CreditNote data" +creditNoteRes.getCreditNoteId() + "saved successfully");
		return creditNoteRes;
		
	}

	@Override
	public CreditNoteModel updateCreditData(CreditNoteModel creditNoteModel) {
		CreditNoteModel creditNoteRes = getValidCreditNote(creditNoteModel.getCreditNoteId());

		if (!Objects.nonNull(creditNoteRes)) {
			throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(), HttpStatus.NOT_FOUND);
		}
		creditNoteRes = creditNoteRepo.save(creditNoteModel);
		log.info("CreditNote data with ID : " + creditNoteRes.getCreditNoteId() + " updated succesfully");

		return creditNoteRes;
	}

	@Override
	public List<CreditNoteModel> updateListOfCreditData(List<CreditNoteModel> creditNoteModel) {
		creditNoteModel = creditNoteRepo.saveAll(creditNoteModel);

		log.info("Multiple CreditNote data  updated succesfully");

		return creditNoteModel;
	}
    
	private CreditNoteModel getValidCreditNote(Integer creditNoteId) {
		
		CreditNoteModel creditNoteRes= null;
		try {
			creditNoteRes = creditNoteRepo.findById(creditNoteId).get();
			return creditNoteRes;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(creditNoteHelper.getNotFoundCreditNoteMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<CreditCustomerDTO> getAllCustomersFromCreditNotes() {
		return creditNoteRepo.getCustomersFromCreditNote();
	}

	@Override
	public List<String> findCreditNoteNobysearchCN(String searchTerm) {
		return creditNoteRepo.findCreditNoteNoBySearch(searchTerm);
	}

	@Override
	public List<String> findallCreditNoteNoCN() {
		return creditNoteRepo.findAllCreditNoteNoINCN();
	}

	@Override
	public List<String> findAllCustomers() {
		
		return creditNoteRepo.getAllCustomers();
	}

	@Override
	public List<String> findAllCustomersBySearch(String customer) {
		return creditNoteRepo.getCustomersBySearch(customer);
	}

	@Override
	public List<String> getCreditNoteByBillType(String searchTerm) {
		return creditNoteRepo.getCreditNoteByBillTypes(searchTerm);
	}

	@Override
	public List<CreditNoteModel> getAllCreditNotes() {
		return creditNoteRepo.getAllCNData() ;
	}

	@Override
	public List<CreditNoteModel> getAllCreditNotesBySearch(String searchTerm, String searchValue) {
		if(searchTerm.equalsIgnoreCase("Invoice No")) {
			return creditNoteRepo.getAllDataBySearchForInvoices(searchValue);
		}else {
			return creditNoteRepo.getAllDataBySearchForBills(searchValue);
		}
	}
	
		
	public List<String> getAllCreditNotesPaymentStatus() {
		
		return creditNoteRepo.findAllPaymentStatus();
	}
   
}