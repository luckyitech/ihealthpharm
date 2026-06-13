package com.ihealthpharm.userhistory.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.userhistory.helper.UserHistoryHelper;
import com.ihealthpharm.userhistory.model.UserHistoryModel;
import com.ihealthpharm.userhistory.service.UserHistoryService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class UserHistoryController {

	@Autowired
	UserHistoryService userHisService;
	
	@Autowired
	UserHistoryHelper userHisHelper;
	@PostMapping("/user/saveuserhistory")
	public ResponseEntity<BaseDto<UserHistoryModel>> saveUserHistory(@RequestBody UserHistoryModel userHisModel) throws IOException {

		UserHistoryModel userHisRes = userHisService.saveUserHistoryDetails(userHisModel);
		return new BaseDto<>(userHisRes,userHisHelper.getSaveUserHistoryMessage(),OK).respond();
	}
}
