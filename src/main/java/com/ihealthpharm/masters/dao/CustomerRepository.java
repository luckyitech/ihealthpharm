package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,Integer>
{
	List<CustomerModel> findByActiveS(Character active);
	
	 List<CustomerModel> findAllByOrderByLastUpdateTimestampDesc();
	
}