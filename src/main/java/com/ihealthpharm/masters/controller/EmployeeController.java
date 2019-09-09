package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.dto.EmployeeDTO;
import com.ihealthpharm.masters.helper.EmployeeHelper;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeeTypeModel;
import com.ihealthpharm.masters.service.EmployeeService;
import com.ihealthpharm.masters.service.EmployeeTypeService;

import lombok.extern.slf4j.Slf4j;
@CrossOrigin
@RestController
@Slf4j
public class EmployeeController {
	String resMessage;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeHelper employeeHelper;
	
	@Autowired
	private EmployeeTypeService employeeTypeService; 

	@PostMapping("/save/employee")
	public ResponseEntity<BaseDto<EmployeeModel>> insertEmployeeData(@Valid @RequestParam("employee") String employeeData, @RequestParam("image") MultipartFile image,
			@RequestParam("identificationDocument") MultipartFile identificationDocument,
			@RequestParam("policeGoodConductCertificate") MultipartFile policeGoodConductCertificate,
			@RequestParam("resume") MultipartFile resume,
			@RequestParam("signedContract") MultipartFile signedContract) throws IOException {
		
		log.info(employeeData);
		log.info("----------------------------------------------------------------------");
		EmployeeModel employeeModel = null;
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			employeeModel.setProfileImage(image.getBytes());
			employeeModel.setIdentificationDocument(identificationDocument.getBytes());
			employeeModel.setPoliceGoodConductCertificate(policeGoodConductCertificate.getBytes());
			employeeModel.setResume(resume.getBytes());
			employeeModel.setSignedContract(signedContract.getBytes());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		//log.info("Request Object insert is: " + employeeDto.toString());

		EmployeeModel employeeRes = employeeService.saveEmployeeData(employeeModel);

		return new BaseDto<>(employeeRes, employeeHelper.getSaveEmployeeMessage(), OK).respond();
	}

	@PutMapping("/update/employees")
	public ResponseEntity<BaseDto<List<EmployeeModel>>> updateEmployeesData(@Valid @RequestBody List<EmployeeDTO> employeeDtos) {
		log.info("Request Object for update is: "+ employeeDtos.toString());
		List<EmployeeModel> employeeRes = employeeService.updateEmployeesData(employeeDtos);
		return new BaseDto<>(employeeRes, employeeHelper.getUpdateEmployeeMessage(), OK).respond();
	}

	@PutMapping("/update/employee")
	public ResponseEntity<BaseDto<EmployeeModel>> updateEmployeeData(@Valid @RequestParam("employee") String employeeData, @RequestParam("image") MultipartFile image,
			@RequestParam("identificationDocument") MultipartFile identificationDocument,
			@RequestParam("policeGoodConductCertificate") MultipartFile policeGoodConductCertificate,
			@RequestParam("resume") MultipartFile resume,
			@RequestParam("signedContract") MultipartFile signedContract) throws IOException {
		
		log.info(employeeData);
		log.info("----------------------------------------------------------------------");
		EmployeeModel employeeModel = null;
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			employeeModel.setProfileImage(image.getBytes());
			employeeModel.setIdentificationDocument(identificationDocument.getBytes());
			employeeModel.setPoliceGoodConductCertificate(policeGoodConductCertificate.getBytes());
			employeeModel.setResume(resume.getBytes());
			employeeModel.setSignedContract(signedContract.getBytes());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		EmployeeModel employeeRes = employeeService.updateEmployeeData(employeeModel);
		return new BaseDto<>(employeeRes, employeeHelper.getUpdateEmployeeMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/employee")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeData(@RequestParam int employeeId) {
		log.info("Request Object for delete is: "+ employeeId);
		employeeService.deleteEmployeeById(employeeId);
		return new BaseDto<>(employeeHelper.getDeleteEmployeeMessage(), OK).respond();
	}

	@DeleteMapping("/delete/employees")
	public ResponseEntity<BaseDto<Object>> deleteEmployeesData(@RequestParam int[] employeeIds) {
		log.info("Request Object for delete is: "+ employeeIds);
		employeeService.deleteEmployeesById(employeeIds);
		return new BaseDto<>(employeeHelper.getDeleteEmployeeMessage(), OK).respond();
	}
	
	@GetMapping("/getallemployeesdata")
	public ResponseEntity<BaseDto<List<EmployeeModel>>> getEmployeesdata() {
		List<EmployeeModel> result = employeeService.findAllEmployees();
		return new BaseDto<>(result, employeeHelper.getRetrieveEmployeeMessage(), OK).respond();
	}

	@GetMapping("/getemployeedatabyid")
	public ResponseEntity<BaseDto<EmployeeModel>> getEmployeeDataById(@RequestParam int employeeId) {
		EmployeeModel result = employeeService.findEmployeeById(employeeId);
		return new BaseDto<>(result, employeeHelper.getRetrieveEmployeeMessage(), OK).respond();
	}
	
	@GetMapping("/getallemployeetypedata")
	public ResponseEntity<BaseDto<List<EmployeeTypeModel>>> getEmployeeTypedata() {
		List<EmployeeTypeModel> result = employeeTypeService.getAllEmployeeTypes();
		return new BaseDto<>(result, employeeHelper.getRetrieveEmployeeMessage(), OK).respond();
	}

}
