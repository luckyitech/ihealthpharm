package com.ihealthpharm.stock.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.ihealthpharm.mail.model.AttachmentModel;
import com.ihealthpharm.mail.model.SendPurchaseOrderModel;
import com.ihealthpharm.mail.model.SendQuotationMailModel;
import com.ihealthpharm.mail.service.impl.SendQuotationMailService;
import com.ihealthpharm.masters.helper.SupplierHelper;
import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.masters.service.EmployeeService;
import com.ihealthpharm.masters.service.PharmacyService;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.stock.helper.PurchaseOrderHelper;
import com.ihealthpharm.stock.model.PurchaseOrderItemsModel;
import com.ihealthpharm.stock.model.PurchaseOrderModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;
import com.ihealthpharm.stock.model.QuotationModel;
import com.ihealthpharm.stock.service.PurchaseOrderItemsService;
import com.ihealthpharm.stock.service.PurchaseOrderService;
import com.ihealthpharm.stock.service.QuotationService;
import com.ihealthpharm.stock.utils.GenerateGRNNo;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService purchaseorderService;

	@Autowired
	PurchaseOrderHelper purchaseorderHelper;

	@Autowired
	SupplierHelper supplierHelper;

	@Autowired
	private ItemPropertyHelper propertyHelper;

	@Autowired
	private QuotationService quotationService;
	
	@Autowired
	private SendQuotationMailService sendQuotationMailService;

	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	PurchaseOrderItemsService purchaseOrderItemsService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Environment env;
	
	
	@PostMapping("/save/purchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> insertPurchaseOrderData(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.savePurchaseOrderData(purchaseorderModel);
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getSavePurchaseOrderMessage(), OK).respond();
	}

	@PutMapping("/update/purchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> updatePurchaseOrderData(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
		log.info("Request Object for update is: ", purchaseorderModel);
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.updatePurchaseOrderData(purchaseorderModel);
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getUpdatePurchaseOrderMessage(), OK).respond();
	}

	@DeleteMapping("/delete/purchaseorder")
	public ResponseEntity<BaseDto<Object>> deletePurchaseOrderData(@RequestParam Integer purchaseorderId) {
		log.info("Request Object for delete is: ", purchaseorderId);
		purchaseorderService.deletePurchaseOrderById(purchaseorderId);
		return new BaseDto<>(purchaseorderHelper.getDeletePurchaseOrderMessage(), OK).respond();
	}

	@GetMapping("/getpurchaseorderdata")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getPurchaseOrderData() {
		List<PurchaseOrderModel> result = purchaseorderService.findAllPurchaseOrders();
		return new BaseDto<>(result, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	@GetMapping("/getpurchaseorderdatabyid")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> getPurchaseOrderDataById(@RequestParam Integer purchaseorderId) {
		PurchaseOrderModel result = purchaseorderService.findPurchaseOrderById(purchaseorderId);
		return new BaseDto<>(result, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	@DeleteMapping("/delete/purchaseorders")
	public ResponseEntity<BaseDto<Object>> deletePurchaseOrderData(@RequestParam Integer[] purchaseOrderIds) {
		log.info("Request Object for delete is: " + purchaseOrderIds[0]);
		purchaseorderService.deletePurchaseOrdersById(purchaseOrderIds);
		return new BaseDto<>(purchaseorderHelper.getDeletePurchaseOrderMessage(), OK).respond();
	}

	@PutMapping("/update/purchaseorders")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> updatePurchaseOrdersData(@Valid @RequestBody List<PurchaseOrderModel> purchaseorderModel) {
		log.info("Request Object for update is: " , purchaseorderModel);
		List<PurchaseOrderModel> PurchaseOrderModelRes = purchaseorderService.updatePurchaseOrdersData(purchaseorderModel);
		return new BaseDto<>(PurchaseOrderModelRes, purchaseorderHelper.getUpdatePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for save pending purchase order 
	 */
	@PostMapping("/save/pendingpurchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> savePendingPurchaseOrder(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
		purchaseorderModel.setModifiedDate(new Date());
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.savePurchaseOrderData(purchaseorderModel, "PENDING");
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getSavePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for save approved purchase order 
	 */
	@PostMapping("/save/approvedpurchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> saveApprovedPurchaseOrder(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
		//purchaseorderModel.setApprovedDate(new Date());
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.savePurchaseOrderData(purchaseorderModel, "APPROVED");
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getSavePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for save rejected purchase order 
	 */
	@PostMapping("/save/rejectedpurchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> saveRejectedPurchaseOrder(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
		purchaseorderModel.setRejectedDate(new Date());
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.savePurchaseOrderData(purchaseorderModel, "REJECTED");
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getSavePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for save partially purchase order 
	 */
	@PostMapping("/save/partiallypurchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> savePartiallyPurchaseOrder(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.savePurchaseOrderData(purchaseorderModel, "PARTIALLY");
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getSavePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for save completed purchase order 
	 */
	@PostMapping("/save/completedpurchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> saveCompletedPurchaseOrder(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.savePurchaseOrderData(purchaseorderModel, "COMPLETED");
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getSavePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for automatic generation of GRN Number 
	 */
	@GetMapping("/generatepurchaseno")
	public ResponseEntity<BaseDto<String>> generatePurchaseNo(@RequestParam Integer pharmacyId) {
		String pharmacyNm = quotationService.getPharmacyNm(pharmacyId);
		Long purchaseNoCount = purchaseorderService.getPurchaseOrderCount(pharmacyId);
		GenerateGRNNo generateGRNNo = new GenerateGRNNo();
		String purchaseNo = generateGRNNo.generatePONumber(pharmacyNm, purchaseNoCount);
		return new BaseDto<>(purchaseNo, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for Supplier details based on the quotation id
	 */
	@GetMapping("/getsupplierbyquotation")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getSuppliersByQuotation(@RequestParam Integer quotationId) {
		List<SupplierModel> suppliers = purchaseorderService.getSuppliersByQuotationId(quotationId);
		return new BaseDto<>(suppliers, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for item details based on the quotation id and Supplier id
	 */
	@GetMapping("/getitemsbysupplierandquotation")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsBySupplierAndQuotation(@RequestParam Integer quotationId, @RequestParam Integer supplierId) {
		List<ItemSupplierDTO> itemsModels = purchaseorderService.getItemsBySupplierAndQuotation(quotationId, supplierId);
		return new BaseDto<>(itemsModels, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for item details based on the quotation id and Supplier id and itemcode and item name
	 */
	@GetMapping("/searchitemsbysupplierandquotation")
	public ResponseEntity<BaseDto<List<ItemSupplierDTO>>> getItemsBySupplierAndQuotation(@RequestParam Integer quotationId, 
			@RequestParam Integer supplierId, @RequestParam(required=false) String itemCode, 
			@RequestParam(required=false) String itemName) {
		List<ItemSupplierDTO> itemsModels = purchaseorderService.getItemsBySupplierAndQuotation(quotationId, supplierId, itemCode, itemName);
		return new BaseDto<>(itemsModels, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for item details based on the purchase order
	 */
	@GetMapping("/getitemsbypurchaseorder")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getItemsByPurchaseOrder(@RequestParam Integer purchaseOrderId) {
		List<ItemsModel> itemsModels = purchaseorderService.getItemsByPurchaseOrder(purchaseOrderId);
		return new BaseDto<>(itemsModels, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for item details based on the purchase order
	 */
	@GetMapping("/getsupplierbypurchaseorder")
	public ResponseEntity<BaseDto<SupplierModel>> getSupplierByPurchaseOrder(@RequestParam Integer purchaseOrderId) {
		SupplierModel supplierModel = purchaseorderService.getSupplierByPurchaseOrder(purchaseOrderId);
		return new BaseDto<>(supplierModel, propertyHelper.getRetrieveMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for purchase orders based on the pharmacyId
	 */
	@GetMapping("/getpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacy(pharmacyId);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	@GetMapping("/getpurchaseordersbypharmacyandponumber")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getPurchaseOrderByPharmacyAndPONumbr(@RequestParam Integer pharmacyId,@RequestParam String poNumber) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndPONumber(pharmacyId,poNumber);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for pending purchase orders based on the pharmacyId
	 */
	@GetMapping("/getpendingpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getPendingPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "PENDING");
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for approved purchase orders based on the pharmacyId
	 */
	@GetMapping("/getapprovedpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getApprovedPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "APPROVED");
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for rejected purchase orders based on the pharmacyId
	 */
	@GetMapping("/getrejectedpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getRejectedPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "REJECTED");
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for partially purchase orders based on the pharmacyId
	 */
	@GetMapping("/getpartiallypurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getPartiallyPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "PARTIALLY");
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for completed purchase orders based on the pharmacyId
	 */
	@GetMapping("/getcompletedpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getCompletedPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "COMPLETED");
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for sent purchase orders based on the pharmacyId
	 */
	@GetMapping("/getsentpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getSentPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getSentPurchaseOrderByPharmacy(pharmacyId);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for search pending purchase orders based on the pharmacyId
	 */
	@GetMapping("/searchpendingpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> searchPendingPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId, @RequestParam String purchaseOrderNo ) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "PENDING", purchaseOrderNo);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for search approved purchase orders based on the pharmacyId
	 */
	@GetMapping("/searchapprovedpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> searchApprovedPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId, @RequestParam String purchaseOrderNo ) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "APPROVED", purchaseOrderNo);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for search rejected purchase orders based on the pharmacyId
	 */
	@GetMapping("/searchrejectedpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> searchRejectedPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId, @RequestParam String purchaseOrderNo ) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "REJECTED", purchaseOrderNo);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for search partially purchase orders based on the pharmacyId
	 */
	@GetMapping("/searchpartiallypurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> searchPartiallyPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId, @RequestParam String purchaseOrderNo ) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "PARTIALLY", purchaseOrderNo);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for search completed purchase orders based on the pharmacyId
	 */
	@GetMapping("/searchcompletedpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> searchCompletedPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId, @RequestParam String purchaseOrderNo ) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatus(pharmacyId, "COMPLETED", purchaseOrderNo);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is for search sent purchase orders based on the pharmacyId
	 */
	@GetMapping("/searchsentpurchaseordersbypharmacy")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> searchSentPurchaseOrderByPharmacy(@RequestParam Integer pharmacyId, @RequestParam String purchaseOrderNo) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getSentPurchaseOrderByPharmacy(pharmacyId, purchaseOrderNo);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}

	//Purchase Details By Batch No
	@GetMapping("/getbatchnobysearchpdbn")
	public ResponseEntity<BaseDto<List<String>>> findbatchNoInpurchaseorderPDBB(@RequestParam String searchTerm){
		List<String> results=purchaseorderService.findbatchNoInpurchaseorderPDBB(searchTerm);
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	@GetMapping("/getallbatchnopdbn")
	public ResponseEntity<BaseDto<List<String>>> findallPDBB(){
		List<String> results=purchaseorderService.findallPDBB();
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	@GetMapping("/getsuppliersbysearchpdbn")
	public ResponseEntity<BaseDto<List<String>>> findSuppliersInpurchaseorderPDBB(@RequestParam String searchTerm){
		List<String> results=purchaseorderService.findSuppliersInpurchaseorderPDBB(searchTerm);
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	@GetMapping("/getallsupplierspdbn")
	public ResponseEntity<BaseDto<List<String>>> findAllSuppliersPDBB(){
		List<String> results=purchaseorderService.findAllSuppliersPDBB();
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	//Purchase Details By Product Name
	@GetMapping("/getitemsbysearchpdpn")
	public ResponseEntity<BaseDto<List<String>>> finditemNameInpurchaseorderPDBP(@RequestParam String searchTerm){
		List<String> results=purchaseorderService.finditemNameInpurchaseorderPDBP(searchTerm);
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	@GetMapping("/getallitemspdpn")
	public ResponseEntity<BaseDto<List<String>>> findallPDBP(){
		List<String> results=purchaseorderService.findallPDBP();
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	//Purchase Register List
	@GetMapping("/getpaytypesbysearchprl")
	public ResponseEntity<BaseDto<List<String>>> findpaymenttypebysearchPRLT(@RequestParam String searchTerm){
		List<String> results=purchaseorderService.findpaymenttypebysearchPRLT(searchTerm);
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	@GetMapping("/getallpaytypesprl")
	public ResponseEntity<BaseDto<List<String>>> findallpaymenttypesPRLT(){
		List<String> results=purchaseorderService.findallpaymenttypesPRLT();
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	@GetMapping("/getsuppliersbysearchprl")
	public ResponseEntity<BaseDto<List<String>>> findsuppliersbysearchPRLS(@RequestParam String searchTerm){
		List<String> results=purchaseorderService.findsuppliersbysearchPRLS(searchTerm);
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	@GetMapping("/getallsuppliersprl")
	public ResponseEntity<BaseDto<List<String>>> findallsuppliersPRLS(){
		List<String> results=purchaseorderService.findallsuppliersPRLS();
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	//Purhase Order Details By PO NO
	@GetMapping("/getpurNobysearchspdpo")
	public ResponseEntity<BaseDto<List<String>>> findPurNoBySearchPDPO(@RequestParam String searchTerm){
		List<String> results=purchaseorderService.findPurNobysearchPDPO(searchTerm);
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	@GetMapping("/getallpurNoinpdpo")
	public ResponseEntity<BaseDto<List<String>>> findAllPurNoPDPO(){
		List<String> results=purchaseorderService.findallPurNoPDPO();
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}
	@GetMapping("/getsupplierbysearchspdpo")
	public ResponseEntity<BaseDto<List<String>>> findSupplierBySearchPDPO(@RequestParam String searchTerm){
		List<String> results=purchaseorderService.findSupplierbysearchPDPO(searchTerm);
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}

	@GetMapping("/getallsuppliersinpdpo")
	public ResponseEntity<BaseDto<List<String>>> findAllSupplierNamesPDPO(){
		List<String> results=purchaseorderService.findallSuppliersPDPO();
		return new BaseDto<>(results,purchaseorderHelper.getRetrievePurchaseOrderMessage(),OK).respond();
	}
	
	
	@GetMapping("/getapprovedpurchaseordersbypharmacy/withlimit")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getApprovedPurchaseOrderByPharmacyWithLimit(@RequestParam Integer pharmacyId,@RequestParam Integer start,@RequestParam Integer end) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatusLimitedRecords(pharmacyId,start,end, "APPROVED");
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}
	
	@GetMapping("/getrejectedpurchaseordersbypharmacy/withlimit")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getRejectedPurchaseOrderByPharmacyWithLimit(@RequestParam Integer pharmacyId, @RequestParam Integer start, @RequestParam Integer end) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatusForRejected(pharmacyId,start,end, "REJECTED");
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}
	
	@GetMapping("/getsentpurchaseordersbypharmacy/withlimit")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getSentPurchaseOrderByPharmacyWithLimit(@RequestParam Integer pharmacyId, @RequestParam Integer start, @RequestParam Integer end) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getSentPurchaseOrderByPharmacyForSent(pharmacyId, start, end);
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}
	
	@GetMapping("/getpendingpurchaseordersbypharmacy/withlimit")
	public ResponseEntity<BaseDto<List<PurchaseOrderModel>>> getPendingPurchaseOrderByPharmacyWithLimit(@RequestParam Integer pharmacyId, @RequestParam Integer start, @RequestParam Integer end) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderService.getPurchaseOrderByPharmacyAndStatusForPending(pharmacyId, start, end, "PENDING");
		return new BaseDto<>(purchaseOrderModels, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}
	
	
	
	@PostMapping("/sent/sendingByMailPurchaseOrderExcel")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> sendExcelFileAsMailAttachForPO(
			@RequestParam Integer purchaseOrderId,
			@RequestParam("excelFile") MultipartFile file,
			@RequestParam String supplierModelObj) throws IOException, TemplateException {

		PurchaseOrderModel purchaseOrderModel=purchaseorderService.findPurchaseOrderById(purchaseOrderId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

		PharmacyModel pharmacyDetails=pharmacyService.findPharmacyById(purchaseOrderModel.getPharmacyModel().getPharmacyId());

		SupplierModel supplierModel = new SupplierModel();
		ObjectMapper objectMapper = new ObjectMapper();

		supplierModel = objectMapper.readValue(supplierModelObj, SupplierModel.class);

		if(Objects.nonNull(purchaseOrderModel)) {
			
		List<PurchaseOrderItemsModel> poItemsList=purchaseOrderItemsService.findPurchaseOrderItemsByPoId(purchaseOrderModel.getPurchaseOrderId());
			if(Objects.nonNull(supplierModel)) {

				EmployeeModel emp=employeeService.findEmployeeById(Integer.parseInt(purchaseOrderModel.getCreatedUser()));
				SendPurchaseOrderModel mailModel=new SendPurchaseOrderModel();

				mailModel.setFromEmail(env.getProperty("spring.mail.username"));

				mailModel.setSubject("REQUEST FOR PURCHASE ORDER"+" "+pharmacyDetails.getPharmacyName()+"("+dateFormat.format(new Date())+")");
				
				mailModel.setCreatedBy(emp.getFirstName()+" "+emp.getLastName());
				mailModel.setPurchaseOrderNo(purchaseOrderModel.getPurchaseOrderNo());
				mailModel.setDescription(purchaseOrderModel.getRemarks());

				mailModel.setPoItemModel(poItemsList);


				mailModel.setPoDate(purchaseOrderModel.getPurchaseOrderDate().toString());

				mailModel.setPharmacyName(pharmacyDetails.getPharmacyName());
				mailModel.setPharmaAddress1(pharmacyDetails.getAddressLine1());
				mailModel.setPharmaAddress2(pharmacyDetails.getAddressLine2());
				mailModel.setPinNo(pharmacyDetails.getTaxId());
				mailModel.setMobileOne(pharmacyDetails.getPhoneNumber());
				mailModel.setWhatsAppNo(pharmacyDetails.getPhoneNumber());
				mailModel.setBccEmail(pharmacyDetails.getBccEmailId());

				System.out.println("file name"+"Request for po" );
				String FilePath = file.getOriginalFilename();
				File f= new File(FilePath);


				AttachmentModel mail = new AttachmentModel();

				try {

					byte[] b=file.getBytes();
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
					System.out.println(mailModel.getPoItemModel());

					try {

						sendQuotationMailService.sendPurchaseOrderEmail(mailModel,mail);


					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}

				if(Objects.nonNull(supplierModel.getContactPersonEmailID()) &&!ObjectUtils.isEmpty(supplierModel.getContactPersonEmailID())) {
					System.out.println("mail one");
					mailModel.setToEmail(supplierModel.getContactPersonEmailID());
					try {
						sendQuotationMailService.sendPurchaseOrderEmail(mailModel,mail);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(Objects.nonNull(supplierModel.getContactPersonEmailIdTwo()) && !ObjectUtils.isEmpty(supplierModel.getContactPersonEmailIdTwo())) {
					System.out.println("mail two");
					mailModel.setToEmail(supplierModel.getContactPersonEmailIdTwo());
					try {
						sendQuotationMailService.sendPurchaseOrderEmail(mailModel,mail);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(Objects.nonNull(supplierModel.getContactPersonEmailIdThree()) && !ObjectUtils.isEmpty(supplierModel.getContactPersonEmailIdThree())) {
					System.out.println("mail three");
					mailModel.setToEmail(supplierModel.getContactPersonEmailIdThree());
					try {
						sendQuotationMailService.sendPurchaseOrderEmail(mailModel,mail);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(Objects.nonNull(supplierModel.getContactPersonEmailIdFour()) && !ObjectUtils.isEmpty(supplierModel.getContactPersonEmailIdFour())) {

					mailModel.setToEmail(supplierModel.getContactPersonEmailIdFour());
					try {
						sendQuotationMailService.sendPurchaseOrderEmail(mailModel,mail);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}



			}
		}

		
		return new BaseDto<>(purchaseOrderModel, "Mail sent successfully", OK).respond();

	}

}
