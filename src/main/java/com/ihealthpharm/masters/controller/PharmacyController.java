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
	

	@PostMapping("/save/pharmacy")
	public ResponseEntity<BaseDto<PharmacyModel>> insertPharmacyData(@Valid @RequestBody PharmacyModel pharmacyModel){
		log.info("Request Object insert is :",pharmacyModel);
		PharmacyModel pharmacyModelRes=pharmacyService.savePharmacyData(pharmacyModel);
		return new BaseDto<>(pharmacyModelRes,pharmacyHelper.getSavePharmacyMessage(),OK).respond();
	}
	
	@PutMapping("/update/pharmacy")
	public ResponseEntity<BaseDto<PharmacyModel>> updatePharmacyData(@Valid @RequestBody PharmacyModel pharmacyModel){
		log.info("Request Object for update is: ", pharmacyModel);
		PharmacyModel pharmacyModelRes = pharmacyService.updatePharmacyData(pharmacyModel);
		return new BaseDto<>(pharmacyModelRes,pharmacyHelper.getUpdatePharmacyMessage(),OK).respond();
	}
	
	@PutMapping("/update/pharmacies")
	public ResponseEntity<BaseDto<List<PharmacyModel>>> updateMultiplePharmaciessData(@Valid @RequestBody List<PharmacyModel> pharmacyModels) {
		log.info("Request Object for update is: "+ pharmacyModels);
		List<PharmacyModel> modelRes = pharmacyService.updateMultiplePharmacyData(pharmacyModels);
		return new BaseDto<>(modelRes,pharmacyHelper.getUpdatePharmacyMessage(),OK).respond();
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
	

}
