package com.ihealthpharm.sales.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.Date;
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
		List<SalesItemsModel> salesItemsModelRes = salesItemsService.getByBillId(sales);
		return new BaseDto<>(salesItemsModelRes,salesItemsHelper.getUpdateSalesItemsMessage(),OK).respond();
	}
	
	//Sales By Product Details
			@GetMapping("/getcustomersbysearchsbpd")
			public ResponseEntity<BaseDto<List<String>>> getCustomersBySearchSalesItemsSBPD(@RequestParam String searchTerm){
				List<String> results=salesItemsService.findCustomersBySalesItemsSBPD(searchTerm);
				return new BaseDto<>(results,salesItemsHelper.getRetrieveSalesItemsItemsMessage(),OK).respond();
			}
			
			@GetMapping("/getallcustomerssbpd")
			public ResponseEntity<BaseDto<List<String>>> getAllCustomersSalesItemsSBPD(){
				List<String> results=salesItemsService.findAllCustomersBySalesItemsSBPD();
				return new BaseDto<>(results,salesItemsHelper.getRetrieveSalesItemsItemsMessage(),OK).respond();
			}

//SBPS
	@GetMapping("/getitemNamebysearchsbps")
	public ResponseEntity<BaseDto<List<String>>> getitemNameBySearchSBPS(@RequestParam String searchTerm){
		List<String> results=salesItemsService.finditemNameInSalesSBPS(searchTerm);
		return new BaseDto<>(results,salesItemsHelper.updateSalesItemsMessage,OK).respond();
	}
	
	@GetMapping("/getnamebysearchsbps")
	public ResponseEntity<BaseDto<List<String>>> getnameBySearchSBPS(@RequestParam String searchTerm){
		List<String> results=salesItemsService.findnameInSalesSBPS(searchTerm);
		return new BaseDto<>(results,salesItemsHelper.updateSalesItemsMessage,OK).respond();
	}
	
	@GetMapping("/getallitemNamebysearchsbps")
	public ResponseEntity<BaseDto<List<String>>> getAllitemNameBySearchSBPS(){
		List<String> results=salesItemsService.findAllitemNameInSalesSBPS();
		return new BaseDto<>(results,salesItemsHelper.updateSalesItemsMessage,OK).respond();
	}
	
	@GetMapping("/getAllnamebysearchsbps")
	public ResponseEntity<BaseDto<List<String>>> getAllnameBySearchSBPS(){
		List<String> results=salesItemsService.findAllnameInSalesSBPS();
		return new BaseDto<>(results,salesItemsHelper.updateSalesItemsMessage,OK).respond();
	}
				
			
}
