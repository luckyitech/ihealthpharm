package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ItemsBinHelper {


	@Value("${itemsBin.save.response}")
	public String saveItemsBinMessage;
	
	@Value("${itemsBin.update.response}")
	public String updateItemsBinMessage;
	
	@Value("${itemsBin.delete.response}")
	public String deleteItemsBinMessage;
	
	
	@Value("${itemsBin.retrieve.response}")
	public String retrieveItemsBinMessage;
	
	@Value("${itemsBin.not.found.response}")
	public String notFoundItemsBinMessage;
}
