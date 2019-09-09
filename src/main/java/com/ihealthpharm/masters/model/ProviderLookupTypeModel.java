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
@Entity(name = "provider_type_lookup")
public class ProviderLookupTypeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROVIDER_TYPE_LOOKUP_ID", length = 11)
	private Integer providerTypeLookupId;

	@Column(name = "PROVIDER_TYPE_CD", length = 2)
	private char[] providerTypeCode;

	@Column(name = "PROVIDER_TYPE_DESC", length = 250)
	private String providerTypeDesc;

	@CreationTimestamp
	@Column(name = "CREATION_TS")
	private Date createdTimeStamp;

	@Column(name = "CREATION_USER_ID", length = 50)
	private String cratedUserId;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATE_TS")
	private Date lastUpdatedTimeStamp;

	@Column(name = "LAST_UPDATE_USER_ID", length = 50)
	private String lastUpdatedUserId;

	@Column(name = "AUDIT_ID", length = 11)
	private Integer auditId;
}
