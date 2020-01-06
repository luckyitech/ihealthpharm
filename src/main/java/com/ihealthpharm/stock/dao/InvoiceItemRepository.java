package com.ihealthpharm.stock.dao;

import java.sql.Array;
import java.sql.Date;
import java.util.List;

import com.ihealthpharm.stock.model.InvoiceItemModel;
import com.ihealthpharm.stock.model.StockModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItemModel, Integer> {

	List<InvoiceItemModel> findAllByOrderByCreationTimeStampDesc();
	//Purchase Invoice Details Queries
	@Query("select distinct sp.name from invoice_items init,invoice inv,items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and "
			+ "init.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and "
			+ "inv.supplierModel.supplierId=sp.supplierId and sp.name like :searchTerm%")
	List<String> findSupplierInInvoiceItems(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct sp.name from invoice_items init,invoice inv,items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and "
			+ "init.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and "
			+ "inv.supplierModel.supplierId=sp.supplierId order by sp.name")
	List<String> findAllSuppliersInInvoiceItems();
	
	@Query("select distinct inv.grnNo from invoice_items init,invoice inv,items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and "
			+ "init.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and "
			+ "inv.supplierModel.supplierId=sp.supplierId and inv.grnNo like :searchTerm%")
	List<String> findinvoiceNoInInvoiceItems(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct inv.grnNo from invoice_items init,invoice inv,items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and "
			+ "init.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and "
			+ "inv.supplierModel.supplierId=sp.supplierId order by inv.grnNo")
	List<String> findAllinvoiceNoInInvoiceItems();
	
	@Query("select distinct inv.invoiceDt from invoice_items init,invoice inv,items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and "
			+ "init.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and "
			+ "inv.supplierModel.supplierId=sp.supplierId and inv.invoiceDt >= :searchTerm")
	List<String> findinvoiceDtInInvoiceItems(@Param("searchTerm") Date searchTerm);
	
	@Query("select distinct inv.invoiceDt from invoice_items init,invoice inv,items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and "
			+ "init.itemsModel.itemId=i.itemId and st.item.itemId=i.itemId and "
			+ "inv.supplierModel.supplierId=sp.supplierId order by inv.invoiceDt")
	List<String> findAllinvoiceDtInInvoiceItems();
	
	@Query("select distinct inv.invoiceNo from invoice_items init,invoice inv "
			+ "where init.invoice.invoiceId=inv.invoiceId and inv.invoiceNo like :searchTerm%")
	List<String> findinvoiceNumberInInvoiceItems(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct inv.invoiceNo from invoice_items init,invoice inv "
			+ "where init.invoice.invoiceId=inv.invoiceId order by inv.invoiceNo")
	List<String> findAllinvoiceNumbersInInvoiceItems();
	
	//Purchase Margin Comparison
	
	@Query("select distinct i.itemName from invoice_items init,invoice inv, items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and init.itemsModel.itemId=i.itemId "
			+ "and st.item.itemId=i.itemId and  inv.supplierModel.supplierId=sp.supplierId and i.itemName like :searchTerm%")
	List<String> findItemNamesInInvoiceItemsPMC(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct i.itemName from invoice_items init,invoice inv, items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and init.itemsModel.itemId=i.itemId "
			+ "and st.item.itemId=i.itemId and  inv.supplierModel.supplierId=sp.supplierId order by i.itemName")
	List<String> findAllItemNamesInInvoiceItemsPMC();
	
	@Query("select distinct sp.name from invoice_items init,invoice inv, items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and init.itemsModel.itemId=i.itemId "
			+ "and st.item.itemId=i.itemId and  inv.supplierModel.supplierId=sp.supplierId and sp.name like :searchTerm%")
	List<String> findSuppliersInInvoiceItemsPMC(@Param("searchTerm") String searchTerm);
	
	@Query("select distinct sp.name from invoice_items init,invoice inv, items i,stock st,supplier sp "
			+ "where init.invoice=inv.invoiceId and init.itemsModel.itemId=i.itemId "
			+ "and st.item.itemId=i.itemId and  inv.supplierModel.supplierId=sp.supplierId order by sp.name")
	List<String> findAllSuppliersInInvoiceItemsPMC();
	
}
