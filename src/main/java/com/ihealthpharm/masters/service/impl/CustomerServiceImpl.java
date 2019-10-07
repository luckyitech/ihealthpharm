package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.CustomerRepository;
import com.ihealthpharm.masters.helper.CustomerHelper;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.service.CustomerService;

import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	
		
	@Autowired
	CustomerHelper customerHelper;
	
	 @Autowired
	 CustomerRepository customerRepository;

    
    @Override
    public void deleteCustomerData(CustomerModel customerModel)
    {
    }

  
    @Override
	public CustomerModel findCustomerData(int customerId) {
		CustomerModel customerRes = getValidCustomers(customerId);

		if(!Objects.nonNull(customerRes))
		{
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(),HttpStatus.NOT_FOUND);
		}
		log.info("Customer data with ID : "+ customerRes.getCustomerId()+" retrieved succesfully");
		return customerRes;

	}
    private CustomerModel getValidCustomers(int customerId)
	{
		CustomerModel CustomerRes = null;
		try {
			CustomerRes =  customerRepository.findById(customerId).get();
			return CustomerRes;

		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(),HttpStatus.NOT_FOUND);
		}


	}

    @Override
	public List<CustomerModel> findAllCustomers() {
		
		return customerRepository.findAll();
	}
   	@Override
    public CustomerModel saveCustomerData(CustomerModel customerModel)
    {
   		customerModel = customerRepository.save(customerModel);
   		log.info("Customer data with Id"+customerModel.getCustomerId()+"save successfully");
		return customerModel;
    }
   	
    @Override
	public CustomerModel updateCustomerData(CustomerModel customerModel) {

		CustomerModel customerRes = getValidCustomers(customerModel.getCustomerId());
		if(!Objects.nonNull(customerRes))
		{
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(),HttpStatus.NOT_FOUND);
		}

		customerRes = customerRepository.save(customerModel);
		log.info("Customers data with ID : "+ customerRes.getCustomerId()+" updated succesfully");
		return customerRes;
		
	}
   	
	@Override
	public List<CustomerModel> updateCustomerData(List<CustomerModel> customerModels) {


		for (CustomerModel cust : customerModels) {
			CustomerModel customerRes = getValidCustomers(cust.getCustomerId());
			if (!Objects.nonNull(customerRes)) {
				throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
			}

			customerRes = customerRepository.save(cust);
			log.info("Customer data with Multiple IDs : " + customerRes.getCustomerId() + " updated succesfully");
		}
		
		
		return customerModels;
	}
	
	@Override
	public void deleteCustomerById(int customerId) {

		CustomerModel customerRes = getValidCustomers(customerId);
		if(!Objects.nonNull(customerRes))
		{
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(),HttpStatus.NOT_FOUND);
		}		
		customerRepository.delete(customerRes);
		log.info("Customer data with ID: "+ customerRes.getCustomerId()+" deleted succesfully");
	}
	
	@Override
	public void deleteCustomerByIds(int[] customerIds) {

		CustomerModel customerRes;
		for (int customers : customerIds) {
			customerRes = getValidCustomers(customers);
			if (!Objects.nonNull(customerRes)) {
				throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
			}
			customerRepository.delete(customerRes);
			log.info("Customer data with IDs: " + customerRes.getCustomerId() + " deleted succesfully");
		}
		
	}

	
}