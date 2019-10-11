package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.CustomerModel;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,Integer>
{
	List<CustomerModel> findAll();
}