package com.ihealthpharm.mail.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ihealthpharm.mail.model.AttachmentModel;
import com.ihealthpharm.mail.model.MailResponse;
import com.ihealthpharm.mail.model.SendPurchaseOrderModel;
import com.ihealthpharm.mail.model.SendQuotationMailModel;
import com.ihealthpharm.mail.service.impl.SendQuotationMailService;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ObjectUtils;

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
	public void sendQuotationEmail(SendQuotationMailModel mailObj,AttachmentModel mail) throws IOException, TemplateException, MessagingException {
		MailResponse response = new MailResponse();

		MimeMessage message = javaMailSender.createMimeMessage();
		//SimpleMailMessage message = new SimpleMailMessage(); 


		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		for(Object f:mail.getAttachments())
		{
			File resource = (File) f;
			FileSystemResource file = new FileSystemResource(resource);
			// add attachment
			helper.addAttachment(file.getFilename(), file);
		}


		try {

			Template t = config.getTemplate("quotation-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mailObj);

			System.out.println(mailObj);

			helper.setTo(mailObj.getToEmail());
			if(Objects.nonNull(mailObj.getBccEmail()) && !ObjectUtils.isEmpty(mailObj.getBccEmail())) {
				helper.setBcc(mailObj.getBccEmail());
			}
			helper.setText(html, true);
			helper.setSubject(mailObj.getSubject());
			helper.setFrom(new InternetAddress(mailObj.getFromEmail()));

		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : "+e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		javaMailSender.send(message);
	}



	public void sendPurchaseOrderEmail(SendPurchaseOrderModel mailObj,AttachmentModel mail) throws  MessagingException, IOException, TemplateException {
		MailResponse response = new MailResponse();

		MimeMessage message = javaMailSender.createMimeMessage();


		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		for(Object f:mail.getAttachments())
		{
			File resource = (File) f;
			FileSystemResource file = new FileSystemResource(resource);
			// add attachment
			helper.addAttachment(file.getFilename(), file);
		}


		try {

			Template t = config.getTemplate("purchase-order-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mailObj);

			System.out.println(mailObj);

			helper.setTo(mailObj.getToEmail());
			if(Objects.nonNull(mailObj.getBccEmail()) && !ObjectUtils.isEmpty(mailObj.getBccEmail())) {
				helper.setBcc(mailObj.getBccEmail());
			}
			helper.setText(html,true);
			helper.setSubject(mailObj.getSubject());
			helper.setFrom(mailObj.getFromEmail());

		} catch (MessagingException e) {
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
			helper.addAttachment("REQUEST FOR QUOTATION (1).xlsx", new ClassPathResource("REQUEST FOR QUOTATION (1).xlsx"));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
	}
}
