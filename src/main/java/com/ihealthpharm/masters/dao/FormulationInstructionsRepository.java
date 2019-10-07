package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.FormulationInstructionsModel;
import com.ihealthpharm.masters.model.PharmacyModel;
@Repository
public interface FormulationInstructionsRepository
extends JpaRepository<FormulationInstructionsModel,Integer>
{
	List<FormulationInstructionsModel> findByActiveS(Character active);
  	
}