package com.ihealthpharm.masters.controller;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
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
import com.ihealthpharm.masters.helper.InsuranceHelper;
import com.ihealthpharm.masters.model.InsuranceModel;
import com.ihealthpharm.masters.service.InsuranceService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class InsuranceController {
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private InsuranceHelper insuranceHelper;
	
	@PostMapping("/save/insurance/image")
	public ResponseEntity<BaseDto<InsuranceModel>> saveInsuranceData(@Valid @RequestParam ("insuranceModel") String insuranceData,@Valid @RequestParam ("termsAndConditionsFile") MultipartFile termsAndConditionsFile){
		log.info("Request Object insert along with image :",insuranceData);
		InsuranceModel insuranceModel=null;
		try {
			insuranceModel=new ObjectMapper().readValue(insuranceData, InsuranceModel.class);
		    insuranceModel.setTermsAndConditionsFile(termsAndConditionsFile.getBytes());
		}catch (IOException e) {
          e.printStackTrace();
		}
		
		InsuranceModel insuranceRes=insuranceService.saveInsurance(insuranceModel);
		return new BaseDto<>(insuranceRes,insuranceHelper.getSaveInsuranceMessage(),OK).respond();
	}
	
	@PostMapping("/save/insurance")
	public ResponseEntity<BaseDto<InsuranceModel>> saveInsurance(@Valid @RequestBody  InsuranceModel insuranceModel ){
		log.info("Request Object to insert is :"+insuranceModel);
		InsuranceModel insurance=insuranceService.saveInsurance(insuranceModel);
		return new BaseDto<>(insurance,insuranceHelper.getSaveInsuranceMessage(),OK).respond();
	}
	
	
	@PutMapping("/update/insurance/image")
	public ResponseEntity<BaseDto<InsuranceModel>> updateInsurance(@Valid @RequestParam("insuraceModel") String insuranceData,@Valid @RequestParam ("termsAndConditionsFile") MultipartFile termsAndConditionsFile){
		log.info("Request Object for update is: ", insuranceData);
		InsuranceModel insuranceModel = null;
		try {
			insuranceModel = new ObjectMapper().readValue(insuranceData, InsuranceModel.class);
			insuranceModel.setTermsAndConditionsFile(termsAndConditionsFile.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InsuranceModel insuranceModelRes = insuranceService.updateInsuranceData(insuranceModel);
		return new BaseDto<>(insuranceModelRes,insuranceHelper.getUpdateInsuranceMessage(),OK).respond();
	}
	
	
	@PutMapping("/update/insurance")
	public ResponseEntity<BaseDto<InsuranceModel>> updateInsuranceData(@Valid @RequestBody InsuranceModel insuranceModel){
		log.info("Request Object for update is :"+insuranceModel);
		InsuranceModel insuranceRes=insuranceService.updateInsuranceData(insuranceModel);
		return new BaseDto<>(insuranceRes,insuranceHelper.getUpdateInsuranceMessage(),OK).respond();
	}
	
	@PutMapping("/update/insurances")
	public ResponseEntity<BaseDto<List<InsuranceModel>>> updateInsurancies(@Valid @RequestBody List<InsuranceModel> insuranceModels ){
		log.info("Request Update For Multiples :"+insuranceModels);
		List<InsuranceModel> insurances=insuranceService.updateMultipleInsurances(insuranceModels);
		return new BaseDto<>(insurances,insuranceHelper.getUpdateInsuranceMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/insurance")
	public ResponseEntity<BaseDto<Object>> deleteInsurance(@RequestParam int insurancePolicyId){
		log.info("Request Object for delete :"+insurancePolicyId);
		insuranceService.delete(insurancePolicyId);
		return new BaseDto<>(insuranceHelper.getDeleteInsuranceMessage(),OK).respond();
	}
	
	
	@GetMapping("/getinsurancies")
	public ResponseEntity<BaseDto<List<InsuranceModel>>> getAllInsurancies(){
		List<InsuranceModel> response=insuranceService.findAllByInsurances();
		return new BaseDto<>(response,insuranceHelper.getRetrieveInsuranceMessage(),OK).respond();
	}
	
	@GetMapping("/getinsurance/byid")
	public ResponseEntity<BaseDto<InsuranceModel>> getInsuranceById(@Valid @RequestParam int insurancePolicyId){
		InsuranceModel insuranceRes=insuranceService.findInsuranceById(insurancePolicyId);
		return new BaseDto<>(insuranceRes,insuranceHelper.getRetrieveInsuranceMessage(),OK).respond();
	}
	
	@GetMapping("/getinsurance/bypolicycode")
	public ResponseEntity<BaseDto<InsuranceModel>> getInsuranceByPolicyCode(@Valid @RequestParam String policyCode){
		InsuranceModel insuranceRes=insuranceService.findInsuranceByPolicyCode(policyCode);
		return new BaseDto<>(insuranceRes,insuranceHelper.getRetrieveInsuranceMessage(),OK).respond();
	}
	
	@GetMapping("/getinsurance/bypolicycodeandpolicyname")
	public ResponseEntity<BaseDto<List<InsuranceModel>>> getInsuranceByPolicyCodeAndPolicyName(@Valid @RequestParam String searchTerm){
		List<InsuranceModel> insuranceRes=insuranceService.findInsuranceByPolicyCodeOrPolicyDescription(searchTerm);
		return new BaseDto<>(insuranceRes,insuranceHelper.getRetrieveInsuranceMessage(),OK).respond();
	}

}
