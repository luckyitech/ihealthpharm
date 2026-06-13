package com.ihealthpharm.reports.helper;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
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
public class PurchaseMarginComparisonExcel extends ReportsExcelUtility{

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
		
		
		Map<String, List<Map<String, Object>>> purchaseMarginMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("INVOICE_NO")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(purchaseMarginMap)) { 
			
			for(String itemName :purchaseMarginMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> purchaseMarginList = purchaseMarginMap.get(itemName);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,purchaseMarginList, itemName, currentRow); 
			}
			
		}
		
		
		writeToFile(workbook, responseFile);
		 
	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> purchaseMarginList, String invoiceNo, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;
				
		// populate Date
		if (!ObjectUtils.isEmpty(purchaseMarginList)) {
			
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(1);
			cell.setCellValue("ITEM NAME");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(2);
			cell.setCellValue("SUPPLIER NAME");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(3);
			cell.setCellValue("BATCH");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("QTY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(5);
			cell.setCellValue("BONUS");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("PURC PRICE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("SALE PRICE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("MRP");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(9);
			cell.setCellValue("P DISC%");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(10);
			cell.setCellValue("S DISC%");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(11);
			cell.setCellValue("NET AMT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(12);
			cell.setCellValue("MARGIN AMT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(13);
			cell.setCellValue("MARGIN%");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(14);
			cell.setCellValue("CREATED BY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(15);
			cell.setCellValue("MODIFIED BY");
			cell.setCellStyle(headerStyle);	
			
			for (Map<String, Object> rowData : purchaseMarginList) {
				
				Row displayRow = sheet.createRow(headRow);
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("INVOICE_NO") ? rowData.get("INVOICE_NO") : "";
				headCell.setCellValue("Invoice No  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(purchaseMarginList.indexOf(rowData) + 1);
				//sheet.autoSizeColumn(0);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				//sheet.autoSizeColumn(1);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SP_NAME") ? rowData.get("SP_NAME") : "";
				//sheet.autoSizeColumn(1);
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				//sheet.autoSizeColumn(2);
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("QUANTITY_APPROVED") ? rowData.get("QUANTITY_APPROVED") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(4);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BONUS") ? rowData.get("BONUS") : "";
				//sheet.autoSizeColumn(4);
				cell = dataRow.createCell(5);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("UNIT_RATE") ? rowData.get("UNIT_RATE") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("UNIT_SALE_RATE") ? rowData.get("UNIT_SALE_RATE") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("MRP") ? rowData.get("MRP") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(8);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else{
					 cell.setCellValue(String.valueOf(value));
				}
				
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("DISCOUNT_PERCENTAGE") ? rowData.get("DISCOUNT_PERCENTAGE") : "";
				//sheet.autoSizeColumn(7);
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SALE_DISCOUNT_PERCENTAGE") ? rowData.get("SALE_DISCOUNT_PERCENTAGE") : "";
				//sheet.autoSizeColumn(7);
				cell = dataRow.createCell(10);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("NET_AMOUNT") ? rowData.get("NET_AMOUNT") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(11);
				try {
					if(NumberUtils.isNumber(String.valueOf(value))) {
						cell.setCellType(CellType.NUMERIC);		
						cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					}else {	
					   cell.setCellValue(String.valueOf(value));
					}
				} catch (Exception e) {
					//log.error(ExceptionUtils.getStackTrace(e)); 
				}
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("MARGIN_AMT") ? rowData.get("MARGIN_AMT") : "";
				//sheet.autoSizeColumn(9);
				cell = dataRow.createCell(12);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
				   cell.setCellValue(String.valueOf(value));
				}
//				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("MARGIN") ? rowData.get("MARGIN") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(13);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
				   cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("EMP_NM") ? rowData.get("EMP_NM") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(14);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("EMP_MODIFIED") ? rowData.get("EMP_MODIFIED") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(15);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
			}
		}

	}


}
