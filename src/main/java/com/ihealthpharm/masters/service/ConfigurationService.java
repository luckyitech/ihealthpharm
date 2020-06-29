package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.ConfigurationModel;

public interface ConfigurationService {
	
	public ConfigurationModel saveconfig(ConfigurationModel config);
	
	public void deleteconfig(ConfigurationModel configId);
	
	public List<ConfigurationModel> getAllConfigurations();
	
	public ConfigurationModel getconfigByconfigId(Integer configId);
	
	public ConfigurationModel updateconfig(ConfigurationModel config);
	
	public Integer getMaxDiscount();
	
	public Integer getMargin();
}
