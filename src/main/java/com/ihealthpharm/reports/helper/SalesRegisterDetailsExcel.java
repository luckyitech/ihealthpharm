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
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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

	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {

		int currentRow = sheet.getLastRowNum();
		double totalCashAmt=0.0;
		double totalCardAmt=0.0;
		double totalMPesaAmt=0.0;
		double totalCreditAmt=0.0;
		double totalChequeAmt=0.0;
		double totalInsuranceAmt=0.0;
		double totalVatAmt=0.0;
		double totalAmount=0.0;
		double grandTotal=0.0;

		totalCashAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsValue("CASH")&&mapper.containsKey("AMOUNT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum();  
		totalMPesaAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("M-PESA")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalCardAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("CARD")?String.valueOf(mapper.get("AMOUNT")):"0")).sum();
		totalChequeAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("CHEQUE")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalCreditAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("CREDIT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalInsuranceAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("INSURANCE")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalVatAmt=responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("VAT_AMT")?String.valueOf(mapper.get("VAT_AMT")):"0")).sum(); 
		totalAmount=(totalCashAmt+totalMPesaAmt+totalCardAmt+totalChequeAmt+totalCreditAmt+totalInsuranceAmt);
		grandTotal=(totalCashAmt+totalMPesaAmt+totalCardAmt+totalChequeAmt+totalCreditAmt+totalInsuranceAmt+totalVatAmt);

		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		Row dataRow2=sheet.createRow(currentRow+4);
		Row dataRow3=sheet.createRow(currentRow+5);
		Row dataRow4=sheet.createRow(currentRow+6);
		Row dataRow5=sheet.createRow(currentRow+7);
		Row dataRow6=sheet.createRow(currentRow+8);
		Row dataRow7=sheet.createRow(currentRow+9);
		Row dataRow8=sheet.createRow(currentRow+10);


		Cell cell_cash_amount = dataRow.createCell(0);
		Cell cell_card_amount=dataRow1.createCell(0);
		Cell cell_credit_amount=dataRow2.createCell(0);
		Cell cell_mpesa_amount=dataRow3.createCell(0);
		Cell cell_cheque_amount=dataRow4.createCell(0);
		Cell cell_insurance_amount=dataRow5.createCell(0);
		Cell cell_total_amount=dataRow6.createCell(0);
		Cell cell_vat_amount=dataRow7.createCell(0);
		Cell cell_grand_amount=dataRow8.createCell(0);


		cell_cash_amount.setCellValue("");
		cell_card_amount.setCellValue("");
		cell_credit_amount.setCellValue("");
		cell_mpesa_amount.setCellValue("");
		cell_cheque_amount.setCellValue("");
		cell_insurance_amount.setCellValue("");
		cell_total_amount.setCellValue("");
		cell_vat_amount.setCellValue("");
		cell_grand_amount.setCellValue("");


		cell_cash_amount = dataRow.createCell(7);
		cell_card_amount=dataRow1.createCell(7);
		cell_credit_amount=dataRow2.createCell(7);
		cell_mpesa_amount=dataRow3.createCell(7);
		cell_cheque_amount=dataRow4.createCell(7);
		cell_insurance_amount=dataRow5.createCell(7);
		cell_total_amount=dataRow6.createCell(7);
		cell_vat_amount=dataRow7.createCell(7);
		cell_grand_amount=dataRow8.createCell(7);

		cell_cash_amount.setCellValue("CASH AMOUNT");
		cell_card_amount.setCellValue("CARD AMOUNT");
		cell_credit_amount.setCellValue("CREDIT AMOUNT");
		cell_mpesa_amount.setCellValue("M-PESA AMOUNT");
		cell_cheque_amount.setCellValue("CHEQUE AMOUNT");
		cell_insurance_amount.setCellValue("INSURANCE AMOUNT");
		cell_total_amount.setCellValue("TOTAL AMOUNT");
		cell_vat_amount.setCellValue("TOTAL VAT AMONT");
		cell_grand_amount.setCellValue("GRAND TOTAL");

		cell_cash_amount = dataRow.createCell(8);
		cell_card_amount=dataRow1.createCell(8);
		cell_credit_amount=dataRow2.createCell(8);
		cell_mpesa_amount=dataRow3.createCell(8);
		cell_cheque_amount=dataRow4.createCell(8);
		cell_insurance_amount=dataRow5.createCell(8);
		cell_total_amount=dataRow6.createCell(8);
		cell_vat_amount=dataRow7.createCell(8);
		cell_grand_amount=dataRow8.createCell(8);

		if(NumberUtils.isNumber(String.valueOf(totalCashAmt))) {
			cell_cash_amount.setCellType(CellType.NUMERIC);		
			cell_cash_amount.setCellValue(Double.parseDouble(String.valueOf(totalCashAmt)));
		}else {	
			cell_cash_amount.setCellValue(String.valueOf(totalCashAmt));
		}

		if(NumberUtils.isNumber(String.valueOf(totalCardAmt))) {
			cell_card_amount.setCellType(CellType.NUMERIC);		
			cell_card_amount.setCellValue(Double.parseDouble(String.valueOf(totalCardAmt)));
		}else {	
			cell_card_amount.setCellValue(String.valueOf(totalCardAmt));
		}

		if(NumberUtils.isNumber(String.valueOf(totalCreditAmt))) {
			cell_credit_amount.setCellType(CellType.NUMERIC);		
			cell_credit_amount.setCellValue(Double.parseDouble(String.valueOf(totalCreditAmt)));
		}else {	
			cell_credit_amount.setCellValue(String.valueOf(totalCreditAmt));
		}

		if(NumberUtils.isNumber(String.valueOf(totalMPesaAmt))) {
			cell_mpesa_amount.setCellType(CellType.NUMERIC);		
			cell_mpesa_amount.setCellValue(Double.parseDouble(String.valueOf(totalMPesaAmt)));
		}else {
			cell_mpesa_amount.setCellValue(String.valueOf(totalMPesaAmt));
		}

		if(NumberUtils.isNumber(String.valueOf(totalChequeAmt))) {
			cell_cheque_amount.setCellType(CellType.NUMERIC);		
			cell_cheque_amount.setCellValue(Double.parseDouble(String.valueOf(totalChequeAmt)));
		}else {	
			cell_cheque_amount.setCellValue(String.valueOf(totalChequeAmt));
		}

		if(NumberUtils.isNumber(String.valueOf(totalInsuranceAmt))) {
			cell_insurance_amount.setCellType(CellType.NUMERIC);		
			cell_insurance_amount.setCellValue(Double.parseDouble(String.valueOf(totalInsuranceAmt)));
		}else {	
			cell_insurance_amount.setCellValue(String.valueOf(totalInsuranceAmt));
		}

		if(NumberUtils.isNumber(String.valueOf(totalAmount))) {
			cell_total_amount.setCellType(CellType.NUMERIC);		
			cell_total_amount.setCellValue(Double.parseDouble(String.valueOf(totalAmount)));
		}else {	
			cell_total_amount.setCellValue(String.valueOf(totalAmount));
		}


		if(NumberUtils.isNumber(String.valueOf(totalVatAmt))) {
			cell_vat_amount.setCellType(CellType.NUMERIC);		
			cell_vat_amount.setCellValue(Double.parseDouble(String.valueOf(totalVatAmt)));
		}else {	
			cell_vat_amount.setCellValue(String.valueOf(totalVatAmt));
		}

		if(NumberUtils.isNumber(String.valueOf(grandTotal))) {
			cell_grand_amount.setCellType(CellType.NUMERIC);		
			cell_grand_amount.setCellValue(Double.parseDouble(String.valueOf(grandTotal)));
		}else {	
			cell_grand_amount.setCellValue(String.valueOf(grandTotal));
		}
	}

	private void createSalesRegisterTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,CellStyle style,
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

			cell = headerRow.createCell(8);
			cell.setCellValue("VAT AMT");
			cell.setCellStyle(headerStyle);

			cell = headerRow.createCell(9);
			cell.setCellValue("PAYMENT STATUS");
			cell.setCellStyle(headerStyle);	

			for (Map<String, Object> rowData : salesRegisterDetails) {

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

				value = rowData.containsKey("AMOUNT") ? rowData.get("AMOUNT") : "0.00";
				cell = dataRow.createCell(5);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("PAID_AMOUNT") ? rowData.get("PAID_AMOUNT") : "";
				cell = dataRow.createCell(6);

				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("BALANCE_AMOUNT") ? rowData.get("BALANCE_AMOUNT") : "";
				cell = dataRow.createCell(7);

				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}

				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
				cell = dataRow.createCell(8);

				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);


			}
		}

	}

}
