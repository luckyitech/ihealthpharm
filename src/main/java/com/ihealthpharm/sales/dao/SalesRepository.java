package com.ihealthpharm.sales.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.dto.SalesBillDTO;
import com.ihealthpharm.sales.dto.SalesDTO;
import com.ihealthpharm.sales.model.SalesModel;

@Repository
public interface SalesRepository extends JpaRepository<SalesModel, Integer> {

	List<SalesModel> findAll(Specification<SalesModel> specification);

	SalesModel findByBillCode(String searchTerm);

	// @Query("select s from sales s order by s.billDate desc limit 100")
	List<SalesModel> findFirst100ByOrderByBillCodeDesc();

	@Query("SELECT new com.ihealthpharm.sales.dto.SalesDTO(billDate, sum((totalProducts*totalQty)/1000) as totalSales) FROM sales s where year(billDate)='2019' GROUP BY year(billDate), month(billDate)  order by billDate desc")
	List<SalesDTO> getAllSalesDataForCharts();

	// SCL

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

	// DBL
	@Query(" select s.billDate from sales s, provider p, sales_items si, items i, manufacturer m, stock st "
			+ " where s.providerModel.providerId=p.providerId and s.billId=si.billId.billId"
			+ " and si.itemsModel.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId"
			+ " and si.stockId.stockId=st.stockId ")
	List<String> findBillDatesInSalesDBL(@Param("searchTerm") String searchTerm);

	@Query("select distinct p.firstName from sales s, provider p, sales_items si, items i, manufacturer m, stock st"
			+ " where s.providerModel.providerId=p.providerId and s.billId=si.billId.billId"
			+ " and si.itemsModel.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId "
			+ " and si.stockId.stockId=st.stockId and p.firstName like :searchTerm% ")
	List<String> findfirst_nmInSalesDBL(@Param("searchTerm") String searchTerm);

	@Query("select distinct m.name from sales s, provider p, sales_items si, items i, manufacturer m, stock st"
			+ " where s.providerModel.providerId=p.providerId and s.billId=si.billId.billId"
			+ " and si.itemsModel.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId"
			+ " and si.stockId.stockId=st.stockId and m.name like :searchTerm% ")
	List<String> findnameInSalesDBL(@Param("searchTerm") String searchTerm);

	@Query(" select distinct s.billDate from sales s, provider p, sales_items si, items i, manufacturer m, stock st "
			+ " where s.providerModel.providerId=p.providerId and s.billId=si.billId.billId"
			+ " and si.itemsModel.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId"
			+ " and si.stockId.stockId=st.stockId ")
	List<String> findAllBillDatesInSalesDBL();

	@Query("select distinct p.firstName from sales s, provider p, sales_items si, items i, manufacturer m, stock st"
			+ " where s.providerModel.providerId=p.providerId and s.billId=si.billId.billId"
			+ " and si.itemsModel.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId "
			+ " and si.stockId.stockId=st.stockId order by p.firstName  ")
	List<String> findAllfirst_nmInSalesDBL();

	@Query("select distinct m.name from sales s, provider p, sales_items si, items i, manufacturer m, stock st"
			+ " where s.providerModel.providerId=p.providerId and s.billId=si.billId.billId"
			+ " and si.itemsModel.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId"
			+ " and si.stockId.stockId=st.stockId order by m.name  ")
	List<String> findAllnameInSalesDBL();

	// SRD

	@Query("select DISTINCT s.billDate from sales s,sales_items si,payment_types pt "
			+ "where s.billId=si.billId.billId and s.billDate like :searchTerm%")
	List<String> findbillDateINSalesSRD(@Param("searchTerm") String searchTerm);

	@Query("select DISTINCT pt.type from sales s,sales_items si,payment_types pt "
			+ "where s.billId=si.billId.billId and pt.type like :searchTerm%")
	List<String> findtypeINSalesSRD(@Param("searchTerm") String searchTerm);

	@Query("select DISTINCT s.billDate from sales s,sales_items si,payment_types pt "
			+ "where s.billId=si.billId.billId order by s.billDate")
	List<String> findAllbillDateINSalesSRD();

	@Query("select DISTINCT pt.type from payment_types pt order by pt.type ")
	List<String> findAlltypeINSalesSRD();

	// SRADL
	@Query("select distinct sp.cityName from sales s,sales_items si,supplier sp,payment_types pt "
			+ "where s.billId=si.billId.billId and si.supplier.supplierId=sp.supplierId and sp.cityName like :searchTerm%")
	List<String> findcityNameINSalesSRADL(@Param("searchTerm") String searchTerm);

