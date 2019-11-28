package com.ihealthpharm.sales.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.stock.model.StockModel;

@Repository
public interface SalesRepository
extends JpaRepository<SalesModel,Integer>
{

	List<SalesModel> findAll(Specification<SalesModel> specification);
	
	SalesModel findByBillCode(String searchTerm);

	//@Query("select s from sales s order by s.billDate desc limit 100")
	List<SalesModel> findFirst100ByOrderByBillCodeDesc();
	
}