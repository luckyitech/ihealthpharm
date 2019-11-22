package com.ihealthpharm.masters.model;

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

@Entity (name="FORMULATION_INSTRUCTIONS")
@Getter
@Setter
@ToString
public class FormulationInstructionsModel extends AuditModel {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2605429224143978802L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FORMULATION_INSTRUCTION_ID", length=11)
	private Integer formulationInstructionId;

	@Column(name="FORMULATION_NAME",length=100)
    private String formulationName;

	@Column(name="INSTRUCTIONS",length=255)
    private String instructions;

	@OneToOne
	@JoinColumn(name="ITEM_ID")
	private ItemsModel itemId;
	
	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacyId;
	
	@Column(name="TIMES_IN_A_DAY",length=11)
	private Integer timesInADay;
	
	@Column(name="MORNING",length=1)
	private Character morning;
	
	@Column(name="AFTER_NOON",length=1)
	private Character afterNoon;
	
	@Column(name="EVENING",length=1)
	private Character evening;
	
	@Column(name="BEFORE_BED",length=1)
	private Character beforeBed;
	
	@Column(name="TIMINGS",length=11)
	private Integer timings;
	    
	@Column(name="BEFORE_MEAL",length=1)
	private Character beforeMeal;
	    
    @Column(name="AFTER_MEAL",length=1)
	private Character afterMeal;
    
    @Column(name="ANY_TIME",length=1)
    private Character anyTime;
    
    @Column(name="ACTIVE_S",length=1, columnDefinition = "'Y'")
    private Character activeS;

    @Column(name="AUDIT_ID",length=11)
    private Integer auditId;

}
