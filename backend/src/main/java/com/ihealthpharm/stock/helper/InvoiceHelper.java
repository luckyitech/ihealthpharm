package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class InvoiceHelper {

	/*Invoice Messages*/
	
	@Value("${invoice.save.response}")
	public String saveInvoiceMessage;
	
	@Value("${invoice.update.response}")
	public String updateInvoiceMessage;
	
	@Value("${invoice.delete.response}")
	public String deleteInvoiceMessage;
	
	@Value("${invoice.retrieve.response}")
	public String retrieveInvoiceMessage;
	
	@Value("${invoice.not.found.response}")
	public String notFoundInvoiceMessage;

	
}
