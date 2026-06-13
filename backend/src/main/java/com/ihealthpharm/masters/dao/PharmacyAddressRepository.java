package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.PharmacyAddressModel;

@Repository
public interface PharmacyAddressRepository extends JpaRepository<PharmacyAddressModel, Integer> {

	List<PharmacyAddressModel> findByActiveS(Character active);
	
}
