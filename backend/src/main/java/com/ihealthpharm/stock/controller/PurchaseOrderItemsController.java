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
import com.ihealthpharm.stock.helper.PurchaseOrderItemsHelper;
import com.ihealthpharm.stock.model.PurchaseOrderItemsModel;
import com.ihealthpharm.stock.service.PurchaseOrderItemsService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class PurchaseOrderItemsController {

	@Autowired
	PurchaseOrderItemsService purchaseOrderItemsService;

	@Autowired
	PurchaseOrderItemsHelper purchaseOrderItemsHelper;

	@PostMapping("/save/purchaseOrderItem")
	public ResponseEntity<BaseDto<PurchaseOrderItemsModel>> insertPurchaseOrderItemsData(@Valid @RequestBody PurchaseOrderItemsModel purchaseOrderItemsModel) {
		PurchaseOrderItemsModel purchaseOrderItemsModelRes = purchaseOrderItemsService.savePurchaseOrderItemsData(purchaseOrderItemsModel);
		return new BaseDto<>(purchaseOrderItemsModelRes, purchaseOrderItemsHelper.getSavePurchaseOrderItemsMessage(), OK).respond();
	}

	@PutMapping("/update/purchaseOrderItem")
	public ResponseEntity<BaseDto<PurchaseOrderItemsModel>> updatePurchaseOrderItemsData(@Valid @RequestBody PurchaseOrderItemsModel purchaseOrderItemsModel) {
		log.info("Request Object for update is: ", purchaseOrderItemsModel);
		PurchaseOrderItemsModel purchaseOrderItemsModelRes = purchaseOrderItemsService.updatePurchaseOrderItemsData(purchaseOrderItemsModel);
		return new BaseDto<>(purchaseOrderItemsModelRes, purchaseOrderItemsHelper.getUpdatePurchaseOrderItemsMessage(), OK).respond();
	}

	@DeleteMapping("/delete/purchaseOrderItem")
	public ResponseEntity<BaseDto<Object>> deletePurchaseOrderItemsData(@RequestParam Integer purchaseOrderItemsId) {
		log.info("Request Object for delete is: ", purchaseOrderItemsId);
		purchaseOrderItemsService.deletePurchaseOrderItemsById(purchaseOrderItemsId);
		return new BaseDto<>(purchaseOrderItemsHelper.getDeletePurchaseOrderItemsMessage(), OK).respond();
	}

	@GetMapping("/getpurchaseOrderItemsdata")
	public ResponseEntity<BaseDto<List<PurchaseOrderItemsModel>>> getPurchaseOrderItemsData() {
		List<PurchaseOrderItemsModel> result = purchaseOrderItemsService.findAllPurchaseOrderItems();
		return new BaseDto<>(result, purchaseOrderItemsHelper.getRetrievePurchaseOrderItemsMessage(), OK).respond();
	}

	@GetMapping("/getpurchaseOrderItemsdatabyid")
	public ResponseEntity<BaseDto<PurchaseOrderItemsModel>> getPurchaseOrderItemsDataById(@RequestParam Integer purchaseOrderItemsId) {
		PurchaseOrderItemsModel result = purchaseOrderItemsService.findPurchaseOrderItemsById(purchaseOrderItemsId);
		return new BaseDto<>(result, purchaseOrderItemsHelper.getRetrievePurchaseOrderItemsMessage(), OK).respond();
	}

	@DeleteMapping("/delete/purchaseOrderItems")
	public ResponseEntity<BaseDto<Object>> deletePurchaseOrderItemsData(@RequestParam Integer[] purchaseOrderIds) {

		log.info("Request Object for delete is: " + purchaseOrderIds[0]);
		purchaseOrderItemsService.deletePurchaseOrderItemssById(purchaseOrderIds);
		return new BaseDto<>(purchaseOrderItemsHelper.getDeletePurchaseOrderItemsMessage(), OK).respond();
	}

	@PutMapping("/update/purchaseOrderItems")
	public ResponseEntity<BaseDto<List<PurchaseOrderItemsModel>>> updatePurchaseOrderItemssData(@Valid @RequestBody List<PurchaseOrderItemsModel> purchaseOrderItemsModel) {
		log.info("Request Object for update is: " , purchaseOrderItemsModel);
		List<PurchaseOrderItemsModel> PurchaseOrderItemsModelRes = purchaseOrderItemsService.updatePurchaseOrderItemssData(purchaseOrderItemsModel);
		return new BaseDto<>(PurchaseOrderItemsModelRes, purchaseOrderItemsHelper.getUpdatePurchaseOrderItemsMessage(), OK).respond();
	}
	@GetMapping("/getmanufacturerbysearchpbpd")
	public ResponseEntity<BaseDto<List<String>>> getManufacturerByPurchaseOrderItems(@RequestParam String searchTerm){
		List<String> results=purchaseOrderItemsService.findManufacturerByPurchaseOrderItem(searchTerm);
		return new BaseDto<>(results,purchaseOrderItemsHelper.getRetrievePurchaseOrderItemsMessage(),OK).respond();
	}
	@GetMapping("/getallmanufacturerpbpd")
	public ResponseEntity<BaseDto<List<String>>> getAllManufacturerByPurchaseOrderItems(){
		List<String> results=purchaseOrderItemsService.findAllManufacturerByPurchaseOrderItem();
		return new BaseDto<>(results,purchaseOrderItemsHelper.getRetrievePurchaseOrderItemsMessage(),OK).respond();
	}
	@GetMapping("/getsuppliersbysearchpbpd")
	public ResponseEntity<BaseDto<List<String>>> getSuppliersByPurchaseOrderItems(@RequestParam String searchTerm){
		List<String> results=purchaseOrderItemsService.findSuppliersByPurchaseOrderItem(searchTerm);
		return new BaseDto<>(results,purchaseOrderItemsHelper.getRetrievePurchaseOrderItemsMessage(),OK).respond();
	}
	@GetMapping("/getallsupplierspbpd")
	public ResponseEntity<BaseDto<List<String>>> getAllSuppliersByPurchaseOrderItems(){
		List<String> results=purchaseOrderItemsService.findAllManufacturerByPurchaseOrderItem();
		return new BaseDto<>(results,purchaseOrderItemsHelper.getRetrievePurchaseOrderItemsMessage(),OK).respond();
	}
}
