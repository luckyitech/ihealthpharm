package com.ihealthpharm.masters.service;

import com.ihealthpharm.masters.model.CustomerImagesModel;

public interface CustomerImagesService {

	public CustomerImagesModel save(CustomerImagesModel customerImage);
	
	public CustomerImagesModel update(CustomerImagesModel customerImage);
	
	public CustomerImagesModel findByCustomerId(Integer customerId);
}
