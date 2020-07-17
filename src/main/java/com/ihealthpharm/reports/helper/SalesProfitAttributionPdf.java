package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.model.ReportsMappingModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import io.jsonwebtoken.Header;
@Component
public class SalesProfitAttributionPdf extends ReportsPDFUtility{
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();
			
			Map<String, List<Map<String, Object>>> salesByPersonMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("CREATED_BY")));			
			
			
			if(!ObjectUtils.isEmpty(salesByPersonMap)) { 
				
				for(String salesPerson :salesByPersonMap.keySet()) {					
					List<Map<String, Object>> salesByPersonList = salesByPersonMap.get(salesPerson);
					createTable(document,model,salesByPersonList,salesPerson);
				}
				
				generateTotalTable(document,model,responseList);
				
			}
			
//			Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
//			createTable(document,model,responseList,dataMap);
//			generateTotalTable(document,model,responseList);

		} catch (Exception e) {
			//log.error(ExceptionUtils.getMessage(e));
			try {
				addMessage(document, ExceptionUtils.getMessage(e));
			} catch (DocumentException e1) {
				//log.error(ExceptionUtils.getMessage(e1));
			}
		} finally {
			document.close();
		}

		return document;
	}

	private void generateTotalTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		DecimalFormat df=new DecimalFormat("0.00");
		double totalProfit;
		double totalPurValue;
		double totalSaleValue;
		totalProfit  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("PROFIT") && !ObjectUtils.isEmpty(mapper.get("PROFIT"))) ?String.valueOf(mapper.get("PROFIT")):"0")).sum();  
		totalSaleValue  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("SALE_AMOUNT") && !ObjectUtils.isEmpty(mapper.get("SALE_AMOUNT"))) ?String.valueOf(mapper.get("SALE_AMOUNT")):"0")).sum();  
		totalPurValue  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("PURCHASE_AMOUNT") && !ObjectUtils.isEmpty(mapper.get("PURCHASE_AMOUNT"))) ?String.valueOf(mapper.get("PURCHASE_AMOUNT")):"0")).sum();  

		PdfPTable totalProfitTable = new PdfPTable(2);
		totalProfitTable.setTotalWidth(500);
		//totalProfitTable.setSpacingBefore(30); 
		totalProfitTable.setWidthPercentage(50);
		totalProfitTable.setLockedWidth(true);
		totalProfitTable.getDefaultCell().setBorder(0); 

		String totProfit=df.format(totalProfit);
		Double profitRound=Double.parseDouble(totProfit);

		String totPur=df.format(totalPurValue);
		Double purchaseValueTot=Double.parseDouble(totPur);

		String totSale=df.format(totalSaleValue);
		Double saleValueTot=Double.parseDouble(totSale);

		PdfPCell nameCell = new PdfPCell(new Phrase("Total Purchase Amount"+" "+" : "+"	"+purchaseValueTot, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalProfitTable.addCell(nameCell);
		totalProfitTable.setLockedWidth(true);
		totalProfitTable.setTotalWidth(500);
		totalProfitTable.getDefaultCell().setBorder(0); 

		PdfPCell nameCell2 = new PdfPCell(new Phrase("Total Sale Amount"+" "+" : "+"	"+saleValueTot, title08)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalProfitTable.addCell(nameCell2);
		totalProfitTable.setLockedWidth(true);
		totalProfitTable.setTotalWidth(500);
		totalProfitTable.getDefaultCell().setBorder(0); 

		PdfPCell nameCell3 = new PdfPCell(new Phrase("Total Profit"+" "+" : "+"	"+profitRound, title08)); 
		nameCell3.setColspan(3);
		nameCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell3.setBorder(0);
		totalProfitTable.addCell(nameCell3);
		totalProfitTable.setLockedWidth(true);
		totalProfitTable.setTotalWidth(500);
		totalProfitTable.getDefaultCell().setBorder(0); 

		document.add(totalProfitTable);

	}
	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> salesProfitList,String salesPerson) throws DocumentException {


		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);


		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 

		PdfPTable salePersonNameTable = new PdfPTable(3);
		PdfPCell nameCell = new PdfPCell(new Phrase("Served By : "+salesPerson, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		salePersonNameTable.addCell(nameCell);
		salePersonNameTable.setLockedWidth(true);
		salePersonNameTable.setTotalWidth(500);
		salePersonNameTable.getDefaultCell().setBorder(0); 
		
		PdfPTable table = new PdfPTable(16);
		table.setTotalWidth(530);
		table.setWidths(new int[] {35,40,55,33,25,20,35,35,35,35,20,30,30,30,40,33});
		table.setWidthPercentage(50);
		table.setLockedWidth(true);
		PdfPCell cell = null;


		for (HeaderDto hearder : headerList) {
			Paragraph headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add(hearder.getDisplayName());

			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if(!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);


			table.addCell(cell);
		}
		table.setHeaderRows(1);


		// populate Date

		if (!ObjectUtils.isEmpty(salesProfitList)) {
			for (Map<String, Object> rowData : salesProfitList) {
				for (HeaderDto hearder : headerList) {
					Object value = rowData.containsKey(hearder.getColumnName()) ? rowData.get(hearder.getColumnName())
							: "";

					value = formatData(hearder,value); 

					cell = new PdfPCell(new Phrase(String.valueOf(value),title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if(!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);

				}
			}
		}

		finalTable.addCell(salePersonNameTable);
		finalTable.addCell(table); 
		document.add(finalTable);

	}
	private Object formatData(HeaderDto hearder, Object value) {

		if(ObjectUtils.isEmpty(hearder.getFormat()))
			return value;

		//getDateStringwithouKnowingInputFormat

		return value;


	}

}
