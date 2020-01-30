package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.CustomerMembershipRepository;
import com.ihealthpharm.masters.helper.CustomerMembershipHelper;
import com.ihealthpharm.masters.model.CustomerMembershipModel;
import com.ihealthpharm.masters.service.CustomerMembershipService;

import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
public class CustomerMembershipServiceImpl implements CustomerMembershipService {
	
	
	@Autowired
	CustomerMembershipRepository customerMembershipRepository;
	
	@Autowired
	CustomerMembershipHelper customerMembershipHelper;
	
	@Override
	public CustomerMembershipModel saveCustomerMembershipData(CustomerMembershipModel customerMembership) {
		CustomerMembershipModel customerMembershipRes = customerMembershipRepository.save(customerMembership);
		log.info("Customer Membership data with ID : " + customerMembershipRes.getCustomerMembershipId() + " Saved succesfully");
		return customerMembershipRes;
	}

	@Override
	public CustomerMembershipModel updateCustomerMembershipData(CustomerMembershipModel customerMembership) {
		CustomerMembershipModel customerMembershipRes = getValidCustomersMembership(customerMembership.getCustomerMembershipId());
		if (!Objects.nonNull(customerMembershipRes)) {
			throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
		}

		customerMembershipRes = customerMembershipRepository.save(customerMembership);
		log.info("Customer Membership data with ID : " + customerMembershipRes.getCustomerMembershipId() + " updated succesfully");
		return customerMembershipRes;
	}

	@Override
	public List<CustomerMembershipModel> updateCustomersMembershipData(List<CustomerMembershipModel> customersMembership) {
		for (CustomerMembershipModel customerMembership : customersMembership) {
			CustomerMembershipModel customerMembershipRes = getValidCustomersMembership(customerMembership.getCustomerMembershipId());
			if (!Objects.nonNull(customerMembershipRes)) {
				throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
			}

			customerMembershipRes = customerMembershipRepository.save(customerMembership);
			log.info("Customer Membership data with ID : " + customerMembershipRes.getCustomerMembershipId() + " updated succesfully");
		}
		return customersMembership;
	}

	@Override
	public List<CustomerMembershipModel> findAllCustomersMembership() {
		
		return customerMembershipRepository.findAllLastestRecords();
	}

	@Override
	public CustomerMembershipModel findCustomerMembershipById(Integer customerMembershipId) {
		CustomerMembershipModel customerMembershipRes = getValidCustomersMembership(customerMembershipId);
		if (!Objects.nonNull(customerMembershipRes)) {
			throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
		}
		
		log.info("Customer Membership data with ID : " + customerMembershipRes.getCustomerMembershipId() + " retrieved succesfully");
		return customerMembershipRes;
	}

	@Override
	public void deleteCustomerMembershipById(Integer customerMembershipId) {
		CustomerMembershipModel customerMembershipRes = customerMembershipRepository.getOne(customerMembershipId);
		if (!Objects.nonNull(customerMembershipRes)) {
			throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
		}
		
		log.info("Customer Membership data with ID : " + customerMembershipRes.getCustomerMembershipId() + " Deleted succesfully");
		customerMembershipRepository.delete(customerMembershipRes);
		
	}

	@Override
	public void deleteCustomersMembershipById(Integer[] customerMembershipIds) {
		CustomerMembershipModel customerMembershipRes;
		for (int customerMembership : customerMembershipIds) {
			customerMembershipRes = getValidCustomersMembership(customerMembership);
			if (!Objects.nonNull(customerMembershipRes)) {
				throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
			}
			customerMembershipRepository.delete(customerMembershipRes);
			log.info("Customer Membership data with ID: " + customerMembershipRes.getCustomerMembershipId()+ " deleted succesfully");
		}
		
	}
	
	public CustomerMembershipModel getValidCustomersMembership(int customerMembershipId) {
		CustomerMembershipModel customerMembershipRes = null;

		try {
			customerMembershipRes =customerMembershipRepository.findById(customerMembershipId).get();
			return customerMembershipRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
		}
	}

	
	@Override
	public CustomerMembershipModel findByMembershipCardNumber(String membershipCardNumber) {
		
		return customerMembershipRepository.findByMembershipCardNumber(membershipCardNumber);
	}

	@Override
	public List<CustomerMembershipModel> findCustomersMembershipBySearch(String key) {
		
		return customerMembershipRepository.findMembershipBySearch(key);
	}
}