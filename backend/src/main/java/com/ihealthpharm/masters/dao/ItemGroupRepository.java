package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.model.ItemGroupModel;

@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroupModel, Serializable> {

	List<ItemGroupModel> findByActiveS(String s);
	
	@Query("SELECT g FROM items_group g order by g.lastUpdateTimestamp  desc")
	List<ItemGroupModel> findAllLastUpdateTimestampRecords();
	
	@Query("select i from items_group i where i.medicalOrNonMedical = :medicalOrNonMedical and i.groupName like :searchTerm% and i.activeS='Y' order by i.lastUpdateTimestamp desc")
	List<ItemGroupModel> findAllBySearchCriteria(@Param("medicalOrNonMedical") String medicalOrNonMedical,@Param("searchTerm") String searchTerm);
	
	ItemGroupModel findByGroupName(String searchTerm);

	ItemGroupModel findByGroupNameContaining(String searchTerm);
}
