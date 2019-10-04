package com.ihealthpharm.masters.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihealthpharm.commons.BaseDto;
import com.ihealthpharm.masters.helper.IndentHelper;
import com.ihealthpharm.masters.model.IndentModel;
import com.ihealthpharm.masters.service.IndentService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class IndentController {

	@Autowired
	IndentService indentService;

	@Autowired
	IndentHelper indentHelper;

	@PostMapping("/save/indent")
	public ResponseEntity<BaseDto<IndentModel>> insertUserData(@Valid @RequestBody IndentModel indentModel) {
		log.info("Request Object insert is: " + indentModel.toString());
		IndentModel indentModelRes = indentService.saveIndentData(indentModel);
		return new BaseDto<>(indentModelRes, indentHelper.getSaveIndentMessage(), OK).respond();
	}

	@PutMapping("/update/indent")
	public ResponseEntity<BaseDto<IndentModel>> updateUserData(@Valid @RequestBody IndentModel usersModel) {
		log.info("Request Object for update is: ", usersModel);
		IndentModel usersModelRes = indentService.updateIndentData(usersModel);
		return new BaseDto<>(usersModelRes, indentHelper.getUpdateIndentMessage(), OK).respond();
	}

	@DeleteMapping("/delete/indent")
	public ResponseEntity<BaseDto<Object>> deleteUserData(@RequestParam int indentId) {
		log.info("Request Object for delete is: ", indentId);
		indentService.deleteIndentById(indentId);
		return new BaseDto<>(indentHelper.getDeleteIndentMessage(), OK).respond();
	}

	@GetMapping("/getindentdata")
	public ResponseEntity<BaseDto<List<IndentModel>>> getUserData() {
		List<IndentModel> result = indentService.findAllIndents();
		return new BaseDto<>(result, indentHelper.getRetrieveIndentMessage(), OK).respond();
	}

	@GetMapping("/getindentdatabyid")
	public ResponseEntity<BaseDto<IndentModel>> getUserDataById(@RequestParam int indentId) {
		IndentModel result = indentService.findIndentById(indentId);
		return new BaseDto<>(result, indentHelper.getRetrieveIndentMessage(), OK).respond();
	}

	@DeleteMapping("/delete/indents")
	public ResponseEntity<BaseDto<Object>> deleteUsersData(@RequestParam int[] usersIds) {

		log.info("Request Object for delete is: " + usersIds[0]);

		indentService.deleteIndentsById(usersIds);
		return new BaseDto<>(indentHelper.getDeleteIndentMessage(), OK).respond();
	}

	@PutMapping("/update/indents")
	public ResponseEntity<BaseDto<List<IndentModel>>> updateUsersData(@Valid @RequestBody List<IndentModel> usersModel) {
		log.info("Request Object for update is: " + usersModel.toString());
		List<IndentModel> IndentModelRes = indentService.updateIndentsData(usersModel);
		return new BaseDto<>(IndentModelRes, indentHelper.getUpdateIndentMessage(), OK).respond();
	}
}
