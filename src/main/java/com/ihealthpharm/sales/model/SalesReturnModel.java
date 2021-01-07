package com.ihealthpharm.sales.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.stock.model.PaymentTypeModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity(name="sales_return")
@EqualsAndHashCode(of = "salesReturnId", callSuper = false)
public class SalesReturnModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8603073234278002705L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SALES_RETURN_ID",length=11)
	private Integer salesReturnId;

	@Column(name="SALES_RETURN_NO",length=20)
	private String salesReturnNumber;

	@Column(name="SALES_RETURN_DATE")
	private LocalDate salesReturnDate;

	@Column(name="STATUS",length=20)
	private String status;
	
	@OneToOne
	@JoinColumn(name="BILL_ID")
	private SalesModel billNumber;
	
	@Column(name="TOTAL_AMOUNT")
	private Float totalAmount;

	@Column(name = "ACTIVE_S",length=1)
	private Character activeS;

	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	@OneToOne
	@JoinColumn(name="PAYMENT_TYPE_ID")
	private PaymentTypeModel paymentType;
	
	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacy;
	
	 @Transient
	    private String empName;
	    
	
}
