package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemsBinModel;

@Repository
public interface ItemsBinRepository extends JpaRepository<ItemsBinModel, Integer> {
	
	List<ItemsBinModel> findAllByOrderByCreationTimeStampDesc();
}
