package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class InvoiceStatusHelper {

	/*InvoiceStatusStatus Messages*/
	
	@Value("${invoiceStatus.save.response}")
	public String saveInvoiceStatusMessage;
	
	@Value("${invoiceStatus.update.response}")
	public String updateInvoiceStatusMessage;
	
	@Value("${invoiceStatus.delete.response}")
	public String deleteInvoiceStatusMessage;
	
	@Value("${invoiceStatus.retrieve.response}")
	public String retrieveInvoiceStatusMessage;
	
	@Value("${invoiceStatus.not.found.response}")
	public String notFoundInvoiceStatusMessage;

	
}
