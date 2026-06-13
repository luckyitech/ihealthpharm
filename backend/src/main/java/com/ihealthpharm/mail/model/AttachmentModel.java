package com.ihealthpharm.mail.model;

import java.util.Date;
import java.util.List;

import javax.mail.Multipart;

import org.springframework.core.io.FileSystemResource;

import lombok.Data;

@Data
public class AttachmentModel {

	private String mailContent;
	 
    private String contentType;
 
    private List<Object> attachments;
    
    private byte[] fileName;
    
    private FileSystemResource file;
    
    private String format;
    
    private Multipart emailContent;

	public Object RecipientType;
 
    public AttachmentModel() {
        //contentType = "text/plain";
        contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }
 
    public String getContentType() {
        return contentType;
    }
 
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public Date getMailSendDate() {
        return new Date();
    }
 
    public String getMailContent() {
        return mailContent;
    }
 
    public void setMailContent(String string) {
        this.mailContent = string;
    }
 
    public List < Object > getAttachments() {
        return attachments;
    }
 
    public void setAttachments(List < Object > attachments) {
        this.attachments = attachments;
    }
	
	public void setText(String format) {
		
		this.format = format;
	}

	public void setContent(Multipart emailContent) {
		this.emailContent = emailContent;
		
	}


}
