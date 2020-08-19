package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class CustomerStatementPdf extends ReportsPDFUtility{
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {
		
		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		 Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();
//			Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
//			createTable(document,model,responseList,dataMap);
//			generateTotalTable(document,model,responseList);
			
			Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
			Map<String, List<Map<String, Object>>> customerstmtMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("CUSTOMER_NAME")));			
			createTable(document,model,responseList,dataMap);
			
//			if(!ObjectUtils.isEmpty(customerstmtMap)) { 
//				
//				for(String customerSummary :customerstmtMap.keySet()) {					
//					List<Map<String, Object>> productList = customerstmtMap.get(customerSummary);
//					
//					createTable(document,model,productList,customerSummary,dataMap);
//				}
				
				generateTotalTable(document,model,responseList);
				
	//		}

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
		double totalAmtReceived;
		double totalAmtPaid;
		double totalTotalOutstanding;
		//double totalAmtToBePaid;
		//totalAmtReceived  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("INVOICE_AMOUNT") && !ObjectUtils.isEmpty(mapper.get("INVOICE_AMOUNT"))) ?String.valueOf(mapper.get("INVOICE_AMOUNT")):"0")).sum();  
		totalAmtPaid  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("AMOUNT_RECEIVED") && !ObjectUtils.isEmpty(mapper.get("AMOUNT_RECEIVED"))) ?String.valueOf(mapper.get("AMOUNT_RECEIVED")):"0")).sum();  
		totalTotalOutstanding  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("outstanding_amt") && !ObjectUtils.isEmpty(mapper.get("outstanding_amt"))) ?String.valueOf(mapper.get("outstanding_amt")):"0")).sum();  
		totalAmtReceived=totalAmtPaid +totalTotalOutstanding;
		
		System.out.println(totalAmtReceived);
		//totalAmtToBePaid  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("TOTAL_AMOUNT_TO_BE_PAID") && !ObjectUtils.isEmpty(mapper.get("TOTAL_AMOUNT_TO_BE_PAID"))) ?String.valueOf(mapper.get("TOTAL_AMOUNT_TO_BE_PAID")):"0")).sum();   
		PdfPTable totalQtyTable = new PdfPTable(2);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		String totAmountRecieved=df.format(totalAmtReceived);
		Double totalReceived=Double.parseDouble(totAmountRecieved);
		
//		String totAmountToBePaid=df.format(totalAmtToBePaid);
//		Double totalAmtsToBePaid=Double.parseDouble(totAmountToBePaid);
		Font bold = new Font(FontFamily.HELVETICA,9);
		
		PdfPCell nameCell = new PdfPCell(new Phrase("Total Amount Spent"+" "+" : "+"	"+totalReceived, bold)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalQtyTable.addCell(nameCell);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
	
		
		PdfPCell nameCell2 = new PdfPCell(new Phrase("Total Amount Received"+" "+":"+" "+totalAmtPaid, bold)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalQtyTable.addCell(nameCell2);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell3 = new PdfPCell(new Phrase("Total Outstanding Amount"+" "+":"+" "+totalTotalOutstanding, bold)); 
		nameCell3.setColspan(3);
		nameCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell3.setBorder(0);
		totalQtyTable.addCell(nameCell3);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
//		PdfPCell nameCell2 = new PdfPCell(new Phrase("Total Amount To Be Paid"+"	"+" : "+"	"+totalAmtsToBePaid, title08)); 
//		nameCell2.setColspan(3);
//		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
//		nameCell2.setBorder(0);
//		totalQtyTable.addCell(nameCell2);
//		totalQtyTable.setLockedWidth(true);
//		totalQtyTable.setTotalWidth(500);
//		totalQtyTable.getDefaultCell().setBorder(0); 
		
		document.add(totalQtyTable);
	
	}
	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> accountPayablesList,Map<String,Object> dataMap) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);
//String approvedDate=(ObjectUtils.isEmpty(accountPayablesList))?"":String.valueOf(accountPayablesList.get(0).get("TO_APPROVED_DATE"));
		
		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 
		
