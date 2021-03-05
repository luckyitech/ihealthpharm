package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class AccountReceivablesReceiptPdf extends ReportsPDFUtility{


	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile,String inputJson) {
		Document document = new Document(new RectangleReadOnly(Utilities.millimetersToPoints(80),420));

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			//writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
			document.setMargins(40,30,0,0);
			document.setMarginMirroringTopBottom(true);
			document.open();
			addHeader(writer, document,model);
			addBillDetails(document, model, responseList);
			createTable(document, model, responseList);
			addTotals(document, model, responseList);
			addFooter(writer, model,document); 

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



	public void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList)
			throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);


		PdfPTable table = new PdfPTable(headerList.size());
		table.setWidths(new int[] {1,2,2,1,1,1});
		table.setTotalWidth(Utilities.millimetersToPoints(80));
		table.setLockedWidth(true);
		table.setSpacingAfter(10);
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
		if (!ObjectUtils.isEmpty(responseList)) {
			for (Map<String, Object> rowData : responseList) {
				for (HeaderDto hearder : headerList) {

					//System.out.println(rowData);


					Object value = rowData.containsKey(hearder.getColumnName()) ? rowData.get(hearder.getColumnName()): "";		

					if(ObjectUtils.isEmpty(value) && true) {


					}

					Font bold = new Font(FontFamily.HELVETICA,7,Font.BOLD);
					if(rowData.get("PAYMENT_STATUS").equals("Partially Paid") && hearder.getColumnName().equals("SOURCE_REF")) {

						cell = new PdfPCell(new Phrase(String.valueOf(value)+" *",bold));
					}else {
						cell = new PdfPCell(new Phrase(String.valueOf(value),bold));
					}
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if(!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);


				}
			}
		}
		//log.info("table width [{}]",table.getTotalWidth());
		document.add(table);

	}

	private void addTotals(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException, ParseException {
		DecimalFormat df=new DecimalFormat("0.00");

		PdfPTable table = new PdfPTable(4);
		table.setTotalWidth(Utilities.millimetersToPoints(80));
		table.setSpacingAfter(0); 
		table.getDefaultCell().setBorder(0);
		table.setLockedWidth(true);

		double totalAmountRec =responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("AMOUNT_RECEIVED") && !ObjectUtils.isEmpty(mapper.get("AMOUNT_RECEIVED"))) ?String.valueOf(mapper.get("AMOUNT_RECEIVED")):"0")).sum();  
		double totalAmountToBeRec =responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("AMOUNT_TO_BE_RECEIVED") && !ObjectUtils.isEmpty(mapper.get("AMOUNT_TO_BE_RECEIVED"))) ?String.valueOf(mapper.get("AMOUNT_TO_BE_RECEIVED")):"0")).sum();  


		String totRecAmt=df.format(totalAmountRec);
		Double Recamount=Double.parseDouble(totRecAmt);

		String totToBeRecAmt=df.format(totalAmountToBeRec);
		Double toBeRecamount=Double.parseDouble(totToBeRecAmt);


		Font bold = new Font(FontFamily.HELVETICA,7, Font.BOLD);
		PdfPCell cell = new PdfPCell(new Phrase("		",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(String.valueOf("	"),bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Tot. Amt Rec  : ",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(String.valueOf(Recamount),bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		table.completeRow();

		document.add(table);

	}

	private void addBillDetails(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {


		String apprDate = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("APPROVED_DATE"));
		//String billCode = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("BILL_CODE"));
		String customerName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("CUSTOMER_NAME"));
		//String doctorName = (ObjectUtils.isEmpty(responseList))?"":String.valueOf(responseList.get(0).get("DOCTOR_NM"));

		PdfPTable table = new PdfPTable(2);
		table.setSpacingAfter(10); 
		table.setTotalWidth(Utilities.millimetersToPoints(80)); 
		table.setLockedWidth(true);
		table.getDefaultCell().setBorder(0);
		table.setWidths(new int[] {1,3});

		Font bold = new Font(FontFamily.HELVETICA,7, Font.BOLD);

		PdfPCell cell = new PdfPCell(new Phrase("App Dt.    :",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.TOP);
		table.addCell(cell);

		//cell = new PdfPCell(new Phrase(DateUtility.getDateStringHH(),bold));
		cell = new PdfPCell(new Phrase(apprDate,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.TOP);
		table.addCell(cell);

		//		cell = new PdfPCell(new Phrase());
		//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		//		cell.setBorder(Rectangle.TOP);
		//		table.addCell(cell);
		//
		//		cell = new PdfPCell(new Phrase());
		//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		//		cell.setBorder(Rectangle.TOP);
		//		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Customer	:",bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(customerName,bold));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase());
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell); 

		cell = new PdfPCell(new Phrase());
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);
		table.setSpacingAfter(10);
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
