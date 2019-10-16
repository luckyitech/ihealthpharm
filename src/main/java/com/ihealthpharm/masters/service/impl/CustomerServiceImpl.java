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
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerHelper customerHelper;
	
	@Override
	public CustomerModel saveCustomerData(CustomerModel customer) {
		CustomerModel customerRes = customerRepository.save(customer);
		log.info("Customer data with ID : " + customerRes.getCustomerId() + " Saved succesfully");
		return customerRes;
	}

	@Override
	public CustomerModel updateCustomerData(CustomerModel customer) {
		CustomerModel customerRes = getValidCustomers(customer.getCustomerId());
		if (!Objects.nonNull(customerRes)) {
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
		}

		customerRes = customerRepository.save(customer);
		log.info("Customer data with ID : " + customerRes.getCustomerId() + " updated succesfully");
		return customerRes;
	}

	@Override
	public List<CustomerModel> updateCustomersData(List<CustomerModel> customers) {
		for (CustomerModel customer : customers) {
			CustomerModel customerRes = getValidCustomers(customer.getCustomerId());
			if (!Objects.nonNull(customerRes)) {
				throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
			}

			customerRes = customerRepository.save(customer);
			log.info("Customer data with ID : " + customerRes.getCustomerId() + " updated succesfully");
		}
		return customers;
	}

	@Override
	public List<CustomerModel> findAllCustomers() {
		
		return customerRepository.findAllByOrderByLastUpdateTimestampDesc();
	}

	@Override
	public CustomerModel findCustomerById(int customerId) {
		CustomerModel customerRes = getValidCustomers(customerId);
		if (!Objects.nonNull(customerRes)) {
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Customer data with ID : " + customerRes.getCustomerId() + " retrieved succesfully");
		return customerRes;
	}

	  @Override
		public List<CustomerModel> findCustomerByActive() {

			return customerRepository.findByActiveS('Y');
	    }

	
	@Override
	public void deleteCustomerById(int customerId) {
		CustomerModel customerRes = customerRepository.getOne(customerId);
		if (!Objects.nonNull(customerRes)) {
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Customer data with ID : " + customerRes.getCustomerId() + " Deleted succesfully");
		customerRepository.delete(customerRes);
		
	}

	@Override
	public void deleteCustomersById(int[] customerIds) {
		CustomerModel customerRes;
		for (int customer : customerIds) {
			customerRes = getValidCustomers(customer);
			if (!Objects.nonNull(customerRes)) {
				throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
			}
			customerRepository.delete(customerRes);
			log.info("Customer data with ID: " + customerRes.getCustomerId() + " deleted succesfully");
		}
		
	}
	
	public CustomerModel getValidCustomers(int customerId) {
		CustomerModel customerRes = null;

		try {
			customerRes =customerRepository.findById(customerId).get();
			return customerRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
