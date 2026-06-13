package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "customer_id_images")
@Data
public class CustomerImagesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUSTOMER_IMAGES_ID")
	private Integer customerImagesId;
	
	@Column(name="CUSTOMER_ID")
	private Integer customerId;
	
	@Column(name="CUSTOMER_IMAGE")
	private byte[] customerImage;
	
	@Column(name="CUSTOMER_TYPE")
	private String fileType;
	
	@Column(name="CUSTOMER_FILE_NM")
	private String fileName;
}
