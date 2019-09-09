package com.ihealthpharm.masters.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vikas
 *	DrugDistributors Persists the Drug and Distributors details 
 *	Setter, getters by default added no need to add manually
 * Audit Columns will be added from AuditModel
 */
@Getter
@Setter
@Entity(name = "items_distributor")
@EqualsAndHashCode(of = "itemDistributorId", callSuper = false)
public class ItemDistributorModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_DISTRIBUTOR_ID",length=11)
	private Integer itemDistributorId;
	
	// Drug Entity mapped 
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="ITEM_ID")
	@JsonBackReference
	private ItemsModel itemId;
	
	// DistrubutorModel Entity mapped 
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="DISTRIBUTOR_ID")
	private DistributorModel distributor;
	
	@Column(name="ACTIVE_S",length=1)
	private String activeS;
	
	
	
}
