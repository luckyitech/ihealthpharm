package com.ihealthpharm.masters.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.CustomerImagesRepository;
import com.ihealthpharm.masters.model.CustomerImagesModel;
import com.ihealthpharm.masters.service.CustomerImagesService;

@Service
public class CustomerImagesServiceImpl implements CustomerImagesService {

	@Autowired
	CustomerImagesRepository customerImagesRepository;
	
	@Override
	public CustomerImagesModel save(CustomerImagesModel customerImage) {
		// TODO Auto-generated method stub
		return customerImagesRepository.save(customerImage);
	}

	@Override
	public CustomerImagesModel update(CustomerImagesModel customerImage) {
		// TODO Auto-generated method stub
		return customerImagesRepository.save(customerImage);
	}

	@Override
	public CustomerImagesModel findByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return customerImagesRepository.findByCustomerId(customerId);
	}

}
