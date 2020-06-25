package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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


			Map<Date, List<Map<String, Object>>> pettyExpMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (Date) map.get("CREATION_TS")));	

			System.out.println(pettyExpMap);
			List<Date> datesList = new ArrayList<>();


			if(!ObjectUtils.isEmpty(pettyExpMap)) { 

				datesList.addAll(pettyExpMap.keySet());
				Collections.sort(datesList);

				for(int i = 0; i < datesList.size(); i++) {	
					List<Map<String, Object>> PettyCashList = pettyExpMap.get(datesList.get(i));
					createTable(document,model,datesList.get(i),PettyCashList);

				}

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


	public void createTable(Document document, ReportsMappingModel model,Date entryDate,List<Map<String, Object>> pettyExpList) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		PdfPTable supllierNameTable = new PdfPTable(3);
		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 

		PdfPTable table = new PdfPTable(8);
		table.setTotalWidth(500);
		table.setWidths(new int[] {30,50,50,50,150,50,50,70});
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
		headerCell.add("PARTY");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);
		
		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("COUNTER PARTY");
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
		
		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("SUBMITTED BY");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		//}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(pettyExpList)) {

			
			double cashOnHand=0.0;
				
			for (Map<String, Object> rowData : pettyExpList) {
						

				String asOfDate=((rowData.containsKey("FROM_DATE") ? String.valueOf(rowData.get("FROM_DATE")) : ""));
				double amountAdded=Double.parseDouble(((rowData.containsKey("DEBIT") ? String.valueOf(rowData.get("DEBIT")) : "")));

				if(rowData.get("ENTRY_TYPE").equals("Petty Cash")) {
					PdfPCell nameCell = new PdfPCell(new Phrase("Petty Cash draw on "+asOfDate+":"+"        "
				+amountAdded, title08)); 

					nameCell.setColspan(3);
					nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					nameCell.setVerticalAlignment(Element.ALIGN_TOP);
					nameCell.setBorder(0);
					supllierNameTable.addCell(nameCell);
					supllierNameTable.setLockedWidth(true);
					supllierNameTable.setTotalWidth(500);
					supllierNameTable.getDefaultCell().setBorder(0);
					
					document.add(supllierNameTable);
					
				}


				if(rowData.get("ENTRY_TYPE").equals("Exp PettyCash")) {

		
					double balance=Double.parseDouble(((rowData.containsKey("BALANCE") ? String.valueOf(rowData.get("BALANCE")) : "")));
					cashOnHand=(Double) balance;
					
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
					
					value = rowData.containsKey("PARTY") ? rowData.get("PARTY") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("COUNTER_PARTY") ? rowData.get("COUNTER_PARTY") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);

					value = rowData.containsKey("EXPENSES_REF") ? rowData.get("EXPENSES_REF") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);

					value=rowData.containsKey("DEBIT") ? (String.valueOf(rowData.get("DEBIT"))) : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);

					double value1=Double.parseDouble(((rowData.containsKey("DEBIT") ? (String.valueOf(rowData.get("DEBIT"))) : "")));
					value = cashOnHand-value1;
					cashOnHand=(Double) value;
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("SUBMITTED_BY") ? rowData.get("SUBMITTED_BY") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
				}
				
			}
			if(!pettyExpList.get(0).get("ENTRY_TYPE").equals("Petty Cash")) {
				
				double balance=Double.parseDouble(((pettyExpList.get(0).containsKey("BALANCE")? String.valueOf(pettyExpList.get(0).get("BALANCE")) : "")));
				double totBalOnHand=(Double) balance;
				
				PdfPTable cashOnHandTable = new PdfPTable(3);
				PdfPCell onHandCell = new PdfPCell(new Phrase("Total Balance at hand : "+totBalOnHand, title08)); 
				onHandCell.setColspan(3);
				onHandCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				onHandCell.setVerticalAlignment(Element.ALIGN_TOP);
				onHandCell.setBorder(0);
				cashOnHandTable.addCell(onHandCell);
				cashOnHandTable.setLockedWidth(true);
				cashOnHandTable.setTotalWidth(500);
				cashOnHandTable.getDefaultCell().setBorder(0); 
				
				document.add(cashOnHandTable);
				
				}
			
			finalTable.addCell(table); 
			document.add(finalTable);
			System.out.println(pettyExpList);
			if(!pettyExpList.get(0).get("ENTRY_TYPE").equals("Petty Cash")) {
				
				DecimalFormat df=new DecimalFormat("0.00");
				double totalAmount;
				double totalBalance;

				totalAmount = pettyExpList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("DEBIT")&&mapper.get("ENTRY_TYPE").equals("Exp PettyCash")?String.valueOf(mapper.get("DEBIT")):"0")).sum(); 

				totalBalance=cashOnHand;

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

				String totBalance=df.format(totalBalance);
				Double bal=Double.parseDouble(totBalance);
				
				PdfPCell nameCell2 = new PdfPCell(new Phrase("Balance  : "+bal+"   ", title08)); 
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
					
		}

	}
}
