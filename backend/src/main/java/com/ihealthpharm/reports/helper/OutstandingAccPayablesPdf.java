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
public class OutstandingAccPayablesPdf extends ReportsPDFUtility{
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();

			Map<String, List<Map<String, Object>>> supplierMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("SUPPLIER_NAME")));			


			if(!ObjectUtils.isEmpty(supplierMap)) { 

				for(String supplier :supplierMap.keySet()) {					
					List<Map<String, Object>> supplierAccPayList = supplierMap.get(supplier);
					createTable(document,model,supplierAccPayList,supplier);
				}

				generateTotalTable(document,model,responseList);

			}

		} catch (Exception e) {
			try {
				addMessage(document, ExceptionUtils.getMessage(e));
			} catch (DocumentException e1) {

			}
		} finally {
			document.close();
		}

		return document;
	}

	private void generateTotalTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		DecimalFormat df=new DecimalFormat("0.00");
		double totalAmountPaid;
		double totalAmountToBePaid;

		totalAmountPaid = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT_PAID")?String.valueOf(mapper.get("TOTAL_AMOUNT_PAID")):"0")).sum(); 
		totalAmountToBePaid = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT_TO_BE_PAID")?String.valueOf(mapper.get("TOTAL_AMOUNT_TO_BE_PAID")):"0")).sum(); 

		PdfPTable totalQtyTable = new PdfPTable(3);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 

		String totAmtPaid=df.format(totalAmountPaid);
		Double amountPaid=Double.parseDouble(totAmtPaid);

		PdfPCell nameCell = new PdfPCell(new Phrase("Total Amount To be Paid : "+amountPaid+"   ", title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalQtyTable.addCell(nameCell);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 

		String totAmtToBePaid=df.format(totalAmountToBePaid);
		Double amountToBePaid=Double.parseDouble(totAmtToBePaid);

		PdfPCell nameCell2 = new PdfPCell(new Phrase("Total Amount To Be Paid  : "+amountToBePaid+"   ", title08)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalQtyTable.addCell(nameCell2);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 

		//document.add(totalQtyTable);

	}

	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> supplierAccPayList,
			String supplier) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);


		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 

		PdfPTable supllierNameTable = new PdfPTable(3);
		PdfPCell nameCell = new PdfPCell(new Phrase("Supplier Name : "+supplier, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		supllierNameTable.addCell(nameCell);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 


		PdfPTable table = new PdfPTable(16);
		table.setTotalWidth(530);
		table.setWidths(new int[] {30,30,35,35,35,35,30,35,30,35,35,35,35,30,30,35});
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
		if (!ObjectUtils.isEmpty(supplierAccPayList)) {
			for (Map<String, Object> rowData : supplierAccPayList) {
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

			DecimalFormat df=new DecimalFormat("0.00");
			double totalAmountPaid;
			double totalAmountToBePaid;

			totalAmountPaid = supplierAccPayList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT_PAID")?String.valueOf(mapper.get("TOTAL_AMOUNT_PAID")):"0")).sum(); 
			totalAmountToBePaid = supplierAccPayList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("TOTAL_AMOUNT_TO_BE_PAID")?String.valueOf(mapper.get("TOTAL_AMOUNT_TO_BE_PAID")):"0")).sum();
			
			PdfPTable totalQtyTable = new PdfPTable(3);
			totalQtyTable.setTotalWidth(500);
			totalQtyTable.setWidthPercentage(50);
			totalQtyTable.setLockedWidth(true);
			totalQtyTable.getDefaultCell().setBorder(0); 

			String totAmtPaid=df.format(totalAmountPaid);
			Double amountPaid=Double.parseDouble(totAmtPaid);

			PdfPCell nameCell1 = new PdfPCell(new Phrase("Total Amount Paid  : "+amountPaid+"   ", title08)); 
			nameCell1.setColspan(3);
			nameCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell1.setBorder(0);
			totalQtyTable.addCell(nameCell1);
			totalQtyTable.setLockedWidth(true);
			totalQtyTable.setTotalWidth(500);
			totalQtyTable.getDefaultCell().setBorder(0); 

			String totAmtToBePaid=df.format(totalAmountToBePaid);
			Double amountToBePaid=Double.parseDouble(totAmtToBePaid);

			PdfPCell nameCell2 = new PdfPCell(new Phrase("Total Amount To Be Paid : "+amountToBePaid+"   ", title08)); 
			nameCell2.setColspan(3);
			nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell2.setBorder(0);
			totalQtyTable.addCell(nameCell2);
			totalQtyTable.setLockedWidth(true);
			totalQtyTable.setTotalWidth(500);
			totalQtyTable.getDefaultCell().setBorder(0); 

			finalTable.addCell(supllierNameTable);
			finalTable.addCell(table); 
			document.add(finalTable);			
			document.add(totalQtyTable);

		}

	}
	

	private Object formatData(HeaderDto hearder, Object value) {

		if(ObjectUtils.isEmpty(hearder.getFormat()))
			return value;

		//getDateStringwithouKnowingInputFormat

		return value;


	}
}
