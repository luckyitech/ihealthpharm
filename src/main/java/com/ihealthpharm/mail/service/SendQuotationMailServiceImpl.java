package com.ihealthpharm.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ihealthpharm.mail.model.MailResponse;
import com.ihealthpharm.mail.model.SendQuotationMailModel;
import com.ihealthpharm.mail.service.impl.SendQuotationMailService;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class SendQuotationMailServiceImpl implements SendQuotationMailService {

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private Configuration config;
	
	@Override
	public void sendQuotationEmail(SendQuotationMailModel mailObj) throws IOException, TemplateException {
		MailResponse response = new MailResponse();
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		

		try {
			
			Template t = config.getTemplate("quotation-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mailObj);
			
			helper.setTo(mailObj.getToEmail());
			helper.setText(html, true);
			helper.setSubject(mailObj.getSubject());
			helper.setFrom(mailObj.getFromEmail());
			
		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : "+e.getMessage());
			response.setStatus(Boolean.FALSE);
		}
		
		javaMailSender.send(message);
	}
	
	@Override
	public void attachFileToMail() {
		log.info("attachFileToMail");
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo("guna.kkit@gmail.com");
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
			helper.addAttachment("test.png", new ClassPathResource("test.png"));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
	}
}
