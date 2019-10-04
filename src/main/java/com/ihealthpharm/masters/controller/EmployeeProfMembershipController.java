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
import com.ihealthpharm.masters.model.EmployeeProfMembershipModel;
import com.ihealthpharm.masters.service.EmployeeProfMembershipService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeeProfMembershipController {

	@Autowired
	EmployeeProfMembershipService employeeProfMembershipService;
	
	@PostMapping("/save/employeeprofmembership")
	public ResponseEntity<BaseDto<EmployeeProfMembershipModel>> insertEmployeeProfMembershipData(@Valid @RequestBody EmployeeProfMembershipModel EmployeeProfMembershipModel) {
		log.info("Request Object insert is: "+EmployeeProfMembershipModel.toString());
		EmployeeProfMembershipModel employeeProfMembershipRes = employeeProfMembershipService.saveEmployeeProfMembershipData(EmployeeProfMembershipModel);
		return new BaseDto<>(employeeProfMembershipRes,"saved",OK).respond();
	}
	
	@PutMapping("/update/employeeprofmembership")
	public ResponseEntity<BaseDto<EmployeeProfMembershipModel>> updateEmployeeProfMembershipData(@Valid @RequestBody EmployeeProfMembershipModel EmployeeProfMembershipModel) {
		log.info("Request Object for update is: "+ EmployeeProfMembershipModel.toString());
		EmployeeProfMembershipModel employeeProfMembershipRes = employeeProfMembershipService.updateEmployeeProfMembershipData(EmployeeProfMembershipModel);
		return new BaseDto<>(employeeProfMembershipRes,"updated",OK).respond();
	}
	
	@DeleteMapping("/delete/employeeprofmembership")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeProfMembershipData(@RequestParam int employeeProfMembershipId) {
		log.info("Request Object for delete is: ", employeeProfMembershipId);
		employeeProfMembershipService.deleteEmployeeEmployeeProfMembershipData(employeeProfMembershipId);;
		return new BaseDto<>("deleted", OK).respond();
	}
	
	@GetMapping("/getallemployeeprofmembershipdata")
	public ResponseEntity<BaseDto<List<EmployeeProfMembershipModel>>> getAllEmployeeProfMembershipData() {
		List<EmployeeProfMembershipModel> result = employeeProfMembershipService.findAllEmployeeProfMembershipData();
		return new BaseDto<>(result, "retrived", OK).respond();
	}
	
	@GetMapping("/getemployeeprofmembershipdatabyid")
	public ResponseEntity<BaseDto<EmployeeProfMembershipModel>> getEmployeeProfMembershipDataById(@RequestParam int employeeProfMembershipId) {
		EmployeeProfMembershipModel result = employeeProfMembershipService.findEmployeeProfMembershipDataById(employeeProfMembershipId);
		return new BaseDto<>(result,  "retrived", OK).respond();
	}
	
}
