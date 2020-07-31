package com.ihealthpharm.sales.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.dto.ItemDTO;
import com.ihealthpharm.sales.dto.SalesBillDTO;
import com.ihealthpharm.sales.dto.SalesBillsLimitedDTO;
import com.ihealthpharm.sales.dto.SalesByDatesDTO;
import com.ihealthpharm.sales.dto.SalesByHour;
import com.ihealthpharm.sales.dto.SalesByPersonsDTO;
import com.ihealthpharm.sales.dto.SalesDTO;
import com.ihealthpharm.sales.model.SalesModel;

@Repository
public interface SalesRepository extends JpaRepository<SalesModel, Integer> {

	List<SalesModel> findAll(Specification<SalesModel> specification);

	SalesModel findByBillCode(String searchTerm);

	// @Query("select s from sales s order by s.billDate desc limit 100")
	List<SalesModel> findFirst100ByOrderByBillCodeDesc();

	@Query("SELECT new com.ihealthpharm.sales.dto.SalesDTO(billDate, sum(totalQty) as totalSales) FROM sales s where TIMESTAMPDIFF(MONTH, billDate, now()) <= 10 GROUP BY month(billDate)  order by billDate")
	List<SalesDTO> getAllSalesDataForCharts();

	// SC

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
	
	
	@Query("SELECT s.billCode from sales s where s.billCode like :searchTerm%")
	List<String> findBillCodeINSalesSRBB(@Param("searchTerm") String searchTerm);

	@Query("SELECT s.customerNm from sales s  WHERE YEAR(billDate) = YEAR(now()) "
			+ "and MONTH(billDate) > ( MONTH(now()) -2 ) "
			+ "order by s.billDate desc")
	List<String> findAllCustomers();

	@Query("SELECT s.customerNm from sales s where s.customerNm like :searchTerm%")
	List<String> findCustomersBySearch(@Param("searchTerm")String searchTerm);
	
	@Query("SELECT s.billCode from sales s where s.customerNm = :customer")
	List<String> findBillCodesByCustomer(@Param("customer")String customer);
	
	@Query("SELECT s.billCode from sales s where (s.billDate BETWEEN :fromDate AND :toDate)")
	List<String> getBillCodesByDates(@Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);
	
	@Query("SELECT s.billCode from sales s where (s.billDate >= :fromDate)")
	List<String> getBillCodesByFromDate(@Param("fromDate")LocalDate start);

	@Query("SELECT s.billCode from sales s where (s.billDate <= :toDate)")
	List<String> getBillCodesByToDate(@Param("toDate")LocalDate end);
	
	@Query("SELECT s.customerNm from sales s where s.billCode = :billCode")
	List<String> findCustomersByBillCode(@Param("billCode")String billCode);
	
	//End of report Code

	@Query("select new com.ihealthpharm.sales.dto.SalesBillDTO(i.billId,i.billCode) from sales i  "
			+ "where  i.billCode like %:billCode%")
	List<SalesBillDTO> getAllSalesBySalesIdSearch(@Param("billCode") String billCode);

