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
import com.ihealthpharm.masters.helper.PharmacyHelper;
import com.ihealthpharm.masters.model.PharmacyBranchModel;
import com.ihealthpharm.masters.service.PharmacyBranchService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin 
@Slf4j
public class PharmacyBranchController {

	@Autowired
	private PharmacyBranchService branchServiceImpl;
	
	@Autowired
	private PharmacyHelper helper;
	
	
	@PostMapping("/savepharmacy/branch")
	public ResponseEntity<BaseDto<PharmacyBranchModel>> addBranch(@Valid @RequestBody PharmacyBranchModel pharmacyBranchModel){
		
          log.info("Request Object for insert is: "+ pharmacyBranchModel);
		PharmacyBranchModel branchModelRes = branchServiceImpl.savePharmacyBranch(pharmacyBranchModel);
		return new BaseDto<>(branchModelRes,helper.getSavePharmacyBranchMessage(),OK).respond();
		
	}
	

	@PutMapping("/updatepharmacy/branch")
	public ResponseEntity<BaseDto<PharmacyBranchModel>> updateBranch(@Valid @RequestBody PharmacyBranchModel pharmacyBranchModel){
		
		log.info("Request Object for update is: ",pharmacyBranchModel);
		PharmacyBranchModel branchModelRes = branchServiceImpl.updateBranch(pharmacyBranchModel);
		return new BaseDto<>(branchModelRes,helper.getUpdatePharmacyBranchMessage(),OK).respond();
		
	}
	
	

	@PutMapping("/multiplebranches/update")
	public ResponseEntity<BaseDto<List<PharmacyBranchModel>>> updateMultipleBranches(@Valid @RequestBody List<PharmacyBranchModel> pharmacyBranchModels){
		log.info("Request Object for update is: "+ pharmacyBranchModels);
		List<PharmacyBranchModel> branchRes = branchServiceImpl.updateMultipleBranches(pharmacyBranchModels);
		return new BaseDto<>(branchRes,helper.getUpdatePharmacyBranchMessage(),OK).respond();
		
	}
	
	@DeleteMapping("/deletepharmacybranch")
	public ResponseEntity<BaseDto<Object>> deleteBranch(@RequestParam Integer pharmacyBranchId){
		log.info("Request Object for update is: "+ pharmacyBranchId);
		branchServiceImpl.deletePharmacyBranch(pharmacyBranchId); 
		return new BaseDto<>(helper.getDeletePharmacyBranchMessage(),OK).respond();
	}
	
	@GetMapping("/getallbranchesdata")
	public ResponseEntity<BaseDto<List<PharmacyBranchModel>>> getAllBranchesdata() {
		List<PharmacyBranchModel> result = branchServiceImpl.findAllBranches();
		return new BaseDto<>(result, helper.getRetrievePharmacyBranchMessage(), OK).respond();
	}
	
	@GetMapping("/getbranchdatabyid")
	public ResponseEntity<BaseDto<PharmacyBranchModel>> getBranchDataById(@RequestParam Integer pharmacyBranchId) {
		PharmacyBranchModel result = branchServiceImpl.findByPharmacyId(pharmacyBranchId);
		return new BaseDto<>(result, helper.getRetrievePharmacyBranchMessage(), OK).respond();
	}
	
}
