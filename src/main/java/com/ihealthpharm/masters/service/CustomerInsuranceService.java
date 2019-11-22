package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.masters.model.CustomerModel;


public interface CustomerInsuranceService {

	 CustomerInsuranceModel saveCustomerInsuranceData(CustomerInsuranceModel customerInsurance);

	 CustomerInsuranceModel updateCustomerInsuranceData(CustomerInsuranceModel customerInsurance);
	
	 List<CustomerInsuranceModel> updateCustomersInsuranceData(List<CustomerInsuranceModel> customersInsurance);
	
	 List<CustomerInsuranceModel> findAllCustomersInsurance();
	
	 CustomerInsuranceModel findCustomerInsuranceById(Integer customerInsuranceId);
	 
	 CustomerInsuranceModel findCustomerInsuranceByPolicyCode(String policyCode);
	 
	 List<CustomerInsuranceModel> findCustomersInsuranceBySearch(String searchKey);
	 
	 CustomerInsuranceModel findCustomerInsuranceByCustomer(CustomerModel customer);
	
	 void deleteCustomerInsuranceById(Integer customerInsuranceIds);
	
	 void deleteCustomersInsuranceById(Integer[] customerInsuranceIds);
	
}