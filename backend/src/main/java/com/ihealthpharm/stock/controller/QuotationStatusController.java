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
import com.ihealthpharm.stock.helper.QuotationStatusHelper;
import com.ihealthpharm.stock.model.QuotationStatusModel;
import com.ihealthpharm.stock.service.QuotationStatusService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of QuotationStatus
 */
@RestController
@CrossOrigin
@Slf4j
public class QuotationStatusController {
	
	@Autowired
	private QuotationStatusHelper quotationStatusHelper;
	
	@Autowired
	private QuotationStatusService quotationStatusService;

	/**
	 * @author Gunasekhar 
	 * Service is to save the QuotationStatus
	 */
	@PostMapping("/save/quotationstatus")
	public ResponseEntity<BaseDto<QuotationStatusModel>> saveQuotationStatus(@Valid @RequestBody QuotationStatusModel quotationStatusModel) {
		log.info("Request Object insert is: "+ quotationStatusModel.toString());
		QuotationStatusModel model = quotationStatusService.saveQuotationStatus(quotationStatusModel);
		return new BaseDto<>(model, quotationStatusHelper.getSaveQuotationStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the QuotationStatus
	 */
	@PutMapping("/update/quotationstatus")
	public ResponseEntity<BaseDto<QuotationStatusModel>> updateQuotationStatus(@Valid @RequestBody QuotationStatusModel quotationStatusModel) {
		log.info("Request Object for update is: ",quotationStatusModel.toString());
		QuotationStatusModel model = quotationStatusService.updateQuotationStatus(quotationStatusModel);
		return new BaseDto<>(model, quotationStatusHelper.getUpdateQuotationStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the QuotationStatuss
	 */
	@PutMapping("/update/multiplequotationstatus")
	public ResponseEntity<BaseDto<List<QuotationStatusModel>>> updateQuotationStatuss(@Valid @RequestBody List<QuotationStatusModel> quotationStatusModels) {
		log.info("Request Object for update is: "+ quotationStatusModels.toString());
		List<QuotationStatusModel> models = quotationStatusService.updateQuotationStatus(quotationStatusModels);
		return new BaseDto<>(models, quotationStatusHelper.getUpdateQuotationStatusMessage(), OK).respond();
	}
		
	/**
	 * @author Gunasekhar 
	 * Service is to delete the QuotationStatus
	 */
	@DeleteMapping("/delete/quotationstatus")
	public ResponseEntity<BaseDto<Object>> deleteQuotationStatus(@RequestParam Integer quotationStatusId) {
		log.info("Request Object for delete is: ", quotationStatusId);
		quotationStatusService.deleteQuotationStatusById(quotationStatusId);
		return new BaseDto<>(quotationStatusHelper.getDeleteQuotationStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete all the QuotationStatuss
	 */
	@DeleteMapping("/delete/multiplequotationstatus")
	public ResponseEntity<BaseDto<Object>> deleteQuotationStatuss(@RequestParam Integer[] quotationStatusIds) {
		log.info("Request Object for delete is: "+ quotationStatusIds.toString());
		quotationStatusService.deleteQuotationStatusByTds(quotationStatusIds);
		return new BaseDto<>(quotationStatusHelper.getDeleteQuotationStatusMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the QuotationStatuss
	 * 
	 */
	@GetMapping("/getallquotationstatus")
	public ResponseEntity<BaseDto<List<QuotationStatusModel>>> getAllQuotationStatuss() {
		List<QuotationStatusModel> quotationStatusModels = quotationStatusService.findAllQuotationStatus();
		return new BaseDto<>(quotationStatusModels, quotationStatusHelper.getRetrieveQuotationStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the QuotationStatus
	 */
	@GetMapping("/getquotationstatusbyid")
	public ResponseEntity<BaseDto<QuotationStatusModel>> getQuotationStatusById(@RequestParam Integer quotationStatusId) {
		QuotationStatusModel result = quotationStatusService.findQuotationStatusById(quotationStatusId);
		return new BaseDto<>(result, quotationStatusHelper.getRetrieveQuotationStatusMessage(), OK).respond();
	}
	
	
}
