package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.DistributorModel;

public interface DistrubutorRepository extends JpaRepository<DistributorModel, Integer>{

	List<DistributorModel> findByActiveS(char c);
	
	List<DistributorModel> findAllByOrderByLastUpdateTimestampDesc();
	
	@Query("select d from distributor d where d.name like %:searchTerm% order by d.creationTimeStamp desc")
	List<DistributorModel> getAllDistributorNamesBySearch(@Param("searchTerm") String searchTerm);

}
