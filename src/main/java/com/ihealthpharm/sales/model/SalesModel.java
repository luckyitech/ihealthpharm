package com.ihealthpharm.sales.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.masters.model.CustomerMembershipModel;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.HospitalModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.ProviderModel;
import com.ihealthpharm.tax.model.TaxCategoryModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "sales")
@EqualsAndHashCode(of="billId",callSuper=false)
public class SalesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BILL_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer billId;

	@Column(name = "ADJUSTED_QTY", length = 11)
	private Integer adjustedQty;
	@Column(name = "BALANCE_AMOUNT", length = 25)
	private Float balanceAmount;

	@Column(name = "BILL_CODE", length = 30)
	private String billCode;
	
	@Column(name="PREVIOUS_BILL_CODE", length = 30)
	private String previousBillCode;

	@Column(name = "BILL_DATE", length = 25)
	private LocalDate billDate;

	@Column(name = "CASH_AMOUNT", length = 25)
	private Float cashAmount;

	@Column(name = "CREATION_TS", length = 25)
	private LocalDateTime creationTs;

	@Column(name = "CREATION_USER_ID", length = 50)
	private String creationUserId;

	@Column(name = "CREDIT_DAYS", length = 25)
	private Float creditDays;

	@Column(name = "CREDIT_CARD_AMOUNT", length = 25)
	private Float creditCardAmount;

	@Column(name = "CREDIT_CARD_NO", length = 20)
	private String creditCardNo;

	@Column(name = "CUSTOMER_NM", length = 100)
	private String customerNm;

	@Column(name = "CUSTOMER_PHONE_NO", length = 20)
	private String customerPhoneNo;

	@Column(name = "EFFECTIVE_MARGIN", length = 25)
	private Float effectiveMargin;

	@Column(name = "EFFECTIVE_OVERALL_DISCOUNT", length = 25)
	private Float effectiveOverallDiscount;

	@Column(name = "EFFECTIVE_SALES_DISC", length = 25)
	private Float effectiveSalesDisc;

	@Column(name = "EFFECTIVE_VAT", length = 25)
	private Float effectiveVat;

	@Column(name = "INSURANCE_CONTRIB_AMT", length = 25)
	private Float insuranceContribAmt;

	@Column(name = "INSURANCE_CONTRIB_PERCENT", length = 25)
	private Float insuranceContribPercent;

	@Column(name = "LAST_UPDATE_TS", length = 25)
	private LocalDateTime lastUpdateTs;

	@Column(name = "LAST_UPDATE_USER_ID", length = 50)
	private String lastUpdateUserId;

	@Column(name = "MARGIN_AMT", length = 25)
	private Float marginAmt;

	@Column(name = "MEMBERSHIP_CONTRIB_AMT", length = 25)
	private Float membershipContribAmt;

	@Column(name = "MEMBERSHIP_CONTRIB_PERCENT", length = 25)
	private Float membershipContribPercent;

	@Column(name = "OVERALL_DISCOUNT", length = 25)
	private Float overallDiscount;

	@Column(name = "PAID_AMOUNT", length = 25)
	private Float paidAmount;

	@Column(name = "PAYMENT_STATUS", length = 20)
	private String paymentStatus;

	@Column(name = "PRESCIPTION_DATE", length = 25)
	private LocalDate presciptionDate;

	@Column(name = "PRESCRIPION", length = 25, columnDefinition = "")
	private byte[] prescripion;

	@Column(name = "REMARKS", length = 100)
	private String remarks;

	@Column(name = "ROUNDED_OFF", length = 25)
	private Float roundedOff;

	@Column(name = "SALE_DISC_AMT", length = 25)
	private Float saleDiscAmt;

	@Column(name = "TOTAL_AMOUNT", length = 25)
	private Float totalAmount;
	
	@Column(name = "NET_AMOUNT", length = 25)
	private Float netAmount;

	@Column(name = "TOTAL_PRODUCTS", length = 11)
	private Integer totalProducts;

	@Column(name = "TOTAL_QTY", length = 11)
	private Integer totalQty;

	@Column(name = "UPI_AMOUNT", length = 25)
	private Float upiAmount;

	@Column(name = "UPI_PHONE_NO", length = 20)
	private String upiPhoneNo;

	@Column(name = "VAT_AMT", length = 25)
	private Float vatAmt;
	
	@Column(name="CHEQUE_NUMBER")
	private Integer chequeNumber;
	
	@Column(name="CHEQUE_AMT")
	private Double chequeAmount;
	
	@Column(name="CHEQUE_DATE")
	private String chequeDate;
	
	@Column(name="CREDIT_AMOUNT")
	private Double creditAmount;
	
	
	
	@OneToOne
	@JoinColumn(name = "CUSTOMER_INSURANCE_ID")
	CustomerInsuranceModel customerInsuranceModel;

	@OneToOne
	@JoinColumn(name = "CUSTOMER_MEMBERSHIP_ID")
	CustomerMembershipModel customerMembershipModel;

	@OneToOne
	@JoinColumn(name = "CUSTOMER_ID")
	CustomerModel customerModel;
	
	@OneToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	EmployeeModel employeeModel;

	@OneToOne
	@JoinColumn(name = "PHARMACY_ID")
	PharmacyModel pharmacyModel;

	@OneToOne
	@JoinColumn(name = "PROVIDER_ID")
	ProviderModel providerModel;

	@OneToOne
	@JoinColumn(name = "HOSPITAL_ID")	
	HospitalModel hospitalModel;
	
	
}