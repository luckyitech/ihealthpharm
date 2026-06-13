package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ItemUOMHelper {

	@Value("${uom.save.response}")
	public String saveUomMessage;
	
	@Value("${uom.update.response}")
	public String updateUomMessage;
	
	@Value("${uom.delete.response}")
	public String deleteUomMessage;
	
	@Value("${uom.retrieve.response}")
	public String retrieveUomMessage;
	
	@Value("${uom.not.found.response}")
	public String notFoundUomMessage;
	
}
