package com.ihealthpharm.reports.helper;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.model.ReportsMappingModel;
@Component
public class AccountReceivablesExcel extends ReportsExcelUtility{
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
		
//		Map<String, List<Map<String, Object>>> accountReceivablesMap = responseList.stream()
//				.collect(Collectors.groupingBy(map -> (String) String.valueOf(map.get("FROM_APPROVED_DATE"))));	
		
		setHeader(workbook,sheet,model);
		int currentRow = sheet.getLastRowNum();
//if(!ObjectUtils.isEmpty(accountReceivablesMap)) { 
//			
//			for(String supplierName :accountReceivablesMap.keySet()) {	
//				List<Map<String, Object>> accountReceivablesDetails = accountReceivablesMap.get(supplierName);
		
		Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
		
		createSupplierTable(sheet, responseFile, borderStyle, headerStyle,responseList,currentRow,dataMap);
		//	}
		generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
//}
		writeToFile(workbook, responseFile);
		 
	}
	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		DecimalFormat df=new DecimalFormat("0.00");
		int currentRow = sheet.getLastRowNum();
	
		double TotalAmtReceived = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT_RECEIVED")?String.valueOf(mapper.get("AMOUNT_RECEIVED")):"0")).sum(); 
		double TotalAmtToBeReceived = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT_TO_BE_RECEIVED")?String.valueOf(mapper.get("AMOUNT_TO_BE_RECEIVED")):"0")).sum(); 
		
