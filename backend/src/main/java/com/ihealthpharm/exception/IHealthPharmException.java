package com.ihealthpharm.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class IHealthPharmException extends RuntimeException {

	private static final long serialVersionUID = -5948408229365475681L;
	
	private String message;
	
	private Exception exception;
	
	private HttpStatus status;
	
	public IHealthPharmException(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}
	
	

}
