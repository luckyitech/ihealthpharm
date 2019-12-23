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
	
    List<ItemFormModel> findAllByOrderByLastUpdateTimestampDesc();
	
	@Query("select i from items_forms i where i.medicalOrNonMedical = :medicalOrNonMedical and i.form like :searchTerm%")
	List<ItemFormModel> findAllBySearchCriteria(@Param("medicalOrNonMedical") String medicalOrNonMedical,@Param("searchTerm") String searchTerm);
	
}
