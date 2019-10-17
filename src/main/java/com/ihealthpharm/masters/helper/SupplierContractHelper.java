package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class SupplierContractHelper {

	@Value("${suppliercontract.save.response}")
	public String saveSupplierContractMessage;
	
	@Value("${suppliercontract.update.response}")
	public String updateSupplierContractMessage;
	
	@Value("${suppliercontract.delete.response}")
	public String deleteSupplierContractMessage;
	
	@Value("${suppliercontract.retrieve.response}")
	public String retrieveSupplierContractMessage;
	
	@Value("${suppliercontract.not.found.response}")
	public String notFoundSupplierContractMessage;

}
