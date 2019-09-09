package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.DistributorModel;

public interface DistrubutorRepository extends JpaRepository<DistributorModel, Integer>{

	List<DistributorModel> findByActiveS(char c);
	
	List<DistributorModel> findAllByOrderByLastUpdateTimestampDesc();

}
