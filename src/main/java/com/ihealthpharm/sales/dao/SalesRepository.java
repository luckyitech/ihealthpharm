package com.ihealthpharm.sales.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
	@Query("select distinct m.name from sales s,sales_items si,provider p,items i,stock st,manufacturer m where s.billId=si.billId.billId and s.providerModel.providerId=p.providerId  and si.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId and m.name like :searchTerm%")
	List<String> findManufacturerInSalesSCL(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct m.name from sales s,sales_items si,provider p,items i,stock st,manufacturer m where s.billId=si.billId.billId and s.providerModel.providerId=p.providerId  and si.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId order by m.name")
	List<String> findAllManufacturerInSalesSCL();
	
	@Query("select distinct p.firstName from sales s,sales_items si,provider p,items i,stock st,manufacturer m where s.billId=si.billId.billId and s.providerModel.providerId=p.providerId  and si.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId and p.firstName like :searchTerm%")
	List<String> findProviderInSalesSCL(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct p.firstName from sales s,sales_items si,provider p,items i,stock st,manufacturer m where s.billId=si.billId.billId and s.providerModel.providerId=p.providerId  and si.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId order by p.firstName")
	List<String> findAllProvidersInSalesSCL();
	
	@Query("select distinct s.billDate from sales s,sales_items si,provider p,items i,stock st,manufacturer m where s.billId=si.billId.billId and s.providerModel.providerId=p.providerId  and si.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId and s.billDate like :searchTerm%")
	List<String> findBillDateInSalesSCL(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct s.billDate from sales s,sales_items si,provider p,items i,stock st,manufacturer m where s.billId=si.billId.billId and s.providerModel.providerId=p.providerId  and si.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId order by s.billDate")
	List<String> findAllBillDatesInSalesSCL();
	
}