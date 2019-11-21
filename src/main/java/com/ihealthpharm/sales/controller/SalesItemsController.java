package com.ihealthpharm.sales.controller;

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
import com.ihealthpharm.sales.helper.SalesItemsHelper;
import com.ihealthpharm.sales.model.SalesItemsModel;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.sales.service.SalesItemsService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class SalesItemsController {

	@Autowired
	SalesItemsService salesItemsService;
	
	@Autowired
	SalesItemsHelper salesItemsHelper;
	
	@PostMapping("/save/salesItems")
	public ResponseEntity<BaseDto<List<SalesItemsModel>>> insertSalesItemsData(@Valid @RequestBody List<SalesItemsModel> salesItemsModel) {
		log.info("Request Object insert is: "+ salesItemsModel.toString());
		List<SalesItemsModel> salesItemsModelRes = salesItemsService.saveSalesItemsData(salesItemsModel);
		return new BaseDto<>(salesItemsModelRes,salesItemsHelper.getSaveSalesItemsMessage(),OK).respond();
	}

	@PutMapping("/update/salesItems")
	public ResponseEntity<BaseDto<SalesItemsModel>> updatSalesItemsData(@Valid @RequestBody SalesItemsModel salesItemsModel) {
		log.info("Request Object Update is: "+ salesItemsModel.toString());
		SalesItemsModel salesItemsModelRes = salesItemsService.updateSalesItemsData(salesItemsModel);
		return new BaseDto<>(salesItemsModelRes,salesItemsHelper.getUpdateSalesItemsMessage(),OK).respond();
	} 
	
	@DeleteMapping("/delete/salesItems")
	public ResponseEntity<BaseDto<Object>> deleteSalesItemsData(@RequestParam Integer billId) {
		log.info("Request Id delete is: "+ billId);
		salesItemsService.deleteSalesItemsData(billId);
		return new BaseDto<>(salesItemsHelper.getUpdateSalesItemsMessage(),OK).respond();
	}
	
	@GetMapping("/get/salesItemsbyid")
	public ResponseEntity<BaseDto<SalesItemsModel>> getSalesItemsDataById(@RequestParam Integer billId) {
		log.info("Request Id get is: "+ billId);
		SalesItemsModel salesItemsModelRes = salesItemsService.findSalesItemsData(billId);
		return new BaseDto<>(salesItemsModelRes,salesItemsHelper.getUpdateSalesItemsMessage(),OK).respond();
	}
	
	@GetMapping("/get/allsalesItems")
	public ResponseEntity<BaseDto<List<SalesItemsModel>>> getAllSalesItemsData() {
		List<SalesItemsModel> salesItemsModelRes = salesItemsService.findAllSalesItemsData();
		return new BaseDto<>(salesItemsModelRes,salesItemsHelper.getUpdateSalesItemsMessage(),OK).respond();
	}
	
	@PostMapping("/get/salesitemsbybillid")
	public ResponseEntity<BaseDto<List<SalesItemsModel>>> getSalesItemsByStockId(@RequestBody SalesModel sales) {
		System.out.println(sales);
		List<SalesItemsModel> salesItemsModelRes = salesItemsService.getByBillId(sales);
		return new BaseDto<>(salesItemsModelRes,salesItemsHelper.getUpdateSalesItemsMessage(),OK).respond();
	}
}
