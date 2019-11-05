package com.ihealthpharm.reports.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ihealthpharm.reports.model.ReportsMappingModel;

public interface ReportsService {

	public File generateReport(HttpServletResponse response, String inputJson,String reportType) throws Exception;

	List<Map<String, Object>> getReportData(String inputJson) throws Exception; 
	public List<ReportsMappingModel> getReportsDetails();

	
}
