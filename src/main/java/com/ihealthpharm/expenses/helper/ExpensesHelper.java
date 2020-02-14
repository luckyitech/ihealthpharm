package com.ihealthpharm.expenses.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
public class ExpensesHelper {
	
	@Value("${expenses.save.response}")
	public String saveExpensesMessage;
	
	@Value("${expenses.update.response")
	public String updateExpeneMessage;
	
	@Value("${expenses.delete.response}")
	public String deleteExpenseMessage;
	
	@Value("${expenses.retrieve.response}")
	public String RetrieveExpenseMessage;
	
	@Value("${expenses.not.found.response}")
	public String notFoundExpesesMessage;

}
