package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.PharmacyModel;

@Repository
public interface PharmacyRepository extends JpaRepository<PharmacyModel,Integer> {

  	List<PharmacyModel> findByActiveS(Character active);
  	
  	List<PharmacyModel> findAllByOrderByLastUpdateTimestampDesc();

  	@Query("select p from pharmacy p where p.pharmacyName like :pharmacyName% or p.addressLine1 like :pharmacyName%")
	List<PharmacyModel> findByPharmacyNameContains(String pharmacyName);
}
