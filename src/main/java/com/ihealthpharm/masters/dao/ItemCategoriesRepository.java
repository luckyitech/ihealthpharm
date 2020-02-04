package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