//		DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//		String fromDate = f.format((ObjectUtils.isEmpty(responseList))?"":responseList.get(0).get("FROM_APPROVED_DATE")); 
		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		
		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);
	
		cell.setCellValue("");
		cell1.setCellValue("");
		
		cell = dataRow.createCell(9);
		cell1=dataRow1.createCell(9);
		
		cell.setCellValue("Total Amount Received : ");
		cell1.setCellValue("Total Amount To Be Received : ");
		
		cell = dataRow.createCell(10);
		cell1=dataRow1.createCell(10);
		
		String totAmountReceived=df.format(TotalAmtReceived);
		Double totalReceived=Double.parseDouble(totAmountReceived);
		
		String totAmountToBeReceived=df.format(TotalAmtToBeReceived);
		Double totalToBeReceived=Double.parseDouble(totAmountToBeReceived);
		
		cell.setCellValue(totalReceived);
		cell1.setCellValue(totalToBeReceived);
	
	}
	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> accountReceivablesDetails,int rowNum,Map<String,Object> inputJson) {
		//DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//		String fromDate = f.format((ObjectUtils.isEmpty(inputJson))?"":accountReceivablesDetails.get(0).get("FROM_APPROVED_DATE"));
//		String toDate = f.format((ObjectUtils.isEmpty(inputJson))?"":accountReceivablesDetails.get(0).get("TO_APPROVED_DATE"));
		System.out.println(inputJson.get("FROM_APPROVED_DATE"));
		if(inputJson.get("FROM_APPROVED_DATE")!=null && inputJson.get("TO_APPROVED_DATE")!=null) {
		String fromDate = ObjectUtils.isEmpty(inputJson)?"":String.valueOf(inputJson.get("FROM_APPROVED_DATE"));
		String toDate = ObjectUtils.isEmpty(inputJson)?"":String.valueOf(inputJson.get("TO_APPROVED_DATE"));
		
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//		String fromDates=formatter.format(fromDate);
//		String toDates=formatter.format(toDate);
		
		rowNum = rowNum + 3;
		int headRow=rowNum-2;
		
		Row displayRow = sheet.createRow(headRow);
		Cell headCell = displayRow.createCell(0);
		headCell.setCellValue("From Date  :   ");
		headCell=displayRow.createCell(1);
		headCell.setCellValue(fromDate);
		
		headCell = displayRow.createCell(3);
		headCell.setCellValue("To Date  :   ");
		headCell=displayRow.createCell(4);
		headCell.setCellValue(toDate);
//		Row headerRow1 = sheet.createRow(rowNum++);
//		Cell cell1 = headerRow1.createCell(0);
//		cell1.setCellValue("From Date:");
//		cell1.setCellStyle(headerStyle);
		
		// populate Date
		if (!ObjectUtils.isEmpty(accountReceivablesDetails)) {
				
			 
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(1);
			cell.setCellValue("CUSTOMER NAME");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(2);
			cell.setCellValue("RECEIPT NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(3);
			cell.setCellValue("SOURCE REF");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("RECEIPT DATE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(5);
			cell.setCellValue("STATUS");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("AMOUNT RECEIVED");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("AMOUNT TO BE RECEIVED");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("PAYMENT STATUS");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(9);
			cell.setCellValue("SOURCE TYP");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(10);
			cell.setCellValue("APPROVED BY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(11);
			cell.setCellValue("APPROVED DATE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(12);
			cell.setCellValue("CREATED BY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(13);
			cell.setCellValue("MODIFIED BY");
			cell.setCellStyle(headerStyle);	
			
			
			
			for (Map<String, Object> rowData : accountReceivablesDetails) {
				//DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
				//String billDate = f.format((ObjectUtils.isEmpty(accountReceivablesDetails))?"":accountReceivablesDetails.get(0).get("FROM_APPROVED_DATE"));

				
//				Row displayRow = sheet.createRow(rowNum++);
//				Cell headCell = displayRow.createCell(0);
//				Object value = rowData.containsKey("RECEIPT_NO") ? rowData.get("RECEIPT_NO") : "";
//				headCell.setCellValue("RECEIPT NO:");
//				headCell=displayRow.createCell(1);
//				headCell.setCellValue(String.valueOf(value));
				
				
				
				Row dataRow = sheet.createRow(rowNum++);
				Object value =  String.valueOf(accountReceivablesDetails.indexOf(rowData) + 1);
				//sheet.autoSizeColumn(0);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("CUSTOMER_NAME") ? rowData.get("CUSTOMER_NAME") : "";
				//sheet.autoSizeColumn(1);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("RECEIPT_NO") ? rowData.get("RECEIPT_NO") : "";
				//sheet.autoSizeColumn(2);
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SOURCE_REF") ? rowData.get("SOURCE_REF") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("RECEIPT_DATE") ? rowData.get("RECEIPT_DATE") : "";
				//sheet.autoSizeColumn(4);
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("STATUS") ? rowData.get("STATUS") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("AMOUNT_RECEIVED") ? rowData.get("AMOUNT_RECEIVED") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("AMOUNT_TO_BE_RECEIVED") ? rowData.get("AMOUNT_TO_BE_RECEIVED") : "";
				//sheet.autoSizeColumn(7);
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(8);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SOURCE_TYPE") ? rowData.get("SOURCE_TYPE") : "";
				//sheet.autoSizeColumn(9);
				cell = dataRow.createCell(9);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("FIRST_NM") ? rowData.get("FIRST_NM") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(10);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("FROM_APPROVED_DATE") ? rowData.get("FROM_APPROVED_DATE") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(11);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(12);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("MODIFIED_BY") ? rowData.get("MODIFIED_BY") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(13);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				
				
			}
		}
		}
		else {
			rowNum = rowNum + 3;
			int headRow=rowNum-2;
			
			
			// populate Date
			if (!ObjectUtils.isEmpty(accountReceivablesDetails)) {
					
				 
				
				Row headerRow = sheet.createRow(rowNum++);
				Cell cell = headerRow.createCell(0);
				cell.setCellValue("S.NO");
				cell.setCellStyle(headerStyle);
				
				
				cell = headerRow.createCell(1);
				cell.setCellValue("CUSTOMER NAME");
				cell.setCellStyle(headerStyle);
				
				
				cell = headerRow.createCell(2);
				cell.setCellValue("RECEIPT NO");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(3);
				cell.setCellValue("SOURCE REF");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(4);
				cell.setCellValue("RECEIPT DATE");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(5);
				cell.setCellValue("STATUS");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(6);
				cell.setCellValue("AMOUNT RECEIVED");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(7);
				cell.setCellValue("AMOUNT TO BE RECEIVED");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(8);
				cell.setCellValue("PAYMENT STATUS");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(9);
				cell.setCellValue("SOURCE TYP");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(10);
				cell.setCellValue("APPROVED BY");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(11);
				cell.setCellValue("APPROVED DATE");
				cell.setCellStyle(headerStyle);	
				
				
				
				for (Map<String, Object> rowData : accountReceivablesDetails) {
					
					
					
					Row dataRow = sheet.createRow(rowNum++);
					Object value =  String.valueOf(accountReceivablesDetails.indexOf(rowData) + 1);
					//sheet.autoSizeColumn(0);
					cell = dataRow.createCell(0);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					
					value = rowData.containsKey("CUSTOMER_NAME") ? rowData.get("CUSTOMER_NAME") : "";
					//sheet.autoSizeColumn(1);
					cell = dataRow.createCell(1);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("RECEIPT_NO") ? rowData.get("RECEIPT_NO") : "";
					//sheet.autoSizeColumn(2);
					cell = dataRow.createCell(2);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("SOURCE_REF") ? rowData.get("SOURCE_REF") : "";
					//sheet.autoSizeColumn(3);
					cell = dataRow.createCell(3);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("RECEIPT_DATE") ? rowData.get("RECEIPT_DATE") : "";
					//sheet.autoSizeColumn(4);
					cell = dataRow.createCell(4);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("STATUS") ? rowData.get("STATUS") : "";
					//sheet.autoSizeColumn(5);
					cell = dataRow.createCell(5);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);

					
					value = rowData.containsKey("AMOUNT_RECEIVED") ? rowData.get("AMOUNT_RECEIVED") : "";
					//sheet.autoSizeColumn(6);
					cell = dataRow.createCell(6);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("AMOUNT_TO_BE_RECEIVED") ? rowData.get("AMOUNT_TO_BE_RECEIVED") : "";
					//sheet.autoSizeColumn(7);
					cell = dataRow.createCell(7);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
					//sheet.autoSizeColumn(8);
					cell = dataRow.createCell(8);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("SOURCE_TYPE") ? rowData.get("SOURCE_TYPE") : "";
					//sheet.autoSizeColumn(9);
					cell = dataRow.createCell(9);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("FIRST_NM") ? rowData.get("FIRST_NM") : "";
					//sheet.autoSizeColumn(10);
					cell = dataRow.createCell(10);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("FROM_APPROVED_DATE") ? rowData.get("FROM_APPROVED_DATE") : "";
					//sheet.autoSizeColumn(10);
					cell = dataRow.createCell(11);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
			
		}
			}
		}
	}
}
