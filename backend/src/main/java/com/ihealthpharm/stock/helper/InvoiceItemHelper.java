package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class InvoiceItemHelper {

	/*InvoiceItem Messages*/
	
	@Value("${invoiceItem.save.response}")
	public String saveInvoiceItemMessage;
	
	@Value("${invoiceItem.update.response}")
	public String updateInvoiceItemMessage;
	
	@Value("${invoiceItem.delete.response}")
	public String deleteInvoiceItemMessage;
	
	@Value("${invoiceItem.retrieve.response}")
	public String retrieveInvoiceItemMessage;
	
	@Value("${invoiceItem.not.found.response}")
	public String notFoundInvoiceItemMessage;

	
}
