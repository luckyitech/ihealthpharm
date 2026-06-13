package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class DeliveryTypesHelper {

	/*DeliveryTypesStatus Messages*/
	
	@Value("${deliveryTypes.save.response}")
	public String saveDeliveryTypesMessage;
	
	@Value("${deliveryTypes.update.response}")
	public String updateDeliveryTypesMessage;
	
	@Value("${deliveryTypes.delete.response}")
	public String deleteDeliveryTypesMessage;
	
	@Value("${deliveryTypes.retrieve.response}")
	public String retrieveDeliveryTypesMessage;
	
	@Value("${deliveryTypes.not.found.response}")
	public String notFoundDeliveryTypesMessage;

	
}
