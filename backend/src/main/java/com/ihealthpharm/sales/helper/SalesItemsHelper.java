package com.ihealthpharm.sales.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class SalesItemsHelper {
	/*SalesItems Items Messages*/

	@Value("${salesitems.retrieve.response}")
	public String retrieveSalesItemsItemsMessage;
	
	@Value("${salesitems.update.response}")
	public String updateSalesItemsMessage;

	@Value("${salesitems.save.response}")
	public String saveSalesItemsMessage;
	
	@Value("${salesitems.delete.response}")
	public String deleteSalesItemsMessage;
	
	@Value("${salesitems.not.found.response}")
	public String notFoundMessage;
}
