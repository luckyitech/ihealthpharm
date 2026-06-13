package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class CreditDaysHelper {

	@Value("${creditdays.retrieve.response}")
	public String retrieveCreditDaysMessage;
}
