package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class PaymentTypeHelper {

	/*PaymentType Messages*/
	
	@Value("${paymentType.save.response}")
	public String savePaymentTypeMessage;
	
	@Value("${paymentType.update.response}")
	public String updatePaymentTypeMessage;
	
	@Value("${paymentType.delete.response}")
	public String deletePaymentTypeMessage;
	
	@Value("${paymentType.retrieve.response}")
	public String retrievePaymentTypeMessage;
	
	@Value("${paymentType.not.found.response}")
	public String notFoundPaymentTypeMessage;

	
}
