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
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.reports.model.ReportsMappingModel;
@Component
public class SalesProfitAttributionExcel extends ReportsExcelUtility{
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
				.collect(Collectors.groupingBy(map -> (String) map.get("CREATED_BY")));			
		
		
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
		
		double totalProfit = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("PROFIT")?String.valueOf(mapper.get("PROFIT")):"0")).sum(); 
		double totalSaleValue = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("SALE_AMOUNT")?String.valueOf(mapper.get("SALE_AMOUNT")):"0")).sum(); 
		double totalPurValue = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("PURCHASE_AMOUNT")?String.valueOf(mapper.get("PURCHASE_AMOUNT")):"0")).sum(); 
		
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
		
		cell.setCellValue("Total Purchase Amount : ");
		cell1.setCellValue("Total Sale Amount : ");
		cell2.setCellValue("Total Profit : ");
	
		cell = dataRow.createCell(15);
		cell1 = dataRow1.createCell(15);
		cell2 = dataRow2.createCell(15);
	
		cell.setCellValue(totalPurValue);
		cell1.setCellValue(totalSaleValue);
		cell2.setCellValue(totalProfit);
	
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
			cell.setCellValue("BILL CODE");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(2);
			cell.setCellValue("BILL DATE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(3);
			cell.setCellValue("ITEM NAME");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("BATCH NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(5);
			cell.setCellValue("SALE QTY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("QTY FREE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("P PRICE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("P DISC%");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(9);
			cell.setCellValue("S PRICE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(10);
			cell.setCellValue("S DISC%");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(11);
			cell.setCellValue("VAT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(12);
			cell.setCellValue("P AMT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(13);
			cell.setCellValue("S AMT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(14);
			cell.setCellValue("PROFIT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(15);
			cell.setCellValue("PROFIT%");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(16);
			cell.setCellValue("SERVED BY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(17);
			cell.setCellValue("CREATION TS");
			cell.setCellStyle(headerStyle);	
			
			Row displayRow = sheet.createRow(headRow++);
			for (Map<String, Object> rowData : salesProfitList) {

				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
				headCell.setCellValue("Served By  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(salesProfitList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("FROM_BILL_DATE") ? rowData.get("FROM_BILL_DATE") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SALE_QTY") ? rowData.get("SALE_QTY") : "";
				cell = dataRow.createCell(5);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("QTY_FREE") ? rowData.get("QTY_FREE") : "";
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("UNIT_PURCHASE_PRICE") ? rowData.get("UNIT_PURCHASE_PRICE") : "";
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PURCHASE_DISCOUNT_PERCENTAGE") ? rowData.get("PURCHASE_DISCOUNT_PERCENTAGE") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("UNIT_SALE_PRICE") ? rowData.get("UNIT_SALE_PRICE") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("DISCOUNT_PERCENTAGE") ? rowData.get("DISCOUNT_PERCENTAGE") : "";
				cell = dataRow.createCell(10);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("VAT") ? rowData.get("VAT") : "";
				cell = dataRow.createCell(11);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PURCHASE_AMOUNT") ? rowData.get("PURCHASE_AMOUNT") : "";
				cell = dataRow.createCell(12);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SALE_AMOUNT") ? rowData.get("SALE_AMOUNT") : "";
				cell = dataRow.createCell(13);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PROFIT") ? rowData.get("PROFIT") : "";
				cell = dataRow.createCell(14);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PROFIT_PER") ? rowData.get("PROFIT_PER") : "";
				cell = dataRow.createCell(15);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
				cell = dataRow.createCell(16);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("CREATION_TS") ? rowData.get("CREATION_TS") : "";
				cell = dataRow.createCell(17);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
			}
		}

	}
}
