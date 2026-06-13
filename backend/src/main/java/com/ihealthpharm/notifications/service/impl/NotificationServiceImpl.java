package com.ihealthpharm.notifications.service.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ihealthpharm.notifications.dao.NotificationRepository;
import com.ihealthpharm.notifications.model.NotificationModel;
import com.ihealthpharm.notifications.service.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	NotificationRepository notificationRepo;

	@Override
	public NotificationModel saveNotification(NotificationModel notificationModel) {
		return notificationRepo.save(notificationModel);
	}
	

}
