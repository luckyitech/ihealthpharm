package com.ihealthpharm.stock.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.InvoiceModel;

@Repository

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer> {
	List<InvoiceModel> findAllByOrderByCreationTimeStampDesc();
	
	@Query("select d.name from supplier d where d.supplierId = :suppliersId ")
	String getSupplierNameById(@Param("suppliersId") Integer suppliersId);
	
	@Query("select i from invoice i where i.pharmacy.pharmacyId = :pharmacyId ")
	List<InvoiceModel> findAllInvoicesByPharmacyId(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select count(*) from invoice i where i.pharmacy.pharmacyId = :pharmacyId ")
	Long getInvoiceCount(@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select count(*) from purchase_return p ")
	Long getPurchaseReturnCount();
	
	@Query("select ii.itemsModel from invoice i join i.invoiceItems ii where i.invoiceId = :invoiceId ")
	List<ItemsModel> getInvoiceItems(@Param("invoiceId") Integer invoiceId);
	
	List<InvoiceModel> findByInvoiceNo(String invoiceNo);
	
	@Query("select i from invoice i where i.invoiceNo like %:searchTerm% order by i.creationTimeStamp desc")
	List<InvoiceModel> findAllByInvoiceNoSearch(@Param("searchTerm") String searchTerm);
	
	//Purchase Invoice Report
		@Query("select distinct sp.name from invoice inv,invoice_items init,supplier sp where init.invoice=inv.invoiceId and "
				+ "inv.supplierModel.supplierId=sp.supplierId and sp.name like :searchTerm%")
		List<String> findSuppliersInInvoicePIR(@Param("searchTerm") String searchTerm);
		
		@Query("select distinct sp.name from invoice inv,invoice_items init,supplier sp where init.invoice=inv.invoiceId and "
				+ "inv.supplierModel.supplierId=sp.supplierId order by sp.name")
		List<String> findAllSuppliersInInvoicePIR();
	
		@Query("select distinct inv.invoiceDt from invoice inv,invoice_items init,supplier sp where init.invoice=inv.invoiceId and "
				+ "inv.supplierModel.supplierId=sp.supplierId")
		List<String> findInvoiceDtInInvoicePIR(@Param("searchTerm") String searchTerm);
		
		@Query("select distinct inv.invoiceDt from invoice inv,invoice_items init,supplier sp where init.invoice=inv.invoiceId and "
				+ "inv.supplierModel.supplierId=sp.supplierId order by inv.invoiceDt")
		List<String> findAllInvoiceDtInInvoicePIR();
		
}
