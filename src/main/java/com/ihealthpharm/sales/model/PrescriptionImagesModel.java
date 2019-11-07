package com.ihealthpharm.sales.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.CustomerModel;

import lombok.Data;

@Entity(name="prescription_images")
@Data

public class PrescriptionImagesModel extends AuditModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRESCRIPTION_ID", length = 11, columnDefinition = "AUTO_INCREMENT")
	private Integer prescriptionId;
	
	@Column(name="PRESCRIPTION_IMAGE")
	private byte[] prescriptionImage;
	
	@Column(name="PRESCRIPTION_DATE")
	private String prescriptionDate;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerModel customer;
	
	@OneToOne
	@JoinColumn(name="SALES_BILL_ID")
	private SalesModel sales;
	/*public void setPrescriptionDate(Date prescriptionDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateBirth=simpleDateFormat.format(prescriptionDate); 
		this.prescriptionDate = dateBirth;
	}*/
	
}
