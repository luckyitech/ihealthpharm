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
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.dto.StockItemsListDTO;
import com.ihealthpharm.stock.helper.StockHelper;
import com.ihealthpharm.stock.model.InvoiceModel;
import com.ihealthpharm.stock.model.StockModel;
import com.ihealthpharm.stock.service.StockService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gunasekhar 
 * Controller is used to perform the crud operations of Stock
 */
@RestController
@CrossOrigin
@Slf4j
public class StockController {
	
	@Autowired
	private StockHelper stockHelper;
	
	@Autowired
	private StockService stockService;

	/**
	 * @author Gunasekhar 
	 * Service is to save the stock
	 */
	@PostMapping("/save/stock")
	public ResponseEntity<BaseDto<StockModel>> saveStock(@Valid @RequestBody StockModel stockModel) {
		log.info("Request Object insert is: "+ stockModel.toString());
		StockModel model = stockService.saveStock(stockModel);
		return new BaseDto<>(model, stockHelper.getSaveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Jagadeesh 
	 * Service is to save the stock
	 */
	@PostMapping("/save/multiplestocks")
	public ResponseEntity<BaseDto<List<StockModel>>> saveStock(@Valid @RequestBody List<StockModel> stockModel) {
		log.info("Request Object insert is: "+ stockModel.toString());
		List<StockModel> model = stockService.saveStock(stockModel);
		return new BaseDto<>(model, stockHelper.getSaveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the stock
	 */
	@PutMapping("/update/stock")
	public ResponseEntity<BaseDto<StockModel>> updateStock(@Valid @RequestBody StockModel stockModel) {
		log.info("Request Object for update is: ",stockModel.toString());
		StockModel model = stockService.updateStock(stockModel);
		return new BaseDto<>(model, stockHelper.getUpdateStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to update the stocks
	 */
	@PutMapping("/update/multiplestocks")
	public ResponseEntity<BaseDto<List<StockModel>>> updateStocks(@Valid @RequestBody List<StockModel> stockModels) {
		log.info("Request Object for update is: "+ stockModels.toString());
		List<StockModel> models = stockService.updateStocks(stockModels);
		return new BaseDto<>(models, stockHelper.getUpdateStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete the stock
	 */
	@DeleteMapping("/delete/stock")
	public ResponseEntity<BaseDto<Object>> deleteStock(@RequestParam Integer stockId) {
		log.info("Request Object for delete is: ", stockId);
		stockService.deleteStockById(stockId);
		return new BaseDto<>(stockHelper.getDeleteStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to delete the stocks
	 */
	@DeleteMapping("/delete/multiplestocks")
	public ResponseEntity<BaseDto<Object>> deleteStocks(@RequestParam Integer[] stockIds) {
		log.info("Request Object for delete is: "+ stockIds.toString());
		stockService.deleteStockByTds(stockIds);
		return new BaseDto<>(stockHelper.getDeleteStockMessage(), OK).respond();
	}

	/**
	 * @author Gunasekhar 
	 * Service is to get all the stocks
	 * 
	 */
	@GetMapping("/getallstocks")
	public ResponseEntity<BaseDto<List<StockModel>>> getAllStocks() {
		List<StockModel> stockModels = stockService.findAllStocks();
		return new BaseDto<>(stockModels, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Gunasekhar 
	 * Service is to get the stock
	 */
	@GetMapping("/getstockbyid")
	public ResponseEntity<BaseDto<StockModel>> getStockById(@RequestParam Integer stockId) {
		StockModel result = stockService.findStockById(stockId);
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	
	/**
	 * @author Jagadeesh 
	 * Service is to get the stock
	 */
	@GetMapping("/getallstockitems")
	public ResponseEntity<BaseDto<List<ItemsModel>>> getAllStockItems() {
		List<ItemsModel> result = stockService.findAllStockItems();
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Jagadeesh 
	 * Service is to get the stock
	 */
	@PostMapping("/getitembatchnumbers")
	public ResponseEntity<BaseDto<List<String>>> getItemBatchNumberByItemId(@RequestBody ItemsModel item) {
		List<String> result = stockService.getBatchNumbersByItemId(item);
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Jagadeesh 
	 * Service is to get the stock
	 */
	@GetMapping("/getstockbyitemandbatch")
	public ResponseEntity<BaseDto<StockModel>> getStockByItemAndBatch(@RequestParam Integer itemId, @RequestParam String batchNo) {
		ItemsModel item = new ItemsModel();
		item.setItemId(itemId);
		StockModel result = stockService.getStockByItemAndBatchNumber(item,batchNo );
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Jagadeesh 
	 * Service is to get the stock
	 */
	@PostMapping("/getstockbyitem")
	public ResponseEntity<BaseDto<List<StockModel>>> getStockByItem(@RequestBody ItemsModel item) {
		
		List<StockModel> result = stockService.findByItem(item);
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Jagadeesh 
	 * Service is to get the stock
	 */
	@PostMapping("/getstockbyitemnamesearch")
	public ResponseEntity<BaseDto<List<StockModel>>> getStockByItemName(@RequestBody ItemsModel itemName) {
		
		List<StockModel> result = stockService.findByItemName(itemName);
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	/**
	 * @author Jagadeesh 
	 * Service is to get the stock
	 */
	@PostMapping("/getstockbyitemandpharmacy")
	public ResponseEntity<BaseDto<List<StockModel>>> getStockByItemNameAndPharmacy(@RequestBody StockItemsListDTO stockDto){
		List<StockModel> result = stockService.findByItemAndPharmacy(stockDto.getListOfItems(),stockDto.getPharmacy());
	
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	

	@GetMapping("/getstockbyitemandpharmacyid")
	public ResponseEntity<BaseDto<List<StockModel>>> getStockByItemNameAndPharmacy(@RequestParam String searchTerm,@RequestParam String searchCode, @RequestParam Integer pharmacyId,
			@RequestParam Integer pageNumber,@RequestParam Integer pageSize){
		List<StockModel> result = stockService.findByItemAndPharmacy(searchTerm,searchCode,pharmacyId,pageNumber,pageSize);
		
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	@GetMapping("/getstockbyitemandpharmacyidcount")
	public ResponseEntity<BaseDto<Integer>> getStockByItemNameAndPharmacyCount(@RequestParam String searchTerm,@RequestParam String searchCode, @RequestParam Integer pharmacyId){
		Integer result = stockService.findByItemAndPharmacyCount(searchTerm,searchCode,pharmacyId);
		
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	//to update stock Quantity 
	@GetMapping("/getstocksdata/basedonbillid")
		public ResponseEntity<BaseDto<StockModel>> getStockDataBasedOnItem(@RequestParam Integer itemId){
		StockModel result = stockService.findStocksByBillId(itemId);
		return new BaseDto<>(result, stockHelper.getRetrieveStockMessage(), OK).respond();
	}
	
	@GetMapping("/getsuppliersbysearchpol")
	public ResponseEntity<BaseDto<List<String>>> getSuppliersByStock(@RequestParam String searchTerm){
		List<String> results=stockService.findSuppliersByStock(searchTerm);
		return new BaseDto<>(results,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}
	@GetMapping("/getallsupplierspol")
	public ResponseEntity<BaseDto<List<String>>> getAllSuppliersByStock(){
		List<String> results=stockService.findAllSuppliersByStock();
		return new BaseDto<>(results,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}
	
	@GetMapping("/getmanufacturerbysearchpol")
	public ResponseEntity<BaseDto<List<String>>> getManufacturerNamesByStock(@RequestParam String searchTerm){
		List<String> results=stockService.findManufacturerByStock(searchTerm);
		return new BaseDto<>(results,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}
	@GetMapping("/getallmanufacturerpol")
	public ResponseEntity<BaseDto<List<String>>> getAllManufacturerByStock(){
		List<String> results=stockService.findAllManufacturerByStock();
		return new BaseDto<>(results,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}
	@GetMapping("/getinvoicedatesbysearchpol")
	public ResponseEntity<BaseDto<List<String>>> getInvoiceDatesByStock(@RequestParam String searchTerm){
		List<String> results=stockService.findInvoiceDatesByStock(searchTerm);
		return new BaseDto<>(results,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}
	@GetMapping("/getallinvoicedatespol")
	public ResponseEntity<BaseDto<List<String>>> getAllInvoiceDatesByStock(){
		List<String> results=stockService.findAllInvoiceDatesByStock();
		return new BaseDto<>(results,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}

	//get batch no's by search
	@GetMapping("/getallbatchnosbysearch")
	public ResponseEntity<BaseDto<List<StockModel>>> getAllBatchNo(@RequestParam String searchTerm){
		List<StockModel> results=stockService.findAllByBatchNo(searchTerm);
		return new BaseDto<>(results,stockHelper.getRetrieveStockMessage(),OK).respond();
	}
	
	//Supplier By MFR List
	@GetMapping("/getsuppliersbysearchssbml")
	public ResponseEntity<BaseDto<List<String>>> findSupplierbynameInStockSBML(@RequestParam String searchTerm){
		List<String> results=stockService.findSuppliersByStock(searchTerm);
		return new BaseDto<>(results,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}
	@GetMapping("/getallsupplierssbml")
	public ResponseEntity<BaseDto<List<String>>> findallSBML(){
		List<String> results=stockService.findallSBML();
		return new BaseDto<>(results,stockHelper.getRetrieveStockAdjustmentMessage(),OK).respond();
	}
	@GetMapping("/ProfitPercentage")
	public ResponseEntity<BaseDto<List>> getAllProfitController(){
		List result=stockService.findProfitService();
		return new BaseDto<>(result,stockHelper.getRetrieveStockMessage(),OK).respond();
	}
	@GetMapping("/suppliersRevenue")
	public ResponseEntity<BaseDto<List>> getSuppliersRevenue(){
		List result=stockService.findSuppliersRevenue();
		return new BaseDto<>(result,stockHelper.getRetrieveStockMessage(),OK).respond();
	}
	

}
