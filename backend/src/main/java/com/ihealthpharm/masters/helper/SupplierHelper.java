package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class SupplierHelper {

	
	@Value("${supplier.save.response}")
	public String saveSupplierMessage;
	
	@Value("${supplier.update.response}")
	public String updateSupplierMessage;
	
	@Value("${supplier.delete.response}")
	public String deleteSupplierMessage;
	
	@Value("${supplier.retrieve.response}")
	public String retrieveSupplierMessage;
	
	@Value("${supplier.not.found.response}")
	public String notFoundSupplierMessage;

}
