package com.ihealthpharm.mail.service.impl;

import java.io.IOException;

import javax.mail.MessagingException;

import com.ihealthpharm.mail.model.AttachmentModel;
import com.ihealthpharm.mail.model.SendPurchaseOrderModel;
import com.ihealthpharm.mail.model.SendQuotationMailModel;

import freemarker.template.TemplateException;

public interface SendQuotationMailService {

	public void sendQuotationEmail(SendQuotationMailModel mailObj,AttachmentModel mail) throws IOException, TemplateException, MessagingException;
	public void attachFileToMail();
	
	public void sendPurchaseOrderEmail(SendPurchaseOrderModel mailModel, AttachmentModel mail) throws IOException, TemplateException, MessagingException;
}
