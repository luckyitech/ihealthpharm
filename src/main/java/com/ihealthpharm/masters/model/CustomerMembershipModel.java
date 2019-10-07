package com.ihealthpharm.masters.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    
    @OneToOne
    @JoinColumn(name="MEMBERSHIP_CARD_ID")
    CustomerMembershipModel customerMembershipModel;

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

    @Column(name="MEMBERSHIP_VALIDITY_FROM",length=25)
    private LocalDate membershipValidityFrom;

    @Column(name="MEMBERSHIP_VALIDITY_TO",length=25)
    private LocalDate membershipValidityTo;
}