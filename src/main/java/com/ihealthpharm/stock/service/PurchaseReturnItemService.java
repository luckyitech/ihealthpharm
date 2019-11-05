package com.ihealthpharm.stock.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.stock.model.PurchaseReturnItemModel;

public interface PurchaseReturnItemService {

	PurchaseReturnItemModel savePurchaseReturnItemData(@Valid PurchaseReturnItemModel purchaseReturnItemModel);

	PurchaseReturnItemModel updatePurchaseReturnItemData(@Valid PurchaseReturnItemModel purchaseReturnItemModel);

	void deletePurchaseReturnItemsById(Integer purchaseOrderItemsId);

	List<PurchaseReturnItemModel> findAllPurchaseReturnItems();

	PurchaseReturnItemModel findPurchaseReturnItemsById(Integer purchaseReturnItemsId);

	void deletePurchaseReturnItemsById(Integer[] purchaseReturnIds);

	List<PurchaseReturnItemModel> updatePurchaseReturnItemsData(@Valid List<PurchaseReturnItemModel> purchaseReturnItemModel);

}
