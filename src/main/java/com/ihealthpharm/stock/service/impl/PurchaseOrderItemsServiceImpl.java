package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.stock.dao.PurchaseOrderItemsRepository;
import com.ihealthpharm.stock.helper.PurchaseOrderItemsHelper;
import com.ihealthpharm.stock.model.PurchaseOrderItemsModel;
import com.ihealthpharm.stock.service.PurchaseOrderItemsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PurchaseOrderItemsServiceImpl implements PurchaseOrderItemsService {

	@Autowired
	PurchaseOrderItemsRepository purchaseOrderItemsRepository;

	@Autowired
	PurchaseOrderItemsHelper purchaseOrderItemsHelper;

	@Override
	public PurchaseOrderItemsModel savePurchaseOrderItemsData(PurchaseOrderItemsModel purchaseOrderItems) {
		PurchaseOrderItemsModel purchaseOrderItemsRes = purchaseOrderItemsRepository.save(purchaseOrderItems);
		log.info("PurchaseOrderItems data with ID : " + purchaseOrderItemsRes.getPurchaseOrderItemsId() + " Saved succesfully");
		return purchaseOrderItemsRes;
	}

	@Override
	public PurchaseOrderItemsModel updatePurchaseOrderItemsData(PurchaseOrderItemsModel purchaseOrderItems) {
		PurchaseOrderItemsModel purchaseOrderItemsRes = getValidPurchaseOrderItemss(purchaseOrderItems.getPurchaseOrderItemsId());
		if (!Objects.nonNull(purchaseOrderItemsRes)) {
			throw new IHealthPharmException(purchaseOrderItemsHelper.getNotFoundPurchaseOrderItemsMessage(),
					HttpStatus.NOT_FOUND);
		}

		purchaseOrderItemsRes = purchaseOrderItemsRepository.save(purchaseOrderItems);
		log.info("PurchaseOrderItems data with ID : " + purchaseOrderItemsRes.getPurchaseOrderItemsId() + " updated succesfully");
		return purchaseOrderItemsRes;
	}

	@Override
	public List<PurchaseOrderItemsModel> updatePurchaseOrderItemssData(List<PurchaseOrderItemsModel> purchaseOrderItemss) {
		for (PurchaseOrderItemsModel purchaseOrderItems : purchaseOrderItemss) {
			PurchaseOrderItemsModel purchaseOrderItemsRes = getValidPurchaseOrderItemss(purchaseOrderItems.getPurchaseOrderItemsId());
			if (!Objects.nonNull(purchaseOrderItemsRes)) {
				throw new IHealthPharmException(purchaseOrderItemsHelper.getNotFoundPurchaseOrderItemsMessage(),
						HttpStatus.NOT_FOUND);
			}

			purchaseOrderItemsRes = purchaseOrderItemsRepository.save(purchaseOrderItems);
			log.info("PurchaseOrderItems data with ID : " + purchaseOrderItemsRes.getPurchaseOrderItemsId() + " updated succesfully");
		}
		return purchaseOrderItemss;
	}

	@Override
	public List<PurchaseOrderItemsModel> findAllPurchaseOrderItems() {

		return purchaseOrderItemsRepository.findAll();
	}

	@Override
	public PurchaseOrderItemsModel findPurchaseOrderItemsById(Integer purchaseOrderItemsId) {
		PurchaseOrderItemsModel purchaseOrderItemsRes = getValidPurchaseOrderItemss(purchaseOrderItemsId);
		if (!Objects.nonNull(purchaseOrderItemsRes)) {
			throw new IHealthPharmException(purchaseOrderItemsHelper.getNotFoundPurchaseOrderItemsMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("PurchaseOrderItems data with ID : " + purchaseOrderItemsRes.getPurchaseOrderItemsId() + " retrieved succesfully");
		return purchaseOrderItemsRes;
	}

	@Override
	public void deletePurchaseOrderItemsById(Integer purchaseOrderItemsId) {
		PurchaseOrderItemsModel purchaseOrderItemsRes = purchaseOrderItemsRepository.getOne(purchaseOrderItemsId);
		if (!Objects.nonNull(purchaseOrderItemsRes)) {
			throw new IHealthPharmException(purchaseOrderItemsHelper.getNotFoundPurchaseOrderItemsMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("PurchaseOrderItems data with ID : " + purchaseOrderItemsRes.getPurchaseOrderItemsId() + " Deleted succesfully");
		purchaseOrderItemsRepository.delete(purchaseOrderItemsRes);

	}

	@Override
	public void deletePurchaseOrderItemssById(Integer[] purchaseOrderItemsIds) {
		PurchaseOrderItemsModel purchaseOrderItemsRes;
		for (int purchaseOrderItems : purchaseOrderItemsIds) {
			purchaseOrderItemsRes = getValidPurchaseOrderItemss(purchaseOrderItems);
			if (!Objects.nonNull(purchaseOrderItemsRes)) {
				throw new IHealthPharmException(purchaseOrderItemsHelper.getNotFoundPurchaseOrderItemsMessage(),
						HttpStatus.NOT_FOUND);
			}
			purchaseOrderItemsRepository.delete(purchaseOrderItemsRes);
			log.info("PurchaseOrderItems data with ID: " + purchaseOrderItemsRes.getPurchaseOrderItemsId() + " deleted succesfully");
		}
	}

	public PurchaseOrderItemsModel getValidPurchaseOrderItemss(Integer purchaseOrderItemsId) {
		PurchaseOrderItemsModel purchaseOrderItemsRes = null;

		try {
			purchaseOrderItemsRes = purchaseOrderItemsRepository.findById(purchaseOrderItemsId).get();
			purchaseOrderItemsRes.getItemsModel();
			return purchaseOrderItemsRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(purchaseOrderItemsHelper.getNotFoundPurchaseOrderItemsMessage(),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<String> findManufacturerByPurchaseOrderItem(String searchTerm) {
		return purchaseOrderItemsRepository.findManucaturerInPurchaseOrderPBPD(searchTerm);
	}

	@Override
	public List<String> findAllManufacturerByPurchaseOrderItem() {
		return purchaseOrderItemsRepository.findAllManucaturerInPurchaseOrderPBPD();
	}

	@Override
	public List<String> findSuppliersByPurchaseOrderItem(String searchTerm) {
		return purchaseOrderItemsRepository.findSupplierInPurchaseOrderPBPD(searchTerm);
	}

	@Override
	public List<String> findAllSuppliersByPurchaseOrderItem() {
		return purchaseOrderItemsRepository.findAllSuppliersInPurchaseOrderPBPD();
	}

}
