package com.ihealthpharm.userhistory.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.userhistory.model.UserHistoryModel;

public interface UserHistoryRepository extends JpaRepository<UserHistoryModel, Integer>{
	
}
