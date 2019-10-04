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
import com.ihealthpharm.masters.helper.RolesHelper;
import com.ihealthpharm.masters.model.RolesModel;
import com.ihealthpharm.masters.service.RolesService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class RolesController {

	@Autowired
	RolesService rolesService;

	@Autowired
	RolesHelper rolesHelper;

	@PostMapping("/save/role")
	public ResponseEntity<BaseDto<RolesModel>> insertRoleData(@Valid @RequestBody RolesModel roleModel) {
		log.info("Request Object insert is: " + roleModel.toString());
		RolesModel roleModelRes = rolesService.saveRolesData(roleModel);
		return new BaseDto<>(roleModelRes, rolesHelper.getSaveRolesMessage(), OK).respond();
	}

	@PutMapping("/update/role")
	public ResponseEntity<BaseDto<RolesModel>> updateRoleData(@Valid @RequestBody RolesModel rolesModel) {
		log.info("Request Object for update is: ", rolesModel);
		RolesModel rolesModelRes = rolesService.updateRolesData(rolesModel);
		return new BaseDto<>(rolesModelRes, rolesHelper.getUpdateRolesMessage(), OK).respond();
	}

	@DeleteMapping("/delete/role")
	public ResponseEntity<BaseDto<Object>> deleteRoleData(@RequestParam int roleId) {
		log.info("Request Object for delete is: ", roleId);
		rolesService.deleteRolesById(roleId);
		return new BaseDto<>(rolesHelper.getDeleteRolesMessage(), OK).respond();
	}

	@GetMapping("/getrolesdata")
	public ResponseEntity<BaseDto<List<RolesModel>>> getRoleData() {
		List<RolesModel> result = rolesService.findAllRoles();
		return new BaseDto<>(result, rolesHelper.getRetrieveRolesMessage(), OK).respond();
	}

	@GetMapping("/getroledatabyid")
	public ResponseEntity<BaseDto<RolesModel>> getRoleDataById(@RequestParam int roleId) {
		RolesModel result = rolesService.findRoleById(roleId);
		return new BaseDto<>(result, rolesHelper.getRetrieveRolesMessage(), OK).respond();
	}

	@DeleteMapping("/delete/roles")
	public ResponseEntity<BaseDto<Object>> deleteRolesData(@RequestParam int[] RolesId) {

		log.info("Request Object for delete is: " + RolesId[0]);
		rolesService.deleteRolesById(RolesId);
		return new BaseDto<>(rolesHelper.getDeleteRolesMessage(), OK).respond();
	}

	@PutMapping("/update/roles")
	public ResponseEntity<BaseDto<List<RolesModel>>> updateRolesData(@Valid @RequestBody List<RolesModel> rolesModel) {
		log.info("Request Object for update is: " + rolesModel.toString());
		List<RolesModel> RolesModelRes = rolesService.updateRolesData(rolesModel);
		return new BaseDto<>(RolesModelRes, rolesHelper.getUpdateRolesMessage(), OK).respond();
	}
}
