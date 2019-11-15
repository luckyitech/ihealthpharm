package com.ihealthpharm.sales.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.sales.model.SalesReturnItemsModel;

public interface SalesReturnItemService {
	
	
	SalesReturnItemsModel saveSalesReturnItemData(@Valid SalesReturnItemsModel salesReturnItemsModel);

	SalesReturnItemsModel updateSalesReturnsItemData(@Valid SalesReturnItemsModel salesReturnItemsModel);

	void deleteSalesReturnItemsById(Integer salesReturnItemId);

	List<SalesReturnItemsModel> findAllSalesReturnItems();

	SalesReturnItemsModel findSalesReturnItemsById(Integer salesReturnItemId);

	void deletePurchaseReturnItemsById(Integer[] salesReturnItemIds);

}
