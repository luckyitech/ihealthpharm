package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.CustomerMembershipModel;

public interface CustomerMembershipService {

	 CustomerMembershipModel saveCustomerMembershipData(CustomerMembershipModel customerMembership);

	 CustomerMembershipModel updateCustomerMembershipData(CustomerMembershipModel customerMembership);
	
	 List<CustomerMembershipModel> updateCustomersMembershipData(List<CustomerMembershipModel> customersMembership);
	
	 List<CustomerMembershipModel> findAllCustomersMembership();
	
	 CustomerMembershipModel findCustomerMembershipById(int customerMembershipId);
	
	 void deleteCustomerMembershipById(int customerMembershipIds);
	
	 void deleteCustomersMembershipById(int[] customerMembershipIds);
	
}