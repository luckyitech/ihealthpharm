package com.ihealthpharm.masters.helper;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class CustomerHelper {

	@Value("${customer.save.response}")
	public String saveCustomerMessage;
	
	@Value("${customer.update.response}")
	public String updateCustomerMessage;
	
	@Value("${customer.delete.response}")
	public String deleteCustomerMessage;
	
	@Value("${customer.retrieve.response}")
	public String retrieveCustomerMessage;
	
	@Value("${customer.not.found.response}")
	public String notFoundCustomerMessage;

	
	
	
	
}
