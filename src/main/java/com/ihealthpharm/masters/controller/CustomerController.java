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
	private CustomerHelper customerHelper;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save/customersdata")
	public ResponseEntity<BaseDto<CustomerModel>> insertCustomer(@Valid @RequestBody CustomerModel CustomerModel){
		log.info("Request Object Insert is:"+CustomerModel.toString());
		
		CustomerModel customerRes = customerService.saveCustomerData(CustomerModel);
		return new BaseDto<>(customerRes,customerHelper.getSaveCustomerMessage(),OK).respond();		
		
	}
	@PutMapping("/update/customerdata")
	public ResponseEntity<BaseDto<CustomerModel>> updateCustomer(@Valid @RequestBody CustomerModel CustomerModel){
		log.info("Request Object Updated:"+CustomerModel.toString());
		
		CustomerModel customerRes = customerService.updateCustomerData(CustomerModel);
		return new BaseDto<>(customerRes,customerHelper.getUpdateCustomerMessage(),OK).respond();		
	}
	@PutMapping("/update/multiplecustomerdata")
	public ResponseEntity<BaseDto<List<CustomerModel>>> updateCustomers(@Valid @RequestBody List<CustomerModel> customerModels){
		log.info("Request Object Updated:"+customerModels.toString());
		
		List<CustomerModel> customerResList = customerService.updateCustomerData(customerModels);
		return new BaseDto<>(customerResList, customerHelper.getUpdateCustomerMessage(), OK).respond();
	}

	@DeleteMapping("/delete/customerdata")
	public ResponseEntity<BaseDto<Object>> deleteCustomer(@RequestParam int customerId){
		log.info("Request Object Delete is:", customerId);
		
		customerService.deleteCustomerById(customerId);
		return new BaseDto<>(customerHelper.getUpdateCustomerMessage(), OK).respond();
	}
	@DeleteMapping("/delete/multiplecustomers")
	public ResponseEntity<BaseDto<Object>> deleteMultipleCustomerId(@RequestParam int[] customerId){
		log.info("Request Object delete by multiple id's:" +customerId);
		
		customerService.deleteCustomerByIds(customerId);
		return new BaseDto<>(customerHelper.getUpdateCustomerMessage(), OK).respond();
	}
	@GetMapping("/getcustomers")
	public ResponseEntity<BaseDto<List<CustomerModel>>> getAllCustomers(){
		
		List<CustomerModel> result = customerService.findAllCustomers();
		return new BaseDto<>(result, customerHelper.getRetrieveCustomerMessage(), OK).respond();
	}
	
	@GetMapping("/getcustomersbyid")
	public ResponseEntity<BaseDto<CustomerModel>> getCustomerById(@RequestParam int customerId) {
		CustomerModel result = customerService.findCustomerData(customerId);
		return new BaseDto<>(result, customerHelper.getRetrieveCustomerMessage(), OK).respond();
	}
	
}