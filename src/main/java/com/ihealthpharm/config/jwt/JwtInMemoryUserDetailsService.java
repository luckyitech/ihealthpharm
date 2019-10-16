package com.ihealthpharm.config.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ihealthpharm.masters.model.EmployeeCredentialsModel;
import com.ihealthpharm.masters.model.EmployeePharmacyRoleModel;
import com.ihealthpharm.masters.service.EmployeeCredentialsService;
import com.ihealthpharm.masters.service.EmployeePharmacyRoleService;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	EmployeeCredentialsService employeeCredentialsService;
	
	@Autowired
	EmployeePharmacyRoleService employeePharmacyRoleService;
	
	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	static {
		inMemoryUserList.add(new JwtUserDetails(1, "in28minutes",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
		inMemoryUserList.add(new JwtUserDetails(2, "ranga",
				"$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm", "ROLE_USER_2"));
		
		//$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeCredentialsModel usersList = employeeCredentialsService.findEmployeeCredentialsByUserName(username);
		List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
		List<EmployeePharmacyRoleModel> employeePharmacyRoleModel = employeePharmacyRoleService.findEmployeePharmacyRoleDataByEmployeeId(usersList.getEmployee());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		inMemoryUserList.add(
				new JwtUserDetails(
						usersList.getEmployee().getEmployeeId(),
						usersList.getUserName(),
						encoder.encode(usersList.getCurrentPassword()),
						employeePharmacyRoleModel.get(0).getPharmacyRolesModel().getRoleNm()));
		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream().filter(user -> user.getUsername().equals(username)).findFirst();
		
		//System.out.println(usersList.toString());
		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

}
