package com.ihealthpharm.masters.service;


import java.util.List;

import com.ihealthpharm.masters.model.CustomerModel;

public interface CustomerService
{
    
    void deleteCustomerData(CustomerModel customerModel);

    CustomerModel findCustomerData(int customerId);
    
    List<CustomerModel> findAllCustomers();

    CustomerModel saveCustomerData(CustomerModel customerModel);

    CustomerModel updateCustomerData(CustomerModel customerModel);
    
    List<CustomerModel> updateCustomerData(List<CustomerModel> customerModels);
    
    void deleteCustomerById( int customerId);
    
    void deleteCustomerByIds(int[] customerId);
}