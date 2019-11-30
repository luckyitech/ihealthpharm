package com.ihealthpharm.stock.utils;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateQuotationNo {

	public String generateQuotNo(String pharmacyNm, Long quotationCount) {
		return ("QT"+convertDateToString()+generateCount(quotationCount)).toUpperCase();
	}
	
	public static String convertDateToString() {
		Format format = new SimpleDateFormat("ddMMyy");
		return format.format(new Date());
	}
	
	public static String generateCount(Long count) {
		DecimalFormat decimalFormat = new DecimalFormat("0000");
		return decimalFormat.format(count);
	}
	
}
 