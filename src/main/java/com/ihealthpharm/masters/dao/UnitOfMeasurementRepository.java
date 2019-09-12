package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.UnitOfMeasurementModel;

public interface UnitOfMeasurementRepository extends JpaRepository<UnitOfMeasurementModel, Integer> {
	
	List<UnitOfMeasurementModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query("select i from unit_of_measurement i where i.measurementName like %:searchTerm% order by i.creationTimeStamp desc")
	List<UnitOfMeasurementModel> findAllBySearchCriteria(@Param("searchTerm") String searchTerm);

}
