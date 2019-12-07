package com.ihealthpharm.masters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihealthpharm.masters.model.MembershipModel;

public interface MembershipRepository extends JpaRepository<MembershipModel,Integer>
{
	List<MembershipModel> findByActiveS(Character active);
	
	 List<MembershipModel> findAllByOrderByLastUpdateTimestampDesc();

	@Query("select m from membership m where m.membershipCardName like :membershipName% ")
	List<MembershipModel> findByMembershipCardNameContains(@Param("membershipName") String membershipName);
	
}