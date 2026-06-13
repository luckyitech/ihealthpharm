package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class IndentHelper {

	
	@Value("${indent.save.response}")
	public String saveIndentMessage;
	
	@Value("${indent.update.response}")
	public String updateIndentMessage;
	
	@Value("${indent.delete.response}")
	public String deleteIndentMessage;
	
	@Value("${indent.retrieve.response}")
	public String retrieveIndentMessage;
	
	@Value("${indent.not.found.response}")
	public String notFoundIndentMessage;

}
