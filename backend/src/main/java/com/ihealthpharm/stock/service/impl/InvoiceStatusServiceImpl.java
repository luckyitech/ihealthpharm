package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import com.ihealthpharm.stock.dao.InvoiceStatusRepository;
import com.ihealthpharm.stock.service.InvoiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import  com.ihealthpharm.stock.model.*;
import com.ihealthpharm.stock.helper.*;

import com.ihealthpharm.exception.IHealthPharmException;


import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class InvoiceStatusServiceImpl implements InvoiceStatusService {

	@Autowired
	InvoiceStatusRepository invoiceStatusRepository;

	@Autowired
	InvoiceStatusHelper invoiceStatusHelper;

	@Override
	public InvoiceStatusModel saveInvoiceStatus(InvoiceStatusModel invoiceStatusModel) {
		invoiceStatusModel = invoiceStatusRepository.save(invoiceStatusModel);
		log.info("InvoiceStatus data with ID: " + invoiceStatusModel.getInvoiceStatusId() + " saved succesfully");
		return invoiceStatusModel;
	}

	@Override
	public InvoiceStatusModel updateInvoiceStatus(InvoiceStatusModel invoiceStatusModel) {
		InvoiceStatusModel model = getInvoiceStatusModel(invoiceStatusModel.getInvoiceStatusId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(invoiceStatusHelper.getNotFoundInvoiceStatusMessage(), HttpStatus.NOT_FOUND);
		}
		model = invoiceStatusRepository.save(invoiceStatusModel);
		log.info("InvoiceStatus data with ID : " + model.getInvoiceStatusId() + " updated succesfully");
		return model;
	}

	@Override
	public List<InvoiceStatusModel> updateAllInvoiceStatus(List<InvoiceStatusModel> invoiceStatusModels) {
		for (InvoiceStatusModel invoiceStatusModel : invoiceStatusModels) {
			InvoiceStatusModel model = getInvoiceStatusModel(invoiceStatusModel.getInvoiceStatusId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(invoiceStatusHelper.getNotFoundInvoiceStatusMessage(), HttpStatus.NOT_FOUND);
			}
			model = invoiceStatusRepository.save(invoiceStatusModel);
			log.info("InvoiceStatus data with Multiple IDs : " + model.getInvoiceStatusId() + " updated succesfully");
		}
		return invoiceStatusModels;
	}

	@Override
	public InvoiceStatusModel findInvoiceStatusById(Integer invoiceStatusId) {
		InvoiceStatusModel invoiceStatusModel = getInvoiceStatusModel(invoiceStatusId);
		if (!Objects.nonNull(invoiceStatusModel)) {
			throw new IHealthPharmException(invoiceStatusHelper.getNotFoundInvoiceStatusMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("InvoiceStatus data with ID : "+ invoiceStatusModel.getInvoiceStatusId()+" retrieved succesfully");
		return invoiceStatusModel;
	}

	@Override
	public void deleteInvoiceStatusById(Integer invoiceStatusId) {
		InvoiceStatusModel invoiceStatusModel = getInvoiceStatusModel(invoiceStatusId);
		if (!Objects.nonNull(invoiceStatusModel)) {
			throw new IHealthPharmException(invoiceStatusHelper.getNotFoundInvoiceStatusMessage(), HttpStatus.NOT_FOUND);
		}		
		invoiceStatusRepository.delete(invoiceStatusModel);
		log.info("InvoiceStatus data with ID: "+ invoiceStatusModel.getInvoiceStatusId()+" deleted succesfully");

	}

	@Override
	public void deleteInvoiceStatusByTds(Integer[] invoiceStatusIds) {
		for (Integer invoiceStatusId : invoiceStatusIds) {
			InvoiceStatusModel invoiceStatusModel = getInvoiceStatusModel(invoiceStatusId);
			if (!Objects.nonNull(invoiceStatusModel)) {
				throw new IHealthPharmException(invoiceStatusHelper.getNotFoundInvoiceStatusMessage(), HttpStatus.NOT_FOUND);
			}
			invoiceStatusRepository.delete(invoiceStatusModel);
			log.info("InvoiceStatus data with ID: "+ invoiceStatusModel.getInvoiceStatusId()+" deleted succesfully");
		}

	}

	private InvoiceStatusModel getInvoiceStatusModel(Integer invoiceStatusId) {
		InvoiceStatusModel invoiceStatusModel = null;
		try {
			invoiceStatusModel = invoiceStatusRepository.findById(invoiceStatusId).get();
			return invoiceStatusModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(invoiceStatusHelper.getNotFoundInvoiceStatusMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<InvoiceStatusModel> findAllInvoiceStatus() {
		return invoiceStatusRepository.findAllByOrderByStatusDesc();
	}

}
