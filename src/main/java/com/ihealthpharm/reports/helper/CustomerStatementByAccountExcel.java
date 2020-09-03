package com.ihealthpharm.reports.helper;

import java.io.File;
import java.text.DecimalFormat;
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
public class CustomerStatementByAccountExcel extends ReportsExcelUtility{

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

		Map<String, List<Map<String, Object>>> salesProductMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("CREDIT_NUMBER")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(salesProductMap)) { 
			
			for(String productSummary :salesProductMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> customerList =salesProductMap.get(productSummary);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,customerList, productSummary,currentRow); 
			}
			//generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}
		writeToFile(workbook, responseFile);

	}
	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		DecimalFormat df=new DecimalFormat("0.00");
		int currentRow = sheet.getLastRowNum();

		double TotalAmtReceived = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT_RECEIVED")?String.valueOf(mapper.get("AMOUNT_RECEIVED")):"0")).sum(); 
		double TotalAmtPaid = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT_RECEIVED")?String.valueOf(mapper.get("AMOUNT_RECEIVED")):"0")).sum(); 
		double TotalOustandingAmt = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("outstanding_amt")?String.valueOf(mapper.get("outstanding_amt")):"0")).sum(); 


		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		Row dataRow2=sheet.createRow(currentRow+4);

		Cell cell = dataRow.createCell(0);
		Cell cell_card_amount=dataRow1.createCell(0);
		Cell cell_credit_amount=dataRow2.createCell(0);

		cell.setCellValue("");
		cell_card_amount.setCellValue("");
		cell_credit_amount.setCellValue("");

		cell = dataRow.createCell(5);
		cell_card_amount=dataRow1.createCell(5);
		cell_credit_amount=dataRow2.createCell(5);

		cell.setCellValue("Total Amount Spent : ");
		cell_card_amount.setCellValue("Total Amount Paid");
		cell_credit_amount.setCellValue("Total Outstanding Amount");

		cell = dataRow.createCell(6);
		//		cell1=dataRow1.createCell(10);
		cell_card_amount=dataRow1.createCell(6);
		cell_credit_amount=dataRow2.createCell(6);


		cell.setCellValue((TotalAmtPaid+TotalOustandingAmt));
		cell_card_amount.setCellValue(TotalAmtPaid);
		cell_credit_amount.setCellValue(TotalOustandingAmt);
		//cell1.setCellValue(totalToBeReceived);

	}
	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> accountReceivablesDetails,String accountNo,int rowNum) {

		rowNum = rowNum + 3;

		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(accountReceivablesDetails)) {

			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("CUSTOMER NAME");
			cell.setCellStyle(headerStyle);



			cell = headerRow.createCell(1);
			cell.setCellValue("TRANSACTION DATE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(2);
			cell.setCellValue("BILL TYPE");
			cell.setCellStyle(headerStyle);	


			cell = headerRow.createCell(3);
			cell.setCellValue("BILL NUMBER");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("PAYMENT STATUS");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(5);
			cell.setCellValue("AMOINT PAID");
			cell.setCellStyle(headerStyle);	


			cell = headerRow.createCell(6);
			cell.setCellValue("AMOUNT RECEIVED");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(7);
			cell.setCellValue("OUTSTANDING AMOUNT");
			cell.setCellStyle(headerStyle);	

			Row displayRow = sheet.createRow(headRow++);
			for (Map<String, Object> rowData : accountReceivablesDetails) {	

				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("CREDIT_NUMBER") ? rowData.get("CREDIT_NUMBER") : "";
				headCell.setCellValue("Account No  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));

				Row dataRow = sheet.createRow(rowNum++);


				value = rowData.containsKey("CUSTOMER_NAME") ? rowData.get("CUSTOMER_NAME") : "";
				//sheet.autoSizeColumn(2);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("RECEIPT_DATE") ? rowData.get("RECEIPT_DATE") : "";
				//sheet.autoSizeColumn(9);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value)); 
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("bill_type_id") ? rowData.get("bill_type_id") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("SOURCE_REF") ? rowData.get("SOURCE_REF") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(3);
				cell.setCellValue((String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(4);
				cell.setCellValue((String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("AMOUNT_RECEIVED") ? rowData.get("AMOUNT_RECEIVED") : "";
				//sheet.autoSizeColumn(7);
				cell = dataRow.createCell(5);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("AMOUNT_RECEIVED") ? rowData.get("AMOUNT_RECEIVED") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("outstanding_amt") ? rowData.get("outstanding_amt") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
			}

			DecimalFormat df=new DecimalFormat("0.00");
			int currentRow = sheet.getLastRowNum();

			double TotalAmtReceived = accountReceivablesDetails.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT_RECEIVED")?String.valueOf(mapper.get("AMOUNT_RECEIVED")):"0")).sum(); 
			double TotalAmtPaid = accountReceivablesDetails.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT_RECEIVED")?String.valueOf(mapper.get("AMOUNT_RECEIVED")):"0")).sum(); 
			double TotalOustandingAmt = accountReceivablesDetails.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("outstanding_amt")?String.valueOf(mapper.get("outstanding_amt")):"0")).sum(); 


			Row dataRow = sheet.createRow(currentRow+2);
			Row dataRow1=sheet.createRow(currentRow+3);
			Row dataRow2=sheet.createRow(currentRow+4);

			Cell cell_amt_spent = dataRow.createCell(0);
			Cell cell_card_amount=dataRow1.createCell(0);
			Cell cell_credit_amount=dataRow2.createCell(0);

			cell_amt_spent.setCellValue("");
			cell_card_amount.setCellValue("");
			cell_credit_amount.setCellValue("");

			cell_amt_spent = dataRow.createCell(5);
			cell_card_amount=dataRow1.createCell(5);
			cell_credit_amount=dataRow2.createCell(5);

			cell_amt_spent.setCellValue("Total Amount Spent : ");
			cell_card_amount.setCellValue("Total Amount Paid");
			cell_credit_amount.setCellValue("Total Outstanding Amount");

			cell_amt_spent = dataRow.createCell(6);
			//		cell1=dataRow1.createCell(10);
			cell_card_amount=dataRow1.createCell(6);
			cell_credit_amount=dataRow2.createCell(6);


			cell_amt_spent.setCellValue((TotalAmtPaid+TotalOustandingAmt));
			cell_card_amount.setCellValue(TotalAmtPaid);
			cell_credit_amount.setCellValue(TotalOustandingAmt);
			//cell1.setCellValue(totalToBeReceived);
		}

	}
}
