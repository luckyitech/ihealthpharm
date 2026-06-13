package com.ihealthpharm.sales.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class SalesReturnItemHelper {
	
	
	@Value("${saleReturnItem.save.response}")
	public String saveSalesReturnItemMessage;
	
	@Value("${saleReturnItem.update.response}")
	public String updateSalesReturnItemMessage;
	
	@Value("${saleReturnItem.delete.response}")
	public String deleteSalesReturnItemMessage;
	
	@Value("${saleReturnItem.retrieve.response}")
	public String retrieveSalesReturnItemMessage;
	
	@Value("${saleReturnItem.not.found.response}")
	public String notFoundSalesReturnItemMessage;

}
