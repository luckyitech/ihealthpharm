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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.reports.model.ReportsMappingModel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PurchaseDetailsByItemNameExcel extends ReportsExcelUtility {

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
		
		
		Map<String, List<Map<String, Object>>> purchaseDetailsMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("ITEM_NM")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(purchaseDetailsMap)) { 
			
			for(String itemName :purchaseDetailsMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> purchaseDetailsList = purchaseDetailsMap.get(itemName);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,purchaseDetailsList, itemName, currentRow); 
			}
			
		}
		
		
		writeToFile(workbook, responseFile);
		 
	}

	private void createSupplierTable(XSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> purchaseDetailsList, String itemName, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;
				
		// populate Date
		if (!ObjectUtils.isEmpty(purchaseDetailsList)) {
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(1);
			cell.setCellValue("SUPPLIER NAME");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(2);
			cell.setCellValue("INVOICE DATE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(3);
			cell.setCellValue("INVOICE NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("BATCH NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(5);
			cell.setCellValue("QTY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("INVOICE AMOUNT");
			cell.setCellStyle(headerStyle);	
			
			for (Map<String, Object> rowData : purchaseDetailsList) {
				
				Row displayRow = sheet.createRow(headRow);
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				headCell.setCellValue("Product Name  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(purchaseDetailsList.indexOf(rowData) + 1);
				sheet.autoSizeColumn(0);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("SP_NAME") ? rowData.get("SP_NAME") : "";
				sheet.autoSizeColumn(1);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("INVOICE_DT") ? rowData.get("INVOICE_DT") : "";
				sheet.autoSizeColumn(2);
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("INVOICE_NO") ? rowData.get("INVOICE_NO ") : "";
				sheet.autoSizeColumn(3);
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				sheet.autoSizeColumn(4);
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("QUANTITY") ? rowData.get("QUANTITY") : "";
				sheet.autoSizeColumn(5);
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("INVOICE_AMOUNT") ? rowData.get("INVOICE_AMOUNT") : "";
				sheet.autoSizeColumn(6);
				cell = dataRow.createCell(6);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
			}
		}

	}

}
