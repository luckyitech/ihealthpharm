package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
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
public class AvailableStockItemsPdf extends ReportsPDFUtility{

	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();


			createTable(document,model,responseList);

			generateTotalTable(document,model,responseList);


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
	DecimalFormat df = new DecimalFormat("####0.00");
	double totalPurValue=0.00;
	
	totalPurValue  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("PURCHASE_VALUE") && !ObjectUtils.isEmpty(mapper.get("PURCHASE_VALUE"))) ?String.valueOf(mapper.get("PURCHASE_VALUE")):"0")).sum();  

	PdfPTable totalProfitTable = new PdfPTable(2);
	totalProfitTable.setTotalWidth(500);
	//totalProfitTable.setSpacingBefore(30); 
	totalProfitTable.setWidthPercentage(50);
	totalProfitTable.setLockedWidth(true);
	totalProfitTable.getDefaultCell().setBorder(0); 

	
	String purchaseValueTot = String.format("%.2f", totalPurValue);

	

	PdfPCell nameCell = new PdfPCell(new Phrase("Total Purchase Amount"+" "+" : "+"	"+purchaseValueTot, title08)); 
	nameCell.setColspan(3);
	nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	nameCell.setVerticalAlignment(Element.ALIGN_TOP);
	nameCell.setBorder(0);
	totalProfitTable.addCell(nameCell);
	totalProfitTable.setLockedWidth(true);
	totalProfitTable.setTotalWidth(500);
	totalProfitTable.getDefaultCell().setBorder(0); 

	
	document.add(totalProfitTable);

}
public void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> salesProfitList) throws DocumentException {


	String reportHeader = model.getReportHeader();
	List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);


	PdfPTable finalTable = new PdfPTable(1);
	finalTable.setTotalWidth(500);
	finalTable.setWidthPercentage(50);
	finalTable.setLockedWidth(true);
	finalTable.getDefaultCell().setBorder(0); 

	

	PdfPTable table = new PdfPTable(headerList.size());
	table.setTotalWidth(530);
	//table.setWidths(new int[] {35,40,55,33,25,20,35,35,35,35,20,30,30,30,40,33});
	table.setWidthPercentage(50);
	table.setLockedWidth(true);
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

	if (!ObjectUtils.isEmpty(salesProfitList)) {
		for (Map<String, Object> rowData : salesProfitList) {
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

	
	finalTable.addCell(table); 
	document.add(finalTable);

}
private Object formatData(HeaderDto hearder, Object value) {

	if(ObjectUtils.isEmpty(hearder.getFormat()))
		return value;

	//getDateStringwithouKnowingInputFormat

	return value;


}

}
