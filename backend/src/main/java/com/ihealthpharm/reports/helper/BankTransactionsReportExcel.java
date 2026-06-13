package com.ihealthpharm.reports.helper;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component
public class BankTransactionsReportExcel extends ReportsExcelUtility{


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


		List<Date> datesList = new ArrayList<>();
		Map<Date, List<Map<String, Object>>> pettyExpMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (Date) map.get("TXN_DATE")));			


		setHeader(workbook,sheet,model);


		if(!ObjectUtils.isEmpty(pettyExpMap)) { 

			datesList.addAll(pettyExpMap.keySet());

			Collections.sort(datesList);

			for(int i = 0; i < datesList.size(); i++) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> pettyCashExpList =pettyExpMap.get(datesList.get(i));
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,pettyCashExpList, currentRow); 
				generateTotalTable(sheet, responseFile,borderStyle,model,pettyCashExpList);

			}

		}

		writeToFile(workbook, responseFile);

	}
	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		DecimalFormat df=new DecimalFormat("0.00");
		int currentRow = sheet.getLastRowNum();

		double partyBalance=0.00;
		double counterPartyBalance=0.00;

		partyBalance  = Double.parseDouble(String.valueOf(responseList.get(responseList.size()-1).get("BALANCE")));
		if(NumberUtils.isNumber(String.valueOf(responseList.get(responseList.size()-1).get("COUNTER_PARTY_BALANCE")))) {
			counterPartyBalance  = Double.parseDouble(String.valueOf(responseList.get(responseList.size()-1).get("COUNTER_PARTY_BALANCE")));
		}
		String partyAccount=String.valueOf(responseList.get(responseList.size()-1).get("PARTY"));
		String counterPartyAccount=String.valueOf(responseList.get(responseList.size()-1).get("COUNTER_PARTY"));

		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);

		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);

		cell.setCellValue("");
		cell1.setCellValue("");

		cell = dataRow.createCell(9);
		cell1=dataRow1.createCell(9);

		cell.setCellValue(partyAccount+" "+" Balance : ");
		cell1.setCellValue(counterPartyAccount+" "+" Balance : ");

		cell = dataRow.createCell(10);
		cell1=dataRow1.createCell(10);

		String totPartyBalance=df.format(partyBalance);
		Double partyBalFinal=Double.parseDouble(totPartyBalance);

		String totCounterPartyBalance=df.format(counterPartyBalance);
		Double couPartyBalFinal=Double.parseDouble(totCounterPartyBalance);

		cell.setCellValue(partyBalFinal);
		cell1.setCellValue(couPartyBalFinal);

	}
	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> accountReceivablesDetails,int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;


		Row headerRow = sheet.createRow(rowNum++);
		Cell cell = headerRow.createCell(0);
		cell.setCellValue("S.NO");
		cell.setCellStyle(headerStyle);


		cell = headerRow.createCell(1);
		cell.setCellValue("TRANSACTION REF NO");
		cell.setCellStyle(headerStyle);


		cell = headerRow.createCell(2);
		cell.setCellValue("PARTY");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(3);
		cell.setCellValue("COUNTER PARTY");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(4);
		cell.setCellValue("MODE");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(5);
		cell.setCellValue("TRANSACTION TYPE");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(6);
		cell.setCellValue("AMOUNT");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(7);
		cell.setCellValue("PARTY BALANCE");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(8);
		cell.setCellValue("COUNTER PARTY BALANCE");
		cell.setCellStyle(headerStyle);	


		cell = headerRow.createCell(9);
		cell.setCellValue("TXN DATE");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(10);
		cell.setCellValue("LAST UPDATE TS");
		cell.setCellStyle(headerStyle);


		cell = headerRow.createCell(11);
		cell.setCellValue("REASON");
		cell.setCellStyle(headerStyle);	


		cell = headerRow.createCell(12);
		cell.setCellValue("SUBMITTED BY");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(13);
		cell.setCellValue("STATUS");
		cell.setCellStyle(headerStyle);	

		for (Map<String, Object> rowData : accountReceivablesDetails) {


			Row dataRow = sheet.createRow(rowNum++);
			Object value =  String.valueOf(accountReceivablesDetails.indexOf(rowData) + 1);
			//sheet.autoSizeColumn(0);
			cell = dataRow.createCell(0);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);


			value = rowData.containsKey("TRANSACTION_REF") ? rowData.get("TRANSACTION_REF") : "";
			//sheet.autoSizeColumn(1);
			cell = dataRow.createCell(1);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("PARTY") ? rowData.get("PARTY") : "";
			//sheet.autoSizeColumn(2);
			cell = dataRow.createCell(2);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("COUNTER_PARTY") ? rowData.get("COUNTER_PARTY") : "";
			//sheet.autoSizeColumn(3);
			cell = dataRow.createCell(3);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("MODE") ? rowData.get("MODE") : "";
			//sheet.autoSizeColumn(4);
			cell = dataRow.createCell(4);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("TRANSACTION_TYPE") ? rowData.get("TRANSACTION_TYPE") : "";
			//sheet.autoSizeColumn(4);
			cell = dataRow.createCell(5);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("AMOUNT") ? rowData.get("AMOUNT") : "";
			//sheet.autoSizeColumn(5);
			cell = dataRow.createCell(6);
			cell.setCellValue(Double.parseDouble(String.valueOf(value)));
			cell.setCellStyle(borderStyle);


			value = rowData.containsKey("BALANCE") ? rowData.get("BALANCE") : "";
			//sheet.autoSizeColumn(6);
			cell = dataRow.createCell(7);
			cell.setCellValue(Double.parseDouble(String.valueOf(value)));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("COUNTER_PARTY_BALANCE") ? rowData.get("COUNTER_PARTY_BALANCE") : "";
			//sheet.autoSizeColumn(7);
			cell = dataRow.createCell(8);
			if(NumberUtils.isNumber(String.valueOf(value))) {
				cell.setCellType(CellType.NUMERIC);		
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
			}else {	
				cell.setCellValue(String.valueOf(value));
			}

			cell.setCellStyle(borderStyle);


			value = rowData.containsKey("FROM_TRANSACTION_DATE") ? rowData.get("FROM_TRANSACTION_DATE") : "";
			//sheet.autoSizeColumn(9);
			cell = dataRow.createCell(9);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("LAST_UPDATE_TS") ? rowData.get("LAST_UPDATE_TS") : "";
			//sheet.autoSizeColumn(10);
			cell = dataRow.createCell(10);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);



			value = rowData.containsKey("REASON") ? rowData.get("REASON") : "";
			//sheet.autoSizeColumn(9);
			cell = dataRow.createCell(11);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);


			value = rowData.containsKey("SUBMITTED_BY") ? rowData.get("SUBMITTED_BY") : "";
			//sheet.autoSizeColumn(10);
			cell = dataRow.createCell(12);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("STATUS") ? rowData.get("STATUS") : "";
			//sheet.autoSizeColumn(10);
			cell = dataRow.createCell(13);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);


		}
	}
}
