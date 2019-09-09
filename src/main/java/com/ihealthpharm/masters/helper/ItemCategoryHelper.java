package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ItemCategoryHelper {


	@Value("${itemCategory.save.response}")
	public String saveItemCategoryMessage;
	
	
	@Value("${itemCategory.update.response}")
	public String updateItemCategoryMessage;
	
	@Value("${itemCategory.delete.response}")
	public String deleteItemCategoryMessage;
	
	
	@Value("${itemCategory.retrieve.response}")
	public String retrieveItemCategoryMessage;
	
	@Value("${itemCategory.not.found.response}")
	public String notFoundItemCategoryMessage;
}
