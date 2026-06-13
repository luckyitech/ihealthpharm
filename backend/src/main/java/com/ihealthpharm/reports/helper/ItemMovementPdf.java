package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.HeaderDto;
import com.ihealthpharm.reports.model.ReportsMappingModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@Component
public class ItemMovementPdf extends ReportsPDFUtility{
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();

			Map<Date, List<Map<String, Object>>> salesByPersonMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (Date) map.get("CURRENT_STOCK_DATE")));			


			if(!ObjectUtils.isEmpty(salesByPersonMap)) { 

				for(Date openingClosingDate :salesByPersonMap.keySet()) {					
					List<Map<String, Object>> salesByPersonList = salesByPersonMap.get(openingClosingDate);
					createTable(document,model,salesByPersonList,openingClosingDate);
				}

			
			}

			
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

	
	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> salesProfitList,Date openingClosingDate) throws DocumentException {


		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);


		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 

		PdfPTable salePersonNameTable = new PdfPTable(3);
		PdfPCell nameCell = new PdfPCell(new Phrase("Date : "+df.format(openingClosingDate), title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		salePersonNameTable.addCell(nameCell);
		salePersonNameTable.setLockedWidth(true);
		salePersonNameTable.setTotalWidth(500);
		salePersonNameTable.getDefaultCell().setBorder(0); 

		String reportHeader1 = model.getReportHeader();
		List<HeaderDto> headerList1 = JsonUtility.jsonToList(reportHeader1, HeaderDto.class);

		PdfPTable table = new PdfPTable(headerList1.size());
		table.setTotalWidth(530);
		//table.setWidths(new int[] {35,40,55,33,25,20,35,35,35,35,20,30,30,30,40,33});
		table.setWidthPercentage(50);
		table.setLockedWidth(true);
		PdfPCell cell = null;


		for (HeaderDto hearder : headerList1) {
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

		if (!ObjectUtils.isEmpty(salesProfitList)) {

			for (Map<String, Object> rowData : salesProfitList) {

				for (HeaderDto hearder : headerList1) {
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

			finalTable.addCell(salePersonNameTable);
			finalTable.addCell(table); 
			document.add(finalTable);
			
		}
	}



	private Object formatData(HeaderDto hearder, Object value) {

		if(ObjectUtils.isEmpty(hearder.getFormat()))
			return value;

		//getDateStringwithouKnowingInputFormat

		return value;


	}

}
