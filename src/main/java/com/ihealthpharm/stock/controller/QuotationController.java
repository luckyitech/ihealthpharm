package com.ihealthpharm.stock.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import com.ihealthpharm.mail.service.impl.SendQuotationMailService;
import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.helper.SupplierHelper;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.stock.helper.MediaTypeUtils;
import com.ihealthpharm.stock.helper.QuotationHelper;
import com.ihealthpharm.stock.model.QuotationModel;
import com.ihealthpharm.stock.service.QuotationService;
import com.ihealthpharm.stock.utils.GenerateQuotationNo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of Quotation
 */
@RestController
@CrossOrigin
@Slf4j
public class QuotationController {
	
	@Autowired
	private QuotationHelper quotationHelper;
	
	@Autowired
	private QuotationService quotationService;
	
	@Autowired
	private ItemPropertyHelper propertyHelper;
	
	@Autowired
	private SupplierHelper supplierHelper;
	
	@Autowired
	private SendQuotationMailService sendQuotationMailService;
	
	@Autowired
    private ServletContext servletContext;
	
	/**
	 * @author Gunasekhar 
	 * Service is to save the Quotation
	 */
	@PostMapping("/save/quotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel);
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to save the Request New Quotation
	 */
	@PostMapping("/save/requestnewquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveRequestNewQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "REQUEST NEW", "PENDING");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to save the Request Pending Quotation
	 */
	@PostMapping("/save/requestpendingquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveRequestPendingQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "REQUEST PENDING", "PENDING");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to save the Request Approved Quotation
	 */
	@PostMapping("/save/requestapprovedquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveRequestApprovedQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "REQUEST APPROVED", "PENDING");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to save the Request Rejected Quotation
	 */
	@PostMapping("/save/requestrejectedquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveRequestRejectedQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "REQUEST REJECTED", "PENDING");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to save the Received Pending Quotation
	 */
	@PostMapping("/save/receivedpendingquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveReceivedPendingQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "RECEIVED PENDING", "PENDING");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to save the Received Rejected Quotation
	 */
	@PostMapping("/save/receivedapprovedquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveReceivedApprovedQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "RECEIVED APPROVED", "PENDING");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to save the Received Rejected Quotation
	 */
	@PostMapping("/save/receivedrejectedquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveReceivedRejectedQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "RECEIVED REJECTED", "PENDING");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the Quotation
	 */
	@PutMapping("/update/quotation")
	public ResponseEntity<BaseDto<QuotationModel>> updateQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		log.info("Request Object for update is: ",quotationModel.toString());
		QuotationModel model = quotationService.updateQuotation(quotationModel);
		return new BaseDto<>(model, quotationHelper.getUpdateQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the Quotations
	 */
	@PutMapping("/update/multiplequotations")
	public ResponseEntity<BaseDto<List<QuotationModel>>> updateQuotations(@Valid @RequestBody List<QuotationModel> quotationModels) {
		log.info("Request Object for update is: "+ quotationModels.toString());
		List<QuotationModel> models = quotationService.updateQuotation(quotationModels);
		return new BaseDto<>(models, quotationHelper.getUpdateQuotationMessage(), OK).respond();
	}
		
	/**
	 * @author Gunasekhar 
	 * Service is to delete the Quotation
	 */
	@DeleteMapping("/delete/quotation")
	public ResponseEntity<BaseDto<Object>> deleteQuotation(@RequestParam Integer quotationId) {
		log.info("Request Object for delete is: ", quotationId);
		quotationService.deleteQuotationById(quotationId);
		return new BaseDto<>(quotationHelper.getDeleteQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete all the Quotations
	 */
	@DeleteMapping("/delete/multiplequotations")
	public ResponseEntity<BaseDto<Object>> deleteQuotations(@RequestParam Integer[] quotationIds) {
		log.info("Request Object for delete is: "+ quotationIds.toString());
		quotationService.deleteQuotationByTds(quotationIds);
		return new BaseDto<>(quotationHelper.getDeleteQuotationMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the Quotations
	 * 
	 */
	@GetMapping("/getallquotations")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getAllQuotations() {
		List<QuotationModel> quotationModels = quotationService.findAllQuotation();
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the Quotation
	 */
	@GetMapping("/getquotationbyid")
	public ResponseEntity<BaseDto<QuotationModel>> getQuotationById(@RequestParam Integer quotationId) {
		QuotationModel result = quotationService.findQuotationById(quotationId);
		return new BaseDto<>(result, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Request New Quotation based on the pharmacyId
	 */
	@GetMapping("/getrequestnewquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getRequestNewQuotationByPharmacy(@RequestParam Integer pharmacyId) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "REQUEST NEW");
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Request Pending Quotation based on the pharmacyId
	 */
	@GetMapping("/getrequestpendingquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getRequestpendingQuotationByPharmacy(@RequestParam Integer pharmacyId) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "REQUEST PENDING");
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Request Approved Quotation based on the pharmacyId
	 */
	@GetMapping("/getrequestapprovedquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getRequestApprovedQuotationByPharmacy(@RequestParam Integer pharmacyId) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "REQUEST APPROVED");
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Request Rejected Quotation based on the pharmacyId
	 */
	@GetMapping("/getrequestrejectedquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getRequestRejectedQuotationByPharmacy(@RequestParam Integer pharmacyId) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "REQUEST REJECTED");
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Received Pending Quotation based on the pharmacyId
	 */
	@GetMapping("/getreceivedpendingquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getReceivedPendingQuotationByPharmacy(@RequestParam Integer pharmacyId) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "RECEIVED PENDING");
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Received Approved Quotation based on the pharmacyId
	 */
	@GetMapping("/getreceivedapprovedquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getReceivedApprovedQuotationByPharmacy(@RequestParam Integer pharmacyId) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "RECEIVED APPROVED");
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Received Rejected Quotation based on the pharmacyId
	 */
	@GetMapping("/getreceivedrejectedquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getReceivedRejectedQuotationByPharmacy(@RequestParam Integer pharmacyId) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "RECEIVED REJECTED");
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Request Pending Quotation based on the pharmacyId
	 */
	@GetMapping("/getsentquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getSentQuotationByPharmacy(@RequestParam Integer pharmacyId) {
		List<QuotationModel> quotationModels = quotationService.getSentQuotationByPharmacy(pharmacyId);
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Search Request New Quotation based on the pharmacyId and Quotation No or Description
	 */
	@GetMapping("/searchrequestnewquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> searchRequestNewQuotationByPharmacy(@RequestParam Integer pharmacyId, 
			@RequestParam(required=false) String quotationNo, @RequestParam(required=false) String description) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "REQUEST NEW", quotationNo, description);
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Search Request Pending Quotation based on the pharmacyId and Quotation No or Description
	 */
	@GetMapping("/searchrequestpendingquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> searchRequestpendingQuotationByPharmacy(@RequestParam Integer pharmacyId, 
			@RequestParam(required=false) String quotationNo, @RequestParam(required=false) String description) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "REQUEST PENDING", quotationNo, description);
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Search Request Approved Quotation based on the pharmacyId and Quotation No or Description
	 */
	@GetMapping("/searchrequestapprovedquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> searchRequestApprovedQuotationByPharmacy(@RequestParam Integer pharmacyId, 
			@RequestParam(required=false) String quotationNo, @RequestParam(required=false) String description) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "REQUEST APPROVED", quotationNo, description);
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Search Request Rejected Quotation based on the pharmacyId and Quotation No or Description
	 */
	@GetMapping("/searchrequestrejectedquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> searchRequestRejectedQuotationByPharmacy(@RequestParam Integer pharmacyId, 
			@RequestParam(required=false) String quotationNo, @RequestParam(required=false) String description) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "REQUEST REJECTED", quotationNo, description);
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Search Received Pending Quotation based on the pharmacyId and Quotation No or Description
	 */
	@GetMapping("/searchreceivedpendingquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> searchReceivedPendingQuotationByPharmacy(@RequestParam Integer pharmacyId, 
			@RequestParam(required=false) String quotationNo, @RequestParam(required=false) String description) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "RECEIVED PENDING", quotationNo, description);
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Search Received Approved Quotation based on the pharmacyId and Quotation No or Description
	 */
	@GetMapping("/searchreceivedapprovedquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> searchReceivedApprovedQuotationByPharmacy(@RequestParam Integer pharmacyId, 
			@RequestParam(required=false) String quotationNo, @RequestParam(required=false) String description) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "RECEIVED APPROVED", quotationNo, description);
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Search Received Rejected Quotation based on the pharmacyId and Quotation No or Description
	 */
	@GetMapping("/searchreceivedrejectedquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> searchReceivedRejectedQuotationByPharmacy(@RequestParam Integer pharmacyId, 
			@RequestParam(required=false) String quotationNo, @RequestParam(required=false) String description) {
		List<QuotationModel> quotationModels = quotationService.getQuotationByPharmacyAndStatus(pharmacyId, "RECEIVED REJECTED", quotationNo, description);
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for Request Pending Quotation based on the pharmacyId
	 */
	@GetMapping("/searchsentquotationbypharmacy")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getSentQuotationByPharmacy(@RequestParam Integer pharmacyId, 
			@RequestParam(required=false) String quotationNo, @RequestParam(required=false) String description) {
		List<QuotationModel> quotationModels = quotationService.getSentQuotationByPharmacy(pharmacyId, quotationNo, description);
		return new BaseDto<>(quotationModels, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for automatic generation of Quotation Number 
	 */
	@GetMapping("/generatequotationno")
	public ResponseEntity<BaseDto<String>> generateQuotationeNo(@RequestParam Integer pharmacyId) {
		String pharmacyNm = quotationService.getPharmacyNm(pharmacyId);
		Long quotationCount = quotationService.getQuotationCount(pharmacyId);
		GenerateQuotationNo generateQuotNo = new GenerateQuotationNo();
		String purchaseNo = generateQuotNo.generateQuotNo(pharmacyNm, quotationCount);
		return new BaseDto<>(purchaseNo, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the items based on the Supplier
	 */
	@GetMapping("/getitemsbysupplier")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsBySupplier(@RequestParam Integer supplierId) {
		List<ItemSupplierDTO> result = quotationService.getItemsBySupplier(supplierId);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the items based on the Supplier
	 */
	@GetMapping("/getitemsbysupplieritemcditemname")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsBySupplier(@RequestParam Integer supplierId, 
			@RequestParam(required=false) String itemCode, @RequestParam(required=false) String itemName) {
		System.out.println(itemCode+" "+itemName);
		List<ItemSupplierDTO> result = quotationService.getItemsBySupplier(supplierId, itemCode, itemName);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the list of suppliers and items.
	 */
	@GetMapping("/getsupplieritemsbyquotation")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getSupplierItemsByQuotationId(@RequestParam Integer quotationId) {
		List<SupplierModel> result = quotationService.getSupplierItemsByQuotationId(quotationId);
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the list of suppliers and items.
	 */
	@GetMapping("/getsupplieritemsbyquotationandsupplier")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getSupplierItemsByQuotationId(@RequestParam Integer quotationId, @RequestParam List<Integer> suppliersId) {
		List<SupplierModel> result = quotationService.getSupplierItemsByQuotationIdAndSupplierId(quotationId, suppliersId);
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the list of suppliers and items.
	 */
	@GetMapping("/getsuppliersbyquotation")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getSuppliersByQuotationId(@RequestParam Integer quotationId) {
		List<SupplierModel> result = quotationService.getSuppliersByQuotationId(quotationId);
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the list of suppliers and items.
	 */
	@GetMapping("/getitemsbysupplierquotation")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsBySupplierQuotationId(@RequestParam Integer supplierId, @RequestParam Integer quotationId) {
		List<ItemSupplierDTO> result = quotationService.getItemsBySupplierQuotationId(supplierId, quotationId);
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the list of suppliers and items.
	 */
	@GetMapping("/getsupplierbyitem")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getSupplierByItem(@RequestParam Integer itemsId) {
		List<SupplierModel> result = quotationService.getSupplierByItem(itemsId);
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the items based on the itemcode or itemname
	 */
	@GetMapping("/getitemsbyitemcodeoritemname")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsByItemCodeOrItemName(@RequestParam(required=false) String itemCode, 
			@RequestParam(required=false) String itemName) {
		List<ItemSupplierDTO> result = quotationService.getItemsByItemCodeOrItemName(itemCode, itemName);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the items based on the item code or item name or item description
	 */
	@GetMapping("/getitemsbyitemcodeoritemnameoritemdesc")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsByItemCodeOrItemNameOrItemDesc(@RequestParam(required=false) String itemCode, 
			@RequestParam(required=false) String itemName, @RequestParam(required=false) String itemDescription) {
		List<ItemSupplierDTO> result = quotationService.getItemsByItemCodeOrItemNameorItemDesc(itemCode, itemName, itemDescription);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the items based on the item code or item name
	 */
	@GetMapping("/sendmail")
	public ResponseEntity<BaseDto<String>> sendMail() {
		sendQuotationMailService.attachFileToMail();
		return new BaseDto<>("", propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	 // http://localhost:8080/download1?fileName=abc.zip
    // Using ResponseEntity<InputStreamResource>
	@GetMapping("/download1")
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {
 
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, "guna");
        System.out.println("fileName: " + "guna");
        System.out.println("mediaType: " + mediaType);
 
        File file = new File("d://guna.pdf");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
 
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
	
	
}