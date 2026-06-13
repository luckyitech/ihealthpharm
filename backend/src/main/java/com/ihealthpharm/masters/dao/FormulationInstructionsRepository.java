package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.FormulationInstructionsModel;
@Repository
public interface FormulationInstructionsRepository
extends JpaRepository<FormulationInstructionsModel,Integer>
{
	List<FormulationInstructionsModel> findByActiveS(Character active);

	@Query("SELECT f FROM FORMULATION_INSTRUCTIONS f  order by f.lastUpdateTimestamp desc")
	List<FormulationInstructionsModel> findAllLastUpdated();

}