package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.QuotationItemsModel;


@Repository
public interface QuotationItemsRepository extends JpaRepository<QuotationItemsModel, Integer> {

	List<QuotationItemsModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query("select i from quotation_items q join q.item i where q.quotationItemId = :quotationItemId "
			+ "and ( q.quotationItemStatus.status = 'PENDING' or q.quotationItemStatus.status = 'APPROVED' ) ")
	ItemsModel getQuotationItem(@Param("quotationItemId") Integer quotationItemId);
}
