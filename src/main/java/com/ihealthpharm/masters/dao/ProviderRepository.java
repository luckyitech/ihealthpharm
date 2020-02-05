package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.ProviderModel;

public interface ProviderRepository extends JpaRepository<ProviderModel, Integer>{
    
	List<ProviderModel> findByActiveS(char c);
	
	@Query("select p from provider p order by p.lastUpdateTimestamp desc")
	List<ProviderModel> findAllLastestRecords();

	List<ProviderModel> findFirst100ByOrderByLastUpdateTimestampDesc();
	
	@Query("select p from provider p where p.activeS='Y' order by p.lastUpdateTimestamp desc")
	List<ProviderModel> findAllSupplierRecords();

	List<ProviderModel> findByFirstNameIgnoreCaseContaining(String firstName);

	@Query("select p from provider p where p.firstName like :name% or p.lastName like :name% or p.deaNumber like :name% or p.licenseNumber like :name%")
	List<ProviderModel> findByNameIgnoreCaseContaining(@Param("name") String name);

}
