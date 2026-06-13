package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class SalesHourlyDetails extends ReportsPDFUtility{
	
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {
		
		 
		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		 Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();
			
			Map<String, List<Map<String, Object>>> salesRegisterMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("TIME_TO_GROUP")));
			
			List<String> datesList = new ArrayList<>();


			if(!ObjectUtils.isEmpty(salesRegisterMap)) { 

				datesList.addAll(salesRegisterMap.keySet());
				Collections.sort(datesList);

				for(int i = 0; i < datesList.size(); i++) {	
					List<Map<String, Object>> salesRegisterDetailsMap = salesRegisterMap.get(datesList.get(i));
					createTable(document,model,salesRegisterDetailsMap,datesList.get(i));

				}
				generateTotalTable(document,model,responseList);
			}

//			if(!ObjectUtils.isEmpty(salesRegisterMap)) { 
//				
//				for(String billDate :salesRegisterMap.keySet()) {	
//				List<Map<String, Object>> salesRegisterDetailsMap = salesRegisterMap.get(billDate);
//					
//					createTable(document,model,salesRegisterDetailsMap,billDate);
//				}
//				
//				generateTotalTable(document,model,responseList);
//			}

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

	private void generateTotalTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		
		
		long noOfSales  = responseList.size();
		
		PdfPTable totalQtyTable = new PdfPTable(3);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell = new PdfPCell(new Phrase("Total No. Of Sales  : "+noOfSales+"   ", title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalQtyTable.addCell(nameCell);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);  
		
		document.add(totalQtyTable);
			
		
	}

	public void createTable(Document document, ReportsMappingModel model, 
			List<Map<String, Object>> salesRegisterDetailsList,String billDate) throws DocumentException, ParseException {
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		
		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		PdfPTable supllierNameTable = new PdfPTable(3);
		PdfPCell nameCell = new PdfPCell(new Phrase("Sales From : "+df.format(salesRegisterDetailsList.get(salesRegisterDetailsList.size()-1 ).get("CREATION_TS")), title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		supllierNameTable.addCell(nameCell);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0);
		
		
		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 
		
		
		PdfPTable table = new PdfPTable(12);
		table.setTotalWidth(530);
		//table.setWidths(new int[] {30,40,55,40,50,50,45,50,40,40,30,30,30});
		table.setWidthPercentage(50);
		table.setLockedWidth(true);
		PdfPCell cell = null;

		//for (HeaderDto hearder : headerList) {

			Paragraph headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("S.No");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
	
			table.addCell(cell);	
		
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("BILL NO");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("DATE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("CUSTOMER NAME");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("BILL TYPE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("PAID AMOUNT");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("BALANCE AMOUNT");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("VAT AMT");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);

			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("PAYMENT STATUS");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("CREATED BY");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("MODIFIED BY");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("CREATION TS");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);

			
		//}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(salesRegisterDetailsList)) {
			for (Map<String, Object> rowData : salesRegisterDetailsList) {
				//for (HeaderDto hearder : headerList) {
				
				Object value = String.valueOf(salesRegisterDetailsList.indexOf(rowData) + 1);
				Font bold = new Font(FontFamily.HELVETICA,9);
				cell = new PdfPCell(new Phrase(String.valueOf(value),bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("FROM_BILL_DATE") ? rowData.get("FROM_BILL_DATE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value),bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("CUSTOMER_NM") ? rowData.get("CUSTOMER_NM") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("TYPE") ? rowData.get("TYPE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				
				value = rowData.containsKey("PAID_AMOUNT") ? rowData.get("PAID_AMOUNT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("BALANCE_AMOUNT") ? rowData.get("BALANCE_AMOUNT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("CREATED_BY") ? rowData.get("CREATED_BY") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("MODIFIED_BY") ? rowData.get("MODIFIED_BY") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("CREATION_FORMAT_TS") ? rowData.get("CREATION_FORMAT_TS") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				//}
			}
		}
		finalTable.addCell(supllierNameTable);
		finalTable.addCell(table); 
		//log.info("table width [{}]", table.getTotalWidth());
		document.add(finalTable);

	}


}
class MyObject {

    private Date date;

    public int getHour() {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).getHour();
    }
}
