package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class CustomerInsuranceHelper {

	@Value("${customerInsurance.save.response}")
	public String saveCustomerInsuranceMessage;
	
	@Value("${customerInsurance.update.response}")
	public String updateCustomerInsuranceMessage;
	
	@Value("${customerInsurance.delete.response}")
	public String deleteCustomerInsuranceMessage;
	
	@Value("${customerInsurance.retrieve.response}")
	public String retrieveCustomerInsuranceMessage;
	
	@Value("${customerInsurance.not.found.response}")
	public String notFoundCustomerInsuranceMessage;

	
	
}
