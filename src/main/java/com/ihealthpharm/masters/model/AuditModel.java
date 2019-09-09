package com.ihealthpharm.masters.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Vikas
 *	AuditModel - Is used to add all the Audit columns in the entities automatically.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"creationTimestamp", "lastUpdateTimestamp"}
)
@Getter
@Setter
public class AuditModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreationTimestamp
	@Column(name = "CREATION_TS", updatable = false)
	private Date creationTimeStamp;
	
	
	@Column(name = "CREATION_USER_ID")
	private String createdUser;
	
	@Column(name = "LAST_UPDATE_TS")
	@UpdateTimestamp
	private Date lastUpdateTimestamp = new Date();
	
	@Column(name = "LAST_UPDATE_USER_ID")
	private Integer lastUpdateUser;

    
}
