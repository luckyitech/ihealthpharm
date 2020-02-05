package com.ihealthpharm.masters.controller;

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
import com.ihealthpharm.masters.helper.SupplierHelper;
import com.ihealthpharm.masters.model.ReturnCreditTypeModel;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.masters.service.ReturnCreditTypeService;
import com.ihealthpharm.masters.service.SupplierService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class SupplierController {
	String resMessage;

	@Autowired
	private SupplierService supplierService ;

	@Autowired
	private ReturnCreditTypeService returnCreditTypeService;

	@Autowired
	private SupplierHelper supplierHelper;

	@PostMapping("/save/supplier")
	public ResponseEntity<BaseDto<SupplierModel>> insertSupplierData(@Valid @RequestBody SupplierModel supplierModel) {
		log.info("Request Object insert is: "+supplierModel.toString());
		SupplierModel distrubutorRes = supplierService.saveSupplierData(supplierModel);
		return new BaseDto<>(distrubutorRes,supplierHelper.getSaveSupplierMessage(),OK).respond();
	}

	@PutMapping("/update/supplier")
	public ResponseEntity<BaseDto<SupplierModel>> updateSupplierData(@Valid @RequestBody SupplierModel supplierModel) {
		log.info("Request Object for update is: "+ supplierModel.toString());
		SupplierModel distrubutorRes = supplierService.updateSupplierData(supplierModel);
		return new BaseDto<>(distrubutorRes,supplierHelper.getUpdateSupplierMessage(),OK).respond();
	}

	@PutMapping("/update/suppliers")
	public ResponseEntity<BaseDto<List<SupplierModel>>> updateSuppliersData(@Valid @RequestBody List<SupplierModel> supplierModels) {
		log.info("Request Object for update is: "+ supplierModels.toString());
		List<SupplierModel> distrubutorRes = supplierService.updateSuppliersData(supplierModels);
		return new BaseDto<>(distrubutorRes,supplierHelper.getUpdateSupplierMessage(),OK).respond();
	}

	@DeleteMapping("/delete/supplier")
	public ResponseEntity<BaseDto<Object>> deleteSupplierData(@RequestParam Integer supplierId) {
		log.info("Request Object for delete is: ", supplierId);
		supplierService.deleteSupplierById(supplierId);
		return new BaseDto<>(supplierHelper.getDeleteSupplierMessage(), OK).respond();
	}


	@DeleteMapping("/delete/suppliers")
	public ResponseEntity<BaseDto<Object>> deleteSupplierData(@RequestParam Integer[] supplierIds) {
		log.info("Request Object for delete is: "+ supplierIds.toString());
		supplierService.deleteSuppliersById(supplierIds);
		return new BaseDto<>(supplierHelper.getDeleteSupplierMessage(), OK).respond();
	}

	@GetMapping("/getactivesuppliersdata")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getActiveSupplierData() {
		List<SupplierModel> result = supplierService.findSupplierByActive();
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}

	@GetMapping("/getallsuppliersdata") 
	public ResponseEntity<BaseDto<List<SupplierModel>>> getAllSupplierdata() {
		List<SupplierModel> result = supplierService.findAllSuppliers();
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
	
	@GetMapping("/getallsuppliersdata/foritemsuppliers")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getAllActiveSuppliers(){
		List<SupplierModel> response=supplierService.findAllActiveSuppliers();
		return new BaseDto<>(response, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
	
	
	@GetMapping("/getlimitedsuppliersdata") 
	public ResponseEntity<BaseDto<List<SupplierModel>>> getLimitedSupplierdata() {
		List<SupplierModel> result = supplierService.findLimitedSuppliers();
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
	
	@GetMapping("/getall/limitedsuppliersdata")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getLimitedSuppliersData(@RequestParam Integer start,@RequestParam Integer end){
		List<SupplierModel> result=supplierService.findLimitedSuppliersData(start,end);
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
	
	

	@GetMapping("/getsupplierdatabyid")
	public ResponseEntity<BaseDto<SupplierModel>> getSupplierDataById(@RequestParam Integer supplierId) {
		SupplierModel result = supplierService.findSupplierById(supplierId);
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}

	@GetMapping("/getreturntredittype")
	public ResponseEntity<BaseDto<List<ReturnCreditTypeModel>>> getReturnCreditType() {
		List<ReturnCreditTypeModel> result = returnCreditTypeService.findAllReturnCreditTypes();
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}

	//searchTerm based on supplierName

	@GetMapping("/getallSuppliers/byName")
	public ResponseEntity<BaseDto<List<SupplierModel>>>  getAllSuppliersBasedOnName(@RequestParam String searchTerm){
		List<SupplierModel> results=supplierService.findAllSuppliersByName(searchTerm);
		return new BaseDto<>(results,supplierHelper.getRetrieveSupplierMessage(),OK).respond();
	}
	
	@GetMapping("/getsuppliersdatabyname")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getSuppliersDataByName(@RequestParam("key") String name) {
		List<SupplierModel> result = supplierService.findSuppliersByName(name);
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}

	@GetMapping("/getsuppliersdatabysearch")
	public ResponseEntity<BaseDto<List<SupplierModel>>> getSuppliersDataBySearch(@RequestParam("key") String name,
			@RequestParam("pageNumber") Integer pageNumber,@RequestParam("limit") Integer pageSize) {
		List<SupplierModel> result = supplierService.findSuppliersBySearch(name,pageNumber,pageSize);
		log.info(result.toString());
		return new BaseDto<>(result, supplierHelper.getRetrieveSupplierMessage(), OK).respond();
	}
}
