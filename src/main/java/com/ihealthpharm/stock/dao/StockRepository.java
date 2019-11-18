package com.ihealthpharm.stock.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.stock.model.StockModel;


@Repository
public interface StockRepository extends JpaRepository<StockModel, Integer> {

	List<StockModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query("select s.item from stock s")
	List<ItemsModel> findAllItem();
	
	@Query("select s.batchNo from stock s where item = :itemId")
	List<String> getBatchNumbersByItemId(@Param("itemId")ItemsModel itemId);

	StockModel findByItemAndBatchNo(ItemsModel itemId, String batchNo);
	
	//@Query("select s from stock s where s.item.itemId = :itemId and s.invoice.invoiceId = :invoiceId ")
	//StockModel getStockByItemIdandInvoiceId(@Param("itemId") Integer itemId, @Param("invoiceId") Integer invoiceId);
	
	@Query("select i from stock i where i.item like %:searchTerm%")
	List<StockModel> findByItemName(@Param("searchTerm") ItemsModel searchTerm);

	List<StockModel> findByItem(ItemsModel itemId);

	List<StockModel> findByItemAndPharmacy(ItemsModel itemId, PharmacyModel pharmacy);
	
}
