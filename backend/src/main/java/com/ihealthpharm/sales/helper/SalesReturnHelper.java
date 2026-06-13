package com.ihealthpharm.sales.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class SalesReturnHelper {

	
	@Value("${salesReturn.save.response}")
	public String saveSalesReturnMessage;
	
	@Value("${salesReturn.delete.response}")
	public String deleteSalesReturnMessage;
	
	@Value("${salesReturn.update.response}")
	public String updateSalesReturnMessage;
	
	@Value("${salesReturn.not.found.response}")
	public String notFoundMessage;	
	
	@Value("${salesReturn.retrieve.response}")
	public String retrieveSalesReturnMessage;
	
}
