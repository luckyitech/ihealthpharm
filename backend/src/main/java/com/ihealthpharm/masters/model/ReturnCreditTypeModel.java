package com.ihealthpharm.masters.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@Data
@Entity(name="return_credit_type")
public class ReturnCreditTypeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="RETURN_CREDIT_TYPE_ID", length=11)
	private Integer returnCreditTypeId;
	
	@Column(name ="TYPE", length=20)
	private String type;
	
	@Column(name ="AUDIT_ID", length=11)
	private Integer auditId;
	
	@Column( name = "CREATION_TS")
	@CreationTimestamp
	private Date creationTimeStamp;
	
	@Column(name = "CREATION_USER_ID" , length=50)
	private String creationUserId;
	
	@Column(name = "LAST_UPDATE_TS")
	@UpdateTimestamp
	private Date latestUpdatedTimeStamp;
	
	@Column(name = "LAST_UPDATE_USER_ID", length=50)
	private String latestUpdatedUserId;
}
