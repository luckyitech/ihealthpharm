package com.ihealthpharm.sales.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.sales.model.PrescriptionImagesModel;
import com.ihealthpharm.sales.service.PrescriptionService;

@RestController
@CrossOrigin
public class PrescriptionController {
	
	@Autowired
	PrescriptionService prescriptionService;
	
	@PostMapping("/save/prescription")
	public ResponseEntity<BaseDto<PrescriptionImagesModel>> savePrescription(@RequestParam("prescription") String prescription, @RequestParam("prescriptionImage") MultipartFile prescriptionImage){
		try {
			System.out.println(prescriptionImage.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
	
	@PostMapping("/getbycustomeranddate")
	public ResponseEntity<BaseDto<PrescriptionImagesModel>> getPrescriptionByCustomerAndDate(@RequestParam String customer, @RequestParam String date){
		CustomerModel customerId  = null;
		Date dateObj = null;
		try {
			customerId = new ObjectMapper().readValue(customer,CustomerModel.class);
			dateObj =new ObjectMapper().readValue(date,Date.class);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		PrescriptionImagesModel prescription = prescriptionService.getPrescriptionByCustomerIdAndDate(customerId, date);
		return new BaseDto<>(prescription,"Prescription Saved",HttpStatus.OK).respond();
		}
}
