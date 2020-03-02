package com.ihealthpharm.sales.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.sales.model.SalesReturnItemsModel;

@Repository
public interface SalesReturnItemRepository extends JpaRepository<SalesReturnItemsModel, Integer> {
	 @Query("select DISTINCT sr.salesReturnNumber from sales_return_item sri,sales_return sr "
			 + " where sri.salesReturnId.salesReturnId=sr.salesReturnId  and sr.salesReturnNumber like :searchTerm% ")
	 List<String> findsalesReturnNoInSalesReturn(@Param("searchTerm") String searchTerm);
	 
	 @Query("select DISTINCT sr.salesReturnNumber from sales_return_item sri,sales_return sr "
			 + " where sri.salesReturnId.salesReturnId=sr.salesReturnId order by sr.salesReturnNumber ")
	 List<String> findallsalesReturnNoInSalesReturn();

	 @Query("SELECT sum(sri.returnQuantity) " + 
	 		"FROM sales_return_item sri inner join sales_return sr on sr.salesReturnId.salesReturnId = sri.salesReturnId where sr.billNumber.billId =:billId  and sri.items.itemId=:itemId"
	 		)
	Integer getReturnQtyByItem(@Param("itemId") Integer itemId,@Param("billId")Integer billId);
}
