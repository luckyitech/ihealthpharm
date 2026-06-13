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
 * Controller is used to perform the crud operations of Payment Type
 */
@RestController
@CrossOrigin
@Slf4j
public class PaymentTypeController {
	
	@Autowired
	private PaymentTypeHelper paymentTypeHelper;
	
	@Autowired
	private PaymentTypeService paymentTypeService;

	/**
	 * @author Gunasekhar 
	 * Service is to save the payment type
	 */
	@PostMapping("/save/paymenttype")
	public ResponseEntity<BaseDto<PaymentTypeModel>> savePaymentType(@Valid @RequestBody PaymentTypeModel paymentTypeModel) {
		log.info("Request Object insert is: "+ paymentTypeModel.toString());
		PaymentTypeModel model = paymentTypeService.savePaymentType(paymentTypeModel);
		return new BaseDto<>(model, paymentTypeHelper.getSavePaymentTypeMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the payment type
	 */
	@PutMapping("/update/paymenttype")
	public ResponseEntity<BaseDto<PaymentTypeModel>> updatePaymentType(@Valid @RequestBody PaymentTypeModel paymentTypeModel) {
		log.info("Request Object for update is: ",paymentTypeModel.toString());
		PaymentTypeModel model = paymentTypeService.updatePaymentType(paymentTypeModel);
		return new BaseDto<>(model, paymentTypeHelper.getUpdatePaymentTypeMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the payment types
	 */
	@PutMapping("/update/multiplepaymenttypes")
	public ResponseEntity<BaseDto<List<PaymentTypeModel>>> updatePaymentTypes(@Valid @RequestBody List<PaymentTypeModel> paymentTypeModels) {
		log.info("Request Object for update is: "+ paymentTypeModels.toString());
		List<PaymentTypeModel> models = paymentTypeService.updatePaymentTypes(paymentTypeModels);
		return new BaseDto<>(models, paymentTypeHelper.getUpdatePaymentTypeMessage(), OK).respond();
	}
		
	/**
	 * @author Gunasekhar 
	 * Service is to delete the payment type
	 */
	@DeleteMapping("/delete/paymenttype")
	public ResponseEntity<BaseDto<Object>> deletePaymentType(@RequestParam Integer paymentTypeId) {
		log.info("Request Object for delete is: ", paymentTypeId);
		paymentTypeService.deletePaymentTypeById(paymentTypeId);
		return new BaseDto<>(paymentTypeHelper.getDeletePaymentTypeMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete all the payment types
	 */
	@DeleteMapping("/delete/multiplepaymenttypes")
	public ResponseEntity<BaseDto<Object>> deletePaymentTypes(@RequestParam Integer[] paymentTypeIds) {
		log.info("Request Object for delete is: "+ paymentTypeIds.toString());
		paymentTypeService.deletePaymentTypeByTds(paymentTypeIds);
		return new BaseDto<>(paymentTypeHelper.getDeletePaymentTypeMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the payment types
	 * 
	 */
	@GetMapping("/getallpaymenttypes")
	public ResponseEntity<BaseDto<List<PaymentTypeModel>>> getAllPaymentTypes() {
		List<PaymentTypeModel> paymentTypeModels = paymentTypeService.findAllPaymentTypes();
		return new BaseDto<>(paymentTypeModels, paymentTypeHelper.getRetrievePaymentTypeMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the payment type
	 */
	@GetMapping("/getpaymenttypebyid")
	public ResponseEntity<BaseDto<PaymentTypeModel>> getPaymentTypeById(@RequestParam Integer paymentTypeId) {
		PaymentTypeModel result = paymentTypeService.findPaymentTypeById(paymentTypeId);
		return new BaseDto<>(result, paymentTypeHelper.getRetrievePaymentTypeMessage(), OK).respond();
	}
	
	
}
