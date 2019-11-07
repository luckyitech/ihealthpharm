package com.ihealthpharm.sales.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.sales.model.PrescriptionImagesModel;
import com.ihealthpharm.sales.service.PrescriptionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class PrescriptionController {
	
	@Autowired
	PrescriptionService prescriptionService;
	
	@PostMapping("/save/prescription")
	public ResponseEntity<BaseDto<PrescriptionImagesModel>> savePrescription(@RequestParam("prescription") String prescription, @RequestParam("prescriptionImage") String prescriptionImage){
		System.out.println("-------------------------------------------------------");
		System.out.println(prescription);
		System.out.println(prescriptionImage);
		System.out.println("-------------------------------------------------------");
		PrescriptionImagesModel prescriptionImagesModel = new PrescriptionImagesModel();
		try {
			prescriptionImagesModel = new ObjectMapper().readValue(prescription,PrescriptionImagesModel.class);
			prescriptionImagesModel.setPrescriptionImage(prescriptionImage.getBytes());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		prescriptionImagesModel = prescriptionService.savePrescription(prescriptionImagesModel);
	return new BaseDto<>(prescriptionImagesModel,"Prescription Saved",HttpStatus.OK).respond();
	}
	
	@GetMapping("/getbycustomeranddate")
	public ResponseEntity<BaseDto<PrescriptionImagesModel>> getPrescriptionByCustomerAndDate(@RequestParam String customer, @RequestParam String date){
		CustomerModel customerId  = null;
		Date dateObj = null;
		try {
			customerId = new ObjectMapper().readValue(customer,CustomerModel.class);
			dateObj =new ObjectMapper().readValue(date,Date.class);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		PrescriptionImagesModel prescription = prescriptionService.getPrescriptionByCustomerIdAndDate(customerId, dateObj);
		return new BaseDto<>(prescription,"Prescription Saved",HttpStatus.OK).respond();
		}
}
