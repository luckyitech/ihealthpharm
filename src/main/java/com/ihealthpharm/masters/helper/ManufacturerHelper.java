package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


@Configuration
@Data
public class ManufacturerHelper {
	
	
	@Value("${manufacturer.save.response}")
	public String saveManufacturerMessage;
	
	
	@Value("${manufacturer.update.response}")
	public String updateManufacturerMessage;
	
	@Value("${manufacturer.delete.response}")
	public String deleteManufacturerMessage;
	
	
	@Value("${manufacturer.retrieve.response}")
	public String retrieveManufacturerMessage;
	
	@Value("${manufacturer.not.found.response}")
	public String notFoundManufacturerMessage;



}
