package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class PurchaseReturnHelper {
	
	@Value("${purchaseReturn.save.response}")
	public String savePurchaseReturnMessage;
	
	@Value("${purchaseReturn.update.response}")
	public String updatePurchaseReturnMessage;
	
	@Value("${purchaseReturn.delete.response}")
	public String deletePurchaseReturnMessage;
	
	@Value("${purchaseReturn.retrieve.response}")
	public String retrievePurchaseReturnMessage;
	
	@Value("${purchaseReturn.not.found.response}")
	public String notFoundPurchaseReturnMessage;

	
}
