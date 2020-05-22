package com.ihealthpharm.userhistory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihealthpharm.userhistory.dao.UserHistoryRepository;
import com.ihealthpharm.userhistory.model.UserHistoryModel;
import com.ihealthpharm.userhistory.service.UserHistoryService;

@Service
public class UserHistoryServiceImpl implements UserHistoryService {

	@Autowired
	UserHistoryRepository userHisRepo;
	
	@Override
	public UserHistoryModel saveUserHistoryDetails(UserHistoryModel userHisModel) {
		return userHisRepo.save(userHisModel);
	}

	@Override
	public List<UserHistoryModel> findUserHistoryByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserHistoryModel> findUserHistoryByModule(Integer userId, String module) {
		// TODO Auto-generated method stub
		return null;
	}

}
