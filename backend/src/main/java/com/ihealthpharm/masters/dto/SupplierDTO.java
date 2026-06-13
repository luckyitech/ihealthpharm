package com.ihealthpharm.masters.dto;

import com.ihealthpharm.masters.model.CountryModel;
import com.ihealthpharm.masters.model.ReturnCreditTypeModel;
import com.ihealthpharm.masters.model.StateModel;

public class SupplierDTO {

	private Integer supplierId;
	
	private String name;
	
	private String license;
	
	private String phoneNumber;
	
	private String emailId;
		
	private char activeS;
	
	private String fax;
	
	private String contactPersonEmailID;
	
	private String contactPersonPhoneNumber;
	
	private String contactPersonFirstName;
	
	private String contactPersonMiddleName;
	
	private String contactPersonLastName;
	
	private String website;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String cityName;
	
	private StateModel state;
	
	private String zipCode;
	
	private String paymentTerms;
	
	private Integer paymentCreditNetDays;
	
	private Integer latePaymentInterest;
	
	private String pinNo;
	
	private String dlNo;
	
	private String cstNo;
	
	private CountryModel country;
	
	private char allowOnlineOrders;
	
	private char allowManualOrders;
	
	private char allowPhoneOrders;
	
	private char acceptExpireReturns;
	
	private char acceptDamagedReturns;
	
	private char acceptGoodReturns;
	
	private char supplierAlsoManufacturer;
	
	private char suppliesMedicalNonMedicalBoth;
	
	private ReturnCreditTypeModel returnCreditTypeId;

	public SupplierDTO(Integer supplierId, String name, String license, String phoneNumber, String emailId,
			char activeS, String fax, String contactPersonEmailID, String contactPersonPhoneNumber,
			String contactPersonFirstName, String contactPersonMiddleName, String contactPersonLastName, String website,
			String addressLine1, String addressLine2, String cityName, StateModel state, String zipCode,
			String paymentTerms, Integer paymentCreditNetDays, Integer latePaymentInterest, String pinNo, String dlNo,
			String cstNo, CountryModel country, char allowOnlineOrders, char allowManualOrders, char allowPhoneOrders,
			char acceptExpireReturns, char acceptDamagedReturns, char acceptGoodReturns, char supplierAlsoManufacturer,
			char suppliesMedicalNonMedicalBoth, ReturnCreditTypeModel returnCreditTypeId) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.license = license;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.activeS = activeS;
		this.fax = fax;
		this.contactPersonEmailID = contactPersonEmailID;
		this.contactPersonPhoneNumber = contactPersonPhoneNumber;
		this.contactPersonFirstName = contactPersonFirstName;
		this.contactPersonMiddleName = contactPersonMiddleName;
		this.contactPersonLastName = contactPersonLastName;
		this.website = website;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.cityName = cityName;
		this.state = state;
		this.zipCode = zipCode;
		this.paymentTerms = paymentTerms;
		this.paymentCreditNetDays = paymentCreditNetDays;
		this.latePaymentInterest = latePaymentInterest;
		this.pinNo = pinNo;
		this.dlNo = dlNo;
		this.cstNo = cstNo;
		this.country = country;
		this.allowOnlineOrders = allowOnlineOrders;
		this.allowManualOrders = allowManualOrders;
		this.allowPhoneOrders = allowPhoneOrders;
		this.acceptExpireReturns = acceptExpireReturns;
		this.acceptDamagedReturns = acceptDamagedReturns;
		this.acceptGoodReturns = acceptGoodReturns;
		this.supplierAlsoManufacturer = supplierAlsoManufacturer;
		this.suppliesMedicalNonMedicalBoth = suppliesMedicalNonMedicalBoth;
		this.returnCreditTypeId = returnCreditTypeId;
	}
	
	public SupplierDTO() {};
}
