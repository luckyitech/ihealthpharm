package com.ihealthpharm.reports.helper;

import java.io.File;
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
public class SalesByProductSummaryExcel extends ReportsExcelUtility {

	
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
		
		
		Map<String, List<Map<String, Object>>> salesProductMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("ITEM_NM")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(salesProductMap)) { 
			
			for(String productSummary :salesProductMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> supplierList =salesProductMap.get(productSummary);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,supplierList, productSummary, currentRow); 
			}
			generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}
		
		writeToFile(workbook, responseFile);	 
	}

	private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		int currentRow = sheet.getLastRowNum();
		
		double totalAmount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT")?String.valueOf(mapper.get("TOTAL_AMOUNT")):"0")).sum(); 
		int quantity = responseList.stream().mapToInt(mapper->Integer.parseInt(mapper.containsKey("SALE_QTY")?String.valueOf(mapper.get("SALE_QTY")):"0")).sum(); 
		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		
		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);
	
		cell.setCellValue("");
		cell1.setCellValue("");
		
		cell = dataRow.createCell(9);
		cell1=dataRow1.createCell(9);
		
		cell.setCellValue("Overall Quantity : ");
		cell1.setCellValue("Overall Amount : ");
		
		cell = dataRow.createCell(10);
		cell1=dataRow1.createCell(10);
		
		cell.setCellValue(quantity);
		cell1.setCellValue(totalAmount);
	
	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>>  productList, String productSummary, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(productList)) {
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.No");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(1);
			cell.setCellValue("PRODUCT NAME");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(2);
			cell.setCellValue("BILL NO");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(3);
			cell.setCellValue("DATE");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(4);
			cell.setCellValue("CUSTOMER");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(5);
			cell.setCellValue("BATCH NO");
			cell.setCellStyle(headerStyle);	
			
			
			cell = headerRow.createCell(6);
			cell.setCellValue("EXPIRY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(7);
			cell.setCellValue("UNIT PRICE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(8);
			cell.setCellValue("QTY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(9);
			cell.setCellValue("QTY FREE");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(10);
			cell.setCellValue("S DISC%");
			cell.setCellStyle(headerStyle);	
			

			cell = headerRow.createCell(11);
			cell.setCellValue("TOTAL AMT");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(12);
			cell.setCellValue("CREATED BY");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(13);
			cell.setCellValue("MODIFIED BY");
			cell.setCellStyle(headerStyle);	
			
			Row displayRow = sheet.createRow(headRow++);
			
			for (Map<String, Object> rowData : productList) {
				
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				headCell.setCellValue("Product Name  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(productList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
							
				
				value = rowData.containsKey("BILL_DATE") ? rowData.get("BILL_DATE") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("CUSTOMER_NM") ? rowData.get("CUSTOMER_NM") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				cell = dataRow.createCell(6);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("UNIT_SALE_PRICE") ? rowData.get("UNIT_SALE_PRICE") : "";
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("SALE_QTY") ? rowData.get("SALE_QTY") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("QTY_FREE") ? rowData.get("QTY_FREE") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("DISC_PER") ? rowData.get("DISC_PER") : "";
				cell = dataRow.createCell(10);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("TOTAL_AMOUNT") ? rowData.get("TOTAL_AMOUNT") : "";
				cell = dataRow.createCell(11);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
				cell = dataRow.createCell(12);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("MODIFIED_BY") ? rowData.get("MODIFIED_BY") : "";
				cell = dataRow.createCell(13);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
			}
				
				int currentRow = sheet.getLastRowNum();
				
				double totalAmount = productList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT")?String.valueOf(mapper.get("TOTAL_AMOUNT")):"0")).sum(); 
				int quantity = productList.stream().mapToInt(mapper->Integer.parseInt(mapper.containsKey("SALE_QTY")?String.valueOf(mapper.get("SALE_QTY")):"0")).sum(); 
				
				Row dataRow1 = sheet.createRow(currentRow+2);
				Row dataRow2=sheet.createRow(currentRow+3);
				
				Cell cell1 = dataRow1.createCell(0);
				Cell cell2=dataRow2.createCell(0);
			
				cell1.setCellValue("");
				cell2.setCellValue("");
				
				cell1= dataRow1.createCell(9);
				cell2=dataRow2.createCell(9);
				
				cell1.setCellValue("Total Quantity : ");
				cell2.setCellValue("Total Amount : ");
				
				cell1 = dataRow1.createCell(10);
				cell2=dataRow2.createCell(10);
				
				cell1.setCellValue(quantity);
				cell2.setCellValue(totalAmount);
				
		}

	}
}
