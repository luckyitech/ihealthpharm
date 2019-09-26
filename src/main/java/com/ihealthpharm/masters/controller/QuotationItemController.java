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
import com.ihealthpharm.masters.helper.QuotationItemHelper;
import com.ihealthpharm.masters.model.QuotationItemsModel;
import com.ihealthpharm.masters.service.QuotationItemsService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class QuotationItemController {

	/*@Autowired
	QuotationItemsService quotationItemService;

	@Autowired
	QuotationItemHelper quotationItemHelper;

	@PostMapping("/save/quotationitem")
	public ResponseEntity<BaseDto<QuotationItemsModel>> insertQuotationItemData(
			@Valid @RequestBody QuotationItemsModel quotationItemModel) {
		log.info("Request Object insert is: " + quotationItemModel);
		QuotationItemsModel quotationMItemodelRes = quotationItemService.saveQuotationItemsData(quotationItemModel);
		return new BaseDto<>(quotationMItemodelRes, quotationItemHelper.getSaveQuotationItemMessage(), OK).respond();
	}

	@PutMapping("/update/quotationitems")
	public ResponseEntity<BaseDto<QuotationItemsModel>> updateQuotationItemData(
			@Valid @RequestBody QuotationItemsModel quotationItemModel) {
		log.info("Request Object for update is: ", quotationItemModel);
		QuotationItemsModel quotationItemModelRes = quotationItemService.updateQuotationItemsData(quotationItemModel);
		return new BaseDto<>(quotationItemModelRes, quotationItemHelper.getUpdateQuotationItemMessage(), OK).respond();
	}

	@DeleteMapping("/delete/quotationitemsdata")
	public ResponseEntity<BaseDto<Object>> deleteQuotationItemData(@RequestParam int quotationItemId) {
		log.info("Request Object for delete is: ", quotationItemId);
		quotationItemService.deleteQuotationItemsById(quotationItemId);
		return new BaseDto<>(quotationItemHelper.getDeleteQuotationItemMessage(), OK).respond();
	}

	@GetMapping("/getquotationitemdata")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> getQuotationItemData() {
		List<QuotationItemsModel> result = quotationItemService.findAllQuotationItems();
		return new BaseDto<>(result, quotationItemHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}

	@GetMapping("/getquotationitembyid")
	public ResponseEntity<BaseDto<QuotationItemsModel>> getUserDataById(@RequestParam int quotationItemId) {
		QuotationItemsModel result = quotationItemService.findQuotationItemsData(quotationItemId);
		return new BaseDto<>(result, quotationItemHelper.getRetrieveQuotationItemMessage(), OK).respond();
	}

	@DeleteMapping("/delete/quotationitem")
	public ResponseEntity<BaseDto<Object>> deleteQuotationItemsData(@RequestParam int[] quotationItemId) {

		log.info("Request Object for delete is: " + quotationItemId[0]);

		quotationItemService.deleteQuotationItemsData(quotationItemId);
		;
		return new BaseDto<>(quotationItemHelper.getDeleteQuotationItemMessage(), OK).respond();
	}

	@PutMapping("/update/quotationitem")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> updateQuotationItemsData(
			@Valid @RequestBody List<QuotationItemsModel> quotationItemModel) {
		log.info("Request Object for update is: " + quotationItemModel);
		List<QuotationItemsModel> quotationItemModelRes = quotationItemService
				.updateQuotationItemsData(quotationItemModel);
		return new BaseDto<>(quotationItemModelRes, quotationItemHelper.getUpdateQuotationItemMessage(), OK).respond();
	}*/
}
