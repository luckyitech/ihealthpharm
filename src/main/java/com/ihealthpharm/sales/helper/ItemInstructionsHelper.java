package com.ihealthpharm.sales.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ItemInstructionsHelper {
	
	@Value("${itemInstructions.retrieve.response}")
	public String retrieveItemsInstructionsMessage;
	
	@Value("${itemInstructions.update.response}")
	public String updateItemsInstructionsMessage;

	@Value("${itemInstructions.save.response}")
	public String saveItemsInstructionsMessage;
	
	@Value("${itemInstructions.delete.response}")
	public String deleteItemsInstructionsMessage;
	
	@Value("${itemInstructions.not.found.response}")
	public String notFoundMessage;

}
