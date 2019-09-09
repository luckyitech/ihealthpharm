package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.PharmacyModel;

public interface PharmacyRepository extends JpaRepository<PharmacyModel,Integer > {

  	List<PharmacyModel> findByActiveS(String active);
}
