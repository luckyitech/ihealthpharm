package com.ihealthpharm.stock.utils;

public class GenerateGRNNo {

	public String generateGNR(String distributorNm, Long distributorInvoiceCount) {
		String grnNumber = distributorNm.substring(0, 2).toUpperCase() + distributorInvoiceCount;
		return grnNumber;
	}
	
	public static void main(String a[]) {
		GenerateGRNNo generateGRNNo = new GenerateGRNNo();
		System.out.println(generateGRNNo.generateGNR("Guna", 1l));
	}
}
 