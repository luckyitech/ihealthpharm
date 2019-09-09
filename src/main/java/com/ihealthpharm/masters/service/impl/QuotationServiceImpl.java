package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.QuotationRepository;
import com.ihealthpharm.masters.helper.QuotationHelper;
import com.ihealthpharm.masters.model.QuotationModel;
import com.ihealthpharm.masters.service.QuotationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class QuotationServiceImpl implements QuotationService{

	@Autowired
	QuotationRepository quotationRepository;
	
	@Autowired
	QuotationHelper quotationHelper;
	
	@Override
	public QuotationModel saveQuotationData(QuotationModel quotation) {
		QuotationModel quotationRes = quotationRepository.save(quotation);
		log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " Saved succesfully");
		return quotationRes;
	}

	@Override
	public QuotationModel updateQuotationData(QuotationModel quotation) {
		QuotationModel quotationRes = getValidQuotations(quotation.getQuotationId());
		if (!Objects.nonNull(quotationRes)) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}

		quotationRes = quotationRepository.save(quotation);
		log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " updated succesfully");
		return quotationRes;
	}

	@Override
	public List<QuotationModel> updateQuotationData(List<QuotationModel> quotations) {
		for (QuotationModel quotation : quotations) {
			QuotationModel quotationRes = getValidQuotations(quotation.getQuotationId());
			if (!Objects.nonNull(quotationRes)) {
				throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
			}

			quotationRes = quotationRepository.save(quotation);
			log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " updated succesfully");
		}
		return quotations;
	}

	@Override
	public List<QuotationModel> findAllQuotations() {
		
		return quotationRepository.findAll();
	}

	@Override
	public QuotationModel findQuotationById(int quotationId) {
		QuotationModel quotationRes = getValidQuotations(quotationId);
		if (!Objects.nonNull(quotationRes)) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " retrieved succesfully");
		return quotationRes;
	}

	@Override
	public void deleteQuotationById(int quotationId) {
		QuotationModel quotationRes = quotationRepository.getOne(quotationId);
		if (!Objects.nonNull(quotationRes)) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Quotations data with ID : " + quotationRes.getQuotationId() + " Deleted succesfully");
		quotationRepository.delete(quotationRes);
		
	}

	@Override
	public void deleteQuotationsById(int[] quotationIds) {
		QuotationModel quotationRes;
		for (int quotation : quotationIds) {
			quotationRes = getValidQuotations(quotation);
			if (!Objects.nonNull(quotationRes)) {
				throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
			}
			quotationRepository.delete(quotationRes);
			log.info("Quotations data with ID: " + quotationRes.getQuotationId() + " deleted succesfully");
		}
		
	}
	
	public QuotationModel getValidQuotations(int quotationId) {
		QuotationModel quotationRes = null;

		try {
			quotationRes = quotationRepository.findById(quotationId).get();
			return quotationRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(quotationHelper.getNotFoundQuotationMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