//		PdfPTable supllierNameTable = new PdfPTable(3);
//		if(dataMap.get("CUSTOMER_NAME")!=null) {
//		PdfPCell nameCell = new PdfPCell(new Phrase("Customer Name : "+customerSummary+"                                                     "
//				+ "                                                                          "+ "   "+"As On Date: "+approvedDate, title08)); 
//		nameCell.setColspan(3);
//		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
//		nameCell.setBorder(0);
//		supllierNameTable.addCell(nameCell);
//		supllierNameTable.setLockedWidth(true);
//		supllierNameTable.setTotalWidth(500);
//		supllierNameTable.getDefaultCell().setBorder(0);
//		}
//		
//		else {
//			PdfPCell nameCell = new PdfPCell(new Phrase("Customer Name : "+customerSummary, title08)); 
//			nameCell.setColspan(3);
//			nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			nameCell.setVerticalAlignment(Element.ALIGN_TOP);
//			nameCell.setBorder(0);
//			supllierNameTable.addCell(nameCell);
//			supllierNameTable.setLockedWidth(true);
//			supllierNameTable.setTotalWidth(500);
//			supllierNameTable.getDefaultCell().setBorder(0);
	//	}
		
		
		
		
		PdfPTable table = new PdfPTable(8);
		table.setTotalWidth(500);
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
			
		;
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("CUSTOMER NAME");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("TRANSACTION DATE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("BILL TYPE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
		
			
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("BILL NUMBER");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("AMOINT PAID");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("AMOUNT RECEIVED");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("OUTSTANDING AMOUNT");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
		
			
		
		//}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(accountPayablesList)) {
			for (Map<String, Object> rowData : accountPayablesList) {
				

				Object value = String.valueOf(accountPayablesList.indexOf(rowData) + 1);
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("CUSTOMER_NAME") ? rowData.get("CUSTOMER_NAME") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("RECEIPT_DATE") ? rowData.get("RECEIPT_DATE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				
				value = rowData.containsKey("bill_type_id") ? rowData.get("bill_type_id") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				
				
				
				value = rowData.containsKey("SOURCE_REF") ? rowData.get("SOURCE_REF") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("AMOUNT_RECEIVED") ? rowData.get("AMOUNT_RECEIVED") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("AMOUNT_RECEIVED") ? rowData.get("AMOUNT_RECEIVED") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
		
				

				value = rowData.containsKey("outstanding_amt") ? rowData.get("outstanding_amt") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
		


				
			}
		}
		
		if(dataMap.get("FROM_APPROVED_DATE")!=null && dataMap.get("TO_APPROVED_DATE")!=null && dataMap.get("CUSTOMER_NAME")==null) {
		DecimalFormat df=new DecimalFormat("0.00");
		double totalAmtreceived;
		//double totalAmtToBePaid;
		totalAmtreceived  = accountPayablesList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("outstanding") && !ObjectUtils.isEmpty(mapper.get("outstanding"))) ?String.valueOf(mapper.get("outstanding")):"0")).sum();  
		//totalAmtToBePaid  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("TOTAL_AMOUNT_TO_BE_PAID") && !ObjectUtils.isEmpty(mapper.get("TOTAL_AMOUNT_TO_BE_PAID"))) ?String.valueOf(mapper.get("TOTAL_AMOUNT_TO_BE_PAID")):"0")).sum();   
		PdfPTable totalQtyTable = new PdfPTable(2);
		totalQtyTable.setTotalWidth(500);
//		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		String totAmountReceived=df.format(totalAmtreceived);
		Double totalReceived=Double.parseDouble(totAmountReceived);
		
//		String totAmountToBePaid=df.format(totalAmtToBePaid);
//		Double totalAmtsToBePaid=Double.parseDouble(totAmountToBePaid);
		
		PdfPCell nameCell1 = new PdfPCell(new Phrase("Total Bal"+" "+" : "+"	"+totalReceived, title08)); 
		nameCell1.setColspan(3);
		nameCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell1.setBorder(0);
		totalQtyTable.addCell(nameCell1);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 

		
	
		
		//finalTable.addCell(supllierNameTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);
		document.add(totalQtyTable);
		}
		else {
		//finalTable.addCell(supllierNameTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);
		}

	}

}
