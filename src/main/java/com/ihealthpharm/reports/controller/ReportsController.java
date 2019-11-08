package com.ihealthpharm.reports.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.commons.TimeDurationUtility;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.reports.helper.ReportsExcelUtility;
import com.ihealthpharm.reports.helper.ReportsHelper;
import com.ihealthpharm.reports.helper.ReportsPDFUtility;
import com.ihealthpharm.reports.model.ReportsMappingModel;
import com.ihealthpharm.reports.service.ReportsService;
import com.ihealthpharm.stock.helper.InvoiceStatusHelper;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	private ReportsService resportsService;

	@Autowired
	private ReportsPDFUtility reportsPDFUtility;

	@Autowired
	private ReportsExcelUtility reportsExcelUtility;
	
	@Autowired
	private ReportsHelper reportsHelper;

	@GetMapping("/getReportData")
	public ResponseEntity<BaseDto<List<Map<String, Object>>>> getReportData(HttpServletResponse response, String inputJson) {
		log.info("Reports Search Criteria [{}]", inputJson);
		List<Map<String, Object>> result =null;
		try {
			result = resportsService.getReportData(inputJson);
			return new BaseDto<>(result, reportsHelper.getReportsDataRetrieveResponse(), OK).respond();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return new BaseDto<>(result, reportsHelper.getReportsExceptionResponse(), HttpStatus.EXPECTATION_FAILED).respond();
		}
		
	}
	@GetMapping("/generateReport")
	public void generateReportExcel(HttpServletResponse response, String inputJson) {
		log.info("Reports Search Criteria [{}]", inputJson);
		InputStream inputStream = null;
		File responseFile = null;
		Date start = new Date();
		try {
			responseFile = resportsService.generateReport(response, inputJson, "EXCEL");
			inputStream = FileUtils.openInputStream(responseFile);
			flushStream(response, responseFile, inputStream);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			try {
				responseFile = File.createTempFile("Error", "Report.xls");
				inputStream = FileUtils.openInputStream(responseFile);
				flushStream(response, responseFile, inputStream);
			} catch (IOException e1) {
				log.error(ExceptionUtils.getStackTrace(e1));
			}
			reportsExcelUtility.generateErrorReport(responseFile, ExceptionUtils.getMessage(e));
		} finally {
			// close stream
			if (!ObjectUtils.isEmpty(inputStream)) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(ExceptionUtils.getStackTrace(e));
				}
			}
			// delete temp file
			if (!ObjectUtils.isEmpty(responseFile) && responseFile.exists()) {
				responseFile.delete();
			}
		}
		log.info("Controller Duration [{}]", TimeDurationUtility.duration(start));

	}

	private void flushStream(HttpServletResponse response, File responseFile, InputStream inputStream)
			throws IOException {
		// xls file
		response.addHeader("Content-disposition", "attachment;filename=" + responseFile.getName());
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		// Copy the stream to the response's output stream.
		IOUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();

	}

	@GetMapping("/generateReportPdf")
	public void generateReportPdf(HttpServletResponse response, @RequestParam String inputJson) {
		log.info("Reports Search Criteria [{}]", inputJson);
		InputStream inputStream = null;
		File responseFile = null;
		Date start = new Date();
		try {
			responseFile = resportsService.generateReport(response, inputJson, "PDF");
			inputStream = FileUtils.openInputStream(responseFile);
			flushPdf(response, responseFile, inputStream);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			try {
				responseFile = File.createTempFile("ERROR_", "REPORT.PDF");
				reportsPDFUtility.generateErrorReport(responseFile, ExceptionUtils.getMessage(e));
				inputStream = FileUtils.openInputStream(responseFile);
				flushPdf(response, responseFile, inputStream);
			} catch (IOException e1) {
				log.error(ExceptionUtils.getStackTrace(e1));

			}

		} finally {
			// close Stream
			if (!ObjectUtils.isEmpty(inputStream)) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(ExceptionUtils.getStackTrace(e));
				}
			}

			// delete temp file
			if (!ObjectUtils.isEmpty(responseFile) && responseFile.exists()) {
				responseFile.delete();
			}
		}
		log.info("Controller Duration [{}]", TimeDurationUtility.duration(start));

	}

	private void flushPdf(HttpServletResponse response, File responseFile, InputStream inputStream) throws IOException {
		// pdf file
		response.addHeader("Content-disposition", "attachment;filename=" + responseFile.getName());
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		// Copy the stream to the response's output stream.
		IOUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
	}

	@GetMapping("/getReports")
	public ResponseEntity<List<ReportsMappingModel>> getAllReports(){
		List<ReportsMappingModel> res=resportsService.getReportsDetails();
		return ResponseEntity.ok(res);
		
	}
	
}
