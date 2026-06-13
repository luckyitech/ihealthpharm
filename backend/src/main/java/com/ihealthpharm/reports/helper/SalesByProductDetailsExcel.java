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
public class SalesByProductDetailsExcel extends ReportsExcelUtility {

	public void generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile,String inputJson) {

		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		SXSSFSheet sheet = workbook.createSheet("Report Data");
		
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
		
		
		Map<String, List<Map<String, Object>>> salesByProductDetails = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("CUSTOMER_NM")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(salesByProductDetails)) { 
			
			for(String customerName :salesByProductDetails.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> salesProductDetails =salesByProductDetails.get(customerName);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,salesProductDetails, customerName, currentRow); 
			}
			
			
		}
		
		
		writeToFile(workbook, responseFile);
		 
	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>>  salesByProductList, String customerName, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(salesByProductList)) {
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.No");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(1);
			cell.setCellValue("CUSTOMER NAME");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(2);
			cell.setCellValue("BILL NO");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(3);
			cell.setCellValue("DATE");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(4);
			cell.setCellValue("DOCTOR");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(5);
			cell.setCellValue("PRODUCT NAME");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(6);
			cell.setCellValue("MFR NAME");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("BATCH");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("EXPIRY");
			cell.setCellStyle(headerStyle);	
			

			cell = headerRow.createCell(9);
			cell.setCellValue("QTY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(10);
			cell.setCellValue("UNIT PRICE");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(11);
			cell.setCellValue("DISC%");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(12);
			cell.setCellValue("MARGIN%");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(13);
			cell.setCellValue("PNL AMT");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(14);
			cell.setCellValue("TOTAL AMT");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(15);
			cell.setCellValue("CREATED BY");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(16);
			cell.setCellValue("MODIFIED BY");
			cell.setCellStyle(headerStyle);
			
			
			Row displayRow = sheet.createRow(headRow++);
			for (Map<String, Object> rowData : salesByProductList) {
				
				
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("CUSTOMER_NM") ? rowData.get("CUSTOMER_NM") : "";
				headCell.setCellValue("Customer Name  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(salesByProductList.indexOf(rowData) + 1);
				//sheet.autoSizeColumn(0);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CUSTOMER_NM") ? rowData.get("CUSTOMER_NM") : "";
				//sheet.autoSizeColumn(1);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
							
				
				value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
				//sheet.autoSizeColumn(1);
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
							
				
				value = rowData.containsKey("BILL_DATE") ? rowData.get("BILL_DATE") : "";
				//sheet.autoSizeColumn(2);
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("FIRST_NM") ? rowData.get("FIRST_NM") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				//sheet.autoSizeColumn(4);
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("MFR_NAME") ? rowData.get("MFR_NAME") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(6);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(7);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				//sheet.autoSizeColumn(7);
				cell = dataRow.createCell(8);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				

				value = rowData.containsKey("SALE_QTY") ? rowData.get("SALE_QTY") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("UNIT_SALE_PRICE") ? rowData.get("UNIT_SALE_PRICE") : "";
				//sheet.autoSizeColumn(9);
				cell = dataRow.createCell(10);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("DISC") ? rowData.get("DISC") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(11);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("MARGIN") ? rowData.get("MARGIN") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(12);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PNL_AMT") ? rowData.get("PNL_AMT") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(13);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("TOTAL_AMOUNT") ? rowData.get("TOTAL_AMOUNT") : "";
				//sheet.autoSizeColumn(11);
				cell = dataRow.createCell(14);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(15);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("MODIFIED_BY") ? rowData.get("MODIFIED_BY") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(16);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
			}
		}

	}
}
