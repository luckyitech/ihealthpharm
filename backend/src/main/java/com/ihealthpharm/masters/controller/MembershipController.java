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
import com.ihealthpharm.masters.helper.MembershipHelper;
import com.ihealthpharm.masters.model.MembershipModel;
import com.ihealthpharm.masters.service.MembershipService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class MembershipController {
	
	@Autowired
	private MembershipService membershipService;
	
	@Autowired
	private MembershipHelper membershipHelper;

	@PostMapping("/save/membership")
	public ResponseEntity<BaseDto<MembershipModel>> saveMembership(@Valid @RequestBody  MembershipModel membershipModel ){
		log.info("Request Object to insert is :"+membershipModel);
		MembershipModel membership=membershipService.saveMembership(membershipModel);
		return new BaseDto<>(membership,membershipHelper.getSaveMembershipMessage(),OK).respond();
	}

	@PutMapping("/update/membership")
	public ResponseEntity<BaseDto<MembershipModel>> updateMembershipData(@Valid @RequestBody MembershipModel membershipModel){
		log.info("Request Object for update is :"+membershipModel);
		MembershipModel membershipRes=membershipService.updateMembershipData(membershipModel);
		return new BaseDto<>(membershipRes,membershipHelper.getUpdateMembershipMessage(),OK).respond();
	}
	
	@PutMapping("/update/memberships")
	public ResponseEntity<BaseDto<List<MembershipModel>>> updateMemberships(@Valid @RequestBody List<MembershipModel> membershipModels ){
		log.info("Request Update For Multiples :"+membershipModels);
		List<MembershipModel> memberships=membershipService.updateMultipleMemberships(membershipModels);
		return new BaseDto<>(memberships,membershipHelper.getUpdateMembershipMessage(),OK).respond();
	}
	
	@DeleteMapping("/delete/membership")
	public ResponseEntity<BaseDto<Object>> deleteMembership(@RequestParam Integer membershipId){
		log.info("Request Object for delete :"+membershipId);
		membershipService.delete(membershipId);
		return new BaseDto<>(membershipHelper.getDeleteMembershipMessage(),OK).respond();
	}
	
	
	@GetMapping("/getmemberships")
	public ResponseEntity<BaseDto<List<MembershipModel>>> getAllMemberships(){
		List<MembershipModel> response=membershipService.findAllByMemberships();
		return new BaseDto<>(response,membershipHelper.getRetrieveMembershipMessage(),OK).respond();
	}
	
	@GetMapping("/getmemberships/forcustomers")
	public ResponseEntity<BaseDto<List<MembershipModel>>> getAllMembershipsForCustomers(){
		List<MembershipModel> result=membershipService.getAllMemberships();
		return new BaseDto<>(result,membershipHelper.getRetrieveMembershipMessage(),OK).respond();
	}
	
	@GetMapping("/getmembership/byid")
	public ResponseEntity<BaseDto<MembershipModel>> getMembershipById(@Valid @RequestParam Integer membershipId){
		MembershipModel membershipRes=membershipService.findMembershipById(membershipId);
		return new BaseDto<>(membershipRes,membershipHelper.getRetrieveMembershipMessage(),OK).respond();
	}
	
	@GetMapping("/getmembership/byname")
	public ResponseEntity<BaseDto<List<MembershipModel>>> getMembershipByName(@RequestParam String membershipName){
		List<MembershipModel> membershipRes=membershipService.findMembershipByName(membershipName);
		return new BaseDto<>(membershipRes,membershipHelper.getRetrieveMembershipMessage(),OK).respond();
	}
	

}
