package com.ihealthpharm.sales.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.ihealthpharm.masters.model.AuditModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="item_instructions_working")
@EqualsAndHashCode(of="itemInstructionId",callSuper=false)
public class ItemInstructionsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_INSTRUCTION_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer itemInstructionId;

	@Column(name="ITEM_ID")
	private Integer itemId;

	@Column(name="FORMULATION_ID")
	private Integer formulationInstructionId;

	@Column(name="ITEM_NM")
	private String itemName;

	@Column(name="INSTRUCTIONS")
	private String instructions;

	@Column(name="ITEM_QTY")
	private Integer itemQty;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDate;

	@Column(name = "CUSTOMER_NM")
	private String customerName;

	@CreationTimestamp
	@Column(name = "CREATION_TS")
	private LocalDateTime creationTs;
	
	@Column(name = "CREATION_USER_ID")
	private Integer createdUser;

}
