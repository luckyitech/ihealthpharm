package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.PharmacyHelper;
import com.ihealthpharm.masters.model.PharmacyBranchModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.PharmacyStockModel;
import com.ihealthpharm.masters.service.PharmacyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private PharmacyHelper pharmacyHelper;
	
	
	@PostMapping("/save/pharmacy")
	public ResponseEntity<BaseDto<PharmacyModel>> insertPharmacyData(@Valid @RequestBody PharmacyModel pharmacyModel){
		log.info("Request Object insert is :",pharmacyModel);
		PharmacyModel pharmacyModelRes=pharmacyService.savePharmacyData(pharmacyModel);
		return new BaseDto<>(pharmacyModelRes,pharmacyHelper.getSavePharmacyMessage(),OK).respond();
	}
	
	@PutMapping("/update/pharmacy")
	public ResponseEntity<BaseDto<PharmacyModel>> updatePharmacyData(@Valid @RequestBody PharmacyModel pharmacyModel){
		log.info("Request Object for update is: ", pharmacyModel);
		PharmacyModel pharmacyModelRes = pharmacyService.updatePharmacyData(pharmacyModel);
		return new BaseDto<>(pharmacyModelRes,pharmacyHelper.getUpdatePharmacyMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/pharmacy")
	public ResponseEntity<BaseDto<Object>> deletePharmacyData(@RequestParam int pharmacyId){
		log.info("Request Object for delete is: ", pharmacyId);
		pharmacyService.deletePharmacyById(pharmacyId);
		return new BaseDto<> (pharmacyHelper.getDeletePharmacyMessage(), OK).respond();
	}
	
	@GetMapping("/getactivepharmaciesdata")
	public ResponseEntity<BaseDto<List<PharmacyModel>>> getActivePharmaciesData(){
		List<PharmacyModel> result=pharmacyService.findPharmacyByActive();
		return new BaseDto<>(result,pharmacyHelper.getRetrievePharmacyMessage(),OK).respond();
	}
	
	@GetMapping("/getpharmacybyid")
	public ResponseEntity<BaseDto<PharmacyModel>> getPharmacyById(@RequestParam int pharmacyId){
		PharmacyModel result=pharmacyService.findPharmacyById(pharmacyId);
		return new BaseDto<>(result,pharmacyHelper.getRetrievePharmacyMessage(),OK).respond();
	}
	
//	 Branch CRUD API's
	
	@PostMapping("/add/pharmacy/branch")
	public ResponseEntity<BaseDto<PharmacyBranchModel>> addBranch(@Valid @RequestBody PharmacyBranchModel pharmacyBranchModel){
		log.info("Adding a new Branch for Pharmacy Id = "+ pharmacyBranchModel.getPharmacy().getPharmacyId() + "Branch req= "+pharmacyBranchModel);
		PharmacyBranchModel pharmacyBranchRes=pharmacyService.saveAddBranch(pharmacyBranchModel); 
		return new BaseDto<>(pharmacyBranchRes,pharmacyHelper.getSavePharmacyBranchMessage(),OK).respond();
	}
	

	@PutMapping("/update/pharmacy/branch")
	public ResponseEntity<BaseDto<PharmacyBranchModel>> updateBranch(@Valid @RequestBody PharmacyBranchModel pharmacyBranchModel){
		log.info("Updating Branch  req= "+pharmacyBranchModel);
		PharmacyBranchModel pharmacyBranchRes=pharmacyService.updateBranch(pharmacyBranchModel); 
		return new BaseDto<>(pharmacyBranchRes,pharmacyHelper.getUpdatePharmacyBranchMessage(),OK).respond();
	}
	
	@PutMapping("/update/multiple/pharmacy/branch")
	public ResponseEntity<BaseDto<List<PharmacyBranchModel>>> updateMultipleBranches(@Valid @RequestBody List<PharmacyBranchModel> pharmacyBranchModel){
		log.info("Updating Multiple branches  size= "+pharmacyBranchModel.size());
		List<PharmacyBranchModel> pharmacyBranchRes=pharmacyService.updateMultipleBranches(pharmacyBranchModel); 
		return new BaseDto<>(pharmacyBranchRes,pharmacyHelper.getUpdatePharmacyBranchMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/pharmacy/{pharmacyBranchId}/branch")
	public ResponseEntity<BaseDto<String>> deleteBranch(@PathVariable int pharmacyBranchId){
		log.info("deleting a branche  id= "+pharmacyBranchId);
		pharmacyService.delete(pharmacyBranchId); 
		return new BaseDto<String>(pharmacyHelper.getDeletePharmacyBranchMessage(),OK).respond();
	}
	
	@GetMapping("/get/pharmacy/{pharmacyId}/branches")
	public ResponseEntity<BaseDto<List<PharmacyBranchModel>>> findByPharmacy(@PathVariable int pharmacyId){
		log.info("Find Branches by pharmacy id = "+pharmacyId);
		List<PharmacyBranchModel> pharmacyBranchList = pharmacyService.findByPharmacyId(pharmacyId); 
		return new BaseDto<>(pharmacyBranchList, pharmacyHelper.getRetrievePharmacyBranchMessage(),OK).respond();
	} 
	
//	Pharmacy Stock API's: 
	
	@PostMapping("/add/pharmacy/branch/stock")
	public ResponseEntity<BaseDto<PharmacyStockModel>> addStock(@Valid @RequestBody PharmacyStockModel pharmacyStockModel){
		log.info("New Stock request for Branch = "+ pharmacyStockModel.getPharmacyBranch().getPharmacyBranchId() + "Stock req= "+pharmacyStockModel);
		PharmacyStockModel pharmacyStockRes=pharmacyService.addStock(pharmacyStockModel); 
		return new BaseDto<>(pharmacyStockRes,pharmacyHelper.getSaveStockResponse(),OK).respond();
	}
	
	@PutMapping("/update/pharmacy/branch/stock")
	public ResponseEntity<BaseDto<PharmacyStockModel>> updateStock(@Valid @RequestBody PharmacyStockModel pharmacyStockModel){
		log.info("Updating Stock  req= "+pharmacyStockModel);
		PharmacyStockModel pharmacyStockRes=pharmacyService.updateStock(pharmacyStockModel); 
		return new BaseDto<>(pharmacyStockRes,pharmacyHelper.getUpdateStockMessage(),OK).respond();
	}
	
	@PutMapping("/update/multiple/pharmacy/stocks")
	public ResponseEntity<BaseDto<List<PharmacyStockModel>>> updateStocks(@Valid @RequestBody List<PharmacyStockModel> pharmacyStockModels){
		log.info("Updating Multiple Stocks  size= "+pharmacyStockModels.size());
		List<PharmacyStockModel> pharmacyStocksRes=pharmacyService.updateStocks(pharmacyStockModels); 
		return new BaseDto<>(pharmacyStocksRes,pharmacyHelper.getUpdateStockMessage(),OK).respond();
	} 
	

	@DeleteMapping("/delete/pharmacy/{stockId}/branch")
	public ResponseEntity<BaseDto<String>> deleteStock(@PathVariable int stockId){
		log.info("deleting  Stock  id= "+stockId);
		pharmacyService.deleteStock(stockId); 
		return new BaseDto<String>(pharmacyHelper.getDeleteStockMessage(),OK).respond();
	}

	@GetMapping("/get/pharmacy/branch/{branchId}/stocks")
	public ResponseEntity<BaseDto<List<PharmacyStockModel>>> findStocksByPharmacy(@PathVariable int branchId){
		log.info("Find Stocks by Branche id = "+branchId);
		List<PharmacyStockModel> pharmacyStockList = pharmacyService.findByBranchId(branchId); 
		return new BaseDto<>(pharmacyStockList, pharmacyHelper.getRetrieveStockMessage(),OK).respond();
	} 
	
	@GetMapping("/find/pharmacy/{stockId}/stocks")
	public ResponseEntity<BaseDto<PharmacyStockModel>> findByStockId(@PathVariable int stockId){
		log.info("Find By Stock  id = "+stockId);
		PharmacyStockModel pharmacyStock = pharmacyService.findStockById(stockId); 
		return new BaseDto<>(pharmacyStock, pharmacyHelper.getRetrieveStockMessage(),OK).respond();
	} 

}
