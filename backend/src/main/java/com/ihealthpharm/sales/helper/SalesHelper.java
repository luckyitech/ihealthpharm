package com.ihealthpharm.sales.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class SalesHelper {
	/*Sales Messages*/

	@Value("${sales.retrieve.response}")
	public String retrieveSalesMessage;
	
	@Value("${sales.update.response}")
	public String updateSalesMessage;

	@Value("${sales.save.response}")
	public String saveSalesMessage;
	
	@Value("${sales.delete.response}")
	public String deleteSalesMessage;
	
	@Value("${sales.not.found.response}")
	public String notFoundMessage;
}
