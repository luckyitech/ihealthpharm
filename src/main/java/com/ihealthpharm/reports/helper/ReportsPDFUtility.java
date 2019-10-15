package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.swing.border.Border;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.model.ReportsMappingModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportsPDFUtility {

	private Font title06 = FontFactory.getFont(Font.FontFamily.HELVETICA.toString(), 6);
	private Font title08 = FontFactory.getFont(Font.FontFamily.HELVETICA.toString(), 8);


	
	public void generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile) {

		//Document document = new Document();
        HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
	    Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();
			if (ObjectUtils.isEmpty(responseList))
				addMessage(document, "No data Found");
			else
				createTable(document, model, responseList);
			

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
	}

	public void generateErrorReport(File responseFile, String errorMessage) {

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			document.open();
			addMessage(document, errorMessage);
		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
		} finally {
			document.close();
		}
	}

	private void addMessage(Document document, String message) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		paragraph.add(new Paragraph(message));
		document.add(paragraph);

	}

	
	
	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList)
			throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		
		PdfPTable table = new PdfPTable(headerList.size());
		table.setWidthPercentage(100);
		table.setSpacingBefore(30);
		table.setSpacingAfter(50); 
		PdfPCell cell = null;
		for (HeaderDto hearder : headerList) {
			cell = new PdfPCell(new Phrase(hearder.getDisplayName(),title08));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
		}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(responseList)) {
			for (Map<String, Object> rowData : responseList) {
				for (HeaderDto hearder : headerList) {
					Object value = rowData.containsKey(hearder.getColumnName()) ? rowData.get(hearder.getColumnName())
							: "";
					cell = new PdfPCell(new Phrase(String.valueOf(value),title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);					
					table.addCell(cell);

				}
			}
		}
		log.info("table width [{}]",table.getTotalWidth());
		document.add(table);

	}
}
