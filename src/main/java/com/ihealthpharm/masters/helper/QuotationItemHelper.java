package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;


@Data
@Configuration
public class QuotationItemHelper {
	@Value("${quotationItem.save.response}")
	public String saveQuotationItemMessage;
	
	@Value("${quotationItem.update.response}")
	public String updateQuotationItemMessage;
	
	@Value("${quotationItem.delete.response}")
	public String deleteQuotationItemMessage;
	
	@Value("${quotationItem.retrieve.response}")
	public String retrieveQuotationItemMessage;
	
	@Value("${quotationItem.not.found.response}")
	public String notFoundQuotationItemMessage;

}
