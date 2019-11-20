package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.stock.model.StockModel;

public interface StockService {

	StockModel saveStock(StockModel stockModel);
	
	List<StockModel> saveStock(List<StockModel> stockModel);
	
	StockModel updateStock(StockModel stockModel);

	List<StockModel> updateStocks(List<StockModel> stockModels);

	List<StockModel> findAllStocks();

	StockModel findStockById(Integer stockId);

	void deleteStockById(Integer stockId);

	void deleteStockByTds(Integer[] stockIds);

	List<ItemsModel> findAllStockItems();
	
	List<String> getBatchNumbersByItemId(ItemsModel itemId);
	
	StockModel getStockByItemAndBatchNumber(ItemsModel itemId, String batchNo);
	
	List<StockModel> findByItem(ItemsModel itemId);

	List<StockModel> findByItemAndPharmacy(List<ItemsModel> itemId, PharmacyModel pharmacy);
	
	List<StockModel> findByItemAndPharmacy(String itemName, Integer pharmacyid);
	
	List<StockModel> findByItemName(ItemsModel itemName);
	
	StockModel getStockByItemIdandInvoiceId(Integer itemId, Integer invoiceId);

	StockModel findStocksByBillId(Integer itemId);
	
}
