package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.PurchaseReturnItemModel;

@Repository
public interface PurchaseReturnItemRepository extends JpaRepository<PurchaseReturnItemModel, Integer> {
	//Purchase Return
	
		@Query("select distinct inv.invoiceNo from invoice inv where inv.invoiceNo like :searchTerm%")
		List<String> findinvoiceNoInPR(@Param("searchTerm") String searchTerm);
		
		@Query("select distinct i.itemName,inv.invoiceNo,pr.purchaseReturnDt,pri.amount,"
				+ "pri.returnQuantity,pri.purchaseQuantity from invoice inv,items i,"
				+ "purchase_return_item pri,purchase_return pr where "
				+ "pr.invoiceModel.invoiceId=inv.invoiceId and "
				+ "pri.purchaseReturnModel.purchaseReturnId=pr.purchaseReturnId "
				+ "and pri.itemsModel.itemId=i.itemId order by inv.invoiceNo")
		List<String> findAllinvoiceNoInPR();
}
