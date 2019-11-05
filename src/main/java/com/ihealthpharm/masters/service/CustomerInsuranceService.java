package com.ihealthpharm.masters.service;

import java.util.List;

import com.ihealthpharm.masters.model.CustomerInsuranceModel;


public interface CustomerInsuranceService {

	 CustomerInsuranceModel saveCustomerInsuranceData(CustomerInsuranceModel customerInsurance);

	 CustomerInsuranceModel updateCustomerInsuranceData(CustomerInsuranceModel customerInsurance);
	
	 List<CustomerInsuranceModel> updateCustomersInsuranceData(List<CustomerInsuranceModel> customersInsurance);
	
	 List<CustomerInsuranceModel> findAllCustomersInsurance();
	
	 CustomerInsuranceModel findCustomerInsuranceById(int customerInsuranceId);
	 
	 CustomerInsuranceModel findCustomerInsuranceByPolicyCode(String policyCode);
	
	 void deleteCustomerInsuranceById(int customerInsuranceIds);
	
	 void deleteCustomersInsuranceById(int[] customerInsuranceIds);
	
}