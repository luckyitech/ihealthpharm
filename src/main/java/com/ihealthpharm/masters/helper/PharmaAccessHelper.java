package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class PharmaAccessHelper {

	@Value("${pharmaaccess.save.response}")
	public String savePharmaAccessMessage;
	
	@Value("${pharmaaccess.update.response}")
	public String updatePharmaAccessMessage;
	
	@Value("${pharmaaccess.delete.response}")
	public String deletePharmaAccessMessage;
	
	@Value("${pharmaaccess.retrieve.response}")
	public String retrievePharmaAccessMessage;
	
	@Value("${pharmaaccess.not.found.response}")
	public String notFoundPharmaAccessMessage;

	
}
