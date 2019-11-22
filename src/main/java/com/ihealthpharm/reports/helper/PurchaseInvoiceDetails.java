package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
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
public class PurchaseInvoiceDetails extends ReportsPDFUtility{
	
//	String invoiceNo;
//	String invoiceDate;
//	String supplierName;
	
	
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile) {
		
		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		 Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();
			
			Map<String, List<Map<String, Object>>> purchaseInvoiceDetails = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("SP_NAME")));
			Map<String, List<Map<String, Object>>> purchaseInvoiceDetailsInv = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("INVOICE_NO")));
			Map<Date, List<Map<String, Object>>> purchaseInvoiceDetailsInvDate = responseList.stream()
					.collect(Collectors.groupingBy(map -> (Date) map.get("INVOICE_DT")));
			
			if(!ObjectUtils.isEmpty(purchaseInvoiceDetails)) { 
				String suppName = null;
				String invNo=null;
				for(String supplierName :purchaseInvoiceDetails.keySet()) {	
					List<Map<String, Object>> purchaseInvoiceDetailsMap = purchaseInvoiceDetails.get(supplierName);
					suppName=supplierName;
					//createTable(document,model,purchaseInvoiceDetailsMap,supplierName);
				}
				for(String invoiceNo :purchaseInvoiceDetailsInv.keySet()) {	
					invNo=invoiceNo;
					List<Map<String, Object>> purchaseInvoiceDetailsMap = purchaseInvoiceDetails.get(suppName);
					//createTable(document,model,purchaseInvoiceDetailsMap,suppName,invoiceNo);
				}
				for(Date invoiceDate :purchaseInvoiceDetailsInvDate.keySet()) {	
					List<Map<String, Object>> purchaseInvoiceDetailsMap = purchaseInvoiceDetails.get(suppName);
					createTable(document,model,purchaseInvoiceDetailsMap,suppName,invNo,invoiceDate);
				}
				
				generateTotalTable(document,model,responseList);
			}

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
		
		double grossTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_VALUE")?String.valueOf(mapper.get("TOTAL_VALUE")):"0")).sum(); 
		double netTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("ACTUAL_VALUE")?String.valueOf(mapper.get("ACTUAL_VALUE")):"0")).sum(); 
		double discount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("DISCOUNT")?String.valueOf(mapper.get("DISCOUNT")):"0")).sum(); 
		double charges=responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("HANDLING_CHARGES")?String.valueOf(mapper.get("HANDLING_CHARGES")):"0")).sum(); 
		
		PdfPTable totalQtyTable = new PdfPTable(3);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell = new PdfPCell(new Phrase("GROSS TOTAL : "+grossTotal, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalQtyTable.addCell(nameCell);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell2 = new PdfPCell(new Phrase("MISC. COST : "+charges, title08)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalQtyTable.addCell(nameCell2);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell3 = new PdfPCell(new Phrase("DISCOUNT : "+discount, title08)); 
		nameCell3.setColspan(3);
		nameCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell3.setBorder(0);
		totalQtyTable.addCell(nameCell3);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		PdfPCell nameCell4 = new PdfPCell(new Phrase("NET TOTAL : "+netTotal, title08)); 
		nameCell4.setColspan(3);
		nameCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell4.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell4.setBorder(0);
		totalQtyTable.addCell(nameCell4);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		
		document.add(totalQtyTable);
		
		
		
		
	}

	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> purchaseInvoiceDetailsList,
			String supplierName,String invoiceNo,Date invoiceDate) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		
		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 
		
		PdfPTable supllierNameTable = new PdfPTable(2);
		PdfPCell nameCell = new PdfPCell(new Phrase("Supplier Name  : "+supplierName+  "      "
				+ "Invoice NO   :  "+invoiceNo+"          "+"Invoice Date   : "+invoiceDate, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		supllierNameTable.addCell(nameCell);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 
		
		
		PdfPTable table = new PdfPTable(11);
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
		
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("PRODUCT NAME");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("BATCH NO.");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("QTY");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("EXPIRY");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("FREE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("UNIT PRICE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("GROSS AMT");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("DISC");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("VAT%");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("NET AMT");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			
		//}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(purchaseInvoiceDetailsList)) {
			for (Map<String, Object> rowData : purchaseInvoiceDetailsList) {
				//for (HeaderDto hearder : headerList) {


				Object value = String.valueOf(purchaseInvoiceDetailsList.indexOf(rowData) + 1);
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("QUANTITY_APPROVED") ? rowData.get("QUANTITY_APPROVED") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("BONUS") ? rowData.get("BONUS") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("UNIT_RATE") ? rowData.get("UNIT_RATE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("TOTAL_VALUE") ? rowData.get("TOTAL_VALUE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				

				value = rowData.containsKey("DISCOUNT") ? rowData.get("DISCOUNT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("VAT") ? rowData.get("VAT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("ACTUAL_VALUE") ? rowData.get("ACTUAL_VALUE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				//}
			}
		}
		
		finalTable.addCell(supllierNameTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);

	}


}
