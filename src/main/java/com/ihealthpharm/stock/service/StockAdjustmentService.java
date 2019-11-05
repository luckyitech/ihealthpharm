package com.ihealthpharm.stock.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.stock.dto.StockAdjustmentDTO;
import com.ihealthpharm.stock.model.StockAdjustmentModel;

public interface StockAdjustmentService {

	StockAdjustmentModel saveStockAdjustment(@Valid StockAdjustmentModel stockAdjustmentModel);
	
	List<StockAdjustmentDTO> findBasedOnItemCode(String searchTerm);
	
     List<StockAdjustmentDTO> findBasedOnItemNameSearch(String searchTerm);
	
	List<StockAdjustmentDTO> findBasedOnItemDesc(String searchTerm);
	
	List<StockAdjustmentDTO> findBasedOnItemGenericName(String searchTerm);
	
	
}
