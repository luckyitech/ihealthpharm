package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class StockHelper {

	/*Stock Messages*/
	
	@Value("${stock.save.response}")
	public String saveStockMessage;
	
	@Value("${stock.update.response}")
	public String updateStockMessage;
	
	@Value("${stock.delete.response}")
	public String deleteStockMessage;
	
	@Value("${stock.retrieve.response}")
	public String retrieveStockMessage;
	
	@Value("${stock.not.found.response}")
	public String notFoundStockMessage;
	
	/*Stock Adjustment*/
	
	@Value("${stockadjustment.update.response}")
	public String updateStockAdjustmentMessage;
	
	@Value("${stockadjustment.retrieve.response}")
	public String retrieveStockAdjustmentMessage;
	 
	@Value("${stockadjustment.not.found.response}")
	public String notFoundStockAdjustMessage;
	
	
}
