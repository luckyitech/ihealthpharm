package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Date;

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
public class AccountReceivablesPdf extends ReportsPDFUtility{
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {
		
		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		 Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();
			Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
			createTable(document,model,responseList,dataMap);
			generateTotalTable(document,model,responseList);

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
		double totalAmtreceived;
		double totalAmtToBereceived;
		totalAmtreceived  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("AMOUNT_RECEIVED") && !ObjectUtils.isEmpty(mapper.get("AMOUNT_RECEIVED"))) ?String.valueOf(mapper.get("AMOUNT_RECEIVED")):"0")).sum();    
		totalAmtToBereceived  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("AMOUNT_TO_BE_RECEIVED") && !ObjectUtils.isEmpty(mapper.get("AMOUNT_TO_BE_RECEIVED"))) ?String.valueOf(mapper.get("AMOUNT_TO_BE_RECEIVED")):"0")).sum();   
		PdfPTable totalQtyTable = new PdfPTable(2);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		String totAmountReceived=df.format(totalAmtreceived);
		Double totalReceived=Double.parseDouble(totAmountReceived);
		
		String totAmountToBeReceived=df.format(totalAmtToBereceived);
		Double totalToBePaid=Double.parseDouble(totAmountToBeReceived);
		
		PdfPCell nameCell = new PdfPCell(new Phrase("Total Amount Received"+" "+" : "+"	"+totalReceived, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalQtyTable.addCell(nameCell);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell2 = new PdfPCell(new Phrase("Total Amount To Be Received"+"	"+" : "+"	"+totalToBePaid, title08)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalQtyTable.addCell(nameCell2);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		document.add(totalQtyTable);
	
	}
	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> accountPayablesList,Map<String,Object> dataMap) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		
		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 
		
//		PdfPTable supllierNameTable = new PdfPTable(2);
//		PdfPCell nameCell = new PdfPCell(new Phrase("Product Name : "+itemName, title08)); 
//		nameCell.setColspan(3);
//		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
//		nameCell.setBorder(0);
//		supllierNameTable.addCell(nameCell);
//		supllierNameTable.setLockedWidth(true);
//		supllierNameTable.setTotalWidth(500);
//		supllierNameTable.getDefaultCell().setBorder(0); 
		
		
		if(dataMap.get("FROM_APPROVED_DATE")!=null && dataMap.get("TO_APPROVED_DATE")!=null) {
			
		System.out.println(dataMap.get("FROM_APPROVED_DATE"));
//			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//			System.out.println("************");
//			String fromDates=f.format(dataMap.get("FROM_APPROVED_DATE"));
//			String toDates=f.format(dataMap.get("FROM_APPROVED_DATE"));
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//			String fromDates=formatter.format(String.valueOf(dataMap.get("FROM_APPROVED_DATE")));
//		String toDates=formatter.format(String.valueOf(dataMap.get("TO_APPROVED_DATE")));
			
			PdfPTable supllierNameTable = new PdfPTable(3);
			PdfPCell nameCell = new PdfPCell(new Phrase("From Date : "+String.valueOf((dataMap.get("FROM_APPROVED_DATE")))+"          "+"To Date   : "+String.valueOf(dataMap.get("TO_APPROVED_DATE")), title08)); 
			//PdfPCell nameCell = new PdfPCell(new Phrase("From Date : "+fromDates+"        "+"To Date   : "+toDates, title08)); 
			nameCell.setColspan(3);
			nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			nameCell.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell.setBorder(0);
			supllierNameTable.addCell(nameCell);
			supllierNameTable.setLockedWidth(true);
			supllierNameTable.setTotalWidth(500);
			supllierNameTable.getDefaultCell().setBorder(0);
		
		
		PdfPTable table = new PdfPTable(18);
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
			headerCell.add("CUSTOMER NAME");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("RECEIPT NO");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("SOURCE REF");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("RECEIPT DATE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("STATUS");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("AMT REC");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("AMT TO BE REC");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("PAY STATUS");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("SRC TYPE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("PAY TYPE");
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
			headerCell.add("CHEQUE/CARD/UPI NO");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("CHEQUE/CARD/UPI AUTH CODE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("APPROVED BY");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("APPROVED DATE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("CREATED BY");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("MODIFIED BY");
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
				//for (HeaderDto hearder : headerList) {

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
				
				value = rowData.containsKey("RECEIPT_NO") ? rowData.get("RECEIPT_NO") : "";
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
				
				value = rowData.containsKey("RECEIPT_DATE") ? rowData.get("RECEIPT_DATE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("STATUS") ? rowData.get("STATUS") : "";
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
				
				value = rowData.containsKey("AMOUNT_TO_BE_RECEIVED") ? rowData.get("AMOUNT_TO_BE_RECEIVED") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
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
				
				value = rowData.containsKey("TYPE") ? rowData.get("TYPE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("PAY_TYPE_AMOUNT") ? rowData.get("PAY_TYPE_AMOUNT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("REF_NO") ? rowData.get("REF_NO") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("AUTH_CODE_DATE") ? rowData.get("AUTH_CODE_DATE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("FIRST_NM") ? rowData.get("FIRST_NM") : "";
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
				
				value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("MODIFIED_BY") ? rowData.get("MODIFIED_BY") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);


				//}
			}
		}
		
		//finalTable.addCell(finalTable);
		finalTable.addCell(supllierNameTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);

	}
		else {
			PdfPTable table = new PdfPTable(18);
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
				headerCell.add("CUSTOMER NAME");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("RECEIPT NO");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("SOURCE REF");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("RECEIPT DATE");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("STATUS");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("AMT REC");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("AMT TO BE REC");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("PAY STATUS");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("SRC TYPE");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("PAY TYPE");
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
				headerCell.add("CHEQUE/CARD/UPI NO");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				

				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("CHEQUE/CARD/UPI AUTH CODE");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("APPROVED BY");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("APPROVED DATE");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("CREATED BY");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("MODIFIED BY");
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
					//for (HeaderDto hearder : headerList) {

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
					
					value = rowData.containsKey("RECEIPT_NO") ? rowData.get("RECEIPT_NO") : "";
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
					
					value = rowData.containsKey("RECEIPT_DATE") ? rowData.get("RECEIPT_DATE") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("STATUS") ? rowData.get("STATUS") : "";
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
					
					value = rowData.containsKey("AMOUNT_TO_BE_RECEIVED") ? rowData.get("AMOUNT_TO_BE_RECEIVED") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
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
					
					value = rowData.containsKey("TYPE") ? rowData.get("TYPE") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("PAY_TYPE_AMOUNT") ? rowData.get("PAY_TYPE_AMOUNT") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("REF_NO") ? rowData.get("REF_NO") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("AUTH_CODE_DATE") ? rowData.get("AUTH_CODE_DATE") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("FIRST_NM") ? rowData.get("FIRST_NM") : "";
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

					value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("MODIFIED_BY") ? rowData.get("MODIFIED_BY") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					//}
				}
			}
			
			//finalTable.addCell(finalTable);
		
			finalTable.addCell(table); 
			//log.info("table width [{}]", table.getTotalWidth());
			document.add(finalTable);
		}
	
	}
}
