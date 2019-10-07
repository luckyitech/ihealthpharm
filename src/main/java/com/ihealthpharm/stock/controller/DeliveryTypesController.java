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
import com.ihealthpharm.masters.model.DeliveryTypesModel;
import com.ihealthpharm.stock.helper.DeliveryTypesHelper;
import com.ihealthpharm.stock.service.DeliveryTypesService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of Delivery Types
 */
@RestController
@CrossOrigin
@Slf4j
public class DeliveryTypesController {
	
	@Autowired
	private DeliveryTypesHelper deliveryTypesHelper;
	
	@Autowired
	private DeliveryTypesService deliveryTypesService;

	/**
	 * @author Gunasekhar 
	 * Service is to save the delivery type
	 */
	@PostMapping("/save/deliverytype")
	public ResponseEntity<BaseDto<DeliveryTypesModel>> saveDeliveryTypes(@Valid @RequestBody DeliveryTypesModel deliveryTypesModel) {
		log.info("Request Object insert is: "+ deliveryTypesModel.toString());
		DeliveryTypesModel model = deliveryTypesService.saveDeliveryTypes(deliveryTypesModel);
		return new BaseDto<>(model, deliveryTypesHelper.getSaveDeliveryTypesMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the delivery type
	 */
	@PutMapping("/update/deliverytype")
	public ResponseEntity<BaseDto<DeliveryTypesModel>> updateDeliveryTypes(@Valid @RequestBody DeliveryTypesModel deliveryTypesModel) {
		log.info("Request Object for update is: ",deliveryTypesModel.toString());
		DeliveryTypesModel model = deliveryTypesService.updateDeliveryTypes(deliveryTypesModel);
		return new BaseDto<>(model, deliveryTypesHelper.getUpdateDeliveryTypesMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the delivery types
	 */
	@PutMapping("/update/multipledeliverytypes")
	public ResponseEntity<BaseDto<List<DeliveryTypesModel>>> updateDeliveryTypess(@Valid @RequestBody List<DeliveryTypesModel> deliveryTypesModels) {
		log.info("Request Object for update is: "+ deliveryTypesModels.toString());
		List<DeliveryTypesModel> models = deliveryTypesService.updateDeliveryTypes(deliveryTypesModels);
		return new BaseDto<>(models, deliveryTypesHelper.getUpdateDeliveryTypesMessage(), OK).respond();
	}
		
	/**
	 * @author Gunasekhar 
	 * Service is to delete the delivery type
	 */
	@DeleteMapping("/delete/deliverytype")
	public ResponseEntity<BaseDto<Object>> deleteDeliveryTypes(@RequestParam Integer deliveryTypesId) {
		log.info("Request Object for delete is: ", deliveryTypesId);
		deliveryTypesService.deleteDeliveryTypesById(deliveryTypesId);
		return new BaseDto<>(deliveryTypesHelper.getDeleteDeliveryTypesMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete all the delivery types
	 */
	@DeleteMapping("/delete/multipledeliverytypes")
	public ResponseEntity<BaseDto<Object>> deleteDeliveryTypess(@RequestParam Integer[] deliveryTypesIds) {
		log.info("Request Object for delete is: "+ deliveryTypesIds.toString());
		deliveryTypesService.deleteDeliveryTypesByTds(deliveryTypesIds);
		return new BaseDto<>(deliveryTypesHelper.getDeleteDeliveryTypesMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the delivery types
	 * 
	 */
	@GetMapping("/getalldeliverytypes")
	public ResponseEntity<BaseDto<List<DeliveryTypesModel>>> getAllDeliveryTypess() {
		List<DeliveryTypesModel> deliveryTypesModels = deliveryTypesService.findAllDeliveryTypes();
		return new BaseDto<>(deliveryTypesModels, deliveryTypesHelper.getRetrieveDeliveryTypesMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the delivery type
	 */
	@GetMapping("/getdeliverytypebyid")
	public ResponseEntity<BaseDto<DeliveryTypesModel>> getDeliveryTypesById(@RequestParam Integer deliveryTypesId) {
		DeliveryTypesModel result = deliveryTypesService.findDeliveryTypesById(deliveryTypesId);
		return new BaseDto<>(result, deliveryTypesHelper.getRetrieveDeliveryTypesMessage(), OK).respond();
	}
	
	
}
