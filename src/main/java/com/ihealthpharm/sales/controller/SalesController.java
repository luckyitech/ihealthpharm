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
import com.ihealthpharm.sales.helper.SalesHelper;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.sales.service.SalesService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class SalesController {

	@Autowired
	SalesService salesService;
	
	@Autowired
	SalesHelper salesHelper;
	
	@PostMapping("/save/sales")
	public ResponseEntity<BaseDto<SalesModel>> insertSalesData(@Valid @RequestBody SalesModel salesModel) {
		log.info("Request Object insert is: "+ salesModel.toString());
		SalesModel salesModelRes = salesService.saveSalesData(salesModel);
		return new BaseDto<>(salesModelRes,salesHelper.getSaveSalesMessage(),OK).respond();
	}

	@PutMapping("/update/sales")
	public ResponseEntity<BaseDto<SalesModel>> updatSalesData(@Valid @RequestBody SalesModel salesModel) {
		log.info("Request Object Update is: "+ salesModel.toString());
		SalesModel salesModelRes = salesService.updateSalesData(salesModel);
		return new BaseDto<>(salesModelRes,salesHelper.getUpdateSalesMessage(),OK).respond();
	} 
	
	@DeleteMapping("/delete/sales")
	public ResponseEntity<BaseDto<Object>> deleteSalesData(@RequestParam Integer billId) {
		log.info("Request Id delete is: "+ billId);
		salesService.deleteSalesData(billId);
		return new BaseDto<>(salesHelper.getUpdateSalesMessage(),OK).respond();
	}
	
	@GetMapping("/get/salesbyid")
	public ResponseEntity<BaseDto<SalesModel>> getSalesDataById(@RequestParam Integer billId) {
		log.info("Request Id get is: "+ billId);
		SalesModel salesModelRes = salesService.findSalesData(billId);
		return new BaseDto<>(salesModelRes,salesHelper.getUpdateSalesMessage(),OK).respond();
	}
	
	@GetMapping("/get/allsales")
	public ResponseEntity<BaseDto<List<SalesModel>>> getAllSalesData() {
		List<SalesModel> salesModelRes = salesService.findAllSalesData();
		return new BaseDto<>(salesModelRes,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@PostMapping("/get/bysalessearchkeys")
	public ResponseEntity<BaseDto<List<SalesModel>>> getByStatus(@RequestParam("status") String status,
			@RequestParam("code")  String code,@RequestParam("codeValue") String codeValue,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
		log.info("Status=: "+status);
		log.info("Code=: "+code);
		log.info("code Value=: "+codeValue);
		log.info("start=:"+startDate);
		log.info("end=:"+endDate);
		
		List<SalesModel> salesModelRes = salesService.findByCriteria(status,code,codeValue,startDate,endDate);
		
		return new BaseDto<>(salesModelRes,salesHelper.getUpdateSalesMessage(),OK).respond();
	}
	

	
	//to get billcode based on searchterm 
	@GetMapping("/getbillcode/basedonsearch")
	public ResponseEntity<BaseDto<SalesModel>> getSalesRecordBySearch(@RequestParam String searchTerm){
		SalesModel salesModel=salesService.getSaleByBillCode(searchTerm);
		return new BaseDto<>(salesModel,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}

	@GetMapping("/get/limitedsales")
	public ResponseEntity<BaseDto<List<SalesModel>>> getFirt100SalesDataByBillDate() {
		List<SalesModel> salesModelRes = salesService.findLimitedSalesData();
		return new BaseDto<>(salesModelRes,salesHelper.getUpdateSalesMessage(),OK).respond();
	}
}
