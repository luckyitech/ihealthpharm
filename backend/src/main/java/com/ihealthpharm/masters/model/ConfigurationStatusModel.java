package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="configuration_status")
@Data
@EqualsAndHashCode(of="CONFIGURATION_STATUS_ID",callSuper=false)
public class ConfigurationStatusModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONFIGURATION_STATUS_ID",length=11)
	private Integer configStatusId;
	
	@Column(name="CONFIGURATION_STATUS_VALUE")
	private String configStatusValue;
	
	@Column(name="PHARMACY_ID",length=11)
	private Integer pharmacyId;
}
