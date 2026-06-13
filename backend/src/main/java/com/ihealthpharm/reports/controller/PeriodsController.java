package com.ihealthpharm.reports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.reports.helper.PeriodsHelper;
import com.ihealthpharm.reports.model.ReportsPeriodModel;
import com.ihealthpharm.reports.service.PeriodsService;
import com.ihealthpharm.sales.model.SalesModel;

@RestController
@CrossOrigin
public class PeriodsController {
	
	@Autowired
	private PeriodsService periodsService;
	
	@Autowired
	private PeriodsHelper helper;
	
	
	@GetMapping("/getALL/periods")
	public ResponseEntity<BaseDto<List<ReportsPeriodModel>>> getAllPeriods(){
		List<ReportsPeriodModel> response=periodsService.getAllPeriods();
		return new BaseDto<>(response,helper.getRetrievePeriodsResponse(),HttpStatus.OK).respond();
	}
	
	
	@GetMapping("/getLimited/periods")
	public ResponseEntity<BaseDto<List<ReportsPeriodModel>>> getListOfPeriodsForThreeMonths(){
		List<ReportsPeriodModel> response=periodsService.getThreeMonthsPeriod();
		return new BaseDto<>(response,helper.getRetrievePeriodsResponse(),HttpStatus.OK).respond();
	}
	
	@GetMapping("/getCustomerNames")
	public ResponseEntity<BaseDto<List<String>>> getCustomersBasedOnDummyBills(){
		List<String> response=periodsService.getCustomerNames();
		return new BaseDto<>(response,helper.getRetrievePeriodsResponse(),HttpStatus.OK).respond();
	}
	
	@GetMapping("/getBills")
	public ResponseEntity<BaseDto<List<String>>> getBillsBasedOnDummyBills(){
		List<String> response=periodsService.getBills();
		return new BaseDto<>(response,helper.getRetrievePeriodsResponse(),HttpStatus.OK).respond();
	}
}
