package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.stock.model.QuotationItemsModel;


@Repository
public interface QuotationItemsRepository extends JpaRepository<QuotationItemsModel, Integer> {

	List<QuotationItemsModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query("select new com.ihealthpharm.masters.dto.ItemSupplierDTO(i.itemCode, i.itemName, i.itemDescription, "
			+ " i.itemId, i.manufacturer.name) from quotation_items q join q.item i "
			+ " where q.quotationItemId = :quotationItemId "
			+ " and ( q.quotationItemStatus.status = 'PENDING' or q.quotationItemStatus.status = 'APPROVED' ) ")
	ItemSupplierDTO getQuotationItem(@Param("quotationItemId") Integer quotationItemId);
	
	@Query("select q from quotation_items q where q.quotationItemStatus.status = :status ")
	List<QuotationItemsModel> getQuotaionItemsByStatus(@Param("status") String status);
	
	@Query("select q from quotation_items q where q.quotationItemStatus.status = :status and "
			+ " q.supplier.name like %:name% ")
	List<QuotationItemsModel> getQuotaionItemsByStatus(@Param("status") String status, @Param("name") String name);
	
	
}
