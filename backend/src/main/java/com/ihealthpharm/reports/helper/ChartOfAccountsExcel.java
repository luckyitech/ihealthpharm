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
public class ChartOfAccountsExcel extends ReportsExcelUtility{

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
		
		
		Map<String, List<Map<String, Object>>> chartOfAccountMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("ACCOUNT_TYPE")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(chartOfAccountMap)) { 
			
			for(String chartOfAccount :chartOfAccountMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> chartOfAccountList =chartOfAccountMap.get(chartOfAccount);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,chartOfAccountList, chartOfAccount, currentRow); 
			}
		}
		
		writeToFile(workbook, responseFile);	 
	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>>  chartOfAccountList, String chartOfAccount, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(chartOfAccountList)) {
			
			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.No");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(1);
			cell.setCellValue("ACCOUNT NO");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(2);
			cell.setCellValue("ACCOUNT NAME");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(3);
			cell.setCellValue("ACCOUNT TYPE");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(4);
			cell.setCellValue("INITIAL");
			cell.setCellStyle(headerStyle);
			
			
			cell = headerRow.createCell(5);
			cell.setCellValue("MAXIMUM");
			cell.setCellStyle(headerStyle);	
			
			cell = headerRow.createCell(6);
			cell.setCellValue("TRANSACTION LIMIT");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(7);
			cell.setCellValue("TOTAL LIMIT");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(8);
			cell.setCellValue("CURRENT BALANCE");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(9);
			cell.setCellValue("AS OF DATE");
			cell.setCellStyle(headerStyle);
			
			
			Row displayRow = sheet.createRow(headRow++);
			
			for (Map<String, Object> rowData : chartOfAccountList) {
				
				DecimalFormat df=new DecimalFormat("0.00");
				
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("ACCOUNT_TYPE") ? rowData.get("ACCOUNT_TYPE") : "";
				headCell.setCellValue("Account Type  :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));
				
				
				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(chartOfAccountList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ACCOUNT_NO") ? rowData.get("ACCOUNT_NO") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ACCOUNT_NAME") ? rowData.get("ACCOUNT_NAME") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("ACCOUNT_TYPE") ? rowData.get("ACCOUNT_TYPE") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("INITIAL") ? rowData.get("INITIAL") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
							
				
				value = rowData.containsKey("MAXIMUM") ? rowData.get("MAXIMUM") : "";
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("TRANSACTION_LIMIT") ? rowData.get("TRANSACTION_LIMIT") : "";
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("TOTAL_LIMIT") ? df.format(rowData.get("TOTAL_LIMIT")) : "";
				cell = dataRow.createCell(7);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CURRENT_BALANCE") ? rowData.get("CURRENT_BALANCE") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("AS_OF_DATE") ? rowData.get("AS_OF_DATE") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

			}
			
				
		}

	}
}
