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
import com.ihealthpharm.stock.helper.PurchaseReturnItemHelper;
import com.ihealthpharm.stock.model.PurchaseReturnItemModel;
import com.ihealthpharm.stock.service.PurchaseReturnItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class PurchaseReturnItemController {

	@Autowired
	PurchaseReturnItemService purchaseReturnItemService;

	@Autowired
	PurchaseReturnItemHelper purchaseReturnItemHelper;

	@PostMapping("/save/purchaseReturnItem")
	public ResponseEntity<BaseDto<PurchaseReturnItemModel>> insertPurchaseReturnItemsData(@Valid @RequestBody PurchaseReturnItemModel PurchaseReturnItemModel) {
		PurchaseReturnItemModel purchaseReturnItemModelRes = purchaseReturnItemService.savePurchaseReturnItemData(PurchaseReturnItemModel);
		return new BaseDto<>(purchaseReturnItemModelRes, purchaseReturnItemHelper.getSavePurchaseReturnItemMessage(), OK).respond();
	}
	
	@PostMapping("/save/purchaseReturnItems")
	public ResponseEntity<BaseDto<List<PurchaseReturnItemModel>>> insertPurchaseReturnItems(@Valid @RequestBody List<PurchaseReturnItemModel> PurchaseReturnItemsModel) {
		List<PurchaseReturnItemModel> purchaseReturnItemsRes = purchaseReturnItemService.savePurchaseReturnItems(PurchaseReturnItemsModel);
		return new BaseDto<>(purchaseReturnItemsRes, purchaseReturnItemHelper.getSavePurchaseReturnItemMessage(), OK).respond();
	}

	@PutMapping("/update/purchaseReturnItem")
	public ResponseEntity<BaseDto<PurchaseReturnItemModel>> updatePurchaseReturnItemsData(@Valid @RequestBody PurchaseReturnItemModel PurchaseReturnItemModel) {
		log.info("Request Object for update is: ", PurchaseReturnItemModel);
		PurchaseReturnItemModel purchaseReturnItemModelRes = purchaseReturnItemService.updatePurchaseReturnItemData(PurchaseReturnItemModel);
		return new BaseDto<>(purchaseReturnItemModelRes, purchaseReturnItemHelper.getUpdatePurchaseReturnItemMessage(), OK).respond();
	}

	@DeleteMapping("/delete/purchaseReturnItem")
	public ResponseEntity<BaseDto<Object>> deletePurchaseReturnItemsData(@RequestParam Integer purchaseReturnItemsId) {
		log.info("Request Object for delete is: ", purchaseReturnItemsId);
		purchaseReturnItemService.deletePurchaseReturnItemsById(purchaseReturnItemsId);
		return new BaseDto<>(purchaseReturnItemHelper.getDeletePurchaseReturnItemMessage(), OK).respond();
	}

	@GetMapping("/getPurchaseReturnItemsdata")
	public ResponseEntity<BaseDto<List<PurchaseReturnItemModel>>> getPurchaseReturnItemsData() {
		List<PurchaseReturnItemModel> result = purchaseReturnItemService.findAllPurchaseReturnItems();
		return new BaseDto<>(result, purchaseReturnItemHelper.getRetrievePurchaseReturnItemMessage(), OK).respond();
	}

	@GetMapping("/getPurchaseReturnItemsDataById")
	public ResponseEntity<BaseDto<PurchaseReturnItemModel>> getPurchaseReturnItemsDataById(@RequestParam Integer purchaseReturnItemsId) {
		PurchaseReturnItemModel result = purchaseReturnItemService.findPurchaseReturnItemsById(purchaseReturnItemsId);
		return new BaseDto<>(result, purchaseReturnItemHelper.getRetrievePurchaseReturnItemMessage(), OK).respond();
	}

	@DeleteMapping("/delete/purchaseReturnItems")
	public ResponseEntity<BaseDto<Object>> deletePurchaseReturnItemsData(@RequestParam Integer[] purchaseReturnIds) {

		log.info("Request Object for delete is: " + purchaseReturnIds[0]);
		purchaseReturnItemService.deletePurchaseReturnItemsById(purchaseReturnIds);
		return new BaseDto<>(purchaseReturnItemHelper.getDeletePurchaseReturnItemMessage(), OK).respond();
	}

	@PutMapping("/update/purchaseReturnItems")
	public ResponseEntity<BaseDto<List<PurchaseReturnItemModel>>> updatePurchaseReturnItemssData(@Valid @RequestBody List<PurchaseReturnItemModel> PurchaseReturnItemModel) {
		log.info("Request Object for update is: " , PurchaseReturnItemModel);
		List<PurchaseReturnItemModel> purchaseReturnItemModelRes = purchaseReturnItemService.updatePurchaseReturnItemsData(PurchaseReturnItemModel);
		return new BaseDto<>(purchaseReturnItemModelRes, purchaseReturnItemHelper.getUpdatePurchaseReturnItemMessage(), OK).respond();
	}
	//Purchase Return
	@GetMapping("/getgrnnosbysearchpurret")
	public ResponseEntity<BaseDto<List<String>>> getGRNNoBySearchPR(@RequestParam String searchTerm){
		List<String> results=purchaseReturnItemService.findGRNNoByPR(searchTerm);
		return new BaseDto<>(results,purchaseReturnItemHelper.getRetrievePurchaseReturnItemMessage(),OK).respond();
	}
	
	@GetMapping("/getallgrnnospurret")
	public ResponseEntity<BaseDto<List<String>>> getAllGRNNoByPR(){
		List<String> results=purchaseReturnItemService.findAllGRNNoByPR();
		return new BaseDto<>(results,purchaseReturnItemHelper.getRetrievePurchaseReturnItemMessage(),OK).respond();
	}
	@GetMapping("/getsuppliersbysearchpurret")
	public ResponseEntity<BaseDto<List<String>>> getSupplierBySearchPR(@RequestParam String searchTerm){
		List<String> results=purchaseReturnItemService.findSupplierBySearchPR(searchTerm);
		return new BaseDto<>(results,purchaseReturnItemHelper.getRetrievePurchaseReturnItemMessage(),OK).respond();
	}
	
	@GetMapping("/getallsupplierspurret")
	public ResponseEntity<BaseDto<List<String>>> getAllSuppliersByPR(){
		List<String> results=purchaseReturnItemService.findAllSuppliersByPR();
		return new BaseDto<>(results,purchaseReturnItemHelper.getRetrievePurchaseReturnItemMessage(),OK).respond();
	}
	
	@GetMapping("/getinvoicenosbysearchpurret")
	public ResponseEntity<BaseDto<List<String>>> getInvoiceNoBySearchPR(@RequestParam String searchTerm){
		List<String> results=purchaseReturnItemService.findInvoiceNoBySearchPR(searchTerm);
		return new BaseDto<>(results,purchaseReturnItemHelper.getRetrievePurchaseReturnItemMessage(),OK).respond();
	}
	
	@GetMapping("/getallinvoicenospurret")
	public ResponseEntity<BaseDto<List<String>>> getAllInvoiceNoByPR(){
		List<String> results=purchaseReturnItemService.findAllInvoiceNoByPR();
		return new BaseDto<>(results,purchaseReturnItemHelper.getRetrievePurchaseReturnItemMessage(),OK).respond();
	}
	
	@GetMapping("/getreturnQty/basedon/invoiceIdandItemId")
	public ResponseEntity<BaseDto<Integer>> getallsalesReturnQtyByItemId(@RequestParam Integer itemId,@RequestParam Integer invoiceId ){
		Integer results=purchaseReturnItemService.getReturnQtyByItemId(itemId,invoiceId);
		return new BaseDto<>(results,purchaseReturnItemHelper.getRetrievePurchaseReturnItemMessage(),OK).respond();
	}
}
