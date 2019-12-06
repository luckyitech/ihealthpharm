package com.ihealthpharm.tax.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ihealthpharm.masters.model.CustomerInsuranceModel;
import com.ihealthpharm.masters.model.CustomerMembershipModel;
import com.ihealthpharm.masters.model.CustomerModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.HospitalModel;
import com.ihealthpharm.masters.model.PharmacyModel;
import com.ihealthpharm.masters.model.ProviderModel;
import com.ihealthpharm.sales.model.SalesModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "taxcategory")
@EqualsAndHashCode(of="taxCategoryId",callSuper=false)
public class TaxCategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAXCATEGORY_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer taxCategoryId;
	
	@Column(name = "CATEGORY_CODE", length = 20)
	private String categoryCode;
	
	@Column(name = "CATEGORY_VALUE", length = 11)
	private Integer categoryValue;
}
