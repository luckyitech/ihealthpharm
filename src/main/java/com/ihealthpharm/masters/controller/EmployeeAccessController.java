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
import com.ihealthpharm.masters.dto.EmployeeAccessDTO;
import com.ihealthpharm.masters.model.EmployeeAccessModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.service.EmployeeAccessService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeeAccessController {

	
	@Autowired
	EmployeeAccessService employeeAccessService;
	
	@PostMapping("/save/employeeaccess")
	public ResponseEntity<BaseDto<EmployeeAccessModel>> insertEmployeeAccessData(@Valid @RequestBody EmployeeAccessDTO employeeAccessDto) {
		log.info(employeeAccessDto.toString());
		EmployeeAccessModel employeeAccessRes =employeeAccessService.saveEmployeeAccessData(employeeAccessDto);
		return new BaseDto<>(employeeAccessRes,"Employee Access",OK).respond();
	}
	
	@PutMapping("/update/employeeaccess")
	public ResponseEntity<BaseDto<EmployeeAccessModel>> updateEmployeeAccessData(@Valid @RequestBody EmployeeAccessDTO employeeAccessDto) {
		log.info("Request Object for update is: "+ employeeAccessDto.toString());
		EmployeeAccessModel employeeAccessRes = employeeAccessService.updateEmployeeAccessData(employeeAccessDto);
		return new BaseDto<>(employeeAccessRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employeeaccess")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeAccessData(@RequestParam Integer employeeAccessId) {
		log.info("Request Object for delete is: ", employeeAccessId);
		employeeAccessService.deleteEmployeeAccessData(employeeAccessId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemployeeaccessdata")
	public ResponseEntity<BaseDto<List<EmployeeAccessModel>>> getAllEmployeeAccessdata() {
		List<EmployeeAccessModel> result = employeeAccessService.findAllEmployeeAccessData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemployeeaccessdatabyid")
	public ResponseEntity<BaseDto<EmployeeAccessModel>> getEmployeeAccessDataById(@RequestParam Integer employeeAccessId) {
		EmployeeAccessModel result = employeeAccessService.findEmployeeAccessDataById(employeeAccessId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
	@PostMapping("/getemployeeaccessdatabyemployeeid")
	public ResponseEntity<BaseDto<List<EmployeeAccessModel>>> getEmployeeAccessDataByEmployeeId(@RequestBody EmployeeModel employeeModel) {
		List<EmployeeAccessModel> result = employeeAccessService.findByEmployee(employeeModel);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
}
