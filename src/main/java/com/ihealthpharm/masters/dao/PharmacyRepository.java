package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.PharmacyModel;

@Repository
public interface PharmacyRepository extends JpaRepository<PharmacyModel,Integer> {

  	List<PharmacyModel> findByActiveS(Character active);
  	
  	@Query("select p from pharmacy p where p.activeS='Y' order by p.lastUpdateTimestamp desc ")
  	List<PharmacyModel> findAllLastestRecords();

  	@Query("select p from pharmacy p where p.pharmacyName like :pharmacyName% or p.addressLine1 like :pharmacyName%")
	List<PharmacyModel> findByPharmacyNameContains(@Param("pharmacyName")String pharmacyName);
}
