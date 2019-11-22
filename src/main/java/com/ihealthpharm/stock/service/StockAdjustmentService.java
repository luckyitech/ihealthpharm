package com.ihealthpharm.stock.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.stock.dto.StockAdjustmentDTO;
import com.ihealthpharm.stock.model.StockAdjustmentModel;
import com.ihealthpharm.stock.model.StockModel;

public interface StockAdjustmentService {

	StockAdjustmentModel saveStockAdjustment(@Valid StockAdjustmentModel stockAdjustmentModel);

	List<StockAdjustmentModel> saveStockAdjustementsData(List<StockAdjustmentModel> stockAdjustmentModels);

	List<StockAdjustmentDTO> findBasedOnItemCode(String searchTerm,String batch,String expiry,Integer pharmacyId);

	List<StockAdjustmentDTO> findBasedOnItemNameSearch(String searchTerm,String batch,String expiry,Integer pharmacyId);

	List<StockAdjustmentDTO> findBasedOnItemDesc(String searchTerm,String batch,String expiry,Integer pharmacyId);

	List<StockAdjustmentDTO> findBasedOnItemGenericName(String searchTerm,String batch,String expiry,Integer pharmacyId);

	Integer	getStockQuantity(String batch,String expiry,Integer pharmacyId);	

	List<StockModel> getAllStockMatched(String batch,String expiry,Integer pharmacyId);

	void updateStocksData(@Valid List<StockModel> stockModels);
}
