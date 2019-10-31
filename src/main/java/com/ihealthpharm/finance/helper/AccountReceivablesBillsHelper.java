package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;

public class AccountReceivablesBillsHelper {

	@Value("${accountReceivablesBills.save.response}")
	public String saveAccountReceivablesBillsMessage;
	
	@Value("${accountReceivablesBills.update.response}")
	public String updateAccountReceivablesBillsMessage;
	
	@Value("${accountReceivablesBills.delete.response}")
	public String deleteAccountReceivablesBillsMessage;
	
	@Value("${accountReceivablesBills.retrieve.response}")
	public String retrieveAccountReceivablesBillsMessage;
	
	@Value("${accountReceivablesBills.not.found.response}")
	public String notFoundAccountReceivablesBillsMessage;
}
