package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class SpecializationHelper {

	@Value("${specialization.save.response}")
	public String saveSpecializationMessage;
	
	@Value("${specialization.update.response}")
	public String updateSpecializationMessage;
	
	@Value("${specialization.delete.response}")
	public String deleteSpecializationMessage;
	
	@Value("${specialization.retrieve.response}")
	public String retrieveSpecializationMessage;
	
	@Value("${specialization.not.found.response}")
	public String notFoundSpecializationMessage;


}
