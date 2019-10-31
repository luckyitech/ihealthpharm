package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;

public class AccountReceivablesHelper {

	@Value("${accountReceivables.save.response}")
	public String saveAccountReceivablesMessage;
	
	@Value("${accountReceivables.update.response}")
	public String updateAccountReceivablesMessage;
	
	@Value("${accountReceivables.delete.response}")
	public String deleteAccountReceivablesMessage;
	
	@Value("${accountReceivables.retrieve.response}")
	public String retrieveAccountReceivablesMessage;
	
	@Value("${accountReceivables.not.found.response}")
	public String notFoundAccountReceivablesMessage;
}
