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
	
	List<CustomerModel> findLimitedCorporateCustomersData();
	
	List<CustomerModel> findCorporateCustomersByPhNo(String phno);

	List<CustomerModel> findAllCorporateCustomersByNameSearch(String customerName);

	List<String> findAllCustomersFirstNameBySearch(String searchTerm);

	List<String> findAllCustomersLastNameBySearch(String searchTerm);

	List<CustomerModel> findLimitedStaffCustomersData();

	List<CustomerModel> findAllStaffCustomersByNameSearch(String customerName);

	List<CustomerModel> findStaffCustomersByPhNo(String phno);

	CustomerModel getCustomerModelByName(String name);

	CustomerModel getCustomerModelBySourceRefAndType(String sourceRef, String sourceType);

}