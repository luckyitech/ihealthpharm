package com.ihealthpharm.stock.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.stock.dto.StockAdjustmentDTO;
import com.ihealthpharm.stock.model.StockAdjustmentModel;

public interface StockAdjustmentService {

	StockAdjustmentModel saveStockAdjustment(@Valid StockAdjustmentModel stockAdjustmentModel);
	
	List<StockAdjustmentModel> saveStockAdjustementsData(List<StockAdjustmentModel> stockAdjustmentModels);
	
	List<StockAdjustmentDTO> findBasedOnItemCode(String searchTerm,String batch,String expiry,int pharmacyId);
	
     List<StockAdjustmentDTO> findBasedOnItemNameSearch(String searchTerm);
	
	List<StockAdjustmentDTO> findBasedOnItemDesc(String searchTerm);
	
	List<StockAdjustmentDTO> findBasedOnItemGenericName(String searchTerm);
	
	
}
