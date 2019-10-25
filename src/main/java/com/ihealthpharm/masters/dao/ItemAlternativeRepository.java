package com.ihealthpharm.masters.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.ItemAlternativeModel;
import com.ihealthpharm.masters.model.ItemsModel;

public interface ItemAlternativeRepository extends JpaRepository<ItemAlternativeModel, Integer> {

	List<ItemAlternativeModel> findByItemId(ItemsModel item);

	@Transactional
	@Modifying
	@Query("update item_alternative set alternativeItemId=:itemAlternativesId where itemId=:itemModel ")
	void   updateBasedOnItem(@Param("itemAlternativesId")ItemsModel itemAlternativesId,@Param("itemModel") ItemsModel itemModel);

}
