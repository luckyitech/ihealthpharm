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
import com.ihealthpharm.masters.helper.PharmacyHelper;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.service.PharmacyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private PharmacyHelper pharmacyHelper;
	

	@PostMapping("/save/pharmacywithlogo")
	public ResponseEntity<BaseDto<PharmacyModel>> insertPharmacyData(@Valid @RequestParam("pharmacyModel") String pharmacyData,@RequestParam("pharmacyLogoPath") MultipartFile pharmacyLogoPath){
		log.info("Request Object insert is :",pharmacyData);
		PharmacyModel pharmacyModel = null;
		try {
			pharmacyModel = new ObjectMapper().readValue(pharmacyData, PharmacyModel.class);
			pharmacyModel.setPharmacyLogoPath(pharmacyLogoPath.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PharmacyModel pharmacyModelRes=pharmacyService.savePharmacyData(pharmacyModel);
		return new BaseDto<>(pharmacyModelRes,pharmacyHelper.getSavePharmacyMessage(),OK).respond();
	}
	
	@PostMapping("/save/pharmacy")
	public ResponseEntity<BaseDto<PharmacyModel>> insertPharmacyData(@Valid @RequestBody PharmacyModel pharmacyModel){
		log.info("Request Object insert is :",pharmacyModel);
		PharmacyModel pharmacyModelRes=pharmacyService.savePharmacyData(pharmacyModel);
		return new BaseDto<>(pharmacyModelRes,pharmacyHelper.getSavePharmacyMessage(),OK).respond();
	}
	
	@PutMapping("/update/pharmacywithlogo")
	public ResponseEntity<BaseDto<PharmacyModel>> updatePharmacyData(@Valid @RequestParam("pharmacyModel") String pharmacyData,@RequestParam("pharmacyLogoPath") MultipartFile pharmacyLogoPath){
		log.info("Request Object for update is: ", pharmacyData);
		PharmacyModel pharmacyModel = null;
		try {
			pharmacyModel = new ObjectMapper().readValue(pharmacyData, PharmacyModel.class);
			pharmacyModel.setPharmacyLogoPath(pharmacyLogoPath.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PharmacyModel pharmacyModelRes = pharmacyService.updatePharmacyData(pharmacyModel);
		return new BaseDto<>(pharmacyModelRes,pharmacyHelper.getUpdatePharmacyMessage(),OK).respond();
	}
	
	@PutMapping("/update/pharmacy")
	public ResponseEntity<BaseDto<PharmacyModel>> updatePharmacyData(@Valid @RequestBody PharmacyModel pharmacyModel){
		log.info("Request Object for update is: ", pharmacyModel);
		PharmacyModel pharmacyModelRes = pharmacyService.updatePharmacyData(pharmacyModel);
		return new BaseDto<>(pharmacyModelRes,pharmacyHelper.getUpdatePharmacyMessage(),OK).respond();
	}
	
	
	@PutMapping("/update/pharmacies")
	public ResponseEntity<BaseDto<List<PharmacyModel>>> updateHospital(@Valid @RequestBody List<PharmacyModel> pharmacyModel) {
		log.info("Hospital data update request: ", pharmacyModel);
		List<PharmacyModel> res=pharmacyService.updateMultiplePharmacyData(pharmacyModel);
		return new BaseDto<>(res, pharmacyHelper.getUpdatePharmacyMessage(), OK).respond();
	}
	
	
	
	@DeleteMapping("/delete/pharmacy")
	public ResponseEntity<BaseDto<Object>> deletePharmacyData(@RequestParam int pharmacyId){
		log.info("Request Object for delete is: ", pharmacyId);
		pharmacyService.deletePharmacyById(pharmacyId);
		return new BaseDto<> (pharmacyHelper.getDeletePharmacyMessage(), OK).respond();
	}
	
	@GetMapping("/getactivepharmaciesdata")
	public ResponseEntity<BaseDto<List<PharmacyModel>>> getActivePharmaciesData(){
		List<PharmacyModel> result=pharmacyService.findPharmacyByActive();
		return new BaseDto<>(result,pharmacyHelper.getRetrievePharmacyMessage(),OK).respond();
	}
	
	@GetMapping("/getpharmacybyid")
	public ResponseEntity<BaseDto<PharmacyModel>> getPharmacyById(@RequestParam int pharmacyId){
		PharmacyModel result=pharmacyService.findPharmacyById(pharmacyId);
		return new BaseDto<>(result,pharmacyHelper.getRetrievePharmacyMessage(),OK).respond();
	}
	
	@GetMapping("/getallpharmacies")
	public ResponseEntity<BaseDto<List<PharmacyModel>>> getAllPharmaciesData(){
		List<PharmacyModel> result=pharmacyService.getAllPharmacies();
		return new BaseDto<>(result,pharmacyHelper.getRetrievePharmacyMessage(),OK).respond();
	}
	

}
