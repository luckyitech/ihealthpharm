package com.ihealthpharm.stock.utils;

public class GenerateQuotationNo {

	public String generateQuotNo(String pharmacyNm, Long quotationCount) {
		String quotationNo = pharmacyNm.substring(0, 2).toUpperCase() + quotationCount;
		return quotationNo;
	}
	
	public static void main(String a[]) {
		GenerateQuotationNo generateGRNNo = new GenerateQuotationNo();
		System.out.println(generateGRNNo.generateQuotNo("Guna", 1l));
	}
}
 