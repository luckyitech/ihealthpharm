package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemGenericNamesModel;
import com.ihealthpharm.masters.model.ItemGroupModel;

@Repository
public interface ItemGenericNameRepository extends JpaRepository<ItemGenericNamesModel, Integer>{

	List<ItemGenericNamesModel> findByActiveS(String s);
	
	@Query("SELECT gn FROM items_generic_names gn order by gn.lastUpdateTimestamp desc")
	List<ItemGenericNamesModel> findAllByLastestRecords();
	
	@Query("select i from items_generic_names i where i.medicalOrNonMedical = :medicalOrNonMedical and i.genericCode like :searchTerm% and i.itemGroupId =:itemGroupModel and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<ItemGenericNamesModel> findAllBySearchCriteria(@Param("medicalOrNonMedical") String medicalOrNonMedical,@Param("searchTerm") String searchTerm,@Param("itemGroupModel") ItemGroupModel itemGroupModel );

	ItemGenericNamesModel findByGenericName(String searchTerm);

	ItemGenericNamesModel findByGenericNameContains(String searchTerm);
	
}
