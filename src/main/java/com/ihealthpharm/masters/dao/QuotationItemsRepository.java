package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.QuotationItemsModel;
import com.ihealthpharm.masters.model.QuotationModel;

@Repository
public interface QuotationItemsRepository extends JpaRepository<QuotationItemsModel,Integer>
{
	
	@Query(name="from QuotationItemsModel qim where qm.quotationId=:qId.quotationId")
	List<QuotationItemsModel> findQuotationItemByQuotationId(@Param("qId") QuotationModel qId);

	@Query(name="from QuotationItemsModel qm where qm.activeS = :active")
	List<QuotationItemsModel> findByActiveS(String active);	
	
}