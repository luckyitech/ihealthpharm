package com.ihealthpharm.reports.service;

import java.util.List;

import com.ihealthpharm.reports.model.ReportsPeriodModel;

public interface PeriodsService {

	List<ReportsPeriodModel> getAllPeriods();

	List<ReportsPeriodModel> getThreeMonthsPeriod();

}
