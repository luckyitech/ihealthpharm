package com.ihealthpharm.notifications.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.notifications.model.CheckListModel;

@Repository
public interface CheckListRepository extends JpaRepository<CheckListModel, Integer>{

/*	@Query("select c from checklist c where c.targetDate=:targetDate")
	List<CheckListModel> findAllCheckListRepo(@Param("targetDate")LocalDate targetDate);*/
	
	@Query("select c from checklist c")
	List<CheckListModel> findCheckListRepo();
		
	@Query("select count(c.checkListId) from checklist c where c.status='pending'")
	Integer countPendingStatusRepo();
}
