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



@Entity (name="membership")
@Data
@EqualsAndHashCode(of="membershipCardId",callSuper=false)
public class MembershipModel extends AuditModel{



	/**
	 * 
	 */
	private static final long serialVersionUID = -8402717136800761813L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MEMBERSHIP_CARD_ID",length=11)
	private Integer membershipCardId;

	@Column(name="MEMBERSHIP_CARD_NAME",length=100)
	private String membershipCardName;

	@Column(name="START_DATE")
	private String membershipStartDate;

	@Column(name="END_DATE")
	private String membershipEndDate;

	@Column(name="DURATION_IN_MONTHS",length=11)
	private Integer membershipDurationInMonths;

	@Column(name="CREDIT_AMOUNT")
	private Float membershipCreditAmount;

	@Column(name="CREDIT_DAYS")
	private Float membershipCreditDays;

	@Column(name="BONUS_PERCENTAGE")
	private Float membershipBonusPercentage;

	@Column(name="DISCOUNT_PERCENTAGE")
	private Float membershipDiscountPercentage;

	@Column(name="OFFERS",length=100)
	private String offers;

	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;

	@Column(name = "ACTIVE_S",length=1)
	private Character activeS;

	@OneToOne
	@JoinColumn(name="Pharmacy_ID")
	private PharmacyModel pharmacyModel;


	public void setMembershipStartDate(Date startDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String membershipStart=simpleDateFormat.format(startDate);  
		this.membershipStartDate = membershipStart;
	}

	public void setMembershipEndDate(Date membershipEnd)  {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate=simpleDateFormat.format(membershipEnd);  
		this.membershipEndDate = endDate;
	}


}
