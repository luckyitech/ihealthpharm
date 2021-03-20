package com.ihealthpharm.mail.service.impl;

import java.io.IOException;

import com.ihealthpharm.mail.model.SendQuotationMailModel;

import freemarker.template.TemplateException;

public interface SendQuotationMailService {

	public void sendQuotationEmail(SendQuotationMailModel mailObj) throws IOException, TemplateException;
	public void attachFileToMail();
}
