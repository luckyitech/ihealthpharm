package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.MembershipModel;

public interface MembershipService {

	MembershipModel saveMembership(@Valid MembershipModel membershipModel);

	List<MembershipModel> findAllByMemberships();

	MembershipModel updateMembershipData(@Valid MembershipModel membershipModel);

	List<MembershipModel> updateMultipleMemberships(@Valid List<MembershipModel> membershipModels);
	
	MembershipModel findMembershipById(Integer membershipId);
	
	void delete(Integer membershipId);

	void deleteMultipleMemberships(Integer[] membershipId);
	
	List<MembershipModel> findMembershipByName(@Valid String membershipName);

	List<MembershipModel> getAllMemberships();
	
}
