package com.ihealthpharm.reports.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.ihealthpharm.reports.model.ReportsMappingModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public interface ReportGenerator {

	Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile);
	
	public void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList)
			throws DocumentException;
	
	public void addMessage(Document document, String message) throws DocumentException;
}
