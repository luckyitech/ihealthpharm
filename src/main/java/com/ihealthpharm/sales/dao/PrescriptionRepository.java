package com.ihealthpharm.sales.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.sales.model.PrescriptionImagesModel;

public interface PrescriptionRepository extends JpaRepository<PrescriptionImagesModel, Integer> {

	
	
	PrescriptionImagesModel findByCustomerAndPrescriptionDate(CustomerModel customerId, String date);

}
