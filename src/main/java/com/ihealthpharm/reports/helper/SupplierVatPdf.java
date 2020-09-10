package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
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
public class SupplierVatPdf extends ReportsPDFUtility{


	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();

			//addHeader(writer, document,model,responseList);

			Map<String, List<Map<String, Object>>> purchaseOrderDetailsPONO = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("PURCHASE_ORDER_NO")));

			if(!ObjectUtils.isEmpty(purchaseOrderDetailsPONO)) { 

				for(String purchaseOrderNo :purchaseOrderDetailsPONO.keySet()) {	

					List<Map<String, Object>> purchaseOrderDetailsMap = purchaseOrderDetailsPONO.get(purchaseOrderNo);

					createTable(document,model,purchaseOrderDetailsMap,purchaseOrderNo);
				}


				generateTotalTable(document,model,responseList);


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

private void generateTotalTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
	double subTotal = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("TOTAL_VALUE") && !ObjectUtils.isEmpty(mapper.get("TOTAL_VALUE"))) ?String.valueOf(mapper.get("TOTAL_VALUE")):"0")).sum(); 
	double totalVat = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("VAT_AMT")&& !ObjectUtils.isEmpty(mapper.get("VAT_AMT")))?String.valueOf(mapper.get("VAT_AMT")):"0")).sum(); 

	DecimalFormat df=new DecimalFormat("0.00");

	String doubleSubTotal=df.format(subTotal);
	double subTotals=Double.parseDouble(doubleSubTotal); 
	
	String vatTotal=df.format(totalVat);
	double finalVatTot=Double.parseDouble(vatTotal); 

	PdfPTable totalQtyTable = new PdfPTable(3);
	totalQtyTable.setTotalWidth(500);
	totalQtyTable.setSpacingBefore(30); 
	totalQtyTable.setWidthPercentage(50);
	totalQtyTable.setLockedWidth(true);
	totalQtyTable.getDefaultCell().setBorder(0); 

	PdfPCell nameCell = new PdfPCell(new Phrase("SUB TOTAL : "+subTotals, title08)); 
	nameCell.setColspan(3);
	nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	nameCell.setVerticalAlignment(Element.ALIGN_TOP);
	nameCell.setBorder(0);
	totalQtyTable.addCell(nameCell);
	totalQtyTable.setLockedWidth(true);
	totalQtyTable.setTotalWidth(500);
	totalQtyTable.getDefaultCell().setBorder(0); 


	PdfPCell nameCell2 = new PdfPCell(new Phrase("VAT TOTAL: "+finalVatTot, title08)); 
	nameCell2.setColspan(3);
	nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
	nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
	nameCell2.setBorder(0);
	totalQtyTable.addCell(nameCell2);
	totalQtyTable.setLockedWidth(true);
	totalQtyTable.setTotalWidth(500);
	totalQtyTable.getDefaultCell().setBorder(0);


	document.add(totalQtyTable);

}

