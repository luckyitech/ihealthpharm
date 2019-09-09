package com.ihealthpharm.masters.controller;

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
import com.ihealthpharm.masters.helper.QuotationHelper;
import com.ihealthpharm.masters.model.QuotationModel;
import com.ihealthpharm.masters.service.QuotationService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class QuotationController {

	@Autowired
	QuotationService quotationService;

	@Autowired
	QuotationHelper quotationHelper;

	@PostMapping("/save/quotation")
	public ResponseEntity<BaseDto<QuotationModel>> insertQuotationData(
			@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object insert is: " + quotationModel.toString());
		QuotationModel quotationModelRes = quotationService.saveQuotationData(quotationModel);
		return new BaseDto<>(quotationModelRes, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}

	@PutMapping("/update/quotation")
	public ResponseEntity<BaseDto<QuotationModel>> updateQuotationData(
			@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object for update is: ", quotationModel);
		QuotationModel quotationModelRes = quotationService.updateQuotationData(quotationModel);
		return new BaseDto<>(quotationModelRes, quotationHelper.getUpdateQuotationMessage(), OK).respond();
	}

	@DeleteMapping("/delete/quotation")
	public ResponseEntity<BaseDto<Object>> deleteQuotationData(@RequestParam int quotationId) {
		log.info("Request Object for delete is: ", quotationId);
		quotationService.deleteQuotationById(quotationId);
		;
		return new BaseDto<>(quotationHelper.getDeleteQuotationMessage(), OK).respond();
	}

	@GetMapping("/getquotationdata")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getQuotationData() {
		List<QuotationModel> result = quotationService.findAllQuotations();
		return new BaseDto<>(result, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}

	@GetMapping("/getquotationdatabyid")
	public ResponseEntity<BaseDto<QuotationModel>> getQuotationDataById(@RequestParam int quotationId) {
		QuotationModel result = quotationService.findQuotationById(quotationId);
		return new BaseDto<>(result, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}

	@DeleteMapping("/delete/quotations")
	public ResponseEntity<BaseDto<Object>> deleteQuotationData(@RequestParam int[] usersIds) {

		log.info("Request Object for delete is: " + usersIds[0]);
		quotationService.deleteQuotationsById(usersIds);
		return new BaseDto<>(quotationHelper.getDeleteQuotationMessage(), OK).respond();
	}

	@PutMapping("/update/quotations")
	public ResponseEntity<BaseDto<List<QuotationModel>>> updateQuotationData(
			@Valid @RequestBody List<QuotationModel> quotationModel) {
		log.info("Request Object for update is: " + quotationModel.toString());
		List<QuotationModel> QuotationModelRes = quotationService.updateQuotationData(quotationModel);
		return new BaseDto<>(QuotationModelRes, quotationHelper.getUpdateQuotationMessage(), OK).respond();
	}
}
