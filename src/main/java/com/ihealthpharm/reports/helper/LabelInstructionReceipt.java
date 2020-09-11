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
		Document document = new Document(new RectangleReadOnly(Utilities.millimetersToPoints(40),68));
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			document.setMargins(0,0,0,0);
			//document.setMarginMirroringTopBottom(true);
			//document.setMarginLeft(-1);
			document.open();
			addHeader(writer, document,model,responseList);
			addLabelDetails(document, model, responseList);
			addFooter(writer, model,document,responseList); 

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

	private void addHeader(PdfWriter writer, Document document, ReportsMappingModel model,List<Map<String, Object>> responseList) { 

		System.out.println(responseList.get(0));
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		String expDate=null;

		PdfPTable header = new PdfPTable(2);
		try {
			header.setTotalWidth(Utilities.millimetersToPoints(50));
			header.setWidths(new int[] { 35 ,15});
			
			PdfPCell leftContent = new PdfPCell();
			leftContent.setBorder(Rectangle.BOTTOM);
			leftContent.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.BOTTOM);
			
			PdfPCell rightContent = new PdfPCell();
			rightContent.setBorder(Rectangle.BOTTOM);
			rightContent.setHorizontalAlignment(Element.ALIGN_LEFT);
			


			String headerContent = model.getHeaderContent();
			if(!ObjectUtils.isEmpty(headerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(headerContent,HeaderFooterContentDto.class);				
				for(HeaderFooterContentDetailsDto dto:contentDto.getLeftContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize(),Font.BOLD)); 
					//phrase.setAlignment(0);

					leftContent.addElement(phrase);					
				}

				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize(),Font.BOLD)); 
					//phrase.setAlignment(0);

					centerContent.addElement(phrase);					
				}

				
				
					Date date = new Date();  
					
					HeaderFooterContentDetailsDto expDt = new HeaderFooterContentDetailsDto();
					expDt.setFontName("Helvetica");
					expDt.setSize(4);
					expDt.setText(f.format(date));
					contentDto.getRightContent().set(0, expDt);
				


				for(HeaderFooterContentDetailsDto dto:contentDto.getRightContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize(),Font.BOLD)); 
					//phrase.setAlignment(0);

					rightContent.addElement(phrase);					
				}


			}

			leftContent.setPaddingLeft(0);
			rightContent.setPaddingRight(0);
			header.addCell(leftContent);
			header.addCell(rightContent);

			//header.setSpacingAfter(10);

			PdfPTable finalFeader = new PdfPTable(1);
			finalFeader.getDefaultCell().setBorder(0);
			header.getDefaultCell().setBorder(0);

			//header.setHorizontalAlignment(Element.ALIGN_LEFT);

			finalFeader.setTotalWidth(Utilities.millimetersToPoints(40));
			finalFeader.addCell(header);

			document.add(finalFeader);

		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}


	private void addLabelDetails(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {



		String expDate="";
		Date date = new Date();

		String itemName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("ITEM_NM"));
		//String quantity= (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("ITEM_QTY"));
		String instruction= (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("INSTRUCTIONS"));
		//String additionalInstruction="Space the doses evenly.Keep taking until the course is finished,unless you are told to stop";

		//String customerName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("CUSTOMER_NM"));

		//		if(responseList.get(0).get("EXPIRY_DT")==null) {
		//
		//
		//		}else {
		//			expDate = f.format((ObjectUtils.isEmpty(responseList))?"":responseList.get(0).get("EXPIRY_DT"));
		//		}



		PdfPTable table_itemName = new PdfPTable(1);
		//table_itemName.setSpacingAfter(5); 
		table_itemName.setTotalWidth(Utilities.millimetersToPoints(40));
		table_itemName.getDefaultCell().setBorder(0);
		table_itemName.setLockedWidth(true);

		Font bold = new Font(FontFamily.HELVETICA,4, Font.BOLD);

		PdfPCell cell1 = new PdfPCell(new Phrase(itemName,bold));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBorder(Rectangle.NO_BORDER);
		table_itemName.addCell(cell1);

		table_itemName.completeRow();

		document.add(table_itemName);

		Font bold1 = new Font(FontFamily.HELVETICA,4, Font.BOLD);
		PdfPTable table_instruction = new PdfPTable(1);
		//table_instruction.setSpacingAfter(5); 
		table_instruction.setTotalWidth(Utilities.millimetersToPoints(40));
		table_instruction.getDefaultCell().setBorder(0);
		table_instruction.setLockedWidth(true);


		//PdfPCell cell2 = new PdfPCell(new Phrase(instruction,bold1));
		PdfPCell cell2 = new PdfPCell(new Phrase("Take two daily Swallow whole, do not chew.Take with or after food.",bold1));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBorder(Rectangle.NO_BORDER);
		table_instruction.addCell(cell2);

		table_instruction.completeRow();
		table_instruction.setSpacingAfter(10);

		document.add(table_instruction);

	}


	private void addFooter(PdfWriter writer, ReportsMappingModel model,Document document,List<Map<String, Object>> responseList) {
		PdfPTable footer = new PdfPTable(2);
		
		try {
			footer.setTotalWidth(Utilities.millimetersToPoints(40));
			footer.setWidths(new int[] { 20 ,20});
			footer.setSpacingBefore(0);
			
			PdfPCell centerContent = new PdfPCell();
			centerContent.setBorder(Rectangle.TOP);

			PdfPCell leftContent = new PdfPCell();
			leftContent.setBorder(Rectangle.TOP);


			PdfPCell rightContent = new PdfPCell();
			rightContent.setBorder(Rectangle.TOP);



			String footerContent = model.getFooterContent();
			if(!ObjectUtils.isEmpty(footerContent)) {
				HeaderFooterContentDto contentDto = (HeaderFooterContentDto) JsonUtility.jsonToObject(footerContent,HeaderFooterContentDto.class);				
				for(HeaderFooterContentDetailsDto dto:contentDto.getCenterContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize(),Font.BOLD));
					//phrase.setAlignment(1); 
					centerContent.addElement(phrase);

				}



				if(responseList.get(0).get("CUSTOMER_NM")==null) {

					System.out.println("Entered");
				}else {
					System.out.println("Entered else");
					String customerName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("CUSTOMER_NM"));

					HeaderFooterContentDetailsDto customer = new HeaderFooterContentDetailsDto();
					customer.setFontName("Helvetica");
					customer.setSize(4);
					customer.setText(customerName);
					contentDto.getLeftContent().set(0, customer);
				}

				for(HeaderFooterContentDetailsDto dto:contentDto.getLeftContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize(),Font.BOLD));
					//phrase1.setAlignment(0); 
					leftContent.addElement(phrase);

				}


				for(HeaderFooterContentDetailsDto dto:contentDto.getRightContent()) {		
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize(),Font.BOLD));
					//phrase2.setAlignment(1); 
					rightContent.addElement(phrase);

				}
			}

			footer.addCell(leftContent);

			footer.addCell(rightContent);

			leftContent.setHorizontalAlignment(Element.ALIGN_LEFT);
			leftContent.setPaddingLeft(0);

			rightContent.setHorizontalAlignment(Element.ALIGN_CENTER);
			rightContent.setPaddingLeft(0);
			
								
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
