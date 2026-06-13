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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@Component
public class SupplierStatementPdf extends ReportsPDFUtility{
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
			Map<String, List<Map<String, Object>>> supplierstmtMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("SP_NAME")));			
			
			
			if(!ObjectUtils.isEmpty(supplierstmtMap)) { 
				
				for(String supplierSummary :supplierstmtMap.keySet()) {					
					List<Map<String, Object>> productList = supplierstmtMap.get(supplierSummary);
					
					createTable(document,model,productList,supplierSummary,dataMap);
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
		DecimalFormat df=new DecimalFormat("0.00");
		double totalAmtpaid;
		//double totalAmtToBePaid;
		totalAmtpaid  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("balance") && !ObjectUtils.isEmpty(mapper.get("balance"))) ?String.valueOf(mapper.get("balance")):"0")).sum();  
		//totalAmtToBePaid  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("TOTAL_AMOUNT_TO_BE_PAID") && !ObjectUtils.isEmpty(mapper.get("TOTAL_AMOUNT_TO_BE_PAID"))) ?String.valueOf(mapper.get("TOTAL_AMOUNT_TO_BE_PAID")):"0")).sum();   
		PdfPTable totalQtyTable = new PdfPTable(2);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		String totAmountPaid=df.format(totalAmtpaid);
		Double totalPaid=Double.parseDouble(totAmountPaid);
		
//		String totAmountToBePaid=df.format(totalAmtToBePaid);
//		Double totalAmtsToBePaid=Double.parseDouble(totAmountToBePaid);
		
		PdfPCell nameCell = new PdfPCell(new Phrase("Total Balance"+" "+" : "+"	"+totalPaid, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalQtyTable.addCell(nameCell);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
//		
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
	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> accountPayablesList,String supplierSummary,Map<String,Object> dataMap) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		
		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 
		
		PdfPTable supllierNameTable = new PdfPTable(3);
		PdfPCell nameCell = new PdfPCell(new Phrase("Supplier Name : "+supplierSummary, title08)); 
		//PdfPCell nameCell = new PdfPCell(new Phrase("From Date : "+fromDates+"        "+"To Date   : "+toDates, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		supllierNameTable.addCell(nameCell);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0);
		
		
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
			headerCell.add("DATE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("REF NO");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("TYPE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("INVOICE NO");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("DEBIT AMT");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("CREDIT AMT");
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
		if (!ObjectUtils.isEmpty(accountPayablesList)) {
			for (Map<String, Object> rowData : accountPayablesList) {
				

				Object value = String.valueOf(accountPayablesList.indexOf(rowData) + 1);
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("FROM_APPROVED_DATE") ? rowData.get("FROM_APPROVED_DATE") : "";
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
				
				
				value = rowData.containsKey("SOURCE_TYPE") ? rowData.get("SOURCE_TYPE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("INVOICE_NO") ? rowData.get("INVOICE_NO") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				
				
				value = rowData.containsKey("debit") ? rowData.get("debit") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("credit") ? rowData.get("credit") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("balance") ? rowData.get("balance") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
		
				

				
		


				
			}
		}
		
		if(dataMap.get("FROM_APPROVED_DATE")!=null && dataMap.get("TO_APPROVED_DATE")!=null && dataMap.get("SP_NAME")==null) {
		DecimalFormat df=new DecimalFormat("0.00");
		double totalAmtpaid;
		//double totalAmtToBePaid;
		totalAmtpaid  = accountPayablesList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("balance") && !ObjectUtils.isEmpty(mapper.get("balance"))) ?String.valueOf(mapper.get("balance")):"0")).sum();  
		//totalAmtToBePaid  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("TOTAL_AMOUNT_TO_BE_PAID") && !ObjectUtils.isEmpty(mapper.get("TOTAL_AMOUNT_TO_BE_PAID"))) ?String.valueOf(mapper.get("TOTAL_AMOUNT_TO_BE_PAID")):"0")).sum();   
		PdfPTable totalQtyTable = new PdfPTable(2);
		totalQtyTable.setTotalWidth(500);
//		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		String totAmountPaid=df.format(totalAmtpaid);
		Double totalPaid=Double.parseDouble(totAmountPaid);
		
//		String totAmountToBePaid=df.format(totalAmtToBePaid);
//		Double totalAmtsToBePaid=Double.parseDouble(totAmountToBePaid);
		
		PdfPCell nameCell1 = new PdfPCell(new Phrase("Total Bal"+" "+" : "+"	"+totalPaid, title08)); 
		nameCell1.setColspan(3);
		nameCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell1.setBorder(0);
		totalQtyTable.addCell(nameCell1);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 

		
	
		
		finalTable.addCell(supllierNameTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);
		document.add(totalQtyTable);
		}
		else {
		finalTable.addCell(supllierNameTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);
		}

	}


}
