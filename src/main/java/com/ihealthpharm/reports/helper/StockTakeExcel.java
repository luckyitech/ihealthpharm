package com.ihealthpharm.reports.helper;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component

public class StockTakeExcel extends ReportsExcelUtility{
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

		Map<String, List<Map<String, Object>>> salesByPersonMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("ITEM_NM")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(salesByPersonMap)) { 
			
			for(String salePerson :salesByPersonMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> salesByPersonList =salesByPersonMap.get(salePerson);
				createSalesByPersonTable(sheet, responseFile, borderStyle, headerStyle,salesByPersonList, salePerson, currentRow); 
			}
			generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}
		
		writeToFile(workbook, responseFile);	 
		 
	}
	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		int currentRow = sheet.getLastRowNum();
		double totalDiff;
		
		double totalPreviousAmt = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("PREVIOUS_AMT")?String.valueOf(mapper.get("PREVIOUS_AMT")):"0")).sum(); 
		double totalAdjustAmt = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("ADJUSTED_AMT")?String.valueOf(mapper.get("ADJUSTED_AMT")):"0")).sum(); 
		if(totalPreviousAmt>totalAdjustAmt)
			totalDiff  = totalPreviousAmt-totalAdjustAmt;
		else
			totalDiff  = totalAdjustAmt-totalPreviousAmt;
		
		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		Row dataRow2=sheet.createRow(currentRow+4);
		
		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);
		Cell cell2=dataRow2.createCell(0);
		
		cell.setCellValue("");
		cell1.setCellValue("");
		cell2.setCellValue("");
		
		cell = dataRow.createCell(14);
		cell1 = dataRow1.createCell(14);
		cell2 = dataRow2.createCell(14);
		
		cell.setCellValue("Actual Amount : ");
		cell1.setCellValue("Adjusted Amount : ");
		cell2.setCellValue("Variation : ");
	
		cell = dataRow.createCell(15);
		cell1 = dataRow1.createCell(15);
		cell2 = dataRow2.createCell(15);
	
		cell.setCellValue(totalPreviousAmt);
		cell1.setCellValue(totalAdjustAmt);
		cell2.setCellValue(totalDiff);
	
	}
	private void createSalesByPersonTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> salesProfitList,String salePerson,int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;
				
		// populate Date
		if (!ObjectUtils.isEmpty(salesProfitList)) {
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(1);
			cell.setCellValue("ITEM NAME");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(2);
			cell.setCellValue("INVOICE NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(3);
			cell.setCellValue("BATCH NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("EXPIRY DT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(5);
			cell.setCellValue("RACK");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("SHELF");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("ACTUAL STOCK");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("ADJ STOCK");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(9);
			cell.setCellValue("STOCK CHANGE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(10);
			cell.setCellValue("P DISC%");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(11);
			cell.setCellValue("P PRICE");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(12);
			cell.setCellValue("ACTUAL AMT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(13);
			cell.setCellValue("ADJ AMT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(14);
			cell.setCellValue("REMARKS");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(15);
			cell.setCellValue("UPDATED BY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(16);
			cell.setCellValue("UPDATED TS");
			cell.setCellStyle(headerStyle);	
			
			
			
			Row displayRow = sheet.createRow(headRow++);
			for (Map<String, Object> rowData : salesProfitList) {

				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				headCell.setCellValue("Item Name  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(salesProfitList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("INVOICE_NO") ? rowData.get("INVOICE_NO") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("RACK") ? rowData.get("RACK") : "";
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SHELF") ? rowData.get("SHELF") : "";
				cell = dataRow.createCell(6);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("PHYSICAL_STOCK") ? rowData.get("PHYSICAL_STOCK") : "";
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ADJUSTEMENT_STOCK") ? rowData.get("ADJUSTEMENT_STOCK") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("STOCK_VALUE_CHANGE") ? rowData.get("STOCK_VALUE_CHANGE") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("P_DISC") ? rowData.get("P_DISC") : "";
				cell = dataRow.createCell(10);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("P_PRICE") ? rowData.get("P_PRICE") : "";
				cell = dataRow.createCell(11);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PREVIOUS_AMT") ? rowData.get("PREVIOUS_AMT") : "";
				cell = dataRow.createCell(12);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ADJUSTED_AMT") ? rowData.get("ADJUSTED_AMT") : "";
				cell = dataRow.createCell(13);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("REMARKS") ? rowData.get("REMARKS") : "";
				cell = dataRow.createCell(14);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("LAST_UPDATE_TS") ? rowData.get("LAST_UPDATE_TS") : "";
				cell = dataRow.createCell(15);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("LAST_UPDATE_USER") ? rowData.get("LAST_UPDATE_USER") : "";
				cell = dataRow.createCell(16);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				
			}
			int currentRow = sheet.getLastRowNum();
			
			double totalDiff;
			
			double totalPreviousAmt = salesProfitList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("PREVIOUS_AMT")?String.valueOf(mapper.get("PREVIOUS_AMT")):"0")).sum(); 
			double totalAdjustAmt = salesProfitList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("ADJUSTED_AMT")?String.valueOf(mapper.get("ADJUSTED_AMT")):"0")).sum(); 
			if(totalPreviousAmt>totalAdjustAmt)
				totalDiff  = totalPreviousAmt-totalAdjustAmt;
			else
				totalDiff  = totalAdjustAmt-totalPreviousAmt;
			
			Row dataRow = sheet.createRow(currentRow+2);
			Row dataRow1=sheet.createRow(currentRow+3);
			Row dataRow2=sheet.createRow(currentRow+4);
			
			cell = dataRow.createCell(0);
			Cell cell1=dataRow1.createCell(0);
			Cell cell2=dataRow2.createCell(0);
			
			cell.setCellValue("");
			cell1.setCellValue("");
			cell2.setCellValue("");
			
			cell = dataRow.createCell(14);
			cell1 = dataRow1.createCell(14);
			cell2 = dataRow2.createCell(14);
			
			cell.setCellValue("Actual Amount : ");
			cell1.setCellValue("Adjusted Amount : ");
			cell2.setCellValue("Variation : ");
		
			cell = dataRow.createCell(15);
			cell1 = dataRow1.createCell(15);
			cell2 = dataRow2.createCell(15);
		
			cell.setCellValue(totalPreviousAmt);
			cell1.setCellValue(totalAdjustAmt);
			cell2.setCellValue(totalDiff);
		}

	}
}
