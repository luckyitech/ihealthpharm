package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ItemGroupHelper {

	@Value("${itemGroup.save.response}")
	public String saveItemGroupMessage;
	
	@Value("${itemGroup.update.response}")
	public String updateItemGroupMessage;
	
	@Value("${itemGroup.delete.response}")
	public String deleteItemGroupMessage;
	
	@Value("${itemGroup.retrieve.response}")
	public String retrieveItemGroupMessage;
	
	@Value("${itemGroup.not.found.response}")
	public String notFoundItemGroupMessage;

	
}
