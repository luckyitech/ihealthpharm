package com.ihealthpharm.checklist.model;

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
		
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DONE_BY")
	private String doneBy;
	
	@Column(name="TARGET_DATE")
	private LocalDate targetDate;
	
	@Column(name="TARGET_DATE_TS")
	private String targetTime;
	
	@Column(name="CHECKLIST_NAME")
	private String checkListName;
	
	@Column(name="DONE_DATE_TS")
	private Date doneDateTS;
	
	@Column(name="ASSIGNED_DATE")
	private LocalDate assignedDate;

	public CheckListModel(String title, LocalDate assignedDate) {
		super();
		this.title = title;
		this.assignedDate = assignedDate;
	}
	
	public CheckListModel() {
		super();
	
	}
}
