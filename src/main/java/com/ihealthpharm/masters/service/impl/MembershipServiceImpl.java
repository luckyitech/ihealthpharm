package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.MembershipRepository;
import com.ihealthpharm.masters.helper.MembershipHelper;
import com.ihealthpharm.masters.model.MembershipModel;
import com.ihealthpharm.masters.service.MembershipService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional
public class MembershipServiceImpl implements MembershipService  {


	@Autowired
	private MembershipRepository membershipRepo;

	@Autowired
	private MembershipHelper membershipHelper;


	@Override
	public MembershipModel saveMembership( MembershipModel membershipModel) {
		MembershipModel membershipRes = membershipRepo.save(membershipModel);
		log.info("Membership data with ID: " + membershipRes.getMembershipCardId() + " saved succesfully");
		return membershipRes;
	}

	
	@Override
	public MembershipModel updateMembershipData(@Valid MembershipModel membershipModel) {
		MembershipModel membershipRes = getValidMembership(membershipModel.getMembershipCardId());

		if (!Objects.nonNull(membershipRes)) {
			throw new IHealthPharmException(membershipHelper.getNotFoundMembershipMessage(), HttpStatus.NOT_FOUND);
		}
		membershipRes = membershipRepo.save(membershipModel);
		log.info("Membership data with ID : " + membershipRes.getMembershipCardId() + " updated succesfully");

		return membershipRes;
	}

	
	@Override
	public List<MembershipModel> updateMultipleMemberships(@Valid List<MembershipModel> membershipModels) {
		membershipModels = membershipRepo.saveAll(membershipModels);

		log.info("Multiple Membership data  updated succesfully");

		return membershipModels;

	}

	@Override
	public MembershipModel findMembershipById(int membershipId) {

		MembershipModel membershipModelRes = getValidMembership(membershipId);
		if (!Objects.nonNull(membershipModelRes)) {
			throw new IHealthPharmException(membershipHelper.getNotFoundMembershipMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Membership data with ID : " + membershipModelRes.getMembershipCardId() + " retrieved succesfully");
		return membershipModelRes;

	}

	@Override
	public void delete(int membershipId) {
		MembershipModel membershipRes = getValidMembership(membershipId);
		if (!Objects.nonNull(membershipRes)) {
			throw new IHealthPharmException(membershipHelper.getNotFoundMembershipMessage(), HttpStatus.NOT_FOUND);
		}
		membershipRepo.delete(membershipRes);
		log.info("Membership data with ID: " + membershipRes.getMembershipCardId() + " deleted succesfully");

	}

	@Override
	public void deleteMultipleMemberships(int[] membershipId) {
		MembershipModel membershipRes;
		for (int membership : membershipId) {
			membershipRes = getValidMembership(membership);
			if (!Objects.nonNull(membershipRes)) {
				throw new IHealthPharmException(membershipHelper.getNotFoundMembershipMessage(), HttpStatus.NOT_FOUND);
			}
			membershipRepo.delete(membershipRes);
			log.info("Membership data with ID: " + membershipRes.getMembershipCardId() + " deleted succesfully");
		}		
	}



	private MembershipModel getValidMembership(int membershipId) {
		MembershipModel membershipRes = null;
		try {
			membershipRes = membershipRepo.findById(membershipId).get();
			return membershipRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(membershipHelper.getNotFoundMembershipMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@Override
	public List<MembershipModel> findAllByMemberships() {
		return membershipRepo.findAllByOrderByLastUpdateTimestampDesc();
	}


}
