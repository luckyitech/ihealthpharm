package com.ihealthpharm.tax.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "taxcategory")

public class TaxCategoryModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAXCATEGORY_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer taxCategoryId;
	
	@Column(name = "CATEGORY_CODE", length = 20)
	private String categoryCode;
	
	@Column(name = "CATEGORY_VALUE", length = 11)
	private Integer categoryValue;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
}
