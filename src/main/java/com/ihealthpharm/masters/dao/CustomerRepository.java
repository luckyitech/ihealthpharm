package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,Integer>
{
	List<CustomerModel> findByActiveS(Character active);
	
	 List<CustomerModel> findAllByOrderByLastUpdateTimestampDesc();

	List<CustomerModel> findFirst100ByOrderByCustomerNameAsc();

	List<CustomerModel> findByCustomerNameIgnoreCaseContaining(String customerName);

	@Query("select c from customer c where c.customerName like %:name% or c.lastName like %:name%")
	List<CustomerModel> findByCustomerNameAndLastNameIgnoreCaseContaining(@Param("name") String name);

	List<CustomerModel> findAll(Specification<CustomerModel> specification);
	
}