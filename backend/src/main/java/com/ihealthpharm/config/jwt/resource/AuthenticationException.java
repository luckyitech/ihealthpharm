package com.ihealthpharm.config.jwt.resource;

public class AuthenticationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1645520880367217211L;

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
