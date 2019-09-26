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
import com.ihealthpharm.masters.model.EmployeePharmacyRoleModel;
import com.ihealthpharm.masters.service.EmployeePharmacyRoleService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeePharmacyRolesController {

	@Autowired
	EmployeePharmacyRoleService employeePharmacyRoleService;
	
	@PostMapping("/save/employeepharmacyrole")
	public ResponseEntity<BaseDto<EmployeePharmacyRoleModel>> insertDistrubutorData(@Valid @RequestBody EmployeePharmacyRoleModel employeePharmacyRoleModel) {
		log.info("Request Object insert is: "+employeePharmacyRoleModel.toString());
		EmployeePharmacyRoleModel pharmacyRolesRes = employeePharmacyRoleService.saveEmployeePharmacyRoleData(employeePharmacyRoleModel);
		return new BaseDto<>(pharmacyRolesRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/employeepharmacyrole")
	public ResponseEntity<BaseDto<EmployeePharmacyRoleModel>> updateDistrubutorData(@Valid @RequestBody EmployeePharmacyRoleModel employeePharmacyRoleModel) {
		log.info("Request Object for update is: "+ employeePharmacyRoleModel.toString());
		EmployeePharmacyRoleModel pharmacyRolesRes = employeePharmacyRoleService.updateEmployeePharmacyRoleData(employeePharmacyRoleModel);
		return new BaseDto<>(pharmacyRolesRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employeepharmacyrole")
	public ResponseEntity<BaseDto<Object>> deleteProviderData(@RequestParam int pharmaAccessId) {
		log.info("Request Object for delete is: ", pharmaAccessId);
		employeePharmacyRoleService.deleteEmployeePharmacyRoleData(pharmaAccessId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemployeepharmacyroledata")
	public ResponseEntity<BaseDto<List<EmployeePharmacyRoleModel>>> getAllDistributordata() {
		List<EmployeePharmacyRoleModel> result = employeePharmacyRoleService.findAllEmployeePharmacyRoleData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemployeepharmacyroledatabyid")
	public ResponseEntity<BaseDto<EmployeePharmacyRoleModel>> getProviderDataById(@RequestParam int pharmaAccessId) {
		EmployeePharmacyRoleModel result = employeePharmacyRoleService.findEmployeePharmacyRoleDataById(pharmaAccessId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
}
