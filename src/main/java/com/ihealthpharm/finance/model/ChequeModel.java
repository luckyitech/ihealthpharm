package com.ihealthpharm.finance.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="cheque")
@Data
@EqualsAndHashCode(of = "chequeId", callSuper = false)
public class ChequeModel extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2780605689575601660L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CHEQUE_ID")
	private Integer chequeId;
	
	@Column(name="NUMBER")
	private String chequeNumber;
	
	@Column(name="CHEQUE_DATE")
	private LocalDate chequeDate;
	
	@Column(name="CHEQUE_RAISED_DATE")
	private LocalDate chequeRaisedDt;
	
	@Column(name="AMOUNT")
	private Float chequeAmt;
	
	@Column(name="ACTIVE_S")
	private Character activeS;
	
	@Column(name="APPROVAL_STATUS")
	private String chequeApprovalStatus;
	
	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyModel;

	@Column(name="TYPE")
	private String payType;
	
	//bi-directional many-to-one association to chequeItem
	@JsonManagedReference
	@OneToMany(mappedBy="cheque", fetch=FetchType.LAZY)
	private List<ChequeItemsModel> chequeItems;
	
	@Column(name="STATUS")
	private String status;
	
	@Transient
	private String requestedName;
	
	@Transient
	private String approverName;
	
}
