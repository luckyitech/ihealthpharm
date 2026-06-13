package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ItemAlternativeHelper {

	@Value("${itemalternative.save.response}")
	public String saveItemAlternativeMessage;
	
	@Value("${itemalternative.update.response}")
	public String updateItemAlternativeMessage;
	
	@Value("${itemalternative.delete.response}")
	public String deleteItemAlternativeMessage;
	
	@Value("${itemalternative.retrieve.response}")
	public String retrieveItemAlternativeMessage;
	
	@Value("${itemalternative.not.found.response}")
	public String notFoundItemAlternativeMessage;

	
}
