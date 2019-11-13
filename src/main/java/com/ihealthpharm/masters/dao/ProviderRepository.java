package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.ProviderModel;

public interface ProviderRepository extends JpaRepository<ProviderModel, Integer>{
    
	List<ProviderModel> findByActiveS(char c);
	
	List<ProviderModel> findAllByOrderByLastUpdateTimestampDesc();

	List<ProviderModel> findFirst100ByOrderByLastUpdateTimestampDesc();

	List<ProviderModel> findByFirstNameIgnoreCaseContaining(String firstName);

}
