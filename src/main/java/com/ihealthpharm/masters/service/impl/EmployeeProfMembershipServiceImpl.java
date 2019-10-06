package com.ihealthpharm.masters.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeProfMembershipRepository;
import com.ihealthpharm.masters.helper.EmployeeProfMembershipHelper;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeeProfMembershipModel;
import com.ihealthpharm.masters.service.EmployeeProfMembershipService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeProfMembershipServiceImpl implements EmployeeProfMembershipService {

	@Autowired
	EmployeeProfMembershipRepository employeeProfMembershipRepository;

	@Autowired
	EmployeeProfMembershipHelper employeeProfMembershipHelper;

	@Override
	public void deleteEmployeeEmployeeProfMembershipData(Integer employeeProfMembershipId) {
		EmployeeProfMembershipModel employeeProfMembershipModel = getValidEmployeeProfMembershipModel(employeeProfMembershipId);
		employeeProfMembershipRepository.delete(employeeProfMembershipModel);
		log.info(employeeProfMembershipHelper.getDeleteEmployeeProfMembershipMessage());
	}

	@Override
	public EmployeeProfMembershipModel findEmployeeProfMembershipDataById(Integer employeeProfMembershipId) {
		EmployeeProfMembershipModel employeeProfMembershipModel = getValidEmployeeProfMembershipModel(employeeProfMembershipId);
		log.info(employeeProfMembershipHelper.getRetrieveEmployeeProfMembershipMessage());
		return employeeProfMembershipModel;
	}

	@Override
	public List<EmployeeProfMembershipModel> findAllEmployeeProfMembershipData() {

		return employeeProfMembershipRepository.findAll();
	}

	@Override
	public EmployeeProfMembershipModel saveEmployeeProfMembershipData(EmployeeProfMembershipModel employeeProfMembershipModel) {
		employeeProfMembershipModel = employeeProfMembershipRepository.save(employeeProfMembershipModel);
		return employeeProfMembershipModel;
	}

	@Override
	public EmployeeProfMembershipModel updateEmployeeProfMembershipData(EmployeeProfMembershipModel employeeProfMembershipModel) {
		EmployeeProfMembershipModel employeeProfMembershipRes = getValidEmployeeProfMembershipModel(employeeProfMembershipModel.getEmployeeProfMembershipId());

		if (!Objects.nonNull(employeeProfMembershipRes)) {
			throw new IHealthPharmException(employeeProfMembershipHelper.getNotFoundEmployeeProfMembershipMessage(),HttpStatus.NOT_FOUND);
		}

		employeeProfMembershipRes =employeeProfMembershipRepository.save(employeeProfMembershipModel);
		return employeeProfMembershipRes;
	}

	private EmployeeProfMembershipModel getValidEmployeeProfMembershipModel(Integer employeeProfMembershipId)
	{
		EmployeeProfMembershipModel employeeProfMembershipModel = null;
		try {
			employeeProfMembershipModel =  employeeProfMembershipRepository.findById(employeeProfMembershipId).get();
			return employeeProfMembershipModel;
		}catch(NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employeeProfMembershipHelper.getNotFoundEmployeeProfMembershipMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public EmployeeProfMembershipModel findEmployeeProfMembershipDataByEmployeeId(EmployeeModel employee) {
		
		return employeeProfMembershipRepository.findByEmployee(employee);
	}

}
