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
import  com.ihealthpharm.stock.service.*;
import com.ihealthpharm.stock.helper.*;
import com.ihealthpharm.stock.model.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of Invoices Status
 */
@RestController
@CrossOrigin
@Slf4j
public class InvoiceStatusController {
	
	@Autowired
	private InvoiceStatusHelper invoiceStatusHelper;
	
	@Autowired
	private InvoiceStatusService invoiceStatusService;

	/**
	 * @author Gunasekhar 
	 * Service is to save the invoice status
	 */
	@PostMapping("/save/invoicestatus")
	public ResponseEntity<BaseDto<InvoiceStatusModel>> saveInvoiceStatus(@Valid @RequestBody InvoiceStatusModel invoiceStatusModel) {
		log.info("Request Object insert is: "+ invoiceStatusModel.toString());
		InvoiceStatusModel model = invoiceStatusService.saveInvoiceStatus(invoiceStatusModel);
		return new BaseDto<>(model, invoiceStatusHelper.getSaveInvoiceStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the invoice status
	 */
	@PutMapping("/update/invoicestatus")
	public ResponseEntity<BaseDto<InvoiceStatusModel>> updateInvoiceStatus(@Valid @RequestBody InvoiceStatusModel invoiceStatusModel) {
		log.info("Request Object for update is: ",invoiceStatusModel.toString());
		InvoiceStatusModel model = invoiceStatusService.updateInvoiceStatus(invoiceStatusModel);
		return new BaseDto<>(model, invoiceStatusHelper.getUpdateInvoiceStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update all the invoice status
	 */
	@PutMapping("/update/multipleinvoicestatus")
	public ResponseEntity<BaseDto<List<InvoiceStatusModel>>> updateAllInvoiceStatus(@Valid @RequestBody List<InvoiceStatusModel> invoiceStatusModels) {
		log.info("Request Object for update is: "+ invoiceStatusModels.toString());
		List<InvoiceStatusModel> models = invoiceStatusService.updateAllInvoiceStatus(invoiceStatusModels);
		return new BaseDto<>(models, invoiceStatusHelper.getUpdateInvoiceStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete the invoice status
	 */
	@DeleteMapping("/delete/invoicestatus")
	public ResponseEntity<BaseDto<Object>> deleteInvoiceStatus(@RequestParam Integer invoiceStatusId) {
		log.info("Request Object for delete is: ", invoiceStatusId);
		invoiceStatusService.deleteInvoiceStatusById(invoiceStatusId);
		return new BaseDto<>(invoiceStatusHelper.getDeleteInvoiceStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete all the invoice status
	 */
	@DeleteMapping("/delete/multipleinvoicestatus")
	public ResponseEntity<BaseDto<Object>> deleteInvoiceStatus(@RequestParam Integer[] invoiceStatusIds) {
		log.info("Request Object for delete is: "+ invoiceStatusIds.toString());
		invoiceStatusService.deleteInvoiceStatusByTds(invoiceStatusIds);
		return new BaseDto<>(invoiceStatusHelper.getDeleteInvoiceStatusMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the invoice status
	 * 
	 */
	@GetMapping("/getallinvoicestatus")
	public ResponseEntity<BaseDto<List<InvoiceStatusModel>>> getAllInvoiceStatus() {
		List<InvoiceStatusModel> invoiceStatusModels = invoiceStatusService.findAllInvoiceStatus();
		return new BaseDto<>(invoiceStatusModels, invoiceStatusHelper.getRetrieveInvoiceStatusMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the invoice status
	 */
	@GetMapping("/getinvoicestatusbyid")
	public ResponseEntity<BaseDto<InvoiceStatusModel>> getInvoiceStatusById(@RequestParam Integer invoiceStatusId) {
		InvoiceStatusModel result = invoiceStatusService.findInvoiceStatusById(invoiceStatusId);
		return new BaseDto<>(result, invoiceStatusHelper.getRetrieveInvoiceStatusMessage(), OK).respond();
	}
	
	
}
