package com.ihealthpharm.masters.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ihealthpharm.masters.model.ConfigurationStatusModel;

public interface ConfigurationStatusRepository extends JpaRepository<ConfigurationStatusModel, Integer> {

	@Query(value="select * from configuration_status limit 1",nativeQuery = true)
	public ConfigurationStatusModel findFirstRecord();

	
}
