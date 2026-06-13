package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.model.ConfigurationStatusModel;
import com.ihealthpharm.masters.service.ConfigurationStatusService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class ConfigurationStatusController {

	@Autowired
	ConfigurationStatusService configurationStatusService;
	
	@PostMapping("/saveconfigurationstatus")
	public ResponseEntity<BaseDto<ConfigurationStatusModel>> saveCountry(@RequestBody ConfigurationStatusModel configurationStatusModel) {
		
		configurationStatusModel = configurationStatusService.configurationStatusSave(configurationStatusModel);
		return new BaseDto<>(configurationStatusModel, "Configuration Status Saved", OK).respond();
	}
	
	@PutMapping("/updateconfigurationstatus")
	public ResponseEntity<BaseDto<ConfigurationStatusModel>> updateCountry(@RequestBody ConfigurationStatusModel configurationStatusModel) {
		
		configurationStatusModel = configurationStatusService.configurationStatusUpdate(configurationStatusModel);
		return new BaseDto<>(configurationStatusModel, "Configuration Status Updated", OK).respond();
	}
	
	@GetMapping("/getconfigurationstatus")
	public ResponseEntity<BaseDto<ConfigurationStatusModel>> getCountry() {
		
		ConfigurationStatusModel configurationStatusModel = configurationStatusService.findConfigurationStatus();
		return new BaseDto<>(configurationStatusModel, "Configuration Status Retrived", OK).respond();
	}
}
