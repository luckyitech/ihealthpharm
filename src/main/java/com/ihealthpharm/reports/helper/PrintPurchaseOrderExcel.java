package com.ihealthpharm.reports.helper;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.dto.HeaderFooterContentDetailsDto;
import com.ihealthpharm.reports.dto.HeaderFooterContentDto;
import com.ihealthpharm.reports.model.ReportsMappingModel;

@Component
public class PrintPurchaseOrderExcel extends ReportsExcelUtility{

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
		font.setFontHeightInPoints((short)12);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBold(true);
		font.setColor(IndexedColors.BLACK.getIndex()); 

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(font);
		headerStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
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
		
		
		CellStyle subHeaderStyle = workbook.createCellStyle();
		subHeaderStyle.setFont(font);
		subHeaderStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		subHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		subHeaderStyle.setVerticalAlignment(VerticalAlignment.TOP);
		subHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
		subHeaderStyle.setBorderBottom(BorderStyle.THIN);  
	
		subHeaderStyle.setBorderRight(BorderStyle.THIN);  
	
		subHeaderStyle.setBorderTop(BorderStyle.THIN);  
		
		subHeaderStyle.setBorderLeft(BorderStyle.THIN);  
		
		


		Map<String, List<Map<String, Object>>> QuotationDetailsMap = responseList.stream()
				.collect(Collectors.groupingBy(map -> (String) map.get("PURCHASE_ORDER_NO")));			


		setHeader(workbook,sheet,model,subHeaderStyle);


		if(!ObjectUtils.isEmpty(QuotationDetailsMap)) { 

			for(String itemName :QuotationDetailsMap.keySet()) {	
				int currentRow = sheet.getLastRowNum();
				List<Map<String, Object>> purchaseOrderDetails = QuotationDetailsMap.get(itemName);
				createQuotationTable(sheet, responseFile, borderStyle, subHeaderStyle,headerStyle,purchaseOrderDetails, itemName, currentRow);

			}
			//generateTotalTable(sheet, responseFile,borderStyle,model,responseList);
		}


