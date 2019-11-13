package com.ihealthpharm.sales.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.sales.model.SalesReturnModel;

public interface SalesReturnRepository extends JpaRepository<SalesReturnModel, Integer> {
	
	List<SalesReturnModel> findAllByOrderByLastUpdateTimestampDesc();

}
