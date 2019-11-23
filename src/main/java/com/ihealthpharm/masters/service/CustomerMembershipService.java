package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.CustomerMembershipModel;

public interface CustomerMembershipService {

	 CustomerMembershipModel saveCustomerMembershipData(CustomerMembershipModel customerMembership);

	 CustomerMembershipModel updateCustomerMembershipData(CustomerMembershipModel customerMembership);
	
	 List<CustomerMembershipModel> updateCustomersMembershipData(List<CustomerMembershipModel> customersMembership);
	
	 List<CustomerMembershipModel> findAllCustomersMembership();
	
	 CustomerMembershipModel findCustomerMembershipById(Integer customerMembershipId);
	
	 void deleteCustomerMembershipById(Integer customerMembershipIds);
	
	 void deleteCustomersMembershipById(Integer[] customerMembershipIds);
	 
	 CustomerMembershipModel findByMembershipCardNumber(String membershipCardNumber);
	 
	 List<CustomerMembershipModel> findCustomersMembershipBySearch(String key);
	
}