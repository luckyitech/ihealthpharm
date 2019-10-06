package com.ihealthpharm.stock.dao;

import java.util.List;

import com.ihealthpharm.stock.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer> {

	List<InvoiceModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query("select d.name from distributor d where d.distributorId = :distributorId ")
	String getDistributorNameById(@Param("distributorId") Integer distributorId);
	
	@Query("select i from invoice i where i.pharmacy.pharmacyId = :pharmacyId ")
	List<InvoiceModel> findAllInvoicesByPharmacyId(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select count(*) from invoice i where i.distributorModel.distributorId = :distributorId ")
	Long getInvoiceCount(@Param("distributorId") Integer distributorId);
}
