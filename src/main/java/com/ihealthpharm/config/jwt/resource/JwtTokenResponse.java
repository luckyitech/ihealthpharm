package com.ihealthpharm.config.jwt.resource;

import java.io.Serializable;
import java.util.List;

import com.ihealthpharm.masters.model.EmployeeAccessModel;

import lombok.Data;
@Data
public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = 8317676219297719109L;

	private final String token;

	private  Integer id;
	
	private List<EmployeeAccessModel> permissions;

	public String getToken() {
		return this.token;
	}

	
	public JwtTokenResponse(String token) {
		this.token = token;
	}

	public JwtTokenResponse(String token, Integer id, List<EmployeeAccessModel> permissions) {
		super();
		this.token = token;
		this.id = id;
		this.permissions = permissions;
	}
	
}