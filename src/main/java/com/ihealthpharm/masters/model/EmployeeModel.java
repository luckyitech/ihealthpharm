package com.ihealthpharm.masters.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "employee")
@JsonIgnoreProperties("inspection")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
@EqualsAndHashCode(of="employeeId",callSuper=false)
public class EmployeeModel extends AuditModel implements Serializable{

	private static final long serialVersionUID = -3990973417396713887L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID" ,length=11)
	private Integer employeeId;
	
	@Column(name = "EMPLOYEE_TYPE_FULL_PART_TIME")
	private String employeeTypeFullTimOrPartTime;
	
		
	@Column( name = "PHONE_NBR", length=20)
	private String phoneNumber;
	
	@Column( name = "EMPLOYEE_CD", length=20)
	private String employeeCode;
	
	@Column( name = "TITLE", length=5)
	private char[] title;
	
	@Column( name = "FIRST_NM",length=50)
	private String firstName;
	
	@Column( name = "MIDDLE_NM",length=50)
	private String middleName;
	
	@Column( name = "LAST_NM",length=50)
	private String lastName;
	
	@Column( name = "GENDER_CD",length=1)
	private Character genderCode;
	
	@Column( name = "SALARY")
	private Double salary;
	
	@Column( name = "ACTIVE_S",length=1)
	private Character activeS;
	
	@Column( name = "DOB_DT")
	private String dateOfBirth;
	
	@Column( name = "DATE_OF_JOINING")
	private String dateOfJoining;
	
	@Column( name = "EMAIL_ID",length=50)
	private String emailId;
	
	@Column( name = "DESIGNATION_NM",length=250)
	private String designation;
	
	@Column( name = "BIOGRAPHY_DESC",length=5000)
	private String bipgraphyDesc;
	
	@Column( name = "ADDRESS_LINE1",length=250)
	private String addressLine1;
	
	@Column( name = "ADDRESS_LINE2",length=250)
	private String addressLine2;
	
	
	@Column(name = "CITY_NM",length=50)
	private String city;
	
	@OneToOne
	@JoinColumn(name="PROVINCES_ID")
	private StateModel state;
	
	@OneToOne
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel country;
	
	@Column( name = "BLOOD_GROUP",length=20)
	private String bloodGroup;
	
	@Column(name = "ARE_YOU_WORK_ON_SHIFTS",length=2)
	private char[] nightShifts;
	
	@Column( name = "POSTAL_CD" ,length=20)
	private String postalCode;
	
	@Lob
	@Column( name = "PROFILE_IMAGE")
	private byte[] profileImage;
	
	@Column( name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	@Column( name = "PHARMACY_ID",length=11)
	private Integer pharmacyId;
	
	@Column( name = "KRA_PIN",length=20)
	private String kraPin;
	
	@Lob
	@Column( name = "IDENTIFICATION_DOCUMENT")
	private byte[] identificationDocument;
	
	@Column( name = "NHIF_NO",length=20)
	private String nhifNumber;
	
	@Lob
	@Column( name = "POLICE_GOOD_CONDUCT_CERTIFICATE")
	private byte[] policeGoodConductCertificate;
	
	@Lob
	@Column( name = "RESUME")
	private byte[] resume;
	
	@Lob
	@Column( name = "SIGNED_CONTRACT")
	private byte[] signedContract;
	
	@Column( name = "LANDLINE_NUMBER",length=20)
	private String landlineNumber;
	
	@Column( name = "POST_BOX",length=20)
	private String postBox;
	
	@Column( name = "IDENTIFICATION_NO",length=20)
	private String identificationNumber;
	
	@Column( name = "NSSF_NO",length=20)
	private String nssfNumber;
	
	@OneToOne
	@JoinColumn(name="EMPLOYEE_TYPE_LOOKUP_ID")
	private EmployeeTypeModel employeeTypeModel;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeeProfMembershipModel> employeeProfMembershipModel;
	*/
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeeHonorModel> employeeHonorModel;
	*/
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeeInterestModel> employeeInterestModel;
	*/
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeePublicationModel> employeePublicationModel;
	*/
	/*@OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeeEducationModel> employeeEducationModel;
	*/
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmploymentHistoryModel> employeeHistoryModel;*/
	
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeeSalaryModel> employeeSalaryModel;*/
	
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeeCredentialsModel> employeeCredentialsModel;*/
	
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeePharmacyRoleModel> employeePharmacyRoleModel;*/
	
	public void setDateOfBirth(Date birthDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateBirth=simpleDateFormat.format(birthDate);  
		this.dateOfBirth = dateBirth;
    }
	
	public void setDateOfJoining(Date dateOfJoin)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateOfJoins=simpleDateFormat.format(dateOfJoin);  
		this.dateOfJoining = dateOfJoins;
	}
	
	
}
