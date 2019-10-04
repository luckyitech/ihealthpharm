package com.ihealthpharm.masters.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ihealthpharm.masters.model.CountryModel;
import com.ihealthpharm.masters.model.EmployeeTypeModel;
import com.ihealthpharm.masters.model.StateModel;

import lombok.Data;

@Data
public class EmployeeDTO {

	private Integer employeeId;

	private String employeeTypeFullTimeOrPartTime;

	private String phoneNumber;

	private String employeeCode;

	private char[] title;

	private String firstName;

	private String middleName;

	private String lastName;

	private char genderCode;

	private double salary;

	private char activeS;

	private Date dateOfBirth;

	private Date dateOfJoining;

	private String emailId;

	private String designation;

	private String bipgraphyDesc;

	private String addressLine1;

	private String addressLine2;

	//private String addressLine3;

	private String city;

	private StateModel state;

	private CountryModel country;

	private String bloodGroup;

	private char[] nightShifts;

	private String postalCode;

	private MultipartFile profileImage;

	private Integer pharmacyId;

	private String kraPin;
	
	private MultipartFile identificationDocument;
	
	private String nhifNumber;
	
	private MultipartFile policeGoodConductCertificate;
	
	private MultipartFile resume;
	
	private MultipartFile signedContract;
	
	private String landlineNumber;
	
	private String postBox;
	
	private String identificationNumber;
	
	private String nssfNumber;
	
	private EmployeeTypeModel employeeTypeModel;
	
	private String employeeTypeFullTimOrPartTime;
	
	private List<EmployeeProfMembershipDto> employeeProfMembershipDto;

	private List<EmployeeHonorDTO> employeeHonorDto;

	private List<EmployeeInterestDTO> employeeInterestDto;

	private List<EmployeePublicationDTO> employeePublicationDto;

	private List<EmployeeEducationDTO> employeeEducationDto;

	private List<EmploymentHistoryDTO> employeeHistoryDto;

	private List<EmployeeSalaryDTO> employeeSalaryDto;
}
