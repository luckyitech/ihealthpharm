package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ProviderHelper {

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


	
}
