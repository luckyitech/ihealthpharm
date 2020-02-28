package com.ihealthpharm.reports.helper;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component
public class PettyCashExpenditureExcel extends ReportsExcelUtility {

	
	public void generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile) {

		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		SXSSFSheet sheet = workbook.createSheet("Report Data");
		FormulaEvaluator formulaEval = workbook.getCreationHelper().createFormulaEvaluator();
		
		if (ObjectUtils.isEmpty(responseList)) {
			Row headerRow = sheet.createRow(0);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("No Data Found");
			writeToFile(workbook, responseFile);
			return;
		}

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
			
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)11);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBold(true);
		font.setColor(IndexedColors.DARK_BLUE.getIndex()); 
		
		CellStyle headerStyle = workbook.createCellStyle();
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
		
		
		Map<String, List<Map<String, Object>>> pettyExpMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String.valueOf(map.get("FROM_DATE")))));					
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(pettyExpMap)) { 
			
			for(String pettyExpSummary :pettyExpMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> pettyCashExpList =pettyExpMap.get(pettyExpSummary);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,pettyCashExpList, pettyExpSummary, currentRow); 
			}
			generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}
		
		writeToFile(workbook, responseFile);	 
	}

	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		int currentRow = sheet.getLastRowNum();
		
		double totalAmount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		double totalBalance=Double.parseDouble(String.valueOf(responseList.get(0).get("BALANCE")))-totalAmount; 
		
		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		
		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);
	
		cell.setCellValue("");
		cell1.setCellValue("");
		
		cell = dataRow.createCell(4);
		cell1=dataRow1.createCell(4);
		
		cell.setCellValue("Total Amount : ");
		cell1.setCellValue("Balance : ");
		
		cell = dataRow.createCell(5);
		cell1=dataRow1.createCell(5);
		
		cell.setCellValue(totalBalance);
		cell1.setCellValue(totalAmount);
	
	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>>  pettyExpList, String pettyCashExpSummary, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(pettyExpList)) {
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.No");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(1);
			cell.setCellValue("DATE");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(2);
			cell.setCellValue("DESCRIPTION");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(3);
			cell.setCellValue("AMOUNT");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(4);
			cell.setCellValue("BALANCE");
			cell.setCellStyle(headerStyle);	
			
			
				
//				Row displayRow = sheet.createRow(headRow);
//				Cell headCell = displayRow.createCell(0);
//				Object value = rowData.containsKey("BALANCE") ? rowData.get(0).get("BALANCE") : "";
//				headCell.setCellValue("Product Name  :   ");
//				headCell=displayRow.createCell(1);
//				headCell.setCellValue(String.valueOf(value));
			
				for (Map<String, Object> rowData : pettyExpList) {
			
				Row dataRow = sheet.createRow(rowNum++);
				Object value =  String.valueOf(pettyExpList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("FROM_DATE") ? rowData.get("FROM_DATE") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
							
				
				value = rowData.containsKey("DESCRIPTION") ? rowData.get("DESCRIPTION") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("AMOUNT") ? rowData.get("AMOUNT") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BALANCE") ? rowData.get("BALANCE") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
			}
				
				int currentRow = sheet.getLastRowNum();
				
				double totalAmount = pettyExpList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
				
				Row dataRow1 = sheet.createRow(currentRow+2);
				
				Cell cell1 = dataRow1.createCell(0);
				
				cell1.setCellValue("");
				
				cell1= dataRow1.createCell(4);
				
				cell1.setCellValue("Total Amount : ");
				
				cell1 = dataRow1.createCell(5);
					
				cell1.setCellValue(totalAmount);
				
		}

	}
}

