package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.DateUtility;
import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.dto.HeaderFooterContentDetailsDto;
import com.ihealthpharm.reports.dto.HeaderFooterContentDto;
import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component
public class SalesRegisterDetailsExcel extends ReportsExcelUtility{
	DecimalFormat df2 = new DecimalFormat("#.00");
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
			
		Map<String, List<Map<String, Object>>> salesRegisterDetailsMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("TYPE")));			
				
		setHeader(workbook,sheet,model);
			
		if(!ObjectUtils.isEmpty(salesRegisterDetailsMap)) { 
			
			for(String billType :salesRegisterDetailsMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> salesRegisterDetails = salesRegisterDetailsMap.get(billType);
				createSalesRegisterTable(sheet, responseFile, borderStyle, style,headerStyle,salesRegisterDetails, currentRow); 
			}
			generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}
			
		writeToFile(workbook, responseFile);
		 
	}

	private void generateTotalTable(XSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
	
		int currentRow = sheet.getLastRowNum();
		double totalCashAmt=0.0;
		double totalCardAmt=0.0;
		double totalMPesaAmt=0.0;
		double totalCreditAmt=0.0;
		double totalChequeAmt=0.0;
		double totalInsuranceAmt=0.0;
		
		totalCashAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsValue("CASH")&&mapper.containsKey("AMOUNT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum();  
		totalMPesaAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("M-PESA")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalCardAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("CARD")?String.valueOf(mapper.get("AMOUNT")):"0")).sum();
		totalChequeAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("CHEQUE")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalCreditAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("CREDIT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalInsuranceAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("INSURANCE")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		
		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		Row dataRow2=sheet.createRow(currentRow+4);
		Row dataRow3=sheet.createRow(currentRow+5);
		Row dataRow4=sheet.createRow(currentRow+6);
		Row dataRow5=sheet.createRow(currentRow+7);
			
		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);
		Cell cell2=dataRow2.createCell(0);
		Cell cell3=dataRow3.createCell(0);
		Cell cell4=dataRow4.createCell(0);
		Cell cell5=dataRow5.createCell(0);
		
		cell.setCellValue("");
		cell1.setCellValue("");
		cell2.setCellValue("");
		cell3.setCellValue("");
		cell4.setCellValue("");
		cell5.setCellValue("");
		
		/*for (Map<String, Object> map : responseList) {
			if(map.containsValue("CASH")) {
				System.out.println("TOTAL Cash AMT:"+totalCashAmt);
				cell = dataRow.createCell(5);
				cell.setCellValue("Cash: ");
				cell = dataRow.createCell(6);
				cell.setCellValue("Total Cash Amount : ");
				cell = dataRow.createCell(7);
				cell.setCellValue(totalCashAmt);
			}
			if(map.containsValue("CARD")) {
				System.out.println("TOTAL Card AMT:"+totalCardAmt);
				cell1 = dataRow.createCell(5);
				cell1.setCellValue("Card: ");
				cell1 = dataRow.createCell(6);
				cell1.setCellValue("Total Card Amount : ");
				cell1 = dataRow.createCell(7);
				cell1.setCellValue(totalCardAmt);
			}
			if(map.containsValue("M-PESA")) {
				System.out.println("TOTAL M-PESA AMT:"+totalMPesaAmt);
				cell2 = dataRow.createCell(5);
				cell2.setCellValue("M-Pesa: ");
				cell2 = dataRow.createCell(6);
				cell2.setCellValue("Total M-Pesa Amount : ");
				cell2 = dataRow.createCell(7);
				cell2.setCellValue(totalMPesaAmt);
			}
			if(map.containsValue("CREDIT")) {
				System.out.println("TOTAL Credit AMT:"+totalCreditAmt);
				cell3 = dataRow.createCell(5);
				cell3.setCellValue("Credit: ");
				cell3 = dataRow.createCell(6);
				cell3.setCellValue("Total Credit Amount : ");
				cell3 = dataRow.createCell(7);
				cell3.setCellValue(totalCreditAmt);
			}
			if(map.containsValue("CHEQUE")) {
				System.out.println("TOTAL Cheque AMT:"+totalChequeAmt);
				cell4 = dataRow.createCell(5);
				cell4.setCellValue("Cheque: ");
				cell4 = dataRow.createCell(6);
				cell4.setCellValue("Total Cheque Amount : ");
				cell4 = dataRow.createCell(7);
				cell4.setCellValue(totalChequeAmt);
			}
	}*/
		
				cell = dataRow.createCell(6);
				cell1=dataRow1.createCell(6);
				cell2=dataRow2.createCell(6);
				cell3=dataRow3.createCell(6);
				cell4=dataRow4.createCell(6);
				cell5=dataRow5.createCell(6);

				
				cell.setCellValue("CASH AMOUNT");
				cell1.setCellValue("CARD AMOUNT");
				cell2.setCellValue("CREDIT AMOUNT");
				cell3.setCellValue("M-PESA AMOUNT");
				cell4.setCellValue("CHEQUE AMOUNT");
				cell5.setCellValue("INSURANCE AMOUNT");
				
				cell = dataRow.createCell(7);
				cell1=dataRow1.createCell(7);
				cell2=dataRow2.createCell(7);
				cell3=dataRow3.createCell(7);
				cell4=dataRow4.createCell(7);
				cell5=dataRow5.createCell(7);
				
				cell.setCellValue(df2.format(totalCashAmt));
				cell1.setCellValue(df2.format(totalCardAmt));
				cell2.setCellValue(df2.format(totalCreditAmt));
				cell3.setCellValue(df2.format(totalMPesaAmt));
				cell4.setCellValue(df2.format(totalChequeAmt));
				cell5.setCellValue(df2.format(totalInsuranceAmt));
				//cell.setCellStyle(borderStyle);
				
			}
			
	
	private void createSalesRegisterTable(XSSFSheet sheet,File responseFile, CellStyle borderStyle ,CellStyle style,
			CellStyle headerStyle, List<Map<String, Object>> salesRegisterDetails,int rowNum) {

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
			cell.setCellValue("AMOUNT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("PAID AMOUNT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("BALANCE AMOUNT");
			cell.setCellStyle(headerStyle);	
			
			for (Map<String, Object> rowData : salesRegisterDetails) {
					
				Row dataRow = sheet.createRow(rowNum++);
				Object value =  String.valueOf(salesRegisterDetails.indexOf(rowData) + 1);
				sheet.autoSizeColumn(0);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
				sheet.autoSizeColumn(1);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("FROM_BILL_DATE") ? rowData.get("FROM_BILL_DATE") : "";
				sheet.autoSizeColumn(2);
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CUSTOMER_NM") ? rowData.get("CUSTOMER_NM") : "";
				sheet.autoSizeColumn(3);
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("TYPE") ? rowData.get("TYPE") : "";
				sheet.autoSizeColumn(4);
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("AMOUNT") ? rowData.get("AMOUNT") : "0.00";
				sheet.autoSizeColumn(5);
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("PAID_AMOUNT") ? rowData.get("PAID_AMOUNT") : "";
				sheet.autoSizeColumn(6);
				cell = dataRow.createCell(6);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BALANCE_AMOUNT") ? rowData.get("BALANCE_AMOUNT") : "";
				sheet.autoSizeColumn(7);
				cell = dataRow.createCell(7);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				
			}
		}

	}

}
