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

@Entity (name="CUSTOMER_INSURANCE")
@Getter
@Setter
@ToString
public class CustomerInsuranceModel {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="INSURANCE_POLICY_PERSONAL_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int insurancePolicyPersonalId;
	
	 @Column(name="INSURANCE_POLICY_CODE",length=100)
	    private String insurancePolicyCode;
	    
	
    @Column(name="INSURANCE_AMOUNT_LIMIT",length=25)
    private float insuranceAmountLimit;

    @Column(name="INSURANCE_DURATION_IN_MONTHS",length=11)
    private int insuranceDurationInMonths;

    @Column(name="INSURANCE_PERCENTAGE_COVERAGE",length=25)
    private float insurancePercentageCoverage;
    
    @Column(name="INSURANCE_POLICY_START",length=25)
    private String insurancePolicyStart;
    

    @Column(name="INSURANCE_POLICY_END",length=25)
    private String insurancePolicyEnd;

   
    @OneToOne
    @JoinColumn(name="INSURANCE_POLICY_ID")
    private InsuranceModel insuranceModel;
    
    @OneToOne
    @JoinColumn(name="CUSTOMER_ID")
    private CustomerModel customerModel;
    
    public void setInsuranceStartDate(Date startDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String insuranceStart=simpleDateFormat.format(startDate);  
		this.insurancePolicyStart = insuranceStart;
    }

  public void setInsuranceEndDate(Date insuranceEnd)  {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate=simpleDateFormat.format(insuranceEnd);  
		this.insurancePolicyEnd = endDate;
    }
}