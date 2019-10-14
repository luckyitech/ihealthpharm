package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.dao.PurchaseOrderItemsRepository;
import com.ihealthpharm.stock.dao.PurchaseOrderRepository;
import com.ihealthpharm.stock.dao.PurchaseOrderStatusRepository;
import com.ihealthpharm.stock.helper.PurchaseOrderHelper;
import com.ihealthpharm.stock.model.PurchaseOrderItemsModel;
import com.ihealthpharm.stock.model.PurchaseOrderModel;
import com.ihealthpharm.stock.model.PurchaseOrderStatusModel;
import com.ihealthpharm.stock.service.PurchaseOrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseorderRepository;
	
	@Autowired
	PurchaseOrderItemsRepository purchaseOrderItemsRepository;
	
	@Autowired
	PurchaseOrderStatusRepository  purchaseOrderStatusRepository;

	@Autowired
	PurchaseOrderHelper purchaseorderHelper;

	@Override
	public PurchaseOrderModel savePurchaseOrderData(PurchaseOrderModel purchaseorder) {
		
		List<PurchaseOrderItemsModel> itemsModels = purchaseorder.getPurchaseorderitems();
		
		PurchaseOrderModel purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		
		for(PurchaseOrderItemsModel p : itemsModels) {
			purchaseOrderItemsRepository.save(p);
		}
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " Saved succesfully");
		return purchaseorderRes;
	}
	
	@Override
	public PurchaseOrderModel savePurchaseOrderData(PurchaseOrderModel purchaseorder, String status) {
		
		List<PurchaseOrderItemsModel> itemsModels = purchaseorder.getPurchaseorderitems();
		
		PurchaseOrderStatusModel orderStatusModel  = purchaseOrderStatusRepository.findByStatus(status);
		purchaseorder.setPurchaseOrderStatusModel(orderStatusModel);
		
		PurchaseOrderModel purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		
		for(PurchaseOrderItemsModel p : itemsModels) {
			purchaseOrderItemsRepository.save(p);
		}
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " Saved succesfully");
		return purchaseorderRes;
	}

	@Override
	public PurchaseOrderModel updatePurchaseOrderData(PurchaseOrderModel purchaseorder) {
		List<PurchaseOrderItemsModel> itemsModels = purchaseorder.getPurchaseorderitems();
		
		PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorder.getPurchaseOrderId());
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}

		purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		
		for(PurchaseOrderItemsModel p : itemsModels) {
			purchaseOrderItemsRepository.save(p);
		}
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " updated succesfully");
		return purchaseorderRes;
	}
	
	@Override
	public PurchaseOrderModel updatePurchaseOrderData(PurchaseOrderModel purchaseorder, String status) {
		List<PurchaseOrderItemsModel> itemsModels = purchaseorder.getPurchaseorderitems();
		
		PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorder.getPurchaseOrderId());
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}

		PurchaseOrderStatusModel orderStatusModel  = purchaseOrderStatusRepository.findByStatus(status);
		purchaseorder.setPurchaseOrderStatusModel(orderStatusModel);
		
		purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		
		for(PurchaseOrderItemsModel p : itemsModels) {
			purchaseOrderItemsRepository.save(p);
		}
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " updated succesfully");
		return purchaseorderRes;
	}

	@Override
	public List<PurchaseOrderModel> updatePurchaseOrdersData(List<PurchaseOrderModel> purchaseorders) {
		for (PurchaseOrderModel purchaseorder : purchaseorders) {
			PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorder.getPurchaseOrderId());
			if (!Objects.nonNull(purchaseorderRes)) {
				throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
						HttpStatus.NOT_FOUND);
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
	public PurchaseOrderModel findPurchaseOrderById(Integer purchaseorderId) {
		PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorderId);
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " retrieved succesfully");
		return purchaseorderRes;
	}

	@Override
	public void deletePurchaseOrderById(Integer purchaseorderId) {
		PurchaseOrderModel purchaseorderRes = purchaseorderRepository.getOne(purchaseorderId);
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " Deleted succesfully");
		purchaseorderRepository.delete(purchaseorderRes);

	}

	@Override
	public void deletePurchaseOrdersById(Integer[] purchaseorderIds) {
		PurchaseOrderModel purchaseorderRes;
		for (int purchaseorder : purchaseorderIds) {
			purchaseorderRes = getValidPurchaseOrders(purchaseorder);
			if (!Objects.nonNull(purchaseorderRes)) {
				throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
						HttpStatus.NOT_FOUND);
			}
			purchaseorderRepository.delete(purchaseorderRes);
			log.info("PurchaseOrder data with ID: " + purchaseorderRes.getPurchaseOrderId() + " deleted succesfully");
		}
	}

	public PurchaseOrderModel getValidPurchaseOrders(Integer purchaseorderId) {
		PurchaseOrderModel purchaseorderRes = null;

		try {
			purchaseorderRes = purchaseorderRepository.findById(purchaseorderId).get();
			return purchaseorderRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public Long getPurchaseOrderCount(Integer pharmacyId) {
		log.info("Pharmacy Id: "+ pharmacyId+" ");
		return purchaseorderRepository.getPurchaseOrderCount(pharmacyId);
	}

	@Override
	public List<DistributorModel> getDistributorsByQuotationId(Integer quotationId) {
		log.info("QuotationId Id: "+ quotationId+" ");
		return purchaseorderRepository.getDistributorsByQuotationId(quotationId);
	}

	@Override
	public List<ItemsModel> getItemsByDistributorAndQuotation(Integer quotationId, Integer distributorId) {
		log.info("QuotationId Id: "+ quotationId+" Distributor Id "+distributorId);
		return purchaseorderRepository.getItemsByDistributorAndQuotation(quotationId, distributorId);
	}

	@Override
	public List<ItemsModel> getItemsByPurchaseOrder(Integer purchaseOrderId) {
		log.info("PurchaseOrder Id: "+ purchaseOrderId);
		return purchaseorderRepository.getItemsByPurchaseOrder(purchaseOrderId);
	}
	
	@Override
	public List<PurchaseOrderModel> getPurchaseOrderByPharmacy(Integer pharmacyId) {
		log.info("Pharmacy Id: "+ pharmacyId);
		return purchaseorderRepository.getPurchaseOrderByPharmacy(pharmacyId);
	}

	@Override
	public List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(Integer pharmacyId, String status) {
		log.info("Pharmacy Id: "+ pharmacyId+" Status "+status);
		return purchaseorderRepository.getPurchaseOrderByPharmacyAndStatus(pharmacyId, status);
	}

}
