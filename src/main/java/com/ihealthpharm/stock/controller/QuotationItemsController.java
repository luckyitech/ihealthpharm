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
import com.ihealthpharm.stock.helper.QuotationItemsHelper;
import com.ihealthpharm.stock.model.QuotationItemsModel;
import com.ihealthpharm.stock.service.QuotationItemsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of QuotationItems
 */
@RestController
@CrossOrigin
@Slf4j
public class QuotationItemsController {
	
	@Autowired
	private QuotationItemsHelper quotationItemsHelper;
	
	@Autowired
	private QuotationItemsService quotationItemsService;

	/**
	 * @author Gunasekhar 
	 * Service is to save the QuotationItems
	 */
	@PostMapping("/save/quotationitems")
	public ResponseEntity<BaseDto<QuotationItemsModel>> saveQuotationItems(@Valid @RequestBody QuotationItemsModel quotationItemsModel) {
		log.info("Request Object insert is: "+ quotationItemsModel.toString());
		QuotationItemsModel model = quotationItemsService.saveQuotationItems(quotationItemsModel);
		return new BaseDto<>(model, quotationItemsHelper.getSaveQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the QuotationItems
	 */
	@PutMapping("/update/quotationitems")
	public ResponseEntity<BaseDto<QuotationItemsModel>> updateQuotationItems(@Valid @RequestBody QuotationItemsModel quotationItemsModel) {
		log.info("Request Object for update is: ",quotationItemsModel.toString());
		QuotationItemsModel model = quotationItemsService.updateQuotationItems(quotationItemsModel);
		return new BaseDto<>(model, quotationItemsHelper.getUpdateQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the QuotationItems by status
	 */
	@PutMapping("/update/approvedquotationitem")
	public ResponseEntity<BaseDto<QuotationItemsModel>> updateApprovedQuotationItems(@Valid @RequestBody QuotationItemsModel quotationItemsModel) {
		log.info("Request Object for update is: ",quotationItemsModel.toString());
		QuotationItemsModel model = quotationItemsService.updateQuotationItems(quotationItemsModel, "APPROVED");
		return new BaseDto<>(model, quotationItemsHelper.getUpdateQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the QuotationItems by status
	 */
	@PutMapping("/update/rejectedquotationitem")
	public ResponseEntity<BaseDto<QuotationItemsModel>> updateRejectedQuotationItems(@Valid @RequestBody QuotationItemsModel quotationItemsModel) {
		log.info("Request Object for update is: ",quotationItemsModel.toString());
		QuotationItemsModel model = quotationItemsService.updateQuotationItems(quotationItemsModel, "REJECTED");
		return new BaseDto<>(model, quotationItemsHelper.getUpdateQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the QuotationItemss
	 */
	@PutMapping("/update/multiplequotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> updateQuotationItemss(@Valid @RequestBody List<QuotationItemsModel> quotationItemsModels) {
		log.info("Request Object for update is: "+ quotationItemsModels.toString());
		List<QuotationItemsModel> models = quotationItemsService.updateQuotationItems(quotationItemsModels);
		return new BaseDto<>(models, quotationItemsHelper.getUpdateQuotationItemMessage(), OK).respond();
	}
		
	/**
	 * @author Gunasekhar 
	 * Service is to delete the QuotationItems
	 */
	@DeleteMapping("/delete/quotationitems")
	public ResponseEntity<BaseDto<Object>> deleteQuotationItems(@RequestParam Integer quotationItemsId) {
		log.info("Request Object for delete is: ", quotationItemsId);
		quotationItemsService.deleteQuotationItemsById(quotationItemsId);
		return new BaseDto<>(quotationItemsHelper.getDeleteQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete all the QuotationItems
	 */
	@DeleteMapping("/delete/multiplequotationitems")
	public ResponseEntity<BaseDto<Object>> deleteQuotationItems(@RequestParam Integer[] quotationItemsIds) {
		log.info("Request Object for delete is: "+ quotationItemsIds.toString());
		quotationItemsService.deleteQuotationItemsByTds(quotationItemsIds);
		return new BaseDto<>(quotationItemsHelper.getDeleteQuotationItemMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the QuotationItemss
	 * 
	 */
	@GetMapping("/getallquotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> getAllQuotationItemss() {
		List<QuotationItemsModel> quotationItemsModels = quotationItemsService.findAllQuotationItems();
		return new BaseDto<>(quotationItemsModels, quotationItemsHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the QuotationItems
	 */
	@GetMapping("/getquotationitemsbyid")
	public ResponseEntity<BaseDto<QuotationItemsModel>> getQuotationItemsById(@RequestParam Integer quotationItemsId) {
		QuotationItemsModel result = quotationItemsService.findQuotationItemsById(quotationItemsId);
		return new BaseDto<>(result, quotationItemsHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the pending QuotationItems
	 */
/*	@GetMapping("/getpendingquotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> getPendingQuotationItems() {
		List<QuotationItemsModel> result = quotationItemsService.getQuotaionItemsByStatus("PENDING");
		return new BaseDto<>(result, quotationItemsHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}*/
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the approved QuotationItems
	 */
	/*@GetMapping("/getapprovedquotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> getApprovedQuotationItems() {
		List<QuotationItemsModel> result = quotationItemsService.getQuotaionItemsByStatus("APPROVED");
		return new BaseDto<>(result, quotationItemsHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}*/
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the rejected QuotationItems
	 */
/*	@GetMapping("/getrejectedquotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> getRejectedQuotationItems() {
		List<QuotationItemsModel> result = quotationItemsService.getQuotaionItemsByStatus("REJECTED");
		return new BaseDto<>(result, quotationItemsHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}*/
	
	/**
	 * @author Gunasekhar 
	 * Service is to search the pending QuotationItems
	 */
	@GetMapping("/searchpendingquotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> searchPendingQuotationItems(@RequestParam String name) {
		List<QuotationItemsModel> result = quotationItemsService.getQuotaionItemsByStatus("PENDING", name);
		return new BaseDto<>(result, quotationItemsHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to search the approved QuotationItems
	 */
	@GetMapping("/searchapprovedquotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> searchApprovedQuotationItems(@RequestParam String name) {
		List<QuotationItemsModel> result = quotationItemsService.getQuotaionItemsByStatus("APPROVED", name);
		return new BaseDto<>(result, quotationItemsHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to search the rejected QuotationItems
	 */
	@GetMapping("/searchrejectedquotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> searchRejectedQuotationItems(@RequestParam String name) {
		List<QuotationItemsModel> result = quotationItemsService.getQuotaionItemsByStatus("REJECTED", name);
		return new BaseDto<>(result, quotationItemsHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to reject the QuotationItem
	 */
	@GetMapping("/rejectquotationitemsbyid")
	public ResponseEntity<BaseDto<Object>> rejectQuotationItemsById(@RequestParam Integer quotationItemsId) {
		quotationItemsService.rejectQuotationItemsById(quotationItemsId);
		return new BaseDto<>(quotationItemsHelper.getDeleteQuotationItemMessage(), OK).respond();
	}
	

	/**
	 * @author Tarun 
	 * Service is to delete the QuotationItems
	 */
	@GetMapping("/deleteAll/quotationitemsbyid")
	public ResponseEntity<BaseDto<Object>> getQuotationItemsByQuotationId(@RequestParam Integer quotationId) {
	  	quotationItemsService.getAllQuotationsItemsByQuotationId(quotationId);
		return new BaseDto<>(quotationItemsHelper.getDeleteQuotationItemMessage(), OK).respond();
	}
	
}
