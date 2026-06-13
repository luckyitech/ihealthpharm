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
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeeSalaryModel;
import com.ihealthpharm.masters.service.EmployeeSalaryService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeeSalaryController {

	@Autowired
	EmployeeSalaryService employeeSalaryService;
	
	@PostMapping("/save/employeesalary")
	public ResponseEntity<BaseDto<EmployeeSalaryModel>> insertEmployeeSalaryData(@Valid @RequestBody EmployeeSalaryModel EmployeeSalaryModel) {
		log.info("Request Object insert is: "+EmployeeSalaryModel.toString());
		EmployeeSalaryModel employeeSalaryRes = employeeSalaryService.saveEmployeeSalaryData(EmployeeSalaryModel);
		return new BaseDto<>(employeeSalaryRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/employeesalary")
	public ResponseEntity<BaseDto<EmployeeSalaryModel>> updateEmployeeSalaryData(@Valid @RequestBody EmployeeSalaryModel EmployeeSalaryModel) {
		log.info("Request Object for update is: "+ EmployeeSalaryModel.toString());
		EmployeeSalaryModel employeeSalaryRes = employeeSalaryService.updateEmployeeSalaryData(EmployeeSalaryModel);
		return new BaseDto<>(employeeSalaryRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employeesalary")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeSalaryData(@RequestParam Integer employeeSalaryId) {
		log.info("Request Object for delete is: ", employeeSalaryId);
		employeeSalaryService.deleteEmployeeSalaryData(employeeSalaryId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemployeesalarydata")
	public ResponseEntity<BaseDto<List<EmployeeSalaryModel>>> getAllEmployeeSalaryData() {
		List<EmployeeSalaryModel> result = employeeSalaryService.findAllEmployeeSalaryData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemployeesalarydatabyid")
	public ResponseEntity<BaseDto<EmployeeSalaryModel>> getEmployeeSalaryDataById(@RequestParam Integer employeeSalaryId) {
		EmployeeSalaryModel result = employeeSalaryService.findEmployeeSalaryDataById(employeeSalaryId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
	@PostMapping("/getemployeesalarydatabyemployeeid")
	public ResponseEntity<BaseDto<EmployeeSalaryModel>> getEmployeeSalaryDataByEmployeeId(@RequestBody EmployeeModel employee) {
		EmployeeSalaryModel result = employeeSalaryService.findEmployeeSalaryDataByEmployeeId(employee);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
}
