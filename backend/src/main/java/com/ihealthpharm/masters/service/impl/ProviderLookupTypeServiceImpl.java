package com.ihealthpharm.masters.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.ProviderLookupTypeRepository;
import com.ihealthpharm.masters.model.ProviderLookupTypeModel;
import com.ihealthpharm.masters.service.ProviderLookupTypeService;

@Service
public class ProviderLookupTypeServiceImpl implements ProviderLookupTypeService{

	@Autowired
	ProviderLookupTypeRepository providerLookupTypeRepository;
	
	@Override
	public List<ProviderLookupTypeModel> findAllProviderLookupTypes() {
		
		return providerLookupTypeRepository.findAll();
	}
}
