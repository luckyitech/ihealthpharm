package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@Component
public class StockTakePdf extends ReportsPDFUtility{
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
					.collect(Collectors.groupingBy(map -> (String) map.get("ITEM_NM")));			


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
		double totalPreviousAmt;
		double totalAdjustAmt;
		double totalDiff;
		totalPreviousAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("PREVIOUS_AMT") && !ObjectUtils.isEmpty(mapper.get("PREVIOUS_AMT"))) ?String.valueOf(mapper.get("PREVIOUS_AMT")):"0")).sum();  
		totalAdjustAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("ADJUSTED_AMT") && !ObjectUtils.isEmpty(mapper.get("ADJUSTED_AMT"))) ?String.valueOf(mapper.get("ADJUSTED_AMT")):"0")).sum();  
		if(totalPreviousAmt>totalAdjustAmt)
			totalDiff  = totalPreviousAmt-totalAdjustAmt;
		else
			totalDiff  = totalAdjustAmt-totalPreviousAmt;

		PdfPTable totalProfitTable = new PdfPTable(2);
		totalProfitTable.setTotalWidth(500);
		//totalProfitTable.setSpacingBefore(30); 
		totalProfitTable.setWidthPercentage(50);
		totalProfitTable.setLockedWidth(true);
		totalProfitTable.getDefaultCell().setBorder(0); 

		String totPrev=df.format(totalPreviousAmt);
		Double prevAmtRound=Double.parseDouble(totPrev);

		String totAdj=df.format(totalAdjustAmt);
		Double adjAmtRound=Double.parseDouble(totAdj);

		String totDiffAmt=df.format(totalDiff);
		Double diffAmtRound=Double.parseDouble(totDiffAmt);

		PdfPCell nameCell = new PdfPCell(new Phrase("Actual Amount"+" "+" : "+"	"+prevAmtRound, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalProfitTable.addCell(nameCell);
		totalProfitTable.setLockedWidth(true);
		totalProfitTable.setTotalWidth(500);
		totalProfitTable.getDefaultCell().setBorder(0); 

		PdfPCell nameCell2 = new PdfPCell(new Phrase("Adjusted Amount"+" "+" : "+"	"+adjAmtRound, title08)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalProfitTable.addCell(nameCell2);
		totalProfitTable.setLockedWidth(true);
		totalProfitTable.setTotalWidth(500);
		totalProfitTable.getDefaultCell().setBorder(0); 

		PdfPCell nameCell3 = new PdfPCell(new Phrase("Variation"+" "+" : "+"	"+diffAmtRound, title08)); 
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
		PdfPCell nameCell = new PdfPCell(new Phrase("Item Name : "+salesPerson, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		salePersonNameTable.addCell(nameCell);
		salePersonNameTable.setLockedWidth(true);
		salePersonNameTable.setTotalWidth(500);
		salePersonNameTable.getDefaultCell().setBorder(0); 

		String reportHeader1 = model.getReportHeader();
		List<HeaderDto> headerList1 = JsonUtility.jsonToList(reportHeader1, HeaderDto.class);

		PdfPTable table = new PdfPTable(headerList1.size());
		table.setTotalWidth(530);
		//table.setWidths(new int[] {35,40,55,33,25,20,35,35,35,35,20,30,30,30,40,33});
		table.setWidthPercentage(50);
		table.setLockedWidth(true);
		PdfPCell cell = null;


		for (HeaderDto hearder : headerList1) {
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

				for (HeaderDto hearder : headerList1) {
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

			finalTable.addCell(salePersonNameTable);
			DecimalFormat df=new DecimalFormat("0.00");
			double totalPreviousAmt;
			double totalAdjustAmt;
			double totalDiff;
			totalPreviousAmt  = salesProfitList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("PREVIOUS_AMT") && !ObjectUtils.isEmpty(mapper.get("PREVIOUS_AMT"))) ?String.valueOf(mapper.get("PREVIOUS_AMT")):"0")).sum();  
			totalAdjustAmt  = salesProfitList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("ADJUSTED_AMT") && !ObjectUtils.isEmpty(mapper.get("ADJUSTED_AMT"))) ?String.valueOf(mapper.get("ADJUSTED_AMT")):"0")).sum();  
			if(totalPreviousAmt>totalAdjustAmt)
				totalDiff  = totalPreviousAmt-totalAdjustAmt;
			else
				totalDiff  = totalAdjustAmt-totalPreviousAmt;

			PdfPTable totalAmountTable = new PdfPTable(2);
			totalAmountTable.setTotalWidth(500);
			//totalProfitTable.setSpacingBefore(30); 
			totalAmountTable.setWidthPercentage(50);
			totalAmountTable.setLockedWidth(true);
			totalAmountTable.getDefaultCell().setBorder(0); 

			String totPrev=df.format(totalPreviousAmt);
			Double prevAmtRound=Double.parseDouble(totPrev);

			String totAdj=df.format(totalAdjustAmt);
			Double adjAmtRound=Double.parseDouble(totAdj);

			String totDiffAmt=df.format(totalDiff);
			Double diffAmtRound=Double.parseDouble(totDiffAmt);

			PdfPCell nameCell1 = new PdfPCell(new Phrase("Actual Amount"+" "+" : "+"	"+prevAmtRound, title08)); 
			nameCell1.setColspan(3);
			nameCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell1.setBorder(0);
			totalAmountTable.addCell(nameCell1);
			totalAmountTable.setLockedWidth(true);
			totalAmountTable.setTotalWidth(500);
			totalAmountTable.getDefaultCell().setBorder(0); 

			PdfPCell nameCell2 = new PdfPCell(new Phrase("Adjusted Amount"+" "+" : "+"	"+adjAmtRound, title08)); 
			nameCell2.setColspan(3);
			nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell2.setBorder(0);
			totalAmountTable.addCell(nameCell2);
			totalAmountTable.setLockedWidth(true);
			totalAmountTable.setTotalWidth(500);
			totalAmountTable.getDefaultCell().setBorder(0); 

			PdfPCell nameCell3 = new PdfPCell(new Phrase("Variation"+" "+" : "+"	"+diffAmtRound, title08)); 
			nameCell3.setColspan(3);
			nameCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell3.setBorder(0);
			totalAmountTable.addCell(nameCell3);
			totalAmountTable.setLockedWidth(true);
			totalAmountTable.setTotalWidth(500);
			totalAmountTable.getDefaultCell().setBorder(0); 

			totalAmountTable.setSpacingAfter(15);

			finalTable.addCell(table); 
			document.add(finalTable);

			document.add(totalAmountTable);

		}
	}



	private Object formatData(HeaderDto hearder, Object value) {

		if(ObjectUtils.isEmpty(hearder.getFormat()))
			return value;

		//getDateStringwithouKnowingInputFormat

		return value;


	}

}
