package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import com.ihealthpharm.stock.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;

import  com.ihealthpharm.stock.model.*;
import com.ihealthpharm.stock.helper.*;
import com.ihealthpharm.stock.dao.*;


import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PaymentTypeServiceImpl implements PaymentTypeService {

	@Autowired
	PaymentTypeRepository  paymentTypeRepository;

	@Autowired
	PaymentTypeHelper paymentTypeHelper;

	@Override
	public PaymentTypeModel savePaymentType(PaymentTypeModel paymentTypeModel) {
		paymentTypeModel = paymentTypeRepository.save(paymentTypeModel);
		log.info("PaymentType data with ID: " + paymentTypeModel.getPaymentTypeId() + " saved succesfully");
		return paymentTypeModel;
	}

	@Override
	public PaymentTypeModel updatePaymentType(PaymentTypeModel paymentTypeModel) {
		PaymentTypeModel model = getPaymentTypeModel(paymentTypeModel.getPaymentTypeId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(paymentTypeHelper.getNotFoundPaymentTypeMessage(), HttpStatus.NOT_FOUND);
		}
		model = paymentTypeRepository.save(paymentTypeModel);
		log.info("PaymentType data with ID : " + model.getPaymentTypeId() + " updated succesfully");
		return model;
	}

	@Override
	public List<PaymentTypeModel> updatePaymentTypes(List<PaymentTypeModel> paymentTypeModels) {
		for (PaymentTypeModel paymentTypeModel : paymentTypeModels) {
			PaymentTypeModel model = getPaymentTypeModel(paymentTypeModel.getPaymentTypeId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(paymentTypeHelper.getNotFoundPaymentTypeMessage(), HttpStatus.NOT_FOUND);
			}
			model = paymentTypeRepository.save(paymentTypeModel);
			log.info("PaymentType data with Multiple IDs : " + model.getPaymentTypeId() + " updated succesfully");
		}
		return paymentTypeModels;
	}

	@Override
	public PaymentTypeModel findPaymentTypeById(Integer paymentTypeId) {
		PaymentTypeModel paymentTypeModel = getPaymentTypeModel(paymentTypeId);
		if (!Objects.nonNull(paymentTypeModel)) {
			throw new IHealthPharmException(paymentTypeHelper.getNotFoundPaymentTypeMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("PaymentType data with ID : "+ paymentTypeModel.getPaymentTypeId()+" retrieved succesfully");
		return paymentTypeModel;
	}

	@Override
	public void deletePaymentTypeById(Integer paymentTypeId) {
		PaymentTypeModel paymentTypeModel = getPaymentTypeModel(paymentTypeId);
		if (!Objects.nonNull(paymentTypeModel)) {
			throw new IHealthPharmException(paymentTypeHelper.getNotFoundPaymentTypeMessage(), HttpStatus.NOT_FOUND);
		}		
		paymentTypeRepository.delete(paymentTypeModel);
		log.info("PaymentType data with ID: "+ paymentTypeModel.getPaymentTypeId()+" deleted succesfully");

	}

	@Override
	public void deletePaymentTypeByTds(Integer[] paymentTypeIds) {
		for (Integer paymentTypeId : paymentTypeIds) {
			PaymentTypeModel paymentTypeModel = getPaymentTypeModel(paymentTypeId);
			if (!Objects.nonNull(paymentTypeModel)) {
				throw new IHealthPharmException(paymentTypeHelper.getNotFoundPaymentTypeMessage(), HttpStatus.NOT_FOUND);
			}
			paymentTypeRepository.delete(paymentTypeModel);
			log.info("PaymentType data with ID: "+ paymentTypeModel.getPaymentTypeId()+" deleted succesfully");
		}

	}

	private PaymentTypeModel getPaymentTypeModel(Integer paymentTypeId) {
		PaymentTypeModel paymentTypeModel = null;
		try {
			paymentTypeModel = paymentTypeRepository.findById(paymentTypeId).get();
			return paymentTypeModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(paymentTypeHelper.getNotFoundPaymentTypeMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<PaymentTypeModel> findAllPaymentTypes() {
		return paymentTypeRepository.findAllByOrderByTypeDesc();
	}

}
