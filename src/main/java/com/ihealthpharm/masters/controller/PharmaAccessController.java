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
import com.ihealthpharm.masters.helper.PharmaAccessHelper;
import com.ihealthpharm.masters.model.PharmaAccessModel;
import com.ihealthpharm.masters.service.PharmaAccessService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class PharmaAccessController {

	@Autowired
	PharmaAccessService pharmaAccessService;
	
	@Autowired
	PharmaAccessHelper pharmaAccessHelper;
	
	@PostMapping("/save/pharmaaccess")
	public ResponseEntity<BaseDto<PharmaAccessModel>> insertDistrubutorData(@Valid @RequestBody PharmaAccessModel pharmaAccessModel) {
		log.info("Request Object insert is: "+pharmaAccessModel.toString());
		PharmaAccessModel pharmaAccessRes = pharmaAccessService.savePharmaAccessData(pharmaAccessModel);
		return new BaseDto<>(pharmaAccessRes,pharmaAccessHelper.getSavePharmaAccessMessage(),OK).respond();
	}
	
	@PutMapping("/update/pharmaaccess")
	public ResponseEntity<BaseDto<PharmaAccessModel>> updateDistrubutorData(@Valid @RequestBody PharmaAccessModel pharmaAccessModel) {
		log.info("Request Object for update is: "+ pharmaAccessModel.toString());
		PharmaAccessModel pharmaAccessRes = pharmaAccessService.updatePharmaAccessData(pharmaAccessModel);
		return new BaseDto<>(pharmaAccessRes,pharmaAccessHelper.getUpdatePharmaAccessMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/pharmaaccess")
	public ResponseEntity<BaseDto<Object>> deleteProviderData(@RequestParam int pharmaAccessId) {
		log.info("Request Object for delete is: ", pharmaAccessId);
		pharmaAccessService.deletePharmaAccessData(pharmaAccessId);;
		return new BaseDto<>(pharmaAccessHelper.getDeletePharmaAccessMessage(), OK).respond();
	}
	
	@GetMapping("/getallpharmaaccessdata")
	public ResponseEntity<BaseDto<List<PharmaAccessModel>>> getAllSupplierdata() {
		List<PharmaAccessModel> result = pharmaAccessService.findAllPharmaAccessData();
		return new BaseDto<>(result, pharmaAccessHelper.getRetrievePharmaAccessMessage(), OK).respond();
	}
	
	@GetMapping("/getpharmaaccessdatabyid")
	public ResponseEntity<BaseDto<PharmaAccessModel>> getProviderDataById(@RequestParam int pharmaAccessId) {
		PharmaAccessModel result = pharmaAccessService.findPharmaAccessDataById(pharmaAccessId);
		return new BaseDto<>(result, pharmaAccessHelper.getRetrievePharmaAccessMessage(), OK).respond();
	}
	
}
