package com.ihealthpharm.stock.utils;

public class GenerateGRNNo {

	public String generateGNR(String pharmacyNm, Long invoiceCount) {
		String grnNumber = pharmacyNm.substring(0, 2).toUpperCase() + invoiceCount;
		return grnNumber;
	}
	
	public static void main(String a[]) {
		GenerateGRNNo generateGRNNo = new GenerateGRNNo();
		System.out.println(generateGRNNo.generateGNR("Guna", 1l));
	}
}
 