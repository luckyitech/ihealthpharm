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
import com.ihealthpharm.masters.helper.UserRolesHelper;
import com.ihealthpharm.masters.model.UserRolesModel;
import com.ihealthpharm.masters.service.UserRolesService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class UserRolesController {

	@Autowired
	UserRolesService userRolesService;
	
	@Autowired
	UserRolesHelper userRolesHelper;
	
	@PostMapping("/save/userroles")
	public ResponseEntity<BaseDto<UserRolesModel>> insertUserData(@Valid @RequestBody UserRolesModel userRolesModel) {
		log.info("Request Object insert is: "+ userRolesModel.toString());
		UserRolesModel userRolesModelRes = userRolesService.saveUserRolesData(userRolesModel);
		return new BaseDto<>(userRolesModelRes,userRolesHelper.getSaveUserRolesMessage(),OK).respond();
	}
	
	@PutMapping("/update/userrole")
	public ResponseEntity<BaseDto<UserRolesModel>> updateUserData(@Valid @RequestBody UserRolesModel usersModel) {
		log.info("Request Object for update is: ", usersModel);
		UserRolesModel usersModelRes = userRolesService.updateUserRolesData(usersModel);
		return new BaseDto<>(usersModelRes,userRolesHelper.getUpdateUserRolesMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/userrole")
	public ResponseEntity<BaseDto<Object>> deleteUserRoleData(@RequestParam int userId) {
		log.info("Request Object for delete is: ", userId);
		userRolesService.deleteUserRolesById(userId);;
		return new BaseDto<>(userRolesHelper.getDeleteUserRolesMessage(), OK).respond();
	}
	
	@GetMapping("/getuserrolesdata")
	public ResponseEntity<BaseDto<List<UserRolesModel>>> getUserData() {
		List<UserRolesModel> result = userRolesService.findAllUserRoles();
		return new BaseDto<>(result, userRolesHelper.getRetrieveUserRolesMessage(), OK).respond();
	}
	
		
	@GetMapping("/getuserrolesdatabyid")
	public ResponseEntity<BaseDto<UserRolesModel>> getUserDataById(@RequestParam int userRoleId) {
		UserRolesModel result = userRolesService.findUserRoleById(userRoleId);
		return new BaseDto<>(result, userRolesHelper.getRetrieveUserRolesMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/userroles")
	public ResponseEntity<BaseDto<Object>> deleteUserRolesData(@RequestParam int[] userRoleIds) {
		
			log.info("Request Object for delete is: "+userRoleIds[0]);
		
	userRolesService.deleteUserRolesById(userRoleIds);
	return new BaseDto<>(userRolesHelper.getDeleteUserRolesMessage(), OK).respond();
	}

	@PutMapping("/update/userroles")
	public ResponseEntity<BaseDto<List<UserRolesModel>>> updateUserRolesData(@Valid @RequestBody List<UserRolesModel> usersModel) {
	log.info("Request Object for update is: "+ usersModel.toString());
	List<UserRolesModel> UserRolesModelRes = userRolesService.updateUserRolesData(usersModel);
	return new BaseDto<>(UserRolesModelRes,userRolesHelper.getUpdateUserRolesMessage(),OK).respond();
	}
}
