package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.UnitOfMeasurementModel;

public interface UnitOfMeasurementRepository extends JpaRepository<UnitOfMeasurementModel, Integer> {
	
	List<UnitOfMeasurementModel> findAllByOrderByCreationTimeStampDesc();

}
