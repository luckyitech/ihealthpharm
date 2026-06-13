package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class BankTransactionsHelper {
	
	@Value("${bankTransactions.save.response}")
	public String saveBankTransactionsMessage;
	
	@Value("${bankTransactions.update.response}")
	public String updateBankTransactionsMessage;
	
	@Value("${bankTransactions.delete.response}")
	public String deleteBankTransactionsMessage;
	
	@Value("${bankTransactions.retrieve.response}")
	public String retrieveBankTransactionsMessage;
	
	@Value("${bankTransactions.not.found.response}")
	public String notFoundBankTransactionsMessage;
}
