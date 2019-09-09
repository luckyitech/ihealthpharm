package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class PurchaseOrderHelper {

	/*PurchaseOrder Messages*/
	
	@Value("${purchaseorder.save.response}")
	public String savePurchaseOrderMessage;
	
	@Value("${purchaseorder.update.response}")
	public String updatePurchaseOrderMessage;
	
	@Value("${purchaseorder.delete.response}")
	public String deletePurchaseOrderMessage;
	
	@Value("${purchaseorder.retrieve.response}")
	public String retrievePurchaseOrderMessage;
	
	@Value("${purchaseorder.not.found.response}")
	public String notFoundPurchaseOrderMessage;

}
