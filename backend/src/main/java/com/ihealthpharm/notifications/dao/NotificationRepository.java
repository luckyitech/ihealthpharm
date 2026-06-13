package com.ihealthpharm.notifications.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ihealthpharm.notifications.model.NotificationModel;


@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, Integer>{
	

}
