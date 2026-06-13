package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ItemSupplierHelper {

	
	@Value("${itemSupplier.save.response}")
	public String saveItemSupplierMessage;
	
	@Value("${itemSupplier.update.response}")
	public String updateItemSupplierMessage;
	
	@Value("${itemSupplier.delete.response}")
	public String deleteItemSupplierMessage;
	
	@Value("${itemSupplier.retrieve.response}")
	public String retrieveItemSupplierMessage;
	
	@Value("${itemSupplier.not.found.response}")
	public String notFoundItemSupplierMessage;
	
}
