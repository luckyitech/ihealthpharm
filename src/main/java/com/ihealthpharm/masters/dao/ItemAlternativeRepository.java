package com.ihealthpharm.masters.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.dto.AlternativeItemDTO;
import com.ihealthpharm.masters.model.ItemAlternativeModel;
import com.ihealthpharm.masters.model.ItemsModel;

public interface ItemAlternativeRepository extends JpaRepository<ItemAlternativeModel, Integer> {

	@Query("select new com.ihealthpharm.masters.dto.AlternativeItemDTO(i.alternativeItemId.itemId,i.alternativeItemId.itemName) from item_alternative i where i.itemId.itemId=:itemId")
	List<AlternativeItemDTO> findByItemsId(@Param("itemId") Integer itemId);

	@Query("select i.itemId  from item_alternative i where i.itemId=:item")
	List<ItemsModel> findByItemId(@Param("item") ItemsModel item);
	
	@Transactional
	@Modifying
	@Query("update item_alternative set alternativeItemId=:itemAlternativesId where itemId=:itemModel ")
	void   updateBasedOnItem(@Param("itemAlternativesId")ItemsModel itemAlternativesId,@Param("itemModel") ItemsModel itemModel);

	@Transactional
	@Modifying
	@Query("delete from item_alternative where itemId.itemId=:item")
	void deleteByItemId(@Param("item") Integer item);

}
