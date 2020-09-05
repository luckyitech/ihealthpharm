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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Component
public class PurchaseOrderDetailsPdf  extends ReportsPDFUtility{
	
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
			
			//addHeader(writer, document,model,responseList);
	
		
			Map<String, List<Map<String, Object>>> purchaseOrderDetailsPONO = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("PURCHASE_ORDER_NO")));
			
			if(!ObjectUtils.isEmpty(purchaseOrderDetailsPONO)) { 
				
				for(String purchaseOrderNo :purchaseOrderDetailsPONO.keySet()) {	
					
					List<Map<String, Object>> purchaseOrderDetailsMap = purchaseOrderDetailsPONO.get(purchaseOrderNo);
				
					createTable(document,model,purchaseOrderDetailsMap,purchaseOrderNo);
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
	private void addHeader(PdfWriter writer, Document document,ReportsMappingModel model,List<Map<String, Object>> responseList) throws DocumentException {
		
		String grnNo = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("GRN_NO"));
		
		PdfPTable header = new PdfPTable(3);
		try {
			// set defaults
			header.setWidths(new int[] { 40,30 ,30});
			header.setTotalWidth(550);
			header.setLockedWidth(true);
			/*
			 * header.getDefaultCell().setFixedHeight(40);
			 * header.getDefaultCell().setBorder(Rectangle.BOTTOM);
			 * header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
			 */
			// add text
	PdfPCell leftContent = new PdfPCell();
			leftContent.setBorder(Rectangle.BOTTOM);
			/*
			 * leftContent.setPaddingBottom(15); leftContent.setPaddingLeft(10);
			 * leftContent.setBorder(Rectangle.BOTTOM);
			 * leftContent.setBorderColor(BaseColor.LIGHT_GRAY);
			 */
			

			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.BOTTOM);
			/*
			 * centerContent.setPaddingBottom(15);
			 * centerContent.setBorder(Rectangle.BOTTOM);
			 * centerContent.setBorderColor(BaseColor.LIGHT_GRAY);
			 */	
			
			PdfPCell rightContent = new PdfPCell();
			rightContent.setBorder(Rectangle.BOTTOM);
			/*
			 * rightContent.setPaddingBottom(15); rightContent.setPaddingRight(10);
			 * rightContent.setBorder(Rectangle.BOTTOM);
			 * rightContent.setBorderColor(BaseColor.LIGHT_GRAY);
			 */
			
			String headerContent = model.getHeaderContent();
//			System.out.println("------------------------------------------------------------");
//			System.out.println(headerContent);
//			System.out.println("------------------------------------------------------------");
			if(!ObjectUtils.isEmpty(headerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(headerContent,HeaderFooterContentDto.class);	
//				System.out.println(contentDto);
				for(HeaderFooterContentDetailsDto dto:contentDto.getLeftContent()) {		
					/*
					 * if(dto.getText().equalsIgnoreCase("DOCPHARMA LIMITED")) { String titleText =
					 * dto.getText(); titleText += "                                "+ "Grn No:";
					 * dto.setText(titleText); }
					 */
					leftContent.addElement(new Phrase(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize())));
					System.out.println(dto.getText());
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
		double subTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("TOTAL_VALUE") && !ObjectUtils.isEmpty(mapper.get("TOTAL_VALUE"))) ?String.valueOf(mapper.get("TOTAL_VALUE")):"0")).sum(); 
		double discount = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("DISC_AMT")&& !ObjectUtils.isEmpty(mapper.get("DISC_AMT")))?String.valueOf(mapper.get("DISC_AMT")):"0")).sum(); 
		double charges = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("OTHER_CHARGES")&& !ObjectUtils.isEmpty(mapper.get("OTHER_CHARGES")))?String.valueOf(mapper.get("OTHER_CHARGES")):"0")).sum(); 
		double totalVat = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("VAT_AMT")&& !ObjectUtils.isEmpty(mapper.get("VAT_AMT")))?String.valueOf(mapper.get("VAT_AMT")):"0")).sum(); 
		double net=(subTotal+totalVat+charges);
		
		
		DecimalFormat df=new DecimalFormat("0.00");
		String doubleAamt=df.format(net);
		double netTotal=Double.parseDouble(doubleAamt); 
		
		String doubleSubTotal=df.format(subTotal);
		double subTotals=Double.parseDouble(doubleSubTotal); 
		
		String vatTot=df.format(totalVat);
		double vatFinal=Double.parseDouble(vatTot); 

		PdfPTable totalQtyTable = new PdfPTable(3);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell = new PdfPCell(new Phrase("SUB TOTAL : "+subTotals, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalQtyTable.addCell(nameCell);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell2 = new PdfPCell(new Phrase("MISC COST : "+charges, title08)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalQtyTable.addCell(nameCell2);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell3 = new PdfPCell(new Phrase("VAT : "+vatFinal, title08)); 
		nameCell3.setColspan(3);
		nameCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell3.setBorder(0);
		totalQtyTable.addCell(nameCell3);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		PdfPCell nameCell4 = new PdfPCell(new Phrase("DISCOUNT : "+discount, title08)); 
		nameCell4.setColspan(3);
		nameCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell4.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell4.setBorder(0);
		totalQtyTable.addCell(nameCell4);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		
		PdfPCell nameCell5 = new PdfPCell(new Phrase("NET TOTAL : "+netTotal, title08)); 
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

	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> purchaseOrderDetailsList,
			String poNo) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		
		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		String supplierName=String.valueOf(purchaseOrderDetailsList.get(0).get("SP_NAME"));
		String address=String.valueOf(purchaseOrderDetailsList.get(0).get("CITY_NM"));
		String poDate=formatter.format(purchaseOrderDetailsList.get(0).get("PURCHASE_ORDER_DT"));

		PdfPTable supllierNameTable = new PdfPTable(2);
//		PdfPCell nameCell = new PdfPCell(new Phrase("Supplier Name  : "+supplierName+  "      "
//				+ "GRN NO   :  "+grnNo+"          "+"Invoice Date   : "+invoiceDates, title08)); 
		
		PdfPCell nameCell = new PdfPCell(new Phrase("Supplier Name  : "+supplierName+  "       "
				+ "PO No  :  "+poNo+"         "+"PO Date   : "+poDate, title08)); 
		
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		supllierNameTable.addCell(nameCell);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell1 = new PdfPCell(new Phrase("Address  : "+address, title08)); 
		nameCell1.setColspan(3);
		nameCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell1.setBorder(0);
		supllierNameTable.addCell(nameCell1);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 
		
		
		
		PdfPTable table = new PdfPTable(13);
		table.setTotalWidth(500);
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
			headerCell.add("QTN NO");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);
		
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("ITEM NAME");
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
			headerCell.add("DISC AMT");
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
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(purchaseOrderDetailsList)) {
			for (Map<String, Object> rowData : purchaseOrderDetailsList) {
				//for (HeaderDto hearder : headerList) {


				Object value = String.valueOf(purchaseOrderDetailsList.indexOf(rowData) + 1);
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("QUOTATION_NO") ? rowData.get("QUOTATION_NO") : "";
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
				
				
				value = rowData.containsKey("QUANTITY") ? rowData.get("QUANTITY") : "";
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
				
				value = rowData.containsKey("DISCOUNT") ? rowData.get("DISCOUNT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("DISC_AMT") ? rowData.get("DISC_AMT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				
				value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
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
				
				value = rowData.containsKey("APP_BY") ? rowData.get("APP_BY") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("EMP_NM") ? rowData.get("EMP_NM") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("EMP_MODIFIED") ? rowData.get("EMP_MODIFIED") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				

			}
		}
		
		finalTable.addCell(supllierNameTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);

	}

	
}
