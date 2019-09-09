package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class RolesHelper {

	/*Roles Messages*/
	
	@Value("${roles.save.response}")
	public String saveRolesMessage;
	
	@Value("${roles.update.response}")
	public String updateRolesMessage;
	
	@Value("${roles.delete.response}")
	public String deleteRolesMessage;
	
	@Value("${roles.retrieve.response}")
	public String retrieveRolesMessage;
	
	@Value("${roles.not.found.response}")
	public String notFoundRolesMessage;

}
