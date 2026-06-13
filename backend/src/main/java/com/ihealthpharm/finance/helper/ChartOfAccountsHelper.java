package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ChartOfAccountsHelper {

	@Value("${chartOfAccounts.save.response}")
	public String saveChartOfAccountsMessage;
	
	@Value("${chartOfAccounts.update.response}")
	public String updateChartOfAccountsMessage;
	
	@Value("${chartOfAccounts.delete.response}")
	public String deleteChartOfAccountsMessage;
	
	@Value("${chartOfAccounts.retrieve.response}")
	public String retrieveChartOfAccountsMessage;
	
	@Value("${chartOfAccounts.not.found.response}")
	public String notFoundChartOfAccountsMessage;
}
