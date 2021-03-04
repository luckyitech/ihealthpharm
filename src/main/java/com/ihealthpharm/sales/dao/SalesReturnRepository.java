package com.ihealthpharm.sales.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.sales.model.SalesReturnModel;

public interface SalesReturnRepository extends JpaRepository<SalesReturnModel, Integer> {

	List<SalesReturnModel> findAllByOrderByLastUpdateTimestampDesc();

	@Query(" select sr.billNumber.billCode from sales_return sr " + 
			" inner join sales s on s.billId = sr.billNumber.billId " + 
			" inner join employee e on e.employeeId=sr.createdUser where concat(e.firstName , ' ' , e.lastName)=:searchTerm " + 
			" order by sr.creationTimeStamp desc")
	List<String> findLastSRIByEmp(@Param("searchTerm") String searchTerm);

	@Query(" select sr.billNumber.billCode from  sales_return sr " + 
			" INNER JOIN sales_items si ON sr.billNumber.billId = si.billId.billId " + 
			" inner join sales s on s.billId=sr.billNumber.billId " + 
			" INNER JOIN items i ON i.itemId = si.itemsModel.itemId  " + 
			" inner join customer c on c.customerId=s.customerModel.customerId where concat(c.customerName ,' ', c.lastName)= :searchTerm " + 
			" order by sr.creationTimeStamp desc ")
	List<String> findLastSRIByCust(@Param("searchTerm") String searchTerm);

	@Query("select s from sales_return s order by s.lastUpdateTimestamp desc")
	List<SalesReturnModel> getAllSalesReturnData();

	@Transactional
	@Modifying
	@Query("update sales_return set remarks=:remarks,status='Paid' where salesReturnNumber=:srNo")
	void updateRemarksStatusInSalesReturn(@Param("remarks")String remarks, @Param("srNo")String srNo);

	
	@Query("select s from sales_return s where salesReturnNumber=:srNo")
	SalesReturnModel getSalesReturnDataByRefNo(@Param("srNo")String srNo);
}
