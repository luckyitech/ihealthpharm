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
	EmployeeInterestService employeeInterestService;
	
	@PostMapping("/save/employeeinterest")
	public ResponseEntity<BaseDto<EmployeeInterestModel>> insertEmployeeInterestData(@Valid @RequestBody EmployeeInterestModel EmployeeInterestModel) {
		log.info("Request Object insert is: "+EmployeeInterestModel.toString());
		EmployeeInterestModel employeeInterestRes = employeeInterestService.saveEmployeeInterestData(EmployeeInterestModel);
		return new BaseDto<>(employeeInterestRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/employeeinterest")
	public ResponseEntity<BaseDto<EmployeeInterestModel>> updateEmployeeInterestData(@Valid @RequestBody EmployeeInterestModel EmployeeInterestModel) {
		log.info("Request Object for update is: "+ EmployeeInterestModel.toString());
		EmployeeInterestModel employeeInterestRes = employeeInterestService.updateEmployeeInterestData(EmployeeInterestModel);
		return new BaseDto<>(employeeInterestRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employeeinterest")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeInterestData(@RequestParam int employeeInterestId) {
		log.info("Request Object for delete is: ", employeeInterestId);
		employeeInterestService.deleteEmployeeInterestData(employeeInterestId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemployeeinterestdata")
	public ResponseEntity<BaseDto<List<EmployeeInterestModel>>> getAllEmployeeInterestData() {
		List<EmployeeInterestModel> result = employeeInterestService.findAllEmployeeInterestData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemployeeinterestdatabyid")
	public ResponseEntity<BaseDto<EmployeeInterestModel>> getEmployeeInterestDataById(@RequestParam int employeeInterestId) {
		EmployeeInterestModel result = employeeInterestService.findEmployeeInterestDataById(employeeInterestId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
}
