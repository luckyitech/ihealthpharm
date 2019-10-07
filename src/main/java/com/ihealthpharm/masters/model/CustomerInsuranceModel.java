package com.ihealthpharm.masters.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name="CUSTOMER_INSURANCE")
@Getter
@Setter
@ToString
public class CustomerInsuranceModel {
    
    @OneToOne
    @JoinColumn(name="INSURANCE_POLICY_ID")
    CustomerInsuranceModel customerInsuranceModel;

    @Column(name="INSURANCE_AMOUNT_LIMIT",length=25)
    private float insuranceAmountLimit;

    @Column(name="INSURANCE_DURATION_IN_MONTHS",length=11)
    private int insuranceDurationInMonths;

    @Column(name="INSURANCE_PERCENTAGE_COVERAGE",length=25)
    private float insurancePercentageCoverage;

    @Column(name="INSURANCE_POLICY_END",length=25)
    private LocalDate insurancePolicyEnd;

    @Column(name="INSURANCE_POLICY_START",length=25)
    private LocalDate insurancePolicyStart;
}