package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

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
import com.ihealthpharm.masters.dto.EmployeeNameAndAcessDTO;
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
	public ResponseEntity<BaseDto<EmployeeModel>> insertEmployeeData(@Valid @RequestParam("employee") String employeeData ) throws IOException {

		EmployeeModel employeeModel = null;
		log.info(employeeData);
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmployeeModel employeeRes = employeeService.saveEmployeeData(employeeModel);
		return new BaseDto<>(employeeRes, employeeHelper.getSaveEmployeeMessage(), OK).respond();
	}

	@PutMapping("/save/employeewithprofileimage")
	public ResponseEntity<BaseDto<EmployeeModel>> insertEmployeeDataWithImage(@Valid @RequestParam("employee") String employeeData, 
			@RequestParam("image") MultipartFile image) throws IOException {
		EmployeeModel employeeModel = null;

		log.info(employeeData);
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			employeeModel.setProfileImage(image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmployeeModel employeeRes = employeeService.saveEmployeeData(employeeModel);
		return new BaseDto<>(employeeRes, employeeHelper.getSaveEmployeeMessage(), OK).respond();
	}

	@PutMapping("/save/employeewithidentificationdocument")
	public ResponseEntity<BaseDto<EmployeeModel>> insertEmployeeDataWithIdentificationDocument(@Valid @RequestParam("employee") String employeeData, 
			@RequestParam("identificationDocument") MultipartFile identificationDocument) throws IOException {
		EmployeeModel employeeModel = null;
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			employeeModel.setIdentificationDocument(identificationDocument.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmployeeModel employeeRes = employeeService.saveEmployeeData(employeeModel);
		return new BaseDto<>(employeeRes, employeeHelper.getSaveEmployeeMessage(), OK).respond();
	}

	@PutMapping("/save/employeewithpolicegoodconductcertificate")
	public ResponseEntity<BaseDto<EmployeeModel>> insertEmployeeDataWithPoliceGoodConductCertificate(@Valid @RequestParam("employee") String employeeData, 
			@RequestParam("policeGoodConductCertificate") MultipartFile policeGoodConductCertificate) throws IOException {
		EmployeeModel employeeModel = null;
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			employeeModel.setPoliceGoodConductCertificate(policeGoodConductCertificate.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmployeeModel employeeRes = employeeService.saveEmployeeData(employeeModel);
		return new BaseDto<>(employeeRes, employeeHelper.getSaveEmployeeMessage(), OK).respond();
	}

	@PutMapping("/save/employeewithresume")
	public ResponseEntity<BaseDto<EmployeeModel>> insertEmployeeDataWithResume(@Valid @RequestParam("employee") String employeeData, 
			@RequestParam("resume") MultipartFile resume) throws IOException {
		EmployeeModel employeeModel = null;
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			employeeModel.setResume(resume.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmployeeModel employeeRes = employeeService.saveEmployeeData(employeeModel);
		return new BaseDto<>(employeeRes, employeeHelper.getSaveEmployeeMessage(), OK).respond();
	}

	@PutMapping("/save/employeewithsignedcontract")
	public ResponseEntity<BaseDto<EmployeeModel>> insertEmployeeDataWithSignedContract(@Valid @RequestParam("employee") String employeeData, 
			@RequestParam("signedContract") MultipartFile signedContract) throws IOException {
		EmployeeModel employeeModel = null;
		log.info(employeeData);
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			employeeModel.setSignedContract(signedContract.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmployeeModel employeeRes = employeeService.saveEmployeeData(employeeModel);
		return new BaseDto<>(employeeRes, employeeHelper.getSaveEmployeeMessage(), OK).respond();
	}

	@PutMapping("/update/employees")
	public ResponseEntity<BaseDto<List<EmployeeModel>>> updateEmployeesData(@Valid @RequestBody List<EmployeeModel> EmployeeModels) throws ParseException {
		log.info("Request Object for update is: "+ EmployeeModels.toString());
		List<EmployeeModel> employeeRes = employeeService.updateEmployeesData(EmployeeModels);
		return new BaseDto<>(employeeRes, employeeHelper.getUpdateEmployeeMessage(), OK).respond();
	}

	@PutMapping("/update/employee")
	public ResponseEntity<BaseDto<EmployeeModel>> updateEmployeeData(@Valid @RequestBody EmployeeModel employeeData) throws IOException {

		EmployeeModel employeeRes = employeeService.updateEmployeeData(employeeData);
		return new BaseDto<>(employeeRes, employeeHelper.getUpdateEmployeeMessage(), OK).respond();
	}


	@PutMapping("update/employee/image")
	public ResponseEntity<BaseDto<EmployeeModel>> updateEmployeeDataWithoutImage(@Valid @RequestBody EmployeeModel employeeData){
		log.info("Request Object for Update :"+employeeData);
		EmployeeModel empModel=employeeService.updateEmployeeData(employeeData);
		return new BaseDto<>(empModel,employeeHelper.getUpdateEmployeeMessage(),OK).respond();
	}
	
	@PutMapping("/update/employee/withimages")
	public ResponseEntity<BaseDto<EmployeeModel>> updateEmployeeData(@Valid @RequestParam("employee") String employeeData, 
@RequestParam(value="image",required=false) MultipartFile image,
			@RequestParam(value="identificationDocument",required=false) MultipartFile identificationDocument,
			@RequestParam(value="policeGoodConductCertificate",required=false) MultipartFile policeGoodConductCertificate,
			@RequestParam(value="resume",required=false) MultipartFile resume,
			@RequestParam(value="signedContract",required=false) MultipartFile signedContract) throws IOException {
		
		EmployeeModel employeeModel = null;
		try {
			employeeModel = new ObjectMapper().readValue(employeeData, EmployeeModel.class);
			if (Objects.nonNull(image)) {
				employeeModel.setProfileImage(image.getBytes());
			}
			if (Objects.nonNull(identificationDocument)) {
			employeeModel.setIdentificationDocument(identificationDocument.getBytes());
			}
			if (Objects.nonNull(policeGoodConductCertificate)) {
			employeeModel.setPoliceGoodConductCertificate(policeGoodConductCertificate.getBytes());
			}
			if (Objects.nonNull(resume)) {
			
			employeeModel.setResume(resume.getBytes());
			}
			if (Objects.nonNull(signedContract)) {
			employeeModel.setSignedContract(signedContract.getBytes());
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		EmployeeModel employeeRes = employeeService.updateEmployeeData(employeeModel);
		return new BaseDto<>(employeeRes, employeeHelper.getUpdateEmployeeMessage(), OK).respond();
	}
	

	@DeleteMapping("/delete/employee")
	public ResponseEntity<BaseDto<Object>> deleteEmployeeData(@RequestParam Integer employeeId) {
		log.info("Request Object for delete is: "+ employeeId);
		employeeService.deleteEmployeeById(employeeId);
		return new BaseDto<>(employeeHelper.getDeleteEmployeeMessage(), OK).respond();
	}

	@DeleteMapping("/delete/employees")
	public ResponseEntity<BaseDto<Object>> deleteEmployeesData(@RequestParam Integer[] employeeIds) {
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
	public ResponseEntity<BaseDto<EmployeeModel>> getEmployeeDataById(@RequestParam Integer employeeId) {
		EmployeeModel result = employeeService.findEmployeeById(employeeId);
		return new BaseDto<>(result, employeeHelper.getRetrieveEmployeeMessage(), OK).respond();
	}

	@GetMapping("/getallemployeetypedata")
	public ResponseEntity<BaseDto<List<EmployeeTypeModel>>> getEmployeeTypedata() {
		List<EmployeeTypeModel> result = employeeTypeService.getAllEmployeeTypes();
		return new BaseDto<>(result, employeeHelper.getRetrieveEmployeeMessage(), OK).respond();
	}

	@GetMapping("/getemployeeid")
	public ResponseEntity<BaseDto<EmployeeModel>> getLasteCreatedEmployeeId() {
		EmployeeModel result = employeeService.findLastCreatedEmployeeId();
		return new BaseDto<>(result, employeeHelper.getRetrieveEmployeeMessage(), OK).respond();
	}

	@GetMapping("/getemployeebyname")
	public ResponseEntity<BaseDto<List<EmployeeModel>>> getEmployeeByName(@RequestParam String name) {
		List<EmployeeModel> result = employeeService.findEmployeeByFirstNameAndLastName(name);
		return new BaseDto<>(result, employeeHelper.getRetrieveEmployeeMessage(), OK).respond();
	}

	@GetMapping("/getEmployess/havingAccess")
	public ResponseEntity<BaseDto<List<EmployeeNameAndAcessDTO>>>  getAllEmployeesHavingAccess(){
		List<EmployeeNameAndAcessDTO> response=employeeService.getAllEmployeesWithAccess();
		return new BaseDto<>(response,employeeHelper.getRetrieveEmployeeMessage(),OK).respond();
	}

}
