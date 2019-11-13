package com.ihealthpharm.sales.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.sales.helper.SalesHelper;
import com.ihealthpharm.sales.helper.SalesReturnHelper;
import com.ihealthpharm.sales.model.SalesReturnModel;
import com.ihealthpharm.sales.service.SalesReturnService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class SalesReturnController {
	
	@Autowired
	private SalesReturnService salesReturnService;
	
	@Autowired
	private SalesReturnHelper salesReturnHelper;
	
	@Autowired
	SalesHelper salesHelper;
	
	@PostMapping("/save/salesreturn")
	public ResponseEntity<BaseDto<SalesReturnModel>> saveSalesReturn(@Valid @RequestBody  SalesReturnModel salesReturnModel ){
		log.info("Request Object to insert is :"+salesReturnModel);
		SalesReturnModel salesReturn=salesReturnService.saveSalesReturnDate(salesReturnModel);
		return new BaseDto<>(salesReturn,salesReturnHelper.getSaveSalesReturnMessage(),OK).respond();
	}
		
	
	@GetMapping("/getsalesreturn")
	public ResponseEntity<BaseDto<List<SalesReturnModel>>> getAllSalesReturnsData(){
		List<SalesReturnModel> response=salesReturnService.findAllSalesReturns();
		return new BaseDto<>(response,salesReturnHelper.getRetrieveSalesReturnMessage(),OK).respond();
	}
	
	
   // url = get/salesitemsbybillid  to get salesitems based on billId
  // ul = /get/allsales  to get list of bills data 
	
	
}
