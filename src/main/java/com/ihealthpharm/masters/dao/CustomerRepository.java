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

	@Query("SELECT c  FROM customer c order by c.lastUpdateTimestamp desc ")
	List<CustomerModel> findFirst100ByOrderByCustomerNameAsc(Pageable limit);
	
	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)  FROM customer c  where c.activeS='Y' order by c.lastUpdateTimestamp desc ")
	List<CustomerModel> findFirst100ActiveByOrderByCustomerNameAsc(Pageable limit);
	
	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)  FROM customer c  where c.activeS='Y' and c.corporate='Y' order by c.lastUpdateTimestamp desc")
	List<CustomerModel> findFirst100ActiveByOrderByCorporateCustomerNameAsc(Pageable limit);
	
	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)  FROM customer c  where c.activeS='Y' and c.corporate='S' order by c.lastUpdateTimestamp desc")
	List<CustomerModel> findFirst100ActiveByOrderByStaffCustomerNameAsc(Pageable limit);
	
	@Query("SELECT c  FROM customer c where c.activeS='Y' order by c.lastUpdateTimestamp desc ")
	List<CustomerModel> findFirst100Records(Pageable limit);

	List<CustomerModel> findByCustomerNameIgnoreCaseContaining(String customerName);

	@Query("select c from customer c where c.customerName like %:name% or c.lastName like %:name% or c.phoneNumber like %:name% or c.addressLine1 like %:name% "
			+ " or c.emailId like %:name%")
	List<CustomerModel> findByCustomerNameAndLastNameIgnoreCaseContaining(@Param("name") String name);

	List<CustomerModel> findAll(Specification<CustomerModel> specification);

	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)  "
			+ "FROM customer c where (c.phoneNumber like %:customerName% or c.customerName like %:customerName% or c.lastName like %:customerName%) and activeS='Y'")
	List<CustomerModel> findCustomerByNameSearch(@Param("customerName") String customerName);
	
	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)  "
			+ "FROM customer c where (c.phoneNumber like %:customerName% or c.customerName like %:customerName% or c.lastName like %:customerName%) and activeS='Y' and c.corporate='Y'")
	List<CustomerModel> findCorporateCustomerByNameSearch(@Param("customerName") String customerName);
	
	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)  "
			+ "FROM customer c where (c.phoneNumber like %:customerName% or c.customerName like %:customerName% or c.lastName like %:customerName%) and activeS='Y' and c.corporate='S'")
	List<CustomerModel> findStaffCustomerByNameSearch(@Param("customerName") String customerName);

	@Query("select  c from customer c where c.customerName like  :customerName% or c.lastName like :customerName% or c.phoneNumber like :customerName%")
	List<CustomerModel> findCustomerBySearchingName(@Param("customerName") String customerName);

	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)  "
			+ "FROM customer c where  (c.phoneNumber like %:phno%) and activeS='Y'")
	List<CustomerModel> findByPhoneNumber(@Param("phno") String phno);
	
	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)  "
			+ "FROM customer c where (c.phoneNumber like %:phno%) and activeS='Y' and c.corporate='Y'")
	List<CustomerModel> findByCorporatePhoneNumber(@Param("phno") String phno);
	
	@Query("SELECT new com.ihealthpharm.masters.model.CustomerModel(c.customerId, concat(c.customerName,' ', c.lastName) as customerName,phoneNumber,corporate)  "
			+ "FROM customer c where (c.phoneNumber like %:phno%) and activeS='Y' and c.corporate='S'")
	List<CustomerModel> findByStaffPhoneNumber(@Param("phno") String phno);

	@Query("select distinct c.customerName from customer c where c.customerName like :key%")
	List<String> findCustomerFirstNamesBySearch(@Param("key")String key);
	
	@Query("select distinct c.lastName from customer c where c.lastName like :key%")
	List<String> findCustomerLastNamesBySearch(@Param("key")String key);

}