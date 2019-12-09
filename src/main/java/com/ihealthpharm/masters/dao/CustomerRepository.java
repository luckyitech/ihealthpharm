package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
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

	 @Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber)  FROM customer c")
	List<CustomerModel> findFirst100ByOrderByCustomerNameAsc(Pageable limit);

	List<CustomerModel> findByCustomerNameIgnoreCaseContaining(String customerName);

	@Query("select c from customer c where c.customerName like %:name% or c.lastName like %:name% or c.phoneNumber like %:name% or c.addressLine1 like %:name% "
			+ " or c.emailId like %:name%")
	List<CustomerModel> findByCustomerNameAndLastNameIgnoreCaseContaining(@Param("name") String name);

	List<CustomerModel> findAll(Specification<CustomerModel> specification);

	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber)  FROM customer c where c.customerName like :customerName%")
	List<CustomerModel> findCustomerByNameSearch(@Param("customerName") String customerName);

	@Query("select  c from customer c where c.customerName like  :customerName% or c.lastName like :customerName% ")
	List<CustomerModel> findCustomerBySearchingName(@Param("customerName") String customerName);
	
}