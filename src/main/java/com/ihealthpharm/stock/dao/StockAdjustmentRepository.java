package com.ihealthpharm.stock.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.dto.StockAdjustmentDTO;
import com.ihealthpharm.stock.model.StockAdjustmentModel;
import com.ihealthpharm.stock.model.StockModel;

@Repository
public interface StockAdjustmentRepository extends JpaRepository<StockAdjustmentModel, Serializable> {

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentDTO(i,f,b,sum(b.quantity) as onHandStock) from stock b "
			+ " inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId inner join supplier s on b.supplier=s.supplierId "
			+ " where i.itemCode like %:searchTerm% and b.batchNo=:batchNo and b.expiryDt=:dates and b.pharmacy.pharmacyId=:pharmacyId"
			+ " group by b.batchNo,b.expiryDt,b.pharmacy")
	List<StockAdjustmentDTO> getStockItemsOnItemCodes(@Param("searchTerm")String searchTerm,@Param("batchNo")String batchNo,@Param("dates")Date  dates,@Param("pharmacyId")int pharmacyId);

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentDTO(i,f,b,sum(b.quantity) as onHandStock) from stock b "
			+ " inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId inner join supplier s on b.supplier=s.supplierId "
			+ " where i.itemName like %:searchTerm% and b.batchNo=:batchNo and b.expiryDt=:dates and b.pharmacy.pharmacyId=:pharmacyId"
			+ " group by b.batchNo,b.expiryDt,b.pharmacy")
	List<StockAdjustmentDTO> getStockItemsOnItemNames(@Param("searchTerm")String searchTerm,@Param("batchNo")String batchNo,@Param("dates")Date  dates,@Param("pharmacyId")int pharmacyId);

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentDTO(i,f,b,sum(b.quantity) as onHandStock) from stock b "
			+ " inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId inner join supplier s on b.supplier=s.supplierId "
			+ " where i.itemDescription like %:searchTerm% and b.batchNo=:batchNo and b.expiryDt=:dates and b.pharmacy.pharmacyId=:pharmacyId"
			+ " group by b.batchNo,b.expiryDt,b.pharmacy")
	List<StockAdjustmentDTO> getStockItemsOnItemDesc (@Param("searchTerm")String searchTerm,@Param("batchNo")String batchNo,@Param("dates")Date  dates,@Param("pharmacyId")int pharmacyId);

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentDTO(i,f,b,sum(b.quantity) as onHandStock) from stock b "
			+ " inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId inner join supplier s on b.supplier=s.supplierId "
			+ " where i.itemGenericName.genericName like %:searchTerm% and b.batchNo=:batchNo and b.expiryDt=:dates and b.pharmacy.pharmacyId=:pharmacyId"
			+ " group by b.batchNo,b.expiryDt,b.pharmacy")
	List<StockAdjustmentDTO> findByItemGenericNames(@Param("searchTerm")String searchTerm,@Param("batchNo")String batchNo,@Param("dates")Date  dates,@Param("pharmacyId")int pharmacyId);

	
	
	
	@Query("select sum(b.quantity) from stock b inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId inner join supplier s on b.supplier=s.supplierId " + 
			" where  b.batchNo=:batchNo and b.expiryDt=:dates and b.pharmacy.pharmacyId=:pharmacyId")	
	Integer getAllStockQuantity(@Param("batchNo")String batchNo,@Param("dates")Date  dates,@Param("pharmacyId")int pharmacyId);

	
	
	@Query("select i from stock b inner join items i on b.item=i.itemId inner join items_forms f on i.itemForm=f.itemformId inner join supplier s on b.supplier=s.supplierId " + 
			" where  b.batchNo=:batchNo and b.expiryDt=:expiry and b.pharmacy.pharmacyId=:pharmacyId")	
	List<StockModel> getAllStocksMatchWithStockAdjId(@Param("batchNo")String batchNo,@Param("expiry")Date  expiry,@Param("pharmacyId")int pharmacyId);

}
