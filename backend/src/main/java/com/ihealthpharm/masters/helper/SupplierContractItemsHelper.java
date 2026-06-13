package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class SupplierContractItemsHelper {

	
	@Value("${suppliercontractitems.save.response}")
	public String saveSupplierContractItemsMessage;
	
	@Value("${suppliercontractitems.update.response}")
	public String updateSupplierContractItemsMessage;
	
	@Value("${suppliercontractitems.delete.response}")
	public String deleteSupplierContractItemsMessage;
	
	@Value("${suppliercontractitems.retrieve.response}")
	public String retrieveSupplierContractItemsMessage;
	
	@Value("${suppliercontractitems.not.found.response}")
	public String notFoundSupplierContractItemsMessage;

}
