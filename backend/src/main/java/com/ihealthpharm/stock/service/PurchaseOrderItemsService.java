package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.stock.model.PurchaseOrderItemsModel;

public interface PurchaseOrderItemsService {

	PurchaseOrderItemsModel savePurchaseOrderItemsData(PurchaseOrderItemsModel purchaseOrderItems);

	PurchaseOrderItemsModel updatePurchaseOrderItemsData(PurchaseOrderItemsModel purchaseOrderItems);

	List<PurchaseOrderItemsModel> updatePurchaseOrderItemssData(List<PurchaseOrderItemsModel> purchaseOrderItems);

	List<PurchaseOrderItemsModel> findAllPurchaseOrderItems();

	PurchaseOrderItemsModel findPurchaseOrderItemsById(Integer purchaseOrderItemsId);

	void deletePurchaseOrderItemsById(Integer purchaseOrderItemsIds);

	void deletePurchaseOrderItemssById(Integer[] purchaseOrderItemsIds);
	
	List<String> findManufacturerByPurchaseOrderItem(String searchTerm);
	
	List<String> findAllManufacturerByPurchaseOrderItem();
	
	List<String> findSuppliersByPurchaseOrderItem(String searchTerm);
	
	List<String> findAllSuppliersByPurchaseOrderItem();

	List<PurchaseOrderItemsModel> findPurchaseOrderItemsByPoId(Integer purchaseOrderId);

}
