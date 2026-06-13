package com.ihealthpharm.checklist.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.checklist.helper.CheckListHelper;
import com.ihealthpharm.checklist.model.CheckListModel;
import com.ihealthpharm.checklist.service.CheckListService;
import com.ihealthpharm.commons.BaseDto;
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
	
	@GetMapping("/getTitle")
	public ResponseEntity<BaseDto<List<CheckListModel>>> getOnlyTitles(){
		List<CheckListModel> res = checkListService.onlyTitleService();
		return new BaseDto<>(res, checkListHelper.getRetrieveCheckListMessage(),OK).respond();
	}
	
	@GetMapping("/getFilteredCheckList")
	public ResponseEntity<BaseDto<List<CheckListModel>>> getFilteredCheck(@RequestParam String title){
		List<CheckListModel> checkListRes = checkListService.getFilteredCheckList(title);
		return new BaseDto<>(checkListRes, checkListHelper.getRetrieveCheckListMessage(),OK).respond();
	}
	
}
