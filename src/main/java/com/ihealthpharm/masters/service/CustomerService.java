package com.ihealthpharm.masters.service;


import java.util.List;

import com.ihealthpharm.masters.model.CustomerModel;


public interface CustomerService
{
    
	 CustomerModel saveCustomerData(CustomerModel customer);

	 CustomerModel updateCustomerData(CustomerModel customer);
	
	 List<CustomerModel> updateCustomersData(List<CustomerModel> customers);
	
	 List<CustomerModel> findAllCustomers();
	 
	 List<CustomerModel> findLimitedCustomers();
	
	 CustomerModel findCustomerById(int customerId);
	
	 void deleteCustomerById(int customerIds);
	
	 void deleteCustomersById(int[] customerIds);
	 

	List<CustomerModel> findCustomerByActive();

	List<CustomerModel> findCustomersByName(String customerName);

}