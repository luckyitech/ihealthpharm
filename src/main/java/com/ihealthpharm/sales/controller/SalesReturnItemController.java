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
import com.ihealthpharm.sales.helper.SalesReturnItemHelper;
import com.ihealthpharm.sales.model.SalesReturnItemsModel;
import com.ihealthpharm.sales.service.SalesReturnItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class SalesReturnItemController {
	
	@Autowired
	SalesReturnItemHelper salesReturnItemHelper;
	
	@Autowired
	SalesReturnItemService salesReturnItemService;
	
	@PostMapping("/save/salesreturnitems")
	public ResponseEntity<BaseDto<List<SalesReturnItemsModel>>> saveSalesReturnItems(@Valid @RequestBody  List<SalesReturnItemsModel> salesReturnItemsModel ){
		log.info("Request Object to insert is :"+salesReturnItemsModel.toString());
		List<SalesReturnItemsModel> salesReturn=salesReturnItemService.saveSalesReturnItemData(salesReturnItemsModel);
		return new BaseDto<>(salesReturn,salesReturnItemHelper.getSaveSalesReturnItemMessage(),OK).respond();
	}
	
	@PutMapping("/update/salesreturnitems")
	public ResponseEntity<BaseDto<SalesReturnItemsModel>> updateSalesReturnItemsData(@Valid @RequestBody SalesReturnItemsModel salesReturnItemsModel) {
		log.info("Request Object for update is: ", salesReturnItemsModel);
		SalesReturnItemsModel salesRetrunModelRes = salesReturnItemService.updateSalesReturnsItemData(salesReturnItemsModel);
		return new BaseDto<>(salesRetrunModelRes, salesReturnItemHelper.getUpdateSalesReturnItemMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/salesreturnitems")
	public ResponseEntity<BaseDto<Object>> deleteSalesReturnItemsData(@RequestParam Integer salesReturnItemId) {
		log.info("Request Object for delete is: ", salesReturnItemId);
		salesReturnItemService.deleteSalesReturnItemsById(salesReturnItemId);
		return new BaseDto<>(salesReturnItemHelper.getDeleteSalesReturnItemMessage(), OK).respond();
	}
	
	@GetMapping("/getsalesreturnitems")
	public ResponseEntity<BaseDto<List<SalesReturnItemsModel>>> getAllSalesReturnItemsData(){
		List<SalesReturnItemsModel> response=salesReturnItemService.findAllSalesReturnItems();
		return new BaseDto<>(response,salesReturnItemHelper.getRetrieveSalesReturnItemMessage(),OK).respond();
	}
	
	@GetMapping("/getsalesreturnnobysearchsr")
	public ResponseEntity<BaseDto<List<String>>> getsalesReturnNoBySearchSBPS(@RequestParam String searchTerm){
		List<String> results=salesReturnItemService.findSalesReturnNoInSalesReturn(searchTerm);
		return new BaseDto<>(results,salesReturnItemHelper.getRetrieveSalesReturnItemMessage(),OK).respond();
	}
	@GetMapping("/getallsalesreturnnosr")
	public ResponseEntity<BaseDto<List<String>>> getallsalesReturnNoBySearchSBPS(){
		List<String> results=salesReturnItemService.findAllSalesReturnNumbersInSalesReturn();
		return new BaseDto<>(results,salesReturnItemHelper.getRetrieveSalesReturnItemMessage(),OK).respond();
	}

}
