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
import com.ihealthpharm.masters.model.EmploymentHistoryModel;
import com.ihealthpharm.masters.service.EmploymentHistoryService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmploymentHistoryController {

	@Autowired
	EmploymentHistoryService employmentHistoryService;
	
	@PostMapping("/save/employmenthistory")
	public ResponseEntity<BaseDto<EmploymentHistoryModel>> insertEmploymentHistoryData(@Valid @RequestBody EmploymentHistoryModel EmploymentHistoryModel) {
		log.info("Request Object insert is: "+EmploymentHistoryModel.toString());
		EmploymentHistoryModel employmentHistoryRes = employmentHistoryService.saveEmploymentHistoryData(EmploymentHistoryModel);
		return new BaseDto<>(employmentHistoryRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/employmenthistory")
	public ResponseEntity<BaseDto<EmploymentHistoryModel>> updateEmploymentHistoryData(@Valid @RequestBody EmploymentHistoryModel EmploymentHistoryModel) {
		log.info("Request Object for update is: "+ EmploymentHistoryModel.toString());
		EmploymentHistoryModel employmentHistoryRes = employmentHistoryService.updateEmploymentHistoryData(EmploymentHistoryModel);
		return new BaseDto<>(employmentHistoryRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employmenthistory")
	public ResponseEntity<BaseDto<Object>> deleteEmploymentHistoryData(@RequestParam Integer employmentHistoryId) {
		log.info("Request Object for delete is: ", employmentHistoryId);
		employmentHistoryService.deleteEmploymentHistoryData(employmentHistoryId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemploymenthistorydata")
	public ResponseEntity<BaseDto<List<EmploymentHistoryModel>>> getAllEmploymentHistoryData() {
		List<EmploymentHistoryModel> result = employmentHistoryService.findAllEmploymentHistoryData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemploymenthistorydatabyid")
	public ResponseEntity<BaseDto<EmploymentHistoryModel>> getEmploymentHistoryDataById(@RequestParam Integer employmentHistoryId) {
		EmploymentHistoryModel result = employmentHistoryService.findEmploymentHistoryDataById(employmentHistoryId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
	@PostMapping("/getemploymenthistorydatabyemployeeid")
	public ResponseEntity<BaseDto<EmploymentHistoryModel>> getEmploymentHistoryDataByEmployeeId(@RequestBody EmployeeModel employee) {
		EmploymentHistoryModel result = employmentHistoryService.findEmploymentHistoryDataByEmployeeId(employee);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
}
