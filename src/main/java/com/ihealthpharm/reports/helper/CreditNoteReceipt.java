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
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CreditNoteReceipt extends ReportsPDFUtility {
	
	@Override
		public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile,String inputJson) {
	    Document document = new Document(new RectangleReadOnly(Utilities.millimetersToPoints(80),420));

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			//writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
			document.setMargins(40,30,0,0);
			document.setMarginMirroringTopBottom(true);
			document.open();
			for(int i=0;i<responseList.size();i++) {
				document.newPage();
				addHeader(writer, document,model);
				addCreditNoteDetails(document, model, responseList.get(i));
				//createTable(document, model, responseList);
				//addTotals(document, model, responseList);
				addFooter(writer, model,document); 
			}

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
		PdfPTable header = new PdfPTable(1);
		try {
			header.setTotalWidth(Utilities.millimetersToPoints(80));

			/*
			 * // add text PdfPCell leftContent = new PdfPCell();
			 * leftContent.setBorder(Rectangle.NO_BORDER);
			 */

			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.NO_BORDER);
			
			/*
			 * PdfPCell rightContent = new PdfPCell();
			 * rightContent.setBorder(Rectangle.NO_BORDER);
			 */
			
			String headerContent = model.getHeaderContent();
			if(!ObjectUtils.isEmpty(headerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(headerContent,HeaderFooterContentDto.class);				
				/*
				 * for(HeaderFooterContentDetailsDto dto:contentDto.getLeftContent()) {
				 * leftContent.addElement(new Phrase(dto.getText(),
				 * FontFactory.getFont(dto.getFontName(), dto.getSize())));
				 * 
				 * }
				 */
				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize(),Font.BOLD)); 
					phrase.setAlignment(1); 
					centerContent.addElement(phrase);					
				}
				
				/*
				 * for(HeaderFooterContentDetailsDto dto:contentDto.getRightContent()) {
				 * rightContent.addElement(new Phrase(dto.getText(),
				 * FontFactory.getFont(dto.getFontName(), dto.getSize())));
				 * 
				 * }
				 */
				
			}
			///header.addCell(leftContent);
			header.addCell(centerContent);
			//header.addCell(rightContent);
			header.setSpacingAfter(10);
			
			PdfPTable title = new PdfPTable(1);
			title.setTotalWidth(Utilities.millimetersToPoints(80));
			//title.setWidthPercentage(50);
			
			Font bold = new Font(FontFamily.HELVETICA,7, Font.BOLD);
			PdfPCell titleCell = new PdfPCell(new Phrase(model.getTitle(), bold)); 
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

			finalFeader.setTotalWidth(Utilities.millimetersToPoints(80));
			finalFeader.addCell(header);
			finalFeader.addCell(title);
			
			document.add(finalFeader);
			
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}
	
	private void addCreditNoteDetails(Document document, ReportsMappingModel model, Map<String, Object> responseList) throws DocumentException {
		
		String CreditNoteNo = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get("credit_note_no"));
		String customerName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get("FIRST_NM"));
		String creditNoteDate = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get("credit_date"));
		String salesBillNo = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get("bill_id"));
		String amount = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get("amount"));
		String remarks = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get("remarks"));
		String approvedBy = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get("APPROVED_BY"));
		
		PdfPTable table = new PdfPTable(4);
		table.setSpacingAfter(10); 
		table.setTotalWidth(Utilities.millimetersToPoints(80)); 
		table.setLockedWidth(true);
		table.getDefaultCell().setBorder(0);
		table.setWidths(new int[] {1,3,2,2});
		
		Font bold = new Font(FontFamily.HELVETICA,7, Font.BOLD);
		PdfPCell cell = new PdfPCell(new Phrase("Credit Note No		:",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.TOP);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(CreditNoteNo,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.TOP);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Customer Name		:",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(customerName,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Credit Date		:",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(creditNoteDate,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Bill No		:",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(salesBillNo,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Amount		:",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(amount,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("Remarks		:",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(remarks,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		table.completeRow();
		
		cell = new PdfPCell(new Phrase("Approved By		:",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(approvedBy,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);
		table.completeRow();
		
		document.add(table);
		
	}

	private void addFooter(PdfWriter writer, ReportsMappingModel model,Document document) {
		PdfPTable footer = new PdfPTable(1);
		try {
			footer.setTotalWidth(Utilities.millimetersToPoints(80));
			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.NO_BORDER);
			
			String footerContent = model.getFooterContent();
			if(!ObjectUtils.isEmpty(footerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(footerContent,HeaderFooterContentDto.class);				
				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize(),Font.BOLD));
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
	
	private PdfPCell getCell(String text,int align) {
	    PdfPCell cell = new PdfPCell();
	    text=text.replaceAll(" ","\u00a0");
	    //cell.setColspan(cm);
	    cell.setBorder(0);
	    cell.setUseAscender(true);
	    cell.setUseDescender(true);
	    Paragraph p = new Paragraph(text,new Font(Font.FontFamily.HELVETICA, 8));
	    p.setAlignment(align);
	    p.setAlignment(Element.ALIGN_JUSTIFIED);
	    //p.setFont(font1);
	    p.setAlignment(align);
	    cell.addElement(p);
	    return cell;
	}
	
	private PdfPCell getCellWithBorder(String text,int align,int bottom) {
	    PdfPCell cell = new PdfPCell();
	    text=text.replaceAll(" ","\u00a0");
	   // cell.setColspan(cm);
	    cell.setUseAscender(true);
	    cell.setUseDescender(true);
	    cell.setBorder(0);
		cell.setBorder(bottom);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
	    Paragraph p = new Paragraph(text,new Font(Font.FontFamily.HELVETICA, 8));
	    p.setAlignment(Element.ALIGN_JUSTIFIED);
	    //p.setFont(font1);
	    p.setAlignment(align);
	    cell.addElement(p);
	    return cell;
	}
	
}
