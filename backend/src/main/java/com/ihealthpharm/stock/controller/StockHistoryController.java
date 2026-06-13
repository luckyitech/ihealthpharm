package com.ihealthpharm.stock.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.stock.helper.InvoiceItemHelper;
import com.ihealthpharm.stock.helper.StockHelper;
import com.ihealthpharm.stock.service.InvoiceItemService;
import com.ihealthpharm.stock.service.StockHistoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class StockHistoryController {
	
	@Autowired
	private StockHelper stockHelper;
	
	@Autowired
	private StockHistoryService stockHistoryService;
	
	//Slow Moving Product Details
	@GetMapping("/getitemcodesbysearchsmpd")
	public ResponseEntity<BaseDto<List<String>>> getItemCodesByStockHisSMPD(@RequestParam String searchTerm){
		List<String> results=stockHistoryService.findItemCodesInStockSMPD(searchTerm);
		return new BaseDto<>(results,stockHelper.getRetrieveStockMessage(),OK).respond();
	}
	
	@GetMapping("/getallitemcodessmpd")
	public ResponseEntity<BaseDto<List<String>>> getAllItemCodesByStockSMPD(){
		List<String> results=stockHistoryService.findAllItemCodesInStockSMPD();
		return new BaseDto<>(results,stockHelper.getRetrieveStockMessage(),OK).respond();
	}
	
	@GetMapping("/getitemnamesbysearchsmpd")
	public ResponseEntity<BaseDto<List<String>>> getItemNamesByStockHisSMPD(@RequestParam String searchTerm){
		List<String> results=stockHistoryService.findItemNamesInStockSMPD(searchTerm);
		return new BaseDto<>(results,stockHelper.getRetrieveStockMessage(),OK).respond();
	}
	
	@GetMapping("/getallitemnamessmpd")
	public ResponseEntity<BaseDto<List<String>>> getAllItemNamesByStockSMPD(){
		List<String> results=stockHistoryService.findAllItemNamesInStockSMPD();
		return new BaseDto<>(results,stockHelper.getRetrieveStockMessage(),OK).respond();
	}

}
