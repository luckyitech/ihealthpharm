package com.ihealthpharm.stock.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.dto.StockAdjustmentDTO;
import com.ihealthpharm.stock.model.StockAdjustmentModel;

@Repository
public interface StockAdjustmentRepository extends JpaRepository<StockAdjustmentModel, Serializable> {

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentDTO(i,f,b,sum(b.quantity) as onHandStock) from stock b inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId where i.itemCode like %:searchTerm% "
			+ "group by i.itemId,b.batchNo,b.expiryDt")
	List<StockAdjustmentDTO> getStockItemsOnItemCodes(@Param("searchTerm")String searchTerm);

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentDTO(i,f,b,sum(b.quantity) as onHandStock) from stock b inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId where i.itemName like %:searchTerm% "
			+ "group by i.itemId,b.batchNo,b.expiryDt")
	List<StockAdjustmentDTO> getStockItemsOnItemNames(@Param("searchTerm")String searchTerm);

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentDTO(i,f,b,sum(b.quantity) as onHandStock) from stock b inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId where i.itemDescription like %:searchTerm% "
			+ "group by i.itemId,b.batchNo,b.expiryDt")
	List<StockAdjustmentDTO> getStockItemsOnItemDesc (@Param("searchTerm")String searchTerm);

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentDTO(i,f,b,sum(b.quantity) as onHandStock) from stock b inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId where i.itemGenericName.genericName like %:searchTerm% "
			+ "group by i.itemId,b.batchNo,b.expiryDt")
	List<StockAdjustmentDTO> findByItemGenericNames(@Param("searchTerm")String searchTerm);
	
	
}
