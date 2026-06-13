package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.stock.model.QuotationStatusModel;


@Repository
public interface QuotationStatusRepository extends JpaRepository<QuotationStatusModel, Integer> {

	List<QuotationStatusModel> findAllByOrderByStatusDesc();
	
	@Query("select q from quotation_status q where q.status = :status ")
	QuotationStatusModel findByStatus(@Param("status") String status);
}
