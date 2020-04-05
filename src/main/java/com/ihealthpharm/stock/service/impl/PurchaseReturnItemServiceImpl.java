package com.ihealthpharm.stock.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.stock.dao.PurchaseReturnItemRepository;
import com.ihealthpharm.stock.helper.PurchaseReturnItemHelper;
import com.ihealthpharm.stock.model.PurchaseReturnItemModel;
import com.ihealthpharm.stock.service.PurchaseReturnItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PurchaseReturnItemServiceImpl implements PurchaseReturnItemService {
	
	@Autowired 
	PurchaseReturnItemRepository purchaseReturnItemRepository;
	
	@Autowired
	PurchaseReturnItemHelper purchaseReturnItemsHelper;

	@Override
	public PurchaseReturnItemModel savePurchaseReturnItemData(PurchaseReturnItemModel purchaseReturnItemModel) {
		PurchaseReturnItemModel purchaseReturnItemsRes = purchaseReturnItemRepository.save(purchaseReturnItemModel);
		log.info("PurchaseRetrunItems data with ID : " + purchaseReturnItemsRes.getPurchaseReturnItemId() + " Saved succesfully");
		return purchaseReturnItemsRes;
	}

	@Override
	public PurchaseReturnItemModel updatePurchaseReturnItemData(PurchaseReturnItemModel purchaseReturnItemModel) {

		PurchaseReturnItemModel purchaseReturnItemsRes = getValidPurchaseReturnItems(purchaseReturnItemModel.getPurchaseReturnItemId());
		if (!Objects.nonNull(purchaseReturnItemsRes)) {
			throw new IHealthPharmException(purchaseReturnItemsHelper.getNotFoundPurchaseReturnItemMessage(),
					HttpStatus.NOT_FOUND);
		}

		purchaseReturnItemsRes = purchaseReturnItemRepository.save(purchaseReturnItemsRes);
		log.info("PurchaseReturnItems data with ID : " + purchaseReturnItemsRes.getPurchaseReturnItemId() + " updated succesfully");
		return purchaseReturnItemsRes;
	
	}
	
	@Override
	public List<PurchaseReturnItemModel> updatePurchaseReturnItemsData(List<PurchaseReturnItemModel> purchaseReturnItemModel) {
		List<PurchaseReturnItemModel> purchaseRtnItmList = new ArrayList<PurchaseReturnItemModel>();
		for (PurchaseReturnItemModel purchaseReturnItems : purchaseReturnItemModel) {
			PurchaseReturnItemModel PurchaseReturnItemsRes = getValidPurchaseReturnItems(purchaseReturnItems.getPurchaseReturnItemId());
			if (!Objects.nonNull(PurchaseReturnItemsRes)) {
				throw new IHealthPharmException(purchaseReturnItemsHelper.getNotFoundPurchaseReturnItemMessage(),
						HttpStatus.NOT_FOUND);
			}

			PurchaseReturnItemsRes = purchaseReturnItemRepository.save(purchaseReturnItems);
			purchaseRtnItmList.add(PurchaseReturnItemsRes);
			log.info("PurchaseReturnItems data with ID : " + PurchaseReturnItemsRes.getPurchaseReturnItemId() + " updated succesfully");
		}
		return purchaseRtnItmList;
	}
	
	@Override
	public List<PurchaseReturnItemModel> findAllPurchaseReturnItems() {
		return purchaseReturnItemRepository.findAll();
	}
	
	@Override
	public PurchaseReturnItemModel findPurchaseReturnItemsById(Integer purchaseReturnItemsId) {
		PurchaseReturnItemModel purchaseReturnItemsRes = getValidPurchaseReturnItems(purchaseReturnItemsId);
		if (!Objects.nonNull(purchaseReturnItemsRes)) {
			throw new IHealthPharmException(purchaseReturnItemsHelper.getNotFoundPurchaseReturnItemMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("PurchaseReturnItems data with ID : " + purchaseReturnItemsRes.getPurchaseReturnItemId() + " retrieved succesfully");
		return purchaseReturnItemsRes;
	}

	@Override
	public void deletePurchaseReturnItemsById(Integer PurchaseReturnItemsId) {
		PurchaseReturnItemModel purchaseReturnItemsRes = purchaseReturnItemRepository.getOne(PurchaseReturnItemsId);
		if (!Objects.nonNull(purchaseReturnItemsRes)) {
			throw new IHealthPharmException(purchaseReturnItemsHelper.getNotFoundPurchaseReturnItemMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("PurchaseReturnItems data with ID : " + purchaseReturnItemsRes.getPurchaseReturnItemId() + " Deleted succesfully");
		purchaseReturnItemRepository.delete(purchaseReturnItemsRes);
	}
	
	@Override
	public void deletePurchaseReturnItemsById(Integer[] purchaseReturnIds) {
		PurchaseReturnItemModel PurchaseReturnItemsRes;
		for (int PurchaseReturnItems : purchaseReturnIds) {
			PurchaseReturnItemsRes = getValidPurchaseReturnItems(PurchaseReturnItems);
			if (!Objects.nonNull(PurchaseReturnItemsRes)) {
				throw new IHealthPharmException(purchaseReturnItemsHelper.getNotFoundPurchaseReturnItemMessage(),
						HttpStatus.NOT_FOUND);
			}
			purchaseReturnItemRepository.delete(PurchaseReturnItemsRes);
			log.info("PurchaseReturnItems data with ID: " + PurchaseReturnItemsRes.getPurchaseReturnItemId() + " deleted succesfully");
		}
	}
	
	public PurchaseReturnItemModel getValidPurchaseReturnItems(Integer purchaseReturnItemsId) {
		PurchaseReturnItemModel PurchaseReturnItemsRes = null;

		try {
			PurchaseReturnItemsRes = purchaseReturnItemRepository.findById(purchaseReturnItemsId).get();
			PurchaseReturnItemsRes.getItemsModel();
			return PurchaseReturnItemsRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(purchaseReturnItemsHelper.getNotFoundPurchaseReturnItemMessage(),
					HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public List<PurchaseReturnItemModel> savePurchaseReturnItems(@Valid List<PurchaseReturnItemModel> purchaseReturnItems) {
		List<PurchaseReturnItemModel> purchaseRtnItmList = new ArrayList<PurchaseReturnItemModel>();
		for (PurchaseReturnItemModel purchaseRtnItems : purchaseReturnItems) {
			PurchaseReturnItemModel purchaseReturnItemsRes = purchaseReturnItemRepository.save(purchaseRtnItems);
			purchaseRtnItmList.add(purchaseReturnItemsRes);
			log.info("PurchaseReturnItems data with ID : " + purchaseReturnItemsRes.getPurchaseReturnItemId() + " saved succesfully");
		}
		return purchaseRtnItmList;
	}
	//Purchase Returns

	@Override
	public List<String> findGRNNoByPR(String searchTerm) {
		return purchaseReturnItemRepository.findGRNNoInPurchaseReturnItems(searchTerm);
	}

	@Override
	public List<String> findAllGRNNoByPR() {
		return purchaseReturnItemRepository.findAllGRNNoPurchaseReturnItems() ;
	}

	@Override
	public List<String> findSupplierBySearchPR(String searchTerm) {
		return purchaseReturnItemRepository.findSupplierInPurchaseReturnItems(searchTerm);
	}

	@Override
	public List<String> findAllSuppliersByPR() {
		return purchaseReturnItemRepository.findAllSuppliersInPurchaseReturnItems();
	}

	@Override
	public List<String> findInvoiceNoBySearchPR(String searchTerm) {
		return purchaseReturnItemRepository.findInvoiceNoInPurchaseReturnItems(searchTerm);
	}

	@Override
	public List<String> findAllInvoiceNoByPR() {
		return purchaseReturnItemRepository.findAllInvoiceNoPurchaseReturnItems();
	}

	@Override
	public Integer getReturnQtyByItemId(Integer itemId, Integer invoiceId) {
		return purchaseReturnItemRepository.getReturnQtyByItem(itemId,invoiceId);
	}
	
}
