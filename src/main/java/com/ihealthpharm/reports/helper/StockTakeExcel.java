package com.ihealthpharm.reports.helper;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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

public class StockTakeExcel extends ReportsExcelUtility{
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

		
		Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
		
		createSupplierTable(sheet, responseFile, borderStyle, headerStyle,responseList,currentRow,dataMap);
	
		//generateTotalTable(sheet, responseFile,borderStyle,model,responseList);

		writeToFile(workbook, responseFile);
		 
	}
	
	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> accountReceivablesDetails,int rowNum,Map<String,Object> inputJson) {
		
		
		DateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm a");
		String billDate="";
		
		System.out.println(inputJson.get("FROM_SYSTEM_DATE"));
		if(inputJson.get("FROM_SYSTEM_DATE")!=null && inputJson.get("TO_SYSTEM_DATE")!=null) {
		String fromDate = ObjectUtils.isEmpty(inputJson)?"":String.valueOf(inputJson.get("FROM_SYSTEM_DATE"));
		String toDate = ObjectUtils.isEmpty(inputJson)?"":String.valueOf(inputJson.get("TO_SYSTEM_DATE"));
		
		
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

		// populate Date
		if (!ObjectUtils.isEmpty(accountReceivablesDetails)) {
				
			 
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(1);
			cell.setCellValue("ITEM NAME");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(2);
			cell.setCellValue("INVOICE NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(3);
			cell.setCellValue("BATCH NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("EXPIRY DATE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(5);
			cell.setCellValue("RACK");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("SHELF");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("QUANTITY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("ADJ STOCK");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(9);
			cell.setCellValue("LAST UPDATED USER");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(10);
			cell.setCellValue("LAST UPDATED DT");
			cell.setCellStyle(headerStyle);	
			
	
			
			
			
			for (Map<String, Object> rowData : accountReceivablesDetails) {
	
				
				
				
				Row dataRow = sheet.createRow(rowNum++);
				Object value =  String.valueOf(accountReceivablesDetails.indexOf(rowData) + 1);
				//sheet.autoSizeColumn(0);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				//sheet.autoSizeColumn(1);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("INVOICE_NO") ? rowData.get("INVOICE_NO") : "";
				//sheet.autoSizeColumn(2);
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				//sheet.autoSizeColumn(4);
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("RACK") ? rowData.get("RACK") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("SHELF") ? rowData.get("SHELF") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(6);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PHYSICAL_STOCK") ? rowData.get("PHYSICAL_STOCK") : "";
				//sheet.autoSizeColumn(7);
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ADJUSTEMENT_STOCK") ? rowData.get("ADJUSTEMENT_STOCK") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(8);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("LAST_UPDATE_USER_ID") ? rowData.get("LAST_UPDATE_USER_ID") : "";
				//sheet.autoSizeColumn(9);
				cell = dataRow.createCell(9);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
//               if(rowData.get("LAST_UPDATE_TS")==null) {
//					
//				}else {
//					billDate = f.format((ObjectUtils.isEmpty(rowData))?"":rowData.get("LAST_UPDATE_TS"));
//				}
//				
//				
//				value =  billDate;
				
				value = rowData.containsKey("LAST_UPDATE_TS") ? rowData.get("LAST_UPDATE_TS") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(10);
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
				cell.setCellValue("ITEM NAME");
				cell.setCellStyle(headerStyle);
				
				
				cell = headerRow.createCell(2);
				cell.setCellValue("INVOICE NO");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(3);
				cell.setCellValue("BATCH NO");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(4);
				cell.setCellValue("EXPIRY DATE");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(5);
				cell.setCellValue("RACK");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(6);
				cell.setCellValue("SHELF");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(7);
				cell.setCellValue("QUANTITY");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(8);
				cell.setCellValue("ADJ STOCK");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(9);
				cell.setCellValue("LAST UPDATED USER");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(10);
				cell.setCellValue("LAST UPDATED DT");
				cell.setCellStyle(headerStyle);	
				
		
				
				
				
				for (Map<String, Object> rowData : accountReceivablesDetails) {
					//DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
					//String billDate = f.format((ObjectUtils.isEmpty(accountReceivablesDetails))?"":accountReceivablesDetails.get(0).get("FROM_APPROVED_DATE"));

					
//					Row displayRow = sheet.createRow(rowNum++);
//					Cell headCell = displayRow.createCell(0);
//					Object value = rowData.containsKey("RECEIPT_NO") ? rowData.get("RECEIPT_NO") : "";
//					headCell.setCellValue("RECEIPT NO:");
//					headCell=displayRow.createCell(1);
//					headCell.setCellValue(String.valueOf(value));
					
					
					
					Row dataRow = sheet.createRow(rowNum++);
					Object value =  String.valueOf(accountReceivablesDetails.indexOf(rowData) + 1);
					//sheet.autoSizeColumn(0);
					cell = dataRow.createCell(0);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					
					value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
					//sheet.autoSizeColumn(1);
					cell = dataRow.createCell(1);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("INVOICE_NO") ? rowData.get("INVOICE_NO") : "";
					//sheet.autoSizeColumn(2);
					cell = dataRow.createCell(2);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
					//sheet.autoSizeColumn(3);
					cell = dataRow.createCell(3);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
					//sheet.autoSizeColumn(4);
					cell = dataRow.createCell(4);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("RACK") ? rowData.get("RACK") : "";
					//sheet.autoSizeColumn(5);
					cell = dataRow.createCell(5);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);

					
					value = rowData.containsKey("SHELF") ? rowData.get("SHELF") : "";
					//sheet.autoSizeColumn(6);
					cell = dataRow.createCell(6);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("PHYSICAL_STOCK") ? rowData.get("PHYSICAL_STOCK") : "";
					//sheet.autoSizeColumn(7);
					cell = dataRow.createCell(7);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("ADJUSTEMENT_STOCK") ? rowData.get("ADJUSTEMENT_STOCK") : "";
					//sheet.autoSizeColumn(8);
					cell = dataRow.createCell(8);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("LAST_UPDATE_USER_ID") ? rowData.get("LAST_UPDATE_USER_ID") : "";
					//sheet.autoSizeColumn(9);
					cell = dataRow.createCell(9);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
//					  if(rowData.get("LAST_UPDATE_TS")==null) {
//							
//						}else {
//							billDate = f.format((ObjectUtils.isEmpty(rowData))?"":rowData.get("LAST_UPDATE_TS"));
//						}
//						
//						
//						value =  billDate;
//					
					value = rowData.containsKey("LAST_UPDATE_TS") ? rowData.get("LAST_UPDATE_TS") : "";
					//sheet.autoSizeColumn(10);
					cell = dataRow.createCell(10);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
			
		}
			}
		}
	}

}
