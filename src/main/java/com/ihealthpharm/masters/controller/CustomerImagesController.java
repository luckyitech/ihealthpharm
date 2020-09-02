package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.model.CustomerImagesModel;
import com.ihealthpharm.masters.service.CustomerImagesService;

@RestController
@CrossOrigin
public class CustomerImagesController {

	@Autowired
	CustomerImagesService customerImagesService;
	
	@PostMapping("/savecustomerimage")
	public ResponseEntity<BaseDto<CustomerImagesModel>> saveCustomerImage(@RequestParam MultipartFile customerImage,
			@RequestParam Integer customerId){
		CustomerImagesModel customerImageObj = new CustomerImagesModel();
		try {
			customerImageObj.setCustomerId(customerId);
			customerImageObj.setCustomerImage(customerImage.getBytes());
		}
		catch(Exception e)
		{
			throw new IHealthPharmException("Couldn't Save Customer Id, Please Contect Administrator", HttpStatus.NOT_ACCEPTABLE);
		}
		CustomerImagesModel customerImageRes = customerImagesService.save(customerImageObj);
		
		return new BaseDto<>(customerImageRes,"Customer Image saved", OK).respond();
	}
	
	@PostMapping("/updatecustomerimage")
	public ResponseEntity<BaseDto<CustomerImagesModel>> updateCustomerImage(
			@RequestParam(value="customerImage") MultipartFile customerImage,
			@RequestParam Integer customerId,
			@RequestParam(required = false) Integer customerImageId){
		CustomerImagesModel customerImageObj = new CustomerImagesModel();
		try {
			if(customerImageId != null)
				customerImageObj.setCustomerImagesId(customerImageId);
			
			customerImageObj.setCustomerId(customerId);
			customerImageObj.setCustomerImage(customerImage.getBytes());
		}
		catch(Exception e)
		{
			throw new IHealthPharmException("Couldn't Update Customer Id, Please Contect Administrator", HttpStatus.NOT_ACCEPTABLE);
		}
		
		CustomerImagesModel customerImageRes = customerImagesService.update(customerImageObj);
		
		return new BaseDto<>(customerImageRes,"Customer Image updated", OK).respond();
	}
	
	@GetMapping("/getcustomerimagebyid")
	public ResponseEntity<BaseDto<CustomerImagesModel>> getCustomerImageByCustomerId(@RequestParam("customerId") Integer customerId){
		CustomerImagesModel customerImageRes = customerImagesService.findByCustomerId(customerId);
		return new BaseDto<>(customerImageRes,"Customer Image saved", OK).respond();
	}
}
