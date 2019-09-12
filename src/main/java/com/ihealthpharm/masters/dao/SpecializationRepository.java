package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.SpecializationModel;

@Repository
public interface SpecializationRepository extends JpaRepository<SpecializationModel, Integer> {

	public List<SpecializationModel> findByActiveS(String active);
	
	List<SpecializationModel> findAllByOrderByCreationTimeStampDesc();
	
	
	@Query("select i from specialization i where i.specializationName like %:searchTerm% order by i.creationTimeStamp desc")
	List<SpecializationModel> findAllBySearchCriteria(@Param("searchTerm") String searchTerm);
	
	
}
