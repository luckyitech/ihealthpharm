package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.dto.ItemDTO;
import com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO;
import com.ihealthpharm.masters.model.ItemGenericNamesModel;
import com.ihealthpharm.masters.model.ItemGroupModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.dto.StockAdjustmentItemDTO;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsModel, Serializable> {

	public List<ItemsModel> findByActiveS(String s);

	@Query("SELECT i FROM items i order by i.lastUpdateTimestamp desc")
	List<ItemsModel> findAllLastestRecords();

	@Query("select i from items i where i.itemName like :searchTerm%  order by i.lastUpdateTimestamp desc")
	List<ItemsModel> findAllByItemNameSearch(@Param("searchTerm") String searchTerm);

	@Query("select i from items i where (i.itemName like :searchTerm%) and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<ItemsModel> findAllByItemNameSearchForSupplier(@Param("searchTerm") String searchTerm);

	@Query("select i from items i where i.medicalOrNonMedical =:medicalOrNonMedical and i.itemName like %:searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByMedicalAndItemName(@Param("medicalOrNonMedical") String medicalOrNonMedical,@Param("searchTerm") String searchTerm);

	@Query("select i from items i where i.medicalOrNonMedical =:medicalOrNonMedical and i.itemDescription like %:searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByMedicalAndItemDesc(@Param("medicalOrNonMedical")String medicalOrNonMedical,@Param("searchTerm")String searchTerm);

	@Query("select i from items i where i.itemDescription like %:searchTerm% order by i.lastUpdateTimestamp desc")
	List<ItemsModel> findAllByItemDescription(@Param("searchTerm") String searchTerm);

	@Query("select i from items i inner join com.ihealthpharm.masters.model.ItemGenericNamesModel ig on i.itemGenericName.itemGenericNameId=ig.itemGenericNameId "
			+ "where ig.genericName like %:searchTerm% order by i.lastUpdateTimestamp desc")
	List<ItemsModel> findByItemGenericName(@Param("searchTerm") String searchTerm);

	List<ItemsModel> findByItemGroup(ItemGroupModel groupCode);

	public List<ItemsModel> findByItemCode(String searchTerm);

	@Query("select i from items i inner join com.ihealthpharm.masters.model.ItemGenericNamesModel ig on "
			+ "i.itemGenericName.itemGenericNameId=ig.itemGenericNameId "
			+ "where (i.itemDescription like %:searchTerm% OR  i.itemCode like %:searchTerm% OR  i.itemName like %:searchTerm% or ig.genericName like %:searchTerm%) and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	public List<ItemsModel> findByItemCodeOrItemNameOrItemDescription(@Param("searchTerm") String searchTerm);

	public List<ItemsModel> findFirst100ByOrderByItemCode();

	public List<ItemsModel> findAll(Specification<ItemsModel> specification);

	public List<ItemsModel> findByItemGenericNameContains(ItemGenericNamesModel genericRes);

	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i where i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<ItemDTO> findItemsByLimit(Pageable pageable);

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentItemDTO(i.itemId,i.itemCode) from items i where i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<StockAdjustmentItemDTO> findItemsByLimitWithItemCode(Pageable pageable);

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentItemDTO(i.itemId,i.itemDescription) from items i  where i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<StockAdjustmentItemDTO>	findItemsByLimitWithItemDesc(Pageable pageable);

	@Query("select new com.ihealthpharm.stock.dto.StockAdjustmentItemDTO(i.itemId,ig.genericName) from items i inner join items_generic_names ig on i.itemGenericName=ig.itemGenericNameId")
	List<StockAdjustmentItemDTO> findItemsByLimitWithItemGenericName(Pageable limit);

	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemName like :searchTerm%  order by i.itemName asc")
	List<ItemDTO> getAllItemsDataByItemName(@Param("searchTerm")String searchTerm, Pageable pageable);

	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemCode like :searchTerm%  order by i.itemName asc")
	List<ItemDTO> getAllItemsDataByItemCode(@Param("searchTerm")String searchTerm, Pageable pageable);

	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemDescription like %:searchTerm% order by i.itemName asc")
	List<ItemDTO> getAllItemsDataByItemDescription(@Param("searchTerm")String searchTerm, Pageable pageable);

	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemGenericName.genericName like %:searchTerm%  order by i.itemName asc")
	List<ItemDTO> getAllItemsDataByItemGenericName(@Param("searchTerm")String searchTerm, Pageable pageable);

	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,i.itemName) from items i  "
			+ "where  i.itemName like :searchTerm%  order by i.lastUpdateTimestamp desc")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemName(@Param("searchTerm") String searchTerm);

	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,i.itemName) from items i  "
			+ "where  i.itemName like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemNameForStock(@Param("searchTerm") String searchTerm);

	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,i.itemCode) from items i  "
			+ "where  i.itemCode like :itemCode%  order by i.lastUpdateTimestamp desc")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemCode(@Param("itemCode") String itemCode);


	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,i.itemCode) from items i  "
			+ "where  i.itemCode like :itemCode% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemCodeForStock(@Param("itemCode") String itemCode);


	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,i.itemDescription) from items i  "
			+ "where  i.itemDescription like :itemdesc%  order by i.lastUpdateTimestamp desc")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemDesc(@Param("itemdesc") String itemdesc);


	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,i.itemDescription) from items i  "
			+ "where  i.itemDescription like :itemdesc% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemDescForStock(@Param("itemdesc") String itemdesc);

	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,ig.genericName) from items i "
			+ " inner join items_generic_names ig on i.itemGenericName=ig.itemGenericNameId "
			+ "where  ig.genericName like :itemGeneric% order by i.lastUpdateTimestamp desc")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemGenericName(@Param("itemGeneric")String itemGeneric);

	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,ig.genericName) from items i "
			+ " inner join items_generic_names ig on i.itemGenericName=ig.itemGenericNameId "
			+ "where ( ig.genericName like :itemGeneric% ) and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemGenericNameForStock(@Param("itemGeneric")String itemGeneric);

	@Query("select count(i) from items i where i.itemName like :searchTerm%  order by i.lastUpdateTimestamp desc")
	public Integer getCountOfItemsByItemName(@Param("searchTerm") String searchTerm);

	@Query("select count(i) from items i where i.itemCode like :searchTerm% order by i.lastUpdateTimestamp desc")
	public Integer getCountOfItemsByItemCode(@Param("searchTerm") String searchTerm);

	@Query("select count(i) from items i where i.itemDescription like :searchTerm% order by i.lastUpdateTimestamp desc")
	public Integer getCountOfItemsByItemDescription(@Param("searchTerm") String searchTerm);

	@Query("select count(i) from items i where i.itemGenericName.genericName like :searchTerm% order by i.lastUpdateTimestamp desc")
	public Integer getCountOfItemsByItemGenericName(@Param("searchTerm") String searchTerm);

	@Query("select count(i) from items i order by i.itemCode")
	public Integer getAllCountOfItems();

	@Query("select new  com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO(s.stockId,i.itemName,s.item.itemId,s.invoiceNo,s.remarks,s.rack,s.batchNo,s.expiryDt,s.quantity,s.quantity,s.shelf) from stock s inner join items i on s.item=i.itemId where i.itemName like :searchTerm% or s.rack like :searchTerm%  order by i.itemName,s.batchNo asc")
	public List<ItemsForStockAdjustDTO> FindByItemNameForStockItemNameSearch(@Param("searchTerm")String searchTerm);

	@Query("select new  com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO(s.stockId,i.itemName,s.item.itemId,s.invoiceNo,s.remarks,s.rack,s.batchNo,s.expiryDt,s.quantity,s.quantity,s.shelf) from stock s inner join items i on s.item=i.itemId where s.barcode like %:barcode%  order by i.itemName,s.batchNo asc")
	public List<ItemsForStockAdjustDTO> FindByBarcodeForStockTakeSearch(@Param("barcode")String barcode);
	
	@Query("select new  com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO(s.stockId,i.itemName,s.item.itemId,s.invoiceNo,s.remarks,s.rack,s.batchNo,s.expiryDt,s.quantity,s.quantity,s.shelf) from stock s inner join items i on s.item=i.itemId  order by i.itemName,s.batchNo asc")
	public List<ItemsForStockAdjustDTO> getAllStockAdjustRecords();

	@Query("select new  com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO(s.stockId,i.itemName,s.item.itemId,s.invoiceNo,s.remarks,s.rack,s.batchNo,s.expiryDt,s.quantity,s.quantity,s.shelf) from stock s inner join items i on s.item=i.itemId where s.rack like :rack% and s.shelf like :shelf% order by i.itemName,s.batchNo asc")
	public List<ItemsForStockAdjustDTO> getAllRecordsByRackAndShelf(@Param("rack") String rack,@Param("shelf") String shelf);

	@Query("select new  com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO(s.stockId,i.itemName,s.item.itemId,s.invoiceNo,s.remarks,s.rack,s.batchNo,s.expiryDt,s.quantity,s.quantity,s.shelf) from stock s inner join items i on s.item=i.itemId where s.rack=:rack and s.shelf=:shelf order by i.itemName,s.batchNo asc")
	public List<ItemsForStockAdjustDTO> getAllRecordsByRackAndShelfForIntegers(@Param("rack") String rack,@Param("shelf") String shelf);

	@Query("select new  com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO(s.stockId,i.itemName,s.item.itemId,s.invoiceNo,s.remarks,s.rack,s.batchNo,s.expiryDt,s.quantity,s.quantity,s.shelf) from stock s inner join items i on s.item=i.itemId where s.item.itemId=:itemId and s.batchNo=:batchNo order by i.itemName,s.batchNo asc")
	public List<ItemsForStockAdjustDTO> getAllRecordsByItemIdAndBatch(@Param("itemId") Integer itemId,@Param("batchNo") String batchNo);

	@Query("select new  com.ihealthpharm.masters.dto.ItemsForStockAdjustDTO(s.stockId,i.itemName,s.item.itemId,s.invoiceNo,s.remarks,s.rack,s.batchNo,s.expiryDt,s.quantity,s.quantity,s.shelf) from stock s inner join items i on s.item=i.itemId where s.stockId=:stockId")
	public List<ItemsForStockAdjustDTO> getAllRecordsByStockId(@Param("stockId") Integer stockId);

	@Query("select i from items i where i.itemCode like :searchTerm% order by i.lastUpdateTimestamp desc")
	List<ItemsModel> findAllByItemCodeSWS(@Param("searchTerm") String searchTerm);

	@Query("select configValue from configuration where configDesc='maxdiscount' and activeS='Y'")
	public Integer getMarkup();

	@Query("select configValue from configuration where configDesc='margin' and activeS='Y'")
	public Integer getMargin();

	@Transactional
	@Modifying
	@Query("update stock s"
			+ " set s.unitSaleRate=ROUND(((s.unitPurchaseRate*(1+(:margin/100)))*(1+(:markup/100))),2), s.entryType='sales price update',s.mrp=s.unitSaleRate "
			+ "where s.unitPurchaseRate is not null and s.item.itemId=:itemId")
	public void updateStockWithMargin(@Param("itemId") Integer itemId,@Param("margin") Integer margin, @Param("markup") Integer markup);

	@Query(value="call StockUpdateByItemId(:itemId)",nativeQuery = true)
	public Integer StockUpdateByItemId(@Param("itemId") Integer itemId);

	@Query("select i from items i where i.barcode like %:barcode% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	public List<ItemsModel> findAllItemsByBarCodeSearchForSupplier(@Param("barcode")String barcode);

	@Transactional
	@Modifying
	@Query("update stock s"
			+ " set s.shelf=:shelfNumber, s.rack=:rackNumber "
			+ "where s.item.itemId=:itemId")
	void updateRackAndShelfinStockTable(@Param("rackNumber")String rackNumber, @Param("shelfNumber")String shelfNumber,@Param("itemId")Integer itemId);
}
