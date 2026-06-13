package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EmployeePublicationHelper {

	@Value("${employeepublication.save.response}")
	public String saveEmployeePublicationMessage;
	
	@Value("${employeepublication.update.response}")
	public String updateEmployeePublicationMessage;
	
	@Value("${employeepublication.delete.response}")
	public String deleteEmployeePublicationMessage;
	
	@Value("${employeepublication.retrieve.response}")
	public String retrieveEmployeePublicationMessage;
	
	@Value("${employeepublication.not.found.response}")
	public String notFoundEmployeePublicationMessage;

	
}
