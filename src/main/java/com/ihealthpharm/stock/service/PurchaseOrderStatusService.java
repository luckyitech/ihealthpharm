package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.stock.model.PurchaseOrderStatusModel;

public interface PurchaseOrderStatusService {

	PurchaseOrderStatusModel savePurchaseOrderStatus(PurchaseOrderStatusModel purchaseOrderStatusModel);

	PurchaseOrderStatusModel updatePurchaseOrderStatus(PurchaseOrderStatusModel purchaseOrderStatusModel);

	List<PurchaseOrderStatusModel> updatePurchaseOrderStatuss(List<PurchaseOrderStatusModel> purchaseOrderStatusModels);

	List<PurchaseOrderStatusModel> findAllPurchaseOrderStatus();
	
	PurchaseOrderStatusModel findPurchaseOrderStatusById(Integer purchaseOrderStatusId);

	void deletePurchaseOrderStatusById(Integer purchaseOrderStatusId);

	void deletePurchaseOrderStatusByTds(Integer[] purchaseOrderStatusIds);
	
	PurchaseOrderStatusModel findByStatus(String status);

}
