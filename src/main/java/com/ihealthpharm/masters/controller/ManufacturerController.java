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
import com.ihealthpharm.masters.helper.ManufacturerHelper;
import com.ihealthpharm.masters.model.ManufacturerModel;
import com.ihealthpharm.masters.service.ManufacturerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class ManufacturerController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@Autowired
	private ManufacturerHelper manufacturerHelper;
	
	@PostMapping("/save/manufacturer")
	public ResponseEntity<BaseDto<ManufacturerModel>> insertManufacturerData(@Valid @RequestBody ManufacturerModel manufacturerModel) {
		log.info("Request Object insert is: "+ manufacturerModel);
		
		ManufacturerModel manufacturerModelRes = manufacturerService.saveManufacturerData(manufacturerModel);
		return new BaseDto<>(manufacturerModelRes,manufacturerHelper.getSaveManufacturerMessage(),OK).respond();
	}
	
	@PutMapping("/update/manufacturer")
	public ResponseEntity<BaseDto<ManufacturerModel>> updateManufacturerData(@Valid @RequestBody ManufacturerModel manufacturerModel) {
		log.info("Request Object for update is: ",manufacturerModel);
		ManufacturerModel manufacturerModelRes = manufacturerService.updateManufacturerData(manufacturerModel);
		return new BaseDto<>(manufacturerModelRes,manufacturerHelper.getUpdateManufacturerMessage(),OK).respond();
	}
	
	
	@PutMapping("/update/manufacturers")
	public ResponseEntity<BaseDto<List<ManufacturerModel>>> updateManufacturersData(@Valid @RequestBody List<ManufacturerModel> manufacturerModels) {
		log.info("Request Object for update is: "+ manufacturerModels);
		List<ManufacturerModel> manufacturerRes = manufacturerService.updateManufacturersData(manufacturerModels);
		return new BaseDto<>(manufacturerRes,manufacturerHelper.getUpdateManufacturerMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/manufacturer")
	public ResponseEntity<BaseDto<Object>> deleteManufacturerData(@RequestParam Integer manufacturerId) {
		log.info("Request Object for delete is: ", manufacturerId);
		manufacturerService.deleteManufacturerById(manufacturerId);
		return new BaseDto<>(manufacturerHelper.getDeleteManufacturerMessage(), OK).respond();
	}
	
	
	@DeleteMapping("/delete/manufacturers")
	public ResponseEntity<BaseDto<Object>> deleteManufaturersData(@RequestParam Integer[] manufacturerIds) {
		log.info("Request Object for delete is: "+ manufacturerIds);
		manufacturerService.deleteMultipleManufacturersById(manufacturerIds);
		return new BaseDto<>(manufacturerHelper.getDeleteManufacturerMessage(), OK).respond();
	}
	

	@GetMapping("/getactivemanufacturersdata")
	public ResponseEntity<BaseDto<List<ManufacturerModel>>> getActiveManufacturerdata() {
		List<ManufacturerModel> result = manufacturerService.findManufacturerByActive();
		return new BaseDto<>(result, manufacturerHelper.getRetrieveManufacturerMessage(), OK).respond();
	}
	
	
	@GetMapping("/getallmanufacturersdata")
	public ResponseEntity<BaseDto<List<ManufacturerModel>>> getAllManufacturerdata() {
		List<ManufacturerModel> result = manufacturerService.findAllManufacturers();
		return new BaseDto<>(result, manufacturerHelper.getRetrieveManufacturerMessage(), OK).respond();
	}
	
	@GetMapping("/getmanufacturerdatabyid")
	public ResponseEntity<BaseDto<ManufacturerModel>> getManufacturerDataById(@RequestParam Integer manufacturerId) {
		ManufacturerModel result = manufacturerService.findManufacturerById(manufacturerId);
		return new BaseDto<>(result, manufacturerHelper.getRetrieveManufacturerMessage(), OK).respond();
	}
	
	@GetMapping("/getallmanufacturersdatabysearch")
	public ResponseEntity<BaseDto<List<ManufacturerModel>>> getAllManufacturersdata(@RequestParam String searchTerm) {
	   List<ManufacturerModel> result=manufacturerService.findAllManufacturersData(searchTerm);
		return new BaseDto<>(result, manufacturerHelper.getRetrieveManufacturerMessage(), OK).respond();
	}
	
}
