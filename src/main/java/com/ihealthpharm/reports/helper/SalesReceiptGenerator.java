package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.xddf.usermodel.text.TextAlignment;
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
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SalesReceiptGenerator extends ReportsPDFUtility {
	
	@Override
		public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile) {
	    Document document = new Document(PageSize.A4, 36, 36, 50, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			//writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
			document.open();
			addHeader(writer, document,model);
			addBillDetails(document, model, responseList);
			createTable(document, model, responseList);
			addTotals(document, model, responseList);
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
					Paragraph phrase=new Paragraph(dto.getText(), FontFactory.getFont(dto.getFontName(), dto.getSize())); 
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
	

	
	public void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList)
			throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		
		PdfPTable table = new PdfPTable(headerList.size());
		table.setTotalWidth(Utilities.millimetersToPoints(80));
		table.setLockedWidth(true);
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
					if(ObjectUtils.isEmpty(value) && true) {
						
						
					}
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

	private void addTotals(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		PdfPTable table = new PdfPTable(4);
		table.setTotalWidth(Utilities.millimetersToPoints(80));
		table.setSpacingAfter(10); 
		table.getDefaultCell().setBorder(0);
		table.setLockedWidth(true);
		
		int totalItems = responseList.size();
		double totalAmount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("MRP")?String.valueOf(mapper.get("MRP")):"0.0")).sum(); 
		Double totalQty = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_QTY")?String.valueOf(mapper.get("TOTAL_QTY")):"0.0")).sum(); 
		double totalDiscount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("OVERALL_DISCOUNT")?String.valueOf(mapper.get("OVERALL_DISCOUNT")):"0.0")).sum(); 
		
		double totalVat = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("EFFECTIVE_VAT")?String.valueOf(mapper.get("VAT")):"0.0")).sum();
		double totalNetAmount = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("SALE_AMOUNT")?String.valueOf(mapper.get("SALE_AMOUNT")):"0.0")).sum();

		
		PdfPCell cell = null;
		cell = getCellWithBorder("Tot. Items  :",Element.ALIGN_LEFT,Rectangle.TOP);	
		table.addCell(cell);
		cell = getCellWithBorder(String.valueOf(totalItems) ,Element.ALIGN_LEFT,Rectangle.TOP);
		table.addCell(cell);
		cell = getCellWithBorder("Tot. Amt  : ",Element.ALIGN_LEFT,Rectangle.TOP);
		table.addCell(cell);
		cell = getCellWithBorder(ReportsPDFUtility.decilFormatter.format(totalAmount),Element.ALIGN_LEFT,Rectangle.TOP);
		table.addCell(cell);
		
		cell = getCell("Tot. Qty.    :",Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = getCell(String.valueOf(totalQty.intValue()),Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = getCell("Tot. Dis.  : ",Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = getCell(ReportsPDFUtility.decilFormatter.format(totalDiscount),Element.ALIGN_LEFT);
		table.addCell(cell);
		

		
		cell = getCellWithBorder("VAT Amt    :",Element.ALIGN_LEFT,Rectangle.BOTTOM);	
		table.addCell(cell);
		cell = getCellWithBorder(ReportsPDFUtility.decilFormatter.format(totalVat),Element.ALIGN_LEFT,Rectangle.BOTTOM);	
		table.addCell(cell);	
		cell = getCellWithBorder("Net Amt   :",Element.ALIGN_LEFT,Rectangle.BOTTOM);	
		table.addCell(cell);		
		cell = getCellWithBorder(ReportsPDFUtility.decilFormatter.format(totalNetAmount),Element.ALIGN_LEFT,Rectangle.BOTTOM);	
		table.addCell(cell);
		table.completeRow();
		
		document.add(table);
		
		
	}

	private void addBillDetails(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		
		String billCode = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("BILL_CODE"));
		String customerName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("CUSTOMER_NM"));
		
		PdfPTable table = new PdfPTable(4);
		table.setSpacingAfter(10); 
		table.setTotalWidth(Utilities.millimetersToPoints(80)); 
		table.setLockedWidth(true);
		table.getDefaultCell().setBorder(0);
		table.setWidths(new int[] {1,3,2,2});
		
		PdfPCell cell = null;		
		cell = getCellWithBorder("Bill # : ",Element.ALIGN_LEFT,Rectangle.TOP);		
		table.addCell(cell);
		cell = getCellWithBorder(billCode,Element.ALIGN_LEFT,Rectangle.TOP);
		table.addCell(cell);
		cell = getCellWithBorder("Dr.Name   :",Element.ALIGN_LEFT,Rectangle.TOP);
		table.addCell(cell);
		cell = getCellWithBorder("XXXXXX",Element.ALIGN_LEFT,Rectangle.TOP);
		table.addCell(cell);
		cell = getCell("Dt.    : ",Element.ALIGN_LEFT);
		table.addCell(cell);
		cell = getCell(DateUtility.getDateStringHH(),Element.ALIGN_LEFT);
		//cell.setNoWrap(true);
		table.addCell(cell);
		cell = getCell("Cust.Name:",Element.ALIGN_LEFT);
		cell.setNoWrap(true);
		table.addCell(cell);
		cell = getCell(customerName,Element.ALIGN_LEFT);
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
