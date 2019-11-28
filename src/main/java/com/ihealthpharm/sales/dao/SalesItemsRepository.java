package com.ihealthpharm.sales.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.model.SalesItemsModel;
import com.ihealthpharm.sales.model.SalesModel;

@Repository
public interface SalesItemsRepository
extends JpaRepository<SalesItemsModel,Integer>
{

	
	List<SalesItemsModel> findByBillId(SalesModel sales);
	
	//Sales By Product Details
	
	@Query("select distinct s.customerNm from sales_items si,sales s,items i,stock st,manufacturer m,provider p "
			+ "where si.billId.billId=s.billId and si.itemsModel.itemId=i.itemId "
			+ "and st.stockId=si.stockId.stockId and i.manufacturer.manufacturerId=m.manufacturerId "
			+ "and s.providerModel.providerId=p.providerId and s.customerNm like :searchTerm%")
	List<String> findCustomersInSalesItemsSBPD(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct s.customerNm from sales_items si,sales s,items i,stock st,manufacturer m,provider p "
			+ "where si.billId.billId=s.billId and si.itemsModel.itemId=i.itemId "
			+ "and st.stockId=si.stockId.stockId and i.manufacturer.manufacturerId=m.manufacturerId "
			+ "and s.providerModel.providerId=p.providerId order by s.customerNm")
	List<String> findAllCustomersInSalesItemsSBPD();
	
}