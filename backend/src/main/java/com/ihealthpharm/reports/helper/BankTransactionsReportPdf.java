package com.ihealthpharm.reports.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
public class BankTransactionsReportPdf extends ReportsPDFUtility{
	
	
	@Override
	public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
			File responseFile,String inputJson) {

		HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
		Document document = new Document(PageSize.A4, 36, 36, 150, 36);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
			writer.setPageEvent(event); 
			document.open();
			
			Map<Date, List<Map<String, Object>>> pettyExpMap = responseList.stream()
					.collect(Collectors.groupingBy(map -> (Date) map.get("TXN_DATE")));	

			
			List<Date> datesList = new ArrayList<>();


			if(!ObjectUtils.isEmpty(pettyExpMap)) { 

				datesList.addAll(pettyExpMap.keySet());
				Collections.sort(datesList);

				for(int i = 0; i < datesList.size(); i++) {	
					List<Map<String, Object>> PettyCashList = pettyExpMap.get(datesList.get(i));
					createTable(document,model,PettyCashList);
					generateTotalTable(document,model,PettyCashList);

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

	private void generateTotalTable(Document document, ReportsMappingModel model, List<Map<String, Object>> responseList) throws DocumentException {
		DecimalFormat df = new DecimalFormat("####0.00");
		double partyBalance=0.00;
		double counterPartyBalance=0.00;
	
		partyBalance  = Double.parseDouble(String.valueOf(responseList.get(responseList.size()-1).get("BALANCE")));
		if(NumberUtils.isNumber(String.valueOf(responseList.get(responseList.size()-1).get("COUNTER_PARTY_BALANCE")))) {
			counterPartyBalance  = Double.parseDouble(String.valueOf(responseList.get(responseList.size()-1).get("COUNTER_PARTY_BALANCE")));
		}
		System.out.println(partyBalance);
		PdfPTable totalProfitTable = new PdfPTable(2);
		totalProfitTable.setTotalWidth(500);
		//totalProfitTable.setSpacingBefore(30); 
		totalProfitTable.setWidthPercentage(50);
		totalProfitTable.setLockedWidth(true);
		totalProfitTable.getDefaultCell().setBorder(0); 

		
		String partyBalanceRounded = String.format("%.2f", partyBalance);	
		String counterPartyBalanceRounded = String.format("%.2f", counterPartyBalance);


		String partyAccount=String.valueOf(responseList.get(responseList.size()-1).get("PARTY"));
		String counterPartyAccount=String.valueOf(responseList.get(responseList.size()-1).get("COUNTER_PARTY"));

		PdfPCell nameCell = new PdfPCell(new Phrase(partyAccount+" "+"  Balance"+" "+" : "+"	"+partyBalanceRounded, title08)); 
		nameCell.setColspan(3);
		nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell.setBorder(0);
		totalProfitTable.addCell(nameCell);
		totalProfitTable.setLockedWidth(true);
		totalProfitTable.setTotalWidth(500);
		totalProfitTable.getDefaultCell().setBorder(0); 

		PdfPCell nameCell2 = new PdfPCell(new Phrase(counterPartyAccount+" "+"  Balance"+" "+" : "+"	"+counterPartyBalanceRounded, title08)); 
		nameCell2.setColspan(3);
		nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
		nameCell2.setBorder(0);
		totalProfitTable.addCell(nameCell2);
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
