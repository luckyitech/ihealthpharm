package com.ihealthpharm.stock.controller;

import static org.springframework.http.HttpStatus.OK;

import java.sql.Date;
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
 * Controller is used to perform the crud operations of Invoices Items
 */
@RestController
@CrossOrigin
@Slf4j
public class InvoiceItemController {
	
	@Autowired
	private InvoiceItemHelper invoiceItemHelper;
	
	@Autowired
	private InvoiceItemService invoiceItemService;

	/**
	 * @author Gunasekhar 
	 * Service is save the invoice item
	 */
	@PostMapping("/save/invoiceitem")
	public ResponseEntity<BaseDto<InvoiceItemModel>> saveInvoiceItem(@Valid @RequestBody InvoiceItemModel invoiceItemModel) {
		log.info("Request Object insert is: "+ invoiceItemModel.toString());
		InvoiceItemModel model = invoiceItemService.saveInvoiceItem(invoiceItemModel);
		return new BaseDto<>(model, invoiceItemHelper.getSaveInvoiceItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is update the invoice item
	 */
	@PutMapping("/update/invoiceitem")
	public ResponseEntity<BaseDto<InvoiceItemModel>> updateInvoiceItem(@Valid @RequestBody InvoiceItemModel invoiceItemModel) {
		log.info("Request Object for update is: ",invoiceItemModel.toString());
		InvoiceItemModel model = invoiceItemService.updateInvoiceItem(invoiceItemModel);
		return new BaseDto<>(model, invoiceItemHelper.getUpdateInvoiceItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is update the invoice items
	 */
	@PutMapping("/update/multipleinvoiceitems")
	public ResponseEntity<BaseDto<List<InvoiceItemModel>>> updateInvoiceItems(@Valid @RequestBody List<InvoiceItemModel> invoiceItemModels) {
		log.info("Request Object for update is: "+ invoiceItemModels.toString());
		List<InvoiceItemModel> models = invoiceItemService.updateInvoiceItems(invoiceItemModels);
		return new BaseDto<>(models, invoiceItemHelper.getUpdateInvoiceItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is delete the invoice item
	 */
	@DeleteMapping("/delete/invoiceitem")
	public ResponseEntity<BaseDto<Object>> deleteInvoiceItem(@RequestParam Integer invoiceItemId) {
		log.info("Request Object for delete is: ", invoiceItemId);
		invoiceItemService.deleteInvoiceItemById(invoiceItemId);
		return new BaseDto<>(invoiceItemHelper.getDeleteInvoiceItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is delete the invoice items
	 */
	@DeleteMapping("/delete/multipleinvoiceitems")
	public ResponseEntity<BaseDto<Object>> deleteInvoiceItems(@RequestParam Integer[] invoiceItemIds) {
		log.info("Request Object for delete is: "+ invoiceItemIds.toString());
		invoiceItemService.deleteInvoiceItemByTds(invoiceItemIds);
		return new BaseDto<>(invoiceItemHelper.getDeleteInvoiceItemMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the invoiceItems
	 * 
	 */
	@GetMapping("/getallinvoiceitems")
	public ResponseEntity<BaseDto<List<InvoiceItemModel>>> getAllInvoiceItems() {
		List<InvoiceItemModel> invoiceItemModels = invoiceItemService.findAllInvoiceItems();
		return new BaseDto<>(invoiceItemModels, invoiceItemHelper.getRetrieveInvoiceItemMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the invoiceItem
	 */
	@GetMapping("/getinvoiceitembyid")
	public ResponseEntity<BaseDto<InvoiceItemModel>> getInvoiceItemById(@RequestParam Integer invoiceItemId) {
		InvoiceItemModel result = invoiceItemService.findInvoiceItemById(invoiceItemId);
		return new BaseDto<>(result, invoiceItemHelper.getRetrieveInvoiceItemMessage(), OK).respond();
	}
	
	//Purchase Invoice Details
	
	@GetMapping("/getsuppliersbysearchpid")
	public ResponseEntity<BaseDto<List<String>>> getSuppliersByInvoiceItems(@RequestParam String searchTerm){
		List<String> results=invoiceItemService.findSuppliersByInvoiceItems(searchTerm);
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	@GetMapping("/getallsupplierspid")
	public ResponseEntity<BaseDto<List<String>>> getAllSuppliersByInvoiceItems(){
		List<String> results=invoiceItemService.findAllSuppliersByInvoiceItems();
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	@GetMapping("/getgrnnosbysearchpid")
	public ResponseEntity<BaseDto<List<String>>> getInvoiceNoByInvoiceItems(@RequestParam String searchTerm){
		List<String> results=invoiceItemService.findInvoiceNoByInvoiceItems(searchTerm);
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	

	@GetMapping("/getallgrnnospid")
	public ResponseEntity<BaseDto<List<String>>> getAllInvoiceNoByInvoiceItems(){
		List<String> results=invoiceItemService.findAllInvoiceNoByInvoiceItems();
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	@GetMapping("/getinvoicedatebysearchpid")
	public ResponseEntity<BaseDto<List<String>>> getInvoiceDtByInvoiceItems(@RequestParam Date searchTerm){
		List<String> results=invoiceItemService.findInvoiceDtByInvoiceItems(searchTerm);
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	@GetMapping("/getallinvoicedatepid")
	public ResponseEntity<BaseDto<List<String>>> getAllInvoiceDtByInvoiceItems(){
		List<String> results=invoiceItemService.findAllInvoiceDtByInvoiceItems();
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	@GetMapping("/getinvoicenosbysearchpid")
	public ResponseEntity<BaseDto<List<String>>> getInvoiceNUMBERSByInvoiceItems(@RequestParam String searchTerm){
		List<String> results=invoiceItemService.findInvoiceNumbersByInvoiceItems(searchTerm);
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	@GetMapping("/getallinvoicenumberspid")
	public ResponseEntity<BaseDto<List<String>>> getAllInvoiceNumbersByInvoiceItems(){
		List<String> results=invoiceItemService.findAllInvoiceNumbersByInvoiceItems();
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	//Purchase Margin Comparison
	@GetMapping("/gettemnamesbysearchpmc")
	public ResponseEntity<BaseDto<List<String>>> getItemNamesByInvoiceItemsPMC(@RequestParam String searchTerm){
		List<String> results=invoiceItemService.findItemNamesByInvoiceItemsPMC(searchTerm);
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	@GetMapping("/getallitemnamespmc")
	public ResponseEntity<BaseDto<List<String>>> getAllItemNamesByInvoiceItemsPMC(){
		List<String> results=invoiceItemService.findAllItemNamesByInvoiceItemsPMC();
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	@GetMapping("/getsuppliersbysearchpmc")
	public ResponseEntity<BaseDto<List<String>>> getSuppliersByInvoiceItemsPMC(@RequestParam String searchTerm){
		List<String> results=invoiceItemService.findSuppliersByInvoiceItemsPMC(searchTerm);
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	@GetMapping("/getallsupplierspmc")
	public ResponseEntity<BaseDto<List<String>>> getAllSuppliersByInvoiceItemsPMC(){
		List<String> results=invoiceItemService.findAllSuppliersByInvoiceItemsPMC();
		return new BaseDto<>(results,invoiceItemHelper.getRetrieveInvoiceItemMessage(),OK).respond();
	}
	
	
}
