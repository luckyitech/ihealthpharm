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

@Entity (name="customer_membership")
@Data
@EqualsAndHashCode(of="customerMembershipId",callSuper=false)
public class CustomerMembershipModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5097225016751841553L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUSTOMER_MEMBERSHIP_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer customerMembershipId;

	@Column(name="MEMBERSHIP_CREDIT_DAYS",length=11)
	private Integer membershipCreditDays;

	@Column(name="MEMBERSHIP_CARD_NO",length=100)
	private String membershipCardNumber;

	@Column(name="CUSTOMER_NAME",length=25)
	private String customerName;

	@Column(name="MEMBERSHIP_BONUS_PERCENTAGE",length=25)
	private Float membershipBonusPercentage;

	@Column(name="MEMBERSHIP_CREDIT_AMOUNT",length=25)
	private Float membershipCreditAmount;

	@Column(name="MEMBERSHIP_DISCOUNT_PERCENTAGE",length=25)
	private Float membershipDiscountPercentage;

	@Column(name="MEMBERSHIP_DURATION_IN_MONTHS",length=11)
	private Integer membershipDurationInMonths;

	@Column(name="MEMBERSHIP_CARD_NAME",length=100)
	private String membershipCardName;

	@Column(name="MEMBERSHIP_START_DATE",length=25)
	private String membershipStartDate;

	@Column(name="MEMBERSHIP_END_DATE",length=25)
	private String membershipEndDate;

	@OneToOne
	@JoinColumn(name="MEMBERSHIP_CARD_ID")
	private MembershipModel membershipModel;
	
	@Column(name="ACTIVE_S")
	private char activeS;

	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerModel customerModel;

	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;

	public void setMStartDate(Date startDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String membershipStart=simpleDateFormat.format(startDate);  
		this.membershipStartDate = membershipStart;
	}

	public void setMEndDate(Date membershipEnd)  {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate=simpleDateFormat.format(membershipEnd);  
		this.membershipEndDate = endDate;
	}

}