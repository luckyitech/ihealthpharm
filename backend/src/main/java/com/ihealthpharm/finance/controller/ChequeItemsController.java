package com.ihealthpharm.finance.controller;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.finance.helper.ChequeItemsHelper;
import com.ihealthpharm.finance.model.ChequeItemsModel;
import com.ihealthpharm.finance.service.ChequeItemsService;
import com.ihealthpharm.stock.helper.InvoiceItemHelper;
import com.ihealthpharm.stock.model.InvoiceItemModel;
import com.ihealthpharm.stock.service.InvoiceItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class ChequeItemsController {

	@Autowired
	private ChequeItemsHelper chequeItemHelper;
	
	@Autowired
	private ChequeItemsService chequeItemService;

	/**
	 * @author Tarun 
	 * Service is to save the cheque item
	 */
	@PostMapping("/save/chequeItem")
	public ResponseEntity<BaseDto<ChequeItemsModel>> saveInvoiceItem(@Valid @RequestBody ChequeItemsModel chequeItemsModel) {
		log.info("Request Object insert is: "+ chequeItemsModel.toString());
		ChequeItemsModel model = chequeItemService.saveChequeItem(chequeItemsModel);
		return new BaseDto<>(model,chequeItemHelper.getSaveChequeItemsMessage(), OK).respond();
	}
	
}
