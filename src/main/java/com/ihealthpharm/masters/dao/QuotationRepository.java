package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.QuotationModel;

public interface QuotationRepository extends JpaRepository<QuotationModel, Integer> {

	@Query(name="from QuotationModel qm where qm.quotationNo=:qNo")
	List<QuotationModel> findQuotationsByQuotationNo(@Param("qNo") String qNo);

	@Query(name="from QuotationModel qm where qm.activeS = :active")
	List<QuotationModel> findByActiveS(String active);	
	
}