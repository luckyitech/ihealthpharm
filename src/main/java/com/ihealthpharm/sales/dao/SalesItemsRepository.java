package com.ihealthpharm.sales.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.model.SalesItemsModel;
import com.ihealthpharm.sales.model.SalesModel;
import com.ihealthpharm.stock.model.StockModel;

@Repository
public interface SalesItemsRepository
extends JpaRepository<SalesItemsModel,Integer>
{

	

	List<SalesItemsModel> findByBillId(SalesModel sales);
}