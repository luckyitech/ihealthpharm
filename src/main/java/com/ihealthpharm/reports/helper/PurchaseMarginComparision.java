package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
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

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PurchaseMarginComparision extends ReportsPDFUtility{
	
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile) {
		
		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		 Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();
			
			Map<String, List<Map<String, Object>>> purchaseMarginComparision = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("ITEM_NM")));			
			
			
			if(!ObjectUtils.isEmpty(purchaseMarginComparision)) { 
				
				for(String itemName :purchaseMarginComparision.keySet()) {					
					List<Map<String, Object>> purchaseMarginCompDetails = purchaseMarginComparision.get(itemName);
					createTable(document,model,purchaseMarginCompDetails,itemName);
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


	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> purchaseMarginCompList,
			String itemName) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		
		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 
		
		PdfPTable supllierNameTable = new PdfPTable(2);
		PdfPCell nameCell = new PdfPCell(new Phrase("Product Name : "+itemName, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		supllierNameTable.addCell(nameCell);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 
		
		
		PdfPTable table = new PdfPTable(11);
		table.setTotalWidth(500);
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
			headerCell.add("DISTRIBUTOR NAME");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("BATCH");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("QTY");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("FREE QTY.");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("PURC.PRICE");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("MRP");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("DISC.AMT.");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("NET AMT.");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("MARGIN AMT.");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
			headerCell = new Paragraph();
			headerCell.setFont(headerFont);
			headerCell.add("MARGIN AMT/ITEM");
			cell = new PdfPCell(headerCell);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);
			
			table.addCell(cell);
			
		//}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(purchaseMarginCompList)) {
			for (Map<String, Object> rowData : purchaseMarginCompList) {
				//for (HeaderDto hearder : headerList) {

				Object value = String.valueOf(purchaseMarginCompList.indexOf(rowData) + 1);
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("SP_NAME") ? rowData.get("SP_NAME") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("BATCH_NO") ? rowData.get("BATCH_NO") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("ORDER_QUANTITY") ? rowData.get("ORDER_QUANTITY") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("BONUS") ? rowData.get("BONUS") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("UNIT_PURCHASE_RATE") ? rowData.get("UNIT_PURCHASE_RATE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("MRP") ? rowData.get("MRP") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("DISCOUNT") ? rowData.get("DISCOUNT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("NET_AMOUNT") ? rowData.get("NET_AMOUNT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("MARGIN_AMT") ? rowData.get("MARGIN_AMT") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("MARGIN_AMT_ITEM") ? rowData.get("MARGIN_AMT_ITEM") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
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
