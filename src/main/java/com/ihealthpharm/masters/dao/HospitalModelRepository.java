package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.HospitalModel;

@Repository
public interface HospitalModelRepository extends JpaRepository<HospitalModel, Serializable>{

	List<HospitalModel> findByActiveS(String status);

	HospitalModel findByHospitalId(Integer hospitalId);
	
	List<HospitalModel> findAllByOrderByCreationTimeStampDesc();

	

	List<HospitalModel> findFirst100ByOrderByLastUpdatedTimeStampDesc();

	@Query("select h from hospital h where h.hospitalName like %:searchKey% or h.license like %:searchKey% or h.addressLine1 like %:searchKey%")
	List<HospitalModel> findByHospitalNameIgnoreCaseContaining(@Param("searchKey") String searchKey);

	
}
