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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component
public class PurchaseOrderDetailsExcel extends ReportsExcelUtility {
	
	public void generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile) {

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
		
		
		Map<String, List<Map<String, Object>>> purchaseInvoiceDetailsMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("SP_NAME")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(purchaseInvoiceDetailsMap)) { 
			
			for(String supplierName :purchaseInvoiceDetailsMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> purchaseInvoiceDetails = purchaseInvoiceDetailsMap.get(supplierName);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,purchaseInvoiceDetails, supplierName, currentRow); 
			}
			generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}
		
		
		writeToFile(workbook, responseFile);
		 
	}

	
	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		int currentRow = sheet.getLastRowNum();
		DecimalFormat df=new DecimalFormat("0.00");
		
		double grossTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_VALUE")?String.valueOf(mapper.get("TOTAL_VALUE")):"0")).sum(); 
		double discountTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("DISCOUNT")?String.valueOf(mapper.get("DISCOUNT")):"0")).sum(); 
		double vatTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("VAT_AMT")?String.valueOf(mapper.get("VAT_AMT")):"0")).sum(); 
		//double netTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("ACTUAL_VALUE")?String.valueOf(mapper.get("ACTUAL_VALUE")):"0")).sum(); 
		double chargesTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("OTHER_CHARGES")?String.valueOf(mapper.get("OTHER_CHARGES")):"0")).sum(); 
		double netAmt=(grossTotal+vatTotal+chargesTotal);
		
		String net=df.format(netAmt);
		String sub=df.format(grossTotal);
		String vat=df.format(vatTotal);
		Double subTotal=Double.parseDouble(sub);
		Double netTotal=Double.parseDouble(net);
		Double totalVat=Double.parseDouble(vat);
		
		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		Row dataRow2=sheet.createRow(currentRow+4);
		Row dataRow3=sheet.createRow(currentRow+5);
		Row dataRow4=sheet.createRow(currentRow+6);
		
		
		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);
		Cell cell2=dataRow2.createCell(0);
		Cell cell3=dataRow3.createCell(0);
		Cell cell4=dataRow4.createCell(0);
		cell.setCellValue("");
		cell1.setCellValue("");
		cell2.setCellValue("");
		cell3.setCellValue("");
		cell4.setCellValue("");
		//cell.setCellStyle(borderStyle);
		
		cell = dataRow.createCell(6);
		cell1=dataRow1.createCell(6);
		cell2=dataRow2.createCell(6);
		cell3=dataRow3.createCell(6);
		cell4=dataRow4.createCell(6);

		cell.setCellValue("GROSS TOTAL : ");
		cell1.setCellValue("MISC COST : ");
		cell2.setCellValue("DISCOUNT : ");
		cell3.setCellValue("VAT : ");
		cell4.setCellValue("NET TOTAL : ");
		//cell.setCellStyle(borderStyle);
					
		
		
		cell = dataRow.createCell(7);
		cell1=dataRow1.createCell(7);
		cell2=dataRow2.createCell(7);
		cell3=dataRow3.createCell(7);
		cell4=dataRow4.createCell(7);
		
		cell.setCellValue(subTotal);
		cell1.setCellValue(chargesTotal);
		cell2.setCellValue(discountTotal);
		cell3.setCellValue(totalVat);
		cell4.setCellValue(netTotal);
		//cell.setCellStyle(borderStyle);
		
		
	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> purchaseInvoiceDetails, String supplierName, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;
		
		// populate Date
		if (!ObjectUtils.isEmpty(purchaseInvoiceDetails)) {
			
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(1);
			cell.setCellValue("PRODUCT NAME");
			cell.setCellStyle(headerStyle);
			
			
//			cell = headerRow.createCell(2);
//			cell.setCellValue("BATCH NO");
//			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(2);
			cell.setCellValue("QTY");
			cell.setCellStyle(headerStyle);	
			
//			cell =;	 headerRow.createCell(4);
//			cell.setCellValue("EXPIRY");
//			cell.setCellStyle(headerStyle)
			
			cell = headerRow.createCell(3);
			cell.setCellValue("BONUS");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("UNIT PRICE");
			cell.setCellStyle(headerStyle);	
			
//			cell = headerRow.createCell(7);
//			cell.setCellValue("GROSS AMOUNT");
//			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(5);
			cell.setCellValue("DISCOUNT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("VAT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("NET AMOUNT");
			cell.setCellStyle(headerStyle);	
			
			for (Map<String, Object> rowData : purchaseInvoiceDetails) {
				
				Row displayRow = sheet.createRow(headRow);
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("SP_NAME") ? rowData.get("SP_NAME") : "";
				headCell.setCellValue("Supplier Name  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				 //sheet.addMergedRegion(new CellRangeAddress(8,8,0,5));
			    
				Cell headCell1 = displayRow.createCell(2);
				value = rowData.containsKey("PURCHASE_ORDER_NO") ? rowData.get("PURCHASE_ORDER_NO") : "";
				headCell1.setCellValue("PO No  :   ");
				headCell1=displayRow.createCell(3);
				headCell1.setCellValue(String.valueOf(value));
				
				Cell headCell2 = displayRow.createCell(4);
				value = rowData.containsKey("PURCHASE_ORDER_DT") ? rowData.get("PURCHASE_ORDER_DT") : "";
				headCell2.setCellValue("PO Date  :   ");
				headCell2=displayRow.createCell(5);
				headCell2.setCellValue(String.valueOf(value));
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(purchaseInvoiceDetails.indexOf(rowData) + 1);
				//sheet.autoSizeColumn(0);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				//sheet.autoSizeColumn(1);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				

				
				value = rowData.containsKey("QUANTITY") ? rowData.get("QUANTITY") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(2);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				
				
				value = rowData.containsKey("BONUS") ? rowData.get("BONUS") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(3);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("UNIT_RATE") ? rowData.get("UNIT_RATE") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(4);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				
				value = rowData.containsKey("DISCOUNT") ? rowData.get("DISCOUNT") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(5);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
				//sheet.autoSizeColumn(9);
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("TOTAL_VALUE") ? rowData.get("TOTAL_VALUE") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				
				
			}
		}

	}


}
