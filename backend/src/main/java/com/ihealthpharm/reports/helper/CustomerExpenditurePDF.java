
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
	public class CustomerExpenditurePDF  extends ReportsPDFUtility{
		@Override
		public Document generateReport(List<Map<String, Object>> responseList, ReportsMappingModel model,
				File responseFile,String inputJson) {
			
			HeaderFooterPageEvent event =new HeaderFooterPageEvent(model);
			 Document document = new Document(PageSize.A4, 36, 36, 150, 36);

			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(responseFile));
				writer.setPageEvent(event); 
				document.open();
//				Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
//				createTable(document,model,responseList,dataMap);
//				generateTotalTable(document,model,responseList);
				
				Map<String,Object> dataMap= (Map<String, Object>) JsonUtility.jsonToMap(inputJson);
//				Map<String, List<Map<String, Object>>> customerstmtMap = responseList.stream()
//						.collect(Collectors.groupingBy(map -> (String) map.get("BILL_CODE")));			
				createTable(document,model,responseList,dataMap);
				
//				if(!ObjectUtils.isEmpty(customerstmtMap)) { 
//					
//					for(String customerSummary :customerstmtMap.keySet()) {					
//						List<Map<String, Object>> productList = customerstmtMap.get(customerSummary);
//						
//						createTable(document,model,productList,customerSummary,dataMap);
//					}
					
					generateTotalTable(document,model,responseList);
					
		//		}

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
			double totalAmtReceived;
			double totalAmtPaid;
			double totalTotalOutstanding;
			double totalTotalOutstanding1;
			//double totalAmtToBePaid;
			totalAmtReceived  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("TOTAL_AMOUNT") && !ObjectUtils.isEmpty(mapper.get("TOTAL_AMOUNT"))) ?String.valueOf(mapper.get("TOTAL_AMOUNT")):"0")).sum();  
			totalAmtPaid  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("PAID_AMOUNT") && !ObjectUtils.isEmpty(mapper.get("PAID_AMOUNT"))) ?String.valueOf(mapper.get("PAID_AMOUNT")):"0")).sum();  
			totalTotalOutstanding  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("OVERALL_DISCOUNT") && !ObjectUtils.isEmpty(mapper.get("OVERALL_DISCOUNT"))) ?String.valueOf(mapper.get("OVERALL_DISCOUNT")):"0")).sum();  
			totalTotalOutstanding1  = responseList.stream().mapToDouble(mapper->Double.parseDouble((mapper.containsKey("BALANCE_AMOUNT") && !ObjectUtils.isEmpty(mapper.get("BALANCE_AMOUNT"))) ?String.valueOf(mapper.get("BALANCE_AMOUNT")):"0")).sum();  
	
			PdfPTable totalQtyTable = new PdfPTable(2);
			totalQtyTable.setTotalWidth(500);
			totalQtyTable.setSpacingBefore(30); 
			totalQtyTable.setWidthPercentage(50);
			totalQtyTable.setLockedWidth(true);
			totalQtyTable.getDefaultCell().setBorder(0); 
			
			String totAmountRecieved=df.format(totalAmtReceived);
			Double totalReceived=Double.parseDouble(totAmountRecieved);
			
			Font bold = new Font(FontFamily.HELVETICA,9);
			
			PdfPCell nameCell = new PdfPCell(new Phrase("TOTAL AMOUNT"+" "+" : "+"	"+totalReceived, bold)); 
			nameCell.setColspan(3);
			nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell.setBorder(0);
			totalQtyTable.addCell(nameCell);
			totalQtyTable.setLockedWidth(true);
			totalQtyTable.setTotalWidth(500);
			totalQtyTable.getDefaultCell().setBorder(0); 
			
			
			PdfPCell nameCell2 = new PdfPCell(new Phrase("TOTAL AMOUNT PAID"+" "+":"+" "+totalAmtPaid, bold)); 
			nameCell2.setColspan(3);
			nameCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell2.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell2.setBorder(0);
			totalQtyTable.addCell(nameCell2);
			totalQtyTable.setLockedWidth(true);
			totalQtyTable.setTotalWidth(500);
			totalQtyTable.getDefaultCell().setBorder(0); 
			
			PdfPCell nameCell3 = new PdfPCell(new Phrase("TOTAL DISCOUNT"+" "+":"+" "+totalTotalOutstanding, bold)); 
			nameCell3.setColspan(3);
			nameCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell3.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell3.setBorder(0);
			totalQtyTable.addCell(nameCell3);
			totalQtyTable.setLockedWidth(true);
			totalQtyTable.setTotalWidth(500);
			totalQtyTable.getDefaultCell().setBorder(0);
			
			PdfPCell nameCell4 = new PdfPCell(new Phrase("TOTAL OUTSTANDING AMOUNT"+" "+":"+" "+totalTotalOutstanding1, bold)); 
			nameCell4.setColspan(3);
			nameCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameCell4.setVerticalAlignment(Element.ALIGN_TOP);
			nameCell4.setBorder(0);
			totalQtyTable.addCell(nameCell4);
			totalQtyTable.setLockedWidth(true);
			totalQtyTable.setTotalWidth(500);
			totalQtyTable.getDefaultCell().setBorder(0);
 
			document.add(totalQtyTable);
		
		}
		private void createTable(Document document, ReportsMappingModel model, List<Map<String, Object>> accountPayablesList,Map<String,Object> dataMap) throws DocumentException {
			System.out.println("called");
			String reportHeader = model.getReportHeader();
			List<HeaderDto> headerList = JsonUtility.jsonToList(reportHeader, HeaderDto.class);
	//String approvedDate=(ObjectUtils.isEmpty(accountPayablesList))?"":String.valueOf(accountPayablesList.get(0).get("TO_APPROVED_DATE"));
			
			PdfPTable finalTable = new PdfPTable(1);
			finalTable.setTotalWidth(500);
			finalTable.setWidthPercentage(50);
			finalTable.setLockedWidth(true);
			finalTable.getDefaultCell().setBorder(0); 
							
			PdfPTable table = new PdfPTable(10);
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
				headerCell.add("BILL NO");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);				
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("BILL DATE");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("TOTAL QTY");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);		
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("TOTAL AMOUNT");
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
				headerCell.add("AMOUNT PAID");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("TOTAL DISCOUNT");
				cell = new PdfPCell(headerCell);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				if (!model.isShowVerticalLines())
					cell.setBorder(Rectangle.BOTTOM);
				
				table.addCell(cell);
				
				headerCell = new Paragraph();
				headerCell.setFont(headerFont);
				headerCell.add("OUTSTANDING AMOUNT");
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
			
				
			
			//}
			table.setHeaderRows(1);

			// populate Date
			if (!ObjectUtils.isEmpty(accountPayablesList)) {
				for (Map<String, Object> rowData : accountPayablesList) {
					

					Object value = String.valueOf(accountPayablesList.indexOf(rowData) + 1);
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);

					value = rowData.containsKey("BILL_CODE") ? rowData.get("BILL_CODE") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("BILL_DATE") ? rowData.get("BILL_DATE") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
										
					value = rowData.containsKey("TOTAL_QTY") ? rowData.get("TOTAL_QTY") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
				
					value = rowData.containsKey("TOTAL_AMOUNT") ? rowData.get("TOTAL_AMOUNT") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("PAYMENT_STATUS") ? rowData.get("PAYMENT_STATUS") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
					
					value = rowData.containsKey("PAID_AMOUNT") ? rowData.get("PAID_AMOUNT") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
			
					value = rowData.containsKey("OVERALL_DISCOUNT") ? rowData.get("OVERALL_DISCOUNT") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
			
					value = rowData.containsKey("BALANCE_AMOUNT") ? rowData.get("BALANCE_AMOUNT") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
								
					value = rowData.containsKey("created_by") ? rowData.get("created_by") : "";
					cell = new PdfPCell(new Phrase(String.valueOf(value), title06));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!model.isShowVerticalLines())
						cell.setBorder(Rectangle.BOTTOM);

					table.addCell(cell);
				
				}
			}
			
			finalTable.addCell(table); 
			//log.info("table width [{}]", table.getTotalWidth());
			document.add(finalTable);

		}

	}
