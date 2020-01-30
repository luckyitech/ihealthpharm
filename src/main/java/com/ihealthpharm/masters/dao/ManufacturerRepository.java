package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ManufacturerModel;

@Repository
public interface ManufacturerRepository  extends JpaRepository<ManufacturerModel, Integer>{
	
	List<ManufacturerModel> findByActiveS(Character active);
	
	@Query("select m from manufacturer m where m.activeS='Y' order by m.lastUpdateTimestamp desc")
	List<ManufacturerModel> findAllByOrderByLastUpdateTimestampDesc();

	@Query("select i from manufacturer i where i.name like :searchTerm% or i.licence like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<ManufacturerModel> findAllBySearchCriteria(@Param("searchTerm") String searchTerm);
}
