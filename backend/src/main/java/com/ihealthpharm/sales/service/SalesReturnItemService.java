package com.ihealthpharm.sales.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.sales.model.SalesReturnItemsModel;

public interface SalesReturnItemService {
	
	
	List<SalesReturnItemsModel> saveSalesReturnItemData(@Valid List<SalesReturnItemsModel> salesReturnItemsModel);

	SalesReturnItemsModel updateSalesReturnsItemData(@Valid SalesReturnItemsModel salesReturnItemsModel);

	void deleteSalesReturnItemsById(Integer salesReturnItemId);

	List<SalesReturnItemsModel> findAllSalesReturnItems();

	SalesReturnItemsModel findSalesReturnItemsById(Integer salesReturnItemId);

	void deletePurchaseReturnItemsById(Integer[] salesReturnItemIds);
	
	List<String> findSalesReturnNoInSalesReturn(String searchTerm);
	
	List<String> findAllSalesReturnNumbersInSalesReturn();

	Integer getReturnQtyByItemId(Integer itemId,Integer billId);

	List<SalesReturnItemsModel> getReturnItemsById(Integer salesReturnId);

}
