package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="indent_status")
public class IndentStatusModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="INDENT_STATUS_ID",length=11)
	private Integer indentStatusId;
	
	@Column(name="STATUS",length=20)
	private String status;
	
}
