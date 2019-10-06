package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class InsuranceHelper {


	@Value("${insurance.save.response}")
	public String saveInsuranceMessage;
	
	@Value("${insurance.update.response}")
	public String updateInsuranceMessage;
	
	@Value("${insurance.delete.response}")
	public String deleteInsuranceMessage;
	
	@Value("${insurance.retrieve.response}")
	public String retrieveInsuranceMessage;
	
	@Value("${insurance.not.found.response}")
	public String notFoundInsuranceMessage;
	
	
}
