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
import com.ihealthpharm.masters.model.InsuranceModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.ProviderModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "sales")
@Getter
@Setter
@ToString
public class SalesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BILL_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer billId; 

	@Column(name = "ADJUSTED_QTY", length = 11)
	private Integer adjustedQty;

	@Column(name = "BALANCE_AMOUNT", length = 25)
	private Double balanceAmount;

	@Column(name = "BILL_CODE", length = 30)
	private String billCode;

	@Column(name = "BILL_DATE", length = 25)
	private LocalDate billDate;

	@Column(name = "CASH_AMOUNT", length = 25)
	private Double cashAmount;

	@Column(name = "CREATION_TS", length = 25)
	private LocalDateTime creationTs;

	@Column(name = "CREATION_USER_ID", length = 50)
	private String creationUserId;

	@Column(name = "CREDIT_AMOUNT", length = 25)
	private Double creditAmount;

	@Column(name = "TOTAL_AMOUNT", length = 25)
	private Double totalAmount;

	@Column(name = "PAID_AMOUNT", length = 25)
	private Double paidAmount;
	
	@Column(name = "CREDIT_CARD_AMOUNT", length = 25)
	private Double creditCardAmount;

	
	@Column(name = "CREDIT_CARD_NO", length = 20)
	private String creditCardNo;

	@Column(name = "CUSTOMER_NM", length = 100)
	private String customerNm;

	@Column(name = "CUSTOMER_PHONE_NO", length = 11)
	private Integer customerPhoneNo;

	@Column(name = "EFFECTIVE_MARGIN", length = 25)
	private Double effectiveMargin;

	@Column(name = "EFFECTIVE_VAT", length = 25)
	private Double effectiveVat;

	@Column(name = "INSURANCE_CONTRIB_AMOUNT", length = 25)
	private Double insuranceContribAmount;

	@Column(name = "INSURANCE_CONTRIB_PERCENT", length = 25)
	private Double insuranceContribPercent;

	@Column(name = "LAST_UPDATE_TS", length = 25)
	private LocalDateTime lastUpdateTs;

	@Column(name = "LAST_UPDATE_USER_ID", length = 50)
	private String lastUpdateUserId;

	@Column(name = "OVERALL_DISCOUNT", length = 25)
	private Double overallDiscount;

	@Column(name = "OVERALL_DISCOUNT_PERCENTAGE", length = 25)
	private Double overallDiscountPercentage;

	@Column(name = "PRESCIPTION_DATE")
	private LocalDate presciptionDate;

	@Column(name = "PRESCRIPION")
	private Byte[] prescripion;

	@Column(name = "REMARKS", length = 100)
	private String remarks;

	@Column(name = "ROUNDED_OFF", length = 25)
	private Double roundedOff;

	@Column(name = "TOTAL_PRODUCTS", length = 11)
	private Integer totalProducts;

	@Column(name = "TOTAL_QTY", length = 11)
	private Integer totalQty;

	@Column(name = "UPI_AMOUNT", length = 25)
	private Double upiAmount;

	@Column(name = "UPI_PHONE_NO", length = 20)
	private String upiPhoneNo;

	@OneToOne
	@JoinColumn(name = "CUSTOMER_MEMBERSHIP_ID")
	CustomerMembershipModel customerMembershipModel;

	@OneToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	EmployeeModel employeeModel;

	
	@OneToOne
	@JoinColumn(name = "CUSTOMER_INSURANCE_ID")
	CustomerInsuranceModel customerInsuranceModel;
	
	@OneToOne
	@JoinColumn(name = "CUSTOMER_ID")
	CustomerModel customerModel;

	@OneToOne
	@JoinColumn(name = "PHARMACY_ID")
	PharmacyModel pharmacyModel;

	@OneToOne
	@JoinColumn(name = "PROVIDER_ID")
	ProviderModel providerModel;
}