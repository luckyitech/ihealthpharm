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
import com.ihealthpharm.masters.helper.CustomerHelper;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerHelper customerHelper;

	@PostMapping("/save/customer")
	public ResponseEntity<BaseDto<CustomerModel>> insertCustomerData(@Valid @RequestBody CustomerModel customerModel) {

		CustomerModel customerModelRes = customerService.saveCustomerData(customerModel);
		return new BaseDto<>(customerModelRes, customerHelper.getSaveCustomerMessage(), OK).respond();
	}

	@PutMapping("/update/customer")
	public ResponseEntity<BaseDto<CustomerModel>> updateCustomerData(@Valid @RequestBody CustomerModel customerModel) {
		CustomerModel customerModelRes = customerService.updateCustomerData(customerModel);
		return new BaseDto<>(customerModelRes, customerHelper.getUpdateCustomerMessage(), OK).respond();
	}

	@PutMapping("/update/customers")
	public ResponseEntity<BaseDto<List<CustomerModel>>> updateCustomersData(@Valid @RequestBody List<CustomerModel> customerModel) {
		log.info("Request Object for update is: " , customerModel);
		List<CustomerModel> CustomerModelRes = customerService.updateCustomersData(customerModel);
		return new BaseDto<>(CustomerModelRes, customerHelper.getUpdateCustomerMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/customer")
	public ResponseEntity<BaseDto<Object>> deleteCustomerData(@RequestParam Integer customerId) {
		log.info("Request Object for delete is: ", customerId);
		customerService.deleteCustomerById(customerId);
		return new BaseDto<>(customerHelper.getDeleteCustomerMessage(), OK).respond();
	}

	@DeleteMapping("/delete/customers")
	public ResponseEntity<BaseDto<Object>> deleteCustomerData(@RequestParam Integer[] customerIds) {

		log.info("Request Object for delete is: " + customerIds[0]);
		customerService.deleteCustomersById(customerIds);
		return new BaseDto<>(customerHelper.getDeleteCustomerMessage(), OK).respond();
	}

	
	@GetMapping("/getcustomerdata")
	public ResponseEntity<BaseDto<List<CustomerModel>>> getCustomerData() {
		List<CustomerModel> result = customerService.findAllCustomers();
		return new BaseDto<>(result, customerHelper.getRetrieveCustomerMessage(), OK).respond();
	}

	@GetMapping("/getcustomerdatabyid")
	public ResponseEntity<BaseDto<CustomerModel>> getCustomerDataById(@RequestParam Integer customerId) {
		CustomerModel result = customerService.findCustomerById(customerId);
		return new BaseDto<>(result, customerHelper.getRetrieveCustomerMessage(), OK).respond();
	}

	@GetMapping("/getlimitedcustomerdata")
	public ResponseEntity<BaseDto<List<CustomerModel>>> getLimitedCustomerData() {
		List<CustomerModel> result = customerService.findLimitedCustomers();
		return new BaseDto<>(result, customerHelper.getRetrieveCustomerMessage(), OK).respond();
	}
	
	//@GetMapping
	
	
	
	
	@GetMapping("/getlimitedcustomerdata/tomap/membership")
	public ResponseEntity<BaseDto<List<CustomerModel>>> getAllLimitedCustomersToMapMembership(){
		List<CustomerModel> repsonse=customerService.findAllLimitedCustomersData();
		return new BaseDto<>(repsonse, customerHelper.getRetrieveCustomerMessage(), OK).respond();
	}
	
	@GetMapping("/getcustomerdatabyname")
	public ResponseEntity<BaseDto<List<CustomerModel>>> getCustomerDataByName(@RequestParam("key") String customerName) {
		List<CustomerModel> result = customerService.findCustomersByName(customerName);
		return new BaseDto<>(result, customerHelper.getRetrieveCustomerMessage(), OK).respond();
	}
	
	@GetMapping("/getcustomerdatabynamesearch")
	public ResponseEntity<BaseDto<List<CustomerModel>>> getCustomersDataBySearchingName(@RequestParam("key")String customerName){
		List<CustomerModel> response=customerService.findAllCustomersByNameSearch(customerName);
		return new BaseDto<>(response,customerHelper.getRetrieveCustomerMessage(),OK).respond();
	}
	
	
}