	// sales history searches
	@Query("select s from sales s where s.paymentStatus = :key order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesByPaymentStatus(@Param("key") String key, Pageable limit);

	@Query("select s from sales s where s.paymentStatus = :status and s.billDate between :start and :end "
			+ "and (s.customerNm like :code% or s.billCode like :code% ) and s.activeS='Y' order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesSearchByStatusSearchCodeDate(@Param("status") String status,@Param("code") String code,@Param("start") LocalDate start,@Param("end") LocalDate end, Pageable limit);

	@Query("select s from sales s where s.paymentStatus = :status and s.billDate between :start and :end and s.activeS='Y' order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesSearchByStatusDate(@Param("status") String status,@Param("start") LocalDate start,@Param("end") LocalDate end, Pageable limit);

	@Query("select s from sales s where (s.billDate between :start and :end) and (s.customerNm like :code% or s.billCode like :code%) and s.activeS='Y' order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesSearchByCodeDate(@Param("code") String code,@Param("start") LocalDate start,@Param("end") LocalDate end, Pageable limit);

	@Query("select s from sales s where s.billCode like :key% order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesByBillCode(@Param("key") String key, Pageable limit);

	@Query("select s from sales s inner join customer c on s.customerModel.customerId=c.customerId where c.customerName like %:key% or c.lastName like %:key% order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesByCustomerName(@Param("key") String key, Pageable limit);

	
	@Query("select s from sales s inner join employee e on e.employeeId=s.creationUserId where e.firstName  like %:key% or e.lastName  like %:key% order by s.lastUpdateTs DESC")
	List<SalesModel> findAllSalesBySalesPersonName(@Param("key") String key, Pageable limit);
	
	@Query("select s from sales s inner join customer c on s.customerModel.customerId=c.customerId where c.phoneNumber  like :key% order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesByCustomerPhoneNumber(@Param("key") String key, Pageable limit);

	@Query("select s from sales s where s.billDate between :start and :end order by s.lastUpdateTs DESC")
	List<SalesModel> findSalesByBillDate(@Param("start") LocalDate start,@Param("end") LocalDate end, Pageable limit);

	//counts

	@Query("select count(s) from sales s where s.paymentStatus = :status and s.billDate between :start and :end and s.customerNm like :code% or s.billCode like :code% order by s.lastUpdateTs DESC")
	Integer findSalesSearchByStatusSearchCodeDateCount(@Param("status") String status,@Param("code") String code,@Param("start") LocalDate start,@Param("end") LocalDate end);

	@Query("select count(s) from sales s where s.paymentStatus = :status and s.billDate between :start and :end  order by s.lastUpdateTs DESC")
	Integer findSalesSearchByStatusDateCount(@Param("status") String status,@Param("start") LocalDate start,@Param("end") LocalDate end);

	@Query("select count(s) from sales s where (s.billDate between :start and :end) and (s.customerNm like :code% or s.billCode like :code%)  order by s.lastUpdateTs DESC")
	Integer findSalesSearchByCodeDateCount(@Param("code") String code,@Param("start") LocalDate start,@Param("end") LocalDate end);

	@Query("select count(s) from sales s where s.paymentStatus = :key order by s.lastUpdateTs DESC")
	Integer findSalesByPaymentStatusCount(@Param("key") String key );

	@Query("select count(s) from sales s where s.billCode like :key% order by s.lastUpdateTs DESC")
	Integer findSalesByBillCodeCount(@Param("key") String key);

	@Query("select count(s) from sales s inner join customer c on s.customerModel.customerId=c.customerId where c.customerName like %:key% or c.lastName like %:key% order by s.lastUpdateTs DESC")
	Integer findSalesByCustomerNameCount(@Param("key") String key);

	@Query("select count(s) from sales s inner join employee e on e.employeeId=s.creationUserId where e.firstName  like %:key% or e.lastName  like %:key% order by s.lastUpdateTs DESC")
	Integer findSalesBySalesPersonCount(@Param("key") String key);
	

	@Query("select count(s) from sales s inner join customer c on s.customerModel.customerId=c.customerId where c.phoneNumber like :key% order by s.lastUpdateTs DESC")
	Integer findSalesByCustomerPhoneNumberCount(@Param("key") String key);

	@Query("select count(s) from sales s where s.billDate between :start and :end order by s.billDate DESC")
	Integer findSalesByBillDateCount(@Param("start") LocalDate start,@Param("end") LocalDate end);

	@Query("SELECT sum(s.netAmount) from sales s where date(s.billDate) = CURDATE() and PAYMENT_STATUS not in ('CANCEL','DUMMY BILL') group by s.billDate")
	Integer todaySalesRepo();

	@Query("select count(s.customerModel.customerId) from sales s where date(billDate) = CURDATE() and s.cashAmount is not null  group by billDate")
	Integer cashRepo();

	@Query("select count(s.customerModel.customerId) from sales s where date(billDate) = CURDATE() and s.creditAmount is not null  group by billDate")
	Integer creditRepo();

	@Query("SELECT sum(s.netAmount) AS YesterdaySales  from sales s where PAYMENT_STATUS not in ('CANCEL','DUMMY BILL') and billDate = (select max(billDate) from sales where billDate < curdate())")
	Integer yesterdayDiff();

	@Query("select count(s.customerModel.customerId) from sales s where date(billDate) = CURDATE() and s.upiAmount is not null  group by billDate")
	Integer upiCustomers();

	@Query("select count(s.customerModel.customerId) from sales s where date(billDate) = CURDATE() and s.creditCardAmount is not null  group by billDate")
	Integer creditCardCustomers();

	@Query("select count(s.customerModel.customerId) from sales s where date(billDate) = CURDATE() and s.chequeAmount is not null  group by billDate")
	Integer chequeCustomers();

	@Query("select sum(s.cashAmount) from sales s where date(billDate) = CURDATE() and s.cashAmount is not null and PAYMENT_STATUS not in ('CANCEL','DUMMY BILL') group by billDate")
	Integer cashAmount();

	@Query("select sum(s.creditAmount) from sales s where date(billDate) = CURDATE() and s.creditAmount is not null and PAYMENT_STATUS not in ('CANCEL','DUMMY BILL') group by billDate")
	Integer creditAmount();

	@Query("select sum(s.upiAmount) from sales s where date(billDate) = CURDATE() and s.upiAmount is not null and PAYMENT_STATUS not in ('CANCEL','DUMMY BILL') group by billDate")
	Integer upiAmount();

	@Query("select sum(s.creditCardAmount) from sales s where date(billDate) = CURDATE() and s.creditCardAmount is not null and PAYMENT_STATUS not in ('CANCEL','DUMMY BILL') group by billDate")
	Integer CreditCardAmount();

	@Query("select sum(s.chequeAmount) from sales s where date(billDate) = CURDATE() and s.chequeAmount is not null and PAYMENT_STATUS not in ('CANCEL','DUMMY BILL') group by billDate")
	Integer chequeAmount();

	@Query("select new com.ihealthpharm.sales.dto.SalesByDatesDTO( concat(year(s.billDate),'-',month(s.billDate)) as date, (sum(s.totalAmount))/1000) from sales s inner join employee e on s.employeeModel.employeeId = e.employeeId\r\n" + 
			"where s.billDate between :fromDate and :toDate and e.employeeId=:employeeId group by month(s.billDate) order by s.billDate")
	List<SalesByDatesDTO> getSalesByDatesRepo(@Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate, @Param("employeeId")Integer empId);
	
	@Query("select new com.ihealthpharm.sales.dto.SalesByPersonsDTO(concat(e.firstName,' ',e.lastName) as name, sum(s.totalAmount) as amount) from employee e, sales s "
			+ "where s.employeeModel.employeeId = e.employeeId and TIMESTAMPDIFF(MONTH, s.billDate, now()) <= 10\r\n" + 
			"group by e.employeeId order by amount desc")
	List<SalesByPersonsDTO> getSalesByPersonRepo();

	@Query("select new com.ihealthpharm.sales.dto.SalesBillsLimitedDTO(b.billId,b.billCode) from sales b where b.activeS='Y' order by b.lastUpdateTs desc")
	 List<SalesBillsLimitedDTO> findBillsByLimit(Pageable pageable);
	
	@Query("select new com.ihealthpharm.sales.dto.SalesBillsLimitedDTO(b.billId,b.billCode) from sales b  "
			+ "where  b.billCode like :searchTerm%  order by b.lastUpdateTs desc")
	List<SalesBillsLimitedDTO> getBillsByName(@Param("searchTerm") String searchTerm);

	
	@Query("select new com.ihealthpharm.sales.dto.SalesByHour((sum(s.totalAmount))/1000, HOUR(TIME(s.creationTs))) from sales s where s.billDate = :start  and s.employeeModel.employeeId = :selectedChartEmployee and HOUR(TIME(s.creationTs)) between :fromTime and :toTime group by  HOUR(TIME(s.creationTs)) ")
	List<SalesByHour> findSalesByHours(@Param("selectedChartEmployee")int selectedChartEmployee, @Param("start") LocalDate start, @Param("fromTime")int fromTime,@Param("toTime") int toTime);
	
}