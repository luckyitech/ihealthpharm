package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.HospitalModel;

@Repository
public interface HospitalModelRepository extends JpaRepository<HospitalModel, Serializable>{

	List<HospitalModel> findByActiveS(String status);

	HospitalModel findByHospitalId(Integer hospitalId);
	
	List<HospitalModel> findAllByOrderByCreationTimeStampDesc();

	

	List<HospitalModel> findFirst100ByOrderByLastUpdatedTimeStampDesc();

	List<HospitalModel> findByHospitalNameIgnoreCaseContaining(String hospitalName);

	
}
