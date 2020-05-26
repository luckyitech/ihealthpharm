package com.ihealthpharm.reports.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ihealthpharm.reports.model.ReportsMappingModel;

@Repository
public interface ReportsMappingRepository extends JpaRepository<ReportsMappingModel, Integer> {

	ReportsMappingModel findByReportCode(@Param("reportCode") String reportCode);

}
