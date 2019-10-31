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
import com.ihealthpharm.masters.helper.CustomerMembershipHelper;
import com.ihealthpharm.masters.model.CustomerMembershipModel;
import com.ihealthpharm.masters.service.CustomerMembershipService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class CustomerMembershipController {

	@Autowired
	CustomerMembershipService customerMembershipService;

	@Autowired
	CustomerMembershipHelper customerMembershipHelper;

	@PostMapping("/save/customermembership")
	public ResponseEntity<BaseDto<CustomerMembershipModel>> insertCustomerMembershipData(@Valid @RequestBody CustomerMembershipModel customerMembershipModel) {

		CustomerMembershipModel customerMembershipModelRes = customerMembershipService.saveCustomerMembershipData(customerMembershipModel);
		return new BaseDto<>(customerMembershipModelRes, customerMembershipHelper.saveCustomerMembershipMessage, OK).respond();
	}

	@PutMapping("/update/customermembership")
	public ResponseEntity<BaseDto<CustomerMembershipModel>> updateCustomerMembershipData(@Valid @RequestBody CustomerMembershipModel customerMembershipModel) {
		log.info("------------------------------------");
		log.info("Request Object for update is: ", customerMembershipModel.toString());
		CustomerMembershipModel customerMembershipModelRes = customerMembershipService.updateCustomerMembershipData(customerMembershipModel);
		return new BaseDto<>(customerMembershipModelRes, customerMembershipHelper.updateCustomerMembershipMessage, OK).respond();
	}

	@DeleteMapping("/delete/customermembership")
	public ResponseEntity<BaseDto<Object>> deleteCustomerMembershipData(@RequestParam int customerMembershipId) {
		log.info("Request Object for delete is: ", customerMembershipId);
		customerMembershipService.deleteCustomerMembershipById(customerMembershipId);
		return new BaseDto<>(customerMembershipHelper.deleteCustomerMembershipMessage, OK).respond();
	}

	@GetMapping("/getcustomermembershipdata")
	public ResponseEntity<BaseDto<List<CustomerMembershipModel>>> getCustomerMembershipData() {
		List<CustomerMembershipModel> result = customerMembershipService.findAllCustomersMembership();
		return new BaseDto<>(result, customerMembershipHelper.retrieveCustomerMembershipMessage, OK).respond();
	}

	@GetMapping("/getcustomermembershipdatabyid")
	public ResponseEntity<BaseDto<CustomerMembershipModel>> getCustomerMembershipDataById(@RequestParam int customerMembershipId) {
		CustomerMembershipModel result = customerMembershipService.findCustomerMembershipById(customerMembershipId);
		return new BaseDto<>(result, customerMembershipHelper.retrieveCustomerMembershipMessage, OK).respond();
	}

	@DeleteMapping("/delete/customersmembership")
	public ResponseEntity<BaseDto<Object>> deleteCustomerMembershipData(@RequestParam int[] customerMembershipIds) {

		log.info("Request Object for delete is: " + customerMembershipIds[0]);
		customerMembershipService.deleteCustomersMembershipById(customerMembershipIds);
		return new BaseDto<>(customerMembershipHelper.deleteCustomerMembershipMessage, OK).respond();
	}

	@PutMapping("/update/customersmembership")
	public ResponseEntity<BaseDto<List<CustomerMembershipModel>>> updateCustomersMembershipData(@Valid @RequestBody List<CustomerMembershipModel> customerMembershipModel) {
		log.info("Request Object for update is: " , customerMembershipModel);
		List<CustomerMembershipModel> CustomerMembershipModelRes = customerMembershipService.updateCustomersMembershipData(customerMembershipModel);
		return new BaseDto<>(CustomerMembershipModelRes, customerMembershipHelper.updateCustomerMembershipMessage, OK).respond();
	}
	
	
	
}