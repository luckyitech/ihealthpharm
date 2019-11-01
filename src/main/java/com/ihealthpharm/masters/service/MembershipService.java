package com.ihealthpharm.masters.service;

import java.util.List;

import javax.validation.Valid;

import com.ihealthpharm.masters.model.MembershipModel;

public interface MembershipService {

	MembershipModel saveMembership(@Valid MembershipModel membershipModel);

	List<MembershipModel> findAllByMemberships();

	MembershipModel updateMembershipData(@Valid MembershipModel membershipModel);

	List<MembershipModel> updateMultipleMemberships(@Valid List<MembershipModel> membershipModels);
	
	MembershipModel findMembershipById(int membershipId);
	
	void delete(int membershipId);

	void deleteMultipleMemberships(int[] membershipId);
	

	
}
