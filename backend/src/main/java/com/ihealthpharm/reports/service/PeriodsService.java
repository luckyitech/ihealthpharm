package com.ihealthpharm.reports.service;

import java.util.List;

import com.ihealthpharm.reports.model.ReportsPeriodModel;
import com.ihealthpharm.sales.model.SalesModel;

public interface PeriodsService {

	List<ReportsPeriodModel> getAllPeriods();

	List<ReportsPeriodModel> getThreeMonthsPeriod();
	
	List<String> getCustomerNames();
	
	List<String> getBills();

}
