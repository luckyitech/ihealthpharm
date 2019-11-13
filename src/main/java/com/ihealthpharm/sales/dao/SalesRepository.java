package com.ihealthpharm.sales.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.model.SalesModel;

@Repository
public interface SalesRepository
extends JpaRepository<SalesModel,Integer>
{

	List<SalesModel> findAll(Specification<SalesModel> specification);
	
	SalesModel findByBillCode(String searchTerm);
}