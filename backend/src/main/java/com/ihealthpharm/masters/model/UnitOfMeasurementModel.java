package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Tarun
 * Setter, getters by default added no need to add manually
 */

@Entity(name="unit_of_measurement")
@Data
@EqualsAndHashCode(of = "unitMeasurementId", callSuper = false)
public class UnitOfMeasurementModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2974799108922300466L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UNIT_OF_MEASUREMENT_ID",length=11)
	private Integer unitMeasurementId;
	
	@Column(name="MEASUREMENT_CD")
	private String measurementCode;
	
	@Column(name="MEASUREMENT_NAME")
	private String measurementName;
	
	@Column(name="MEASUREMENT_DESC")
	private String measurementDesc;
	
	@Column(name = "ACTIVE_S")
	private String activeS;
	
}
