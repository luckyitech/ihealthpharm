package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class CountryStateHelper {

	/*Country State Messages*/

	@Value("${country.retrieve.response}")
	public String retrieveCountryMessage;
	
	@Value("${state.retrieve.response}")
	public String retrieveProvinceMessage;

	@Value("${country.save.response}")
	public String saveCountryMessage;
	
	@Value("${state.save.response}")
	public String saveProvinceMessage;
}
