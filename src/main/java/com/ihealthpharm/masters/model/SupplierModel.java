package com.ihealthpharm.masters.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.stock.model.PaymentTypeModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "supplier")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@EqualsAndHashCode(of="supplierId",callSuper=false)
public class SupplierModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="SUPPLIER_ID", length=11)
	private Integer supplierId;
	
	@Column(name = "SP_NAME", length=100)
	private String name;
	
	@Column(name = "LICENSE", length=100)
	private String license;
	
	@Column(name = "PHONE_NBR", length=20)
	private String phoneNumber;
	
	@Column(name = "EMAIL_ID", length=50)
	private String emailId;
		
	@Column(name = "ACTIVE_S",length=1)
	private String activeS;
	
	@Column(name = "FAX",length=20)
	private String fax;
	
	@Column(name = "CONTACT_PERSON_EMAIL_ID",length=50)
	private String contactPersonEmailID;
	
	@Column(name = "CONTACT_PERSON_PHONE_NBR",length=20)
	private String contactPersonPhoneNumber;
	
	@Column(name = "CONTACT_PERSON_FIRST_NM",length=50)
	private String contactPersonFirstName;
	
	@Column(name = "CONTACT_PERSON_MIDDLE_NM",length=50)
	private String contactPersonMiddleName;
	
	@Column(name = "CONTACT_PERSON_LAST_NM",length=50)
	private String contactPersonLastName;
	
	@Column(name = "WEBSITE",length=500)
	private String website;
	
	@Column(name = "ADDRESS_LINE1",length=250)
	private String addressLine1;
	
	@Column( name ="ADDRESS_LINE2",length=250)
	private String addressLine2;
	
	@Column(name = "CITY_NM",length=50)
	private String cityName;
	
	@OneToOne
	@JoinColumn(name="PROVINCES_ID")
	private StateModel state;
	
	@Column(name = "ZIP_CD",length=10)
	private String zipCode;
	
	@Column(name = "PAYMENT_TERMS",columnDefinition = "TEXT")
	private String paymentTerms;
	
	@Column(name = "PAYMENT_CREDIT_NET_DAYS", length=11)
	private Integer paymentCreditNetDays;
	
	@Column(name = "LATE_PAYMENT_INTEREST", length=11)
	private Integer latePaymentInterest;
	
	@Column(name = "PIN_NO", length=20)
	private String pinNo;
	
	@Column(name="SL_NO", length=20)
	private String dlNo;
	
	@Column(name = "CST_NO", length=20)
	private String cstNo;
	
	@Column(name = "BANK_NAME", length=50)
	private String bankName;
	
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	
	@Column(name = "IFSC_CODE")
	private String ifscCode;
	
	@Column(name = "MICR_CODE")
	private String micrCode;
	
	@OneToOne
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel country;
	
	@Column(name = "ALLOW_ONLINE_ORDERS", length=1)
	private String allowOnlineOrders;
	
	@Column(name = "ALLOW_MANUAL_ORDERS", length=1)
	private String allowManualOrders;
	
	@Column(name = "ALLOW_PHONE_ORDERS", length=1)
	private String allowPhoneOrders;
	
	@Column(name = "ACCEPT_EXPIRY_RETURNS", length=1)
	private String acceptExpireReturns;
	
	@Column(name = "ACCEPT_DAMAGED_RETURNS", length=1)
	private String acceptDamagedReturns;
	
	@Column(name = "ACCEPT_GOOD_RETURNS", length=1)
	private String acceptGoodReturns;
	
	@Column(name = "SUPPLIER_ALSO_MANUFACTURER", length=1)
	private String supplierAlsoManufacturer;
	
	@Column(name = "SUPPLIES_MEDICAL_NON_MEDICAL_BOTH", length=1)
	private String suppliesMedicalNonMedicalBoth;
	
	@OneToOne
	@JoinColumn(name = "RETURN_CREDIT_TYPE_ID")
	private ReturnCreditTypeModel returnCreditTypeId;
	
	@OneToOne
	@JoinColumn(name = "PAYMENT_TYPE_ID")
	private PaymentTypeModel paymentType;
	
	@Transient
	private List<ItemSupplierDTO> itemsModels;

}
