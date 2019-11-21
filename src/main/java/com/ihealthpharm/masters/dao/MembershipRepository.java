package com.ihealthpharm.masters.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihealthpharm.masters.model.MembershipModel;

public interface MembershipRepository extends JpaRepository<MembershipModel,Integer>
{
	List<MembershipModel> findByActiveS(Character active);
	
	 List<MembershipModel> findAllByOrderByLastUpdateTimestampDesc();

	List<MembershipModel> findByMembershipCardNameContains(@Valid String membershipName);
	
}