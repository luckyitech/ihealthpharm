package com.ihealthpharm.masters.service;

import com.ihealthpharm.masters.model.ConfigurationStatusModel;

public interface ConfigurationStatusService {

	public ConfigurationStatusModel configurationStatusSave(ConfigurationStatusModel configurationStatusModel);
	
	public ConfigurationStatusModel configurationStatusUpdate(ConfigurationStatusModel configurationStatusModel);
	
	public ConfigurationStatusModel findConfigurationStatus();
}
