package com.ihealthpharm.stock.utils;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateGRNNo {

	public String generateGNR(String pharmacyNm, Long count) {
		return ("PI" +convertDateToString()+generateCount(count)).toUpperCase();
	}
	
	public String generatePONumber(String pharmacyNm, Long count) {
		return ("PO" +convertDateToString()+generateCount(count)).toUpperCase();
	}
	
	public String generatePRNumber(String pharmacyNm, Long count) {
		return ("PR" +convertDateToString()+generateCount(count)).toUpperCase();
	}
	
	public String convertDateToString() {
		Format format = new SimpleDateFormat("ddMMyy");
		return format.format(new Date());
	}
	
	public String generateCount(Long count) {
		DecimalFormat decimalFormat = new DecimalFormat("0000");
		return decimalFormat.format(count);
	}

}
 