package com.ihealthpharm.masters.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class MembershipHelper {


	@Value("${membership.save.response}")
	public String saveMembershipMessage;
	
	@Value("${membership.update.response}")
	public String updateMembershipMessage;
	
	@Value("${membership.delete.response}")
	public String deleteMembershipMessage;
	
	@Value("${membership.retrieve.response}")
	public String retrieveMembershipMessage;
	
	@Value("${membership.not.found.response}")
	public String notFoundMembershipMessage;
	
	
}