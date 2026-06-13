package com.ihealthpharm.checklist.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ihealthpharm.checklist.dao.CheckListRepository;
import com.ihealthpharm.checklist.model.CheckListModel;
import com.ihealthpharm.checklist.service.CheckListService;

@Service
@Transactional
public class CheckListServiceImpl implements CheckListService {

	@Autowired
	CheckListRepository checkListRepo;
	
	/*@Override
	public List<CheckListModel> findAllCheckList(String targetDate){
		LocalDate localDate = null;
        DateTimeFormatter formatter = null;      
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        localDate = LocalDate.parse(targetDate, formatter);
		List<CheckListModel> checkListRes = checkListRepo.findAllCheckListRepo(localDate);
		System.out.println(checkListRes);
		return checkListRes;
		}
*/
	@Override
	public List<CheckListModel> saveChecklist(List<CheckListModel> checkListModel) {
		List<CheckListModel> checkListRes = checkListRepo.saveAll(checkListModel);
		return checkListRes;
	}
		
	@Override
	public Integer countCheckListPending() {
		return checkListRepo.countPendingStatusRepo();
	}

	@Override
	public List<CheckListModel> findAllCheckList() {
		return checkListRepo.findCheckListRepo();
	}

	@Override
	public CheckListModel addCheckList(CheckListModel checkListModel) {
		return checkListRepo.save(checkListModel);
	}
	
	@Override
	public List<CheckListModel> onlyTitleService(){
		return checkListRepo.onlyTitle();
	}
	
	@Override
	public List<CheckListModel> getFilteredCheckList(String title){
		return checkListRepo.findFilteredCheckList(title);
	}
	
	
}
