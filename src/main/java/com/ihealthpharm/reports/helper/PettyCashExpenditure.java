package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

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
public class PettyCashExpenditure extends ReportsPDFUtility {

	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

			Map<String, List<Map<String, Object>>> pettyExpMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String.valueOf(map.get("FROM_DATE")))));			


			if(!ObjectUtils.isEmpty(pettyExpMap)) { 
				//addHeader(document,responseList);

				for(String pettyCashSummary :pettyExpMap.keySet()) {					
					List<Map<String, Object>> pettyExpList = pettyExpMap.get(pettyCashSummary);
					createTable(document,model,pettyExpList,pettyCashSummary,responseList);
					
				}


				generateTotalTable(document,model,responseList);

			}

		} catch (Exception e) {
			try {
				addMessage(document, ExceptionUtils.getMessage(e));
			} catch (DocumentException e1) {

			}
		} finally {
			document.close();
		}

		return document;
	}

	private void generateTotalTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		DecimalFormat df=new DecimalFormat("0.00");
		double totalAmount;
		Double totalBalance;

		totalAmount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalBalance=Double.parseDouble(String.valueOf(responseList.get(0).get("BALANCE")))+Double.parseDouble(String.valueOf(responseList.get(0).get("AMOUNT")))-totalAmount;


		PdfPTable totalTable = new PdfPTable(3);
		totalTable.setTotalWidth(500);
		totalTable.setSpacingBefore(30); 
		totalTable.setWidthPercentage(50);
		totalTable.setLockedWidth(true);
		totalTable.getDefaultCell().setBorder(0); 

		String totAmt=df.format(totalAmount);
		Double amount=Double.parseDouble(totAmt);


		PdfPCell nameCell1 = new PdfPCell(new Phrase("Total Amount  : "+amount+"   ", title08)); 
		nameCell1.setColspan(3);
		nameCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell1.setBorder(0);
		totalTable.addCell(nameCell1);
		totalTable.setLockedWidth(true);
		totalTable.setTotalWidth(500);
		totalTable.getDefaultCell().setBorder(0); 

		String totBalance=df.format(totalBalance);
		Double balance=Double.parseDouble(totBalance);

		PdfPCell nameCell2 = new PdfPCell(new Phrase("Balance  : "+balance+"   ", title08)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalTable.addCell(nameCell2);
		totalTable.setLockedWidth(true);
		totalTable.setTotalWidth(500);
		totalTable.getDefaultCell().setBorder(0); 

		document.add(totalTable);

	}

	/*private void addHeader(Document document,List<Map<String, Object>> responseList) throws DocumentException {

		double cashOnHand=Double.parseDouble(String.valueOf(responseList.get(0).get("BALANCE")));


		PdfPTable supllierNameTable = new PdfPTable(3);
		PdfPCell nameCell = new PdfPCell(new Phrase("Total Balance at hand : "+cashOnHand, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		supllierNameTable.addCell(nameCell);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 

		document.add(supllierNameTable);

	}*/

	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> pettyExpList,
			String pettyCashSummary, List<Map<String, Object>> responseList) throws DocumentException {
		
		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 

		PdfPTable table = new PdfPTable(5);
		table.setTotalWidth(500);
		table.setWidths(new int[] {30,50,320,50,50});
		table.setWidthPercentage(50);
		table.setLockedWidth(true);
		PdfPCell cell = null;

		//for (HeaderDto hearder : headerList) {

		Paragraph headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("S.No");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("DATE");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("DESCRIPTION");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("AMOUNT");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("BALANCE");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		//}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(pettyExpList)) {

			double balance=Double.parseDouble(((pettyExpList.get(0).containsKey("BALANCE") ? String.valueOf(pettyExpList.get(0).get("BALANCE")) : "")));
			double amountDebit=Double.parseDouble(((pettyExpList.get(0).containsKey("AMOUNT") ? String.valueOf(pettyExpList.get(0).get("AMOUNT")) : "")));
			
			double cashOnHand=balance+amountDebit;
			
			PdfPTable supllierNameTable = new PdfPTable(3);
			PdfPCell nameCell = new PdfPCell(new Phrase("Total Balance at hand : "+cashOnHand, title08)); 
			nameCell.setColspan(3);
			nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			nameCell.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell.setBorder(0);
			supllierNameTable.addCell(nameCell);
			supllierNameTable.setLockedWidth(true);
			supllierNameTable.setTotalWidth(500);
			supllierNameTable.getDefaultCell().setBorder(0); 
			for (Map<String, Object> rowData : pettyExpList) {
				//for (HeaderDto hearder : headerList) {
				
				Object value = String.valueOf(pettyExpList.indexOf(rowData) + 1);
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("FROM_DATE") ? rowData.get("FROM_DATE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("DESCRIPTION") ? rowData.get("DESCRIPTION") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("AMOUNT") ? rowData.get("AMOUNT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				double value1=Double.parseDouble(((rowData.containsKey("AMOUNT") ? (String.valueOf(rowData.get("AMOUNT"))) : "")));
				value = cashOnHand-value1;
				balance=(Double) value;
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				//}
			}
			
			document.add(supllierNameTable);
			
			DecimalFormat df=new DecimalFormat("0.00");
			double totalAmount;

			totalAmount = pettyExpList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 

			PdfPTable totalTable = new PdfPTable(3);
			totalTable.setTotalWidth(500);
			totalTable.setWidthPercentage(50);
			totalTable.setLockedWidth(true);
			totalTable.getDefaultCell().setBorder(0); 

			String totAmt=df.format(totalAmount);
			Double amount=Double.parseDouble(totAmt);

			PdfPCell nameCell1 = new PdfPCell(new Phrase("Total Amount  : "+amount+"   ", title08)); 
			nameCell1.setColspan(3);
			nameCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell1.setBorder(0);
			totalTable.addCell(nameCell1);
			totalTable.setLockedWidth(true);
			totalTable.setTotalWidth(500);
			totalTable.getDefaultCell().setBorder(0); 
			
			//finalTable.addCell(supllierNameTable);
			finalTable.addCell(table); 
			document.add(finalTable);		
			document.add(totalTable);

		}


	}
}

