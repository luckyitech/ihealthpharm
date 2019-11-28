package com.ihealthpharm.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.stock.model.StockModel;

@Repository
public interface StockRepository extends JpaRepository<StockModel, Integer> {

	List<StockModel> findAllByOrderByCreationTimeStampDesc();

	@Query("select s.item from stock s")
	List<ItemsModel> findAllItem();

	@Query("select s.batchNo from stock s where item = :itemId")
	List<String> getBatchNumbersByItemId(@Param("itemId") ItemsModel itemId);

	StockModel findByItemAndBatchNo(ItemsModel itemId, String batchNo);

	// @Query("select s from stock s where s.item.itemId = :itemId and
	// s.invoice.invoiceId = :invoiceId ")
	// StockModel getStockByItemIdandInvoiceId(@Param("itemId") Integer itemId,
	// @Param("invoiceId") Integer invoiceId);

	@Query("select i from stock i where i.item like %:searchTerm%")
	List<StockModel> findByItemName(@Param("searchTerm") ItemsModel searchTerm);

	List<StockModel> findByItem(ItemsModel itemId);

	List<StockModel> findByItemAndPharmacy(ItemsModel itemId, PharmacyModel pharmacy);

	@Query("select s from stock s where s.item.itemId=:itemId")
	StockModel getStockDataBillId(Integer itemId);

	// find stock by item name
	@Query("select s from stock s inner join items i on s.item.itemId=i.itemId where s.item.itemName like :searchTerm% "
			+ "and s.pharmacy.pharmacyId=:pharmacyId")
	List<StockModel> findStockByItemNameAndPharmacyId(@Param("searchTerm") String itemName,
			@Param("pharmacyId") Integer pharmacyId);

	// find stock by item code
	@Query("select s from stock s inner join items i on s.item.itemId=i.itemId where s.item.itemCode like :searchTerm% "
			+ "and s.pharmacy.pharmacyId=:pharmacyId")
	List<StockModel> findStockByItemCodeAndPharmacyId(@Param("searchTerm") String searchTerm,
			@Param("pharmacyId") Integer pharmacyId);

	// find stock by item Description
	@Query("select s from stock s inner join items i on s.item.itemId=i.itemId where s.item.itemDescription like :searchTerm% "
			+ "and s.pharmacy.pharmacyId=:pharmacyId")
	List<StockModel> findStockByItemDescriptionAndPharmacyId(@Param("searchTerm") String searchTerm,@Param("pharmacyId")  Integer pharmacyId);

	// find stock by stock batch number
	@Query("select s from stock s  where s.batchNo like :searchTerm% "
			+ "and s.pharmacy.pharmacyId=:pharmacyId")
	List<StockModel> findStockByBatchNumberAndPharmacyId(@Param("searchTerm") String searchTerm,@Param("pharmacyId")  Integer pharmacyId);

	// find stock by item Description
		@Query("select s from stock s inner join items i on s.item.itemId=i.itemId inner join "
				+ "items_generic_names ig on ig.itemGenericNameId=i.itemGenericName.itemGenericNameId "
				+ "where ig.genericName like :searchTerm% "
				+ "and s.pharmacy.pharmacyId=:pharmacyId")
	List<StockModel> findStockByItemGenericNameAndPharmacyId(@Param("searchTerm") String searchTerm,@Param("pharmacyId") Integer pharmacyId);

	//find supplier by search
		@Query("select distinct sp.name from stock st,invoice_items init,invoice inv,items i,manufacturer m,supplier sp " 
		+ "where init.itemsModel.itemId=st.item.itemId and inv.invoiceId=init.invoice.invoiceId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId "
	    + "and inv.supplierModel.supplierId=sp.supplierId and sp.name like :searchTerm% ")
		List<String> findSupplierInStockPOL(@Param("searchTerm") String searchTerm);
		
	//find all suppliers by stock	
		@Query("select distinct sp.name from stock st,invoice_items init,invoice inv,items i,manufacturer m,supplier sp "
		+ "where init.itemsModel.itemId=st.item.itemId and inv.invoiceId=init.invoice.invoiceId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId "
	    +"and inv.supplierModel.supplierId=sp.supplierId order by sp.name ")
		List<String> findAllSuppliersInStockPOL();
		
	//find manufacturer by search	
		@Query("select distinct m.name from stock st,invoice_items init,invoice inv,items i,manufacturer m,supplier sp "  
				+"where init.itemsModel.itemId=st.item.itemId and inv.invoiceId=init.invoice.invoiceId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId "  
				+"and inv.supplierModel.supplierId=sp.supplierId and m.name like :searchTerm%  ")
				List<String> findManufacturerInStockPOL(@Param("searchTerm") String searchTerm);
		
	//find all manufacturers by stock	
		@Query("select distinct m.name from stock st,invoice_items init,invoice inv,items i,manufacturer m,supplier sp "  
				+"where init.itemsModel.itemId=st.item.itemId and inv.invoiceId=init.invoice.invoiceId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId "  
				+"and inv.supplierModel.supplierId=sp.supplierId order by m.name  ")
				List<String> findAllManufacturerInStockPOL();
		
	//find invoice dates by search
		@Query("select distinct inv.invoiceDt from stock st,invoice_items init,invoice inv,items i,manufacturer m,supplier sp "  
				+"where init.itemsModel.itemId=st.item.itemId and inv.invoiceId=init.invoice.invoiceId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId "  
				+"and inv.supplierModel.supplierId=sp.supplierId and inv.invoiceDt>=:searchTerm  ")
				List<String> findInvoiceDatesInStockPOL(@Param("searchTerm") String searchTerm);
		//find all invoice dates by stock
		
		@Query("select distinct inv.invoiceDt from stock st,invoice_items init,invoice inv,items i,manufacturer m,supplier sp "  
				+"where init.itemsModel.itemId=st.item.itemId and inv.invoiceId=init.invoice.invoiceId and st.item.itemId=i.itemId and i.manufacturer.manufacturerId=m.manufacturerId "  
				+"and inv.supplierModel.supplierId=sp.supplierId order by inv.invoiceDt  ")
				List<String> findAllInvoiceDatesInStockPOL();
}
