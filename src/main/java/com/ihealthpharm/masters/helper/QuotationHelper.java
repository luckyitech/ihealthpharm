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
	
	@Value("${quotation.save.error.response}")
	public String errorSavingQuotation;

	@Value("${quotation.retrieve.quotationitem.response}")
	public String retrieveQuotationItem;
	
	@Value("${quotation.retrieve.quotationitem.notfound.response}")
	public String retrieveQuotationItemNotFound;

}
