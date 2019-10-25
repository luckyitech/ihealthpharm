package com.ihealthpharm.stock.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.model.ItemsModel;
import  com.ihealthpharm.stock.service.*;
import com.ihealthpharm.stock.helper.*;
import com.ihealthpharm.stock.model.*;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of Stock
 */
@RestController
@CrossOrigin
@Slf4j
public class StockController {
	
	@Autowired
	private StockHelper stockHelper;
	
	@Autowired
	private StockService stockService;

	/**
	 * @author Gunasekhar 
	 * Service is to save the stock
	 */
	@PostMapping("/save/stock")
	public ResponseEntity<BaseDto<StockModel>> saveStock(@Valid @RequestBody StockModel stockModel) {
		log.info("Request Object insert is: "+ stockModel.toString());
		StockModel model = stockService.saveStock(stockModel);
		return new BaseDto<>(model, stockHelper.getSaveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the stock
	 */
	@PutMapping("/update/stock")
	public ResponseEntity<BaseDto<StockModel>> updateStock(@Valid @RequestBody StockModel stockModel) {
		log.info("Request Object for update is: ",stockModel.toString());
		StockModel model = stockService.updateStock(stockModel);
		return new BaseDto<>(model, stockHelper.getUpdateStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the stocks
	 */
	@PutMapping("/update/multiplestocks")
	public ResponseEntity<BaseDto<List<StockModel>>> updateStocks(@Valid @RequestBody List<StockModel> stockModels) {
		log.info("Request Object for update is: "+ stockModels.toString());
		List<StockModel> models = stockService.updateStocks(stockModels);
		return new BaseDto<>(models, stockHelper.getUpdateStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete the stock
	 */
	@DeleteMapping("/delete/stock")
	public ResponseEntity<BaseDto<Object>> deleteStock(@RequestParam Integer stockId) {
		log.info("Request Object for delete is: ", stockId);
		stockService.deleteStockById(stockId);
		return new BaseDto<>(stockHelper.getDeleteStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete the stocks
	 */
	@DeleteMapping("/delete/multiplestocks")
	public ResponseEntity<BaseDto<Object>> deleteStocks(@RequestParam Integer[] stockIds) {
		log.info("Request Object for delete is: "+ stockIds.toString());
		stockService.deleteStockByTds(stockIds);
		return new BaseDto<>(stockHelper.getDeleteStockMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the stocks
	 * 
	 */
	@GetMapping("/getallstocks")
	public ResponseEntity<BaseDto<List<StockModel>>> getAllStocks() {
		List<StockModel> stockModels = stockService.findAllStocks();
		return new BaseDto<>(stockModels, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the stock
	 */
	@GetMapping("/getstockbyid")
	public ResponseEntity<BaseDto<StockModel>> getStockById(@RequestParam Integer stockId) {
		StockModel result = stockService.findStockById(stockId);
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	@GetMapping("/getallstockitems")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllStockItems() {
		List<ItemsModel> result = stockService.findAllStockItems();
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	
}
