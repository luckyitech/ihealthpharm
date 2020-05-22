package com.ihealthpharm.finance.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="modes")
@EqualsAndHashCode(of="modeId",callSuper=false)
public class ModeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MODE_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer modeId;
	
	@Column(name="MODE_NAME")
	private String name;
}
