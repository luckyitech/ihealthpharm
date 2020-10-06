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
import java.util.stream.Stream;

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
public class ItemMovementDetailedPdf extends ReportsPDFUtility{
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();

			Map<String, List<Map<String, Object>>> itemMovementMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (String) map.get("ITEM_NM")));			


			if(!ObjectUtils.isEmpty(itemMovementMap)) { 


				for(String itemName :itemMovementMap.keySet()) {					
					List<Map<String, Object>> itemsMovementList = itemMovementMap.get(itemName);
					Map<String, List<Map<String, Object>>> salesByExpiryMap = itemsMovementList.stream()
							.collect(Collectors.groupingBy(map -> (String) map.get("EXPIRY_DT")));			
					for(String expiryDt :salesByExpiryMap.keySet()) {
						List<Map<String, Object>> salesByExpiryList = salesByExpiryMap.get(expiryDt);
						createTable(document,model,salesByExpiryList,expiryDt);
					}
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


	private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> salesProfitList,String expiryDate) throws DocumentException {

		String openingStock=null;
		int closingStock=0;

		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String itemName=String.valueOf(salesProfitList.get(0).get("ITEM_NM"));
		if((String.valueOf(salesProfitList.get(0).get("ENTRY_TYPE")).equals("Sales Billing"))) {
		 openingStock=String.valueOf(salesProfitList.get(0).get("OPENING_STOCK"));
		 closingStock=Integer.parseInt(openingStock);
		}else {
		 openingStock=String.valueOf(salesProfitList.get(0).get("QUANTITY"));
		}
		
		
			for(int i=0;i<salesProfitList.size();i++) {

				//System.out.println(String.valueOf(salesProfitList.get(i).get("ENTRY_TYPE")));

				if((String.valueOf(salesProfitList.get(i).get("ENTRY_TYPE")).equals("Sales Billing")))	{
					//System.out.println("Sales Billing enter");
					closingStock=closingStock-Integer.parseInt(String.valueOf((salesProfitList.get(i).get("QUANTITY"))));

				}
				else{
					closingStock=closingStock+Integer.parseInt(String.valueOf((salesProfitList.get(i).get("QUANTITY"))));

				}

			}
		
		//String closingStock=String.valueOf(salesProfitList.get(salesProfitList.size()-1).get("QUANTITY"));
		String reportHeader = model.getReportHeader();
		List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);


		PdfPTable finalTable = new PdfPTable(1);
		finalTable.setTotalWidth(500);
		finalTable.setWidthPercentage(50);
		finalTable.setLockedWidth(true);
		finalTable.getDefaultCell().setBorder(0); 

		Font bold = new Font(FontFamily.HELVETICA,9);
		PdfPTable salePersonNameTable = new PdfPTable(3);
		PdfPCell nameCell = new PdfPCell(new Phrase("Item  : "+itemName+  "       "
				+ "Exp. Dt  :  "+expiryDate+"         "+"O.Stock  : "+openingStock+  "       "
				+ "C.Stock  :  "+closingStock+"         ", bold)); 

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



			//			long totalSales  = salesProfitList.stream().mapToInt(mapper->Integer.parseInt((mapper.containsValue("Sales Billing") && !ObjectUtils.isEmpty(mapper.get("Sales Billing"))) ?String.valueOf(mapper.get("Sales Billing")):"0")).count();  

			long totalSales = salesProfitList.stream() .filter(x -> (x.containsValue("Sales Billing"))||(x.containsValue("Sales Update"))||(x.containsValue("Sales Maintenance (+)"))) .count();
			long totalInv = salesProfitList.stream() .filter(x -> (x.containsValue("Purchase"))||(x.containsValue("Purchase Update"))||(x.containsValue("Invoice Addition"))) .count();
			long totalSalesRetrun = salesProfitList.stream() .filter(x -> x.containsValue("Sales Canceling")).count();
			long totalStockTake = salesProfitList.stream() .filter(x -> (x.containsValue("Stock Take"))||(x.containsValue("Stock Adjustment"))) .count();
			long totalStockAdd = salesProfitList.stream() .filter(x -> (x.containsValue("Stock"))||(x.containsValue("Stock Update"))||(x.containsValue("New Stock Addition"))) .count();
			long totalPurReturn = salesProfitList.stream() .filter(x -> (x.containsValue("Purchase Return"))||x.containsValue("Purchase Delete")) .count();




			PdfPTable totalAmountTable = new PdfPTable(2);
			totalAmountTable.setTotalWidth(500);
			//totalProfitTable.setSpacingBefore(30); 
			totalAmountTable.setWidthPercentage(50);
			totalAmountTable.setLockedWidth(true);
			totalAmountTable.getDefaultCell().setBorder(0); 


			if(salesProfitList.stream() .filter(x -> (x.containsValue("Sales Billing"))||(x.containsValue("Sales Update"))||(x.containsValue("Sales Maintenance (+)"))) .count()>0) {
				PdfPCell nameCell1 = new PdfPCell(new Phrase("Total sales :"+" "+" : "+"	"+totalSales, title08)); 
				nameCell1.setColspan(3);
				nameCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				nameCell1.setVerticalAlignment(Element.ALIGN_TOP);
				nameCell1.setBorder(0);
				totalAmountTable.addCell(nameCell1);
				totalAmountTable.setLockedWidth(true);
				totalAmountTable.setTotalWidth(500);
				totalAmountTable.getDefaultCell().setBorder(0); 
			}

			if(salesProfitList.stream() .filter(x -> (x.containsValue("Purchase"))||(x.containsValue("Purchase Update"))||(x.containsValue("Invoice Addition"))) .count()>0) {
				PdfPCell nameCell2 = new PdfPCell(new Phrase("Total Invoice :"+" "+" : "+"	"+totalInv, title08)); 
				nameCell2.setColspan(3);
				nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
				nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
				nameCell2.setBorder(0);
				totalAmountTable.addCell(nameCell2);
				totalAmountTable.setLockedWidth(true);
				totalAmountTable.setTotalWidth(500);
				totalAmountTable.getDefaultCell().setBorder(0);
			}

			if(salesProfitList.stream() .filter(x -> x.containsValue("Sales Canceling")).count()>0) {
				PdfPCell nameCell3 = new PdfPCell(new Phrase("Total Sales Return :"+" "+" : "+"	"+totalSalesRetrun, title08)); 
				nameCell3.setColspan(3);
				nameCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
				nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
				nameCell3.setBorder(0);
				totalAmountTable.addCell(nameCell3);
				totalAmountTable.setLockedWidth(true);
				totalAmountTable.setTotalWidth(500);
				totalAmountTable.getDefaultCell().setBorder(0);
			}

			if(salesProfitList.stream() .filter(x -> (x.containsValue("Stock Take"))||(x.containsValue("Stock Adjustment"))) .count()>0) {
				PdfPCell nameCell4 = new PdfPCell(new Phrase("Total Stock Take :"+" "+" : "+"	"+totalStockTake, title08)); 
				nameCell4.setColspan(3);
				nameCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
				nameCell4.setVerticalAlignment(Element.ALIGN_TOP);
				nameCell4.setBorder(0);
				totalAmountTable.addCell(nameCell4);
				totalAmountTable.setLockedWidth(true);
				totalAmountTable.setTotalWidth(500);
				totalAmountTable.getDefaultCell().setBorder(0);
			}

			if(salesProfitList.stream() .filter(x -> (x.containsValue("Stock"))||(x.containsValue("Stock Update"))||(x.containsValue("New Stock Addition"))) .count()>0) {
				PdfPCell nameCell5 = new PdfPCell(new Phrase("Total Stock Addition :"+" "+" : "+"	"+totalStockAdd, title08)); 
				nameCell5.setColspan(3);
				nameCell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
				nameCell5.setVerticalAlignment(Element.ALIGN_TOP);
				nameCell5.setBorder(0);
				totalAmountTable.addCell(nameCell5);
				totalAmountTable.setLockedWidth(true);
				totalAmountTable.setTotalWidth(500);
				totalAmountTable.getDefaultCell().setBorder(0);
			}

			if(salesProfitList.stream() .filter(x -> (x.containsValue("Purchase Return"))||x.containsValue("Purchase Delete")) .count()>0) {
				PdfPCell nameCell6 = new PdfPCell(new Phrase("Total Purchase Return :"+" "+" : "+"	"+totalPurReturn, title08)); 
				nameCell6.setColspan(3);
				nameCell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
				nameCell6.setVerticalAlignment(Element.ALIGN_TOP);
				nameCell6.setBorder(0);
				totalAmountTable.addCell(nameCell6);
				totalAmountTable.setLockedWidth(true);
				totalAmountTable.setTotalWidth(500);
				totalAmountTable.getDefaultCell().setBorder(0);
			}

			totalAmountTable.setSpacingAfter(15);

			document.add(salePersonNameTable);
			finalTable.addCell(table); 
			document.add(finalTable);

			document.add(totalAmountTable);


		}
	}



	private Object formatData(HeaderDto hearder, Object value) {

		if(ObjectUtils.isEmpty(hearder.getFormat()))
			return value;

		//getDateStringwithouKnowingInputFormat

		return value;


	}

}
