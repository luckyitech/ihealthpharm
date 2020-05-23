package com.ihealthpharm.reports.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.reports.model.ReportsPeriodModel;

@Repository
public interface PeriodsRepository extends JpaRepository<ReportsPeriodModel, Integer>{

	@Query("SELECT p FROM list_of_periods p where p.periodId <:periodId")
	List<ReportsPeriodModel> getListOfThreeMonthsPeriods(@Param("periodId") int periodId);

	
}
