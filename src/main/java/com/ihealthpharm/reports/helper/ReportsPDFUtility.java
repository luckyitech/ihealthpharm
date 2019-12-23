package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
/*import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;*/
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.model.ReportsMappingModel;
import com.ihealthpharm.reports.service.ReportGenerator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportsPDFUtility implements ReportGenerator{

	protected static  Font title06 = FontFactory.getFont(Font.FontFamily.HELVETICA.toString(), 6);
	protected static  Font title08 = FontFactory.getFont(Font.FontFamily.HELVETICA.toString(), 8);
	protected static  Font headerFont = FontFactory.getFont(Font.FontFamily.HELVETICA.toString(),7,Font.BOLD);
	protected static  DecimalFormat decilFormatter = new DecimalFormat("0.00");



	
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,File responseFile,String inputJson) {

		//Document document = new Document();
        HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
	    Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();
			if (ObjectUtils.isEmpty(responseList))
				addMessage(document, "No data Found");
			else {
				createTable(document, model, responseList);
				if(model.isShowBarCharts())
				  createBarChart(writer,document);
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

	//TODO Bar Chart Logic goes here
	private void createBarChart(PdfWriter writer, Document document) {
		/*
		 * document.newPage(); DefaultCategoryDataset dataSet = new
		 * DefaultCategoryDataset(); dataSet.setValue(791, "Populastion", "1750 AD");
		 * dataSet.setValue(978, "Populastion", "1800 AD"); dataSet.setValue(1262,
		 * "Populastion", "1850 AD"); dataSet.setValue(1650, "Populastion", "1900 AD");
		 * dataSet.setValue(2519, "Populastion", "1950 AD"); dataSet.setValue(6070,
		 * "Populastion", "2000 AD");
		 * 
		 * JFreeChart chart = ChartFactory.createBarChart("World Population growth",
		 * "Year", "Population in millions", dataSet, PlotOrientation.VERTICAL, false,
		 * true, false);
		 * 
		 * PdfContentByte contentByte = writer.getDirectContent(); PdfTemplate template
		 * = contentByte.createTemplate(500, 500); Graphics2D graphics2d =
		 * template.createGraphics(500, 500, new DefaultFontMapper()); Rectangle2D
		 * rectangle2d = new Rectangle2D.Double(0, 0, 500, 500);
		 * 
		 * chart.draw(graphics2d, rectangle2d);
		 * 
		 * graphics2d.dispose(); contentByte.addTemplate(template, 0, 0);
		 */

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

	public void addMessage(Document document, String message) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		paragraph.add(new Paragraph(message));
		document.add(paragraph);

	}

	
	
	public void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList)
			throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		PdfPTable table = new PdfPTable(headerList.size());
		table.setWidthPercentage(100);
		table.setSpacingBefore(30);
		table.setSpacingAfter(50); 
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
					Object value = rowData.containsKey(hearder.getColumnName()) ? rowData.get(hearder.getColumnName())
							: "";
					
					value = formatData(hearder,value); 
					
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

	private Object formatData(HeaderDto hearder, Object value) {
		
		if(ObjectUtils.isEmpty(hearder.getFormat()))
		 return value;
		
		//getDateStringwithouKnowingInputFormat
		
		return value;
		
		
	}
}
