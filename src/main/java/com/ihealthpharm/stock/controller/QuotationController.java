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
import com.ihealthpharm.masters.dto.ItemDistributorDTO;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
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
	 * Service is to get the items based on the distributor
	 */
	@GetMapping("/getitemsbydistributor")
	public ResponseEntity<BaseDto<List<ItemDistributorDTO>>> getItemsByDistributor(@RequestParam Integer distributorId) {
		List<ItemDistributorDTO> result = quotationService.getItemsByDistributor(distributorId);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the items based on the distributor
	 */
	@GetMapping("/getitemsbydistributoritemcditemname")
	public ResponseEntity<BaseDto<List<ItemDistributorDTO>>> getItemsByDistributor(@RequestParam Integer distributorId, 
			@RequestParam(required=false) String itemCode, @RequestParam(required=false) String itemName) {
		System.out.println(itemCode+" "+itemName);
		List<ItemDistributorDTO> result = quotationService.getItemsByDistributor(distributorId, itemCode, itemName);
		return new BaseDto<>(result, propertyHelper.getRetrieveMessage(), OK).respond();
	}
	
}




