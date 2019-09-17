package com.ihealthpharm.masters.controller;

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
import com.ihealthpharm.masters.helper.PharmacyHelper;
import com.ihealthpharm.masters.model.PharmacyStockModel;
import com.ihealthpharm.masters.service.PharmacyStockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class PharmacyStockPointController {

	@Autowired
	private PharmacyStockService stockService;
	
	@Autowired
	private PharmacyHelper pharmacyHelper;
	
	
	@PostMapping("/savepharmacy/stock")
	public ResponseEntity<BaseDto<PharmacyStockModel>> addStock(@Valid @RequestBody PharmacyStockModel pharmacyStockModel){
		
		log.info("Request Object for insert is :"+pharmacyStockModel);
		
		PharmacyStockModel pharmacyStockRes=stockService.addStock(pharmacyStockModel); 
		return new BaseDto<>(pharmacyStockRes,pharmacyHelper.getSaveStockResponse(),OK).respond();
	}
	
	@PutMapping("/updatepharmacy/stock")
	public ResponseEntity<BaseDto<PharmacyStockModel>> updateStock(@Valid @RequestBody PharmacyStockModel pharmacyStockModel){
		
		log.info("Request Object for update is :"+pharmacyStockModel);
		PharmacyStockModel pharmacyStockRes=stockService.updateStock(pharmacyStockModel); 
		return new BaseDto<>(pharmacyStockRes,pharmacyHelper.getUpdateStockMessage(),OK).respond();
	}
	
	@PutMapping("/updatemultiple/stocks")
	public ResponseEntity<BaseDto<List<PharmacyStockModel>>> updateStocks(@Valid @RequestBody List<PharmacyStockModel> pharmacyStockModels){
		log.info("Request Object for update is : "+pharmacyStockModels);
		List<PharmacyStockModel> pharmacyStocksRes=stockService.updateStocks(pharmacyStockModels); 
		return new BaseDto<>(pharmacyStocksRes,pharmacyHelper.getUpdateStockMessage(),OK).respond();
	} 
	

	@DeleteMapping("/delete/pharmacystock")
	public ResponseEntity<BaseDto<Object>> deleteStock(@RequestParam int stockId){
		log.info("Request Object for delete is  "+stockId);
		stockService.deleteStock(stockId); 
		return new BaseDto<>(pharmacyHelper.getDeleteStockMessage(),OK).respond();
	}
	

	
	@GetMapping("/getpharmacyStockById")
	public ResponseEntity<BaseDto<PharmacyStockModel>> findByStockId(@RequestParam int stockId){
		log.info("Request Object for stock : "+stockId);
		PharmacyStockModel pharmacyStock = stockService.findStockById(stockId); 
		return new BaseDto<>(pharmacyStock, pharmacyHelper.getRetrieveStockMessage(),OK).respond();
	} 
	
	@GetMapping("/getall/stocks")
	public ResponseEntity<BaseDto<List<PharmacyStockModel>>> findAllStocks(){
		List<PharmacyStockModel> result =stockService.findAllPharmaStocks();
		return new BaseDto<>(result, pharmacyHelper.getRetrieveStockMessage(), OK).respond();
	}

	
	
}
