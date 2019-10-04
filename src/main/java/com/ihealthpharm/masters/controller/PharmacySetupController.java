package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.model.PharmacySetupModel;
import com.ihealthpharm.masters.service.PharmacySetupService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class PharmacySetupController {

	@Autowired
	PharmacySetupService pharmacySetupService;
	
	@PostMapping("/save/pharmacysetup")
	public ResponseEntity<BaseDto<PharmacySetupModel>> insertPharmacySetupData(@Valid @RequestBody PharmacySetupModel pharmacySetupModel) {
		log.info("Request Object insert is: "+ pharmacySetupModel.toString());
		
		PharmacySetupModel pharmacySetupRes = pharmacySetupService.savePharmacySetup(pharmacySetupModel);
		return new BaseDto<>(pharmacySetupRes,"Pharmacy Setup Saved",OK).respond();
	}
	
	@GetMapping("/get/pharmacysetup")
	public ResponseEntity<BaseDto<List<PharmacySetupModel>>> getAllPharmacySetupData() {
		
		
		List<PharmacySetupModel> pharmacySetupRes = pharmacySetupService.findAllPharmacySetups();
		return new BaseDto<>(pharmacySetupRes,"Pharmacy Setup Saved",OK).respond();
	}
}
