package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.model.ReportsMappingModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportsPDFUtility {

	public void generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model, File responseFile) {

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(responseFile));
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
		PdfPCell cell = null;
		for (HeaderDto hearder : headerList) {
			cell = new PdfPCell(new Phrase(hearder.getDisplayName()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
		}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(responseList)) {
			for (Map<String, Object> rowData : responseList) {
				for (HeaderDto hearder : headerList) {
					Object value = rowData.containsKey(hearder.getColumnName()) ? rowData.get(hearder.getColumnName())
							: "";
					table.addCell(String.valueOf(value));

				}
			}
		}

		document.add(table);

	}
}
