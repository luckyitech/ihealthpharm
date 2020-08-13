package com.ihealthpharm.reports.helper;

import java.io.File;
import java.text.DateFormat;
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
public class DeliveryNoteExcel extends ReportsExcelUtility{

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

		setHeader(workbook,sheet,model);
		int currentRow = sheet.getLastRowNum();

		Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
		addBillDetails(sheet, responseFile,borderStyle,model,responseList);
		createSupplierTable(sheet, responseFile, borderStyle, headerStyle,responseList,currentRow,dataMap);

		addBillTotals(sheet,responseFile,borderStyle,model,responseList);
		writeToFile(workbook, responseFile);

	}

	private void addBillTotals(SXSSFSheet sheet, File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {
		
		int currentRow = sheet.getLastRowNum();
		int totalItems = responseList.size();

		double totalAmount = Double.parseDouble(String.valueOf(responseList.get(0).get("TOTAL_AMOUNT")));//responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT")?String.valueOf(mapper.get("TOTAL_AMOUNT")):"0.0")).sum(); 
		Double totalQty = Double.parseDouble(String.valueOf(responseList.get(0).get("TOTAL_QTY")));//responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_QTY")?String.valueOf(mapper.get("TOTAL_QTY")):"0.0")).sum(); 
		double totalDiscount = Double.parseDouble(String.valueOf(responseList.get(0).get("OVERALL_DISCOUNT")));//responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("OVERALL_DISCOUNT")?String.valueOf(mapper.get("EFFECTIVE_OVERALL_DISCOUNT")):"0.0")).sum(); 

		double totalVat = Double.parseDouble(String.valueOf(responseList.get(0).get("VAT_AMT")));//responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("EFFECTIVE_VAT")?String.valueOf(mapper.get("VAT")):"0.0")).sum();

		//double totalNetAmount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("SALE_AMOUNT")?String.valueOf(mapper.get("SALE_AMOUNT")):"0.0")).sum();
		double totalNetAmount =Double.parseDouble(String.valueOf(responseList.get(0).get("NET_AMOUNT")));
		double balanceAmt =Double.parseDouble(String.valueOf(responseList.get(0).get("BALANCE_AMOUNT")));
		double paidAmt =Double.parseDouble(String.valueOf(responseList.get(0).get("PAID_AMOUNT")));
		String servBy = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("FIRST_NM"));
		String printedBy = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("SERVED_BY"));
		
		
		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow+3);
		Row dataRow2=sheet.createRow(currentRow+4);
		Row dataRow3=sheet.createRow(currentRow+5);
		Row dataRow4=sheet.createRow(currentRow+6);
		Row dataRow5=sheet.createRow(currentRow+7);
		Row dataRow6=sheet.createRow(currentRow+8);
		Row dataRow7=sheet.createRow(currentRow+9);
		Row dataRow8=sheet.createRow(currentRow+10);
		Row dataRow9=sheet.createRow(currentRow+11);
		Row dataRow10=sheet.createRow(currentRow+12);

		Cell cell_totItems = dataRow.createCell(0);
		Cell cell_totQty=dataRow1.createCell(0);
		Cell cell_vatAmt=dataRow2.createCell(0);
		Cell cell_balAmt=dataRow3.createCell(0);
		Cell cell_servBy=dataRow4.createCell(0);
		Cell cell_printBy=dataRow5.createCell(0);
		Cell cell_totAmt=dataRow.createCell(3);
		Cell cell_totDisc=dataRow1.createCell(3);
		Cell cell_netAmt=dataRow2.createCell(3);
		Cell cell_paidAmt=dataRow3.createCell(3);
		

		cell_totItems.setCellValue("");
		cell_totQty.setCellValue("");
		cell_vatAmt.setCellValue("");
		cell_balAmt.setCellValue("");
		
		cell_totAmt.setCellValue("Total Amt");
		cell_totDisc.setCellValue("Docpharma Sign");
		cell_netAmt.setCellValue("Courier Sign");
		cell_paidAmt.setCellValue("Customer Sign");
		

		cell_totItems = dataRow.createCell(1);
		cell_totQty=dataRow1.createCell(1);
		cell_vatAmt = dataRow2.createCell(1);
		cell_balAmt=dataRow3.createCell(1);
		cell_servBy=dataRow4.createCell(1);
		cell_printBy=dataRow5.createCell(1);
		cell_totAmt=dataRow.createCell(4);
		cell_totDisc=dataRow1.createCell(4);
		cell_netAmt=dataRow2.createCell(4);
		cell_paidAmt=dataRow3.createCell(4);
		

		
		cell_totAmt.setCellValue(String.valueOf(totalAmount));
		cell_totDisc.setCellValue(String.valueOf(""));
		cell_netAmt.setCellValue(String.valueOf(""));
		cell_paidAmt.setCellValue(String.valueOf(""));
		
		
	}

	private void addBillDetails(SXSSFSheet sheet, File responseFile, CellStyle borderStyle, ReportsMappingModel model,
			List<Map<String, Object>> responseList) {


		DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
		String billDate="";

		if(responseList.get(0).get("LAST_UPDATE_TS")==null) {

		}else {
			billDate = f.format((ObjectUtils.isEmpty(responseList))?"":responseList.get(0).get("LAST_UPDATE_TS"));
		}
		//String billDate = f.format((ObjectUtils.isEmpty(responseList))?"":responseList.get(0).get("LAST_UPDATE_TS"));
		String billCode = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("BILL_CODE"));
		String customerName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("CUSTOMER_NM"));
		String doctorName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("DOCTOR_NM"));

		int currentRow = sheet.getLastRowNum();
		int currentRow1 = sheet.getLastRowNum();
		int currentRow2 = sheet.getLastRowNum();

		Row dataRow = sheet.createRow(currentRow+2);
		Row dataRow1=sheet.createRow(currentRow1+3);
		Row dataRow2=sheet.createRow(currentRow2+4);
		
		Cell cell_bill = dataRow.createCell(0);
		Cell cell_dt=dataRow1.createCell(0);
		Cell cell_cust=dataRow2.createCell(0);
		Cell cell_doc=dataRow.createCell(3);
		

		cell_bill.setCellValue("Bill");
		cell_dt.setCellValue("Bill Date");
		cell_cust.setCellValue("Customer");
		cell_doc.setCellValue("Doctor");
		

		cell_bill = dataRow.createCell(1);
		cell_dt=dataRow1.createCell(1);
		cell_cust = dataRow2.createCell(1);
		cell_doc=dataRow.createCell(4);
		
		cell_bill.setCellValue(String.valueOf(billCode));
		cell_dt.setCellValue(String.valueOf(billDate));
		cell_cust.setCellValue(String.valueOf(customerName));
		cell_doc.setCellValue(String.valueOf(doctorName));
		

	}

	private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> accountReceivablesDetails,int rowNum,Map<String,Object> inputJson) {

		rowNum = rowNum + 7;
//		int headRow=rowNum-2;
		
		Row headerRow = sheet.createRow(rowNum++);
		Cell cell = headerRow.createCell(0);
		cell.setCellValue("S.NO");
		cell.setCellStyle(headerStyle);


		cell = headerRow.createCell(1);
		cell.setCellValue("ITEM NAME");
		cell.setCellStyle(headerStyle);


		cell = headerRow.createCell(2);
		cell.setCellValue("QTY");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(3);
		cell.setCellValue("TAX");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(4);
		cell.setCellValue("PRICE");
		cell.setCellStyle(headerStyle);	

		cell = headerRow.createCell(5);
		cell.setCellValue("TOTAL");
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

			value = rowData.containsKey("SALE_QTY") ? rowData.get("SALE_QTY") : "";
			//sheet.autoSizeColumn(2);
			cell = dataRow.createCell(2);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("TAX") ? rowData.get("TAX") : "";
			//sheet.autoSizeColumn(3);
			cell = dataRow.createCell(3);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("PRICE") ? rowData.get("PRICE") : "";
			//sheet.autoSizeColumn(4);
			cell = dataRow.createCell(4);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);

			value = rowData.containsKey("SALE_AMOUNT") ? rowData.get("SALE_AMOUNT") : "";
			//sheet.autoSizeColumn(5);
			cell = dataRow.createCell(5);
			cell.setCellValue(String.valueOf(value));
			cell.setCellStyle(borderStyle);


		}

	}

}
