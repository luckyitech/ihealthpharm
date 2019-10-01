package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.model.EmployeeAccessModel;
import com.ihealthpharm.masters.service.EmployeeAccessService;

@CrossOrigin
@RestController

public class EmployeeAccessController {

	
	@Autowired
	EmployeeAccessService employeeAccessService;
	
	@PostMapping("/save/employeeaccess")
	public ResponseEntity<BaseDto<EmployeeAccessModel>> insertEmployeeAccessData(@Valid @RequestBody EmployeeAccessModel employeeAccessModel) {
		
		EmployeeAccessModel employeeAccessRes = employeeAccessService.saveEmployeeAccessData(employeeAccessModel);
		return new BaseDto<>(employeeAccessRes,"Employee Access",OK).respond();
	}
}
