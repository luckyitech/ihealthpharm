package com.ihealthpharm.notifications.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class NotificationHelper {
	@Value("${notification.save.response}")
	public String saveNotificationMessage;
	
	@Value("${notification.update.response}")
	public String updateNotificationMessage;
	
	@Value("${notification.delete.response}")
	public String deleteNotificationMessage;
	
	@Value("${notification.retrieve.response}")
	public String retrieveNotificationMessage;
	
	@Value("${notification.not.found.response}")
	public String notFoundNotificationMessage;

}
