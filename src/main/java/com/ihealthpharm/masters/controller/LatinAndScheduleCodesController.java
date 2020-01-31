package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.LatinAndScheduleCodesHelper;
import com.ihealthpharm.masters.model.LatinShortCodesModel;
import com.ihealthpharm.masters.model.ScheduleCodeModel;
import com.ihealthpharm.masters.service.LatinAndScheduleCodesService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
public class LatinAndScheduleCodesController {
	
	@Autowired
	private LatinAndScheduleCodesService latinAndScheduleCodesService;
	
	@Autowired
	private LatinAndScheduleCodesHelper latinAndScheduleHelper;
	
	@GetMapping("/getall/latincodes")
	public ResponseEntity<BaseDto<List<LatinShortCodesModel>>> getAllLatinCodesData() {
		log.info("Request Object for :Latin codes");
		List<LatinShortCodesModel> result = latinAndScheduleCodesService.findAllLatinCodes();
		return new BaseDto<>(result, latinAndScheduleHelper.getRetrieveLatinCodeMessage(), OK).respond();
	}
	
	@GetMapping("/getall/schedulecodes")
	public ResponseEntity<BaseDto<List<ScheduleCodeModel>>> getAllSchedulesCodesData() {
		log.info("Request Object for :Schedule codes");
		List<ScheduleCodeModel> result = latinAndScheduleCodesService.findAllScheduleCodes();
		return new BaseDto<>(result, latinAndScheduleHelper.getRetrieveScheduleCodeMessage(), OK).respond();
	}
	
	/*@GetMapping("/getconcatenatecode")
	public ResponseEntity<BaseDto<List<LatinShortCodesModel>>> getConcatenateByLatinCode() {
		List<LatinShortCodesModel> result=latinAndScheduleCodesService.findConcatenateCode();
		return new BaseDto<>(result, latinAndScheduleHelper.getRetrieveLatinCodeMessage(), OK).respond();
	}*/
	
}
