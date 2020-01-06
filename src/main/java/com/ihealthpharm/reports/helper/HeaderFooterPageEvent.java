package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.DateUtility;
import com.ihealthpharm.commons.JsonUtility;
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
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeaderFooterPageEvent extends PdfPageEventHelper {

	private PdfTemplate t;
	private Image total;
	private ReportsMappingModel reportsMappingModel;
	
	private Font title12 = FontFactory.getFont(Font.FontFamily.HELVETICA.toString(), 12);
	private Font title08 = FontFactory.getFont(Font.FontFamily.HELVETICA.toString(), 8);

	
	public HeaderFooterPageEvent(ReportsMappingModel reportsMappingModel){
		this.reportsMappingModel = reportsMappingModel;
	}
	
	public void onOpenDocument(PdfWriter writer, Document document) {
		t = writer.getDirectContent().createTemplate(30, 16);
		try {
			total = Image.getInstance(t);
			total.setRole(PdfName.ARTIFACT);
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		addHeader(writer, document);
	}
	
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		addFooter(writer,document);
	}

	private void addHeader(PdfWriter writer, Document document) {
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
			
			String headerContent = reportsMappingModel.getHeaderContent();
			System.out.println("------------------------------------------------------------");
			System.out.println(headerContent);
			System.out.println("------------------------------------------------------------");
			if(!ObjectUtils.isEmpty(headerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(headerContent,HeaderFooterContentDto.class);	
				System.out.println(contentDto);
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

				for(HeaderFooterContentDetailsDto dto:contentDto.getRightContent()) {					
					rightContent.addElement(new Phrase(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize())));
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

			PdfPCell titleCell = new PdfPCell(new Phrase(reportsMappingModel.getTitle(), title12)); 
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
			
			finalFeader.writeSelectedRows(0, -1, document.left(),
					document.top() + ((document.topMargin() + header.getTotalHeight()) / 2), writer.getDirectContent());
	
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}
	
	private void addFooter(PdfWriter writer, Document document) {
		PdfPTable footer = new PdfPTable(4);
		try {
			// set defaults
			footer.setWidths(new int[] { 25,30 ,5,2});
			footer.setTotalWidth(550);
			footer.setLockedWidth(true);
			
			// add text
			PdfPCell leftContent = new PdfPCell();
			leftContent.setBorder(Rectangle.NO_BORDER);
			
			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.NO_BORDER);
						
			PdfPCell rightContent = new PdfPCell();
			rightContent.setBorder(Rectangle.NO_BORDER);
			
			String footerContent = reportsMappingModel.getFooterContent();
			if(!ObjectUtils.isEmpty(footerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(footerContent,HeaderFooterContentDto.class);				
				for(HeaderFooterContentDetailsDto dto:contentDto.getLeftContent()) {					
					leftContent.addElement(new Phrase(DateUtility.getDateString(), FontFactory.getFont(dto.getFontName(), dto.getSize())));
					
				}
				
				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {					
					centerContent.addElement(new Phrase(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize())));
					
				}
				
				for(HeaderFooterContentDetailsDto dto:contentDto.getRightContent()) {					
					rightContent.addElement(new Phrase(String.format("Page %d of", writer.getPageNumber()), FontFactory.getFont(dto.getFontName(), dto.getSize())));
					
				}
				
			}
			footer.addCell(leftContent);
			footer.addCell(centerContent);
			footer.addCell(rightContent);
			
			// add placeholder for total page count
			PdfPCell totalPageCount = new PdfPCell(total);
			totalPageCount.setBorder(0);
			//totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
			footer.addCell(totalPageCount);		 	
			
			PdfPTable finalFooter = new PdfPTable(1);
			finalFooter.setTotalWidth(550);
			finalFooter.setWidthPercentage(100);
			finalFooter.getDefaultCell().setBorder(0);
			finalFooter.setLockedWidth(true);
			footer.getDefaultCell().setBorder(0);


			
			finalFooter.addCell(footer);
			PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			finalFooter.writeSelectedRows(0, -1, 25, 25,canvas);
			canvas.endMarkedContentSequence();
			
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}

	private void addFooter1(PdfWriter writer) {
		PdfPTable footer = new PdfPTable(3);
		try {
			// set defaults
			footer.setWidths(new int[] { 24, 2, 1 });
			footer.setTotalWidth(550);
			footer.setSpacingBefore(30);
			footer.setLockedWidth(true);
			footer.setPaddingTop(25);
			footer.getDefaultCell().setFixedHeight(40);
			footer.getDefaultCell().setBorder(Rectangle.TOP);
			footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
			// add current page count
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			// add copyright

			PdfPCell dateCell = new PdfPCell(new Phrase(DateUtility.getDateString(), title08)); 
			dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			dateCell.setBorder(Rectangle.TOP);
			dateCell.setBorderColor(BaseColor.LIGHT_GRAY);
			
			
			footer.addCell(dateCell);			
			footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()),title08));

			// add placeholder for total page count
			PdfPCell totalPageCount = new PdfPCell(total);
			totalPageCount.setBorder(Rectangle.TOP);
			totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
			footer.addCell(totalPageCount);

			// write page
			PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			footer.writeSelectedRows(0, -1, 25, 25, canvas);
			canvas.endMarkedContentSequence();
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		int totalLength = String.valueOf(writer.getPageNumber()).length();
		int totalWidth = totalLength * 5;
		ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,new Phrase(String.valueOf(writer.getPageNumber()), title08), totalWidth,2, 0);
	}

		
	public Paragraph addTitle(){
        Paragraph p = new Paragraph(reportsMappingModel.getTitle(), title12);
        p.setSpacingAfter(20);
        p.setAlignment(1); // Center
        return p;
   }
}
