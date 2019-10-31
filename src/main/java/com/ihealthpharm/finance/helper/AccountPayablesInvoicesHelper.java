package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;

public class AccountPayablesInvoicesHelper {

	@Value("${accountPayablesInvoices.save.response}")
	public String saveAccountPayablesInvoicesMessage;
	
	@Value("${accountPayablesInvoices.update.response}")
	public String updateAccountPayablesInvoicesMessage;
	
	@Value("${accountPayablesInvoices.delete.response}")
	public String deleteAccountPayablesInvoicesMessage;
	
	@Value("${accountPayablesInvoices.retrieve.response}")
	public String retrieveAccountPayablesInvoicesMessage;
	
	@Value("${accountPayablesInvoices.not.found.response}")
	public String notFoundAccountPayablesInvoicesMessage;
	
}
