package com.ihealthpharm.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ihealthpharm.commons.BaseDto;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class IHealthPharmAdvice {
	
	@Value("${general.exception.response}")
	protected String generalexceptioMessage;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseDto<Object>> generateException(final Exception exception) {		
		log.error("Exception occurred ", exception);
		return new BaseDto<>(generalexceptioMessage, INTERNAL_SERVER_ERROR).respond();
	}
	
	@ExceptionHandler(IHealthPharmException.class)
	public ResponseEntity<BaseDto<Object>> generateUserDefinedException(final IHealthPharmException userDefinedException) {
		if(!Objects.nonNull(userDefinedException.getException()))
			log.error("Exception occurred ", userDefinedException.getException());
		return new BaseDto<>(userDefinedException.getMessage(), userDefinedException.getStatus()).respond();
	}
	
	

}
