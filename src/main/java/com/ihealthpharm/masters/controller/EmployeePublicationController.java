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
import com.ihealthpharm.masters.model.EmployeePublicationModel;
import com.ihealthpharm.masters.service.EmployeePublicationService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeePublicationController {

	@Autowired
	EmployeePublicationService employeePublicationService;
	
	@PostMapping("/save/employeepublication")
	public ResponseEntity<BaseDto<EmployeePublicationModel>> insertEmployeePublicationData(@Valid @RequestBody EmployeePublicationModel EmployeePublicationModel) {
		log.info("Request Object insert is: "+EmployeePublicationModel.toString());
		EmployeePublicationModel employeePublicationRes = employeePublicationService.saveEmployeePublicationData(EmployeePublicationModel);
		return new BaseDto<>(employeePublicationRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/employeepublication")
	public ResponseEntity<BaseDto<EmployeePublicationModel>> updateEmployeePublicationData(@Valid @RequestBody EmployeePublicationModel EmployeePublicationModel) {
		log.info("Request Object for update is: "+ EmployeePublicationModel.toString());
		EmployeePublicationModel employeePublicationRes = employeePublicationService.updateEmployeePublicationData(EmployeePublicationModel);
		return new BaseDto<>(employeePublicationRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employeepublication")
	public ResponseEntity<BaseDto<Object>> deleteProviderData(@RequestParam int employeeEducationId) {
		log.info("Request Object for delete is: ", employeeEducationId);
		employeePublicationService.deleteEmployeePublicationData(employeeEducationId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemployeepublicationdata")
	public ResponseEntity<BaseDto<List<EmployeePublicationModel>>> getAllEmployeePublicationData() {
		List<EmployeePublicationModel> result = employeePublicationService.findAllEmployeePublicationData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemployeepublicationdatabyid")
	public ResponseEntity<BaseDto<EmployeePublicationModel>> getEmployeePublicationDataById(@RequestParam int employeeEducationId) {
		EmployeePublicationModel result = employeePublicationService.findEmployeePublicationDataById(employeeEducationId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
}
