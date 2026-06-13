package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.QuotationItemStatusModel;


@Repository
public interface QuotationItemStatusRepository extends JpaRepository<QuotationItemStatusModel, Integer> {

	List<QuotationItemStatusModel> findAllByOrderByStatusDesc();
	
	@Query("select q from quotation_item_status q where q.status = :status ")
	QuotationItemStatusModel findByStatus(@Param("status") String status);
}
