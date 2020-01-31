package com.ihealthpharm.notifications.service;

import java.util.List;

import com.ihealthpharm.notifications.model.CheckListModel;

public interface CheckListService {

	//List<CheckListModel> findAllCheckList(String targetDate);
	
	List<CheckListModel> findAllCheckList();

	List<CheckListModel> saveChecklist(List<CheckListModel> checkListModel);
	
	Integer countCheckListPending();

	CheckListModel addCheckList(CheckListModel checkListModel);
}
