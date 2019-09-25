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
import com.ihealthpharm.masters.model.PharmacyRolesModel;
import com.ihealthpharm.masters.service.PharmacyRolesService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class PharmacyRolesController {

	@Autowired
	PharmacyRolesService pharmacyRolesService;
	
	@PostMapping("/save/pharmacyroles")
	public ResponseEntity<BaseDto<PharmacyRolesModel>> insertDistrubutorData(@Valid @RequestBody PharmacyRolesModel pharmacyRolesModel) {
		log.info("Request Object insert is: "+pharmacyRolesModel.toString());
		PharmacyRolesModel pharmacyRolesRes = pharmacyRolesService.savePharmacyRolesData(pharmacyRolesModel);
		return new BaseDto<>(pharmacyRolesRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/pharmacyroles")
	public ResponseEntity<BaseDto<PharmacyRolesModel>> updateDistrubutorData(@Valid @RequestBody PharmacyRolesModel pharmacyRolesModel) {
		log.info("Request Object for update is: "+ pharmacyRolesModel.toString());
		PharmacyRolesModel pharmacyRolesRes = pharmacyRolesService.updatePharmacyRolesData(pharmacyRolesModel);
		return new BaseDto<>(pharmacyRolesRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/pharmacyroles")
	public ResponseEntity<BaseDto<Object>> deleteProviderData(@RequestParam int pharmaAccessId) {
		log.info("Request Object for delete is: ", pharmaAccessId);
		pharmacyRolesService.deletePharmacyRolesData(pharmaAccessId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallpharmacyrolesdata")
	public ResponseEntity<BaseDto<List<PharmacyRolesModel>>> getAllDistributordata() {
		List<PharmacyRolesModel> result = pharmacyRolesService.findAllPharmacyRolesData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getpharmacyrolesdatabyid")
	public ResponseEntity<BaseDto<PharmacyRolesModel>> getProviderDataById(@RequestParam int pharmaAccessId) {
		PharmacyRolesModel result = pharmacyRolesService.findPharmacyRolesDataById(pharmaAccessId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
}
