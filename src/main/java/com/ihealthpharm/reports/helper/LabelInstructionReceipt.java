package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LabelInstructionReceipt extends ReportsPDFUtility {
	
	@Override
		public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile,String inputJson) {
	    Document document = new Document(new RectangleReadOnly(Utilities.millimetersToPoints(80),420));
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			document.setMargins(40,30,0,0);
			document.setMarginMirroringTopBottom(true);
			document.open();
			addHeader(writer, document,model);
			addLabelDetails(document, model, responseList);
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
		PdfPTable header = new PdfPTable(1);
		try {
			header.setTotalWidth(Utilities.millimetersToPoints(80));

			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.NO_BORDER);
			
			
			String headerContent = model.getHeaderContent();
			if(!ObjectUtils.isEmpty(headerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(headerContent,HeaderFooterContentDto.class);				
				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize(),Font.BOLD)); 
					phrase.setAlignment(1); 
					centerContent.addElement(phrase);					
				}
				
				
			}
			header.addCell(centerContent);
			header.setSpacingAfter(10);
			PdfPTable title = new PdfPTable(1);
			title.setTotalWidth(Utilities.millimetersToPoints(80));
			Font bold = new Font(FontFamily.HELVETICA,7, Font.BOLD);
			
		
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
	

	private void addLabelDetails(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		
		
		DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String expDate="";
		 Date date = new Date();
		
		String itemName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("ITEM_NM"));
		String quantity= (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("ITEM_QTY"));
		String instruction= (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("INSTRUCTIONS"));
		 String additionalInstruction="Space the doses evenly.Keep taking until the course is finished,unless you are told to stop";

		String customerName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("CUSTOMER_NM"));
		
		if(responseList.get(0).get("EXPIRY_DT")==null) {
			
		
	}else {
		expDate = f.format((ObjectUtils.isEmpty(responseList))?"":responseList.get(0).get("EXPIRY_DT"));
	}
		
		 
		
		PdfPTable table_itemName = new PdfPTable(1);
		table_itemName.setSpacingAfter(5); 
		table_itemName.setTotalWidth(Utilities.millimetersToPoints(80));
		table_itemName.getDefaultCell().setBorder(0);
		table_itemName.setLockedWidth(true);
		
		Font bold = new Font(FontFamily.HELVETICA,7, Font.BOLD);
		
		PdfPCell cell1 = new PdfPCell(new Phrase(itemName,bold));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBorder(Rectangle.NO_BORDER);
		table_itemName.addCell(cell1);
		
		table_itemName.completeRow();
		
		document.add(table_itemName);
		
		
		PdfPTable table_instruction = new PdfPTable(1);
		table_instruction.setSpacingAfter(5); 
		table_instruction.setTotalWidth(Utilities.millimetersToPoints(80));
		table_instruction.getDefaultCell().setBorder(0);
		table_instruction.setLockedWidth(true);
		
	
		
		PdfPCell cell2 = new PdfPCell(new Phrase(instruction,bold));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBorder(Rectangle.NO_BORDER);
		table_instruction.addCell(cell2);
		
		table_instruction.completeRow();
		
		document.add(table_instruction);
		
		

		PdfPTable table_add_instruction = new PdfPTable(1);
		table_add_instruction.setSpacingAfter(10); 
		table_add_instruction.setTotalWidth(Utilities.millimetersToPoints(80));
		table_add_instruction.getDefaultCell().setBorder(0);
		table_add_instruction.setLockedWidth(true);
		
		
		
		PdfPCell cell3 = new PdfPCell(new Phrase(additionalInstruction,bold));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setBorder(Rectangle.NO_BORDER);
		table_add_instruction.addCell(cell3);
		
		table_add_instruction.completeRow();
		
		document.add(table_add_instruction);
		
		
		
		PdfPTable table = new PdfPTable(4);
		table.setSpacingAfter(15); 
		table.setTotalWidth(Utilities.millimetersToPoints(80)); 
		table.setLockedWidth(true);
		table.getDefaultCell().setBorder(0);
		table.setWidths(new int[] {1,3,2,2});
		
		PdfPCell cell = new PdfPCell(new Phrase("Qty    : ",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(quantity ,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Exp Dt   : ",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(expDate,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("Mr/Mrs ",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase(customerName,bold));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Dt    : ",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(f.format(date),bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
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
