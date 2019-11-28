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
	@Query("select s from stock s inner join items i on s.item.itemId=i.itemId where s.item.itemDescription like %:searchTerm% "
			+ "and s.pharmacy.pharmacyId=:pharmacyId")
	List<StockModel> findStockByItemDescriptionAndPharmacyId(@Param("searchTerm") String searchTerm,@Param("pharmacyId")  Integer pharmacyId);

	// find stock by stock batch number
	@Query("select s from stock s  where s.batchNo like :searchTerm% "
			+ "and s.pharmacy.pharmacyId=:pharmacyId")
	List<StockModel> findStockByBatchNumberAndPharmacyId(@Param("searchTerm") String searchTerm,@Param("pharmacyId")  Integer pharmacyId);

	// find stock by item Description
		@Query("select s from stock s inner join items i on s.item.itemId=i.itemId inner join "
				+ "items_generic_names ig on ig.itemGenericNameId=i.itemGenericName.itemGenericNameId "
				+ "where ig.genericName like %:searchTerm% "
				+ "and s.pharmacy.pharmacyId=:pharmacyId")
	List<StockModel> findStockByItemGenericNameAndPharmacyId(@Param("searchTerm") String searchTerm,@Param("pharmacyId") Integer pharmacyId);
	
	@Query("select st from stock st where st.batchNo like %:searchTerm% order by st.creationTimeStamp desc")
	List<StockModel> findAllByBatchNoSearch(@Param("searchTerm") String searchTerm);
}
