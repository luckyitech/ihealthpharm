package com.ihealthpharm.stock.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.stock.dto.StockAdjustmentDTO;
import com.ihealthpharm.stock.helper.StockHelper;
import com.ihealthpharm.stock.model.StockAdjustmentModel;
import com.ihealthpharm.stock.model.StockModel;
import com.ihealthpharm.stock.service.StockAdjustmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class StockAdjustementController {

	@Autowired
	private StockAdjustmentService stockAdjustmentService;

	@Autowired
	private StockHelper stockHelper;
	
	@PostMapping("/save/stockadjustment")
	public ResponseEntity<BaseDto<StockAdjustmentModel>> insertItemData(@Valid @RequestBody StockAdjustmentModel stockAdjustmentModel) {
		log.info("Request Object insert is: "+ stockAdjustmentModel);
		StockAdjustmentModel stockAdjustModelRes = stockAdjustmentService.saveStockAdjustment(stockAdjustmentModel);
		return new BaseDto<>(stockAdjustModelRes,stockHelper.getUpdateStockAdjustmentMessage(),OK).respond();
	}

	@GetMapping("/get/stockstotal/matchedstockadjustid")
	public ResponseEntity<BaseDto<Integer>> getStockAdjustmentTotalData(@RequestParam String batch,@RequestParam String  expiry,@RequestParam("pharmacyId") Integer pharmacyId ){
		Integer result = stockAdjustmentService.getStockQuantity(batch,expiry,pharmacyId);
		return new BaseDto<>(result, stockHelper.getRetrieveStockAdjustmentMessage(), OK).respond();
	}

	@GetMapping("/get/stocks/matchedIds")
	public ResponseEntity<BaseDto<List<StockModel>>> getAllStocksMatchedWithStockAdjustId(@RequestParam String batch,@RequestParam String  expiry,@RequestParam("pharmacyId") Integer pharmacyId){
		List<StockModel> result=stockAdjustmentService.getAllStockMatched(batch,expiry,pharmacyId);
		return new BaseDto<>(result,stockHelper.getRetrieveStockMessage(),OK).respond();
	}
	
	@PutMapping("/update/stocks/basedonStockAdjust")
	public BaseDto<List<StockModel>> updateStocksBasedOnStockAdjust(@Valid @RequestBody List<StockModel> stockModels){
		log.info("Request Object for update is :"+stockModels);
		stockAdjustmentService.updateStocksData(stockModels);
		return new BaseDto<>(stockModels,stockHelper.getUpdateStockAdjustmentMessage(),OK);
	}
	
	
	//based on itemCode
	@PostMapping("/get/stockitems/basedonitemcode")
	public ResponseEntity<BaseDto<List<StockAdjustmentDTO>>> getStockItemDataBasedOnItemCode(@RequestParam String searchTerm,@RequestParam String batch,@RequestParam String  expiry,@RequestParam("pharmacyId") Integer pharmacyId ){
		List<StockAdjustmentDTO> result=stockAdjustmentService.findBasedOnItemCode(searchTerm,batch,expiry,pharmacyId);
		return new BaseDto<>(result,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}

	//based on itemName
	@PostMapping("/get/stockitems/basedonitemname")
	public ResponseEntity<BaseDto<List<StockAdjustmentDTO>>> getStockItemDataBasedOnItemName(@RequestParam String searchTerm,@RequestParam String batch,@RequestParam String  expiry,@RequestParam("pharmacyId") Integer pharmacyId ){
		List<StockAdjustmentDTO> response=stockAdjustmentService.findBasedOnItemNameSearch(searchTerm,batch,expiry,pharmacyId);
		return new BaseDto<>(response,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}

	//based on itemDesc
	@PostMapping("/get/stockitems/basedonitemdesc")
	public ResponseEntity<BaseDto<List<StockAdjustmentDTO>>> getStockItemDataSearchByItemDesc(@RequestParam String searchTerm,@RequestParam String batch,@RequestParam String  expiry,@RequestParam("pharmacyId") Integer pharmacyId ){
		
		List<StockAdjustmentDTO> response=stockAdjustmentService.findBasedOnItemDesc(searchTerm,batch,expiry,pharmacyId);
		return new BaseDto<>(response,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}

	//based on itemGeneric
	@PostMapping("/get/stockitems/basedonitemgeneric")
	public ResponseEntity<BaseDto<List<StockAdjustmentDTO>>> getStockItemDataBasedOnItemGenericName(@RequestParam String searchTerm,@RequestParam String batch,@RequestParam String  expiry,@RequestParam("pharmacyId") Integer pharmacyId ){
		List<StockAdjustmentDTO> resp=stockAdjustmentService.findBasedOnItemGenericName(searchTerm,batch,expiry,pharmacyId);
		return new BaseDto<>(resp,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}

}
