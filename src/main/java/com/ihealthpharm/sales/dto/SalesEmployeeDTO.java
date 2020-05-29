package com.ihealthpharm.sales.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.masters.model.CustomerMembershipModel;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.HospitalModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.ProviderModel;

import lombok.Data;

@Data
public class SalesEmployeeDTO {
	
	private Integer billId;

	private Integer adjustedQty;
	
	private Float balanceAmount;

	private String billCode;
	
	private String previousBillCode;

	private LocalDate billDate;

	private Float cashAmount;

	private LocalDateTime creationTs;

	private String creationUserId;

	private Float creditDays;

	private Float creditCardAmount;

	private String creditCardNo;
	
	private String creditCardAuthNo;

	private String customerNm;

	private String customerPhoneNo;

	private Float effectiveMargin;

	private Float effectiveOverallDiscount;

	private Float effectiveSalesDisc;

	private Float effectiveVat;

	private Float insuranceContribAmt;

	private Float insuranceContribPercent;

	private LocalDateTime lastUpdateTs;

	private String lastUpdateUserId;

	private Float marginAmt;

	private Float membershipContribAmt;

	private Float membershipContribPercent;

	private Float overallDiscount;

	private Float paidAmount;

	private String paymentStatus;

	private LocalDate presciptionDate;

	private byte[] prescripion;

	private String remarks;

	private Float roundedOff;

	private Float saleDiscAmt;

	private Float totalAmount;
	
	private Float netAmount;

	private Integer totalProducts;

	private Integer totalQty;

	private Float upiAmount;

	private String upiPhoneNo;
	
	private String upiTransactionId;

	private Float vatAmt;
	
	private Integer chequeNumber;
	
	private Double chequeAmount;
	
	private String chequeDate;
	
	private Double creditAmount;
	
	CustomerInsuranceModel customerInsuranceModel;

	CustomerMembershipModel customerMembershipModel;

	CustomerModel customerModel;
	
	String employeeModel;

	PharmacyModel pharmacyModel;

	ProviderModel providerModel;

	HospitalModel hospitalModel;
	
	private Character activeS;

	public SalesEmployeeDTO(Integer billId, Integer adjustedQty, Float balanceAmount, String billCode,
			String previousBillCode, LocalDate billDate, Float cashAmount, LocalDateTime creationTs,
			String creationUserId, Float creditDays, Float creditCardAmount, String creditCardNo,
			String creditCardAuthNo, String customerNm, String customerPhoneNo, Float effectiveMargin,
			Float effectiveOverallDiscount, Float effectiveSalesDisc, Float effectiveVat, Float insuranceContribAmt,
			Float insuranceContribPercent, LocalDateTime lastUpdateTs, String lastUpdateUserId, Float marginAmt,
			Float membershipContribAmt, Float membershipContribPercent, Float overallDiscount, Float paidAmount,
			String paymentStatus, LocalDate presciptionDate, byte[] prescripion, String remarks, Float roundedOff,
			Float saleDiscAmt, Float totalAmount, Float netAmount, Integer totalProducts, Integer totalQty,
			Float upiAmount, String upiPhoneNo, String upiTransactionId, Float vatAmt, Integer chequeNumber,
			Double chequeAmount, String chequeDate, Double creditAmount, CustomerInsuranceModel customerInsuranceModel,
			CustomerMembershipModel customerMembershipModel, CustomerModel customerModel, String employeeModel,
			PharmacyModel pharmacyModel, ProviderModel providerModel, HospitalModel hospitalModel, Character activeS) {
		super();
		this.billId = billId;
		this.adjustedQty = adjustedQty;
		this.balanceAmount = balanceAmount;
		this.billCode = billCode;
		this.previousBillCode = previousBillCode;
		this.billDate = billDate;
		this.cashAmount = cashAmount;
		this.creationTs = creationTs;
		this.creationUserId = creationUserId;
		this.creditDays = creditDays;
		this.creditCardAmount = creditCardAmount;
		this.creditCardNo = creditCardNo;
		this.creditCardAuthNo = creditCardAuthNo;
		this.customerNm = customerNm;
		this.customerPhoneNo = customerPhoneNo;
		this.effectiveMargin = effectiveMargin;
		this.effectiveOverallDiscount = effectiveOverallDiscount;
		this.effectiveSalesDisc = effectiveSalesDisc;
		this.effectiveVat = effectiveVat;
		this.insuranceContribAmt = insuranceContribAmt;
		this.insuranceContribPercent = insuranceContribPercent;
		this.lastUpdateTs = lastUpdateTs;
		this.lastUpdateUserId = lastUpdateUserId;
		this.marginAmt = marginAmt;
		this.membershipContribAmt = membershipContribAmt;
		this.membershipContribPercent = membershipContribPercent;
		this.overallDiscount = overallDiscount;
		this.paidAmount = paidAmount;
		this.paymentStatus = paymentStatus;
		this.presciptionDate = presciptionDate;
		this.prescripion = prescripion;
		this.remarks = remarks;
		this.roundedOff = roundedOff;
		this.saleDiscAmt = saleDiscAmt;
		this.totalAmount = totalAmount;
		this.netAmount = netAmount;
		this.totalProducts = totalProducts;
		this.totalQty = totalQty;
		this.upiAmount = upiAmount;
		this.upiPhoneNo = upiPhoneNo;
		this.upiTransactionId = upiTransactionId;
		this.vatAmt = vatAmt;
		this.chequeNumber = chequeNumber;
		this.chequeAmount = chequeAmount;
		this.chequeDate = chequeDate;
		this.creditAmount = creditAmount;
		this.customerInsuranceModel = customerInsuranceModel;
		this.customerMembershipModel = customerMembershipModel;
		this.customerModel = customerModel;
		this.employeeModel = employeeModel;
		this.pharmacyModel = pharmacyModel;
		this.providerModel = providerModel;
		this.hospitalModel = hospitalModel;
		this.activeS = activeS;
	}

	public SalesEmployeeDTO() {
		super();
	}
	

}
