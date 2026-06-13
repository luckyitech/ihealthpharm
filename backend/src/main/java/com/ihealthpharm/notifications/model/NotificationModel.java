package com.ihealthpharm.notifications.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="notification")
@EqualsAndHashCode(of="notification_id",callSuper=false)
public class NotificationModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NOTIFICATION_ID", length=11, columnDefinition="AUTO_INCREMENT")
	private int notificationid;
	
	@Column(name="EMAIL_ID")
	private String email;
	
	@Column(name="DATE")
	private LocalDate Date;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;

	
}
