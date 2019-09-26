package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.dto.QuotationDTO;
import com.ihealthpharm.masters.helper.QuotationHelper;
import com.ihealthpharm.masters.model.QuotationItemsModel;
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
	
	
	@PostMapping("/save/quotation-quotationitem")
	public ResponseEntity<BaseDto<QuotationModel>> saveQuotation(@Valid @RequestBody QuotationDTO quotationModelDTO) {
		
		ResponseEntity<BaseDto<@Valid QuotationModel>> baseDto = null;			
		QuotationModel quotationModel = quotationService.saveQuotationData(quotationModelDTO);						
		if(Objects.nonNull(quotationModel)){
			baseDto = new BaseDto<>(quotationModel, quotationHelper.getSaveQuotationMessage(), OK).respond();
		}else{
			baseDto = new BaseDto<>(quotationModel, quotationHelper.getErrorSavingQuotation(), INTERNAL_SERVER_ERROR).respond(); 
		}
		return baseDto; 	
	}	
	
	@PostMapping("/delete/quotation")
	public ResponseEntity<BaseDto<Object>> deleteQuotation(@RequestParam int quotationModelID){
		quotationService.deleteQuotationById(quotationModelID);				
		return new BaseDto<>(quotationHelper.getDeleteQuotationMessage(), OK).respond();
	}
	
	@PostMapping("/delete/quotations")
	public ResponseEntity<BaseDto<Object>> deleteQuotations(@RequestParam int[] quotationIds){		
		quotationService.deleteQuotationsByIds(quotationIds);				
		return new BaseDto<>(quotationHelper.getDeleteQuotationMessage(), OK).respond();
	}
			
	@PostMapping("/update/quotationstatus")
	public ResponseEntity<BaseDto<Object>> updateQuotationStatus(@Valid @RequestBody QuotationModel quotationModel){
		quotationModel = quotationService.updateQuotationData(quotationModel);		
		return new BaseDto<>(quotationHelper.getUpdateQuotationMessage(), OK).respond();
	}
	
	@PostMapping("/find/quotationbyqno")
	public ResponseEntity<BaseDto<List<QuotationModel>>> findQuotation(@RequestParam String qNo){
		List<QuotationModel> lqm = quotationService.findQuotationsByQuotationNo(qNo);
		if(Objects.isNull(lqm) || lqm.isEmpty()){
			return new BaseDto<>(lqm,quotationHelper.getNotFoundQuotationMessage(), OK).respond();
		}		
		return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationMessage(), OK).respond();	
	}
		
	@PostMapping("/find/allquotations")
	public ResponseEntity<BaseDto<List<QuotationModel>>> findAllQuotations(){
		List<QuotationModel> lqm = quotationService.findAllQuotations();
		if(Objects.isNull(lqm) || lqm.isEmpty()){
			return new BaseDto<>(lqm,quotationHelper.getNotFoundQuotationMessage(), OK).respond();
		}		
		return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationMessage(), OK).respond();	
	}	
	
	@PostMapping("/find/allquotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> findAllQuotationItems(){
		List<QuotationItemsModel> lqm = quotationService.findAllQuotationItems();
		if(Objects.isNull(lqm) || lqm.isEmpty()){
			return new BaseDto<>(lqm,quotationHelper.getNotFoundQuotationMessage(), OK).respond();
		}		
		return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationItem(), OK).respond();	
	}
		
	@PostMapping("/find/quotationitembyqid")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> findQuotationItemByQuotationModel(@Valid @RequestParam QuotationModel qid){
		List<QuotationItemsModel> lqm = quotationService.findQuotationItemByQuotationModel(qid);
		if(Objects.isNull(lqm) || lqm.isEmpty()){
			return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationItemNotFound(), OK).respond();
		}
		return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationItem(), OK).respond();	
	}
	
	@PostMapping("/find/findactivequotations")
	public ResponseEntity<BaseDto<List<QuotationModel>>> findActiveQuotations(){
		List<QuotationModel> lqm = quotationService.findActiveQuotations();
		if(Objects.isNull(lqm) || lqm.isEmpty()){
			return new BaseDto<>(lqm,quotationHelper.getNotFoundQuotationMessage(), OK).respond();
		}
		return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationMessage(), OK).respond();	
	}
	
	@PostMapping("/find/findactivequotationitems")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> findActiveQuotationItems(){
		List<QuotationItemsModel> lqm = quotationService.findActiveQuotationItems();
		if(Objects.isNull(lqm) || lqm.isEmpty()){
			return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationItemNotFound(), OK).respond();
		}
		return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationItem(), OK).respond();	
	}
	
	@PostMapping("/find/sortquotationbycreateddate")
	public ResponseEntity<BaseDto<List<QuotationModel>>> findQuotationSortedByCreationDate(){
		List<QuotationModel> lqm = quotationService.findQuotationSortedByCreationDate();
		if(Objects.isNull(lqm) || lqm.isEmpty()){
			return new BaseDto<>(lqm,quotationHelper.getNotFoundQuotationMessage(), OK).respond();
		}
		return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationMessage(), OK).respond();	
	}
	
	@PostMapping("/find/sortquotationitemsbycreateddate")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> findQuotationItemsSortedByCreationDate(){
		List<QuotationItemsModel> lqm = quotationService.findQuotationItemsSortedByCreationDate();
		if(Objects.isNull(lqm) || lqm.isEmpty()){
			return new BaseDto<>(lqm,quotationHelper.getNotFoundQuotationMessage(), OK).respond();
		}
		return new BaseDto<>(lqm,quotationHelper.getRetrieveQuotationMessage(), OK).respond();	
	}
}