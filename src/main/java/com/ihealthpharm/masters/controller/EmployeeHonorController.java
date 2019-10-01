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
import com.ihealthpharm.masters.model.EmployeeHonorModel;
import com.ihealthpharm.masters.service.EmployeeHonorService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeeHonorController {

	@Autowired
	EmployeeHonorService employeeHonorService;
	
	@PostMapping("/save/employeehonor")
	public ResponseEntity<BaseDto<EmployeeHonorModel>> insertEmployeeHonorData(@Valid @RequestBody EmployeeHonorModel EmployeeHonorModel) {
		log.info("Request Object insert is: "+EmployeeHonorModel.toString());
		EmployeeHonorModel employeeHonorRes = employeeHonorService.saveEmployeeHonorData(EmployeeHonorModel);
		return new BaseDto<>(employeeHonorRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/employeehonor")
	public ResponseEntity<BaseDto<EmployeeHonorModel>> updateEmployeeHonorData(@Valid @RequestBody EmployeeHonorModel EmployeeHonorModel) {
		log.info("Request Object for update is: "+ EmployeeHonorModel.toString());
		EmployeeHonorModel employeeHonorRes = employeeHonorService.updateEmployeeHonorData(EmployeeHonorModel);
		return new BaseDto<>(employeeHonorRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employeehonor")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeHonorData(@RequestParam int employeeHonorId) {
		log.info("Request Object for delete is: ", employeeHonorId);
		employeeHonorService.deleteEmployeeHonorData(employeeHonorId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemployeehonordata")
	public ResponseEntity<BaseDto<List<EmployeeHonorModel>>> getAllEmployeeHonordata() {
		List<EmployeeHonorModel> result = employeeHonorService.findAllEmployeeHonorData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemployeehonordatabyid")
	public ResponseEntity<BaseDto<EmployeeHonorModel>> getEmployeeHonorDataById(@RequestParam int employeeHonorId) {
		EmployeeHonorModel result = employeeHonorService.findEmployeeHonorDataById(employeeHonorId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
}
