package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.CustomerInsuranceRepository;
import com.ihealthpharm.masters.helper.CustomerInsuranceHelper;
import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.masters.service.CustomerInsuranceService;

import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
public class CustomerInsuranceServiceImpl implements CustomerInsuranceService {
	
	
	@Autowired
	CustomerInsuranceRepository customerInsuranceRepository;
	
	@Autowired
	CustomerInsuranceHelper customerInsuranceHelper;
	
	@Override
	public CustomerInsuranceModel saveCustomerInsuranceData(CustomerInsuranceModel customerInsurance) {
		CustomerInsuranceModel customerInsuranceRes = customerInsuranceRepository.save(customerInsurance);
		log.info("Customer Insurance data with ID : " + customerInsuranceRes.getInsurancePolicyPersonalId() + " Saved succesfully");
		return customerInsuranceRes;
	}

	@Override
	public CustomerInsuranceModel updateCustomerInsuranceData(CustomerInsuranceModel customerInsurance) {
		CustomerInsuranceModel customerInsuranceRes = getValidCustomersInsurance(customerInsurance.getInsurancePolicyPersonalId());
		if (!Objects.nonNull(customerInsuranceRes)) {
			throw new IHealthPharmException(customerInsuranceHelper.notFoundCustomerInsuranceMessage, HttpStatus.NOT_FOUND);
		}

		customerInsuranceRes = customerInsuranceRepository.save(customerInsurance);
		log.info("Customer Insurance data with ID : " + customerInsuranceRes.getInsurancePolicyPersonalId() + " updated succesfully");
		return customerInsuranceRes;
	}

	@Override
	public List<CustomerInsuranceModel> updateCustomersInsuranceData(List<CustomerInsuranceModel> customersInsurance) {
		for (CustomerInsuranceModel customerInsurance : customersInsurance) {
			CustomerInsuranceModel customerInsuranceRes = getValidCustomersInsurance(customerInsurance.getInsurancePolicyPersonalId());
			if (!Objects.nonNull(customerInsuranceRes)) {
				throw new IHealthPharmException(customerInsuranceHelper.notFoundCustomerInsuranceMessage, HttpStatus.NOT_FOUND);
			}

			customerInsuranceRes = customerInsuranceRepository.save(customerInsurance);
			log.info("Customer Insurance data with ID : " + customerInsuranceRes.getInsurancePolicyPersonalId() + " updated succesfully");
		}
		return customersInsurance;
	}

	@Override
	public List<CustomerInsuranceModel> findAllCustomersInsurance() {
		
		return customerInsuranceRepository.findAll();
	}

	@Override
	public CustomerInsuranceModel findCustomerInsuranceById(int customerInsuranceId) {
		CustomerInsuranceModel customerInsuranceRes = getValidCustomersInsurance(customerInsuranceId);
		if (!Objects.nonNull(customerInsuranceRes)) {
			throw new IHealthPharmException(customerInsuranceHelper.notFoundCustomerInsuranceMessage, HttpStatus.NOT_FOUND);
		}
		
		log.info("Customer Insurance data with ID : " + customerInsuranceRes.getInsurancePolicyPersonalId() + " retrieved succesfully");
		return customerInsuranceRes;
	}

	@Override
	public void deleteCustomerInsuranceById(int customerInsuranceId) {
		CustomerInsuranceModel customerInsuranceRes = customerInsuranceRepository.getOne(customerInsuranceId);
		if (!Objects.nonNull(customerInsuranceRes)) {
			throw new IHealthPharmException(customerInsuranceHelper.notFoundCustomerInsuranceMessage, HttpStatus.NOT_FOUND);
		}
		
		log.info("Customer Insurance data with ID : " + customerInsuranceRes.getInsurancePolicyPersonalId() + " Deleted succesfully");
		customerInsuranceRepository.delete(customerInsuranceRes);
		
	}

	@Override
	public void deleteCustomersInsuranceById(int[] customerInsuranceIds) {
		CustomerInsuranceModel customerInsuranceRes;
		for (int customerInsurance : customerInsuranceIds) {
			customerInsuranceRes = getValidCustomersInsurance(customerInsurance);
			if (!Objects.nonNull(customerInsuranceRes)) {
				throw new IHealthPharmException(customerInsuranceHelper.notFoundCustomerInsuranceMessage, HttpStatus.NOT_FOUND);
			}
			customerInsuranceRepository.delete(customerInsuranceRes);
			log.info("Customer Insurance data with ID: " + customerInsuranceRes.getInsurancePolicyPersonalId() + " deleted succesfully");
		}
		
	}
	
	public CustomerInsuranceModel getValidCustomersInsurance(int customerInsuranceId) {
		CustomerInsuranceModel customerInsuranceRes = null;

		try {
			customerInsuranceRes =customerInsuranceRepository.findById(customerInsuranceId).get();
			return customerInsuranceRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(customerInsuranceHelper.notFoundCustomerInsuranceMessage, HttpStatus.NOT_FOUND);
		}
	}

}
