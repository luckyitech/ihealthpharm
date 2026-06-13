package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class UserRolesHelper {

	@Value("${userroles.save.response}")
	public String saveUserRolesMessage;
	
	@Value("${userroles.update.response}")
	public String updateUserRolesMessage;
	
	@Value("${userroles.delete.response}")
	public String deleteUserRolesMessage;
	
	@Value("${userroles.retrieve.response}")
	public String retrieveUserRolesMessage;
	
	@Value("${userroles.not.found.response}")
	public String notFoundUserRolesMessage;

}
