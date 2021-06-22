package com.ihealthpharm.stock.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.commons.TimeDurationUtility;
import com.ihealthpharm.mail.model.AttachmentModel;
import com.ihealthpharm.mail.model.SendQuotationMailModel;
import com.ihealthpharm.mail.service.impl.SendQuotationMailService;
import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.helper.SupplierHelper;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.masters.service.PharmacyService;
import com.ihealthpharm.reports.helper.ReportsExcelUtility;
import com.ihealthpharm.reports.helper.ReportsHelper;
import com.ihealthpharm.reports.helper.ReportsPDFUtility;
import com.ihealthpharm.reports.service.ReportsService;
import com.ihealthpharm.stock.dto.QuotationDTO;
import com.ihealthpharm.stock.helper.MediaTypeUtils;
import com.ihealthpharm.stock.helper.QuotationHelper;
import com.ihealthpharm.stock.model.AutoQuotationsModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;
import com.ihealthpharm.stock.model.QuotationModel;
import com.ihealthpharm.stock.service.QuotationService;
import com.ihealthpharm.stock.utils.GenerateQuotationNo;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar ,Tarun
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

	@Autowired
	private PharmacyService pharmacyService;

	
	@Autowired
	private Environment env;

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
		//log.info("Request Object insert is: "+ quotationModel.toString());
		quotationModel.setModifiedDt(new Date());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "REQUEST NEW");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to save the Request Pending Quotation
	 */
	@PostMapping("/save/requestpendingquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveRequestPendingQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		//log.info("Request Object insert is: "+ quotationModel.toString());
		quotationModel.setModifiedDt(new Date());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "REQUEST PENDING");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to save the Request Approved Quotation
	 */
	@PostMapping("/save/requestapprovedquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveRequestApprovedQuotation(@RequestBody QuotationModel quotationModel) {
		//log.info("Request Object insert is: "+ quotationModel.toString());
		//quotationModel.setApprovedDt(new Date());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "REQUEST APPROVED");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to save the Request Rejected Quotation
	 */
	@PostMapping("/save/requestrejectedquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveRequestRejectedQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		//log.info("Request Object insert is: "+ quotationModel.toString());
		quotationModel.setRejectedDate(new Date());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "REQUEST REJECTED");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}

	/*
	 * @author Gunasekhar 
	 * Service is to save the Received Pending Quotation
	 */
	@PostMapping("/save/receivedpendingquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveReceivedPendingQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		//log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "RECEIVED PENDING");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to save the Received Rejected Quotation
	 */
	@PostMapping("/save/receivedapprovedquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveReceivedApprovedQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		//log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "RECEIVED APPROVED");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to save the Received Rejected Quotation
	 */
	@PostMapping("/save/receivedrejectedquotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveReceivedRejectedQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		//log.info("Request Object insert is: "+ quotationModel.toString());
		QuotationModel model = quotationService.saveQuotation(quotationModel, "RECEIVED REJECTED");
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
		System.out.println(pharmacyId);
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
			@RequestParam(required=false) String itemName, @RequestParam(required=false) String itemDescription,@RequestParam(required=true) Integer supplierId) {
		List<ItemSupplierDTO> result = quotationService.getItemsByItemCodeOrItemNameorItemDesc(itemCode, itemName, itemDescription,supplierId);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	/**
	 * @author tarun 
	 * Service is to get the items based on the item code or item name or item description
	 */
	@GetMapping("/getitemsbyitemcodeoritemnameoritemdescForQuotation")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsByItemCodeOrItemNameOrItemDescForQuotation(@RequestParam(required=false) String itemCode, 
			@RequestParam(required=false) String itemName, @RequestParam(required=false) String itemDescription) {
		List<ItemSupplierDTO> result = quotationService.getItemsByItemCodeOrItemNameorItemDescForQuotation(itemCode, itemName, itemDescription);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	
	@GetMapping("/getitemsbyitemcodeoritemnameoritemdescForAutoQuotation")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsByItemCodeOrItemNameOrItemDescForAutoQuotation(@RequestParam(required=false) String itemCode, 
			@RequestParam(required=false) String itemName, @RequestParam(required=false) String itemDescription) {
		List<ItemSupplierDTO> result = quotationService.getItemsByItemCodeOrItemNameorItemDescForAutoQuotation(itemCode, itemName, itemDescription);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	
	@GetMapping("/getItemsForAutoQuotation")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsForAutoQuotation() {
		List<ItemSupplierDTO> result = quotationService.getItemsForAutoQuotation();
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


	//   ---------------------Quotation Changes --------------------------------------------------- //

	/**
	 * @author Tarun 
	 * Service is to get the quotations based on qtn number
	 */
	@GetMapping("/getPendingQuotations/basedOnQtnNo")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getAllQuotationsForPendingQtnSearch(@RequestParam String quotationNo){
		List<QuotationModel> response=quotationService.getAllQuotationsBasedOnQtnNoForPendingSearch(quotationNo,"REQUEST NEW");
		return new BaseDto<>(response,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}

	@GetMapping("/getPendingApprovalQuotations/basedOnQtnNo")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getAllPendingQuotationsForPendingQtnSearch(@RequestParam String quotationNo){
		List<QuotationModel> response=quotationService.getAllQuotationsBasedOnQtnNoForPendingApprovalSearch(quotationNo,"REQUEST PENDING");
		return new BaseDto<>(response,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}

	@GetMapping("/getApprovedQuotations/basedOnQtnNo")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getAllQuotationsForApprovedQtnSearch(@RequestParam String quotationNo){
		List<QuotationModel> response=quotationService.getAllQuotationsForApprovedQtnSearchBasedOnQtnNo(quotationNo,"REQUEST APPROVED");
		return new BaseDto<>(response,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}

	@GetMapping("/getRejectededQuotations/basedOnQtnNo")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getAllQuotationsForRejectedQtnSearch(@RequestParam String quotationNo){
		List<QuotationModel> response=quotationService.getAllQuotationsForRejectedQtnSearchBasedOnQtnNo(quotationNo,"REQUEST REJECTED");
		return new BaseDto<>(response,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}


	/**
	 * @author Tarun 
	 * Service is to get the quotations based on qtn number
	 */

	//qtn popup search
	@GetMapping("/getItems/byItemDescForQuotation")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsByItemDescForQuotation(
			@RequestParam String itemDescription) {
		List<ItemSupplierDTO> result = quotationService.getItemsByItemDescForQuotation(itemDescription);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	/**
	 * @author Tarun 
	 * Service is to save the send by mail Quotation
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	@PostMapping("/save/sendingByMailQuotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveSendByMailQuotation(@Valid @RequestBody QuotationModel quotationModel) throws IOException, TemplateException {
		QuotationModel model = quotationService.saveSendByMailQuotation(quotationModel,"SENT EMAIL");


		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}


	@PostMapping("/sent/sendingByMailQuotationExcel")
	public ResponseEntity<BaseDto<QuotationModel>> sendExcelFileAsMailAttach(
			@RequestParam Integer quotationId,
			@RequestParam("excelFile") MultipartFile blobData,
			@RequestParam String requestedName,
			@RequestParam String supplierModelObj) throws IOException, TemplateException {

		QuotationModel quotationModel=quotationService.findQuotationById(quotationId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

		PharmacyModel pharmacyDetails=pharmacyService.findPharmacyById(quotationModel.getPharmacyModel().getPharmacyId());

		SupplierModel supplierModel = new SupplierModel();
		ObjectMapper objectMapper = new ObjectMapper();

		supplierModel = objectMapper.readValue(supplierModelObj, SupplierModel.class);

		if(Objects.nonNull(quotationModel)) {
			//			List<SupplierModel> suppliersList=quotationService.getAllSuppliersByQuotationId(quotationModel.getQuotationId());
			//			System.out.println(suppliersList.size());


			if(Objects.nonNull(supplierModel)) {

				List<QuotationItemsModel> quotationItemsForEachSupplier=quotationService.getQuotationDataByIdAndSup(quotationModel.getQuotationId(),supplierModel.getSupplierId());

				SendQuotationMailModel mailModel=new SendQuotationMailModel();

				mailModel.setFromEmail(env.getProperty("spring.mail.username"));


				mailModel.setSubject("REQUEST FOR QUOTATION"+" "+pharmacyDetails.getPharmacyName()+"("+dateFormat.format(new Date())+")");
				mailModel.setQuotationNo(quotationModel.getQuotationNo());


				mailModel.setRequestedBy(requestedName);
				mailModel.setQuotationNo(quotationModel.getQuotationNo());
				mailModel.setDescription(quotationModel.getDescription());
				mailModel.setQuotItemModel(quotationItemsForEachSupplier);
				mailModel.setQuotationDate(dateFormat.format(quotationModel.getQuotationDt()));
				mailModel.setPharmacyName(pharmacyDetails.getPharmacyName());
				mailModel.setPharmaAddress1(pharmacyDetails.getAddressLine1());
				mailModel.setPharmaAddress2(pharmacyDetails.getAddressLine2());
				mailModel.setPinNo(pharmacyDetails.getTaxId());
				mailModel.setMobileOne(pharmacyDetails.getPhoneNumber());
				mailModel.setWhatsAppNo(pharmacyDetails.getPhoneNumber());
				mailModel.setBccEmail(pharmacyDetails.getBccEmailId());
				
				if(Objects.isNull(quotationModel.getRemarks())) {
					mailModel.setRemarks("");
				}else {
				mailModel.setRemarks(quotationModel.getRemarks());
				}
				
				System.out.println("file name"+"Request for quotation" );
				String FilePath = blobData.getOriginalFilename();
				File f= new File(FilePath);


				AttachmentModel mail = new AttachmentModel();

				try {

					byte[] b=blobData.getBytes();
					OutputStream os= new FileOutputStream(f);
					os.write(b);
					os.close();
					System.out.println("OutputStream"+os);
					System.out.println("File"+f);
					List < Object > attachments = new ArrayList < Object > ();
					attachments.add(f);
					mail.setAttachments(attachments);
					System.out.println(attachments);


				}  catch(Exception e) {
					e.printStackTrace();
				}



				if(Objects.nonNull(supplierModel.getEmailId())&&!ObjectUtils.isEmpty(supplierModel.getEmailId())) {
					System.out.println("mail main");
					mailModel.setToEmail(supplierModel.getEmailId());
					System.out.println(mailModel.getQuotItemModel());

					try {

						sendQuotationMailService.sendQuotationEmail(mailModel,mail);


					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}

				if(Objects.nonNull(supplierModel.getContactPersonEmailID()) &&!ObjectUtils.isEmpty(supplierModel.getContactPersonEmailID())) {
					System.out.println("mail one");
					mailModel.setToEmail(supplierModel.getContactPersonEmailID());
					try {
						sendQuotationMailService.sendQuotationEmail(mailModel,mail);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(Objects.nonNull(supplierModel.getContactPersonEmailIdTwo()) && !ObjectUtils.isEmpty(supplierModel.getContactPersonEmailIdTwo())) {
					System.out.println("mail two");
					mailModel.setToEmail(supplierModel.getContactPersonEmailIdTwo());
					try {
						sendQuotationMailService.sendQuotationEmail(mailModel,mail);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(Objects.nonNull(supplierModel.getContactPersonEmailIdThree()) && !ObjectUtils.isEmpty(supplierModel.getContactPersonEmailIdThree())) {
					System.out.println("mail three");
					mailModel.setToEmail(supplierModel.getContactPersonEmailIdThree());
					try {
						sendQuotationMailService.sendQuotationEmail(mailModel,mail);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(Objects.nonNull(supplierModel.getContactPersonEmailIdFour()) && !ObjectUtils.isEmpty(supplierModel.getContactPersonEmailIdFour())) {

					mailModel.setToEmail(supplierModel.getContactPersonEmailIdFour());
					try {
						sendQuotationMailService.sendQuotationEmail(mailModel,mail);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}



			}
		}

		quotationService.updateQuotationItemSupplierMailStatusToSent(quotationModel.getQuotationId(),supplierModel.getSupplierId());
		return new BaseDto<>(quotationModel, "Mail sent successfully", OK).respond();

	}

	/**
	 * @author Tarun 
	 * Service is to save the send by mail Quotation
	 */
	@GetMapping("/getAll/sendByMailQuotation")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getSendByMailQuotation() {
		List<QuotationModel> model = quotationService.getSendByMailQuotation();
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}

	/**
	 * @author tarun 
	 * Service is to save the Received Rejected Quotation
	 */
	@PostMapping("/save/approvedSupplierQuotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveApprovedQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		QuotationModel model = quotationService.saveSupplierApprovedQuotation(quotationModel, "SENT EMAIL APPROVED");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}


	/**
	 * @author tarun 
	 * Service is to save the Received Rejected Quotation
	 */
	@PostMapping("/save/rejectedSupplierQuotation")
	public ResponseEntity<BaseDto<QuotationModel>> saveRejectedQuotation(@Valid @RequestBody QuotationModel quotationModel) {
		QuotationModel model = quotationService.saveSupplierRejectedQuotation(quotationModel, "SENT EMAIL REJECTED");
		return new BaseDto<>(model, quotationHelper.getSaveQuotationMessage(), OK).respond();
	}



	/**
	 * @author tarun 
	 * Service is to get the Received mail approved Quotation
	 */
	@GetMapping("/get/approvedSupplierQuotation")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getApprovedMailQuotation() {
		List<QuotationModel> model = quotationService.getSupplierApprovedQuotation();
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}

	/**
	 * @author tarun 
	 * Service is to get the Received mail approved Quotation
	 */
	@GetMapping("/get/rejectedSupplierQuotation")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getRejectedMailQuotation() {
		List<QuotationModel> model = quotationService.getSupplierRejectedQuotation();
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}

	// searches in supplier qtns
	/**
	 * @author tarun 
	 * Service is to get the outstanding quotations by qtuotation number
	 */
	@GetMapping("/getOutstandingQtns/basedOnQtnNo")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getAllQuotationsForOustandingQtnSearch(@RequestParam String quotationNo){
		List<QuotationModel> model = quotationService.getSendByMailQuotationForOutstanding(quotationNo);
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}

	/**
	 * @author tarun 
	 * Service is to get the Supplier Quoations Approved quotations by qtuotation number
	 */
	@GetMapping("/getApprovedSupplierQtns/basedOnQtnNo")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getSupplierQtnsApprovedSearches(@RequestParam String quotationNo) {
		List<QuotationModel> model = quotationService.getSupplierApprovedQuotationSearch(quotationNo);
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}

	/**
	 * @author tarun 
	 * Service is to get the Supplier Quoations Rejected quotations by qtuotation number
	 */
	@GetMapping("/getRejectedSupplierQuotation/basedOnQtnNo")
	public ResponseEntity<BaseDto<List<QuotationModel>>> getRejectedMailQuotationForSearch(@RequestParam String quotationNo) {
		List<QuotationModel> model = quotationService.getSupplierRejectedQuotationForSearch(quotationNo);
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}


	/**
	 * @author tarun 
	 * Service is to get the  Quoations for po
	 */
	@GetMapping("/getLimitedQuotations/ForPO")
	public ResponseEntity<BaseDto<List<QuotationDTO>>> getQuotationsForPO( @RequestParam Integer start, @RequestParam Integer end) {
		List<QuotationDTO> model = quotationService.getLimitedQuotationsForPO(start,end);
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}

	/**
	 * @author tarun 
	 * Service is to get the  Quoations for po
	 */
	@GetMapping("/getQuotation/ForPO")
	public ResponseEntity<BaseDto<QuotationModel>> getQuotationDataForPO( @RequestParam Integer quotationId) {
		QuotationModel model = quotationService.getQuotationDataForPO(quotationId);
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}

	/**
	 * @author tarun 
	 * Service is to get the  Quoations for po on Qtn search
	 */
	@GetMapping("/getQuotations/ForPO/byQtnNoSearch")
	public ResponseEntity<BaseDto<List<QuotationDTO>>> getQuotationsForPOBySearch( @RequestParam String quotationNo) {
		List<QuotationDTO> model = quotationService.getQuotationsForPOBySearch(quotationNo);
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}


	@GetMapping("/getQuotationNoBySearch")
	public ResponseEntity<BaseDto<List<String>>> getQtnNoBySearch(@RequestParam String searchTerm){
		List<String> results=quotationService.findQuotationNoBySearch(searchTerm);
		return new BaseDto<>(results,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}

	@GetMapping("/getAllQuotationNo")
	public ResponseEntity<BaseDto<List<String>>> getAllQtnNo(){
		List<String> results=quotationService.findAllQuotationNo();
		return new BaseDto<>(results,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}

	@GetMapping("/getSuppliersInQtnBySearch")
	public ResponseEntity<BaseDto<List<String>>> getSuppliersInQtnBySearch(@RequestParam String searchTerm){
		List<String> results=quotationService.findSuppliersInQtnBySearch(searchTerm);
		return new BaseDto<>(results,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}

	@GetMapping("/getQuotation/ForPO/bySupplierAndQuotation")
	public ResponseEntity<BaseDto<List<QuotationItemsModel>>> getQuotationDataForPOBySupplier(@RequestParam Integer quotationId,@RequestParam Integer supplierId) {
		List<QuotationItemsModel> model = quotationService.getQuotationDataForPOBySupplier(quotationId,supplierId);
		return new BaseDto<>(model, quotationHelper.getRetrieveQuotationMessage(), OK).respond();
	}


	@GetMapping("/getSuppliersList/basedOnQtnNo")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getSuppliersInQtnByQuotationNo(@RequestParam String quotationNo){
		List<SupplierModel> results=quotationService.findSuppliersInQtnByQuotationNo(quotationNo);
		return new BaseDto<>(results,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}

	
	@GetMapping("/getSuppliersList/basedOnQtnNoForUpdatePrice")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getSuppliersInQtnByQuotationNoForPriceUpdate(@RequestParam String quotationNo){
		List<SupplierModel> results=quotationService.findSuppliersInQtnByQuotationNoForPriceUpdate(quotationNo);
		return new BaseDto<>(results,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}
	
	
	@GetMapping("/updateAutoQuotationRecord/ForQuotation")
	public ResponseEntity<BaseDto<AutoQuotationsModel>> markAutoQuotationItemInActive(@RequestParam Integer itemId,@RequestParam Integer supplierId,@RequestParam Character flag){
		AutoQuotationsModel autoQuotModel=quotationService.markAutoQuotationItemInActive(itemId,supplierId,flag);
		return new BaseDto<>(autoQuotModel,quotationHelper.getRetrieveQuotationMessage(),OK).respond();
	}
}