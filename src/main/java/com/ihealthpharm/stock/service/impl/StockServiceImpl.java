package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.dao.StockRepository;
import com.ihealthpharm.stock.helper.StockHelper;
import com.ihealthpharm.stock.model.StockModel;
import com.ihealthpharm.stock.service.StockService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository  stockRepository;

	@Autowired
	StockHelper stockHelper;

	@Override
	public StockModel saveStock(StockModel stockModel) {
		stockModel = stockRepository.save(stockModel);
		log.info("Stock data with ID: " + stockModel.getStockId() + " saved succesfully");
		return stockModel;
	}

	@Override
	public StockModel updateStock(StockModel stockModel) {
		StockModel model = getStockModel(stockModel.getStockId());
		if (!Objects.nonNull(model)) {
			throw new IHealthPharmException(stockHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
		}
		model = stockRepository.save(stockModel);
		log.info("Stock data with ID : " + model.getStockId() + " updated succesfully");
		return model;
	}

	@Override
	public List<StockModel> updateStocks(List<StockModel> stockModels) {
		for (StockModel stockModel : stockModels) {
			StockModel model = getStockModel(stockModel.getStockId());
			if (!Objects.nonNull(model)) {
				throw new IHealthPharmException(stockHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
			}
			model = stockRepository.save(stockModel);
			log.info("Stock data with Multiple IDs : " + model.getStockId() + " updated succesfully");
		}
		return stockModels;
	}

	@Override
	public List<StockModel> findAllStocks() {
		return stockRepository.findAllByOrderByCreationTimeStampDesc();
	}

	@Override
	public StockModel findStockById(Integer stockId) {
		StockModel stockModel = getStockModel(stockId);
		if (!Objects.nonNull(stockModel)) {
			throw new IHealthPharmException(stockHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Stock data with ID : "+ stockModel.getStockId()+" retrieved succesfully");
		return stockModel;
	}

	@Override
	public void deleteStockById(Integer stockId) {
		StockModel stockModel = getStockModel(stockId);
		if (!Objects.nonNull(stockModel)) {
			throw new IHealthPharmException(stockHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
		}		
		stockRepository.delete(stockModel);
		log.info("Stock data with ID: "+ stockModel.getStockId()+" deleted succesfully");

	}

	@Override
	public void deleteStockByTds(Integer[] stockIds) {
		for (Integer stockId : stockIds) {
			StockModel stockModel = getStockModel(stockId);
			if (!Objects.nonNull(stockModel)) {
				throw new IHealthPharmException(stockHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
			}
			stockRepository.delete(stockModel);
			log.info("Stock data with ID: "+ stockModel.getStockId()+" deleted succesfully");
		}

	}

	private StockModel getStockModel(Integer stockId) {
		StockModel stockModel = null;
		try {
			stockModel = stockRepository.findById(stockId).get();
			return stockModel;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(stockHelper.getNotFoundStockMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<ItemsModel> findAllStockItems() {
		List<ItemsModel> listOfItems = stockRepository.findAllItem();
		log.info(listOfItems.toString());
		log.info("All items in stock retrieved succesfully");
		return listOfItems;
	}

}
