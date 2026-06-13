
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
	public class CustomerExpenditureExcel extends ReportsExcelUtility{
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
			
			
//			
//			Map<String, List<Map<String, Object>>> salesProductMap = responseList.stream()
//					.collect(Collectors.groupingBy(map -> (String) map.get("CUSTOMER_NAME")));	
			
			setHeader(workbook,sheet,model);
			int currentRow = sheet.getLastRowNum();
			Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
//			if(!ObjectUtils.isEmpty(salesProductMap)) { 
//				
//				for(String productSummary :salesProductMap.keySet()) {	
//					int currentRow = sheet.getLastRowNum();
//					List<Map<String, Object>> supplierList =salesProductMap.get(productSummary);
//					createSupplierTable(sheet, responseFile, borderStyle, headerStyle,supplierList, productSummary, currentRow,dataMap);
					createSupplierTable(sheet, responseFile, borderStyle, headerStyle,responseList,currentRow,dataMap);
			//	}
				generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		//	}
			
			writeToFile(workbook, responseFile);
			 
		}
		private void generateTotalTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle, ReportsMappingModel model,
				List<Map<String, Object>> responseList) {
			DecimalFormat df=new DecimalFormat("0.00");
			int currentRow = sheet.getLastRowNum();
		
			double TotalAmtReceived = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT")?String.valueOf(mapper.get("TOTAL_AMOUNT")):"0")).sum(); 
			double TotalAmtPaid = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("PAID_AMOUNT")?String.valueOf(mapper.get("PAID_AMOUNT")):"0")).sum(); 
			double TotalOustandingAmt = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("OVERALL_DISCOUNT")?String.valueOf(mapper.get("OVERALL_DISCOUNT")):"0")).sum(); 
			double TotalAmtToBerec2 = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("BALANCE_AMOUNT")?String.valueOf(mapper.get("BALANCE_AMOUNT")):"0")).sum(); 
			

			
			Row dataRow = sheet.createRow(currentRow+2);
			Row dataRow1=sheet.createRow(currentRow+3);
			Row dataRow2=sheet.createRow(currentRow+4);
			Row dataRow3=sheet.createRow(currentRow+5);
			
			Cell cell = dataRow.createCell(0);
			Cell cell_card_amount=dataRow1.createCell(0);
			Cell cell_credit_amount=dataRow2.createCell(0);
			Cell cell_credit_amount1=dataRow3.createCell(0);
		
			cell.setCellValue("");
			cell_card_amount.setCellValue("");
			cell_credit_amount.setCellValue("");
			cell_credit_amount1.setCellValue("");
			
			cell = dataRow.createCell(7);
			cell_card_amount=dataRow1.createCell(7);
			cell_credit_amount=dataRow2.createCell(7);
			cell_credit_amount1=dataRow3.createCell(7);
			
			cell.setCellValue("TOTAL AMOUNT : ");
			cell_card_amount.setCellValue("TOTAL AMOUNT PAID");
			cell_credit_amount.setCellValue("TOTAL DISCOUNT");
			cell_credit_amount1.setCellValue("TOTAL OUTSTANDING AMOUNT");
			
			cell = dataRow.createCell(8);
			cell_card_amount=dataRow1.createCell(8);
			cell_credit_amount=dataRow2.createCell(8);
			cell_credit_amount1=dataRow3.createCell(8);
			
			cell.setCellValue(TotalAmtReceived);
			cell_card_amount.setCellValue(TotalAmtPaid);
			cell_credit_amount.setCellValue(TotalOustandingAmt);
			cell_credit_amount1.setCellValue(TotalAmtToBerec2);
		
		}
		private void createSupplierTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
				CellStyle headerStyle, List<Map<String, Object>> accountReceivablesDetails,int rowNum,Map<String,Object> inputJson) {
			
			
			rowNum = rowNum + 3;
			
			int headRow=rowNum-2;


			
			// populate Date
			if (!ObjectUtils.isEmpty(accountReceivablesDetails)) {
					
				 
				
//				Row headerRow = sheet.createRow(rowNum++);
//				Cell cell = headerRow.createCell(0);
//				cell.setCellValue("S.NO");
//				cell.setCellStyle(headerStyle);
				
				Row headerRow = sheet.createRow(rowNum++);
				Cell cell = headerRow.createCell(0);
				cell.setCellValue("BILL NO");
				cell.setCellStyle(headerStyle);
				
			
				
				cell = headerRow.createCell(1);
				cell.setCellValue("BILL DATE");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(2);
				cell.setCellValue("TOTAL QTY");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(3);
				cell.setCellValue("TOTAL AMOUNT");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(4);
				cell.setCellValue("PAYMENT STATUS");
				cell.setCellStyle(headerStyle);	
			
				
				cell = headerRow.createCell(5);
				cell.setCellValue("AMOUNT PAID");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(6);
				cell.setCellValue("TOTAL DISCOUNT");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(7);
				cell.setCellValue("OUTSTANDING AMOUNT");
				cell.setCellStyle(headerStyle);	
				
				cell = headerRow.createCell(8);
				cell.setCellValue("CREATED BY");
				cell.setCellStyle(headerStyle);	
//				System.out.println("Head Ro is"+headRow);
//				Row displayRow = sheet.createRow(headRow++);
//				Cell headCell = displayRow.createCell(0);
//				Object value = accountReceivablesDetails.get(0).containsKey("CUSTOMER_NAME") ? accountReceivablesDetails.get(0).get("CUSTOMER_NAME") : "";
//				headCell.setCellValue("Customer Name : ");
//				headCell=displayRow.createCell(1);
//				headCell.setCellValue(String.valueOf(value));
//				

				
				
				for (Map<String, Object> rowData : accountReceivablesDetails) {	
						
					
					Row dataRow = sheet.createRow(rowNum++);
//				   value =  String.valueOf(productList.indexOf(rowData) + 1);
//					//sheet.autoSizeColumn(0);
//					cell = dataRow.createCell(0);
//					cell.setCellValue(String.valueOf(value));
//					cell.setCellStyle(borderStyle);
				
					
					Object value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
					//sheet.autoSizeColumn(2);
					cell = dataRow.createCell(0);
					cell.setCellValue(String.valueOf(value));
					cell.setCellStyle(borderStyle);
					
					
					
					value = rowData.containsKey("BILL_DATE") ? rowData.get("BILL_DATE") : "";
					//sheet.autoSizeColumn(9);
					cell = dataRow.createCell(1);
					cell.setCellValue(String.valueOf(value)); 
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("TOTAL_QTY") ? rowData.get("TOTAL_QTY") : "";
					//sheet.autoSizeColumn(3);
					cell = dataRow.createCell(2);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					cell.setCellStyle(borderStyle);

					value = rowData.containsKey("TOTAL_AMOUNT") ? rowData.get("TOTAL_AMOUNT") : "";
					//sheet.autoSizeColumn(6);
					cell = dataRow.createCell(3);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
					//sheet.autoSizeColumn(7);
					cell = dataRow.createCell(4);
					cell.setCellValue(String.valueOf(value)); 
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("PAID_AMOUNT") ? rowData.get("PAID_AMOUNT") : "";
					//sheet.autoSizeColumn(8);
					cell = dataRow.createCell(5);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("OVERALL_DISCOUNT") ? rowData.get("OVERALL_DISCOUNT") : "";
					//sheet.autoSizeColumn(8);
					cell = dataRow.createCell(6);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("BALANCE_AMOUNT") ? rowData.get("BALANCE_AMOUNT") : "";
					//sheet.autoSizeColumn(8);
					cell = dataRow.createCell(7);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
					cell.setCellStyle(borderStyle);
					
					value = rowData.containsKey("created_by") ? rowData.get("created_by") : "";
					//sheet.autoSizeColumn(8);
					cell = dataRow.createCell(8);
					cell.setCellValue(String.valueOf(value)); 
					cell.setCellStyle(borderStyle);
				}
				
				
//				if(inputJson.get("FROM_APPROVED_DATE")!=null && inputJson.get("TO_APPROVED_DATE")!=null && inputJson.get("CUSTOMER_NAME")==null) {
//				
//					int currentRow = sheet.getLastRowNum();
	//
//				double totalAmount = accountReceivablesDetails.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("outstanding")?String.valueOf(mapper.get("outstanding")):"0")).sum(); 
//				Row dataRow1 = sheet.createRow(currentRow+2);
//				Cell cell1 = dataRow1.createCell(0);
//				cell1.setCellValue("");
//				cell1= dataRow1.createCell(4);
//			    cell1.setCellValue("Total Balance : ");
//			    cell1 = dataRow1.createCell(5);
//			    cell1.setCellValue(totalAmount);
//				}
			
			
			
				
				
			}

		}

	}
