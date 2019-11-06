package com.ihealthpharm.mail.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ihealthpharm.mail.service.impl.SendQuotationMailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class SendQuotationMailServiceImpl implements SendQuotationMailService {

	@Autowired
    private JavaMailSender javaMailSender;

	@Override
	public void sendMail() {
		log.info("send mail");
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo("guna.kkit@gmail.com");
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
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
