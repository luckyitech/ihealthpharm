package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EmployeeInterestHelper {

	/*EmployeeInterest Messages*/
	
	@Value("${employeeinterest.save.response}")
	public String saveEmployeeInterestMessage;
	
	@Value("${employeeinterest.update.response}")
	public String updateEmployeeInterestMessage;
	
	@Value("${employeeinterest.delete.response}")
	public String deleteEmployeeInterestMessage;
	
	@Value("${employeeinterest.retrieve.response}")
	public String retrieveEmployeeInterestMessage;
	
	@Value("${employeeinterest.not.found.response}")
	public String notFoundEmployeeInterestMessage;

	
}
