package com.ihealthpharm.stock.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.stock.dao.PurchaseReturnItemRepository;
import com.ihealthpharm.stock.dao.PurchaseReturnRepository;
import com.ihealthpharm.stock.helper.PurchaseReturnHelper;
import com.ihealthpharm.stock.model.PurchaseReturnModel;
import com.ihealthpharm.stock.service.PurchaseReturnService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PurchaseReturnServiceImpl implements PurchaseReturnService {

	@Autowired
	PurchaseReturnRepository purchaseReturnDao;
	
	@Autowired
	PurchaseReturnItemRepository purchaseItemReturnDao;
	
	@Autowired 
	PurchaseReturnHelper purchaseReturnHelper;

	@Override
	public PurchaseReturnModel savePurchaseReturns(PurchaseReturnModel purchaseReturnModel) {
		PurchaseReturnModel purchaseRtnObj = purchaseReturnDao.save(purchaseReturnModel);
		log.info("PurchaseOrder data with ID : " + purchaseRtnObj.getPurchaseReturnId() + " Saved succesfully");
		return purchaseRtnObj;
	}
	
	@Override
	public PurchaseReturnModel updatePurchaseReturns(PurchaseReturnModel purchaseReturnModel) {
		
		PurchaseReturnModel purchaseReturnResp = validatePurchaseReturn(purchaseReturnModel.getPurchaseReturnId());
		if (!Objects.nonNull(purchaseReturnResp)) {
			throw new IHealthPharmException(purchaseReturnHelper.getNotFoundPurchaseReturnMessage(),
					HttpStatus.NOT_FOUND);
		}
		PurchaseReturnModel purchaseRtnObj = purchaseReturnDao.save(purchaseReturnModel);	
		log.info("PurchaseReturn data with ID : " + purchaseRtnObj.getPurchaseReturnId() + " updated succesfully");
		return purchaseRtnObj;
	}
	
	@Override
	public List<PurchaseReturnModel> findAllPurchaseReturns() {
		return purchaseReturnDao.findAll();
	}
	
	@Override
	public PurchaseReturnModel findPurchaseRetrunById(Integer purchaseRetrunId) {
		PurchaseReturnModel purchaseReturnResp = validatePurchaseReturn(purchaseRetrunId);
		if (!Objects.nonNull(purchaseReturnResp)) {
			throw new IHealthPharmException(purchaseReturnHelper.getNotFoundPurchaseReturnMessage(),
					HttpStatus.NOT_FOUND);
		}
		log.info("PurchaseRetrun data with ID : " + purchaseReturnResp.getPurchaseReturnId() + " retrieved succesfully");
		return purchaseReturnResp;
	}
	
	
	public PurchaseReturnModel validatePurchaseReturn(Integer purchaseRetrunId) {
		try {
			return purchaseReturnDao.findById(purchaseRetrunId).get();
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(purchaseReturnHelper.getNotFoundPurchaseReturnMessage(),
					HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public void deletePurchaseReturnById(Integer purchaseRetrunId) {
		PurchaseReturnModel purchaseorderRes = purchaseReturnDao.getOne(purchaseRetrunId);
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseReturnHelper.getNotFoundPurchaseReturnMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("PurchaseRetrun data with ID : " + purchaseorderRes.getPurchaseReturnId() + " Deleted succesfully");
		purchaseReturnDao.delete(purchaseorderRes);

	}

	@Override
	public void deletePurchaseReturnByIds(Integer[] purchaseRetrunIds) {
		PurchaseReturnModel purchaseRetrunRes;
		for (int purchaseRtnId : purchaseRetrunIds) {
			purchaseRetrunRes = validatePurchaseReturn(purchaseRtnId);
			if (!Objects.nonNull(purchaseRetrunRes)) {
				throw new IHealthPharmException(purchaseReturnHelper.getNotFoundPurchaseReturnMessage(),
						HttpStatus.NOT_FOUND);
			}
			purchaseReturnDao.delete(purchaseRetrunRes);
			log.info("PurchaseOrder data with ID: " + purchaseRetrunRes.getPurchaseReturnId() + " deleted succesfully");
		}
	}


	@Override
	public List<PurchaseReturnModel> updatePurchaseReturns(List<PurchaseReturnModel> purchaseReturnModels) {
		List<PurchaseReturnModel> purchaseRtnList = new ArrayList<PurchaseReturnModel>();
		for (PurchaseReturnModel purchaseReturn : purchaseReturnModels) {
			PurchaseReturnModel purchaseRtnRes = validatePurchaseReturn(purchaseReturn.getPurchaseReturnId());
			if (!Objects.nonNull(purchaseRtnRes)) {
				throw new IHealthPharmException(purchaseReturnHelper.getNotFoundPurchaseReturnMessage(),
						HttpStatus.NOT_FOUND);
			}

			purchaseRtnRes = purchaseReturnDao.save(purchaseReturn);
			purchaseRtnList.add(purchaseRtnRes);
			log.info("PurchaseOrder data with ID : " + purchaseRtnRes.getPurchaseReturnId() + " updated succesfully");
		}
		return purchaseRtnList;
	}
}
