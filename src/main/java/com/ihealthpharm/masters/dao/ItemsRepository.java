package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.dto.ItemDTO;
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
			+ "where i.itemDescription like %:searchTerm% OR  i.itemCode like %:searchTerm% OR  i.itemName like %:searchTerm% or ig.genericName like %:searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
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
			+ "where  i.itemName like :searchTerm%  order by i.lastUpdateTimestamp desc")
	List<ItemDTO> getAllItemsDataByItemName(@Param("searchTerm")String searchTerm, Pageable pageable);

	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemCode like :searchTerm%  order by i.lastUpdateTimestamp desc")
	List<ItemDTO> getAllItemsDataByItemCode(@Param("searchTerm")String searchTerm, Pageable pageable);

	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemDescription like %:searchTerm% order by i.lastUpdateTimestamp desc")
	List<ItemDTO> getAllItemsDataByItemDescription(@Param("searchTerm")String searchTerm, Pageable pageable);

	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemGenericName.genericName like %:searchTerm%  order by i.lastUpdateTimestamp desc")
	List<ItemDTO> getAllItemsDataByItemGenericName(@Param("searchTerm")String searchTerm, Pageable pageable);

	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,i.itemName) from items i  "
			+ "where  i.itemName like :searchTerm%  order by i.lastUpdateTimestamp desc")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemName(@Param("searchTerm") String searchTerm);
	
	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,i.itemName) from items i  "
			+ "where  i.itemName like :searchTerm%  order by i.lastUpdateTimestamp desc")
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
			+ "where  ig.genericName like :itemGeneric%  order by i.lastUpdateTimestamp desc")
    List<AlternativeItemDTO> getAlternativeItemsDataByItemGenericName(@Param("itemGeneric")String itemGeneric);
	
	
	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,ig.genericName) from items i "
			+ " inner join items_generic_names ig on i.itemGenericName=ig.itemGenericNameId "
			+ "where  ig.genericName like :itemGeneric% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
    List<AlternativeItemDTO> getAlternativeItemsDataByItemGenericNameForStock(@Param("itemGeneric")String itemGeneric);
	
	
	
	@Query("select count(i) from items i where i.itemName like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	public Integer getCountOfItemsByItemName(@Param("searchTerm") String searchTerm);

	@Query("select count(i) from items i where i.itemCode like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	public Integer getCountOfItemsByItemCode(@Param("searchTerm") String searchTerm);

	@Query("select count(i) from items i where i.itemDescription like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	public Integer getCountOfItemsByItemDescription(@Param("searchTerm") String searchTerm);

	@Query("select count(i) from items i where i.itemGenericName.genericName like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	public Integer getCountOfItemsByItemGenericName(@Param("searchTerm") String searchTerm);

	@Query("select count(i) from items i order by i.itemCode")
	public Integer getAllCountOfItems();
	
}
