package com.ihealthpharm.reports.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.ihealthpharm.reports.model.ReportsMappingModel;

public interface ExcelReportGenerator {

	void generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile,String inputJson);
	
}
