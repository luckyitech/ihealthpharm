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
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.helper.InvoiceHelper;
import com.ihealthpharm.stock.model.InvoiceModel;
import com.ihealthpharm.stock.service.InvoiceService;
import com.ihealthpharm.stock.service.QuotationService;
import com.ihealthpharm.stock.utils.GenerateGRNNo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of Invoices
 */
@RestController
@CrossOrigin
@Slf4j
public class InvoiceController {
	
	@Autowired
	private InvoiceHelper invoiceHelper;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private QuotationService quotationService;
	
	@Autowired
	private ItemPropertyHelper propertyHelper;

	/**
	 * @author Gunasekhar 
	 * Service is save the invoice, invoice items and stock details
	 */
	@PostMapping("/save/invoice")
	public ResponseEntity<BaseDto<InvoiceModel>> saveInvoice(@Valid @RequestBody InvoiceModel invoiceModel) {
		log.info("Request Object insert is: "+ invoiceModel.toString());
		InvoiceModel model = invoiceService.saveInvoice(invoiceModel);
		model.setInvoiceItems(null);
		model.setStocks(null);
		return new BaseDto<>(model, invoiceHelper.getSaveInvoiceMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for update the Invoice
	 */
	@PutMapping("/update/invoice")
	public ResponseEntity<BaseDto<InvoiceModel>> updateInvoice(@Valid @RequestBody InvoiceModel invoiceModel) {
		log.info("Request Object for update is: ",invoiceModel.toString());
		InvoiceModel model = invoiceService.updateInvoice(invoiceModel);
		return new BaseDto<>(model, invoiceHelper.getUpdateInvoiceMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for update the Invoices
	 */
	@PutMapping("/update/multipleinvoices")
	public ResponseEntity<BaseDto<List<InvoiceModel>>> updateInvoices(@Valid @RequestBody List<InvoiceModel> invoiceModels) {
		log.info("Request Object for update is: "+ invoiceModels.toString());
		List<InvoiceModel> models = invoiceService.updateInvoices(invoiceModels);
		return new BaseDto<>(models, invoiceHelper.getUpdateInvoiceMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for delete Invoice
	 */
	@DeleteMapping("/delete/invoice")
	public ResponseEntity<BaseDto<Object>> deleteInvoice(@RequestParam Integer invoiceId) {
		log.info("Request Object for delete is: ", invoiceId);
		invoiceService.deleteInvoiceById(invoiceId);
		return new BaseDto<>(invoiceHelper.getDeleteInvoiceMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for delete Invoices
	 */
	@DeleteMapping("/delete/multipleinvoices")
	public ResponseEntity<BaseDto<Object>> deleteInvoices(@RequestParam Integer[] invoiceIds) {
		log.info("Request Object for delete is: "+ invoiceIds.toString());
		invoiceService.deleteInvoiceByTds(invoiceIds);
		return new BaseDto<>(invoiceHelper.getDeleteInvoiceMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for get all invoices
	 * 
	 */
	@GetMapping("/getallinvoices")
	public ResponseEntity<BaseDto<List<InvoiceModel>>> getAllInvoices() {
		List<InvoiceModel> invoiceModels = invoiceService.findAllInvoices();
		return new BaseDto<>(invoiceModels, invoiceHelper.getRetrieveInvoiceMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for get invoice using invoiceId
	 */
	@GetMapping("/getinvoicebyid")
	public ResponseEntity<BaseDto<InvoiceModel>> getInvoiceById(@RequestParam Integer invoiceId) {
		InvoiceModel result = invoiceService.findInvoiceById(invoiceId);
		return new BaseDto<>(result, invoiceHelper.getRetrieveInvoiceMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for automatic generation of GRN Number 
	 */
	@GetMapping("/generatepurchasegrnno")
	public ResponseEntity<BaseDto<String>> generatePurchaseGRNNo(@RequestParam Integer pharmacyId) {
		String pharmacyNm = quotationService.getPharmacyNm(pharmacyId);
		Long invoiceCount = invoiceService.getInvoiceCount(pharmacyId);
		GenerateGRNNo generateGRNNo = new GenerateGRNNo();
		String grnNumber = generateGRNNo.generateGNR(pharmacyNm, invoiceCount);
		return new BaseDto<>(grnNumber, invoiceHelper.getRetrieveInvoiceMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for get all invoices of particular pharmacy
	 */
	@GetMapping("/getallinvoicesbypharmacy")
	public ResponseEntity<BaseDto<List<InvoiceModel>>> getAllInvoicesByPharmacy(@RequestParam Integer pharmacyId) {
		List<InvoiceModel> invoiceModels = invoiceService.findAllInvoicesByPharmacyId(pharmacyId);
		return new BaseDto<>(invoiceModels, invoiceHelper.getRetrieveInvoiceMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for get all invoice items
	 */
	@GetMapping("/getallinvoicesitems")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getInvoiceItems(@RequestParam Integer invoiceId) {
		List<ItemsModel> itemsModels = invoiceService.getInvoiceItems(invoiceId);
		return new BaseDto<>(itemsModels, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
}
