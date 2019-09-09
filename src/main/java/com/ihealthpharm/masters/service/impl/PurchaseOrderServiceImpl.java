package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.PurchaseOrderRepository;
import com.ihealthpharm.masters.helper.PurchaseOrderHelper;
import com.ihealthpharm.masters.model.PurchaseOrderModel;
import com.ihealthpharm.masters.service.PurchaseOrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

	@Autowired
	PurchaseOrderRepository purchaseorderRepository;
	
	@Autowired
	PurchaseOrderHelper purchaseorderHelper;
	
	@Override
	public PurchaseOrderModel savePurchaseOrderData(PurchaseOrderModel purchaseorder) {
		PurchaseOrderModel purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " Saved succesfully");
		return purchaseorderRes;
	}

	@Override
	public PurchaseOrderModel updatePurchaseOrderData(PurchaseOrderModel purchaseorder) {
		PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorder.getPurchaseOrderId());
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(), HttpStatus.NOT_FOUND);
		}

		purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " updated succesfully");
		return purchaseorderRes;
	}

	@Override
	public List<PurchaseOrderModel> updatePurchaseOrdersData(List<PurchaseOrderModel> purchaseorders) {
		for (PurchaseOrderModel purchaseorder : purchaseorders) {
			PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorder.getPurchaseOrderId());
			if (!Objects.nonNull(purchaseorderRes)) {
				throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(), HttpStatus.NOT_FOUND);
			}

			purchaseorderRes = purchaseorderRepository.save(purchaseorder);
			log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " updated succesfully");
		}
		return purchaseorders;
	}

	@Override
	public List<PurchaseOrderModel> findAllPurchaseOrders() {
		
		return purchaseorderRepository.findAll();
	}

	@Override
	public PurchaseOrderModel findPurchaseOrderById(int purchaseorderId) {
		PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorderId);
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " retrieved succesfully");
		return purchaseorderRes;
	}

	@Override
	public void deletePurchaseOrderById(int purchaseorderId) {
		PurchaseOrderModel purchaseorderRes = purchaseorderRepository.getOne(purchaseorderId);
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " Deleted succesfully");
		purchaseorderRepository.delete(purchaseorderRes);
		
	}

	@Override
	public void deletePurchaseOrdersById(int[] purchaseorderIds) {
		PurchaseOrderModel purchaseorderRes;
		for (int purchaseorder : purchaseorderIds) {
			purchaseorderRes = getValidPurchaseOrders(purchaseorder);
			if (!Objects.nonNull(purchaseorderRes)) {
				throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(), HttpStatus.NOT_FOUND);
			}
			purchaseorderRepository.delete(purchaseorderRes);
			log.info("PurchaseOrder data with ID: " + purchaseorderRes.getPurchaseOrderId() + " deleted succesfully");
		}
		
	}
	
	public PurchaseOrderModel getValidPurchaseOrders(int purchaseorderId) {
		PurchaseOrderModel purchaseorderRes = null;

		try {
			purchaseorderRes =purchaseorderRepository.findById(purchaseorderId).get();
			return purchaseorderRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
