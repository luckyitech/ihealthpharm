package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.DateUtility;
import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.dto.HeaderFooterContentDetailsDto;
import com.ihealthpharm.reports.dto.HeaderFooterContentDto;
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

		CellStyle wrapStyle = workbook.createCellStyle();
		wrapStyle.setWrapText(true);
		wrapStyle.setVerticalAlignment(VerticalAlignment.TOP);
		wrapStyle.setAlignment(HorizontalAlignment.CENTER);

		
		CellStyle borderStyle = workbook.createCellStyle();
		 // Styling border of cell.  
		borderStyle.setBorderBottom(BorderStyle.THIN);  
		borderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());  
		borderStyle.setBorderRight(BorderStyle.THIN);  
		borderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());  
		borderStyle.setBorderTop(BorderStyle.THIN);  
		borderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());  
		borderStyle.setBorderLeft(BorderStyle.THIN);  
		borderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());  
		
		CellStyle headerStyle = workbook.createCellStyle();
		
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)11);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBold(true);
		font.setColor(IndexedColors.DARK_BLUE.getIndex()); 
		
		headerStyle.setFont(font);
		headerStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setVerticalAlignment(VerticalAlignment.TOP);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setBorderBottom(BorderStyle.THIN);  
		headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());  
		headerStyle.setBorderRight(BorderStyle.THIN);  
		headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());  
		headerStyle.setBorderTop(BorderStyle.THIN);  
		headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());  
		headerStyle.setBorderLeft(BorderStyle.THIN);  
		headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());  
		
		
		String reportHeader = model.getReportHeader();
		String headerContent = model.getHeaderContent();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		int rowNum = 0;
		int colNum = 0;

		if (!ObjectUtils.isEmpty(headerContent)) {
			HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(headerContent,
					HeaderFooterContentDto.class);
			
			String reportHeaderContent = getReportHeader(contentDto);
			int totalMergeRows = getTotalMergeRows(contentDto);
			
			/*
			 * int totalMergeRows = Stream.of(contentDto.getLeftContent().size(),
			 * contentDto.getCenterContent().size(),
			 * contentDto.getRightContent().size()).max(Integer::compareTo).get();
			 */
			int totalMergeColumns = headerList.size();
			
			for (int m = 0; m < totalMergeRows; m++) {
				Row headerRow = sheet.createRow(rowNum++);
				Cell cell = headerRow.createCell(0);
				cell.setCellValue(reportHeaderContent);
				cell.setCellStyle(wrapStyle);
			}
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, totalMergeRows - 1, 0, totalMergeColumns - 1);
			sheet.addMergedRegion(cellRangeAddress);

			
			Row reportDateRow =sheet.createRow(rowNum++);
			Cell cellReportTime = reportDateRow.createCell(0);
			cellReportTime.setCellValue("Report Generated Date - "+DateUtility.getDateString());
			cellReportTime.setCellStyle(wrapStyle);
			
			CellRangeAddress cellRangeReportDate= new CellRangeAddress(rowNum - 1, rowNum - 1, 0, totalMergeColumns - 1);
			sheet.addMergedRegion(cellRangeReportDate);
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue(model.getTitle());
			cell.setCellStyle(wrapStyle);
			sheet.createRow(rowNum++);
			CellRangeAddress cellRangeAddress1 = new CellRangeAddress(rowNum - 2, rowNum - 1, 0, totalMergeColumns - 1);
			sheet.addMergedRegion(cellRangeAddress1);

			
			/*
			 * sheet.createRow(rowNum++); CellRangeAddress cellRangeAddress2 = new
			 * CellRangeAddress(rowNum - 1, rowNum - 1, 0, totalMergeColumns - 1);
			 * sheet.addMergedRegion(cellRangeAddress2);
			 */


			
		}
		
		//int dataStartRow = rowNum;

		// create Header
		Row headerRow = sheet.createRow(rowNum++);
		for (HeaderDto hearder : headerList) {
			Cell cell = headerRow.createCell(colNum);
			cell.setCellValue(hearder.getDisplayName());
			cell.setCellStyle(headerStyle);
			colNum++;
		}
		sheet.createFreezePane(0, rowNum);

		// populate Date
		if (!ObjectUtils.isEmpty(responseList)) {
			for (Map<String, Object> rowData : responseList) {
				colNum = 0;
				Row dataRow = sheet.createRow(rowNum++);
				for (HeaderDto hearder : headerList) {
					Object value = rowData.containsKey(hearder.getColumnName()) ? rowData.get(hearder.getColumnName())
							: "";
					sheet.autoSizeColumn(colNum);
					Cell cell = dataRow.createCell(colNum++);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);


				}
			}
		}

		/*
		 * int dataEndRow = rowNum;
		 * 
		 * CellRangeAddress region = new CellRangeAddress(dataStartRow, dataEndRow-1, 0,
		 * headerList.size() - 1); setRegionBorderWithMedium(region, sheet);
		 */ 
		  writeToFile(workbook, responseFile);
		 
	}
	
	

	private void setRegionBorderWithMedium(CellRangeAddress region,Sheet sheet) {
        RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
    }

	private int getTotalMergeRows(HeaderFooterContentDto contentDto) {

		int totalMergeRows =0;
		if (!ObjectUtils.isEmpty(contentDto)) {
			for (HeaderFooterContentDetailsDto dto : contentDto.getLeftContent()) {
				if (StringUtils.isNotBlank(dto.getText())) {
					totalMergeRows++;
				}

			}

			for (HeaderFooterContentDetailsDto dto : contentDto.getCenterContent()) {
				if (StringUtils.isNotBlank(dto.getText())) {
					totalMergeRows++;
				}
			}

			for (HeaderFooterContentDetailsDto dto : contentDto.getRightContent()) {
				if (StringUtils.isNotBlank(dto.getText())) {
					totalMergeRows++;
				}
			}
		}


		return totalMergeRows;
	}

	private String getReportHeader(HeaderFooterContentDto contentDto) {

		StringBuffer buffer = new StringBuffer();

		if (!ObjectUtils.isEmpty(contentDto)) {
			for (HeaderFooterContentDetailsDto dto : contentDto.getLeftContent()) {
				if (StringUtils.isNotBlank(dto.getText())) {
					buffer.append(dto.getText());
					buffer.append(System.lineSeparator());
				}

			}

			for (HeaderFooterContentDetailsDto dto : contentDto.getCenterContent()) {
				if (StringUtils.isNotBlank(dto.getText())) {
					buffer.append(dto.getText());
					buffer.append(System.lineSeparator());
				}
			}

			for (HeaderFooterContentDetailsDto dto : contentDto.getRightContent()) {
				if (StringUtils.isNotBlank(dto.getText())) {
					buffer.append(dto.getText());
					buffer.append(System.lineSeparator());
				}
			}

		}

		return buffer.toString();
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
