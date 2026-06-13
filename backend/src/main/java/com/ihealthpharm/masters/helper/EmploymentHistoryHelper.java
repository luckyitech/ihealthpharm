package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EmploymentHistoryHelper {

	@Value("${employeehistory.save.response}")
	public String saveEmploymentHistoryMessage;
	
	@Value("${employeehistory.update.response}")
	public String updateEmploymentHistoryMessage;
	
	@Value("${employeehistory.delete.response}")
	public String deleteEmploymentHistoryMessage;
	
	@Value("${employeehistory.retrieve.response}")
	public String retrieveEmploymentHistoryMessage;
	
	@Value("${employeehistory.not.found.response}")
	public String notFoundEmploymentHistoryMessage;

	
}
