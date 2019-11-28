package com.ihealthpharm.masters.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="customer_insurance")
@Data
@EqualsAndHashCode(of="customerInsuranceId",callSuper=false)
public class CustomerInsuranceModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803379230298216607L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUSTOMER_INSURANCE_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer customerInsuranceId;

	@Column(name="INSURANCE_POLICY_CODE",length=100)
	private String policyCode;

	@Column(name="CUSTOMER_POLICY_NO",length=100)
	private String customerPolicyNumber;

	@Column(name="CUSTOMER_NAME",length=25)
	private String customerName;

	@Column(name="INSURANCE_AMOUNT_LIMIT",length=25)
	private Float policyAmountLimit;

	@Column(name="INSURANCE_DURATION_IN_MONTHS",length=11)
	private Integer policyDurationInMonths;

	@Column(name="INSURANCE_CONTRIBUTION_PERCENTAGE",length=25)
	private Float contributionPercentage;

	@Column(name="INSURANCE_POLICY_START",length=25)
	private String policyStartDate;

	@Column(name="INSURANCE_POLICY_END",length=25)
	private String policyEndDate;

	@OneToOne
	@JoinColumn(name="INSURANCE_POLICY_ID")
	private InsuranceModel insuranceModel;

	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerModel customerModel;

	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;

	public void setPolicyStart(Date startDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String customerStart=simpleDateFormat.format(startDate);  
		this.policyStartDate = customerStart;
	}

	public void setPolicyEnd(Date endDate)  {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String customerEnd=simpleDateFormat.format(endDate);  
		this.policyEndDate = customerEnd;
	}
}