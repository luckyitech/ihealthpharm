package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class UsersHelper {

	/*Distributor Messages*/
	
	@Value("${users.save.response}")
	public String saveUsersMessage;
	
	@Value("${users.update.response}")
	public String updateUsersMessage;
	
	@Value("${users.delete.response}")
	public String deleteUsersMessage;
	
	@Value("${users.retrieve.response}")
	public String retrieveUsersMessage;
	
	@Value("${users.not.found.response}")
	public String notFoundUsersMessage;

}
