package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class AccountTypeHelper {

	@Value("${accountType.save.response}")
	public String saveAccountTypeMessage;
	
	@Value("${accountType.update.response}")
	public String updateAccountTypeMessage;
	
	@Value("${accountType.delete.response}")
	public String deleteAccountTypeMessage;
	
	@Value("${accountType.retrieve.response}")
	public String retrieveAccountTypeMessage;
	
	@Value("${accountType.not.found.response}")
	public String notFoundAccountTypeMessage;
}
