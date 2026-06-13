package com.ihealthpharm.userhistory.service;

import java.util.List;

import com.ihealthpharm.userhistory.model.UserHistoryModel;

public interface UserHistoryService {
	UserHistoryModel saveUserHistoryDetails(UserHistoryModel userHisModel);

	List<UserHistoryModel> findUserHistoryByUserId(Integer userId);

	List<UserHistoryModel> findUserHistoryByModule(Integer userId,String module);
}
