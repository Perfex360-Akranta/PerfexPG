package com.akranta.tpm.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.MailDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.MailDaoImpl;
import com.akranta.tpm.service.MailService;

public class Mail {
	private String username ="aprasanth.atpl@gmail.com";
	private String password = "atpl0001";//"kolkata@1";
	private String fromaddress= "aprasanth.atpl@gmail.com";//"PERFEX.360@ITC.IN";
	private String host = "smtp.gmail.com";
	private String port = "465";
	private String toaddress;
	private String mailsubject;
	private String mailtext;
	MailService mailService; 
	MailDao mailDao;
	public Mail(){
		
		host = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SMTP_HOST");
		port = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SMTP_PORT");
		fromaddress = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SMTP_FROM_ADDRESS");
		password = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SMTP_USER_PWD");
		username =UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SMTP_USER_NAME");

		
	}
	public Mail(DBActionTemplate dbActionTemplate) throws Exception{
		List<String> mailInfoList = new ArrayList<String>();
		//mailService = new MailServiceImpl(dbActionTemplate);
		//mailInfoList = mailService.getMailInfo();
		//mailService.getMailInfo(this);
		mailDao = new MailDaoImpl(dbActionTemplate);
		mailDao.getMailInfo(this);
		/*if(mailInfoList.size()>0)
		{
			CommonMessage.debugMsg(mailInfoList.get(0));
			CommonMessage.debugMsg(mailInfoList.get(1));
		    CommonMessage.debugMsg(mailInfoList.get(2));
		    CommonMessage.debugMsg(mailInfoList.get(3));
		    CommonMessage.debugMsg(mailInfoList.get(4));
		   this.username=mailInfoList.get(1);
		   this.password=mailInfoList.get(2);
		   this.toaddress="info.akranta@gmail.com";
		   this.fromaddress=mailInfoList.get(0);
		   this.host=mailInfoList.get(3);
		   this.port=mailInfoList.get(4);
		}	*/	
	}
	public void sendMail(String from,String to,String subject,String text) throws MessagingException{
		
		if (CommonFunctions.isValidKeyId(from)){
			this.fromaddress=from;
		}
		if (CommonFunctions.isValidKeyId(to)){
			this.toaddress=to;
		}
		this.mailsubject=subject;
		this.mailtext=text;	
		
		Properties props = new Properties();
		props.put("mail.smtp.user", fromaddress);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable","false");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.debug", "true");
		//props.setProperty("mail.smtp.ssl.trust", "smtpserver");
		props.put("mail.smtp.ssl.trust", "smtpserver");
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallb", "false");		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		MimeMessage msg = new MimeMessage(session);
		msg.setText(mailtext);
		msg.setSubject(mailsubject);
		msg.setContent(mailtext,"text/html");			
		msg.setFrom(new InternetAddress(fromaddress));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toaddress));
		
		Transport.send(msg);
			//CommonMessage.debugMsg("send Mail send");
	}	
	
	public void sendMailAttachment(String from,String to,String subject,String text,String cc,List<String> fileName) throws MessagingException{
		
		if (CommonFunctions.isValidKeyId(from)){
			this.fromaddress=from;
		}
		if (CommonFunctions.isValidKeyId(to)){
			this.toaddress=to;
		}
		this.mailsubject=subject;
		this.mailtext=text;	
		
		Properties props = new Properties();
		props.put("mail.smtp.user", fromaddress);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable","false");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.debug", "true");
		//props.setProperty("mail.smtp.ssl.trust", "smtpserver");
		props.put("mail.smtp.ssl.trust", "smtpserver");
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallb", "false");		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		MimeMessage msg = new MimeMessage(session);
		msg.setText(mailtext);
		msg.setSubject(mailsubject);
		msg.setContent(mailtext,"text/html");			
		msg.setFrom(new InternetAddress(fromaddress));
		CommonMessage.debugMsg("TO : "+toaddress);
		
		if(toaddress.indexOf(";") > 0)
		{
			String[] toAddressArr = toaddress.split(";");
			InternetAddress[] addressTo = new InternetAddress[toAddressArr.length];

			for (int i = 0; i < toAddressArr.length; i++)
			{
			    addressTo[i] = new InternetAddress(toAddressArr[i]);
			}
			msg.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);
		}
		else
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toaddress));

		 
		//msg.addRecipients(Message.RecipientType.TO, toaddress);
		
		if (CommonFunctions.isValidKeyId(cc))
		{
			if(cc.indexOf(";") > 0)
			{
				String[] ccArr = cc.split(";");
				InternetAddress[] addressTo = new InternetAddress[ccArr.length];

				for (int i = 0; i < ccArr.length; i++)
				{
				    addressTo[i] = new InternetAddress(ccArr[i]);
				}
				msg.setRecipients(javax.mail.Message.RecipientType.CC, addressTo);
			}
			else
				msg.addRecipients(Message.RecipientType.CC, cc);
			
		}
		CommonMessage.debugMsg("Mail Size A : "+fileName.size());
		if (fileName.size()>0)
		{
			MimeBodyPart msgBody = new MimeBodyPart();		     
			msgBody.setContent(mailtext,"text/html");
			Multipart multipartMsg = new MimeMultipart();
			multipartMsg.addBodyPart(msgBody);
			
			for(int i=0;i<fileName.size();i++)
			{
				CommonMessage.debugMsg("Mail Attachment : "+fileName.get(i));
				
				FileDataSource fileDs = new FileDataSource(fileName.get(i));			
			
			    
			    MimeBodyPart msgAttach = new MimeBodyPart();		      
			    msgAttach.setDataHandler(new DataHandler(fileDs));
			    msgAttach.setFileName(fileDs.getName());	   
			    multipartMsg.addBodyPart(msgAttach);
				
			   
			}
			msg.setContent(multipartMsg);		      	      
		    msg.setSentDate(new Date());
			
		}
	    
		
		Transport.send(msg);
			//CommonMessage.debugMsg("send Mail send");
	}	
	public void sendMailWithAttachment(String from,String to,String subject,String text,String fileName) throws MessagingException{
		
		//CommonMessage.debugMsg("send Mail Call");
		String fileName1=null;
		fileName1="E:/cosykart.pdf";
		if (CommonFunctions.isValidKeyId(to)){
			this.toaddress=to;
		}
		this.mailsubject=subject;
		this.mailtext=text;		
		Properties props = new Properties();
		props.put("mail.smtp.user", fromaddress);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable","false");
		props.put("mail.smtp.auth", "true");		
		props.put("mail.smtp.ssl.trust", "smtpserver");
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallb", "false");		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		MimeMessage msg = new MimeMessage(session);			
		msg.setSubject(mailsubject);			
		msg.setFrom(new InternetAddress(fromaddress));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toaddress));
		
		FileDataSource fileDs = new FileDataSource(fileName1);
		MimeBodyPart msgBody = new MimeBodyPart();		     
	    msgBody.setContent(mailtext,"text/html");
	    MimeBodyPart msgAttach = new MimeBodyPart();		      
	    msgAttach.setDataHandler(new DataHandler(fileDs));
	    msgAttach.setFileName(fileDs.getName());		      
	    Multipart multipartMsg = new MimeMultipart();
	    multipartMsg.addBodyPart(msgBody);
	    multipartMsg.addBodyPart(msgAttach);		     
	    msg.setContent(multipartMsg);		      	      
	    msg.setSentDate(new Date());
	    Transport.send(msg);
		      
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFromaddress() {
		return fromaddress;
	}
	public void setFromaddress(String fromaddress) {
		this.fromaddress = fromaddress;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getToaddress() {
		return toaddress;
	}
	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}
	public String getMailsubject() {
		return mailsubject;
	}
	public void setMailsubject(String mailsubject) {
		this.mailsubject = mailsubject;
	}
	public String getMailtext() {
		return mailtext;
	}
	public void setMailtext(String mailtext) {
		this.mailtext = mailtext;
	}
	
	

}
