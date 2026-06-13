package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PrintQuotationReceiptPdf extends ReportsPDFUtility{

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


			Map<String, List<Map<String, Object>>> purchaseOrderDetails = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("SP_NAME")));

			if(!ObjectUtils.isEmpty(purchaseOrderDetails)) { 

				for(String itemName :purchaseOrderDetails.keySet()) {
					
					addHeader(writer, document,model,responseList);
					List<Map<String, Object>> purchaseOrderDetailsMap = purchaseOrderDetails.get(itemName);
					createTable(document,model,purchaseOrderDetailsMap,itemName);
					document.newPage();
				}

				//generateTotalTable(document,model,responseList);


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

		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
		PdfPTable header = new PdfPTable(3);
		try {
			// set defaults
			header.setWidths(new int[] { 40,30 ,30});
			header.setTotalWidth(500);
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
					
					Font bold  = new Font(FontFamily.HELVETICA, dto.getSize(), Font.BOLD);
					if(dto.getText().contains("Date")){
						leftContent.addElement(new Phrase(dto.getText()+" "+dateFormat.format(new Date()),bold));
					}else if(dto.getText().contains("Quotation")){
						leftContent.addElement(new Phrase(dto.getText()+" "+responseList.get(0).get("QUOTATION_NO"),bold));
					}else {
					leftContent.addElement(new Phrase(dto.getText(), bold));
					}
					leftContent.setPaddingBottom(5);
					

				}

				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {
					Font bold  = new Font(FontFamily.HELVETICA, dto.getSize(), Font.BOLD);
					centerContent.addElement(new Phrase(dto.getText(),bold));

				}

				

				for(HeaderFooterContentDetailsDto dto:contentDto.getRightContent()) {
					Font bold  = new Font(FontFamily.HELVETICA, dto.getSize(), Font.BOLD);
					rightContent.addElement(new Phrase(dto.getText(), bold));
					//rightContent.addElement(new Phrase(String.format("GRN NO:", writer.getPageNumber()), FontFactory.getFont(dto.getFontName(), dto.getSize())));
				}


			}

			header.addCell(leftContent);
			header.addCell(centerContent);
			header.addCell(rightContent);

		

			PdfPTable finalFeader = new PdfPTable(1);
			finalFeader.setTotalWidth(500);
			finalFeader.setWidthPercentage(100);
			finalFeader.getDefaultCell().setBorder(0);
			finalFeader.setLockedWidth(true);
			header.getDefaultCell().setBorder(0);
			
			//finalFeader.addCell(title);
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


	
	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> quotationDetailsList,
			String supplier) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);


		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 


		PdfPTable supllierNameTable = new PdfPTable(4);
		
		PdfPTable supplierDetailTable = new PdfPTable(2);
		
		Font bold  = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
		
		PdfPCell nameCell = new PdfPCell(new Phrase("Supplier  : "+supplier,bold)); 
		PdfPCell nameCell1 = new PdfPCell(new Phrase("Address  : "+quotationDetailsList.get(0).get("ADDRESS"),bold)); 
		PdfPCell nameCell2 = new PdfPCell(new Phrase("Phone  : "+quotationDetailsList.get(0).get("PHONE_NBR"),bold)); 
		PdfPCell nameCell3 = new PdfPCell(new Phrase("Qtn No  : "+quotationDetailsList.get(0).get("QUOTATION_NO"),bold)); 
		PdfPCell nameCell4 = new PdfPCell(new Phrase("Requested By  : "+quotationDetailsList.get(0).get("REQUESTED_BY"),title08)); 
		
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		
		nameCell1.setColspan(3);
		nameCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell1.setBorder(0);
		
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		
		nameCell3.setColspan(3);
		nameCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell3.setBorder(0);
		
		nameCell4.setColspan(3);
		nameCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell4.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell4.setBorder(0);
		
		
		
		supllierNameTable.addCell(nameCell);
		supllierNameTable.addCell(nameCell4);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 
		
		supplierDetailTable.addCell(nameCell1);
		supplierDetailTable.addCell(nameCell2);
		supplierDetailTable.addCell(nameCell3);
		supplierDetailTable.setLockedWidth(true);
		supplierDetailTable.setTotalWidth(500);
		supplierDetailTable.getDefaultCell().setBorder(0); 
		

		PdfPTable table = new PdfPTable(headerList.size());
		table.setTotalWidth(500);
		table.setWidthPercentage(50);
		table.setLockedWidth(true);
		PdfPCell cell = null;

		for (HeaderDto hearder : headerList) {
			Paragraph headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add(hearder.getDisplayName());
			//headerCell.setFont(FontFactory.getFont("Helvetica",7,Font.BOLD));

			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if(!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);


			table.addCell(cell);
		}
		

		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(quotationDetailsList)) {
			for (Map<String, Object> rowData : quotationDetailsList) {
				for (HeaderDto hearder : headerList) {

					Object value = rowData.containsKey(hearder.getColumnName()) ? rowData.get(hearder.getColumnName()): "";		
					if(ObjectUtils.isEmpty(value) && true) {


					}
				
					cell = new PdfPCell(new Phrase(String.valueOf(value),title08));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if(!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);


				}
			}
		}

		finalTable.addCell(supllierNameTable);
		finalTable.addCell(supplierDetailTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);

	}
}
