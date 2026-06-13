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

import  com.ihealthpharm.stock.service.*;
import com.ihealthpharm.stock.helper.*;
import com.ihealthpharm.stock.model.*;
import com.ihealthpharm.commons.BaseDto;


import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of PurchaseOrderStatus
 */
@RestController
@CrossOrigin
@Slf4j
public class PurchaseOrderStatusController {
	
	@Autowired
	private PurchaseOrderStatusHelper purchaseOrderStatusHelper;
	
	@Autowired
	private PurchaseOrderStatusService purchaseOrderStatusService;

	/**
	 * @author Gunasekhar 
	 * Service is to save the PurchaseOrderStatus
	 */
	@PostMapping("/save/purchaseorderstatus")
	public ResponseEntity<BaseDto<PurchaseOrderStatusModel>> savePurchaseOrderStatus(@Valid @RequestBody PurchaseOrderStatusModel purchaseOrderStatusModel) {
		log.info("Request Object insert is: "+ purchaseOrderStatusModel.toString());
		PurchaseOrderStatusModel model = purchaseOrderStatusService.savePurchaseOrderStatus(purchaseOrderStatusModel);
		return new BaseDto<>(model, purchaseOrderStatusHelper.getSavePurchaseOrderStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the PurchaseOrderStatus
	 */
	@PutMapping("/update/purchaseorderstatus")
	public ResponseEntity<BaseDto<PurchaseOrderStatusModel>> updatePurchaseOrderStatus(@Valid @RequestBody PurchaseOrderStatusModel purchaseOrderStatusModel) {
		log.info("Request Object for update is: ",purchaseOrderStatusModel.toString());
		PurchaseOrderStatusModel model = purchaseOrderStatusService.updatePurchaseOrderStatus(purchaseOrderStatusModel);
		return new BaseDto<>(model, purchaseOrderStatusHelper.getUpdatePurchaseOrderStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the PurchaseOrderStatuss
	 */
	@PutMapping("/update/multiplepurchaseorderstatus")
	public ResponseEntity<BaseDto<List<PurchaseOrderStatusModel>>> updatePurchaseOrderStatuss(@Valid @RequestBody List<PurchaseOrderStatusModel> purchaseOrderStatusModels) {
		log.info("Request Object for update is: "+ purchaseOrderStatusModels.toString());
		List<PurchaseOrderStatusModel> models = purchaseOrderStatusService.updatePurchaseOrderStatuss(purchaseOrderStatusModels);
		return new BaseDto<>(models, purchaseOrderStatusHelper.getUpdatePurchaseOrderStatusMessage(), OK).respond();
	}
		
	/**
	 * @author Gunasekhar 
	 * Service is to delete the PurchaseOrderStatus
	 */
	@DeleteMapping("/delete/purchaseorderstatus")
	public ResponseEntity<BaseDto<Object>> deletePurchaseOrderStatus(@RequestParam Integer purchaseOrderStatusId) {
		log.info("Request Object for delete is: ", purchaseOrderStatusId);
		purchaseOrderStatusService.deletePurchaseOrderStatusById(purchaseOrderStatusId);
		return new BaseDto<>(purchaseOrderStatusHelper.getDeletePurchaseOrderStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete all the PurchaseOrderStatus
	 */
	@DeleteMapping("/delete/multiplepurchaseorderstatus")
	public ResponseEntity<BaseDto<Object>> deletePurchaseOrderStatuss(@RequestParam Integer[] purchaseOrderStatusIds) {
		log.info("Request Object for delete is: "+ purchaseOrderStatusIds.toString());
		purchaseOrderStatusService.deletePurchaseOrderStatusByTds(purchaseOrderStatusIds);
		return new BaseDto<>(purchaseOrderStatusHelper.getDeletePurchaseOrderStatusMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the PurchaseOrderStatus
	 * 
	 */
	@GetMapping("/getallpurchaseorderstatus")
	public ResponseEntity<BaseDto<List<PurchaseOrderStatusModel>>> getAllPurchaseOrderStatuss() {
		List<PurchaseOrderStatusModel> purchaseOrderStatusModels = purchaseOrderStatusService.findAllPurchaseOrderStatus();
		return new BaseDto<>(purchaseOrderStatusModels, purchaseOrderStatusHelper.getRetrievePurchaseOrderStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the PurchaseOrderStatus
	 */
	@GetMapping("/getpurchaseorderstatusbyid")
	public ResponseEntity<BaseDto<PurchaseOrderStatusModel>> getPurchaseOrderStatusById(@RequestParam Integer purchaseOrderStatusId) {
		PurchaseOrderStatusModel result = purchaseOrderStatusService.findPurchaseOrderStatusById(purchaseOrderStatusId);
		return new BaseDto<>(result, purchaseOrderStatusHelper.getRetrievePurchaseOrderStatusMessage(), OK).respond();
	}
	
	
}
