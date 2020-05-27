package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.DateUtility;
import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.dto.HeaderFooterContentDetailsDto;
import com.ihealthpharm.reports.dto.HeaderFooterContentDto;
import com.ihealthpharm.reports.model.ReportsMappingModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Component
public class PurchaseInvoiceDetails extends ReportsPDFUtility{

	//	String invoiceNo;
	//	String invoiceDate;
	//	String supplierName;
	//	private PdfTemplate t;
	//	private Image total;


	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();

			addHeader(writer, document,model,responseList);


			Map<String, List<Map<String, Object>>> purchaseInvoiceDetails = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("SP_NAME")));
			Map<String, List<Map<String, Object>>> purchaseInvoiceDetailsAdd = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("CITY_NM"))); 
			//			Map<String, List<Map<String, Object>>> purchaseInvoiceDetailsInv = responseList.stream()
			//					.collect(Collectors.groupingBy(map -> (String) map.get("GRN_NO")));
			Map<String, List<Map<String, Object>>> purchaseInvoiceDetailsInv = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("INVOICE_NO")));
			Map<Date, List<Map<String, Object>>> purchaseInvoiceDetailsInvDate = responseList.stream()
					.collect(Collectors.groupingBy(map -> (Date) map.get("INVOICE_DT")));


			if(!ObjectUtils.isEmpty(purchaseInvoiceDetails)) { 
				String suppName = null;
				String invNo=null;
				String location=null;

				for(String supplierName :purchaseInvoiceDetails.keySet()) {	
					List<Map<String, Object>> purchaseInvoiceDetailsMap = purchaseInvoiceDetails.get(supplierName);
					suppName=supplierName;
					//createTable(document,model,purchaseInvoiceDetailsMap,supplierName);
				}
				for(String address :purchaseInvoiceDetailsAdd.keySet()) {	
					location=address;
					List<Map<String, Object>> purchaseInvoiceDetailsMap = purchaseInvoiceDetails.get(suppName);
					//					List<Map<String, Object>> purchaseInvoiceDetailsMap = purchaseInvoiceDetails.get(address);

					//createTable(document,model,purchaseInvoiceDetailsMap,suppName,invoiceNo);
				}
				for(String invoiceNo :purchaseInvoiceDetailsInv.keySet()) {	
					invNo=invoiceNo;
					List<Map<String, Object>> purchaseInvoiceDetailsMap = purchaseInvoiceDetails.get(suppName);
					//					List<Map<String, Object>> purchaseInvoiceDetailsMap = purchaseInvoiceDetails.get(invoiceNo);

					//createTable(document,model,purchaseInvoiceDetailsMap,suppName,invoiceNo);
				}

				for(Date invoiceDate :purchaseInvoiceDetailsInvDate.keySet()) {	
					List<Map<String, Object>> purchaseInvoiceDetailsMap = purchaseInvoiceDetails.get(suppName);
					createTable(document,model,purchaseInvoiceDetailsMap,suppName,location,invNo,invoiceDate);

				}
				//				List<Map<String, Object>> purchaseInvoiceDetailsMap = null;
				//				createTable(document,model,purchaseInvoiceDetailsMap,suppName,location,invNo,invDate);
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
	private void addHeader(PdfWriter writer, Document document,ReportsMappingModel model,List<Map<String, Object>> responseList) throws DocumentException {

		String grnNo = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("GRN_NO"));

		PdfPTable header = new PdfPTable(3);
		try {
			// set defaults
			header.setWidths(new int[] { 40,30 ,30});
			header.setTotalWidth(550);
			header.setLockedWidth(true);
			
			PdfPCell leftContent = new PdfPCell();
			leftContent.setBorder(Rectangle.BOTTOM);
			

			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.BOTTOM);
			

			PdfPCell rightContent = new PdfPCell();
			rightContent.setBorder(Rectangle.BOTTOM);
			
			String headerContent = model.getHeaderContent();
			if(!ObjectUtils.isEmpty(headerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(headerContent,HeaderFooterContentDto.class);	
				for(HeaderFooterContentDetailsDto dto:contentDto.getLeftContent()) {		
					leftContent.addElement(new Phrase(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize())));
					leftContent.setPaddingBottom(5);

				}

				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {					
					centerContent.addElement(new Phrase(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize())));

				}

				HeaderFooterContentDetailsDto grnData = new HeaderFooterContentDetailsDto();
				grnData.setFontName("Helvetica");
				grnData.setSize(12);
				grnData.setText("  GRN No:"+grnNo);
				contentDto.getRightContent().set(0, grnData);

				for(HeaderFooterContentDetailsDto dto:contentDto.getRightContent()) {					
					rightContent.addElement(new Phrase(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize())));
					//rightContent.addElement(new Phrase(String.format("GRN NO:", writer.getPageNumber()), FontFactory.getFont(dto.getFontName(), dto.getSize())));
				}


			}

			header.addCell(leftContent);
			header.addCell(centerContent);
			header.addCell(rightContent);

			PdfPTable title = new PdfPTable(3);
			title.setTotalWidth(550);
			title.setWidthPercentage(100);
			title.getDefaultCell().setFixedHeight(40);
			title.setLockedWidth(true);

			PdfPCell titleCell = new PdfPCell(new Phrase(model.getTitle())); 
			titleCell.setColspan(3);
			titleCell.setPaddingBottom(5);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleCell.setVerticalAlignment(Element.ALIGN_TOP);

			titleCell.setBorder(Rectangle.NO_BORDER);
			titleCell.setBorderColor(BaseColor.LIGHT_GRAY);
			title.addCell(titleCell);


			PdfPTable finalFeader = new PdfPTable(1);
			finalFeader.setTotalWidth(550);
			finalFeader.setWidthPercentage(100);
			finalFeader.getDefaultCell().setBorder(0);
			finalFeader.setLockedWidth(true);
			header.getDefaultCell().setBorder(0);
			title.getDefaultCell().setBorder(0);	

			finalFeader.addCell(title);
			finalFeader.addCell(header);


			// write content
			// header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
			finalFeader.writeSelectedRows(0, -1, document.left(),
					document.top() + ((document.topMargin() + header.getTotalHeight()) / 2), writer.getDirectContent());




		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}


	private void generateTotalTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		DecimalFormat df=new DecimalFormat("0.00");

		double subTotals = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("TOTAL_VALUE") && !ObjectUtils.isEmpty(mapper.get("TOTAL_VALUE"))) ?String.valueOf(mapper.get("TOTAL_VALUE")):"0")).sum(); 
		double discount = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("DISCOUNT")&& !ObjectUtils.isEmpty(mapper.get("DISCOUNT")))?String.valueOf(mapper.get("DISCOUNT")):"0")).sum(); 
		//double charges = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("HANDLING_CHARGES")&& !ObjectUtils.isEmpty(mapper.get("HANDLING_CHARGES")))?String.valueOf(mapper.get("HANDLING_CHARGES")):"0")).sum(); 
		double charges = Double.parseDouble(String.valueOf(responseList.get(0).get("HANDLING_CHARGES")));

		double vatTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("VAT_AMT")&& !ObjectUtils.isEmpty(mapper.get("VAT_AMT")))?String.valueOf(mapper.get("VAT_AMT")):"0")).sum(); 
		
		double netAmt=(subTotals+vatTotal+charges);

		String net=df.format(netAmt);
		String sub=df.format(subTotals);
		String vat=df.format(vatTotal);
		Double subTotal=Double.parseDouble(sub);
		Double netTotal=Double.parseDouble(net);
		Double totalVat=Double.parseDouble(vat);

		PdfPTable totalQtyTable = new PdfPTable(3);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 

		Font bold = new Font(FontFamily.HELVETICA,9);
		
		PdfPCell nameCell = new PdfPCell(new Phrase("SUB TOTAL : "+subTotal, bold)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalQtyTable.addCell(nameCell);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 

		PdfPCell nameCell2 = new PdfPCell(new Phrase("MISC COST : "+charges, bold)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalQtyTable.addCell(nameCell2);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 

		PdfPCell nameCell3 = new PdfPCell(new Phrase("VAT : "+totalVat, bold)); 
		nameCell3.setColspan(3);
		nameCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell3.setBorder(0);
		totalQtyTable.addCell(nameCell3);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);

		PdfPCell nameCell4 = new PdfPCell(new Phrase("DISCOUNT : "+discount, bold)); 
		nameCell4.setColspan(3);
		nameCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell4.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell4.setBorder(0);
		totalQtyTable.addCell(nameCell4);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);


		PdfPCell nameCell5 = new PdfPCell(new Phrase("NET TOTAL : "+netTotal, bold)); 
		nameCell5.setColspan(3);
		nameCell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell5.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell5.setBorder(0);
		totalQtyTable.addCell(nameCell5);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);


		document.add(totalQtyTable);




	}

	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> purchaseInvoiceDetailsList,
			String supplierName,String address,String invoiceNo,Date invoiceDate) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);
		Font bold = new Font(FontFamily.HELVETICA,9);

		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String invoiceDates=formatter.format(invoiceDate);

		PdfPTable supllierNameTable = new PdfPTable(2);
		//		PdfPCell nameCell = new PdfPCell(new Phrase("Supplier Name  : "+supplierName+  "      "
		//				+ "GRN NO   :  "+grnNo+"          "+"Invoice Date   : "+invoiceDates, title08)); 

		PdfPCell nameCell = new PdfPCell(new Phrase("Supplier Name  : "+supplierName+  "       "
				+ "Invoice No  :  "+invoiceNo+"         "+"Invoice Date   : "+invoiceDates, bold)); 

		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		supllierNameTable.addCell(nameCell);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 

		PdfPCell nameCell1 = new PdfPCell(new Phrase("Address  : "+address, bold)); 
		nameCell1.setColspan(3);
		nameCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell1.setBorder(0);
		supllierNameTable.addCell(nameCell1);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 



		PdfPTable table = new PdfPTable(10);
		table.setTotalWidth(500);
		table.setWidths(new int[] {30,90,50,60,30,40,50,50,30,60});
		table.setWidthPercentage(50);
		table.setLockedWidth(true);
		PdfPCell cell = null;

		//for (HeaderDto hearder : headerList) {

		Paragraph headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("S.NO");
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
		headerCell.add("BATCH");
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
		headerCell.add("QTY");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("BONUS");
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
		headerCell.add("DISC%");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("VAT");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("TOTAL AMT");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		//			headerCell = new Paragraph();
		//			headerCell.setFont(headerFont);
		//			headerCell.add("CHARGES");
		//			cell = new PdfPCell(headerCell);
		//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		//			if (!model.isShowVerticalLines())
		//				cell.setBorder(Rectangle.BOTTOM);
		//			
		//			table.addCell(cell);

		//}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(purchaseInvoiceDetailsList)) {
			for (Map<String, Object> rowData : purchaseInvoiceDetailsList) {
				//for (HeaderDto hearder : headerList) {


				Object value = String.valueOf(purchaseInvoiceDetailsList.indexOf(rowData) + 1);
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("EXPIRY_DT") ? rowData.get("EXPIRY_DT") : "";
				SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
				String values=formatter1.format(value);
				cell = new PdfPCell(new Phrase(values, bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("QUANTITY_APPROVED") ? rowData.get("QUANTITY_APPROVED") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);



				value = rowData.containsKey("BONUS") ? rowData.get("BONUS") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("UNIT_RATE") ? rowData.get("UNIT_RATE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("DISCOUNT") ? rowData.get("DISCOUNT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("TOTAL_VALUE") ? rowData.get("TOTAL_VALUE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				//				value = rowData.containsKey("HANDLING_CHARGES") ? rowData.get("HANDLING_CHARGES") : "";
				//				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				//				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				//				if (!model.isShowVerticalLines())
				//					cell.setBorder(Rectangle.BOTTOM);
				//
				//				table.addCell(cell);

				//}
			}
		}

		finalTable.addCell(supllierNameTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);

	}


}
