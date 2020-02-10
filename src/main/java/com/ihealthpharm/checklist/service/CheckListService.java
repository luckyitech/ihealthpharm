package com.ihealthpharm.checklist.service;

import java.util.List;

import com.ihealthpharm.checklist.model.CheckListModel;

public interface CheckListService {

	//List<CheckListModel> findAllCheckList(String targetDate);
	
	List<CheckListModel> findAllCheckList();

	List<CheckListModel> saveChecklist(List<CheckListModel> checkListModel);
	
	Integer countCheckListPending();

	CheckListModel addCheckList(CheckListModel checkListModel);
	
	List<CheckListModel> onlyTitleService();
	
	List<CheckListModel> getFilteredCheckList(String title);
}
