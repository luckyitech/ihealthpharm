package com.ihealthpharm.checklist.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.checklist.model.CheckListModel;

@Repository
public interface CheckListRepository extends JpaRepository<CheckListModel, Integer>{

/*	@Query("select c from checklist c where c.targetDate=:targetDate")
	List<CheckListModel> findAllCheckListRepo(@Param("targetDate")LocalDate targetDate);*/
	
	@Query("select c from checklist c")
	List<CheckListModel> findCheckListRepo();
		
	@Query("select count(c.checkListId) from checklist c where c.status='pending'")
	Integer countPendingStatusRepo();
	
	@Query("SELECT new com.ihealthpharm.checklist.model.CheckListModel(c.title, c.assignedDate) FROM checklist c WHERE c.title IS NOT NULL GROUP BY c.title")
	List<CheckListModel> onlyTitle();
	
	@Query("SELECT c FROM checklist c WHERE c.title =:title")
	List<CheckListModel> findFilteredCheckList(@Param("title") String title);
}
