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
	
	 CustomerModel findCustomerById(Integer customerId);
	
	 void deleteCustomerById(Integer customerIds);
	
	 void deleteCustomersById(Integer[] customerIds);



	List<CustomerModel> findCustomerByActive();

	List<CustomerModel> findCustomersByName(String customerName);
	
	List<CustomerModel> findCustomersByPhNo(String phno);

	List<CustomerModel> findAllCustomersByNameSearch(String customerName);

	List<CustomerModel> findAllLimitedCustomersData();

	List<CustomerModel> findLimitedCustomersData();

}