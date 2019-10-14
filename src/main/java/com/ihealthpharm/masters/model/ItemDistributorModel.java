package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity(name = "items_distributor")
@EqualsAndHashCode(of = "itemDistributorId", callSuper = false)
public class ItemDistributorModel extends AuditModel{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_DISTRIBUTOR_ID",length=11)
	private Integer itemDistributorId;
	
	// Drug Entity mapped 
	@Column(name="ITEM_ID")
	private Integer itemsId;
	
	// DistrubutorModel Entity mapped 
    @Column(name="DISTRIBUTOR_ID")
	private Integer distributorsId;
	
	@Column(name="ACTIVE_S",length=1)
	private String activeS;
	
	@Column(name="DISTRIBUTOR_PRIORITY",length=11)
	private int distributorPriority;
	
/*	@Column(name="UNIT_RATE")
	private Double unitRate;
	
	@Column(name="DISCOUNT_PERCENTAGE")
	private Double discountPercentage;
*/
}
