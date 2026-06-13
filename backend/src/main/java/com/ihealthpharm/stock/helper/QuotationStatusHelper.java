package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class QuotationStatusHelper {

	/*QuotationStatusStatus Messages*/
	
	@Value("${quotationStatus.save.response}")
	public String saveQuotationStatusMessage;
	
	@Value("${quotationStatus.update.response}")
	public String updateQuotationStatusMessage;
	
	@Value("${quotationStatus.delete.response}")
	public String deleteQuotationStatusMessage;
	
	@Value("${quotationStatus.retrieve.response}")
	public String retrieveQuotationStatusMessage;
	
	@Value("${quotationStatus.not.found.response}")
	public String notFoundQuotationStatusMessage;

}
