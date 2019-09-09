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
import com.ihealthpharm.commons.StandardResponse;
import com.ihealthpharm.masters.helper.HospitalHelper;
import com.ihealthpharm.masters.model.HospitalModel;
import com.ihealthpharm.masters.service.HospitalModelService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Vikas
 *
 */
@RestController
@Slf4j
@CrossOrigin
public class HospitalController {
	
	@Autowired
	private HospitalModelService hospitalModelService;
	
	@Autowired
	private HospitalHelper hospitalHelper;
	
	@PostMapping("/save/hospital")
	public BaseDto<HospitalModel> insertData(@Valid @RequestBody HospitalModel hospitalModel) {
		log.info("Hospital data insert request: ", hospitalModel);
		HospitalModel hospitalRes = hospitalModelService.saveHospital(hospitalModel);
		return new BaseDto<>(hospitalRes,hospitalHelper.getSaveHospitalMessage(), OK);
	}
	
	@PutMapping("/update/hospital")
	public BaseDto<HospitalModel> updateHospital(@Valid @RequestBody HospitalModel hospitalModel) {
		log.info("Hospital data update request: ", hospitalModel);
		HospitalModel hospitalRes = hospitalModelService.updateHospitalData(hospitalModel);
		return new BaseDto<>(hospitalRes,hospitalHelper.getUpdateHospitalMessage(), OK);
	}
	
	@PutMapping("/update/hospitals")
	public BaseDto<List<HospitalModel>> updateHospital(@Valid @RequestBody List<HospitalModel> hospitalModel) {
		log.info("Hospital data update request: ", hospitalModel);
		hospitalModelService.updateMultipleHospitals(hospitalModel);
		return new BaseDto<List<HospitalModel>>(hospitalModel, hospitalHelper.getMultipleUpdatedHospitalMessage(), OK);
	}
		
	@DeleteMapping("/delete/hospital")
	public ResponseEntity<BaseDto<Object>> deleteHospital(@RequestParam int hospitalId) {
		log.info("Hospital data deleting ID: "+hospitalId);
		hospitalModelService.delete(hospitalId);
		return new BaseDto<>(hospitalHelper.getDeleteHospitalMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/hospitals")
	public ResponseEntity<BaseDto<Object>> deleteHospitalsData(@RequestParam int[] hospitalIds) {
		log.info("Request Object for delete is: "+ hospitalIds.toString());
		hospitalModelService.deleteMultipleHospitals(hospitalIds);
		return new BaseDto<>(hospitalHelper.getDeleteHospitalMessage(), OK).respond();
	}
	
	
	@GetMapping("/getactivehospitaldata")
	public ResponseEntity<BaseDto<List<HospitalModel>>> getActiveHospitaldata() {
		List<HospitalModel> result = hospitalModelService.findAllByActive();
		return new BaseDto<>(result, StandardResponse.SUCCESS.toString(), OK).respond();
	}
	
	@GetMapping("/getallhospitaldata")
	public ResponseEntity<BaseDto<List<HospitalModel>>> getAllHospitaldata() {
		List<HospitalModel> result = hospitalModelService.findAllHospitals();
		return new BaseDto<>(result, StandardResponse.SUCCESS.toString(), OK).respond();
	}
	
	
	@GetMapping("/gethospitaldatabyid")
	public ResponseEntity<BaseDto<HospitalModel>> getManufacturerDataById(@RequestParam int hospitalId) {
		HospitalModel result = hospitalModelService.findHospitalById(hospitalId);
		return new BaseDto<>(result, hospitalHelper.getRetrieveHospitalMessage(), OK).respond();
	}
	
}
