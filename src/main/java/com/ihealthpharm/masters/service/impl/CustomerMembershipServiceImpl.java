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
		log.info("Customer Membership data with ID : " + customerMembershipRes.getMembershipCardPersonalId() + " Saved succesfully");
		return customerMembershipRes;
	}

	@Override
	public CustomerMembershipModel updateCustomerMembershipData(CustomerMembershipModel customerMembership) {
		CustomerMembershipModel customerMembershipRes = getValidCustomersMembership(customerMembership.getMembershipCardPersonalId());
		if (!Objects.nonNull(customerMembershipRes)) {
			throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
		}

		customerMembershipRes = customerMembershipRepository.save(customerMembership);
		log.info("Customer Membership data with ID : " + customerMembershipRes.getMembershipCardPersonalId() + " updated succesfully");
		return customerMembershipRes;
	}

	@Override
	public List<CustomerMembershipModel> updateCustomersMembershipData(List<CustomerMembershipModel> customersMembership) {
		for (CustomerMembershipModel customerMembership : customersMembership) {
			CustomerMembershipModel customerMembershipRes = getValidCustomersMembership(customerMembership.getMembershipCardPersonalId());
			if (!Objects.nonNull(customerMembershipRes)) {
				throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
			}

			customerMembershipRes = customerMembershipRepository.save(customerMembership);
			log.info("Customer Membership data with ID : " + customerMembershipRes.getMembershipCardPersonalId() + " updated succesfully");
		}
		return customersMembership;
	}

	@Override
	public List<CustomerMembershipModel> findAllCustomersMembership() {
		
		return customerMembershipRepository.findAll();
	}

	@Override
	public CustomerMembershipModel findCustomerMembershipById(int customerMembershipId) {
		CustomerMembershipModel customerMembershipRes = getValidCustomersMembership(customerMembershipId);
		if (!Objects.nonNull(customerMembershipRes)) {
			throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
		}
		
		log.info("Customer Membership data with ID : " + customerMembershipRes.getMembershipCardPersonalId() + " retrieved succesfully");
		return customerMembershipRes;
	}

	@Override
	public void deleteCustomerMembershipById(int customerMembershipId) {
		CustomerMembershipModel customerMembershipRes = customerMembershipRepository.getOne(customerMembershipId);
		if (!Objects.nonNull(customerMembershipRes)) {
			throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
		}
		
		log.info("Customer Membership data with ID : " + customerMembershipRes.getMembershipCardPersonalId() + " Deleted succesfully");
		customerMembershipRepository.delete(customerMembershipRes);
		
	}

	@Override
	public void deleteCustomersMembershipById(int[] customerMembershipIds) {
		CustomerMembershipModel customerMembershipRes;
		for (int customerMembership : customerMembershipIds) {
			customerMembershipRes = getValidCustomersMembership(customerMembership);
			if (!Objects.nonNull(customerMembershipRes)) {
				throw new IHealthPharmException(customerMembershipHelper.notFoundCustomerMembershipMessage, HttpStatus.NOT_FOUND);
			}
			customerMembershipRepository.delete(customerMembershipRes);
			log.info("Customer Membership data with ID: " + customerMembershipRes.getMembershipCardPersonalId()+ " deleted succesfully");
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

}