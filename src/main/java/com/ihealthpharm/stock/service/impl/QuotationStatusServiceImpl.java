package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.stock.dao.QuotationStatusRepository;
import com.ihealthpharm.stock.helper.QuotationStatusHelper;
import com.ihealthpharm.stock.model.QuotationStatusModel;
import com.ihealthpharm.stock.service.QuotationStatusService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class QuotationStatusServiceImpl implements QuotationStatusService {

	@Autowired
	QuotationStatusRepository  quotationStatusRepository;

	@Autowired
	QuotationStatusHelper quotationStatusHelper;

	@Override
	public QuotationStatusModel saveQuotationStatus(QuotationStatusModel quotationStatusModel) {
		quotationStatusModel = quotationStatusRepository.save(quotationStatusModel);
		log.info("QuotationStatus data with ID: " + quotationStatusModel.getQuotationStatusId() + " saved succesfully");
		return quotationStatusModel;
	}

	@Override
	public QuotationStatusModel updateQuotationStatus(QuotationStatusModel quotationStatusModel) {
		QuotationStatusModel model = getQuotationStatusModel(quotationStatusModel.getQuotationStatusId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(quotationStatusHelper.getNotFoundQuotationStatusMessage(), HttpStatus.NOT_FOUND);
		}
		model = quotationStatusRepository.save(quotationStatusModel);
		log.info("QuotationStatus data with ID : " + model.getQuotationStatusId() + " updated succesfully");
		return model;
	}

	@Override
	public List<QuotationStatusModel> updateQuotationStatus(List<QuotationStatusModel> quotationStatusModels) {
		for (QuotationStatusModel quotationStatusModel : quotationStatusModels) {
			QuotationStatusModel model = getQuotationStatusModel(quotationStatusModel.getQuotationStatusId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(quotationStatusHelper.getNotFoundQuotationStatusMessage(), HttpStatus.NOT_FOUND);
			}
			model = quotationStatusRepository.save(quotationStatusModel);
			log.info("QuotationStatus data with Multiple IDs : " + model.getQuotationStatusId() + " updated succesfully");
		}
		return quotationStatusModels;
	}

	@Override
	public QuotationStatusModel findQuotationStatusById(Integer quotationStatusId) {
		QuotationStatusModel quotationStatusModel = getQuotationStatusModel(quotationStatusId);
		if (!Objects.nonNull(quotationStatusModel)) {
			throw new IHealthPharmException(quotationStatusHelper.getNotFoundQuotationStatusMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("QuotationStatus data with ID : "+ quotationStatusModel.getQuotationStatusId()+" retrieved succesfully");
		return quotationStatusModel;
	}

	@Override
	public void deleteQuotationStatusById(Integer quotationStatusId) {
		QuotationStatusModel quotationStatusModel = getQuotationStatusModel(quotationStatusId);
		if (!Objects.nonNull(quotationStatusModel)) {
			throw new IHealthPharmException(quotationStatusHelper.getNotFoundQuotationStatusMessage(), HttpStatus.NOT_FOUND);
		}		
		quotationStatusRepository.delete(quotationStatusModel);
		log.info("QuotationStatus data with ID: "+ quotationStatusModel.getQuotationStatusId()+" deleted succesfully");

	}

	@Override
	public void deleteQuotationStatusByTds(Integer[] quotationStatusIds) {
		for (Integer quotationStatusId : quotationStatusIds) {
			QuotationStatusModel quotationStatusModel = getQuotationStatusModel(quotationStatusId);
			if (!Objects.nonNull(quotationStatusModel)) {
				throw new IHealthPharmException(quotationStatusHelper.getNotFoundQuotationStatusMessage(), HttpStatus.NOT_FOUND);
			}
			quotationStatusRepository.delete(quotationStatusModel);
			log.info("QuotationStatus data with ID: "+ quotationStatusModel.getQuotationStatusId()+" deleted succesfully");
		}

	}

	private QuotationStatusModel getQuotationStatusModel(Integer quotationStatusId) {
		QuotationStatusModel quotationStatusModel = null;
		try {
			quotationStatusModel = quotationStatusRepository.findById(quotationStatusId).get();
			return quotationStatusModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(quotationStatusHelper.getNotFoundQuotationStatusMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<QuotationStatusModel> findAllQuotationStatus() {
		return quotationStatusRepository.findAllByOrderByStatusDesc();
	}

}
