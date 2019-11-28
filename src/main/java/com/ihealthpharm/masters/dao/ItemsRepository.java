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

@Repository
public interface ItemsRepository extends JpaRepository<ItemsModel, Serializable> {

	public List<ItemsModel> findByActiveS(String s);

	List<ItemsModel> findAllByOrderByLastUpdateTimestampDesc();

	@Query("select i from items i where i.itemName like :searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByItemNameSearch(@Param("searchTerm") String searchTerm);

	@Query("select i from items i where i.medicalOrNonMedical =:medicalOrNonMedical and i.itemName like %:searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByMedicalAndItemName(@Param("medicalOrNonMedical") String medicalOrNonMedical,@Param("searchTerm") String searchTerm);

	@Query("select i from items i where i.medicalOrNonMedical =:medicalOrNonMedical and i.itemDescription like %:searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByMedicalAndItemDesc(@Param("medicalOrNonMedical")String medicalOrNonMedical,@Param("searchTerm")String searchTerm);

	@Query("select i from items i where i.itemDescription like %:searchTerm% order by i.creationTimeStamp desc")
	List<ItemsModel> findAllByItemDescription(@Param("searchTerm") String searchTerm);

	@Query("select i from items i inner join com.ihealthpharm.masters.model.ItemGenericNamesModel ig on i.itemGenericName.itemGenericNameId=ig.itemGenericNameId "
			+ "where ig.genericName like %:searchTerm%")
	List<ItemsModel> findByItemGenericName(@Param("searchTerm") String searchTerm);

	List<ItemsModel> findByItemGroup(ItemGroupModel groupCode);

	public List<ItemsModel> findByItemCode(String searchTerm);

	@Query("select i from items i inner join com.ihealthpharm.masters.model.ItemGenericNamesModel ig on "
			+ "i.itemGenericName.itemGenericNameId=ig.itemGenericNameId "
			+ "where i.itemDescription like %:searchTerm% OR  i.itemCode like %:searchTerm% OR  i.itemName like %:searchTerm% or ig.genericName like %:searchTerm%")
	public List<ItemsModel> findByItemCodeOrItemNameOrItemDescription(@Param("searchTerm") String searchTerm);

	public List<ItemsModel> findFirst100ByOrderByItemCode();

	public List<ItemsModel> findAll(Specification<ItemsModel> specification);

	public List<ItemsModel> findByItemGenericNameContains(ItemGenericNamesModel genericRes);


	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i")
	public List<ItemDTO> findItemsByLimit(Pageable pageable);

	
	/*i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.drugDose,*/
	
	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemName like :searchTerm%")
	List<ItemDTO> getAllItemsDataByItemName(@Param("searchTerm")String searchTerm, Pageable pageable);

	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemCode like :searchTerm%")
	List<ItemDTO> getAllItemsDataByItemCode(@Param("searchTerm")String searchTerm, Pageable pageable);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemDescription like %:searchTerm%")
	List<ItemDTO> getAllItemsDataByItemDescription(@Param("searchTerm")String searchTerm, Pageable pageable);
	
	@Query("select new com.ihealthpharm.masters.dto.ItemDTO(i.itemId,i.medicalOrNonMedical,i.itemCode,i.itemName,i.itemDescription,i.drugDose) from items i  "
			+ "where  i.itemGenericName.genericName like %:searchTerm%")
	List<ItemDTO> getAllItemsDataByItemGenericName(@Param("searchTerm")String searchTerm, Pageable pageable);
	
	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.itemId,i.itemName) from items i  "
			+ "where  i.itemName like :searchTerm%")
	List<AlternativeItemDTO> getAlternativeItemsDataByItemName(@Param("searchTerm") String searchTerm);

	@Query("select count(i) from items i where i.itemName like :searchTerm%")
	public Integer getCountOfItemsByItemName(@Param("searchTerm") String searchTerm);
	
	@Query("select count(i) from items i where i.itemCode like :searchTerm%")
	public Integer getCountOfItemsByItemCode(@Param("searchTerm") String searchTerm);
	
	@Query("select count(i) from items i where i.itemDescription like :searchTerm%")
	public Integer getCountOfItemsByItemDescription(@Param("searchTerm") String searchTerm);
	
	@Query("select count(i) from items i where i.itemGenericName.genericName like :searchTerm%")
	public Integer getCountOfItemsByItemGenericName(@Param("searchTerm") String searchTerm);
	
	@Query("select count(i) from items i order by i.itemCode")
	public Integer getAllCountOfItems();
}
