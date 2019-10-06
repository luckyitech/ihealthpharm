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
import com.ihealthpharm.masters.model.EmployeeEducationModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.EmployeeEducationService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeeEducationController {

	@Autowired
	EmployeeEducationService employeeEducationService;
	
	@PostMapping("/save/employeeeducation")
	public ResponseEntity<BaseDto<EmployeeEducationModel>> insertEmployeeEducationData(@Valid @RequestBody EmployeeEducationModel employeeEducationModel) {
		log.info("Request Object insert is: "+employeeEducationModel.toString());
		EmployeeEducationModel employeeEducationRes = employeeEducationService.saveEmployeeEducationData(employeeEducationModel);
		return new BaseDto<>(employeeEducationRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/employeeeducation")
	public ResponseEntity<BaseDto<EmployeeEducationModel>> updateEmployeeEducationData(@Valid @RequestBody EmployeeEducationModel employeeEducationModel) {
		log.info("Request Object for update is: "+ employeeEducationModel.toString());
		EmployeeEducationModel employeeEducationRes = employeeEducationService.updateEmployeeEducationData(employeeEducationModel);
		return new BaseDto<>(employeeEducationRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employeeeducation")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeEducationData(@RequestParam int employeeEducationId) {
		log.info("Request Object for delete is: ", employeeEducationId);
		employeeEducationService.deleteEmployeeEmployeeEducationData(employeeEducationId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemployeeeducationdata")
	public ResponseEntity<BaseDto<List<EmployeeEducationModel>>> getAllEmployeeEducationdata() {
		List<EmployeeEducationModel> result = employeeEducationService.findAllEmployeeEducationData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemployeeeducationdatabyid")
	public ResponseEntity<BaseDto<EmployeeEducationModel>> getEmployeeEducationDataById(@RequestParam int employeeEducationId) {
		EmployeeEducationModel result = employeeEducationService.findEmployeeEducationDataById(employeeEducationId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
	@PostMapping("/getemployeeeducationdatabyemployeeid")
	public ResponseEntity<BaseDto<EmployeeEducationModel>> getEmployeeEducationDataByEmployeeId(@RequestBody EmployeeModel employeeModel) {
		EmployeeEducationModel result = employeeEducationService.findEmployeeEducationDataByEmployee(employeeModel);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
}
