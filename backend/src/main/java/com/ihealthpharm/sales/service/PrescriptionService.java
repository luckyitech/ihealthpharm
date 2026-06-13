package com.ihealthpharm.sales.service;

import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.sales.model.PrescriptionImagesModel;

public interface PrescriptionService {

	public PrescriptionImagesModel savePrescription(PrescriptionImagesModel prescription);
	
	public PrescriptionImagesModel getPrescriptionByCustomerIdAndDate(CustomerModel customerId, String date);
}
