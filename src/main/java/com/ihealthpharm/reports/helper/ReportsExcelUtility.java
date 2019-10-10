package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.model.ReportsMappingModel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportsExcelUtility {

	public void generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Report Data");

		if (ObjectUtils.isEmpty(responseList)) {
			Row headerRow = sheet.createRow(0);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("No Data Found");
			writeToFile(workbook, responseFile);
			return;
		}

		String reportHeader = model.getReportHeader();

		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);
		int rowNum = 0;
		int colNum = 0;
		// create Header
		Row headerRow = sheet.createRow(rowNum++);
		for (HeaderDto hearder : headerList) {
			Cell cell = headerRow.createCell(colNum++);
			cell.setCellValue(hearder.getDisplayName());
		}

		// populate Date
		if (!ObjectUtils.isEmpty(responseList)) {
			for (Map<String, Object> rowData : responseList) {
				colNum = 0;
				Row dataRow = sheet.createRow(rowNum++);
				for (HeaderDto hearder : headerList) {
					Object value = rowData.containsKey(hearder.getColumnName()) ? rowData.get(hearder.getColumnName())
							: "";
					Cell cell = dataRow.createCell(colNum++);
					cell.setCellValue(String.valueOf(value));

				}
			}
		}

		writeToFile(workbook, responseFile);

	}

	public void generateErrorReport(File responseFile, String errorMessage) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Report Data");

		Row headerRow = sheet.createRow(1);
		Cell cell = headerRow.createCell(1);
		cell.setCellValue(errorMessage);
		writeToFile(workbook, responseFile);

	}

	private void writeToFile(XSSFWorkbook workbook, File responseFile) {
		try {
			FileOutputStream outputStream = new FileOutputStream(responseFile);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException [{}]", ExceptionUtils.getStackTrace(e));
		} catch (IOException e) {
			log.error("IOException [{}]", ExceptionUtils.getStackTrace(e));
		}

	}

}