	@Query("select s.billCode from sales s where s.billCode is not null order by s.billDate DESC")
	List<String> findBillCodeTop100(Pageable pageable);

	@Query("select s.billCode from sales s where s.billCode like :key% order by s.billDate DESC ")
	List<String> findByBillCodeSearch(@Param("key") String key);
	 
	 @Query("select distinct sp.cityName from sales s,sales_items si,supplier sp,payment_types pt "  
		 	   + "where s.billId=si.billId.billId and si.supplier.supplierId=sp.supplierId order by sp.cityName ")
			 		List<String> findAllcityNameINSalesSRADL();
	 
	 //SRBB
	 @Query("SELECT s.billCode from sales s  WHERE YEAR(billDate) = YEAR(now()) "
	 		+ "and MONTH(billDate) > ( MONTH(now()) -2 ) "
	 		+ "order by s.billDate desc")
				 		List<String> findAllBillCodeINSalesSRBB();
	// SRBB
		@Query("SELECT s.billCode from sales s where s.billCode like :searchTerm%")
		List<String> findBillCodeINSalesSRBB(@Param("searchTerm") String searchTerm);

	 
	 @Query("select new com.ihealthpharm.sales.dto.SalesBillDTO(i.billId,i.billCode) from sales i  "
				+ "where  i.billCode like %:billCode%")
	List<SalesBillDTO> getAllSalesBySalesIdSearch(@Param("billCode") String billCode);

	// sales history searches
	@Query("select s from sales s where s.paymentStatus = :key order by s.billDate DESC")
	List<SalesModel> findSalesByPaymentStatus(@Param("key") String key, Pageable limit);
	
	@Query("select s from sales s where s.paymentStatus = :status and s.billDate between :start and :end and s.customerModel.customerName like :code% or s.billCode like :code% order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesSearchByStatusSearchCodeDate(@Param("status") String status,@Param("code") String code,@Param("start") LocalDate start,@Param("end") LocalDate end, Pageable limit);

	@Query("select s from sales s where s.paymentStatus = :status and s.billDate between :start and :end  order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesSearchByStatusDate(@Param("status") String status,@Param("start") LocalDate start,@Param("end") LocalDate end, Pageable limit);

	@Query("select s from sales s where s.billCode like :key% order by s.billDate DESC")
	List<SalesModel> findSalesByBillCode(@Param("key") String key, Pageable limit);

	@Query("select s from sales s inner join customer c on s.customerModel.customerId = c.customerId where c.customerName like :key% or "
			+ "c.lastName like :key% order by s.billDate DESC")
	List<SalesModel> findSalesByCustomerName(@Param("key") String key, Pageable limit);

	@Query("select s from sales s where s.billDate between :start and :end order by s.billDate DESC")
	List<SalesModel> findSalesByBillDate(@Param("start") LocalDate start,@Param("end") LocalDate end, Pageable limit);

	//counts
	
	@Query("select count(s) from sales s where s.paymentStatus = :status and s.billDate between :start and :end and s.customerModel.customerName like :code% or s.billCode like :code% order by s.lastUpdateTs DESC")
	Integer findSalesSearchByStatusSearchCodeDateCount(@Param("status") String status,@Param("code") String code,@Param("start") LocalDate start,@Param("end") LocalDate end);

	@Query("select count(s) from sales s where s.paymentStatus = :status and s.billDate between :start and :end  order by s.lastUpdateTs DESC")
	Integer findSalesSearchByStatusDateCount(@Param("status") String status,@Param("start") LocalDate start,@Param("end") LocalDate end);
	
	@Query("select count(s) from sales s where s.paymentStatus = :key order by s.billDate DESC")
	Integer findSalesByPaymentStatusCount(@Param("key") String key );
	
	@Query("select count(s) from sales s where s.billCode like :key% order by s.billDate DESC")
	Integer findSalesByBillCodeCount(@Param("key") String key);

	@Query("select count(s) from sales s inner join customer c on s.customerModel.customerId = c.customerId where c.customerName like :key% or "
			+ "c.lastName like :key% order by s.billDate DESC")
	Integer findSalesByCustomerNameCount(@Param("key") String key);

	@Query("select count(s) from sales s where s.billDate between :start and :end order by s.billDate DESC")
	Integer findSalesByBillDateCount(@Param("start") LocalDate start,@Param("end") LocalDate end);
}