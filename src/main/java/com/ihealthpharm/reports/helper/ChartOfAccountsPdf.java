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
public class ChartOfAccountsPdf extends ReportsPDFUtility{

	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();

			Map<String, List<Map<String, Object>>> chartOfAccountsMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("ACCOUNT_TYPE")));			


			if(!ObjectUtils.isEmpty(chartOfAccountsMap)) { 

				for(String chartOfAccount :chartOfAccountsMap.keySet()) {					
					List<Map<String, Object>> chartOfAccountsList = chartOfAccountsMap.get(chartOfAccount);
					createTable(document,model,chartOfAccountsList,chartOfAccount);
				}


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


	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> chartOfAccountList,
			String chartOfAccount) throws DocumentException {

		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);


		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 

		PdfPTable supllierNameTable = new PdfPTable(3);
		PdfPCell nameCell = new PdfPCell(new Phrase("Account Type : "+chartOfAccount, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		supllierNameTable.addCell(nameCell);
		supllierNameTable.setLockedWidth(true);
		supllierNameTable.setTotalWidth(500);
		supllierNameTable.getDefaultCell().setBorder(0); 


		PdfPTable table = new PdfPTable(10);
		table.setTotalWidth(500);
		table.setWidths(new int[] {25,50,50,75,50,50,50,50,50,50});
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
		headerCell.add("ACCOUNT NO");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);
		
		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("ACCOUNT NAME");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);
		
		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("ACCOUNT TYPE");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("INITIAL RANGE");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("MAXIMUM");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("TRANSACTION LIMIT");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("TOTAL LIMIT");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("CURRENT BALANCE");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		headerCell = new Paragraph();
		headerCell.setFont(headerFont);
		headerCell.add("AS OF DATE");
		cell = new PdfPCell(headerCell);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (!model.isShowVerticalLines())
			cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		
		//}
		table.setHeaderRows(1);

		// populate Date
		if (!ObjectUtils.isEmpty(chartOfAccountList)) {
			for (Map<String, Object> rowData : chartOfAccountList) {
				//for (HeaderDto hearder : headerList) {

				DecimalFormat df=new DecimalFormat("0.00");
				
				Object value = String.valueOf(chartOfAccountList.indexOf(rowData) + 1);
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("ACCOUNT_NO") ? rowData.get("ACCOUNT_NO") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("ACCOUNT_NAME") ? rowData.get("ACCOUNT_NAME") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("ACCOUNT_TYPE") ? rowData.get("ACCOUNT_TYPE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);
				
				value = rowData.containsKey("INITIAL") ? rowData.get("INITIAL") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("MAXIMUM") ? rowData.get("MAXIMUM") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("TRANSACTION_LIMIT") ? df.format(rowData.get("TRANSACTION_LIMIT")) : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				
				value = rowData.containsKey("TOTAL_LIMIT") ? df.format(rowData.get("TOTAL_LIMIT")) : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("CURRENT_BALANCE") ? df.format(rowData.get("CURRENT_BALANCE")) : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);

				value = rowData.containsKey("AS_OF_DATE") ? rowData.get("AS_OF_DATE") : "";
				cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);

				table.addCell(cell);


			}

			finalTable.addCell(supllierNameTable);
			finalTable.addCell(table); 
			document.add(finalTable);			


		}


	}
}
