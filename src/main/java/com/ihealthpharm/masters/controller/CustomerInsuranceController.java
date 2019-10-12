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
import com.ihealthpharm.masters.helper.CustomerInsuranceHelper;
import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.masters.service.CustomerInsuranceService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class CustomerInsuranceController {

	@Autowired
	CustomerInsuranceService customerInsuranceService;

	@Autowired
	CustomerInsuranceHelper customerInsuranceHelper;

	@PostMapping("/save/customerinsurance")
	public ResponseEntity<BaseDto<CustomerInsuranceModel>> insertCustomerInsuranceData(@Valid @RequestBody CustomerInsuranceModel customerInsuranceModel) {

		CustomerInsuranceModel customerInsuranceModelRes = customerInsuranceService.saveCustomerInsuranceData(customerInsuranceModel);
		return new BaseDto<>(customerInsuranceModelRes, customerInsuranceHelper.saveCustomerInsuranceMessage, OK).respond();
	}

	@PutMapping("/update/customerinsurance")
	public ResponseEntity<BaseDto<CustomerInsuranceModel>> updateCustomerInsuranceData(@Valid @RequestBody CustomerInsuranceModel customerInsuranceModel) {
		log.info("Request Object for update is: ", customerInsuranceModel);
		CustomerInsuranceModel customerInsuranceModelRes = customerInsuranceService.updateCustomerInsuranceData(customerInsuranceModel);
		return new BaseDto<>(customerInsuranceModelRes, customerInsuranceHelper.updateCustomerInsuranceMessage, OK).respond();
	}

	@DeleteMapping("/delete/customerinsurance")
	public ResponseEntity<BaseDto<Object>> deleteCustomerInsuranceData(@RequestParam int customerInsuranceId) {
		log.info("Request Object for delete is: ", customerInsuranceId);
		customerInsuranceService.deleteCustomerInsuranceById(customerInsuranceId);
		return new BaseDto<>(customerInsuranceHelper.deleteCustomerInsuranceMessage, OK).respond();
	}

	@GetMapping("/getcustomerinsurancedata")
	public ResponseEntity<BaseDto<List<CustomerInsuranceModel>>> getCustomerInsuranceData() {
		List<CustomerInsuranceModel> result = customerInsuranceService.findAllCustomersInsurance();
		return new BaseDto<>(result, customerInsuranceHelper.retrieveCustomerInsuranceMessage, OK).respond();
	}

	@GetMapping("/getcustomerinsurancedatabyid")
	public ResponseEntity<BaseDto<CustomerInsuranceModel>> getCustomerInsuranceDataById(@RequestParam int customerInsuranceId) {
		CustomerInsuranceModel result = customerInsuranceService.findCustomerInsuranceById(customerInsuranceId);
		return new BaseDto<>(result, customerInsuranceHelper.retrieveCustomerInsuranceMessage, OK).respond();
	}

	@DeleteMapping("/delete/customersinsurance")
	public ResponseEntity<BaseDto<Object>> deleteCustomerInsuranceData(@RequestParam int[] customerInsuranceIds) {

		log.info("Request Object for delete is: " + customerInsuranceIds[0]);
		customerInsuranceService.deleteCustomersInsuranceById(customerInsuranceIds);
		return new BaseDto<>(customerInsuranceHelper.deleteCustomerInsuranceMessage, OK).respond();
	}

	@PutMapping("/update/customersinsurance")
	public ResponseEntity<BaseDto<List<CustomerInsuranceModel>>> updateCustomersInsuranceData(@Valid @RequestBody List<CustomerInsuranceModel> customerInsuranceModel) {
		log.info("Request Object for update is: " , customerInsuranceModel);
		List<CustomerInsuranceModel> CustomerInsuranceModelRes = customerInsuranceService.updateCustomersInsuranceData(customerInsuranceModel);
		return new BaseDto<>(CustomerInsuranceModelRes, customerInsuranceHelper.updateCustomerInsuranceMessage, OK).respond();
	}
	
	
}
