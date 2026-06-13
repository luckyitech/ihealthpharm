package com.ihealthpharm.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ihealthpharm.masters.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity (name="cheque_items")
@Data
@EqualsAndHashCode(of = "chequeItemsId", callSuper = false)
public class ChequeItemsModel extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4586625898056709854L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CHEQUE_ITEMS_ID")
	private Integer chequeItemsId;
	
	//bi-directional many-to-one association to cheque
	//@JsonIgnore
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CHEQUE_ID")
	private ChequeModel  cheque;
	
	@OneToOne
	@JoinColumn(name="ACCOUNT_PAYABLES_ID")
	private AccountPayablesModel accountPayablesId;


}
