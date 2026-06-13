package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ItemGenericNamesHelper {

	
	@Value("${itemGenericName.save.response}")
	public String saveItemGenericNameMessage;
	
	@Value("${itemGenericName.update.response}")
	public String updateItemGenericNameMessage;
	
	@Value("${itemGenericName.delete.response}")
	public String deleteItemGenericNameMessage;
	
	@Value("${itemGenericName.retrieve.response}")
	public String retrieveItemGenericNameMessage;
	
	@Value("${itemGenericName.not.found.response}")
	public String notFoundItemGenericNameMessage;
	
	
}
