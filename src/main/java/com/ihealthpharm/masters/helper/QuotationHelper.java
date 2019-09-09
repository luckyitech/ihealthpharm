package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class QuotationHelper {

	/*Quotation Messages*/
	
	@Value("${quotation.save.response}")
	public String saveQuotationMessage;
	
	@Value("${quotation.update.response}")
	public String updateQuotationMessage;
	
	@Value("${quotation.delete.response}")
	public String deleteQuotationMessage;
	
	@Value("${quotation.retrieve.response}")
	public String retrieveQuotationMessage;
	
	@Value("${quotation.not.found.response}")
	public String notFoundQuotationMessage;

}
