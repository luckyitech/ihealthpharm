package com.ihealthpharm.reports.helper;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component
public class CustomerMembershipExcel extends ReportsExcelUtility{


	public void generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile,String inputJson) {

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
		
		
		Map<String, List<Map<String, Object>>> salesProductMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("CREDIT_NUMBER")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(salesProductMap)) { 
			
			for(String productSummary :salesProductMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> supplierList =salesProductMap.get(productSummary);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,supplierList, productSummary, currentRow); 
			}
			//generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}
		
		writeToFile(workbook, responseFile);	 
	}

	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		int currentRow = sheet.getLastRowNum();
		
		double totalAmount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT_TO_BE_RECEIVED")?String.valueOf(mapper.get("AMOUNT_TO_BE_RECEIVED")):"0")).sum(); 
		
		Row dataRow = sheet.createRow(currentRow+2);
		
		Cell cell = dataRow.createCell(0);
		
		cell.setCellValue("");
		
		cell = dataRow.createCell(12);
		
		cell.setCellValue("Outstanding Amt : ");
		
		cell = dataRow.createCell(13);
		
		cell.setCellValue(totalAmount);
		
	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>>  productList, String productSummary, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(productList)) {
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.No");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(1);
			cell.setCellValue("ACCOUNT NO");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(2);
			cell.setCellValue("RECEIPT NO");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(3);
			cell.setCellValue("RECEIPT DATE");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(4);
			cell.setCellValue("MASTER");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(5);
			cell.setCellValue("FAMILY");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(6);
			cell.setCellValue("AMT REC");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("AMT TO BE REC");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("PAYMENT STATUS");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(9);
			cell.setCellValue("SOURCE TYPE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(10);
			cell.setCellValue("CREDIT DAYS%");
			cell.setCellStyle(headerStyle);	
			

			cell = headerRow.createCell(11);
			cell.setCellValue("CREDIT LIMIT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(12);
			cell.setCellValue("CREDIT DAYS LEFT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(13);
			cell.setCellValue("CREDIT LIMIT LEFT");
			cell.setCellStyle(headerStyle);	
			
			Row displayRow = sheet.createRow(headRow++);
			
			for (Map<String, Object> rowData : productList) {
				
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("CREDIT_NUMBER") ? rowData.get("CREDIT_NUMBER") : "";
				headCell.setCellValue("Account No  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(productList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREDIT_NUMBER") ? rowData.get("CREDIT_NUMBER") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("RECEIPT_NO") ? rowData.get("RECEIPT_NO") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("RECEIPT_DATE") ? rowData.get("RECEIPT_DATE") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
							
				
				value = rowData.containsKey("MASTER") ? rowData.get("MASTER") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("FAMILY") ? rowData.get("FAMILY") : "";
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("AMOUNT_RECEIVED") ? rowData.get("AMOUNT_RECEIVED") : "";
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("AMOUNT_TO_BE_RECEIVED") ? rowData.get("AMOUNT_TO_BE_RECEIVED") : "";
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SOURCE_TYPE") ? rowData.get("SOURCE_TYPE") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREDIT_DAYS") ? rowData.get("CREDIT_DAYS") : "";
				cell = dataRow.createCell(10);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREDIT_LIMIT") ? rowData.get("CREDIT_LIMIT") : "";
				cell = dataRow.createCell(11);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREDIT_DAYS_LEFT") ? rowData.get("CREDIT_DAYS_LEFT") : "";
				cell = dataRow.createCell(12);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREDIT_LIMIT_LEFT") ? rowData.get("CREDIT_LIMIT_LEFT") : "";
				cell = dataRow.createCell(13);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
			}
				
				int currentRow = sheet.getLastRowNum();
				
				double totalAmount = productList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT_TO_BE_RECEIVED")?String.valueOf(mapper.get("AMOUNT_TO_BE_RECEIVED")):"0")).sum(); 
				
				Row dataRow1 = sheet.createRow(currentRow+2);
				
				Cell cell1 = dataRow1.createCell(0);
				
				cell1.setCellValue("");
				
				cell1= dataRow1.createCell(12);
				
				cell1.setCellValue("Outstanding Amount : ");
				
				cell1 = dataRow1.createCell(13);
				
				cell1.setCellValue(totalAmount);
				
		}

	}
}
