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
import com.ihealthpharm.stock.helper.PurchaseOrderHelper;
import com.ihealthpharm.stock.model.PurchaseOrderModel;
import com.ihealthpharm.stock.service.PurchaseOrderService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService purchaseorderService;

	@Autowired
	PurchaseOrderHelper purchaseorderHelper;

	@PostMapping("/save/purchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> insertPurchaseOrderData(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {

		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.savePurchaseOrderData(purchaseorderModel);
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getSavePurchaseOrderMessage(), OK).respond();
	}

	@PutMapping("/update/purchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> updatePurchaseOrderData(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
		log.info("Request Object for update is: ", purchaseorderModel);
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.updatePurchaseOrderData(purchaseorderModel);
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getUpdatePurchaseOrderMessage(), OK).respond();
	}

	@DeleteMapping("/delete/purchaseorder")
	public ResponseEntity<BaseDto<Object>> deletePurchaseOrderData(@RequestParam int purchaseorderId) {
		log.info("Request Object for delete is: ", purchaseorderId);
		purchaseorderService.deletePurchaseOrderById(purchaseorderId);
		return new BaseDto<>(purchaseorderHelper.getDeletePurchaseOrderMessage(), OK).respond();
	}

	@GetMapping("/getpurchaseorderdata")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getPurchaseOrderData() {
		List<PurchaseOrderModel> result = purchaseorderService.findAllPurchaseOrders();
		return new BaseDto<>(result, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	@GetMapping("/getpurchaseorderdatabyid")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> getPurchaseOrderDataById(@RequestParam int purchaseorderId) {
		PurchaseOrderModel result = purchaseorderService.findPurchaseOrderById(purchaseorderId);
		return new BaseDto<>(result, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	@DeleteMapping("/delete/purchaseorders")
	public ResponseEntity<BaseDto<Object>> deletePurchaseOrderData(@RequestParam int[] purchaseOrderIds) {

		log.info("Request Object for delete is: " + purchaseOrderIds[0]);
		purchaseorderService.deletePurchaseOrdersById(purchaseOrderIds);
		return new BaseDto<>(purchaseorderHelper.getDeletePurchaseOrderMessage(), OK).respond();
	}

	@PutMapping("/update/purchaseorders")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> updatePurchaseOrdersData(@Valid @RequestBody List<PurchaseOrderModel> purchaseorderModel) {
		log.info("Request Object for update is: " , purchaseorderModel);
		List<PurchaseOrderModel> PurchaseOrderModelRes = purchaseorderService.updatePurchaseOrdersData(purchaseorderModel);
		return new BaseDto<>(PurchaseOrderModelRes, purchaseorderHelper.getUpdatePurchaseOrderMessage(), OK).respond();
	}
}
