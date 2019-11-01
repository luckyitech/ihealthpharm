package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.CustomerMembershipModel;

public interface CustomerMembershipRepository extends JpaRepository<CustomerMembershipModel,Integer>
{
	List<CustomerMembershipModel> findAll();
	
	
	 CustomerMembershipModel findByMembershipCardNumber(String membershipCardNumber);
	

}