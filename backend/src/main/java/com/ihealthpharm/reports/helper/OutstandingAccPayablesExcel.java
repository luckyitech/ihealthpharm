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
import org.apache.poi.ss.usermodel.FormulaEvaluator;
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
public class OutstandingAccPayablesExcel extends ReportsExcelUtility{


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
		
		
		Map<String, List<Map<String, Object>>> supplierMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("SUPPLIER_NAME")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(supplierMap)) { 
			
			for(String supplier :supplierMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> supplierAccPayList =supplierMap.get(supplier);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,supplierAccPayList,supplier, currentRow); 
			}
			generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}
		
		writeToFile(workbook, responseFile);	 
	}

	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		int currentRow = sheet.getLastRowNum();
		
		DecimalFormat df=new DecimalFormat("0.00");
		double totalAmountPaid;
		double totalAmountToBePaid;

		totalAmountPaid = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT_PAID")?String.valueOf(mapper.get("TOTAL_AMOUNT_PAID")):"0")).sum(); 
		totalAmountToBePaid = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT_TO_BE_PAID")?String.valueOf(mapper.get("TOTAL_AMOUNT_TO_BE_PAID")):"0")).sum(); 

		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		
		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);
	
		cell.setCellValue("");
		cell1.setCellValue("");
		
		cell = dataRow.createCell(16);
		cell1=dataRow1.createCell(16);
		
		cell.setCellValue("Total Amount Paid : ");
		cell1.setCellValue("Total Amount To Be Paid: ");
		
		cell = dataRow.createCell(17);
		cell1=dataRow1.createCell(17);
		
		cell.setCellValue(totalAmountPaid);
		cell1.setCellValue(totalAmountToBePaid);
	
	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> supplierAccPayList, String supplier, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		
		// populate Date
		if (!ObjectUtils.isEmpty(supplierAccPayList)) {
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(1);
			cell.setCellValue("SUPPLIER NAME");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(2);
			cell.setCellValue("PAYMENT NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(3);
			cell.setCellValue("SOURCE REF");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(4);
			cell.setCellValue("INVOICE NO");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(5);
			cell.setCellValue("PAYMENT DATE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("INV AMT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("CREDIT DAYS");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("STATUS");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(9);
			cell.setCellValue("AMOUNT PAID");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(10);
			cell.setCellValue("AMOUNT TO BE PAID");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(11);
			cell.setCellValue("PAYMENT STATUS");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(12);
			cell.setCellValue("SOURCE TYP");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(13);
			cell.setCellValue("APPROVED BY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(14);
			cell.setCellValue("APPROVED DATE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(15);
			cell.setCellValue("CREATED BY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(16);
			cell.setCellValue("CREATION TS");
			cell.setCellStyle(headerStyle);	
			
			Row displayRow = sheet.createRow(headRow++);
			
			for (Map<String, Object> rowData : supplierAccPayList) {
				
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("SUPPLIER_NAME") ? rowData.get("SUPPLIER_NAME") : "";
				headCell.setCellValue("Supplier Name  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(supplierAccPayList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				

				value = rowData.containsKey("SUPPLIER_NAME") ? rowData.get("SUPPLIER_NAME") : "";
				//sheet.autoSizeColumn(1);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PAYMENT_NO") ? rowData.get("PAYMENT_NO") : "";
				//sheet.autoSizeColumn(2);
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SOURCE_REF") ? rowData.get("SOURCE_REF") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("INVOICE_NO") ? rowData.get("INVOICE_NO") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PAYMENT_DATE") ? rowData.get("PAYMENT_DATE") : "";
				//sheet.autoSizeColumn(4);
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("INVOICE_AMOUNT") ? rowData.get("INVOICE_AMOUNT") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREDIT_DAYS") ? rowData.get("CREDIT_DAYS") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(7);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("STATUS") ? rowData.get("STATUS") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(8);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("TOTAL_AMOUNT_PAID") ? rowData.get("TOTAL_AMOUNT_PAID") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("TOTAL_AMOUNT_TO_BE_PAID") ? rowData.get("TOTAL_AMOUNT_TO_BE_PAID") : "";
				//sheet.autoSizeColumn(7);
				cell = dataRow.createCell(10);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(11);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SOURCE_TYPE") ? rowData.get("SOURCE_TYPE") : "";
				//sheet.autoSizeColumn(9);
				cell = dataRow.createCell(12);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("APPROVED_BY") ? rowData.get("APPROVED_BY") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(13);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("APPROVED_DATE") ? rowData.get("APPROVED_DATE") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(14);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(15);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREATION_TS") ? rowData.get("CREATION_TS") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(16);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
			}
				
				int currentRow = sheet.getLastRowNum();
				
				double totalAmountPaid;
				double totalAmountToBePaid;

				totalAmountPaid = supplierAccPayList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT_PAID")?String.valueOf(mapper.get("TOTAL_AMOUNT_PAID")):"0")).sum(); 
				totalAmountToBePaid = supplierAccPayList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT_TO_BE_PAID")?String.valueOf(mapper.get("TOTAL_AMOUNT_TO_BE_PAID")):"0")).sum(); 

				Row dataRow1 = sheet.createRow(currentRow+2);
				Row dataRow2=sheet.createRow(currentRow+3);
				
				Cell cell1 = dataRow1.createCell(0);
				Cell cell2=dataRow2.createCell(0);
			
				cell1.setCellValue("");
				cell2.setCellValue("");
				
				cell1= dataRow1.createCell(16);
				cell2=dataRow2.createCell(16);
				
				cell1.setCellValue("Total Amount Paid : ");
				cell2.setCellValue("Total Amount To be Paid : ");
				
				cell1 = dataRow1.createCell(17);
				cell2=dataRow2.createCell(17);
				
				cell1.setCellValue(totalAmountPaid);
				cell2.setCellValue(totalAmountToBePaid);
				
		}

	}
}
