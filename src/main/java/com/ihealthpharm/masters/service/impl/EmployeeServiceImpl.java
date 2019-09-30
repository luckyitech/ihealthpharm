package com.ihealthpharm.masters.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.dao.EmployeeAccessRepository;
import com.ihealthpharm.masters.dao.EmployeeRepository;
import com.ihealthpharm.masters.dto.EmployeeAccessDTO;
import com.ihealthpharm.masters.dto.EmployeeDTO;
import com.ihealthpharm.masters.dto.EmployeeEducationDTO;
import com.ihealthpharm.masters.dto.EmployeeHonorDTO;
import com.ihealthpharm.masters.dto.EmployeeInterestDTO;
import com.ihealthpharm.masters.dto.EmployeeProfMembershipDto;
import com.ihealthpharm.masters.dto.EmployeePublicationDTO;
import com.ihealthpharm.masters.dto.EmployeeSalaryDTO;
import com.ihealthpharm.masters.dto.EmploymentHistoryDTO;
import com.ihealthpharm.masters.helper.EmployeeHelper;
import com.ihealthpharm.masters.model.EmployeeAccessModel;
import com.ihealthpharm.masters.model.EmployeeCredentialsModel;
import com.ihealthpharm.masters.model.EmployeeEducationModel;
import com.ihealthpharm.masters.model.EmployeeHonorModel;
import com.ihealthpharm.masters.model.EmployeeInterestModel;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.EmployeeProfMembershipModel;
import com.ihealthpharm.masters.model.EmployeePublicationModel;
import com.ihealthpharm.masters.model.EmployeeSalaryModel;
import com.ihealthpharm.masters.model.EmploymentHistoryModel;
import com.ihealthpharm.masters.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeAccessRepository employeeAccessRepository;

	@Autowired
	EmployeeHelper employeeHelper;

	@Override
	public EmployeeModel saveEmployeeData(EmployeeModel employeeModel,EmployeeAccessDTO employeeAccessDTO) {
		
/*
		EmployeeModel employeeRes = new EmployeeModel();
		employeeRes.setEmployeeId(employeeRes.getEmployeeId());
		employeeRes.setPhoneNumber(employeeDto.getPhoneNumber());
		employeeRes.setFirstName(employeeDto.getFirstName());
		employeeRes.setLastName(employeeDto.getLastName());
		employeeRes.setEmployeeTypeFullTimOrPartTime(employeeDto.getEmployeeTypeFullTimeOrPartTime());
		employeeRes.setEmployeeCode(employeeDto.getEmployeeCode());
		employeeRes.setTitle(employeeDto.getTitle());
		employeeRes.setMiddleName(employeeDto.getMiddleName());
		employeeRes.setGenderCode(employeeDto.getGenderCode());
		employeeRes.setSalary(employeeDto.getSalary());
		employeeRes.setDateOfBirth(employeeDto.getDateOfBirth());
		employeeRes.setDateOfJoining(employeeDto.getDateOfJoining());
		employeeRes.setEmailId(employeeDto.getEmailId());
		employeeRes.setDesignation(employeeDto.getDesignation());
		employeeRes.setBipgraphyDesc(employeeDto.getBipgraphyDesc());
		employeeRes.setAddressLine1(employeeDto.getAddressLine1());
		employeeRes.setAddressLine2(employeeDto.getAddressLine2());
		employeeRes.setActiveS(employeeDto.getActiveS());
		employeeRes.setCity(employeeDto.getCity());
		employeeRes.setState(employeeDto.getState());
		employeeRes.setCountry(employeeDto.getCountry());
		employeeRes.setBloodGroup(employeeDto.getBloodGroup());
		employeeRes.setNightShifts(employeeDto.getNightShifts());
		employeeRes.setPostalCode(employeeDto.getPostalCode());
		try {
			employeeRes.setProfileImage(employeeDto.getProfileImage().getBytes());
			employeeRes.setIdentificationDocument(employeeDto.getIdentificationDocument().getBytes());
			employeeRes.setPoliceGoodConductCertificate(employeeDto.getPoliceGoodConductCertificate().getBytes());
			employeeRes.setResume(employeeDto.getResume().getBytes());
			employeeRes.setSignedContract(employeeDto.getSignedContract().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employeeRes.setNssfNumber(employeeDto.getNssfNumber());
		employeeRes.setIdentificationNumber(employeeDto.getIdentificationNumber());
		employeeRes.setPostBox(employeeDto.getPostBox());
		employeeRes.setLandlineNumber(employeeDto.getLandlineNumber());
		employeeRes.setKraPin(employeeDto.getKraPin());
		employeeRes.setNhifNumber(employeeDto.getNhifNumber());
		employeeRes.setPharmacyId(employeeDto.getPharmacyId());
		employeeRes.setEmployeeTypeModel(employeeDto.getEmployeeTypeModel());
		employeeRes.setCreatedUser(" ");

		List<EmployeeEducationModel> ll = new ArrayList<EmployeeEducationModel>();
		List<EmployeeEducationDTO> list = employeeDto.getEmployeeEducationDto();

		for (EmployeeEducationDTO emp : list) {

			EmployeeEducationModel employeeEducationModel = new EmployeeEducationModel();
			employeeEducationModel.setStudiedAt(emp.getStudiedAt());
			employeeEducationModel.setCreationUserId(" ");
			employeeEducationModel.setDegree(emp.getDegree());
			employeeEducationModel.setGraduationDate(emp.getGraduationDate());
			employeeEducationModel.setEmployee(employeeRes);
			ll.add(employeeEducationModel);

		}
		employeeRes.setEmployeeEducationModel(ll);

		List<EmployeeProfMembershipModel> employeeProfMembershipModelList = new ArrayList<>();
		List<EmployeeProfMembershipDto> employeeProfMembershipDtoList = employeeDto.getEmployeeProfMembershipDto();

		for (EmployeeProfMembershipDto emp : employeeProfMembershipDtoList) {

			EmployeeProfMembershipModel employeeProfMembershipModel = new EmployeeProfMembershipModel();
			employeeProfMembershipModel.setMembershipName(emp.getMembershipName());
			employeeProfMembershipModel.setStartDate(emp.getStartDate());
			employeeProfMembershipModel.setEndDate(emp.getEndDate());
			employeeProfMembershipModel.setCratedUserId("  ");
			employeeProfMembershipModel.setEmployee(employeeRes);
			employeeProfMembershipModelList.add(employeeProfMembershipModel);

		}
		employeeRes.setEmployeeProfMembershipModel(employeeProfMembershipModelList);

		List<EmployeeHonorModel> employeeHonorModelList = new ArrayList<>();
		List<EmployeeHonorDTO> employeeHonorDtoList = employeeDto.getEmployeeHonorDto();

		for (EmployeeHonorDTO emp : employeeHonorDtoList) {
			EmployeeHonorModel employeeHonorModel = new EmployeeHonorModel();
			employeeHonorModel.setHonorName(emp.getHonorName());
			employeeHonorModel.setHonorDesc(emp.getHonorDesc());
			employeeHonorModel.setReceivedDate(emp.getReceivedDate());
			employeeHonorModel.setCratedUserId(" ");
			employeeHonorModel.setEmployee(employeeRes);
			employeeHonorModelList.add(employeeHonorModel);
		}
		employeeRes.setEmployeeHonorModel(employeeHonorModelList);

		List<EmployeeInterestModel> employeeInterestModelList = new ArrayList<>();
		List<EmployeeInterestDTO> employeeInterestDtoList = employeeDto.getEmployeeInterestDto();

		for (EmployeeInterestDTO emp : employeeInterestDtoList) {
			EmployeeInterestModel employeeInterestModel = new EmployeeInterestModel();
			employeeInterestModel.setAreaOfIntrestDesc(emp.getAreaOfIntrestDesc());
			employeeInterestModel.setCratedUserId("  ");
			employeeInterestModel.setEmployee(employeeRes);
			employeeInterestModelList.add(employeeInterestModel);
		}
		employeeRes.setEmployeeInterestModel(employeeInterestModelList);

		List<EmployeePublicationModel> employeePublicationModelList = new ArrayList<>();
		List<EmployeePublicationDTO> employeePublicationDtoList = employeeDto.getEmployeePublicationDto();

		for (EmployeePublicationDTO emp : employeePublicationDtoList) {
			EmployeePublicationModel employeePublicationModel = new EmployeePublicationModel();
			employeePublicationModel.setPublicationName(emp.getPublicationName());
			employeePublicationModel.setPublicationDesc(emp.getPublicationDesc());
			employeePublicationModel.setPublishDate(emp.getPublishDate());
			employeePublicationModel.setCratedUserId(" ");
			employeePublicationModel.setEmployee(employeeRes);
			employeePublicationModelList.add(employeePublicationModel);
		}
		employeeRes.setEmployeePublicationModel(employeePublicationModelList);

		List<EmploymentHistoryModel> employmentHistoryModelList = new ArrayList<>();
		List<EmploymentHistoryDTO> employmentHistoryDtoList = employeeDto.getEmployeeHistoryDto();

		for (EmploymentHistoryDTO emp : employmentHistoryDtoList) {
			EmploymentHistoryModel employmentHistoryModel = new EmploymentHistoryModel();
			employmentHistoryModel.setCompanyName(emp.getCompanyName());
			employmentHistoryModel.setStartDate(emp.getStartDate());
			employmentHistoryModel.setEndDate(emp.getEndDate());
			employmentHistoryModel.setCompnayAddress(emp.getCompnayAddress());
			employmentHistoryModel.setReportingPersonDetails(emp.getReportingPersonDetails());
			employmentHistoryModel.setManagerName(emp.getManagerName());
			employmentHistoryModel.setManagerPhoneNumber(emp.getManagerPhoneNumber());
			employmentHistoryModel.setManagerEmail(emp.getManagerEmail());
			employmentHistoryModel.setDesignation(emp.getDesignation());
			employmentHistoryModel.setEmployementType(emp.getEmployementType());
			employmentHistoryModel.setReference1(emp.getReference1());
			employmentHistoryModel.setReference2(emp.getReference2());
			employmentHistoryModel.setCratedUserId(" ");
			employmentHistoryModel.setEmployee(employeeRes);
			employmentHistoryModelList.add(employmentHistoryModel);
		}
		employeeRes.setEmployeeHistoryModel(employmentHistoryModelList);

		List<EmployeeSalaryModel> EmployeeSalaryModelList = new ArrayList<>();
		List<EmployeeSalaryDTO> EmployeeSalaryDtoList = employeeDto.getEmployeeSalaryDto();

		for (EmployeeSalaryDTO emp : EmployeeSalaryDtoList) {
			EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();
			employeeSalaryModel.setSalaryDate(emp.getSalaryDate());
			employeeSalaryModel.setBasic(emp.getBasic());
			employeeSalaryModel.setHra(emp.getHra());
			employeeSalaryModel.setDa(emp.getDa());
			employeeSalaryModel.setMedical(emp.getMedical());
			employeeSalaryModel.setPTax(emp.getPTax());
			employeeSalaryModel.setPfEmployee(emp.getPfEmployee());
			employeeSalaryModel.setPfEmployer(emp.getPfEmployer());
			employeeSalaryModel.setTds(emp.getTds());
			employeeSalaryModel.setEsi(emp.getEsi());
			employeeSalaryModel.setGrossSalary(emp.getGrossSalary());
			employeeSalaryModel.setCratedUserId(" ");
			employeeSalaryModel.setEmployee(employeeRes);
			EmployeeSalaryModelList.add(employeeSalaryModel);
		}
		employeeRes.setEmployeeSalaryModel(EmployeeSalaryModelList);
		
		log.info(" Active S: Value: "+employeeRes.getActiveS());*/
		EmployeeModel employeeRes = employeeRepository.save(employeeModel);
		EmployeeAccessModel employeeAccessModel = null;
		for(Integer i=0; i<employeeAccessDTO.getPharmaAccessids().length;i++) {
			employeeAccessModel = new EmployeeAccessModel();
			employeeAccessModel.setEmployeeModel(employeeRes);
			employeeAccessModel.setPharmaAccessModel(employeeAccessDTO.getPharmaAccessids()[i]);
			if(employeeAccessDTO.getFlag()[i])
			{
				employeeAccessModel.setActiveS('Y');
			}
			else
			{
				employeeAccessModel.setActiveS('N');
			}
			employeeAccessRepository.save(employeeAccessModel);
		}
		log.info("Employee data with ID: " + employeeRes.getEmployeeId() + " saved succesfully");
		return employeeRes;
	}

	@Override
	public EmployeeModel updateEmployeeData(EmployeeModel employeeModel) {
		EmployeeModel employeeRes = getValidEmployee(employeeModel.getEmployeeId());

		if (!Objects.nonNull(employeeRes)) {
			throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
		}
		// employeeRes = new EmployeeModel();
		/*employeeRes.setEmployeeId(employeeRes.getEmployeeId());
		employeeRes.setPhoneNumber(employeeDto.getPhoneNumber());
		employeeRes.setFirstName(employeeDto.getFirstName());
		employeeRes.setLastName(employeeDto.getLastName());
		employeeRes.setEmployeeTypeFullTimOrPartTime(employeeDto.getEmployeeTypeFullTimeOrPartTime());
		employeeRes.setEmployeeCode(employeeDto.getEmployeeCode());
		employeeRes.setTitle(employeeDto.getTitle());
		employeeRes.setMiddleName(employeeDto.getMiddleName());
		employeeRes.setGenderCode(employeeDto.getGenderCode());
		employeeRes.setSalary(employeeDto.getSalary());
		employeeRes.setDateOfBirth(employeeDto.getDateOfBirth());
		employeeRes.setDateOfJoining(employeeDto.getDateOfJoining());
		employeeRes.setEmailId(employeeDto.getEmailId());
		employeeRes.setDesignation(employeeDto.getDesignation());
		employeeRes.setBipgraphyDesc(employeeDto.getBipgraphyDesc());
		employeeRes.setAddressLine1(employeeDto.getAddressLine1());
		employeeRes.setAddressLine2(employeeDto.getAddressLine2());
		employeeRes.setActiveS(employeeDto.getActiveS());
		employeeRes.setCity(employeeDto.getCity());
		employeeRes.setState(employeeDto.getState());
		employeeRes.setCountry(employeeDto.getCountry());
		employeeRes.setBloodGroup(employeeDto.getBloodGroup());
		employeeRes.setNightShifts(employeeDto.getNightShifts());
		employeeRes.setPostalCode(employeeDto.getPostalCode());
		try {
			employeeRes.setProfileImage(employeeDto.getProfileImage().getBytes());
			employeeRes.setIdentificationDocument(employeeDto.getIdentificationDocument().getBytes());
			employeeRes.setPoliceGoodConductCertificate(employeeDto.getPoliceGoodConductCertificate().getBytes());
			employeeRes.setResume(employeeDto.getResume().getBytes());
			employeeRes.setSignedContract(employeeDto.getSignedContract().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employeeRes.setNssfNumber(employeeDto.getNssfNumber());
		employeeRes.setIdentificationNumber(employeeDto.getIdentificationNumber());
		employeeRes.setPostBox(employeeDto.getPostBox());
		employeeRes.setLandlineNumber(employeeDto.getLandlineNumber());
		employeeRes.setKraPin(employeeDto.getKraPin());
		employeeRes.setNhifNumber(employeeDto.getNhifNumber());
		employeeRes.setPharmacyId(employeeDto.getPharmacyId());
		employeeRes.setEmployeeTypeModel(employeeDto.getEmployeeTypeModel());
		employeeRes.setCreatedUser(" ");

		if (Objects.nonNull(employeeDto.getEmployeeEducationDto())) {
			List<EmployeeEducationModel> ll = new ArrayList<EmployeeEducationModel>();
			List<EmployeeEducationDTO> list = employeeDto.getEmployeeEducationDto();

			for (EmployeeEducationDTO emp : list) {

				EmployeeEducationModel employeeEducationModel = new EmployeeEducationModel();
				employeeEducationModel.setStudiedAt(emp.getStudiedAt());
				//employeeEducationModel.setCreationUserId(" ");
				employeeEducationModel.setDegree(emp.getDegree());
				employeeEducationModel.setGraduationDate(emp.getGraduationDate());
				employeeEducationModel.setEmployee(employeeRes);
				ll.add(employeeEducationModel);

			}
			employeeRes.setEmployeeEducationModel(ll);
		}

		if (Objects.nonNull(employeeDto.getEmployeeProfMembershipDto())) {
			List<EmployeeProfMembershipModel> employeeProfMembershipModelList = new ArrayList<>();
			List<EmployeeProfMembershipDto> employeeProfMembershipDtoList = employeeDto
					.getEmployeeProfMembershipDto();

			for (EmployeeProfMembershipDto emp : employeeProfMembershipDtoList) {

				EmployeeProfMembershipModel employeeProfMembershipModel = new EmployeeProfMembershipModel();
				employeeProfMembershipModel.setMembershipName(emp.getMembershipName());
				employeeProfMembershipModel.setStartDate(emp.getStartDate());
				employeeProfMembershipModel.setEndDate(emp.getEndDate());
				//employeeProfMembershipModel.setCratedUserId("  ");
				employeeProfMembershipModel.setEmployee(employeeRes);
				employeeProfMembershipModelList.add(employeeProfMembershipModel);

			}
			employeeRes.setEmployeeProfMembershipModel(employeeProfMembershipModelList);
		}

		if (Objects.nonNull(employeeDto.getEmployeeHonorDto())) {
			List<EmployeeHonorModel> employeeHonorModelList = new ArrayList<>();
			List<EmployeeHonorDTO> employeeHonorDtoList = employeeDto.getEmployeeHonorDto();

			for (EmployeeHonorDTO emp : employeeHonorDtoList) {
				EmployeeHonorModel employeeHonorModel = new EmployeeHonorModel();
				employeeHonorModel.setHonorName(emp.getHonorName());
				employeeHonorModel.setHonorDesc(emp.getHonorDesc());
				employeeHonorModel.setReceivedDate(emp.getReceivedDate());
				//employeeHonorModel.setCratedUserId(" ");
				employeeHonorModel.setEmployee(employeeRes);
				employeeHonorModelList.add(employeeHonorModel);
			}
			employeeRes.setEmployeeHonorModel(employeeHonorModelList);
		}

		if (Objects.nonNull(employeeDto.getEmployeeInterestDto())) {
			List<EmployeeInterestModel> employeeInterestModelList = new ArrayList<>();
			List<EmployeeInterestDTO> employeeInterestDtoList = employeeDto.getEmployeeInterestDto();

			for (EmployeeInterestDTO emp : employeeInterestDtoList) {
				EmployeeInterestModel employeeInterestModel = new EmployeeInterestModel();
				employeeInterestModel.setAreaOfIntrestDesc(emp.getAreaOfIntrestDesc());
				//employeeInterestModel.setCratedUserId("  ");
				employeeInterestModel.setEmployee(employeeRes);
				employeeInterestModelList.add(employeeInterestModel);
			}
			employeeRes.setEmployeeInterestModel(employeeInterestModelList);
		}

		if (Objects.nonNull(employeeDto.getEmployeePublicationDto())) {
			List<EmployeePublicationModel> employeePublicationModelList = new ArrayList<>();
			List<EmployeePublicationDTO> employeePublicationDtoList = employeeDto.getEmployeePublicationDto();

			for (EmployeePublicationDTO emp : employeePublicationDtoList) {
				EmployeePublicationModel employeePublicationModel = new EmployeePublicationModel();
				employeePublicationModel.setPublicationName(emp.getPublicationName());
				employeePublicationModel.setPublicationDesc(emp.getPublicationDesc());
				employeePublicationModel.setPublishDate(emp.getPublishDate());
				//employeePublicationModel.setCratedUserId(" ");
				employeePublicationModel.setEmployee(employeeRes);
				employeePublicationModelList.add(employeePublicationModel);
			}
			employeeRes.setEmployeePublicationModel(employeePublicationModelList);
		}

		if (Objects.nonNull(employeeDto.getEmployeeHistoryDto())) {
			List<EmploymentHistoryModel> employmentHistoryModelList = new ArrayList<>();
			List<EmploymentHistoryDTO> employmentHistoryDtoList = employeeDto.getEmployeeHistoryDto();

			for (EmploymentHistoryDTO emp : employmentHistoryDtoList) {
				EmploymentHistoryModel employmentHistoryModel = new EmploymentHistoryModel();
				employmentHistoryModel.setCompanyName(emp.getCompanyName());
				employmentHistoryModel.setStartDate(emp.getStartDate());
				employmentHistoryModel.setEndDate(emp.getEndDate());
				employmentHistoryModel.setCompnayAddress(emp.getCompnayAddress());
				employmentHistoryModel.setReportingPersonDetails(emp.getReportingPersonDetails());
				employmentHistoryModel.setManagerName(emp.getManagerName());
				employmentHistoryModel.setManagerPhoneNumber(emp.getManagerPhoneNumber());
				employmentHistoryModel.setManagerEmail(emp.getManagerEmail());
				employmentHistoryModel.setDesignation(emp.getDesignation());
				employmentHistoryModel.setEmployementType(emp.getEmployementType());
				employmentHistoryModel.setReference1(emp.getReference1());
				employmentHistoryModel.setReference2(emp.getReference2());
				//employmentHistoryModel.setCratedUserId(" ");
				employmentHistoryModel.setEmployee(employeeRes);
				employmentHistoryModelList.add(employmentHistoryModel);
			}
			employeeRes.setEmployeeHistoryModel(employmentHistoryModelList);
		}

		if (Objects.nonNull(employeeDto.getEmployeeSalaryDto())) {
			List<EmployeeSalaryModel> EmployeeSalaryModelList = new ArrayList<>();
			List<EmployeeSalaryDTO> EmployeeSalaryDtoList = employeeDto.getEmployeeSalaryDto();

			for (EmployeeSalaryDTO emp : EmployeeSalaryDtoList) {
				EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();
				employeeSalaryModel.setSalaryDate(emp.getSalaryDate());
				employeeSalaryModel.setBasic(emp.getBasic());
				employeeSalaryModel.setHra(emp.getHra());
				employeeSalaryModel.setDa(emp.getDa());
				employeeSalaryModel.setMedical(emp.getMedical());
				//employeeSalaryModel.setPTax(emp.getPTax());
				employeeSalaryModel.setPfEmployee(emp.getPfEmployee());
				employeeSalaryModel.setPfEmployer(emp.getPfEmployer());
				employeeSalaryModel.setTds(emp.getTds());
				employeeSalaryModel.setEsi(emp.getEsi());
				employeeSalaryModel.setGrossSalary(emp.getGrossSalary());
				//employeeSalaryModel.setCratedUserId(" ");
				employeeSalaryModel.setEmployee(employeeRes);
				EmployeeSalaryModelList.add(employeeSalaryModel);
			}
			employeeRes.setEmployeeSalaryModel(EmployeeSalaryModelList);

		}*/
		employeeRes = employeeRepository.save(employeeModel);
		log.info("Employee data with ID : " + employeeRes.getEmployeeId() + " updated succesfully");

		return employeeRes;
	}

	@Override
	public List<EmployeeModel> findAllEmployees() {
		return employeeRepository.findAllByOrderByCreationTimeStampDesc();
	}

	@Override
	public EmployeeModel findEmployeeById(int employeeId) {
		EmployeeModel employeeRes = getValidEmployee(employeeId);
		if (!Objects.nonNull(employeeRes)) {
			throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
		}
		log.info("Employee data with ID : " + employeeRes.getEmployeeId() + " retrieved succesfully");
		return employeeRes;
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		EmployeeModel employeeRes = getValidEmployee(employeeId);
		if (!Objects.nonNull(employeeRes)) {
			throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
		}
		employeeRepository.delete(employeeRes);
		log.info("Employee data with ID: " + employeeRes.getEmployeeId() + " deleted succesfully");
	}

	public EmployeeModel getValidEmployee(int employeeId) {
		EmployeeModel employeeRes = null;
		try {
			employeeRes = employeeRepository.findById(employeeId).get();
			return employeeRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<EmployeeModel> updateEmployeesData(List<EmployeeDTO> employeeDtos) throws ParseException {
		EmployeeModel employeeRes;
		List<EmployeeModel> employeeResp = new ArrayList<>();
		for (EmployeeDTO employeeDto : employeeDtos) {
			employeeRes = getValidEmployee(employeeDto.getEmployeeId());

			if (!Objects.nonNull(employeeRes)) {
				throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
			}
			// employeeRes = new EmployeeModel();
			employeeRes.setEmployeeId(employeeRes.getEmployeeId());
			employeeRes.setPhoneNumber(employeeDto.getPhoneNumber());
			employeeRes.setFirstName(employeeDto.getFirstName());
			employeeRes.setLastName(employeeDto.getLastName());
			employeeRes.setEmployeeTypeFullTimOrPartTime(employeeDto.getEmployeeTypeFullTimeOrPartTime());
			employeeRes.setEmployeeCode(employeeDto.getEmployeeCode());
			employeeRes.setTitle(employeeDto.getTitle());
			employeeRes.setMiddleName(employeeDto.getMiddleName());
			employeeRes.setGenderCode(employeeDto.getGenderCode());
			employeeRes.setSalary(employeeDto.getSalary());
			employeeRes.setActiveS(employeeDto.getActiveS());
			employeeRes.setDateOfBirth(employeeDto.getDateOfBirth());
			employeeRes.setDateOfJoining(employeeDto.getDateOfJoining());
			employeeRes.setEmailId(employeeDto.getEmailId());
			employeeRes.setDesignation(employeeDto.getDesignation());
			employeeRes.setBipgraphyDesc(employeeDto.getBipgraphyDesc());
			employeeRes.setAddressLine1(employeeDto.getAddressLine1());
			employeeRes.setAddressLine2(employeeDto.getAddressLine2());
			employeeRes.setCity(employeeDto.getCity());
			employeeRes.setState(employeeDto.getState());
			employeeRes.setCountry(employeeDto.getCountry());
			employeeRes.setBloodGroup(employeeDto.getBloodGroup());
			employeeRes.setNightShifts(employeeDto.getNightShifts());
			employeeRes.setPostalCode(employeeDto.getPostalCode());
			try {
				employeeRes.setProfileImage(employeeDto.getProfileImage().getBytes());
				employeeRes.setIdentificationDocument(employeeDto.getIdentificationDocument().getBytes());
				employeeRes.setPoliceGoodConductCertificate(employeeDto.getPoliceGoodConductCertificate().getBytes());
				employeeRes.setResume(employeeDto.getResume().getBytes());
				employeeRes.setSignedContract(employeeDto.getSignedContract().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			employeeRes.setNssfNumber(employeeDto.getNssfNumber());
			employeeRes.setIdentificationNumber(employeeDto.getIdentificationNumber());
			employeeRes.setPostBox(employeeDto.getPostBox());
			employeeRes.setLandlineNumber(employeeDto.getLandlineNumber());
			employeeRes.setKraPin(employeeDto.getKraPin());
			employeeRes.setNhifNumber(employeeDto.getNhifNumber());
			employeeRes.setPharmacyId(employeeDto.getPharmacyId());
			employeeRes.setEmployeeTypeModel(employeeDto.getEmployeeTypeModel());
			employeeRes.setCreatedUser(" ");

			if (Objects.nonNull(employeeDto.getEmployeeEducationDto())) {
				List<EmployeeEducationModel> ll = new ArrayList<EmployeeEducationModel>();
				List<EmployeeEducationDTO> list = employeeDto.getEmployeeEducationDto();

				for (EmployeeEducationDTO emp : list) {

					EmployeeEducationModel employeeEducationModel = new EmployeeEducationModel();
					employeeEducationModel.setStudiedAt(emp.getStudiedAt());
					//employeeEducationModel.setCreationUserId(" ");
					employeeEducationModel.setDegree(emp.getDegree());
					employeeEducationModel.setGraduationDate(emp.getGraduationDate());
					employeeEducationModel.setEmployee(employeeRes);
					ll.add(employeeEducationModel);

				}
				employeeRes.setEmployeeEducationModel(ll);
			}

			if (Objects.nonNull(employeeDto.getEmployeeProfMembershipDto())) {
				List<EmployeeProfMembershipModel> employeeProfMembershipModelList = new ArrayList<>();
				List<EmployeeProfMembershipDto> employeeProfMembershipDtoList = employeeDto
						.getEmployeeProfMembershipDto();

				for (EmployeeProfMembershipDto emp : employeeProfMembershipDtoList) {

					EmployeeProfMembershipModel employeeProfMembershipModel = new EmployeeProfMembershipModel();
					employeeProfMembershipModel.setMembershipName(emp.getMembershipName());
					employeeProfMembershipModel.setStartDate(emp.getStartDate());
					employeeProfMembershipModel.setEndDate(emp.getEndDate());
					//employeeProfMembershipModel.setCratedUserId("  ");
					employeeProfMembershipModel.setEmployee(employeeRes);
					employeeProfMembershipModelList.add(employeeProfMembershipModel);

				}
				employeeRes.setEmployeeProfMembershipModel(employeeProfMembershipModelList);
			}

			if (Objects.nonNull(employeeDto.getEmployeeHonorDto())) {
				List<EmployeeHonorModel> employeeHonorModelList = new ArrayList<>();
				List<EmployeeHonorDTO> employeeHonorDtoList = employeeDto.getEmployeeHonorDto();

				for (EmployeeHonorDTO emp : employeeHonorDtoList) {
					EmployeeHonorModel employeeHonorModel = new EmployeeHonorModel();
					employeeHonorModel.setHonorName(emp.getHonorName());
					employeeHonorModel.setHonorDesc(emp.getHonorDesc());
					employeeHonorModel.setReceivedDate(emp.getReceivedDate());
					//employeeHonorModel.setCratedUserId(" ");
					employeeHonorModel.setEmployee(employeeRes);
					employeeHonorModelList.add(employeeHonorModel);
				}
				employeeRes.setEmployeeHonorModel(employeeHonorModelList);
			}

			if (Objects.nonNull(employeeDto.getEmployeeInterestDto())) {
				List<EmployeeInterestModel> employeeInterestModelList = new ArrayList<>();
				List<EmployeeInterestDTO> employeeInterestDtoList = employeeDto.getEmployeeInterestDto();

				for (EmployeeInterestDTO emp : employeeInterestDtoList) {
					EmployeeInterestModel employeeInterestModel = new EmployeeInterestModel();
					employeeInterestModel.setAreaOfIntrestDesc(emp.getAreaOfIntrestDesc());
					//employeeInterestModel.setCratedUserId("  ");
					employeeInterestModel.setEmployee(employeeRes);
					employeeInterestModelList.add(employeeInterestModel);
				}
				employeeRes.setEmployeeInterestModel(employeeInterestModelList);
			}

			if (Objects.nonNull(employeeDto.getEmployeePublicationDto())) {
				List<EmployeePublicationModel> employeePublicationModelList = new ArrayList<>();
				List<EmployeePublicationDTO> employeePublicationDtoList = employeeDto.getEmployeePublicationDto();

				for (EmployeePublicationDTO emp : employeePublicationDtoList) {
					EmployeePublicationModel employeePublicationModel = new EmployeePublicationModel();
					employeePublicationModel.setPublicationName(emp.getPublicationName());
					employeePublicationModel.setPublicationDesc(emp.getPublicationDesc());
					employeePublicationModel.setPublishDate(emp.getPublishDate());
					//employeePublicationModel.setCratedUserId(" ");
					employeePublicationModel.setEmployee(employeeRes);
					employeePublicationModelList.add(employeePublicationModel);
				}
				employeeRes.setEmployeePublicationModel(employeePublicationModelList);
			}

			if (Objects.nonNull(employeeDto.getEmployeeHistoryDto())) {
				List<EmploymentHistoryModel> employmentHistoryModelList = new ArrayList<>();
				List<EmploymentHistoryDTO> employmentHistoryDtoList = employeeDto.getEmployeeHistoryDto();

				for (EmploymentHistoryDTO emp : employmentHistoryDtoList) {
					EmploymentHistoryModel employmentHistoryModel = new EmploymentHistoryModel();
					employmentHistoryModel.setCompanyName(emp.getCompanyName());
					employmentHistoryModel.setStartDate(emp.getStartDate());
					employmentHistoryModel.setEndDate(emp.getEndDate());
					employmentHistoryModel.setCompnayAddress(emp.getCompnayAddress());
					employmentHistoryModel.setReportingPersonDetails(emp.getReportingPersonDetails());
					employmentHistoryModel.setManagerName(emp.getManagerName());
					employmentHistoryModel.setManagerPhoneNumber(emp.getManagerPhoneNumber());
					employmentHistoryModel.setManagerEmail(emp.getManagerEmail());
					employmentHistoryModel.setDesignation(emp.getDesignation());
					employmentHistoryModel.setEmployementType(emp.getEmployementType());
					employmentHistoryModel.setReference1(emp.getReference1());
					employmentHistoryModel.setReference2(emp.getReference2());
					//employmentHistoryModel.setCratedUserId(" ");
					employmentHistoryModel.setEmployee(employeeRes);
					employmentHistoryModelList.add(employmentHistoryModel);
				}
				employeeRes.setEmployeeHistoryModel(employmentHistoryModelList);
			}

			if (Objects.nonNull(employeeDto.getEmployeeSalaryDto())) {
				List<EmployeeSalaryModel> EmployeeSalaryModelList = new ArrayList<>();
				List<EmployeeSalaryDTO> EmployeeSalaryDtoList = employeeDto.getEmployeeSalaryDto();

				for (EmployeeSalaryDTO emp : EmployeeSalaryDtoList) {
					EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();
					employeeSalaryModel.setSalaryDate(emp.getSalaryDate());
					employeeSalaryModel.setBasic(emp.getBasic());
					employeeSalaryModel.setHra(emp.getHra());
					employeeSalaryModel.setDa(emp.getDa());
					employeeSalaryModel.setMedical(emp.getMedical());
					//employeeSalaryModel.setPTax(emp.getPTax());
					employeeSalaryModel.setPfEmployee(emp.getPfEmployee());
					employeeSalaryModel.setPfEmployer(emp.getPfEmployer());
					employeeSalaryModel.setTds(emp.getTds());
					employeeSalaryModel.setEsi(emp.getEsi());
					employeeSalaryModel.setGrossSalary(emp.getGrossSalary());
					//employeeSalaryModel.setCratedUserId(" ");
					employeeSalaryModel.setEmployee(employeeRes);
					EmployeeSalaryModelList.add(employeeSalaryModel);
				}
				employeeRes.setEmployeeSalaryModel(EmployeeSalaryModelList);

			}
			employeeRes = employeeRepository.save(employeeRes);
			employeeResp.add(employeeRes);
			log.info("Employee data with ID : " + employeeRes.getEmployeeId() + " updated succesfully");
		}
		return employeeResp;
	}

	@Override
	public void deleteEmployeesById(int[] employeeIds) {
		EmployeeModel employeeRes;
		for (int employee : employeeIds) {
			employeeRes = getValidEmployee(employee);
			if (!Objects.nonNull(employeeRes)) {
				throw new IHealthPharmException(employeeHelper.getNotFoundEmployeeMessage(), HttpStatus.NOT_FOUND);
			}
			employeeRepository.delete(employeeRes);
			log.info("Employee data with ID: " + employeeRes.getEmployeeId() + " deleted succesfully");
		}
	}

	@Override
	public EmployeeModel findLastCreatedEmployeeId() {
		
		return employeeRepository.findLastCreatedEmployeeId();
	}

	/*@Override
	public EmployeeModel findEmployeeCredentialsModel(EmployeeCredentialsModel employeeCredentialsModel) {
		
		return employeeRepository.findEmployeeCredentialsModel(employeeCredentialsModel);
	}*/

}
