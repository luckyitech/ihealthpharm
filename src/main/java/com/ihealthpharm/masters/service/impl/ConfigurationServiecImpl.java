package com.ihealthpharm.masters.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.dao.ConfigurationRepository;
import com.ihealthpharm.masters.model.ConfigurationModel;
import com.ihealthpharm.masters.service.ConfigurationService;

@Service
public class ConfigurationServiecImpl implements ConfigurationService {

	@Autowired
	ConfigurationRepository configurationRepository;
	
	@Override
	public ConfigurationModel saveconfig(ConfigurationModel config) {
		// TODO Auto-generated method stub
		
		if(config.getActiveS() == 'Y')
		{
			List<ConfigurationModel> configList = null;
			
			configList = configurationRepository.findByConfigDesc(config.getConfigDesc());
			for(int i=0;i<configList.size();i++) {
				configList.get(i).setActiveS('N');
			}
			
			configurationRepository.saveAll(configList);
		}
		return configurationRepository.save(config);
	}

	@Override
	public void deleteconfig(ConfigurationModel configId) {
		// TODO Auto-generated method stub
		configurationRepository.delete(configId);

	}

	@Override
	public List<ConfigurationModel> getAllConfigurations() {
		// TODO Auto-generated method stub
		return configurationRepository.findAll();
	}

	@Override
	public ConfigurationModel getconfigByconfigId(Integer configId) {
		// TODO Auto-generated method stub
		return configurationRepository.getOne(configId);
	}

	@Override
	public ConfigurationModel updateconfig(ConfigurationModel config) {
		// TODO Auto-generated method stub
		if(config.getActiveS() == 'Y')
		{
			List<ConfigurationModel> configList = null;
			
			configList = configurationRepository.findByConfigDesc(config.getConfigDesc());
			for(int i=0;i<configList.size();i++) {
				configList.get(i).setActiveS('N');
			}
			
			configurationRepository.saveAll(configList);
		}
		return configurationRepository.save(config);
	}

	@Override
	public Integer getMaxDiscount() {
		// TODO Auto-generated method stub
		return configurationRepository.findMaxDiscount();
	}

	@Override
	public Integer getMargin() {
		// TODO Auto-generated method stub
		return configurationRepository.findMargin();
	}

}
