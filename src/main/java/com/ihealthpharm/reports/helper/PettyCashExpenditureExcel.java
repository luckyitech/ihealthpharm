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
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
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
public class PettyCashExpenditureExcel extends ReportsExcelUtility {

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

		List<Date> datesList = new ArrayList<>();

		Map<Date, List<Map<String, Object>>> pettyExpMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (Date) map.get("CREATION_TS")));

		setHeader(workbook,sheet,model);


		if(!ObjectUtils.isEmpty(pettyExpMap)) { 

			datesList.addAll(pettyExpMap.keySet());

			Collections.sort(datesList);

			for(int i = 0; i < datesList.size(); i++) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> pettyCashExpList =pettyExpMap.get(datesList.get(i));
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,pettyCashExpList, datesList.get(i), currentRow); 
			}
			//generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}

		writeToFile(workbook, responseFile);	 
	}


	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>>  pettyExpList, Date pettyCashExpSummary, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(pettyExpList)) {

			if(!pettyExpList.get(0).get("ENTRY_TYPE").equals("Petty Cash")) {

				double balance=Double.parseDouble(((pettyExpList.get(0).containsKey("BALANCE")? String.valueOf(pettyExpList.get(0).get("BALANCE")) : "")));
				double totBalOnHand=(Double) balance;
				
				Row displayRow = sheet.createRow(rowNum-2);
				Cell headCell = displayRow.createCell(0);
				headCell.setCellValue("Total Balance at Hand   :  ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(totBalOnHand));
			
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.No");
			cell.setCellStyle(headerStyle);


			cell = headerRow.createCell(1);
			cell.setCellValue("DATE");
			cell.setCellStyle(headerStyle);


			cell = headerRow.createCell(2);
			cell.setCellValue("DESCRIPTION");
			cell.setCellStyle(headerStyle);	


			cell = headerRow.createCell(3);
			cell.setCellValue("AMOUNT");
			cell.setCellStyle(headerStyle);	


			cell = headerRow.createCell(4);
			cell.setCellValue("BALANCE");
			cell.setCellStyle(headerStyle);	

			}
			double cashOnHand=0.0;

			for (Map<String, Object> rowData : pettyExpList) {


				String asOfDate=((rowData.containsKey("FROM_DATE") ? String.valueOf(rowData.get("FROM_DATE")) : ""));
				double amountAdded=Double.parseDouble(((rowData.containsKey("DEBIT") ? String.valueOf(rowData.get("DEBIT")) : "")));

				if(rowData.get("ENTRY_TYPE").equals("Petty Cash")) {

					Row displayRow = sheet.createRow(headRow);
					Cell headCell = displayRow.createCell(0);
					headCell.setCellValue("Petty Cash draw on "+asOfDate+"   :  ");
					headCell=displayRow.createCell(1);
					headCell.setCellValue(String.valueOf(amountAdded));

				}

				if(rowData.get("ENTRY_TYPE").equals("Exp PettyCash")) {


					double balance=Double.parseDouble(((rowData.containsKey("BALANCE") ? String.valueOf(rowData.get("BALANCE")) : "")));
					cashOnHand=(Double) balance;

					Row dataRow = sheet.createRow(rowNum++);
					Object value =  String.valueOf(pettyExpList.indexOf(rowData) + 1);
					Cell cell = dataRow.createCell(0);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);


					value = rowData.containsKey("FROM_DATE") ? rowData.get("FROM_DATE") : "";
					cell = dataRow.createCell(1);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);


					value = rowData.containsKey("EXPENSES_REF") ? rowData.get("EXPENSES_REF") : "";
					cell = dataRow.createCell(2);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);

					value = rowData.containsKey("DEBIT") ? rowData.get("DEBIT") : "";
					cell = dataRow.createCell(3);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					cell.setCellStyle(borderStyle);

					double value1=Double.parseDouble(((rowData.containsKey("DEBIT") ? (String.valueOf(rowData.get("DEBIT"))) : "")));
					value = cashOnHand-value1;
					cashOnHand=(Double) value;
					cell = dataRow.createCell(4);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					cell.setCellStyle(borderStyle);


				}
			}
			
			
			int currentRow = sheet.getLastRowNum();

			if(!pettyExpList.get(0).get("ENTRY_TYPE").equals("Petty Cash")) {

				double totalAmount = pettyExpList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("DEBIT")?String.valueOf(mapper.get("DEBIT")):"0")).sum(); 
				double totalBalance=cashOnHand;

				Row dataRow1 = sheet.createRow(currentRow+2);
				Row dataRow2 = sheet.createRow(currentRow+3);

				Cell cell1 = dataRow1.createCell(0);
				Cell cell2 = dataRow2.createCell(0);

				cell1.setCellValue("");
				cell2.setCellValue("");

				cell1= dataRow1.createCell(4);
				cell2= dataRow2.createCell(4);

				cell1.setCellValue("Total Amount : ");
				cell2.setCellValue("Balance : ");


				cell1 = dataRow1.createCell(5);
				cell2 = dataRow2.createCell(5);

				cell1.setCellValue(totalAmount);
				cell2.setCellValue(totalBalance);
			}
		}

	}
}

