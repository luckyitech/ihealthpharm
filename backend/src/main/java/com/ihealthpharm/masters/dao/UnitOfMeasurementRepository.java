package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.UnitOfMeasurementModel;

public interface UnitOfMeasurementRepository extends JpaRepository<UnitOfMeasurementModel, Integer> {
	
	@Query("SELECT u FROM unit_of_measurement u where u.activeS='Y' order by u.lastUpdateTimestamp desc")
	List<UnitOfMeasurementModel> getAllLastestRecordsWithActive();
	
	@Query("select i from unit_of_measurement i where i.measurementName like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc ")
	List<UnitOfMeasurementModel> findAllBySearchCriteria(@Param("searchTerm") String searchTerm);

	List<UnitOfMeasurementModel> findByActiveS(String string);

}
