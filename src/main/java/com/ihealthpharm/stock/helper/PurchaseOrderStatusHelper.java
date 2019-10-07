package com.ihealthpharm.stock.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class PurchaseOrderStatusHelper {

	/*PurchaseOrderStatus Messages*/
	
	@Value("${purchaseOrderStatus.save.response}")
	public String savePurchaseOrderStatusMessage;
	
	@Value("${purchaseOrderStatus.update.response}")
	public String updatePurchaseOrderStatusMessage;
	
	@Value("${purchaseOrderStatus.delete.response}")
	public String deletePurchaseOrderStatusMessage;
	
	@Value("${purchaseOrderStatus.retrieve.response}")
	public String retrievePurchaseOrderStatusMessage;
	
	@Value("${purchaseOrderStatus.not.found.response}")
	public String notFoundPurchaseOrderStatusMessage;

	
}
