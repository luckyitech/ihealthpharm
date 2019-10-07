package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class PurchaseOrderItemsHelper {

	@Value("${purchaseOrderItems.save.response}")
	public String savePurchaseOrderItemsMessage;
	
	@Value("${purchaseOrderItems.update.response}")
	public String updatePurchaseOrderItemsMessage;
	
	@Value("${purchaseOrderItems.delete.response}")
	public String deletePurchaseOrderItemsMessage;
	
	@Value("${purchaseOrderItems.retrieve.response}")
	public String retrievePurchaseOrderItemsMessage;
	
	@Value("${purchaseOrderItems.not.found.response}")
	public String notFoundPurchaseOrderItemsMessage;

}
