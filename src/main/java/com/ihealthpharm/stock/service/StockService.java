package com.ihealthpharm.stock.service;

import java.util.List;

import com.ihealthpharm.stock.model.StockModel;

public interface StockService {

	StockModel saveStock(StockModel stockModel);

	StockModel updateStock(StockModel stockModel);

	List<StockModel> updateStocks(List<StockModel> stockModels);

	List<StockModel> findAllStocks();

	StockModel findStockById(Integer stockId);

	void deleteStockById(Integer stockId);

	void deleteStockByTds(Integer[] stockIds);

}
