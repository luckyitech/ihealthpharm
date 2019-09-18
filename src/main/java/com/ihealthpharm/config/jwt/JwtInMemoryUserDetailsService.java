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

import com.ihealthpharm.masters.model.UsersModel;
import com.ihealthpharm.masters.service.UserService;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	static {
		inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
		inMemoryUserList.add(new JwtUserDetails(2L, "ranga",
				"$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm", "ROLE_USER_2"));
		
		//$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersModel usersList = userService.findUserByUserName(username);
		List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		inMemoryUserList.add(new JwtUserDetails(usersList.getUserId(),usersList.getUserName(),encoder.encode(usersList.getPassword()),"ROLE_USER_2"));
		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream().filter(user -> user.getUsername().equals(username)).findFirst();

		System.out.println(usersList.toString());
		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

}
