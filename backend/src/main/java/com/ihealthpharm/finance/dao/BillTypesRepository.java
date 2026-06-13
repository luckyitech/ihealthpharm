package com.ihealthpharm.finance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.finance.model.BillTypeModel;

@Repository
public interface BillTypesRepository extends JpaRepository<BillTypeModel, Integer>{
	
	@Query("select bt from bill_types bt")
	List<BillTypeModel> getAllBillTypes();
	
	@Query("select bt.type from bill_types bt")
	List<String> getCNByBillType();
}
