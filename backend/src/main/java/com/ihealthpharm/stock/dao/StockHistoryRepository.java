package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.StockHistoryModel;


@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistoryModel, Integer> {

//Slow Moving Product Details
	
			@Query("select distinct i.itemCode from stock_history sh,items i "
					+ "where sh.item.itemId=i.itemId and i.itemCode like :searchTerm% ")
			List<String> findItemCodeInStockHisSMPD(@Param("searchTerm") String searchTerm);
			
			@Query("select distinct i.itemCode from stock_history sh,items i "
					+ "where sh.item.itemId=i.itemId order by i.itemCode")
			List<String> findAllItemCodesInStockHisSMPD();
	
			@Query("select distinct i.itemName from stock_history sh,items i "
					+ "where sh.item.itemId=i.itemId and i.itemName like :searchTerm% ")
			List<String> findItemNameInStockHisSMPD(@Param("searchTerm") String searchTerm);
			
			@Query("select distinct i.itemName from stock_history sh,items i "
					+ "where sh.item.itemId=i.itemId order by i.itemName")
			List<String> findAllItemNameInStockHisSMPD();
}
