package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ManufacturerModel;


@Repository
public interface ManufacturerRepository  extends JpaRepository<ManufacturerModel, Integer>{
	
	public List<ManufacturerModel> findByActiveS(Character active);
	
	List<ManufacturerModel> findAllByOrderByCreationTimeStampDesc();
}
