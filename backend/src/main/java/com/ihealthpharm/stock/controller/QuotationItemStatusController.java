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
import com.ihealthpharm.stock.helper.QuotationItemStatusHelper;
import com.ihealthpharm.stock.model.QuotationItemStatusModel;
import com.ihealthpharm.stock.service.QuotationItemStatusService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of QuotationItemStatus
 */
@RestController
@CrossOrigin
@Slf4j
public class QuotationItemStatusController {
	
	@Autowired
	private QuotationItemStatusHelper quotationItemStatusHelper;
	
	@Autowired
	private QuotationItemStatusService quotationItemStatusService;

	/**
	 * @author Gunasekhar 
	 * Service is to save the QuotationItemStatus
	 */
	@PostMapping("/save/quotationitemstatus")
	public ResponseEntity<BaseDto<QuotationItemStatusModel>> saveQuotationItemStatus(@Valid @RequestBody QuotationItemStatusModel quotationItemStatusModel) {
		log.info("Request Object insert is: "+ quotationItemStatusModel.toString());
		QuotationItemStatusModel model = quotationItemStatusService.saveQuotationItemStatus(quotationItemStatusModel);
		return new BaseDto<>(model, quotationItemStatusHelper.getSaveQuotationItemStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the QuotationItemStatus
	 */
	@PutMapping("/update/quotationitemstatus")
	public ResponseEntity<BaseDto<QuotationItemStatusModel>> updateQuotationItemStatus(@Valid @RequestBody QuotationItemStatusModel quotationItemStatusModel) {
		log.info("Request Object for update is: ",quotationItemStatusModel.toString());
		QuotationItemStatusModel model = quotationItemStatusService.updateQuotationItemStatus(quotationItemStatusModel);
		return new BaseDto<>(model, quotationItemStatusHelper.getUpdateQuotationItemStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the QuotationItemStatuss
	 */
	@PutMapping("/update/multiplequotationitemstatus")
	public ResponseEntity<BaseDto<List<QuotationItemStatusModel>>> updateQuotationItemStatuss(@Valid @RequestBody List<QuotationItemStatusModel> quotationItemStatusModels) {
		log.info("Request Object for update is: "+ quotationItemStatusModels.toString());
		List<QuotationItemStatusModel> models = quotationItemStatusService.updateQuotationItemStatus(quotationItemStatusModels);
		return new BaseDto<>(models, quotationItemStatusHelper.getUpdateQuotationItemStatusMessage(), OK).respond();
	}
		
	/**
	 * @author Gunasekhar 
	 * Service is to delete the QuotationItemStatus
	 */
	@DeleteMapping("/delete/quotationitemstatus")
	public ResponseEntity<BaseDto<Object>> deleteQuotationItemStatus(@RequestParam Integer quotationItemStatusId) {
		log.info("Request Object for delete is: ", quotationItemStatusId);
		quotationItemStatusService.deleteQuotationItemStatusById(quotationItemStatusId);
		return new BaseDto<>(quotationItemStatusHelper.getDeleteQuotationItemStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete all the QuotationItemStatuss
	 */
	@DeleteMapping("/delete/multiplequotationitemstatus")
	public ResponseEntity<BaseDto<Object>> deleteQuotationItemStatuss(@RequestParam Integer[] quotationItemStatusIds) {
		log.info("Request Object for delete is: "+ quotationItemStatusIds.toString());
		quotationItemStatusService.deleteQuotationItemStatusByTds(quotationItemStatusIds);
		return new BaseDto<>(quotationItemStatusHelper.getDeleteQuotationItemStatusMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the QuotationItemStatuss
	 * 
	 */
	@GetMapping("/getallquotationitemstatus")
	public ResponseEntity<BaseDto<List<QuotationItemStatusModel>>> getAllQuotationItemStatuss() {
		List<QuotationItemStatusModel> quotationItemStatusModels = quotationItemStatusService.findAllQuotationItemStatus();
		return new BaseDto<>(quotationItemStatusModels, quotationItemStatusHelper.getRetrieveQuotationItemStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the QuotationItemStatus
	 */
	@GetMapping("/getquotationitemstatusbyid")
	public ResponseEntity<BaseDto<QuotationItemStatusModel>> getQuotationItemStatusById(@RequestParam Integer quotationItemStatusId) {
		QuotationItemStatusModel result = quotationItemStatusService.findQuotationItemStatusById(quotationItemStatusId);
		return new BaseDto<>(result, quotationItemStatusHelper.getRetrieveQuotationItemStatusMessage(), OK).respond();
	}
	
	
}
