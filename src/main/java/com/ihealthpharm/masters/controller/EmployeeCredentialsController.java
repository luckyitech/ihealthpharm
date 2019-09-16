package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.Date;
import java.util.List;

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
import com.ihealthpharm.masters.helper.EmployeeCredentialsHelper;
import com.ihealthpharm.masters.model.EmployeeCredentialsModel;
import com.ihealthpharm.masters.service.EmployeeCredentialsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class EmployeeCredentialsController {

	@Autowired
	EmployeeCredentialsHelper employeeCredentialsHelper;

	@Autowired
	EmployeeCredentialsService employeeCredentialsService;

	@PostMapping("save/employeecredentials")
	public ResponseEntity<BaseDto<EmployeeCredentialsModel>> saveEmployeeCredentials(
			@RequestBody EmployeeCredentialsModel employeeCredentialsModel) {
		employeeCredentialsModel = employeeCredentialsService.saveEmployeeCredentialsData(employeeCredentialsModel);
		log.info(employeeCredentialsHelper.getSaveEmployeeCredentialsMessage() + " With Id:"
				+ employeeCredentialsModel.getEmployeeCredentialsId());
		return new BaseDto<>(employeeCredentialsModel, employeeCredentialsHelper.getSaveEmployeeCredentialsMessage(),
				OK).respond();
	}

	@PutMapping("update/employeecredential")
	public ResponseEntity<BaseDto<EmployeeCredentialsModel>> updateEmployeeCredential(
			@RequestBody EmployeeCredentialsModel employeeCredentialsModel) {
		employeeCredentialsModel = employeeCredentialsService.updateEmployeeCredentialsData(employeeCredentialsModel);
		log.info(employeeCredentialsHelper.getUpdateEmployeeCredentialsMessage() + " With Id:"
				+ employeeCredentialsModel.getEmployeeCredentialsId());
		return new BaseDto<>(employeeCredentialsModel, employeeCredentialsHelper.getUpdateEmployeeCredentialsMessage(),
				OK).respond();
	}

	@PutMapping("update/employeecredentials")
	public ResponseEntity<BaseDto<List<EmployeeCredentialsModel>>> updateEmployeeCredentials(
			@RequestBody List<EmployeeCredentialsModel> employeeCredentialsModels) {
		log.info("Employee Credentials : " + employeeCredentialsModels);
		employeeCredentialsModels = employeeCredentialsService.updateEmployeeCredentialsData(employeeCredentialsModels);
		return new BaseDto<>(employeeCredentialsModels, employeeCredentialsHelper.getUpdateEmployeeCredentialsMessage(),
				OK).respond();
	}

	@DeleteMapping("delete/employeecredential")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeCredential(@RequestParam int employeeCredentialId) {
		employeeCredentialsService.deleteEmployeeCredentialsById(employeeCredentialId);
		log.info(employeeCredentialsHelper.getDeleteEmployeeCredentialsMessage() + " With Id:" + employeeCredentialId);
		return new BaseDto<>(employeeCredentialsHelper.getDeleteEmployeeCredentialsMessage(), OK).respond();
	}

	@DeleteMapping("delete/employeecredentials")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeCredentials(
			@RequestParam("employeeCredentialIds") int[] employeeCredentialIds) {
		employeeCredentialsService.deleteEmployeesCredentialsById(employeeCredentialIds);
		return new BaseDto<>(employeeCredentialsHelper.getDeleteEmployeeCredentialsMessage(), OK).respond();
	}

	@GetMapping("getemployeecredentialsbyid")
	public ResponseEntity<BaseDto<EmployeeCredentialsModel>> getEmployeeCredentialById(
			@RequestParam("employeeCredentialId") int employeeCredentialId) {
		EmployeeCredentialsModel employeeCredentialsModel = employeeCredentialsService
				.findEmployeeCredentialsById(employeeCredentialId);
		return new BaseDto<>(employeeCredentialsModel, employeeCredentialsHelper.getDeleteEmployeeCredentialsMessage(),
				OK).respond();
	}

	@GetMapping("getallemployeecredentials")
	public ResponseEntity<BaseDto<List<EmployeeCredentialsModel>>> getAllEmployeeCredentials() {
		List<EmployeeCredentialsModel> employeeCredentialsModels = employeeCredentialsService
				.findAllEmployeeCredentials();
		return new BaseDto<>(employeeCredentialsModels, employeeCredentialsHelper.getDeleteEmployeeCredentialsMessage(),
				OK).respond();
	}

	@PostMapping("employeelogin")
	public ResponseEntity<BaseDto<EmployeeCredentialsModel>> checkEmployeeCredentials(
			@RequestParam("userName") String userName, @RequestParam("currentPassword") String currentPassword)
			throws Exception {

		EmployeeCredentialsModel employeeCredentialsModels = employeeCredentialsService.findEmployeeCredentialsByUserNameAndPassword(userName, currentPassword);
		String token = Jwts.builder().setSubject(employeeCredentialsModels.getUserName()).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
	   System.out.println(token);
		return new BaseDto<>(employeeCredentialsModels, employeeCredentialsHelper.getDeleteEmployeeCredentialsMessage(),
				OK).respond();
	}
}
