package com.ihealthpharm.userhistory.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class UserHistoryHelper {

	@Value("${userhistory.save.response}")
	public String saveUserHistoryMessage;
	
	@Value("${userhistory.update.response}")
	public String updateUserHistoryMessage;
	
	@Value("${userhistory.delete.response}")
	public String deleteUserHistoryMessage;
	
	@Value("${userhistory.retrieve.response}")
	public String retrieveUserHistoryMessage;
	
	@Value("${userhistory.not.found.response}")
	public String notFoundUserHistoryMessage;
}
