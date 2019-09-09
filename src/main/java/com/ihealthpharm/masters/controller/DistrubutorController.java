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
import com.ihealthpharm.masters.helper.DistributorHelper;
import com.ihealthpharm.masters.model.DistributorModel;
import com.ihealthpharm.masters.model.ReturnCreditTypeModel;
import com.ihealthpharm.masters.service.DistrubutorService;
import com.ihealthpharm.masters.service.ReturnCreditTypeService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class DistrubutorController {
	String resMessage;

	@Autowired
	private DistrubutorService distrubutorService ;
	
	@Autowired
	private ReturnCreditTypeService returnCreditTypeService;
	
	@Autowired
	private DistributorHelper distributorHelper;
	
	@PostMapping("/save/distrubutor")
	public ResponseEntity<BaseDto<DistributorModel>> insertDistrubutorData(@Valid @RequestBody DistributorModel distributorModel) {
		log.info("Request Object insert is: "+distributorModel.toString());
		DistributorModel distrubutorRes = distrubutorService.saveDistrubutorData(distributorModel);
		return new BaseDto<>(distrubutorRes,distributorHelper.getSaveDistrubutorMessage(),OK).respond();
	}
	
	@PutMapping("/update/distrubutor")
	public ResponseEntity<BaseDto<DistributorModel>> updateDistrubutorData(@Valid @RequestBody DistributorModel distributorModel) {
		log.info("Request Object for update is: "+ distributorModel.toString());
		DistributorModel distrubutorRes = distrubutorService.updateDistrubutorData(distributorModel);
		return new BaseDto<>(distrubutorRes,distributorHelper.getUpdateDistrubutorMessage(),OK).respond();
	}
	
	@PutMapping("/update/distrubutors")
	public ResponseEntity<BaseDto<List<DistributorModel>>> updateDistrubutorsData(@Valid @RequestBody List<DistributorModel> distributorModels) {
		log.info("Request Object for update is: "+ distributorModels.toString());
		List<DistributorModel> distrubutorRes = distrubutorService.updateDistrubutorsData(distributorModels);
		return new BaseDto<>(distrubutorRes,distributorHelper.getUpdateDistrubutorMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/distrubutor")
	public ResponseEntity<BaseDto<Object>> deleteProviderData(@RequestParam int distrubutorId) {
		log.info("Request Object for delete is: ", distrubutorId);
		distrubutorService.deleteDistrubutorById(distrubutorId);;
		return new BaseDto<>(distributorHelper.getDeleteDistrubutorMessage(), OK).respond();
	}
	

	@DeleteMapping("/delete/distrubutors")
	public ResponseEntity<BaseDto<Object>> deleteProviderData(@RequestParam int[] distrubutorsId) {
		log.info("Request Object for delete is: "+ distrubutorsId.toString());
		distrubutorService.deleteDistrubutorsById(distrubutorsId);
		return new BaseDto<>(distributorHelper.getDeleteDistrubutorMessage(), OK).respond();
	}
	
	@GetMapping("/getactivedistrubutorsdata")
	public ResponseEntity<BaseDto<List<DistributorModel>>> getActiveDistributorData() {
		List<DistributorModel> result = distrubutorService.findDistrubutorByActive();
		return new BaseDto<>(result, distributorHelper.getRetrieveDistrubutorMessage(), OK).respond();
	}
	
	@GetMapping("/getalldistrubutorsdata")
	public ResponseEntity<BaseDto<List<DistributorModel>>> getAllDistributordata() {
		List<DistributorModel> result = distrubutorService.findAllDistributors();
		return new BaseDto<>(result, distributorHelper.getRetrieveDistrubutorMessage(), OK).respond();
	}
	
	@GetMapping("/getdistrubutordatabyid")
	public ResponseEntity<BaseDto<DistributorModel>> getProviderDataById(@RequestParam int distrubutorId) {
		DistributorModel result = distrubutorService.findDistrubutorById(distrubutorId);
		return new BaseDto<>(result, distributorHelper.getRetrieveDistrubutorMessage(), OK).respond();
	}
	
	@GetMapping("/getreturntredittype")
	public ResponseEntity<BaseDto<List<ReturnCreditTypeModel>>> getReturnCreditType() {
		List<ReturnCreditTypeModel> result = returnCreditTypeService.findAllReturnCreditTypes();
		return new BaseDto<>(result, distributorHelper.getRetrieveDistrubutorMessage(), OK).respond();
	}
	
}
