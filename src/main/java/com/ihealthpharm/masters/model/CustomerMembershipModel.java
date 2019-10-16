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

@Entity (name="CUSTOMER_MEMBERSHIP")
@Getter
@Setter
@ToString
public class CustomerMembershipModel {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOMER_MEMBERSHIP_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int customerMembershipId;

    @Column(name="MEMBERSHIP_CREDIT_DAYS",length=11)
    private int mCreditDays;

    @Column(name="MEMBERSHIP_CREDIT_LIMIT",length=25)
    private float mCreditLimit;

    @Column(name="MEMBERSHIP_DISCOUNT_AMOUNT",length=25)
    private float mDiscountAmount;

    @Column(name="MEMBERSHIP_DISCOUNT_PERCENTAGE",length=25)
    private float mDiscountPercentage;

    @Column(name="MEMBERSHIP_DURATION_IN_MONTHS",length=11)
    private int mDurationInMonths;

    @Column(name="MEMBERSHIP_START_DATE",length=25)
    private String startDate;

    @Column(name="MEMBERSHIP_END_DATE",length=25)
    private String endDate;
    
    @OneToOne
    @JoinColumn(name="MEMBERSHIP_CARD_ID")
  private MembershipModel membershipModel;
    
    @OneToOne
    @JoinColumn(name="CUSTOMER_ID")
   private CustomerModel customerModel;
    
    public void setMembershipStartDate(Date startDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String membershipStart=simpleDateFormat.format(startDate);  
		this.startDate = membershipStart;
    }

  public void setMembershipEndDate(Date membershipEnd)  {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate=simpleDateFormat.format(membershipEnd);  
		this.endDate = endDate;
    }
}