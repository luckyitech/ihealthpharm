package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.CustomerInsuranceModel;

public interface CustomerInsuranceRepository extends JpaRepository<CustomerInsuranceModel,Integer>
{
	List<CustomerInsuranceModel> findAll();

	CustomerInsuranceModel findByPolicyCode(String policyCode);
	
	
}