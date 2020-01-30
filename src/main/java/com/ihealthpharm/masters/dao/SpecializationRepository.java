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
	
	@Query("SELECT s FROM specialization s where s.activeS='Y' order by s.lastUpdateTimestamp desc")
	List<SpecializationModel> findAllLastestRecords();
	
	@Query("select i from specialization i where i.specializationName like %:searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<SpecializationModel> findAllBySearchCriteria(@Param("searchTerm") String searchTerm);
	
	
}
