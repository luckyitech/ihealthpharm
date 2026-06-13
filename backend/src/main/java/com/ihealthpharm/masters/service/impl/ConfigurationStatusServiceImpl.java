package com.ihealthpharm.masters.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.ConfigurationStatusRepository;
import com.ihealthpharm.masters.model.ConfigurationStatusModel;
import com.ihealthpharm.masters.service.ConfigurationStatusService;


@Service
public class ConfigurationStatusServiceImpl implements ConfigurationStatusService {

	@Autowired
	private ConfigurationStatusRepository configurationStatusRepository;
	
	@Override
	public ConfigurationStatusModel configurationStatusSave(ConfigurationStatusModel configurationStatusModel) {
		
		return configurationStatusRepository.save(configurationStatusModel);
	}

	@Override
	public ConfigurationStatusModel configurationStatusUpdate(ConfigurationStatusModel configurationStatusModel) {
		ConfigurationStatusModel configurationStatusRes = configurationStatusRepository.findById(configurationStatusModel.getConfigStatusId()).get();
		
		if (!Objects.nonNull(configurationStatusRes)) {
			throw new IHealthPharmException("Not Found", HttpStatus.NOT_FOUND);
		} else {
			configurationStatusRes = configurationStatusRepository.save(configurationStatusModel);
			
			configurationStatusModel.setConfigStatusId(null);
			configurationStatusRepository.save(configurationStatusModel);
		}
		
		return configurationStatusRes;
	}

	@Override
	public ConfigurationStatusModel findConfigurationStatus() {
		 
		return configurationStatusRepository.findFirstRecord();
	}

}
