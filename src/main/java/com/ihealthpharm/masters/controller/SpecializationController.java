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
import com.ihealthpharm.masters.helper.SpecializationHelper;
import com.ihealthpharm.masters.model.SpecializationModel;
import com.ihealthpharm.masters.service.SpecializationService;

import lombok.extern.slf4j.Slf4j;



/**
 * @author Tarun
 *
 */
@RestController
@Slf4j
@CrossOrigin
public class SpecializationController {
	
	@Autowired
	private SpecializationService specializationService;
	
	@Autowired
	private SpecializationHelper specializationHelper;
	
	@PostMapping("/save/specialization")
	public ResponseEntity<BaseDto<SpecializationModel>> insertSpecializationData(@Valid @RequestBody SpecializationModel specializationModel) {
		log.info("Request Object insert is: "+ specializationModel);
		SpecializationModel specializationModelRes = specializationService.saveSpecializationData(specializationModel);
		return new BaseDto<>(specializationModelRes,specializationHelper.getSaveSpecializationMessage(),OK).respond();
	}
	
	@PutMapping("/update/specialization")
	public ResponseEntity<BaseDto<SpecializationModel>> updateSpecializationData(@Valid @RequestBody SpecializationModel specializationModel) {
		log.info("Request Object for update is: ",specializationModel);
		SpecializationModel specializationModelRes = specializationService.updateSpecializationData(specializationModel);
		return new BaseDto<>(specializationModelRes,specializationHelper.getUpdateSpecializationMessage(),OK).respond();
	}

	@PutMapping("/update/specializations")
	public ResponseEntity<BaseDto<List<SpecializationModel>>> updateSpecializationsData(@Valid @RequestBody List<SpecializationModel> specializationModels) {
		log.info("Request Object for update is: "+ specializationModels);
		List<SpecializationModel> specializationModelRes = specializationService.updateSpecializationsData(specializationModels);
		return new BaseDto<>(specializationModelRes,specializationHelper.getUpdateSpecializationMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/specialization")
	public ResponseEntity<BaseDto<Object>> deleteSpecializationData(@RequestParam Integer specializationId) {
		log.info("Request Object for delete is: ", specializationId);
		  specializationService.deleteSpecializationById(specializationId);
		return new BaseDto<>(specializationHelper.getDeleteSpecializationMessage(), OK).respond();
	}
	
	
	@DeleteMapping("/delete/specializations")
	public ResponseEntity<BaseDto<Object>> deleteSpecializationsData(@RequestParam Integer[] specializationIds) {
		log.info("Request Object for delete is: "+ specializationIds);
		  specializationService.deleteMultipleSpecializationsById(specializationIds);
		return new BaseDto<>(specializationHelper.getDeleteSpecializationMessage(), OK).respond();
	}
	
	@GetMapping("/getactivespecializationsdata")
	public ResponseEntity<BaseDto<List<SpecializationModel>>> getActiveSpecializationdata() {
		List<SpecializationModel> result = specializationService.findSpecializationByActive();
		return new BaseDto<>(result, specializationHelper.getRetrieveSpecializationMessage(), OK).respond();
	}
	
	
	@GetMapping("/getallspecializationdata")
	public ResponseEntity<BaseDto<List<SpecializationModel>>> getAllSpecializationdata() {
		List<SpecializationModel> result = specializationService.findAllSpecializations();
		return new BaseDto<>(result, specializationHelper.getRetrieveSpecializationMessage(), OK).respond();
	}
	
	
	@GetMapping("/getspecializationdatabyid")
	public ResponseEntity<BaseDto<SpecializationModel>> getSpecializationDataById(@RequestParam Integer specializationId) {
		SpecializationModel result = specializationService.findSpecializationById(specializationId);
		return new BaseDto<>(result, specializationHelper.getRetrieveSpecializationMessage(), OK).respond();
	}
	
	
	@GetMapping("/getallspecializationsbysearch")
	public ResponseEntity<BaseDto<List<SpecializationModel>>> getAllSpecializationdata(@RequestParam String searchTerm) {
		List<SpecializationModel> result = specializationService.findAllSpecializationData(searchTerm);
		return new BaseDto<>(result, specializationHelper.getRetrieveSpecializationMessage(), OK).respond();
	}
	
	

}
