package com.ihealthpharm.notifications.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="checklist")
@EqualsAndHashCode(of="checkListId",callSuper=false)
public class CheckListModel {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CHECKLIST_ID",length=11, columnDefinition = "AUTO_INCREMENT")
	private Integer checkListId;

	@Column(name="SNO")
	private int sno;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="ASSIGNED_BY")
	private String assignedBy;
	
	@Column(name="ASSIGNED_TO")
	private String assignedTo;
	
	@Column(name="TARGET_DATE")
	private LocalDate targetDate;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DONE_BY")
	private String doneBy;
	
	@Column(name="DATE")
	private Date date;
	
}
