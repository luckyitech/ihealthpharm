package com.ihealthpharm.notifications.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.notfications.helper.CheckListHelper;
import com.ihealthpharm.notifications.model.CheckListModel;
import com.ihealthpharm.notifications.service.CheckListService;
@RestController
@CrossOrigin
public class CheckListController {

	@Autowired
	private CheckListService checkListService;
	
	@Autowired
	private CheckListHelper checkListHelper;
	
	/*@GetMapping("/getAllCheckList")
	public ResponseEntity<BaseDto<List<CheckListModel>>> getCheckList(@RequestParam String targetDate ){
		List<CheckListModel> checkListModelRes = checkListService.findAllCheckList(targetDate);
		return new BaseDto<>(checkListModelRes, checkListHelper.getRetrieveCheckListMessage(), OK).respond();
	}*/
	
	@GetMapping("/getAllCheckList")
	public ResponseEntity<BaseDto<List<CheckListModel>>> getCheckList(){
		List<CheckListModel> checkListRes = checkListService.findAllCheckList();
		return new BaseDto<>(checkListRes, checkListHelper.getRetrieveCheckListMessage(),OK).respond();
	}
	
	@PostMapping("/save/checklist")
	public ResponseEntity<BaseDto<List<CheckListModel>>> saveChecklist(@RequestBody List<CheckListModel> checkListModel){
		List<CheckListModel> checkListModelRes=checkListService.saveChecklist(checkListModel);
		return new BaseDto<>(checkListModelRes,checkListHelper.getUpdateCheckListMessage(),OK).respond();
		
	}
	
	@PostMapping("/add/checklist")
	public ResponseEntity<BaseDto<CheckListModel>> addCheckList(@RequestBody CheckListModel checkListModel){
		System.out.println("add: " +checkListModel);
		CheckListModel checkListRes=checkListService.addCheckList(checkListModel);
		return new BaseDto<>(checkListRes,checkListHelper.getSaveCheckListMessage(),OK).respond();
	}
	
	@GetMapping("/getCountPendings")
	public ResponseEntity<BaseDto<Integer>> getCheckListPendingCount(){
		Integer checkListModelRes = checkListService.countCheckListPending();
		return new BaseDto<>(checkListModelRes, checkListHelper.getRetrieveCheckListMessage(), OK).respond();
	}
	
}
