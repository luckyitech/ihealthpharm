package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class CustomerMembershipHelper {

	
	@Value("${customerMembership.save.response}")
	public String saveCustomerMembershipMessage;
	
	@Value("${customerMembership.update.response}")
	public String updateCustomerMembershipMessage;
	
	@Value("${customerMembership.delete.response}")
	public String deleteCustomerMembershipMessage;
	
	@Value("${customerMembership.retrieve.response}")
	public String retrieveCustomerMembershipMessage;
	
	@Value("${customerMembership.not.found.response}")
	public String notFoundCustomerMembershipMessage;
}
