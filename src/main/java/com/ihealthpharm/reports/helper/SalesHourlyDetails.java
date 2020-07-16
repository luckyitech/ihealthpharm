package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
			
			
			if(!ObjectUtils.isEmpty(salesRegisterMap)) { 
				
				for(String billDate :salesRegisterMap.keySet()) {	
				List<Map<String, Object>> salesRegisterDetailsMap = salesRegisterMap.get(billDate);
					
					createTable(document,model,salesRegisterDetailsMap,billDate);
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
		
		DecimalFormat df=new DecimalFormat("0.00");
		double totalCashAmt=0.0;
		double totalCardAmt=0.0;
		double totalMPesaAmt=0.0;
		double totalCreditAmt=0.0;
		double totalChequeAmt=0.0;
		double totalInsuranceAmt=0.0;
		double totalVatAmt=0.0;
		double totalAmount=0.0;
		double grandTotal=0.0;
		
		totalCashAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsValue("CASH")&&mapper.containsKey("AMOUNT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum();  
		totalMPesaAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("M-PESA")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalCardAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("CARD")?String.valueOf(mapper.get("AMOUNT")):"0")).sum();
		totalChequeAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("CHEQUE")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalCreditAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("CREDIT")?String.valueOf(mapper.get("AMOUNT")):"0")).sum();
		totalInsuranceAmt  = responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("AMOUNT")&&mapper.containsValue("INSURANCE")?String.valueOf(mapper.get("AMOUNT")):"0")).sum(); 
		totalVatAmt=responseList.stream().mapToDouble(mapper->Double.parseDouble(mapper.containsKey("VAT_AMT")?String.valueOf(mapper.get("VAT_AMT")):"0")).sum(); 
		totalAmount=Double.parseDouble(df.format((totalCashAmt+totalMPesaAmt+totalCardAmt+totalChequeAmt+totalCreditAmt+totalInsuranceAmt)-(totalVatAmt)));
		grandTotal=Double.parseDouble(df.format(totalAmount+totalVatAmt));
		
		PdfPTable totalQtyTable = new PdfPTable(3);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.setSpacingBefore(30); 
		totalQtyTable.setWidthPercentage(50);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		Font bold = new Font(FontFamily.HELVETICA,9);
		
		PdfPCell nameCell = new PdfPCell(new Phrase("CASH AMOUNT"+"		"+":"+"		"+totalCashAmt, bold)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalQtyTable.addCell(nameCell);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell2 = new PdfPCell(new Phrase("CARD AMOUNT"+"		"+":"+"		"+totalCardAmt, bold)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalQtyTable.addCell(nameCell2);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0); 
		
		PdfPCell nameCell3 = new PdfPCell(new Phrase("CREDIT AMOUNT"+"		"+":"+"		"+totalCreditAmt, bold)); 
		nameCell3.setColspan(3);
		nameCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell3.setBorder(0);
		totalQtyTable.addCell(nameCell3);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		PdfPCell nameCell4 = new PdfPCell(new Phrase("M-PESA AMOUNT"+"		"+":"+"		"+totalMPesaAmt, bold)); 
		nameCell4.setColspan(3);
		nameCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell4.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell4.setBorder(0);
		totalQtyTable.addCell(nameCell4);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		PdfPCell nameCell5 = new PdfPCell(new Phrase("CHEQUE AMOUNT"+"		"+":"+"		"+totalChequeAmt, bold)); 
		nameCell5.setColspan(3);
		nameCell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell5.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell5.setBorder(0);
		totalQtyTable.addCell(nameCell5);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		PdfPCell nameCell6 = new PdfPCell(new Phrase("INSURANCE AMOUNT"+"		"+":"+"		"+totalInsuranceAmt, bold)); 
		nameCell6.setColspan(3);
		nameCell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell6.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell6.setBorder(0);
		totalQtyTable.addCell(nameCell6);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		PdfPCell nameCell7 = new PdfPCell(new Phrase("TOTAL AMOUNT"+"		"+":"+"		"+totalAmount, bold)); 
		nameCell7.setColspan(3);
		nameCell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell7.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell7.setBorder(0);
		totalQtyTable.addCell(nameCell7);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		PdfPCell nameCell8 = new PdfPCell(new Phrase("TOTAL VAT AMOUNT"+"		"+":"+"		"+totalVatAmt, bold)); 
		nameCell8.setColspan(3);
		nameCell8.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell8.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell8.setBorder(0);
		totalQtyTable.addCell(nameCell8);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		PdfPCell nameCell9 = new PdfPCell(new Phrase("GRAND TOTAL"+"		"+":"+"		"+grandTotal, bold)); 
		nameCell9.setColspan(3);
		nameCell9.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell9.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell9.setBorder(0);
		totalQtyTable.addCell(nameCell9);
		totalQtyTable.setLockedWidth(true);
		totalQtyTable.setTotalWidth(500);
		totalQtyTable.getDefaultCell().setBorder(0);
		
		document.add(totalQtyTable);
			
		
	}

	public void createTable(Document document, ReportsMappingModel model, 
			List<Map<String, Object>> salesRegisterDetailsList,String billDate) throws DocumentException, ParseException {

		
		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);

		PdfPTable supllierNameTable = new PdfPTable(3);
		PdfPCell nameCell = new PdfPCell(new Phrase("Sales From : "+salesRegisterDetailsList.get(salesRegisterDetailsList.size()-1 ).get("CREATION_TS"), title08)); 
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
		
		
		PdfPTable table = new PdfPTable(13);
		table.setTotalWidth(530);
		table.setWidths(new int[] {30,40,55,40,50,50,45,50,40,40,30,30,30});
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
			headerCell.add("AMOUNT");
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
				
				value = rowData.containsKey("AMOUNT") ? rowData.get("AMOUNT") : "";
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
				
				value = rowData.containsKey("CREATION_TS") ? rowData.get("CREATION_TS") : "";
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
