package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import com.ihealthpharm.stock.service.PurchaseOrderStatusService;
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
public class PurchaseOrderStatusServiceImpl implements PurchaseOrderStatusService {

	@Autowired
	PurchaseOrderStatusRepository  purchaseOrderStatusRepository;

	@Autowired
	PurchaseOrderStatusHelper purchaseOrderStatusHelper;

	@Override
	public PurchaseOrderStatusModel savePurchaseOrderStatus(PurchaseOrderStatusModel purchaseOrderStatusModel) {
		purchaseOrderStatusModel = purchaseOrderStatusRepository.save(purchaseOrderStatusModel);
		log.info("PurchaseOrderStatus data with ID: " + purchaseOrderStatusModel.getPurchaseOrderStatusId() + " saved succesfully");
		return purchaseOrderStatusModel;
	}

	@Override
	public PurchaseOrderStatusModel updatePurchaseOrderStatus(PurchaseOrderStatusModel purchaseOrderStatusModel) {
		PurchaseOrderStatusModel model = getPurchaseOrderStatusModel(purchaseOrderStatusModel.getPurchaseOrderStatusId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(purchaseOrderStatusHelper.getNotFoundPurchaseOrderStatusMessage(), HttpStatus.NOT_FOUND);
		}
		model = purchaseOrderStatusRepository.save(purchaseOrderStatusModel);
		log.info("PurchaseOrderStatus data with ID : " + model.getPurchaseOrderStatusId() + " updated succesfully");
		return model;
	}

	@Override
	public List<PurchaseOrderStatusModel> updatePurchaseOrderStatuss(List<PurchaseOrderStatusModel> purchaseOrderStatusModels) {
		for (PurchaseOrderStatusModel purchaseOrderStatusModel : purchaseOrderStatusModels) {
			PurchaseOrderStatusModel model = getPurchaseOrderStatusModel(purchaseOrderStatusModel.getPurchaseOrderStatusId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(purchaseOrderStatusHelper.getNotFoundPurchaseOrderStatusMessage(), HttpStatus.NOT_FOUND);
			}
			model = purchaseOrderStatusRepository.save(purchaseOrderStatusModel);
			log.info("PurchaseOrderStatus data with Multiple IDs : " + model.getPurchaseOrderStatusId() + " updated succesfully");
		}
		return purchaseOrderStatusModels;
	}

	@Override
	public PurchaseOrderStatusModel findPurchaseOrderStatusById(Integer purchaseOrderStatusId) {
		PurchaseOrderStatusModel purchaseOrderStatusModel = getPurchaseOrderStatusModel(purchaseOrderStatusId);
		if (!Objects.nonNull(purchaseOrderStatusModel)) {
			throw new IHealthPharmException(purchaseOrderStatusHelper.getNotFoundPurchaseOrderStatusMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("PurchaseOrderStatus data with ID : "+ purchaseOrderStatusModel.getPurchaseOrderStatusId()+" retrieved succesfully");
		return purchaseOrderStatusModel;
	}

	@Override
	public void deletePurchaseOrderStatusById(Integer purchaseOrderStatusId) {
		PurchaseOrderStatusModel purchaseOrderStatusModel = getPurchaseOrderStatusModel(purchaseOrderStatusId);
		if (!Objects.nonNull(purchaseOrderStatusModel)) {
			throw new IHealthPharmException(purchaseOrderStatusHelper.getNotFoundPurchaseOrderStatusMessage(), HttpStatus.NOT_FOUND);
		}		
		purchaseOrderStatusRepository.delete(purchaseOrderStatusModel);
		log.info("PurchaseOrderStatus data with ID: "+ purchaseOrderStatusModel.getPurchaseOrderStatusId()+" deleted succesfully");

	}

	@Override
	public void deletePurchaseOrderStatusByTds(Integer[] purchaseOrderStatusIds) {
		for (Integer purchaseOrderStatusId : purchaseOrderStatusIds) {
			PurchaseOrderStatusModel purchaseOrderStatusModel = getPurchaseOrderStatusModel(purchaseOrderStatusId);
			if (!Objects.nonNull(purchaseOrderStatusModel)) {
				throw new IHealthPharmException(purchaseOrderStatusHelper.getNotFoundPurchaseOrderStatusMessage(), HttpStatus.NOT_FOUND);
			}
			purchaseOrderStatusRepository.delete(purchaseOrderStatusModel);
			log.info("PurchaseOrderStatus data with ID: "+ purchaseOrderStatusModel.getPurchaseOrderStatusId()+" deleted succesfully");
		}

	}

	private PurchaseOrderStatusModel getPurchaseOrderStatusModel(Integer purchaseOrderStatusId) {
		PurchaseOrderStatusModel purchaseOrderStatusModel = null;
		try {
			purchaseOrderStatusModel = purchaseOrderStatusRepository.findById(purchaseOrderStatusId).get();
			return purchaseOrderStatusModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(purchaseOrderStatusHelper.getNotFoundPurchaseOrderStatusMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<PurchaseOrderStatusModel> findAllPurchaseOrderStatus() {
		return purchaseOrderStatusRepository.findAllByOrderByStatusDesc();
	}

	@Override
	public PurchaseOrderStatusModel findByStatus(String status) {
		return purchaseOrderStatusRepository.findByStatus(status);
	}

}
