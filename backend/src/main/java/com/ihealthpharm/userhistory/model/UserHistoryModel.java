package com.ihealthpharm.userhistory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.ihealthpharm.masters.model.EmployeeModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity (name="user_history")
@EqualsAndHashCode(of = "userHistoryId", callSuper = false)
public class UserHistoryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_HISTORY_ID" ,length=11)
	private Integer userHistoryId;
	
	@OneToOne
    @JoinColumn(name="USER_ID")
    EmployeeModel userModel;
	

    @Column( name = "MODULE")
	private String module;
	
	@Column( name = "SCREEN_NAME", length=40)
	private String screenName;
	 
	@Column( name = "ACTION", length=100)
	private String action;
	
	@CreationTimestamp
	@Column(name = "CREATION_TS")
	private Date creationTimeStamp;
	
}
