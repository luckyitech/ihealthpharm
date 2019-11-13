package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfBody;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SupplierByMfrList extends ReportsPDFUtility{

	
	@Override
		public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile) {
	    Document document = new Document(PageSize.A4, 36, 36, 50, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			document.open();
			addHeader(writer, document,model);
			addSupplierName(document, model, responseList);
			createTable(document, model, responseList);
			addFooter(writer, model,document); 

		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
			try {
				addMessage(document, ExceptionUtils.getMessage(e));
			} catch (DocumentException e1) {
				log.error(ExceptionUtils.getMessage(e1));
			}
		} finally {
			document.close();
		}
		
		return document;
		}
	
	private void addHeader(PdfWriter writer, Document document, ReportsMappingModel model) {  
		PdfPTable header = new PdfPTable(3);
		try {
			// add text
			PdfPCell leftContent = new PdfPCell();
			leftContent.setBorder(Rectangle.NO_BORDER);

			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.NO_BORDER);
			
			PdfPCell rightContent = new PdfPCell();
			rightContent.setBorder(Rectangle.NO_BORDER);
			
			String headerContent = model.getHeaderContent();
			if(!ObjectUtils.isEmpty(headerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(headerContent,HeaderFooterContentDto.class);				
				for(HeaderFooterContentDetailsDto dto:contentDto.getLeftContent()) {					
					leftContent.addElement(new Phrase(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize())));
					
				}
				
				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize()));
					phrase.setAlignment(1); 
					centerContent.addElement(phrase);					
				}
				
				for(HeaderFooterContentDetailsDto dto:contentDto.getRightContent()) {					
					rightContent.addElement(new Phrase(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize())));
					
				}
				
			}
			header.addCell(leftContent);
			header.addCell(centerContent);
			header.addCell(rightContent);
			header.setSpacingAfter(10);
			
			PdfPTable title = new PdfPTable(1);
			title.setWidthPercentage(50);
			PdfPCell titleCell = new PdfPCell(new Phrase(model.getTitle(), title08)); 
			titleCell.setColspan(3);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleCell.setVerticalAlignment(Element.ALIGN_CENTER);
			titleCell.setBorder(0);
			title.setSpacingAfter(10);
			title.addCell(titleCell);
			
			
			PdfPTable finalFeader = new PdfPTable(1);
			finalFeader.getDefaultCell().setBorder(0);
			header.getDefaultCell().setBorder(0);
			title.getDefaultCell().setBorder(0);

			finalFeader.addCell(header);
			finalFeader.addCell(title);
			document.add(finalFeader);
			
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}
	

	
	public void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList)
			throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		
		PdfPTable table = new PdfPTable(headerList.size());
		table.setWidthPercentage(50);
		table.setSpacingAfter(10);
		PdfPCell cell = null;
		for (HeaderDto hearder : headerList) {
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
		if (!ObjectUtils.isEmpty(responseList)) {
			for (Map<String, Object> rowData : responseList) {
				for (HeaderDto hearder : headerList) {
					Object value = rowData.containsKey(hearder.getColumnName()) ? rowData.get(hearder.getColumnName()): "";					
					cell = new PdfPCell(new Phrase(String.valueOf(value),title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if(!model.isShowVerticalLines())
					 cell.setBorder(Rectangle.BOTTOM);
					
					table.addCell(cell);

				}
			}
		}
		log.info("table width [{}]",table.getTotalWidth());
		document.add(table);

	}

	
	private void addSupplierName(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		String supplierName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("SP_NAME"));
		Font f1 = FontFactory.getFont(FontFactory.HELVETICA, 1);
		 Paragraph body=new Paragraph("SUPPLIER NAME:    "+supplierName);
		 body.setSpacingBefore(20);
		 body.setAlignment(1);
		 body.setSpacingAfter(25);
		 body.setFont(f1);
		 document.add(body);
		
	}

	
	private void addFooter(PdfWriter writer, ReportsMappingModel model,Document document) {
		PdfPTable footer = new PdfPTable(1);
		try {
			footer.setWidthPercentage(50);
			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.NO_BORDER);
			
			String footerContent = model.getFooterContent();
			if(!ObjectUtils.isEmpty(footerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(footerContent,HeaderFooterContentDto.class);				
				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize()));
					phrase.setAlignment(1); 
					centerContent.addElement(phrase);
				}
			}
			footer.addCell(centerContent);
			centerContent.setHorizontalAlignment(Element.ALIGN_CENTER);
			centerContent.setVerticalAlignment(Element.ALIGN_CENTER);
						
			footer.getDefaultCell().setBorder(0);
			document.add(footer);
		}  catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}

}
