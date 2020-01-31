package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemFormModel;

@Repository
public interface ItemFormRepository extends JpaRepository<ItemFormModel,  Integer> {

	List<ItemFormModel> findByActiveS(String active);
	
	@Query("SELECT f FROM items_forms f where f.activeS='Y' order by f.lastUpdateTimestamp desc")
    List<ItemFormModel> findAllLastRecordsDesc();
	
	@Query("select i from items_forms i where i.medicalOrNonMedical = :medicalOrNonMedical and i.form like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<ItemFormModel> findAllBySearchCriteria(@Param("medicalOrNonMedical") String medicalOrNonMedical,@Param("searchTerm") String searchTerm);
}
