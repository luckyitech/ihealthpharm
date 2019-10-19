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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="customer_membership")
@Getter
@Setter
@ToString
public class CustomerMembershipModel {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOMER_MEMBERSHIP_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int customerMembershipId;

    @Column(name="MEMBERSHIP_CREDIT_DAYS",length=11)
    private int membershipCreditDays;

    @Column(name="MEMBERSHIP_CREDIT_LIMIT",length=25)
    private float membershipCreditLimit;

    @Column(name="MEMBERSHIP_DISCOUNT_AMOUNT",length=25)
    private float membershipDiscountAmount;

    @Column(name="MEMBERSHIP_DISCOUNT_PERCENTAGE",length=25)
    private float membershipDiscountPercentage;

    @Column(name="MEMBERSHIP_DURATION_IN_MONTHS",length=11)
    private int membershipDurationInMonths;
    
    @Column(name="MEMBERSHIP_CARD_NAME",length=100)
    private String membershipCardName;

    @Column(name="MEMBERSHIP_START_DATE",length=25)
    private String membershipStartDate;

    @Column(name="MEMBERSHIP_END_DATE",length=25)
    private String membershipEndDate;
    
    @OneToOne
    @JoinColumn(name="MEMBERSHIP_CARD_ID")
  private MembershipModel membershipModel;
    
    @OneToOne
    @JoinColumn(name="CUSTOMER_ID")
   private CustomerModel customerModel;

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