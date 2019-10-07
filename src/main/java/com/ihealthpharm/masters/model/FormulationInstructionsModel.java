package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
    @Column(name="ITEMS_ID",length=1)
	private int itemId;
	
	@Column(name="TIMES_IN_A_DAY",length=11)
	private int timesInADay;
	
	@Column(name="MORNING",length=1)
	private char morning;
	
	@Column(name="AFTER_NOON",length=1)
	private char afterNoon;
	
	@Column(name="EVENING",length=1)
	private char evening;
	
	@Column(name="TIMINGS",length=11)
	private int timings;
	    
	@Column(name="BEFORE_MEAL",length=1)
	private char beforeMeal;
	    
    @Column(name="AFTER_MEAL",length=1)
	private char afterMeal;
    
    @Column(name="ACTIVE_S",length=1, columnDefinition = "'Y'")
    private char activeS;
    
    @Column(name="SYRUP_DOSAGE",length=50)
    private String syrupDosage;

    @Column(name="AUDIT_ID",length=11)
    private int auditId;

   

}
