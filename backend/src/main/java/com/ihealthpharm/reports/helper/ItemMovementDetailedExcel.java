package com.ihealthpharm.reports.helper;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

@Component
public class ItemMovementDetailedExcel extends ReportsExcelUtility{
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

		Map<String, List<Map<String, Object>>> salesByPersonMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("ITEM_NM")));			


		setHeader(workbook,sheet,model);


		if(!ObjectUtils.isEmpty(salesByPersonMap)) { 

			for(String itemName :salesByPersonMap.keySet()) {	

				List<Map<String, Object>> salesByPersonList =salesByPersonMap.get(itemName);

				Map<String, List<Map<String, Object>>> salesByExpiryMap = salesByPersonList.stream()
						.collect(Collectors.groupingBy(map -> (String) map.get("EXPIRY_DT")));			

				for(String expiryDate :salesByExpiryMap.keySet()) {
					int currentRow = sheet.getLastRowNum();
					List<Map<String, Object>> salesByExpiryList =salesByExpiryMap.get(expiryDate);

					createSalesByPersonTable(sheet, responseFile, borderStyle, headerStyle,salesByExpiryList, expiryDate, currentRow); 
				}
			}

		}

		writeToFile(workbook, responseFile);	 

	}

	private void createSalesByPersonTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle headerStyle, List<Map<String, Object>> salesProfitList,String expiryDate,int rowNum) {

		rowNum = rowNum + 3;
		int headRow=rowNum-2;

		// populate Date
		if (!ObjectUtils.isEmpty(salesProfitList)) {

			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("S.NO");
			cell.setCellStyle(headerStyle);


			cell = headerRow.createCell(1);
			cell.setCellValue("ITEM NAME");
			cell.setCellStyle(headerStyle);


			cell = headerRow.createCell(2);
			cell.setCellValue("SUPPLIER");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(3);
			cell.setCellValue("BILL CODE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(4);
			cell.setCellValue("INVOICE NO");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(5);
			cell.setCellValue("QUANITY");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(6);
			cell.setCellValue("QTY LEFT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(7);
			cell.setCellValue("UNIT SALE RATE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(8);
			cell.setCellValue("SALE PRICE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(9);
			cell.setCellValue("UNIT PURCHASE RATE");
			cell.setCellStyle(headerStyle);	


			cell = headerRow.createCell(10);
			cell.setCellValue("PURCHASE PRICE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(11);
			cell.setCellValue("EXPIRY DT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(12);
			cell.setCellValue("ENTRY TYPE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(13);
			cell.setCellValue("CREATED BY");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(14);
			cell.setCellValue("LAST UPDATE TS");
			cell.setCellStyle(headerStyle);	



			Row displayRow = sheet.createRow(headRow++);
			int closingStock=0;
			int leftStock=0;
			String openingStock=null;


			if((String.valueOf(salesProfitList.get(0).get("ENTRY_TYPE")).equals("Sales Billing"))) {
				openingStock=String.valueOf(salesProfitList.get(0).get("OPENING_STOCK"));
				closingStock=Integer.parseInt(openingStock);
			}else {
				openingStock=String.valueOf(salesProfitList.get(0).get("QUANTITY"));
			}
			for(int i=0;i<salesProfitList.size();i++) {

				//System.out.println(String.valueOf(salesProfitList.get(i).get("ENTRY_TYPE")));

				if((String.valueOf(salesProfitList.get(i).get("ENTRY_TYPE")).equals("Sales Billing")))	{
					//System.out.println("Sales Billing enter");
					closingStock=closingStock-Integer.parseInt(String.valueOf((salesProfitList.get(i).get("QUANTITY"))));

				}
				else{
					if((String.valueOf(salesProfitList.get(i).get("ENTRY_TYPE")).equals("Stock Take"))) {

						closingStock=Integer.parseInt(String.valueOf((salesProfitList.get(i).get("QUANTITY"))));
					}else {
						closingStock=closingStock+Integer.parseInt(String.valueOf((salesProfitList.get(i).get("QUANTITY"))));
					}
				}

			}

			leftStock=Integer.parseInt(openingStock);
			for (Map<String, Object> rowData : salesProfitList) {


				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				Cell headCell = displayRow.createCell(0);
				Object value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				headCell.setCellValue("Item :   ");
				headCell=displayRow.createCell(1);
				headCell.setCellValue(String.valueOf(value));

				Cell headCell1 = displayRow.createCell(2);
				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				headCell1.setCellValue("Exp.Dt  :   ");
				headCell1=displayRow.createCell(3);
				headCell1.setCellValue(String.valueOf(value));

				Cell headCell2 = displayRow.createCell(4);
				//value = rowData.containsKey("OPENING_STOCK") ? rowData.get("QUANTITY") : "";
				headCell2.setCellValue("O.Stock  :   ");
				headCell2=displayRow.createCell(5);
				headCell2.setCellValue(String.valueOf(openingStock));

				Cell headCell3 = displayRow.createCell(6);
				//value = rowData.containsKey("CLOSING_STOCK") ? rowData.get("CLOSING_STOCK") : "";
				headCell3.setCellValue("C.Stock  :   ");
				headCell1=displayRow.createCell(7);
				headCell1.setCellValue(String.valueOf(closingStock));

				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(salesProfitList.indexOf(rowData) + 1);
				cell = dataRow.createCell(0);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				cell = dataRow.createCell(1);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("SUPPLIER_NM") ? rowData.get("SUPPLIER_NM") : "";
				cell = dataRow.createCell(2);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
				cell = dataRow.createCell(3);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("INVOICE_NO") ? rowData.get("INVOICE_NO") : "";
				cell = dataRow.createCell(4);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("QUANTITY") ? rowData.get("QUANTITY") : "";
				cell = dataRow.createCell(5);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);


				if(Objects.nonNull(openingStock)) {


					if(rowData.get("ENTRY_TYPE").equals("Sales Billing")) {

						leftStock=leftStock-Integer.parseInt(String.valueOf(rowData.get("QUANTITY")));


					}else if(rowData.get("ENTRY_TYPE").equals("Sales Canceling") ||
							rowData.get("ENTRY_TYPE").equals("Invoice Addition")||
							rowData.get("ENTRY_TYPE").equals("Sales Return")||
							rowData.get("ENTRY_TYPE").equals("Purchase Return")) {

						leftStock=leftStock+Integer.parseInt(String.valueOf(rowData.get("QUANTITY")));

					}else {
						leftStock=Integer.parseInt(String.valueOf(rowData.get("QUANTITY")));
					}

					value = rowData.containsKey("QUANTITY") ? String.valueOf(leftStock) : "";
					cell = dataRow.createCell(6);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
				}else {

				}


				value = rowData.containsKey("UNIT_SALE_RATE") ? rowData.get("UNIT_SALE_RATE") : "";
				cell = dataRow.createCell(7);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("SALE_PRICE") ? rowData.get("SALE_PRICE") : "";
				cell = dataRow.createCell(8);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("UNIT_PURCHASE_RATE") ? rowData.get("UNIT_PURCHASE_RATE") : "";
				cell = dataRow.createCell(9);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("PURCHASE_PRICE") ? rowData.get("PURCHASE_PRICE") : "";
				cell = dataRow.createCell(10);
				cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				cell = dataRow.createCell(11);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("ENTRY_TYPE") ? rowData.get("ENTRY_TYPE") : "";
				cell = dataRow.createCell(12);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("LAST_UPDATE_USER") ? rowData.get("LAST_UPDATE_USER") : "";
				cell = dataRow.createCell(13);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("LAST_UPDATE_TS") ? rowData.get("LAST_UPDATE_TS") : "";
				cell = dataRow.createCell(14);
				cell.setCellValue(String.valueOf(value));
				cell.setCellStyle(borderStyle);



			}
			int currentRow = sheet.getLastRowNum();

			long totalSales = salesProfitList.stream() .filter(x -> (x.containsValue("Sales Billing"))||(x.containsValue("Sales Update"))||(x.containsValue("Sales Maintenance (+)"))) .count();
			long totalInv = salesProfitList.stream() .filter(x -> (x.containsValue("Purchase"))||(x.containsValue("Purchase Update"))||(x.containsValue("Invoice Addition"))) .count();
			long totalSalesCancel = salesProfitList.stream() .filter(x -> x.containsValue("Sales Canceling")).count();
			long totalStockTake = salesProfitList.stream() .filter(x -> (x.containsValue("Stock Take"))||(x.containsValue("Stock Adjustment"))) .count();
			long totalStockAdd = salesProfitList.stream() .filter(x -> (x.containsValue("Stock"))||(x.containsValue("Stock Update"))||(x.containsValue("Stock Addition"))) .count();
			long totalPurReturn = salesProfitList.stream() .filter(x -> (x.containsValue("Purchase Return"))||x.containsValue("Purchase Delete")) .count();
			long totalSalesRetrun = salesProfitList.stream() .filter(x -> x.containsValue("Sales Return")).count();

			Row dataRow = sheet.createRow(currentRow+2);
			Row dataRow1=sheet.createRow(currentRow+3);
			Row dataRow2=sheet.createRow(currentRow+4);
			Row dataRow3=sheet.createRow(currentRow+5);
			Row dataRow4=sheet.createRow(currentRow+6);
			Row dataRow5=sheet.createRow(currentRow+7);
			Row dataRow6=sheet.createRow(currentRow+8);

			cell = dataRow.createCell(0);
			Cell cell1=dataRow1.createCell(0);
			Cell cell2=dataRow2.createCell(0);
			Cell cell3=dataRow3.createCell(0);
			Cell cell4=dataRow4.createCell(0);
			Cell cell5=dataRow5.createCell(0);
			Cell cell6=dataRow6.createCell(0);

			cell.setCellValue("");
			cell1.setCellValue("");
			cell2.setCellValue("");
			cell3.setCellValue("");
			cell4.setCellValue("");
			cell5.setCellValue("");
			cell6.setCellValue("");


			cell = dataRow.createCell(14);
			cell1 = dataRow1.createCell(14);
			cell2 = dataRow2.createCell(14);
			cell3 = dataRow3.createCell(14);
			cell4 = dataRow4.createCell(14);
			cell5 = dataRow5.createCell(14);
			cell6 = dataRow6.createCell(14);

			if(salesProfitList.stream() .filter(x -> (x.containsValue("Sales Billing"))||(x.containsValue("Sales Update"))||(x.containsValue("Sales Maintenance (+)"))) .count()>0) {

				cell.setCellValue("Total Sales");
			}
			if(salesProfitList.stream() .filter(x -> x.containsValue("Sales Canceling")).count()>0) {

				cell1.setCellValue("Total Sales Cancel");
			}

			if(salesProfitList.stream() .filter(x -> (x.containsValue("Purchase"))||(x.containsValue("Purchase Update"))||(x.containsValue("Invoice Addition"))) .count()>0) {

				cell2.setCellValue("Total Invoice");
			}
			if(salesProfitList.stream() .filter(x -> (x.containsValue("Purchase Return"))||x.containsValue("Purchase Delete")) .count()>0) {

				cell3.setCellValue("Total Purchase Return");
			}
			if(salesProfitList.stream() .filter(x -> (x.containsValue("Stock Take"))||(x.containsValue("Stock Adjustment"))) .count()>0) {

				cell4.setCellValue("Total Stock Take");
			}
			if(salesProfitList.stream() .filter(x -> (x.containsValue("Stock"))||(x.containsValue("Stock Update"))||(x.containsValue("New Stock Addition"))) .count()>0) {

				cell5.setCellValue("Total Stock Addition");
			}
			if(salesProfitList.stream() .filter(x -> x.containsValue("Sales Return")).count()>0) {

				cell6.setCellValue("Total Sales Return");
			}

			cell = dataRow.createCell(15);
			cell1 = dataRow1.createCell(15);
			cell2 = dataRow2.createCell(15);
			cell3 = dataRow3.createCell(15);
			cell4 = dataRow4.createCell(15);
			cell5 = dataRow5.createCell(15);
			cell6 = dataRow6.createCell(15);

			if(salesProfitList.stream() .filter(x -> (x.containsValue("Sales Billing"))||(x.containsValue("Sales Update"))||(x.containsValue("Sales Maintenance (+)"))) .count()>0) {

				cell.setCellValue(totalSales);
			}
			if(salesProfitList.stream() .filter(x -> x.containsValue("Sales Canceling")).count()>0) {

				cell1.setCellValue(totalSalesCancel);
			}
			if(salesProfitList.stream() .filter(x -> (x.containsValue("Purchase"))||(x.containsValue("Purchase Update"))||(x.containsValue("Invoice Addition"))) .count()>0) {

				cell2.setCellValue(totalInv);
			}
			if(salesProfitList.stream() .filter(x -> (x.containsValue("Purchase Return"))||x.containsValue("Purchase Delete")) .count()>0) {

				cell3.setCellValue(totalPurReturn);
			}
			if(salesProfitList.stream() .filter(x -> (x.containsValue("Stock Take"))||(x.containsValue("Stock Adjustment"))) .count()>0) {

				cell4.setCellValue(totalStockTake);
			}
			if(salesProfitList.stream() .filter(x -> (x.containsValue("Stock"))||(x.containsValue("Stock Update"))||(x.containsValue("New Stock Addition"))) .count()>0) {

				cell5.setCellValue(totalStockAdd);
			}
			if(salesProfitList.stream() .filter(x -> x.containsValue("Sales Return")).count()>0) {

				cell6.setCellValue(totalSalesRetrun);
			}

		}

	}
}
