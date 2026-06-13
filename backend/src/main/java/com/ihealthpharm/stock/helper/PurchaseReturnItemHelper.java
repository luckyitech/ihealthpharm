package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class PurchaseReturnItemHelper {
	
	@Value("${purchaseReturnItem.save.response}")
	public String savePurchaseReturnItemMessage;
	
	@Value("${purchaseReturnItem.update.response}")
	public String updatePurchaseReturnItemMessage;
	
	@Value("${purchaseReturnItem.delete.response}")
	public String deletePurchaseReturnItemMessage;
	
	@Value("${purchaseReturnItem.retrieve.response}")
	public String retrievePurchaseReturnItemMessage;
	
	@Value("${purchaseReturnItem.not.found.response}")
	public String notFoundPurchaseReturnItemMessage;

	
}
