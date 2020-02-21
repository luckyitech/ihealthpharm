package com.ihealthpharm.masters.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.masters.dto.ScheduleCodeDescDTO;
import com.ihealthpharm.masters.model.ScheduleCodeModel;

@Repository
public interface ScheduleCategoryCodeRepository extends JpaRepository<ScheduleCodeModel, Serializable> {

	@Query("SELECT sc FROM schedule_category_code sc")
	List<ScheduleCodeModel> findAllLatestRecords();
	
	@Query("select new com.ihealthpharm.masters.dto.ScheduleCodeDescDTO(sc.scheduleCategoryCodeId,concat(sc.scheduleCategoryCode, ' : ' ,sc.scheduleCategoryDesc)) from schedule_category_code sc")
	List<ScheduleCodeDescDTO> getAllLatestRecordsWithDesc();
}
