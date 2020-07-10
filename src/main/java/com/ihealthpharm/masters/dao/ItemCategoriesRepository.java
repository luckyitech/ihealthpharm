package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ihealthpharm.masters.model.ItemCategoryModel;

@Repository
public interface ItemCategoriesRepository extends JpaRepository<ItemCategoryModel, Integer> {

	public List<ItemCategoryModel> findByActiveS(String active);

	@Query("SELECT ic FROM items_categories ic order by ic.lastUpdateTimestamp desc")
	List<ItemCategoryModel> findAllByLastUpdated();

	@Query("select i from items_categories i where i.medicalOrNonMedical = :medicalOrNonMedical and i.categoryName  like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<ItemCategoryModel> findAllBySearchCriteria(@Param("medicalOrNonMedical") String medicalOrNonMedical,@Param("searchTerm") String searchTerm);

	@Query("SELECT ic FROM items_categories ic where ic.activeS='Y' order by ic.lastUpdateTimestamp desc")
	public List<ItemCategoryModel> findAllByLastUpdatedRecords();

	@Transactional
	@Modifying
	@Query("update stock s set s.unitSaleRate=((s.unitPurchaseRate*(1+(:margin/100)))*(1+(:markup/100))), s.entryType='sales price update'"
			+ " where s.unitPurchaseRate is not null and s.item.itemId in "
			+ "(select i.itemId from items i where i.itemCategory.itemCategoryId = :itemCategoryId)")
	public void updateStockWithMargin(@Param("itemCategoryId") Integer itemCategoryId,@Param("margin") Integer margin, @Param("markup") Integer markup);
	
	@Query("select configValue from configuration where configDesc='maxdiscount' and activeS='Y'")
	public Integer getMarkup();
}
