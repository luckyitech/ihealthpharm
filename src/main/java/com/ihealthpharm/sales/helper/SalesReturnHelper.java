package com.ihealthpharm.sales.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class SalesReturnHelper {

	@Value("${salesReturn.retrieve.response}")
	public String retrieveSalesReturnMessage;
	
	@Value("${salesReturn.save.response}")
	public String saveSalesReturnMessage;
	
	@Value("${salesReturn.not.found.response}")
	public String notFoundMessage;	
	
}
