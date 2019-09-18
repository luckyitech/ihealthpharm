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
import com.ihealthpharm.masters.helper.UsersHelper;
import com.ihealthpharm.masters.model.UsersModel;
import com.ihealthpharm.masters.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class UsersController {

	@Autowired
	UserService userService;
	
	@Autowired
	UsersHelper usersHelper;
	
	@PostMapping("/save/user")
	public ResponseEntity<BaseDto<UsersModel>> insertUserData(@Valid @RequestBody UsersModel userModel) {
		log.info("Request Object insert is: "+ userModel.toString());
		UsersModel userModelRes = userService.saveUsersData(userModel);
		return new BaseDto<>(userModelRes,usersHelper.getSaveUsersMessage(),OK).respond();
	}
	
	@PutMapping("/update/user")
	public ResponseEntity<BaseDto<UsersModel>> updateUserData(@Valid @RequestBody UsersModel usersModel) {
		log.info("Request Object for update is: ", usersModel);
		UsersModel usersModelRes = userService.updateUsersData(usersModel);
		return new BaseDto<>(usersModelRes,usersHelper.getUpdateUsersMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/user")
	public ResponseEntity<BaseDto<Object>> deleteUserData(@RequestParam Long userId) {
		log.info("Request Object for delete is: ", userId);
		userService.deleteUsersById(userId);;
		return new BaseDto<>(usersHelper.getDeleteUsersMessage(), OK).respond();
	}
	
	@GetMapping("/getusersdata")
	public ResponseEntity<BaseDto<List<UsersModel>>> getUserData() {
		List<UsersModel> result = userService.findAllUsers();
		return new BaseDto<>(result, usersHelper.getRetrieveUsersMessage(), OK).respond();
	}
	
		
	@GetMapping("/getuserdatabyid")
	public ResponseEntity<BaseDto<UsersModel>> getUserDataById(@RequestParam Long userId) {
		UsersModel result = userService.findUserById(userId);
		return new BaseDto<>(result, usersHelper.getRetrieveUsersMessage(), OK).respond();
	}
	
	@DeleteMapping("/delete/users")
	public ResponseEntity<BaseDto<Object>> deleteUsersData(@RequestParam Long[] usersIds) {
		
			log.info("Request Object for delete is: "+usersIds[0]);
		
	userService.deleteUsersById(usersIds);
	return new BaseDto<>(usersHelper.getDeleteUsersMessage(), OK).respond();
	}

	@PutMapping("/update/users")
	public ResponseEntity<BaseDto<List<UsersModel>>> updateUsersData(@Valid @RequestBody List<UsersModel> usersModel) {
	log.info("Request Object for update is: "+ usersModel.toString());
	List<UsersModel> UsersModelRes = userService.updateUsersData(usersModel);
	return new BaseDto<>(UsersModelRes,usersHelper.getUpdateUsersMessage(),OK).respond();
	}
	
	
}
