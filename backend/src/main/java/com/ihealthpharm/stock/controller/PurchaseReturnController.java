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
import com.ihealthpharm.stock.helper.PurchaseReturnHelper;
import com.ihealthpharm.stock.model.PurchaseReturnModel;
import com.ihealthpharm.stock.service.PurchaseReturnService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class PurchaseReturnController {
	
	@Autowired
	private PurchaseReturnService purchaseReturnService;
	
	@Autowired 
	PurchaseReturnHelper purchaseReturnHelper;
	
	@PostMapping("/save/purchaseReturn")
	public ResponseEntity<BaseDto<PurchaseReturnModel>> insertPurchaseReturnData(@Valid @RequestBody PurchaseReturnModel purchaseReturnModel) {
		PurchaseReturnModel purchaseRetrunModelRes = purchaseReturnService.savePurchaseReturns(purchaseReturnModel);
		return new BaseDto<>(purchaseRetrunModelRes, purchaseReturnHelper.getSavePurchaseReturnMessage(), OK).respond();
	}
	
	@PutMapping("/update/purchaseReturn")
	public ResponseEntity<BaseDto<PurchaseReturnModel>> updatePurchaseReturnData(@Valid @RequestBody PurchaseReturnModel purchaseReturnModel) {
		log.info("Request Object for update is: ", purchaseReturnModel);
		PurchaseReturnModel purchaseRetrunModelRes = purchaseReturnService.updatePurchaseReturns(purchaseReturnModel);
		return new BaseDto<>(purchaseRetrunModelRes, purchaseReturnHelper.getUpdatePurchaseReturnMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/purchaseReturn")
	public ResponseEntity<BaseDto<Object>> deletePurchaseReturnData(@RequestParam Integer purchaseReturnId) {
		log.info("Request Object for delete is: ", purchaseReturnId);
		purchaseReturnService.deletePurchaseReturnById(purchaseReturnId);
		return new BaseDto<>(purchaseReturnHelper.getDeletePurchaseReturnMessage(), OK).respond();
	}
	
	@GetMapping("/getPurchaseReturnData")
	public ResponseEntity<BaseDto<List<PurchaseReturnModel>>> getPurchaseReturnData() {
		List<PurchaseReturnModel> result = purchaseReturnService.findAllPurchaseReturns();
		return new BaseDto<>(result, purchaseReturnHelper.getRetrievePurchaseReturnMessage(), OK).respond();
	}
	
	@GetMapping("/getPurchaseReturnDataById")
	public ResponseEntity<BaseDto<PurchaseReturnModel>> getPurchaseOrderDataById(@RequestParam Integer purchaseReturnId) {
		PurchaseReturnModel result = purchaseReturnService.findPurchaseRetrunById(purchaseReturnId);
		return new BaseDto<>(result, purchaseReturnHelper.getRetrievePurchaseReturnMessage(), OK).respond();
	}

	@DeleteMapping("/delete/purchaseReturns")
	public ResponseEntity<BaseDto<Object>> deletePurchaseOrderData(@RequestParam Integer[] purchaseOrderIds) {
		log.info("Request Object for delete is: " + purchaseOrderIds[0]);
		purchaseReturnService.deletePurchaseReturnByIds(purchaseOrderIds);
		return new BaseDto<>(purchaseReturnHelper.getDeletePurchaseReturnMessage(), OK).respond();
	}

	@PutMapping("/update/purchaseReturns")
	public ResponseEntity<BaseDto<List<PurchaseReturnModel>>> updatePurchaseOrdersData(@Valid @RequestBody List<PurchaseReturnModel> PurchaseReturnModel) {
		log.info("Request Object for update is: " , PurchaseReturnModel);
		List<PurchaseReturnModel> PurchaseReturnModelRes = purchaseReturnService.updatePurchaseReturns(PurchaseReturnModel);
		return new BaseDto<>(PurchaseReturnModelRes, purchaseReturnHelper.getUpdatePurchaseReturnMessage(), OK).respond();
	}

	@GetMapping("/getPurchaseReturnDataByInvoiceNo")
	public ResponseEntity<BaseDto<List<String>>> getPurchaseReturnDataByInvoiceNo(@RequestParam String invoiceNo){
		List<String> purchaseRetrunModelRes=purchaseReturnService.getPurchaseReturnDataByInvoiceNo(invoiceNo);
		return new BaseDto<>(purchaseRetrunModelRes, purchaseReturnHelper.getRetrievePurchaseReturnMessage(), OK).respond();
	}
}
