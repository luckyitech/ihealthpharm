package com.ihealthpharm.reports.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.commons.TimeDurationUtility;
import com.ihealthpharm.reports.dao.ReportsMappingRepository;
import com.ihealthpharm.reports.helper.ReportsCommonUtility;
import com.ihealthpharm.reports.helper.ReportsExcelUtility;
import com.ihealthpharm.reports.helper.ReportsPDFUtility;
import com.ihealthpharm.reports.model.ReportsMappingModel;
import com.ihealthpharm.reports.service.ReportsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	private ReportsMappingRepository reportsMappingRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ReportsPDFUtility reportsPDFUtility;

	@Autowired
	private ReportsExcelUtility reportsExcelUtility;

	@Autowired
	private ReportsCommonUtility reportsCommonUtility;
	
	@SuppressWarnings("unchecked")
	@Override
	public File generateReport(HttpServletResponse response, String inputJson,String reportType) throws Exception {
		File responseFile = null;
		Date start = new Date();

		if(ObjectUtils.isEmpty(inputJson)) 
			throw new Exception("Input Parameters can't be blank ");

		Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);

		
		ReportsMappingModel model = reportsMappingRepository.findByReportCode(String.valueOf(dataMap.get("ReportCode")));
		
		List<Map<String,Object>> responseList =getReportData(model,dataMap);

		String extension =".xls";
		if(StringUtils.equalsIgnoreCase("PDF", reportType))
			extension =".pdf";
		
		String fileName = model.getReportName() + extension;
		responseFile = new File(System.getProperty("java.io.tmpdir") + fileName);
		if (!responseFile.exists())
			responseFile.createNewFile();
		
		
		if (StringUtils.equalsIgnoreCase("PDF", reportType))
			reportsPDFUtility.generateReport(responseList, model, responseFile);
		else
			reportsExcelUtility.generateReport(responseList, model, responseFile);

		log.info("Service Duration [{}]", TimeDurationUtility.duration(start));
		return responseFile;

	}

	@Override
	public List<Map<String, Object>> getReportData(String inputJson) throws Exception {

		if(ObjectUtils.isEmpty(inputJson)) 
			throw new Exception("Input Parameters can't be blank ");

		Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);

		
		ReportsMappingModel model = reportsMappingRepository.findByReportCode(String.valueOf(dataMap.get("ReportCode")));

		List<Map<String,Object>> responseList =getReportData(model,dataMap);
		
		return responseList;
		
		
	}
	
	private List<Map<String, Object>> getReportData(ReportsMappingModel model, Map<String, Object> dataMap) throws Exception {
						
		if(!dataMap.containsKey("ReportCode"))
			throw new Exception("Report Code can't be blank ");


		if(ObjectUtils.isEmpty(model))
			throw new Exception("Reports Mapping Not foung for the given Report code "+ dataMap.get("ReportCode"));
		
		
		String SQL = reportsCommonUtility.prepareSQL(model);
		String Whereclause = reportsCommonUtility.prepareWhereClause(model,dataMap);
		
		log.info("SQL [{}]",SQL);
		log.info("WhereClause[{}]",Whereclause);
		
		List<Map<String,Object>> responseList = jdbcTemplate.queryForList(SQL,Whereclause);	
		
		return responseList;
	}



}
