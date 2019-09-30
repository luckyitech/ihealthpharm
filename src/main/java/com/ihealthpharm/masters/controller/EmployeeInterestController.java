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
import com.ihealthpharm.masters.model.EmployeeInterestModel;
import com.ihealthpharm.masters.service.EmployeeInterestService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeeInterestController {

	@Autowired
	EmployeeInterestService employeeHonorService;
	
	@PostMapping("/save/employeeinterest")
	public ResponseEntity<BaseDto<EmployeeInterestModel>> insertDistrubutorData(@Valid @RequestBody EmployeeInterestModel EmployeeInterestModel) {
		log.info("Request Object insert is: "+EmployeeInterestModel.toString());
		EmployeeInterestModel employeeHonorRes = employeeHonorService.saveEmployeeInterestData(EmployeeInterestModel);
		return new BaseDto<>(employeeHonorRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/employeeinterest")
	public ResponseEntity<BaseDto<EmployeeInterestModel>> updateDistrubutorData(@Valid @RequestBody EmployeeInterestModel EmployeeInterestModel) {
		log.info("Request Object for update is: "+ EmployeeInterestModel.toString());
		EmployeeInterestModel employeeHonorRes = employeeHonorService.updateEmployeeInterestData(EmployeeInterestModel);
		return new BaseDto<>(employeeHonorRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employeeinterest")
	public ResponseEntity<BaseDto<Object>> deleteProviderData(@RequestParam int employeeHonorId) {
		log.info("Request Object for delete is: ", employeeHonorId);
		employeeHonorService.deleteEmployeeInterestData(employeeHonorId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemployeeinterestdata")
	public ResponseEntity<BaseDto<List<EmployeeInterestModel>>> getAllDistributordata() {
		List<EmployeeInterestModel> result = employeeHonorService.findAllEmployeeInterestData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemployeeinterestdatabyid")
	public ResponseEntity<BaseDto<EmployeeInterestModel>> getProviderDataById(@RequestParam int employeeHonorId) {
		EmployeeInterestModel result = employeeHonorService.findEmployeeInterestDataById(employeeHonorId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
}
