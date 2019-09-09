package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ProviderHelper {

	/* provider messages */
	@Value("${provider.save.response}")
	public String saveProviderMessage;
	
	@Value("${provider.update.response}")
	public String updateProviderMessage;
	
	@Value("${provider.delete.response}")
	public String deleteProviderMessage;
	
	@Value("${provider.retrieve.response}")
	public String retrieveProvideMessage;
	
	@Value("${providertypelookup.retrieve.response}")
	public String retrieveProviderTypeLookupMessage;
	
	@Value("${provider.not.found.response}")
	public String notFoundProvideMessage;

	
	/*Distributor Messages*/
	
	@Value("${distrubutor.save.response}")
	public String saveDistrubutorMessage;
	
	@Value("${distrubutor.update.response}")
	public String updateDistrubutorMessage;
	
	@Value("${distrubutor.delete.response}")
	public String deleteDistrubutorMessage;
	
	@Value("${distrubutor.retrieve.response}")
	public String retrieveDistrubutorMessage;
	
	@Value("${distrubutor.not.found.response}")
	public String notFoundDistrubutorMessage;

	/*Employee Messages*/
	
	@Value("${employee.save.response}")
	public String saveEmployeeMessage;
	
	@Value("${employee.update.response}")
	public String updateEmployeeMessage;
	
	@Value("${employee.delete.response}")
	public String deleteEmployeeMessage;
	
	@Value("${employee.retrieve.response}")
	public String retrieveEmployeeMessage;
	
	@Value("${employee.not.found.response}")
	public String notFoundEmployeeMessage;

	
}
