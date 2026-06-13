package com.ihealthpharm.finance.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class BillTypeHelper {



	@Value("${billtype.retrive.response}")
	public String retriveBillTypeMessage;

}
