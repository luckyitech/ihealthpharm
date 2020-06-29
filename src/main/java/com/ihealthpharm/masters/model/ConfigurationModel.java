package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="configuration")
@Data
@EqualsAndHashCode(of="CONFIG_ID",callSuper=false)
public class ConfigurationModel extends AuditModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONFIG_ID",length=11)
	private Integer configId;
	
	@Column(name="CONFIG_VALUE",length=11)
	private Integer configValue;
	
	@Column(name="ACTIVE_S")
	private Character activeS;
	
	@Column(name="CONFIG_DESC")
	private String configDesc;
	
	@Column(name="PHARMACY_ID",length=11)
	private Integer pharmacyId;
	
	
	
	
}
