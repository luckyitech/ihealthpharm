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
import com.ihealthpharm.masters.helper.DistributorHelper;
import com.ihealthpharm.masters.helper.ItemPropertyHelper;
import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.helper.PurchaseOrderHelper;
import com.ihealthpharm.stock.model.PurchaseOrderModel;
import com.ihealthpharm.stock.service.PurchaseOrderService;
import com.ihealthpharm.stock.service.QuotationService;
import com.ihealthpharm.stock.utils.GenerateGRNNo;

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
	DistributorHelper distributorHelper;
	
	@Autowired
	private ItemPropertyHelper propertyHelper;
	
	@Autowired
	private QuotationService quotationService;

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
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.savePurchaseOrderData(purchaseorderModel, "PENDING");
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getSavePurchaseOrderMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for save approved purchase order 
	 */
	@PostMapping("/save/approvedpurchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> saveApprovedPurchaseOrder(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
		PurchaseOrderModel purchaseorderModelRes = purchaseorderService.savePurchaseOrderData(purchaseorderModel, "APPROVED");
		return new BaseDto<>(purchaseorderModelRes, purchaseorderHelper.getSavePurchaseOrderMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for save rejected purchase order 
	 */
	@PostMapping("/save/rejectedpurchaseorder")
	public ResponseEntity<BaseDto<PurchaseOrderModel>> saveRejectedPurchaseOrder(@Valid @RequestBody PurchaseOrderModel purchaseorderModel) {
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
		String purchaseNo = generateGRNNo.generateGNR(pharmacyNm, purchaseNoCount);
		return new BaseDto<>(purchaseNo, purchaseorderHelper.getRetrievePurchaseOrderMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for distributor details based on the quotation id
	 */
	@GetMapping("/getdistributorbyquotation")
	public ResponseEntity<BaseDto<List<DistributorModel>>> getDistributorsByQuotation(@RequestParam Integer quotationId) {
		List<DistributorModel> distributors = purchaseorderService.getDistributorsByQuotationId(quotationId);
		return new BaseDto<>(distributors, distributorHelper.getRetrieveDistrubutorMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is for item details based on the quotation id and distributor id
	 */
	@GetMapping("/getitemsbydistributorandquotation")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getItemsByDistributorAndQuotation(@RequestParam Integer quotationId, @RequestParam Integer distributorId) {
		List<ItemsModel> itemsModels = purchaseorderService.getItemsByDistributorAndQuotation(quotationId, distributorId);
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
	@GetMapping("/getdistributorbypurchaseorder")
	public ResponseEntity<BaseDto<DistributorModel>> getDistributorByPurchaseOrder(@RequestParam Integer purchaseOrderId) {
		DistributorModel distributorModel = purchaseorderService.getDistributorByPurchaseOrder(purchaseOrderId);
		return new BaseDto<>(distributorModel, propertyHelper.getRetrieveMessage(), OK).respond();
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
}
