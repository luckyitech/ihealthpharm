package com.ihealthpharm.stock.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.stock.model.PurchaseReturnItemModel;

public interface PurchaseReturnItemService {

	PurchaseReturnItemModel savePurchaseReturnItemData(@Valid PurchaseReturnItemModel purchaseReturnItemModel);
	
	List<PurchaseReturnItemModel> savePurchaseReturnItems(@Valid List<PurchaseReturnItemModel> purchaseReturnItems);

	PurchaseReturnItemModel updatePurchaseReturnItemData(@Valid PurchaseReturnItemModel purchaseReturnItemModel);

	void deletePurchaseReturnItemsById(Integer purchaseOrderItemsId);

	List<PurchaseReturnItemModel> findAllPurchaseReturnItems();

	PurchaseReturnItemModel findPurchaseReturnItemsById(Integer purchaseReturnItemsId);

	void deletePurchaseReturnItemsById(Integer[] purchaseReturnIds);

	List<PurchaseReturnItemModel> updatePurchaseReturnItemsData(@Valid List<PurchaseReturnItemModel> purchaseReturnItemModel);

	//Purchase Returns
	List<String> findGRNNoByPR(String searchTerm);
	
	List<String> findAllGRNNoByPR();
	
	List<String> findSupplierBySearchPR(String searchTerm);
	
	List<String> findAllSuppliersByPR();
	
	List<String> findInvoiceNoBySearchPR(String searchTerm);
	
	List<String> findAllInvoiceNoByPR();

	Integer getReturnQtyByItemId(Integer itemId, Integer invoiceId);
}
