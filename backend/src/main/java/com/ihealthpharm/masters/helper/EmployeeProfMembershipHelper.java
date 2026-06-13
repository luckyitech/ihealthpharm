package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class EmployeeProfMembershipHelper {

	@Value("${employeeprofmembership.save.response}")
	public String saveEmployeeProfMembershipMessage;
	
	@Value("${employeeprofmembership.update.response}")
	public String updateEmployeeProfMembershipMessage;
	
	@Value("${employeeprofmembership.delete.response}")
	public String deleteEmployeeProfMembershipMessage;
	
	@Value("${employeeprofmembership.retrieve.response}")
	public String retrieveEmployeeProfMembershipMessage;
	
	@Value("${employeeprofmembership.not.found.response}")
	public String notFoundEmployeeProfMembershipMessage;

	
}
