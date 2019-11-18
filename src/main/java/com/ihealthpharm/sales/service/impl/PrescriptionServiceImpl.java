package com.ihealthpharm.sales.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.sales.dao.PrescriptionRepository;
import com.ihealthpharm.sales.model.PrescriptionImagesModel;
import com.ihealthpharm.sales.service.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Override
	public PrescriptionImagesModel savePrescription(PrescriptionImagesModel prescription) {
		prescription = prescriptionRepository.save(prescription);
		return prescription;
	}

	@Override
	public PrescriptionImagesModel getPrescriptionByCustomerIdAndDate(CustomerModel customerId, String date) {
		PrescriptionImagesModel prescription = prescriptionRepository.findByCustomerAndPrescriptionDate(customerId,date);
		return prescription;
	}

}
