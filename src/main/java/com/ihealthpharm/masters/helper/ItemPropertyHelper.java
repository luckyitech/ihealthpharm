package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Vikas
 * All the drug related properties are available here.
 */
@Configuration
@PropertySource("classpath:message.properties")
@Getter
@Setter
public class ItemPropertyHelper {

	@Value("${drug.save.response}")
	public String saveMessage;
	
	@Value("${drug.update.response}")
	public String updateMessage;
	
	@Value("${drug.delete.response}")
	public String deleteMessage;
	
	@Value("${drug.retrieve.response}")
	public String retrieveMessage;
	
	@Value("${drug.not.found.response}")
	public String notFoundMessage;
	
}
