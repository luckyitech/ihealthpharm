package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.stock.dao.QuotationItemStatusRepository;
import com.ihealthpharm.stock.helper.QuotationItemStatusHelper;
import com.ihealthpharm.stock.model.QuotationItemStatusModel;
import com.ihealthpharm.stock.service.QuotationItemStatusService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class QuotationItemStatusServiceImpl implements QuotationItemStatusService {

	@Autowired
	QuotationItemStatusRepository  quotationItemStatusRepository;

	@Autowired
	QuotationItemStatusHelper quotationItemStatusHelper;

	@Override
	public QuotationItemStatusModel saveQuotationItemStatus(QuotationItemStatusModel quotationItemStatusModel) {
		quotationItemStatusModel = quotationItemStatusRepository.save(quotationItemStatusModel);
		log.info("QuotationItemStatus data with ID: " + quotationItemStatusModel.getQuotationItemStatusId() + " saved succesfully");
		return quotationItemStatusModel;
	}

	@Override
	public QuotationItemStatusModel updateQuotationItemStatus(QuotationItemStatusModel quotationItemStatusModel) {
		QuotationItemStatusModel model = getQuotationItemStatusModel(quotationItemStatusModel.getQuotationItemStatusId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(quotationItemStatusHelper.getNotFoundQuotationItemStatusMessage(), HttpStatus.NOT_FOUND);
		}
		model = quotationItemStatusRepository.save(quotationItemStatusModel);
		log.info("QuotationItemStatus data with ID : " + model.getQuotationItemStatusId() + " updated succesfully");
		return model;
	}

	@Override
	public List<QuotationItemStatusModel> updateQuotationItemStatus(List<QuotationItemStatusModel> quotationItemStatusModels) {
		for (QuotationItemStatusModel quotationItemStatusModel : quotationItemStatusModels) {
			QuotationItemStatusModel model = getQuotationItemStatusModel(quotationItemStatusModel.getQuotationItemStatusId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(quotationItemStatusHelper.getNotFoundQuotationItemStatusMessage(), HttpStatus.NOT_FOUND);
			}
			model = quotationItemStatusRepository.save(quotationItemStatusModel);
			log.info("QuotationItemStatus data with Multiple IDs : " + model.getQuotationItemStatusId() + " updated succesfully");
		}
		return quotationItemStatusModels;
	}

	@Override
	public QuotationItemStatusModel findQuotationItemStatusById(Integer quotationItemStatusId) {
		QuotationItemStatusModel quotationItemStatusModel = getQuotationItemStatusModel(quotationItemStatusId);
		if (!Objects.nonNull(quotationItemStatusModel)) {
			throw new IHealthPharmException(quotationItemStatusHelper.getNotFoundQuotationItemStatusMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("QuotationItemStatus data with ID : "+ quotationItemStatusModel.getQuotationItemStatusId()+" retrieved succesfully");
		return quotationItemStatusModel;
	}

	@Override
	public void deleteQuotationItemStatusById(Integer quotationItemStatusId) {
		QuotationItemStatusModel quotationItemStatusModel = getQuotationItemStatusModel(quotationItemStatusId);
		if (!Objects.nonNull(quotationItemStatusModel)) {
			throw new IHealthPharmException(quotationItemStatusHelper.getNotFoundQuotationItemStatusMessage(), HttpStatus.NOT_FOUND);
		}		
		quotationItemStatusRepository.delete(quotationItemStatusModel);
		log.info("QuotationItemStatus data with ID: "+ quotationItemStatusModel.getQuotationItemStatusId()+" deleted succesfully");

	}

	@Override
	public void deleteQuotationItemStatusByTds(Integer[] quotationItemStatusIds) {
		for (Integer quotationItemStatusId : quotationItemStatusIds) {
			QuotationItemStatusModel quotationItemStatusModel = getQuotationItemStatusModel(quotationItemStatusId);
			if (!Objects.nonNull(quotationItemStatusModel)) {
				throw new IHealthPharmException(quotationItemStatusHelper.getNotFoundQuotationItemStatusMessage(), HttpStatus.NOT_FOUND);
			}
			quotationItemStatusRepository.delete(quotationItemStatusModel);
			log.info("QuotationItemStatus data with ID: "+ quotationItemStatusModel.getQuotationItemStatusId()+" deleted succesfully");
		}

	}

	private QuotationItemStatusModel getQuotationItemStatusModel(Integer quotationItemStatusId) {
		QuotationItemStatusModel quotationItemStatusModel = null;
		try {
			quotationItemStatusModel = quotationItemStatusRepository.findById(quotationItemStatusId).get();
			return quotationItemStatusModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(quotationItemStatusHelper.getNotFoundQuotationItemStatusMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<QuotationItemStatusModel> findAllQuotationItemStatus() {
		return quotationItemStatusRepository.findAllByOrderByStatusDesc();
	}

}
