package com.ihealthpharm.stock.utils;

public class GenerateGRNNo {

	public String generateGNR(String pharmacyNm, Long invoiceCount) {
		String grnNumber = pharmacyNm.substring(0, 2).toUpperCase() + invoiceCount;
		return "INV" + grnNumber;
	}
	
	public String generatePONumber(String pharmacyNm, Long invoiceCount) {
		String grnNumber = pharmacyNm.substring(0, 2).toUpperCase() + invoiceCount;
		return "PO" + grnNumber;
	}
	
	public String generatePRNumber(String pharmacyNm, Long invoiceCount) {
		String grnNumber = pharmacyNm.substring(0, 2).toUpperCase() + invoiceCount;
		return "PR" + grnNumber;
	}
	
	public static void main(String a[]) {
		GenerateGRNNo generateGRNNo = new GenerateGRNNo();
		System.out.println(generateGRNNo.generateGNR("Guna", 1l));
	}
}
 