package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ItemFormsHelper {
	

	@Value("${itemForms.save.response}")
	public String saveItemFormsMessage;
	
	
	@Value("${itemForms.update.response}")
	public String updateItemFormsMessage;
	
	@Value("${itemForms.delete.response}")
	public String deleteItemFormsMessage;
	
	
	@Value("${itemForms.retrieve.response}")
	public String retrieveItemFormsMessage;
	
	@Value("${itemForms.not.found.response}")
	public String notFoundItemFormsMessage;

}
