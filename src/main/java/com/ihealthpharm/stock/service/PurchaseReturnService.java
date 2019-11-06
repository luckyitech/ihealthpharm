package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.stock.model.PurchaseReturnModel;

public interface PurchaseReturnService {

	List<PurchaseReturnModel> updatePurchaseReturns(List<PurchaseReturnModel> purchaseReturnModels);

	List<PurchaseReturnModel> findAllPurchaseReturns();

	PurchaseReturnModel savePurchaseReturns(PurchaseReturnModel purchaseReturnModel);

	PurchaseReturnModel updatePurchaseReturns(PurchaseReturnModel purchaseReturnModel);

	PurchaseReturnModel findPurchaseRetrunById(Integer purchaseRetrunId);

	void deletePurchaseReturnById(Integer purchaseRetrunId);

	void deletePurchaseReturnByIds(Integer[] purchaseRetrunIds);

}
