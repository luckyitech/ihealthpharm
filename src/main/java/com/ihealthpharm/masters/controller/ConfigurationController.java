package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

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
import com.ihealthpharm.masters.model.ConfigurationModel;
import com.ihealthpharm.masters.service.ConfigurationService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class ConfigurationController {

	@Autowired
	ConfigurationService configurationService;
	
	@PostMapping("/save/configuration")
	public ResponseEntity<BaseDto<ConfigurationModel>> saveconfig(@RequestBody ConfigurationModel config) {
		
		ConfigurationModel configRes = configurationService.saveconfig(config);
		return new BaseDto<>(configRes, "Configuration Saved", OK).respond();
	}
	
	@PutMapping("/update/configuration")
	public ResponseEntity<BaseDto<ConfigurationModel>> Updateconfig(@RequestBody ConfigurationModel config) {
		
		ConfigurationModel configRes = configurationService.updateconfig(config);
		return new BaseDto<>(configRes, "config Updated", OK).respond();
	}
	
	@DeleteMapping("/delete/configuration")
	public ResponseEntity<BaseDto<Object>> deleteconfig(@RequestBody ConfigurationModel config) {
		
		configurationService.deleteconfig(config);
		return new BaseDto<>("config Deleted", OK).respond();
	}
	
	@GetMapping("/getall/configurations")
	public ResponseEntity<BaseDto<List<ConfigurationModel>>> getAllConfigurations() {
		
		List<ConfigurationModel> configRes = configurationService.getAllConfigurations();
		return new BaseDto<>(configRes, "config Retirved", OK).respond();
	}
	
	@GetMapping("/get/configurationbyid")
	public ResponseEntity<BaseDto<ConfigurationModel>> getAllConfigurationById(@RequestParam("configId") Integer configId) {
		
		ConfigurationModel configRes = configurationService.getconfigByconfigId(configId);
		return new BaseDto<>(configRes, "config Retirved", OK).respond();
	}
	
	@GetMapping("/get/maxdiscount")
	public ResponseEntity<BaseDto<Integer>> getMaxDiscount() {
		
		Integer maxdiscount = configurationService.getMaxDiscount();
		return new BaseDto<>(maxdiscount, "Max Discount Retrived", OK).respond();
	}
	
	@GetMapping("/get/margin")
	public ResponseEntity<BaseDto<Integer>> getMargin() {
		
		Integer maxdiscount = configurationService.getMargin();
		return new BaseDto<>(maxdiscount, "Max Discount Retrived", OK).respond();
	}
}
