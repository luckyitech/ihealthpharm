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

import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component
public class SupplierVatExcel extends ReportsExcelUtility{


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


		Map<String, List<Map<String, Object>>> purchaseInvoiceDetailsMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("PURCHASE_ORDER_NO")));			
		
		
		setHeader(workbook,sheet,model);
		
		
		if(!ObjectUtils.isEmpty(purchaseInvoiceDetailsMap)) { 
			
			for(String poNo :purchaseInvoiceDetailsMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> purchaseInvoiceDetails = purchaseInvoiceDetailsMap.get(poNo);
				createSupplierTable(sheet, responseFile, borderStyle, headerStyle,purchaseInvoiceDetails, poNo, currentRow); 
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

		double vatTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("VAT_AMT")?String.valueOf(mapper.get("VAT_AMT")):"0")).sum(); 



		String sub=df.format(grossTotal);
		String vat=df.format(vatTotal);

		Double subTotal=Double.parseDouble(sub);

		Double totalVat=Double.parseDouble(vat);

		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);


		Cell cell = dataRow.createCell(0);
		Cell cell1=dataRow1.createCell(0);

		cell.setCellValue("");
		cell1.setCellValue("");

		//cell.setCellStyle(borderStyle);

		cell = dataRow.createCell(12);
		cell1=dataRow1.createCell(12);

		cell.setCellValue("SUB TOTAL : ");
		cell1.setCellValue("VAT TOTAL : ");

		//cell.setCellStyle(borderStyle);



		cell = dataRow.createCell(13);
		cell1=dataRow1.createCell(13);

		cell.setCellValue(subTotal);
		cell1.setCellValue(totalVat);

		//cell.setCellStyle(borderStyle);


	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> purchaseInvoiceDetails, String itemName, int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(purchaseInvoiceDetails)) {


			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);

			cell = headerRow.createCell(1);
			cell.setCellValue("QTN NO");
			cell.setCellStyle(headerStyle);
			
			cell = headerRow.createCell(2);
			cell.setCellValue("CST NO");
			cell.setCellStyle(headerStyle);


			cell = headerRow.createCell(3);
			cell.setCellValue("ITEM NAME");
			cell.setCellStyle(headerStyle);


			cell = headerRow.createCell(4);
			cell.setCellValue("QTY");
			cell.setCellStyle(headerStyle);	

			
			cell = headerRow.createCell(5);
			cell.setCellValue("BONUS");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(6);
			cell.setCellValue("UNIT PRICE");
			cell.setCellStyle(headerStyle);	

			
			cell = headerRow.createCell(7);
			cell.setCellValue("DISCOUNT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(8);
			cell.setCellValue("DISC AMT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(9);
			cell.setCellValue("VAT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(10);
			cell.setCellValue("NET AMOUNT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(11);
			cell.setCellValue("APPROVED BY");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(12);
			cell.setCellValue("CREATED BY");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(13);
			cell.setCellValue("MODIFIED BY");
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

				value = rowData.containsKey("QUOTATION_NO") ? rowData.get("QUOTATION_NO") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);
				
				value = rowData.containsKey("CST_NO") ? rowData.get("CST_NO") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				//sheet.autoSizeColumn(1);
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);



				value = rowData.containsKey("QUANTITY") ? rowData.get("QUANTITY") : "";
				//sheet.autoSizeColumn(3);
				cell = dataRow.createCell(4);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);



				value = rowData.containsKey("BONUS") ? rowData.get("BONUS") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(5);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("UNIT_RATE") ? rowData.get("UNIT_RATE") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(6);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("DISCOUNT") ? rowData.get("DISCOUNT") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("DISC_AMT") ? rowData.get("DISC_AMT") : "";
				//sheet.autoSizeColumn(8);
				cell = dataRow.createCell(8);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
				//sheet.autoSizeColumn(9);
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("TOTAL_VALUE") ? rowData.get("TOTAL_VALUE") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(10);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("APP_BY") ? rowData.get("APP_BY") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(11);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("EMP_NM") ? rowData.get("EMP_NM") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(12);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("EMP_MODIFIED") ? rowData.get("EMP_MODIFIED") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(13);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

			}
		}

	}


}
