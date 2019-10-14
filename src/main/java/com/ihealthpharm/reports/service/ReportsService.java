package com.ihealthpharm.reports.service;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

public interface ReportsService {

	public File generateReport(HttpServletResponse response, String inputJson,String reportType) throws Exception;
	

	
}
