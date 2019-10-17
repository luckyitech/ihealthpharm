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

@Entity (name="customer_insurance")
@Getter
@Setter
@ToString
public class CustomerInsuranceModel {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOMER_INSURANCE_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int customerInsuranceId;
	
	 @Column(name="INSURANCE_POLICY_CODE",length=100)
	    private String policyCode;
	    
	
    @Column(name="INSURANCE_AMOUNT_LIMIT",length=25)
    private float iAmountLimit;

    @Column(name="INSURANCE_DURATION_IN_MONTHS",length=11)
    private int iDurationInMonths;

    @Column(name="INSURANCE_PERCENTAGE_COVERAGE",length=25)
    private float PercentageCoverage;
    
    @Column(name="INSURANCE_POLICY_START",length=25)
    private String policyStart;
    

    @Column(name="INSURANCE_POLICY_END",length=25)
    private String policyEnd;

   
    @OneToOne
    @JoinColumn(name="INSURANCE_POLICY_ID")
    private InsuranceModel insuranceModel;
    
    @OneToOne
    @JoinColumn(name="CUSTOMER_ID")
    private CustomerModel customerModel;
    
    public void setPolicyStart(Date startDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String customerStart=simpleDateFormat.format(startDate);  
		this.policyStart = customerStart;
    }

  public void setPolicyEnd(Date endDate)  {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String customerEnd=simpleDateFormat.format(endDate);  
		this.policyEnd = customerEnd;
    }
}