private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> purchaseOrderDetailsList,
		String poNo) throws DocumentException {

	String reportHeader = model.getReportHeader();
	List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);


	PdfPTable finalTable = new PdfPTable(1);
	finalTable.setTotalWidth(500);
	finalTable.setWidthPercentage(50);
	finalTable.setLockedWidth(true);
	finalTable.getDefaultCell().setBorder(0); 

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	String supplierName=String.valueOf(purchaseOrderDetailsList.get(0).get("SP_NAME"));
	String address=String.valueOf(purchaseOrderDetailsList.get(0).get("CITY_NM"));
	String poDate=formatter.format(purchaseOrderDetailsList.get(0).get("PURCHASE_ORDER_DT"));

	PdfPTable supllierNameTable = new PdfPTable(2);
	//		PdfPCell nameCell = new PdfPCell(new Phrase("Supplier Name  : "+supplierName+  "      "
	//				+ "GRN NO   :  "+grnNo+"          "+"Invoice Date   : "+invoiceDates, title08)); 

	PdfPCell nameCell = new PdfPCell(new Phrase("Supplier Name  : "+supplierName+  "       "
			+ "PO No  :  "+poNo+"         "+"PO Date   : "+poDate, title08)); 

	nameCell.setColspan(3);
	nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	nameCell.setVerticalAlignment(Element.ALIGN_TOP);
	nameCell.setBorder(0);
	supllierNameTable.addCell(nameCell);
	supllierNameTable.setLockedWidth(true);
	supllierNameTable.setTotalWidth(500);
	supllierNameTable.getDefaultCell().setBorder(0); 

	PdfPCell nameCell1 = new PdfPCell(new Phrase("Address  : "+address, title08)); 
	nameCell1.setColspan(3);
	nameCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
	nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
	nameCell1.setBorder(0);
	supllierNameTable.addCell(nameCell1);
	supllierNameTable.setLockedWidth(true);
	supllierNameTable.setTotalWidth(500);
	supllierNameTable.getDefaultCell().setBorder(0); 



	PdfPTable table = new PdfPTable(14);
	table.setTotalWidth(500);
	table.setWidthPercentage(50);
	table.setLockedWidth(true);
	PdfPCell cell = null;

	//for (HeaderDto hearder : headerList) {

	Paragraph headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("S.NO");
	cell = new PdfPCell(headerCell);
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	if (!model.isShowVerticalLines())
		cell.setBorder(Rectangle.BOTTOM);

	table.addCell(cell);	

	headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("QTN NO");
	cell = new PdfPCell(headerCell);
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	if (!model.isShowVerticalLines())
		cell.setBorder(Rectangle.BOTTOM);

	table.addCell(cell);
	
	headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("CST NO");
	cell = new PdfPCell(headerCell);
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	if (!model.isShowVerticalLines())
		cell.setBorder(Rectangle.BOTTOM);

	table.addCell(cell);

	headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("ITEM NAME");
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
	headerCell.add("BONUS");
	cell = new PdfPCell(headerCell);
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	if (!model.isShowVerticalLines())
		cell.setBorder(Rectangle.BOTTOM);

	table.addCell(cell);

	headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("UNIT PRICE");
	cell = new PdfPCell(headerCell);
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	if (!model.isShowVerticalLines())
		cell.setBorder(Rectangle.BOTTOM);

	table.addCell(cell);

	headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("DISC%");
	cell = new PdfPCell(headerCell);
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	if (!model.isShowVerticalLines())
		cell.setBorder(Rectangle.BOTTOM);

	table.addCell(cell);

	headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("DISC AMT");
	cell = new PdfPCell(headerCell);
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	if (!model.isShowVerticalLines())
		cell.setBorder(Rectangle.BOTTOM);

	table.addCell(cell);

	headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("VAT");
	cell = new PdfPCell(headerCell);
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	if (!model.isShowVerticalLines())
		cell.setBorder(Rectangle.BOTTOM);

	table.addCell(cell);

	headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("TOTAL AMT");
	cell = new PdfPCell(headerCell);
	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	if (!model.isShowVerticalLines())
		cell.setBorder(Rectangle.BOTTOM);

	table.addCell(cell);

	headerCell = new Paragraph();
	headerCell.setFont(headerFont);
	headerCell.add("APPROVED BY");
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
	table.setHeaderRows(1);

	// populate Date
	if (!ObjectUtils.isEmpty(purchaseOrderDetailsList)) {
		for (Map<String, Object> rowData : purchaseOrderDetailsList) {
			//for (HeaderDto hearder : headerList) {


			Object value = String.valueOf(purchaseOrderDetailsList.indexOf(rowData) + 1);
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);

			value = rowData.containsKey("QUOTATION_NO") ? rowData.get("QUOTATION_NO") : "";
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);
			
			value = rowData.containsKey("CST_NO") ? rowData.get("CST_NO") : "";
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);

			value = rowData.containsKey("ITEM_NM") ? rowData.get("ITEM_NM") : "";
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);


			value = rowData.containsKey("QUANTITY") ? rowData.get("QUANTITY") : "";
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

			value = rowData.containsKey("UNIT_RATE") ? rowData.get("UNIT_RATE") : "";
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

			value = rowData.containsKey("DISC_AMT") ? rowData.get("DISC_AMT") : "";
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);


			value = rowData.containsKey("VAT_AMT") ? rowData.get("VAT_AMT") : "";
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);

			value = rowData.containsKey("TOTAL_VALUE") ? rowData.get("TOTAL_VALUE") : "";
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);

			value = rowData.containsKey("APP_BY") ? rowData.get("APP_BY") : "";
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);

			value = rowData.containsKey("EMP_NM") ? rowData.get("EMP_NM") : "";
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);

			value = rowData.containsKey("EMP_MODIFIED") ? rowData.get("EMP_MODIFIED") : "";
			cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if (!model.isShowVerticalLines())
				cell.setBorder(Rectangle.BOTTOM);

			table.addCell(cell);


		}
	}

	finalTable.addCell(supllierNameTable);
	finalTable.addCell(table); 
	//log.info("table width [{}]", table.getTotalWidth());
	document.add(finalTable);

}
}
