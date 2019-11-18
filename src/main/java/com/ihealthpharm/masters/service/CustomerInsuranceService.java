package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.masters.model.CustomerModel;


public interface CustomerInsuranceService {

	 CustomerInsuranceModel saveCustomerInsuranceData(CustomerInsuranceModel customerInsurance);

	 CustomerInsuranceModel updateCustomerInsuranceData(CustomerInsuranceModel customerInsurance);
	
	 List<CustomerInsuranceModel> updateCustomersInsuranceData(List<CustomerInsuranceModel> customersInsurance);
	
	 List<CustomerInsuranceModel> findAllCustomersInsurance();
	
	 CustomerInsuranceModel findCustomerInsuranceById(int customerInsuranceId);
	 
	 CustomerInsuranceModel findCustomerInsuranceByPolicyCode(String policyCode);
	 
	 CustomerInsuranceModel findCustomerInsuranceByCustomer(CustomerModel customer);
	
	 void deleteCustomerInsuranceById(int customerInsuranceIds);
	
	 void deleteCustomersInsuranceById(int[] customerInsuranceIds);
	
}