		writeToFile(workbook, responseFile);

	}


	private void createQuotationTable(SXSSFSheet sheet,File responseFile, CellStyle borderStyle ,
			CellStyle subHeaderStyle,CellStyle headerStyle, List<Map<String, Object>> purchaseOrderDetails, String itemName, int rowNum) {


		int headRow=rowNum+1;
		rowNum = rowNum + 9;
		

		// populate Date
		if (!ObjectUtils.isEmpty(purchaseOrderDetails)) {

			Row displayRow = sheet.createRow(headRow++);
			headRow=headRow+2;
			Row displayRow1 = sheet.createRow(headRow++);
			Row displayRow2 = sheet.createRow(headRow++);
			Row displayRow3 = sheet.createRow(headRow++);
			Row displayRow4 = sheet.createRow(headRow++);

			Cell headCell = displayRow.createCell(0);
			Object value = purchaseOrderDetails.get(0).containsKey("PURCHASE_ORDER_NO") ? purchaseOrderDetails.get(0).get("PURCHASE_ORDER_NO") : "";
			headCell.setCellValue(" Purchase Order  :   ");
			headCell.setCellStyle(subHeaderStyle);
			headCell=displayRow.createCell(1);
			headCell.setCellValue(String.valueOf(value));
			
			//sheet.addMergedRegion(new CellRangeAddress(8,8,0,5));


			Cell headCell1 = displayRow1.createCell(0);
			value = purchaseOrderDetails.get(0).containsKey("SP_NAME") ? purchaseOrderDetails.get(0).get("SP_NAME") : "";
			headCell1.setCellValue("Supplier  :   ");
			headCell1.setCellStyle(subHeaderStyle);
			headCell1=displayRow1.createCell(1);
			headCell1.setCellValue(String.valueOf(value));
			
			
			Cell headCellCre = displayRow1.createCell(8);
			value = purchaseOrderDetails.get(0).containsKey("CREATED_BY") ? purchaseOrderDetails.get(0).get("CREATED_BY") : "";
			headCellCre.setCellValue("Created By  :   ");
			//headCellCre.setCellStyle(subHeaderStyle);
			headCellCre=displayRow1.createCell(9);
			headCellCre.setCellValue(String.valueOf(value));

			Cell headCell2 = displayRow2.createCell(0);
			value = purchaseOrderDetails.get(0).containsKey("ADDRESS") ? purchaseOrderDetails.get(0).get("ADDRESS") : "";
			headCell2.setCellValue("Address  :   ");
			headCell2.setCellStyle(subHeaderStyle);
			headCell2=displayRow2.createCell(1);
			headCell2.setCellValue(String.valueOf(value));
			
			
			Cell headCellmod = displayRow2.createCell(8);
			value = purchaseOrderDetails.get(0).containsKey("MODIFIED_BY") ? purchaseOrderDetails.get(0).get("MODIFIED_BY") : "";
			headCellmod.setCellValue("Modified By  :   ");
			//headCellmod.setCellStyle(subHeaderStyle);
			headCellmod=displayRow2.createCell(9);
			headCellmod.setCellValue(String.valueOf(value));

			Cell headCell3 = displayRow3.createCell(0);
			value = purchaseOrderDetails.get(0).containsKey("PHONE_NBR") ? purchaseOrderDetails.get(0).get("PHONE_NBR") : "";
			headCell3.setCellValue("Phone  :   ");
			headCell3.setCellStyle(subHeaderStyle);
			headCell3=displayRow3.createCell(1);
			headCell3.setCellValue(String.valueOf(value));
			
			Cell headCellapp = displayRow3.createCell(8);
			value = purchaseOrderDetails.get(0).containsKey("APPROVED_BY") ? purchaseOrderDetails.get(0).get("APPROVED_BY") : "";
			headCellapp.setCellValue("Approved By  :   ");
			//headCellapp.setCellStyle(subHeaderStyle);
			headCellapp=displayRow3.createCell(9);
			headCellapp.setCellValue(String.valueOf(value));

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
			cell.setCellValue("BONUS");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(4);
			cell.setCellValue("UNIT PRICE");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(5);
			cell.setCellValue("DISC%");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(6);
			cell.setCellValue("DISC AMT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(7);
			cell.setCellValue("VAT%");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(8);
			cell.setCellValue("VAT AMT");
			cell.setCellStyle(headerStyle);	

			cell = headerRow.createCell(9);
			cell.setCellValue("TOTAL AMT");
			cell.setCellStyle(headerStyle);	


			
			for (Map<String, Object> rowData : purchaseOrderDetails) {




				Row dataRow = sheet.createRow(rowNum++);
				value =  String.valueOf(purchaseOrderDetails.indexOf(rowData) + 1);
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
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Integer.parseInt(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);



				value = rowData.containsKey("BONUS") ? rowData.get("BONUS") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(3);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("UNIT_RATE") ? rowData.get("UNIT_RATE") : "";
				//sheet.autoSizeColumn(5);
				cell = dataRow.createCell(4);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);


				value = rowData.containsKey("DISCOUNT") ? rowData.get("DISCOUNT") : "";
				//sheet.autoSizeColumn(6);
				cell = dataRow.createCell(5);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);




				value = rowData.containsKey("DISC_AMT") ? rowData.get("DISC_AMT") : "";
				//sheet.autoSizeColumn(9);
				cell = dataRow.createCell(6);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("VAT_PER") ? rowData.get("VAT_PER") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(7);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Integer.parseInt(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(8);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);

				value = rowData.containsKey("NET_AMT") ? rowData.get("NET_AMT") : "";
				//sheet.autoSizeColumn(10);
				cell = dataRow.createCell(9);
				if(NumberUtils.isNumber(String.valueOf(value))) {
					cell.setCellType(CellType.NUMERIC);		
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				}else {	
					cell.setCellValue(String.valueOf(value));
				}
				cell.setCellStyle(borderStyle);



			}
			
			
			int lastRemarks=sheet.getLastRowNum()+4;
			Row remarks = sheet.createRow(lastRemarks);
			Cell remarksCell = remarks.createCell(1);
			remarksCell.setCellValue("REMARKS :");
			remarksCell.setCellStyle(subHeaderStyle);
			Cell remarksCellValue = remarks.createCell(2);
			value=purchaseOrderDetails.get(0).containsKey("REMARKS") ? purchaseOrderDetails.get(0).get("REMARKS") : "";
			remarksCellValue.setCellValue(String.valueOf(value));
			//remarksCellValue.setCellStyle(subHeaderStyle);
			
			
			
			Row terms = sheet.createRow(lastRemarks+2);
			Cell termsCell = terms.createCell(1);
			termsCell.setCellValue("TERMS & CONDITIONS :");
			termsCell.setCellStyle(subHeaderStyle);
			Cell termsCellValue = terms.createCell(2);
			value=purchaseOrderDetails.get(0).containsKey("PO_TERM") ? purchaseOrderDetails.get(0).get("PO_TERM") : "";
			termsCellValue.setCellValue(String.valueOf(value));
			//termsCellValue.setCellStyle(subHeaderStyle);
		}

	}



	public int setHeader(SXSSFWorkbook workbook, SXSSFSheet sheet, ReportsMappingModel model,CellStyle headerStyle) { 

		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBold(true);
		font.setColor(IndexedColors.BLACK.getIndex()); 
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
		CellStyle wrapStyle = workbook.createCellStyle();
		wrapStyle.setWrapText(true);
		wrapStyle.setVerticalAlignment(VerticalAlignment.TOP);
		wrapStyle.setAlignment(HorizontalAlignment.CENTER);
		wrapStyle.setFont(font);
		
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setWrapText(true);
		titleStyle.setVerticalAlignment(VerticalAlignment.TOP);
		titleStyle.setAlignment(HorizontalAlignment.RIGHT);
		titleStyle.setFont(font);
	

		String reportHeader = model.getReportHeader();
		String headerContent = model.getHeaderContent();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		int rowNum = 0;


		if (!ObjectUtils.isEmpty(headerContent)) {
			HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(headerContent,
					HeaderFooterContentDto.class);

			String reportHeaderContent = getReportHeader(contentDto);

			
			int totalMergeRows = getTotalMergeRows(contentDto);

			/*
			 * int totalMergeRows = Stream.of(contentDto.getLeftContent().size(),
			 * contentDto.getCenterContent().size(),
			 * contentDto.getRightContent().size()).max(Integer::compareTo).get();
			 */
			int totalMergeColumns = headerList.size();

		
			
			for (int m = 0; m < totalMergeRows; m++) {
				Row headerRow = sheet.createRow(rowNum++);	
				Cell cell = headerRow.createCell(0);
				cell.setCellValue(reportHeaderContent);
				cell.setCellStyle(wrapStyle);
				
				
			}


			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, totalMergeRows - 1, 0, totalMergeColumns - 1);
			sheet.addMergedRegion(cellRangeAddress);


			Row reportDateRow =sheet.createRow(rowNum++);
			Cell cellReportTime = reportDateRow.createCell(0);
			//			cellReportTime.setCellValue("Report Generated Date - "+DateUtility.getDateString());
			//			cellReportTime.setCellStyle(wrapStyle);

			CellRangeAddress cellRangeReportDate= new CellRangeAddress(rowNum - 1, rowNum - 1, 0, totalMergeColumns - 1);
			sheet.addMergedRegion(cellRangeReportDate);

			Row headerRow = sheet.createRow(rowNum++);
			Cell cell = headerRow.createCell(0);
			cell.setCellValue(model.getTitle());
			cell.setCellStyle(wrapStyle);
			sheet.createRow(rowNum++);

			int headRow=sheet.getLastRowNum();
			Row displayRow = sheet.createRow(headRow+1);

			Cell headCell = displayRow.createCell(0);

			headCell.setCellValue("Date  :   ");
			headCell.setCellStyle(headerStyle);
			headCell=displayRow.createCell(1);
			headCell.setCellValue(String.valueOf(dateFormat.format(new Date())));

			//sheet.addMergedRegion(new CellRangeAddress(8,8,0,5));
			for (int i=contentDto.getRightContent().size()-1;i>0;i--) {
				if (StringUtils.isNotBlank(contentDto.getRightContent().get(i).getText())) {

					headCell=displayRow.createCell(8);
					headCell.setCellStyle(headerStyle);
					headCell.setCellValue(contentDto.getRightContent().get(i).getText());
					
					break;
				}
			}

			System.out.println(rowNum+"======"+totalMergeColumns);
			CellRangeAddress cellRangeAddress1 = new CellRangeAddress(rowNum - 2, rowNum - 1, 0, totalMergeColumns - 1);
			sheet.addMergedRegion(cellRangeAddress1);

		}

		return rowNum;

	}


	public String getReportHeader(HeaderFooterContentDto contentDto) {

		StringBuffer buffer = new StringBuffer();

		if (!ObjectUtils.isEmpty(contentDto)) {


			buffer.append("Purchase Order");
			buffer.append(System.lineSeparator());
			buffer.append(" ");
			buffer.append(System.lineSeparator());
			for (HeaderFooterContentDetailsDto dto : contentDto.getCenterContent()) {
				if (StringUtils.isNotBlank(dto.getText())) {
					buffer.append(dto.getText());
					buffer.append(System.lineSeparator());
				}
			}


		}

		return buffer.toString();
	}



}
