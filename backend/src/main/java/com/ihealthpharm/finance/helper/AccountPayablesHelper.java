package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class AccountPayablesHelper {

	@Value("${accountPayables.save.response}")
	public String saveAccountPayablesMessage;
	
	@Value("${accountPayables.update.response}")
	public String updateAccountPayablesMessage;
	
	@Value("${accountPayables.delete.response}")
	public String deleteAccountPayablesMessage;
	
	@Value("${accountPayables.retrieve.response}")
	public String retrieveAccountPayablesMessage;
	
	@Value("${accountPayables.not.found.response}")
	public String notFoundAccountPayablesMessage;
	
}
