package com.ihealthpharm.masters.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.CustomerRepository;
import com.ihealthpharm.masters.dto.CustomerDTO;
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
	public CustomerModel findCustomerById(Integer customerId) {
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
	public void deleteCustomerById(Integer customerId) {
		CustomerModel customerRes = customerRepository.getOne(customerId);
		if (!Objects.nonNull(customerRes)) {
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
		}
		
		log.info("Customer data with ID : " + customerRes.getCustomerId() + " Deleted succesfully");
		customerRepository.delete(customerRes);
		
	}

	@Override
	public void deleteCustomersById(Integer[] customerIds) {
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
	
	public CustomerModel getValidCustomers(Integer customerId) {
		CustomerModel customerRes = null;

		try {
			customerRes =customerRepository.findById(customerId).get();
			return customerRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(customerHelper.getNotFoundCustomerMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<CustomerModel> findLimitedCustomers() {
		Pageable limit = new PageRequest(0,100);
		return customerRepository.findFirst100ByOrderByCustomerNameAsc(limit);
	}

	@Override
	public List<CustomerModel> findAllLimitedCustomersData() {
		Pageable limit = new PageRequest(0,100);
		return customerRepository.findFirst100Records(limit);
	}	
	
	@Override
	public List<CustomerModel> findCustomersByName(String customerName) {
		
		/*return customerRepository.findAll(new Specification<CustomerModel>() {
		 static final long serialVersionUID = -2059726564132190131L;

			@Override
			public Predicate toPredicate(Root<CustomerModel> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (!customerName.equals(null) && !customerName.equals("undefined") &&  !customerName.equals("")) {
					
					predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("customerName"), customerName+"%")));
					predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("lastName"), customerName+"%")));
				}
				
				return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
			}
		});*/
		
		return customerRepository.findCustomerByNameSearch(customerName);
	}

	@Override
	public List<CustomerModel> findAllCustomersByNameSearch(String customerName) {
		return customerRepository.findCustomerBySearchingName(customerName);
	}

}
