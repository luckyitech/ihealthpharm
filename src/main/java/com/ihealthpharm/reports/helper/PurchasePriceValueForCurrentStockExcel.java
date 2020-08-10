package com.ihealthpharm.reports.helper;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

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
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component
public class PurchasePriceValueForCurrentStockExcel extends ReportsExcelUtility {
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
				
		setHeader(workbook,sheet,model);
				
		int currentRow = sheet.getLastRowNum();
		Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
		createSupplierTable(sheet, responseFile, borderStyle, headerStyle,responseList,currentRow,dataMap); 
		generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		writeToFile(workbook, responseFile);
		 
	}
	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
		
		List<Map<String, Object>> responseList) {
		int currentRow = sheet.getLastRowNum();
		
		double totalPurchaseValue = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("PURCHASE_VALUE")?String.valueOf(mapper.get("PURCHASE_VALUE")):"0")).sum(); 
	
		Row dataRow = sheet.createRow(currentRow+2);
		
		Cell cell = dataRow.createCell(0);
	
		cell.setCellValue("");
		
		cell = dataRow.createCell(9);
		
		cell.setCellValue("Total Purchase Value : ");
		
		cell = dataRow.createCell(10);
		
		cell.setCellValue(totalPurchaseValue);
		
	}
	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> purchaseMarginList,int rowNum,Map<String,Object> dataMap) {

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
			cell.setCellValue("BATCH NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(3);
			cell.setCellValue("EXPIRY DT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("QTY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(5);
			cell.setCellValue("PURCHASE DISCOUNT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("UNIT PURCHASE RATE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("TAX CATEGORY");
			cell.setCellStyle(headerStyle);	
				
			cell = headerRow.createCell(8);
			cell.setCellValue("VAT AMT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(9);
			cell.setCellValue("PURCHASE VALUE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(10);
			cell.setCellValue("UPDATED DATE");
			cell.setCellStyle(headerStyle);	
			
			DecimalFormat df=new DecimalFormat("0.00");
			
		
			
			Row displayRow = sheet.createRow(headRow);
//			Cell headCell = displayRow.createCell(0);
//			Object value = dataMap.containsKey("FROM_UPDATED_DATE") ? dataMap.get("FROM_UPDATED_DATE") : "";
//			headCell.setCellValue("From Date  :   ");
//			headCell=displayRow.createCell(1);
//			headCell.setCellValue(String.valueOf(value));
			
			Cell headCell = displayRow.createCell(0);
			Object value = dataMap.containsKey("TO_UPDATED_DATE") ? dataMap.get("TO_UPDATED_DATE") : "";
			headCell.setCellValue("As Of Date  :   ");
			headCell=displayRow.createCell(1);
			headCell.setCellValue(String.valueOf(value));
			
			headCell = displayRow.createCell(2);
			double totalPurchaseValue = purchaseMarginList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("PURCHASE_VALUE")?String.valueOf(mapper.get("PURCHASE_VALUE")):"0")).sum(); 
			headCell.setCellValue("Total Pur. Value  :   ");
			headCell=displayRow.createCell(3);
			headCell.setCellValue(totalPurchaseValue);
			
			double threshHoldBreakNew=0.00;
			if(dataMap.containsKey("THRESHHOLD_VALUE")) {
				
				double threshHoldBreak=Double.parseDouble(String.valueOf(dataMap.get("THRESHHOLD_VALUE")));
				if(totalPurchaseValue>threshHoldBreak) {
					threshHoldBreakNew=totalPurchaseValue-threshHoldBreak;
				}else {
					threshHoldBreakNew=threshHoldBreak-totalPurchaseValue;
				}
			}
			
			
			headCell = displayRow.createCell(6);
			String threshholdValue=dataMap.containsKey("THRESHHOLD_VALUE")?df.format(dataMap.get("THRESHHOLD_VALUE")):"";
			headCell.setCellValue("Threshhold Value  :   ");
			headCell=displayRow.createCell(7);
			headCell.setCellValue(String.valueOf(threshholdValue));
			
			headCell = displayRow.createCell(8);
			String thBreak=df.format(threshHoldBreakNew);
			headCell.setCellValue("TH Break  :   ");
			headCell=displayRow.createCell(9);
			headCell.setCellValue(String.valueOf(thBreak));
			
			for (Map<String, Object> rowData : purchaseMarginList) {
										
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(purchaseMarginList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
					
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("QUANTITY") ? rowData.get("QUANTITY") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PURCHASE_DISCOUNT_PERCENTAGE") ? rowData.get("PURCHASE_DISCOUNT_PERCENTAGE") : "";
				cell = dataRow.createCell(5);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("UNIT_PURCHASE_RATE") ? rowData.get("UNIT_PURCHASE_RATE") : "";
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CATEGORY_CODE") ? rowData.get("CATEGORY_CODE") : "";
				cell = dataRow.createCell(7);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("PURCHASE_VALUE") ? rowData.get("PURCHASE_VALUE") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("LAST_UPDATE_TS") ? rowData.get("LAST_UPDATE_TS") : "";
				cell = dataRow.createCell(10);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
			}
		}

	}

}
