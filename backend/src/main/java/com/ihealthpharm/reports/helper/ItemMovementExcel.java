package com.ihealthpharm.reports.helper;

import java.io.File;
import java.sql.Date;
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

public class ItemMovementExcel extends ReportsExcelUtility{
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

		Map<Date, List<Map<String, Object>>> salesByPersonMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (Date) map.get("CURRENT_STOCK_DATE")));			


		setHeader(workbook,sheet,model);


		if(!ObjectUtils.isEmpty(salesByPersonMap)) { 

			for(Date stockDate :salesByPersonMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> salesByPersonList =salesByPersonMap.get(stockDate);
				createSalesByPersonTable(sheet, responseFile, borderStyle, headerStyle,salesByPersonList, stockDate, currentRow); 
			}

		}

		writeToFile(workbook, responseFile);	 

	}

	private void createSalesByPersonTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> salesProfitList,Date stockDate,int rowNum) {

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
			cell.setCellValue("SUPPLIER");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(3);
			cell.setCellValue("BILL CODE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(4);
			cell.setCellValue("INVOICE NO");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(5);
			cell.setCellValue("OPENING STOCK");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(6);
			cell.setCellValue("CLOSING STOCK");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(7);
			cell.setCellValue("PURCHASE RATE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("P DISC%");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(9);
			cell.setCellValue("TAX%");
			cell.setCellStyle(headerStyle);	


			cell = headerRow.createCell(10);
			cell.setCellValue("OPENING BAL");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(11);
			cell.setCellValue("CLOSING BAL");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(12);
			cell.setCellValue("EXPIRY DT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(13);
			cell.setCellValue("ENTRY TYPE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(14);
			cell.setCellValue("CREATED BY");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(15);
			cell.setCellValue("LAST UPDATE TS");
			cell.setCellStyle(headerStyle);	



			Row displayRow = sheet.createRow(headRow++);
			for (Map<String, Object> rowData : salesProfitList) {

				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("CURRENT_STOCK_DATE") ? rowData.get("CURRENT_STOCK_DATE") : "";
				headCell.setCellValue("Date  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(df.format(value)));


				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(salesProfitList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("SUPPLIER_NM") ? rowData.get("SUPPLIER_NM") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("INVOICE_NO") ? rowData.get("INVOICE_NO") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("OPENING_STOCK") ? rowData.get("OPENING_STOCK") : "";
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("CLOSING_STOCK") ? rowData.get("CLOSING_STOCK") : "";
				cell = dataRow.createCell(6);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("UNIT_PURCHASE_RATE") ? rowData.get("UNIT_PURCHASE_RATE") : "";
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("P_DISC") ? rowData.get("P_DISC") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("TAX") ? rowData.get("TAX") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("OPENING_BALANCE") ? rowData.get("OPENING_BALANCE") : "";
				cell = dataRow.createCell(10);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("CLOSING_BALANCE") ? rowData.get("CLOSING_BALANCE") : "";
				cell = dataRow.createCell(11);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				cell = dataRow.createCell(12);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("ENTRY_TYPE") ? rowData.get("ENTRY_TYPE") : "";
				cell = dataRow.createCell(13);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("LAST_UPDATE_USER") ? rowData.get("LAST_UPDATE_USER") : "";
				cell = dataRow.createCell(14);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("LAST_UPDATE_TS") ? rowData.get("LAST_UPDATE_TS") : "";
				cell = dataRow.createCell(15);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);



			}

		}

	}
}
