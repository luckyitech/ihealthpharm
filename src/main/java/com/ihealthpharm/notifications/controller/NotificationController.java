package com.ihealthpharm.notifications.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.notifications.helper.NotificationHelper;
import com.ihealthpharm.notifications.model.NotificationModel;
import com.ihealthpharm.notifications.service.NotificationService;

@RestController
@CrossOrigin
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private NotificationHelper notificationHelper;
	
	@PostMapping("/save/notification")
	public ResponseEntity<BaseDto<NotificationModel>> saveNotification(@RequestBody NotificationModel notificationModel){
		NotificationModel notifRes = notificationService.saveNotification(notificationModel);
		return new BaseDto<>(notifRes, notificationHelper.getSaveNotificationMessage(),OK).respond();
	}
	

}
