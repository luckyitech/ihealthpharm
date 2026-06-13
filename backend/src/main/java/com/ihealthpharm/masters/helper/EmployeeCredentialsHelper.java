package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EmployeeCredentialsHelper {

	
	@Value("${employeecredentials.save.response}")
	public String saveEmployeeCredentialsMessage;
	
	@Value("${employeecredentials.update.response}")
	public String updateEmployeeCredentialsMessage;
	
	@Value("${employeecredentials.delete.response}")
	public String deleteEmployeeCredentialsMessage;
	
	@Value("${employeecredentials.retrieve.response}")
	public String retrieveEmployeeCredentialsMessage;
	
	@Value("${employeecredentials.not.found.response}")
	public String notFoundEmployeeCredentialsMessage;

	
}
