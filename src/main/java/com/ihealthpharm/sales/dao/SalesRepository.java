package com.ihealthpharm.sales.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.dto.SalesDTO;
import com.ihealthpharm.sales.model.SalesModel;

@Repository
public interface SalesRepository
extends JpaRepository<SalesModel,Integer>
{

	List<SalesModel> findAll(Specification<SalesModel> specification);
	
	SalesModel findByBillCode(String searchTerm);

	//@Query("select s from sales s order by s.billDate desc limit 100")
	List<SalesModel> findFirst100ByOrderByBillCodeDesc();
	
	@Query("SELECT new com.ihealthpharm.sales.dto.SalesDTO(billDate, sum((totalProducts*totalQty)/1000) as totalSales) FROM sales s where year(billDate)='2019' GROUP BY year(billDate), month(billDate)  order by billDate desc")
	List<SalesDTO> getAllSalesDataForCharts();
}