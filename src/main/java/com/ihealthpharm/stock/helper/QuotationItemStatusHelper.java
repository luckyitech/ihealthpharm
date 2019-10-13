package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
public class QuotationItemStatusHelper {
	
	@Value("${quotationItemStatus.save.response}")
	public String saveQuotationItemStatusMessage;

	@Value("${quotationItemStatus.update.response}")
	public String updateQuotationItemStatusMessage;

	@Value("${quotationItemStatus.delete.response}")
	public String deleteQuotationItemStatusMessage;

	@Value("${quotationItemStatus.retrieve.response}")
	public String retrieveQuotationItemStatusMessage;

	@Value("${quotationItemStatus.not.found.response}")
	public String notFoundQuotationItemStatusMessage;

}
