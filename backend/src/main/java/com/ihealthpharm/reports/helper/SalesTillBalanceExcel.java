package com.ihealthpharm.reports.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
public class SalesTillBalanceExcel extends ReportsExcelUtility{

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

		Map<String, List<Map<String, Object>>> tillAccountMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("TILL_ACCOUNT")));			


		setHeader(workbook,sheet,model);

		List<Date> datesList = new ArrayList<>();

		if(!ObjectUtils.isEmpty(tillAccountMap)) { 

			for(String tillAccount :tillAccountMap.keySet()) {	
				//int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> tillAccountList =tillAccountMap.get(tillAccount);
				Map<Date, List<Map<String, Object>>> salesTillAccountMap = tillAccountList.stream()
						.collect(Collectors.groupingBy(map -> (Date) map.get("AS_NOW_DATE")));			


				if(!ObjectUtils.isEmpty(salesTillAccountMap)) { 

					datesList.addAll(salesTillAccountMap.keySet());
					Collections.sort(datesList);

					for(int i = 0; i < datesList.size(); i++) {	

						int currentRow = sheet.getLastRowNum();
						List<Map<String, Object>> salesTillAccountList =salesTillAccountMap.get(datesList.get(i));

						createSalesByPersonTable(sheet, responseFile, borderStyle, headerStyle,salesTillAccountList, tillAccount, currentRow);  
						generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
					}

				}
			}

		}

		writeToFile(workbook, responseFile);	 

	}
	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		int currentRow = sheet.getLastRowNum();

		double totalCashAmount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		double previousBalance  = Double.parseDouble(String.valueOf(responseList.get(responseList.size()-1).get("PREV_BALANCE")));
		double currentBalance  = Double.parseDouble(String.valueOf(responseList.get(responseList.size()-1).get("CURRENT_BALANCE")));

		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		Row dataRow2=sheet.createRow(currentRow+4);

		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);
		//Cell cell2=dataRow2.createCell(0);

		cell.setCellValue("");
		cell1.setCellValue("");
		//cell2.setCellValue("");

		cell = dataRow.createCell(10);
		cell1 = dataRow1.createCell(10);
		//cell2 = dataRow2.createCell(14);

		cell.setCellValue("Day's Cash Amount Rec : ");
		cell1.setCellValue("Current Balance : ");
		//cell2.setCellValue("Total Profit : ");

		cell = dataRow.createCell(11);
		cell1 = dataRow1.createCell(11);
		//	cell2 = dataRow2.createCell(15);

		cell.setCellValue(totalCashAmount);
		cell1.setCellValue(currentBalance);
		//cell2.setCellValue(totalProfit);

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
			cell.setCellValue("AS OF DATE");
			cell.setCellStyle(headerStyle);


			cell = headerRow.createCell(2);
			cell.setCellValue("CUSTOMER");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(3);
			cell.setCellValue("BILL CODE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(4);
			cell.setCellValue("ACCOUNT NAME");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(5);
			cell.setCellValue("PREVIOUS BALANCE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(6);
			cell.setCellValue("AMOUN");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(7);
			cell.setCellValue("CURRENT BALANCE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(8);
			cell.setCellValue("ADJUSTED BALANCE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(9);
			cell.setCellValue("SUBMITTED BY");
			cell.setCellStyle(headerStyle);	


			Row displayRow = sheet.createRow(headRow++);
			for (Map<String, Object> rowData : salesProfitList) {

				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("AS_OF_DATE") ? rowData.get("AS_OF_DATE") : "";
				headCell.setCellValue("As of Date  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));


				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(salesProfitList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("AS_OF_DATE") ? rowData.get("AS_OF_DATE") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("CUSTOMER_NM") ? rowData.get("CUSTOMER_NM") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("TILL_ACCOUNT") ? rowData.get("TILL_ACCOUNT") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("PREV_BALANCE") ? rowData.get("PREV_BALANCE") : "";
				cell = dataRow.createCell(5);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("AMOUNT") ? rowData.get("AMOUNT") : "";
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("CURRENT_BALANCE") ? rowData.get("CURRENT_BALANCE") : "";
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("ADJUSTED_BALANCE") ? rowData.get("ADJUSTED_BALANCE") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("SUBMITTED_BY") ? rowData.get("SUBMITTED_BY") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);



			}
		}

	}
}
