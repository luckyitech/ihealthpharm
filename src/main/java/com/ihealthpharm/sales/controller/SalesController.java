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
import com.ihealthpharm.stock.model.StockModel;

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
	
	@GetMapping("/monthly/totalSales")
	public ResponseEntity<BaseDto<List>> getAllMonthlySalesData(){
		List result=salesService.totalSalesByMonthWiseData();
		return new BaseDto<>(result,salesHelper.getRetrieveSalesMessage(),OK).respond();
		}
//scl
	@GetMapping("/getmanufacturerbysearchscl")
	public ResponseEntity<BaseDto<List<String>>> getManufacturersBySales(@RequestParam String searchTerm){
		List<String> results=salesService.findManufacturerBySales(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	@GetMapping("/getallmanufacturerscl")
	public ResponseEntity<BaseDto<List<String>>> getAllManufacturersBySales(){
		List<String> results=salesService.findAllManufacturerBySales();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getprovidersbysearchscl")
	public ResponseEntity<BaseDto<List<String>>> getProvidersBySales(@RequestParam String searchTerm){
		List<String> results=salesService.findProvidersBySales(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallprovidersscl")
	public ResponseEntity<BaseDto<List<String>>> getAllProvidersBySales(){
		List<String> results=salesService.findAllProvidersBySales();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	@GetMapping("/getbilldatesbysearchscl")
	public ResponseEntity<BaseDto<List<String>>> getBillDatesBySales(@RequestParam String searchTerm){
		List<String> results=salesService.findBillDateBySales(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	@GetMapping("/getallbilldatesscl")
	public ResponseEntity<BaseDto<List<String>>> getAllBillDatessclBySales(){
		List<String> results=salesService.findAllBillDtaessBySales();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}

//DBL
	@GetMapping("/getbilldatesbysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getBillDatesBySearchDBL(@RequestParam String searchTerm){
		List<String> results=salesService.findBillDatesBySalesDBL(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getfirstnmbysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getfirst_nmBySearchDBL(@RequestParam String searchTerm){
		List<String> results=salesService.findfirst_nmBySalesDBL(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getnamebysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getnameBySearchDBL(@RequestParam String searchTerm){
		List<String> results=salesService.findnameBySalesDBL(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallbilldatesbysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getAllBillDatesDBL(){
		List<String> results=salesService.findAllBillDatesBySalesDBL();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallfirstnmbysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getAllfirst_nmDBL(){
		List<String> results=salesService.findAllfirst_nmBySalesDBL();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallnamebysearchdbl")
	public ResponseEntity<BaseDto<List<String>>> getAllnameBySearchDBL(){
		List<String> results=salesService.findAllnameBySalesDBL();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
//SRD	
	
	@GetMapping("/getbilldatebysearchsrd")
	public ResponseEntity<BaseDto<List<String>>> getbillDateBySearchSRD(@RequestParam String searchTerm){
		List<String> results=salesService.findbillDateINSalesSRD(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/gettypebysearchsrd")
	public ResponseEntity<BaseDto<List<String>>> gettypeBySearchSRD(@RequestParam String searchTerm){
		List<String> results=salesService.findtypeINSalesSRD(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getallbilldatebysearchsrd")
	public ResponseEntity<BaseDto<List<String>>> getAllbillDateBySearchSRD(){
		List<String> results=salesService.findAllbillDateINSalesSRD();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	@GetMapping("/getalltypebysearchsrd")
	public ResponseEntity<BaseDto<List<String>>> getAlltypeBySearchSRD(){
		List<String> results=salesService.findAlltypeINSalesSRD();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	
	//SRADL
	
	@GetMapping("/getcitynamebysearchsrad")
	public ResponseEntity<BaseDto<List<String>>> getcityNameBySearchSRADL(@RequestParam String searchTerm){
		List<String> results=salesService.findcityNameINSalesSRADL(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	@GetMapping("/getallcitynamesrad")
	public ResponseEntity<BaseDto<List<String>>> getAllcityNameBySearchSRADL(){
		List<String> results=salesService.findAllcityNameINSalesSRADL();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	//SRBB
	@GetMapping("/getbillcodesbysearchsrb")
	public ResponseEntity<BaseDto<List<String>>> getBillCodesBySearchSRBB(@RequestParam String searchTerm){
		List<String> results=salesService.findBillCodeINSalesSRBB(searchTerm);
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
	@GetMapping("/getallbillcodesrb")
	public ResponseEntity<BaseDto<List<String>>> getAllBillCodesSRBB(){
		List<String> results=salesService.findAllBillCodeINSalesSRBB();
		return new BaseDto<>(results,salesHelper.getRetrieveSalesMessage(),OK).respond();
	}
}
