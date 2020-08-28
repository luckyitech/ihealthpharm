package com.ihealthpharm.reports.helper;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component
public class SalesHourlyDetailsExcel extends ReportsExcelUtility{
	DecimalFormat df2 = new DecimalFormat("#.00");
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

		CellStyle style=workbook.createCellStyle();
		style.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));  

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

		Map<String, List<Map<String, Object>>> salesRegisterMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("TIME_TO_GROUP")));


		setHeader(workbook,sheet,model);

		List<String> datesList = new ArrayList<>();


		if(!ObjectUtils.isEmpty(salesRegisterMap)) { 

			datesList.addAll(salesRegisterMap.keySet());
			Collections.sort(datesList);

			for(int i = 0; i < datesList.size(); i++) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> salesRegisterDetailsMap = salesRegisterMap.get(datesList.get(i));
				createSalesRegisterTable(sheet, responseFile, borderStyle, style,headerStyle,salesRegisterDetailsMap,datesList.get(i),currentRow); 
			}
			generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}


		//		if(!ObjectUtils.isEmpty(salesRegisterMap)) { 
		//
		//			for(String billDate :salesRegisterMap.keySet()) {	
		//				int currentRow = sheet.getLastRowNum();
		//				List<Map<String, Object>> salesRegisterDetails = salesRegisterMap.get(billDate);
		//				createSalesRegisterTable(sheet, responseFile, borderStyle, style,headerStyle,salesRegisterDetails, billDate,currentRow); 
		//			}
		//			generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		//		}

		writeToFile(workbook, responseFile);

	}

	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {

		int currentRow = sheet.getLastRowNum();
		long noOfSales  = responseList.size();

		Row dataRow = sheet.createRow(currentRow+2);

		Cell cell = dataRow.createCell(0);
		
		cell.setCellValue("");
		
		cell = dataRow.createCell(14);
		
		cell.setCellValue("Total No. Of Sales : ");
		
		cell = dataRow.createCell(15);
		
		cell.setCellValue(noOfSales);
		
	}

	private void createSalesRegisterTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,CellStyle style,
			CellStyle headerStyle, List<Map<String, Object>> salesRegisterDetails,String billDate,int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(salesRegisterDetails)) {


			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);


			cell = headerRow.createCell(1);
			cell.setCellValue("BILL NO");
			cell.setCellStyle(headerStyle);


			cell = headerRow.createCell(2);
			cell.setCellValue("DATE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(3);
			cell.setCellValue("CUSTOMER NAME");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(4);
			cell.setCellValue("BILL TYPE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(5);
			cell.setCellValue("PAID AMOUNT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(6);
			cell.setCellValue("BALANCE AMOUNT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(7);
			cell.setCellValue("VAT AMT");
			cell.setCellStyle(headerStyle);

			cell = headerRow.createCell(8);
			cell.setCellValue("PAYMENT STATUS");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(9);
			cell.setCellValue("CREATED BY");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(10);
			cell.setCellValue("MODIFIED BY");
			cell.setCellStyle(headerStyle);	


			cell = headerRow.createCell(11);
			cell.setCellValue("CREATION TS");
			cell.setCellStyle(headerStyle);	

			Row displayRow = sheet.createRow(headRow++);
			for (Map<String, Object> rowData : salesRegisterDetails) {

				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
				Cell headCell = displayRow.createCell(0);
				String value1 = rowData.containsKey("CREATION_TS") ? df.format(rowData.get("CREATION_TS")) : "";
				headCell.setCellValue("Sales From  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(value1);

				Row dataRow = sheet.createRow(rowNum++);
				Object value =  String.valueOf(salesRegisterDetails.indexOf(rowData) + 1);
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

				value = rowData.containsKey("CUSTOMER_NM") ? rowData.get("CUSTOMER_NM") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("TYPE") ? rowData.get("TYPE") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("PAID_AMOUNT") ? rowData.get("PAID_AMOUNT") : "";
				cell = dataRow.createCell(5);

				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("BALANCE_AMOUNT") ? rowData.get("BALANCE_AMOUNT") : "";
				cell = dataRow.createCell(6);

				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}

				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
				cell = dataRow.createCell(7);

				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("MODIFIED_BY") ? rowData.get("MODIFIED_BY") : "";
				cell = dataRow.createCell(10);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("CREATION_FORMAT_TS") ? rowData.get("CREATION_FORMAT_TS") : "";
				cell = dataRow.createCell(11);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

			}
		}

	}

}
