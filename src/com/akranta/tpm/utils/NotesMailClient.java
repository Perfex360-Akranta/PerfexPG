package com.akranta.tpm.utils;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.EmbeddedObject;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.RichTextItem;


//import lotus.domino.Session;
import javax.mail.PasswordAuthentication;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;

import de.bea.domingo.http.NotesHttpException;

public class NotesMailClient  {
	
	private String dominoServerIP = "";
	private String portNo = "";
	private String UserName = "";
	private String pass="";
//	private String dominoPassword = "";
	
	/*private String dominoServer = "";
	private String dominoMailbox = "";
	private String dominoUsername = "";
	private String dominoPassword = "";*/
	private final String APPLICATION_CONFIG_FILE = "com.akranta.tpm.resources.ApplicationConfig";
	
	private static final Logger MailLog = Logger.getLogger("MailLog");
	public String decryptPassword(String password, int userPin)	throws BusinessApplicationExceptions {
		int tempUserPin =  getFormatedUserPin(userPin);

		String decriptPass ="";
		for(int i=0; i< password.length(); i++ ){
			int diff = password.charAt(i) - tempUserPin;
			decriptPass  +=  (diff) > 32 ? (char)(diff) : (char)(127-((32-diff) + tempUserPin));
		}	
		return decriptPass;
	}
	private int getFormatedUserPin(int userPin) throws BusinessApplicationExceptions 
	{
		NumberFormat formatter = new DecimalFormat("0000");
		String pin = formatter.format(userPin); 
		
		
		if( Integer.parseInt(pin) == 0)
			throw new BusinessApplicationExceptions("tpmusr-000005"); // invalid user pin
		
		int digit = 0, sum = 0;

		for( int i = 0 ; i < pin.length(); i++ )
		{	
			digit = (int)pin.charAt(i) ;
			sum +=  digit;
		}
		String sumStr = Integer.toString(sum);

		do{
			sum = 0;
			for( int i = 0; i < sumStr.length(); i++ )
			{
				digit = (int)(sumStr.charAt(i)-'0');
				sum += digit;
			}
			sumStr = Integer.toString(sum);
		}while( sum > 9 );	
		return sum; 
	}

	
	public NotesMailClient(  )
	{
		/*dominoServer = UIUtils.getPropertyValue(APPLICATION_CONFIG_FILE, "LOTUS_NOTES_SERVER_NAME");
		dominoMailbox = UIUtils.getPropertyValue(APPLICATION_CONFIG_FILE, "LOTUS_NOTES_DB_FILENAME");
		dominoUsername = UIUtils.getPropertyValue(APPLICATION_CONFIG_FILE, "LOTUS_NOTES_USERNAME");
		
	//	String encryptedpassword = UIUtils.getPropertyValue(APPLICATION_CONFIG_FILE, "LOTUS_NOTES_PASSWORD");
	//	String decryptedpassword=null;
		*/
		dominoServerIP=UIUtils.getPropertyValue(APPLICATION_CONFIG_FILE, "SMTP_HOST");
		portNo = UIUtils.getPropertyValue(APPLICATION_CONFIG_FILE, "SMTP_PORT");
		UserName = UIUtils.getPropertyValue(APPLICATION_CONFIG_FILE, "SMTP_FROMADDRESS");
		pass=UIUtils.getPropertyValue(APPLICATION_CONFIG_FILE, "SMTP_PWD");
		/*try {
		 decryptedpassword=decryptPassword(encryptedpassword,1234);
		} catch (BusinessApplicationExceptions e) {
			
			e.printStackTrace();
		}*/
		
		//dominoPassword=decryptedpassword;
		CommonMessage.debugMsg("dominoServerIP  "+dominoServerIP);
		CommonMessage.debugMsg("Port No  "+portNo);
		CommonMessage.debugMsg("User Nmae  "+UserName);
		
		CommonMessage.debugMsg("Initializing Notes Mail Client..... in May Month");

		MailLog.info("Initializing Notes Mail Client.....");
		MailLog.info(" dominoUsername " + UserName );
		MailLog.info(" dominoMailbox " + portNo );
		//MailLog.info(" dominoPassword " + dominoPassword );
	}
	
	public void send(String sendTo, String copyTo, String subject, String content,String attachmentFile) throws Exception
	{

		CommonMessage.debugMsg("Initializing Notes Mail Client...Send method..  " +content.substring(0,4));

	//	public void sendMailWithAttachment(String from,InternetAddress[] toAdress,String subject,String text,String fileName) throws MessagingException{
		try{	
			//CommonMessage.debugMsg("send Mail Call");
			String fileName1=null;
			fileName1="E:/cosykart.pdf";
			/*
			if (CommonFunctions.isValidKeyId(to[])){
				this.toaddress=to;
			} */
			String mailtext="";
			String mailsubject=subject;

//			if(content.substring(0,4).equals("http")){
//				 mailtext="\r\n"+""
//							+ "    Before Clicking The Link Please Find the Attached file.</BR> If you want to Approve then only Click on Link \r\n ";
//				 mailtext +=content;
//			}
//			else{
//				
//			
////					mailtext="\r\n Dear Sir,</Br>";
////					mailtext=" Following Why Why Is created in Perfex System </BR>";
////					mailtext+=content.replace("</BR>", "     \r\n");
//				mailtext="<br> Dear Sir,<br>";
//				//mailtext=" Following Why Why Is created in Perfex System <br>";
//				mailtext+=content.replace("</BR>", "     <br>");
//				
//				CommonMessage.debugMsg(" Mail Contents    "+mailtext);
//				
//				
//		}
			
			if(content != null && content.startsWith("http")){ 
				 mailtext = "<html><body>"
				            + "Before Clicking The Link Please Find the Attached file.<br>"
				            + "If you want to Approve then only Click on Link<br><br>"
				            + content
				            + "</body></html>";
			}
			else{
				
			
//					mailtext="\r\n Dear Sir,</Br>";
//					mailtext=" Following Why Why Is created in Perfex System </BR>";
//					mailtext+=content.replace("</BR>", "     \r\n");
				mailtext="<br> Dear Sir,<br>";
				//mailtext=" Following Why Why Is created in Perfex System <br>";
				mailtext+=content.replace("</BR>", "     <br>");
				 mailtext = "<html><body> <br> Dear Sir,<br>"
				            + content.replace("\r\n", "<br>").replace("\n", "<br>")
				            + "</body></html>";
				CommonMessage.debugMsg(" Mail Contents    "+mailtext);
				
				
				
		}
			Properties props = new Properties();
			
			props.put("mail.smtp.host", dominoServerIP);
			props.put("mail.smtp.port", portNo);
			// for development
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			props.put("mail.smtp.auth", "true");
			
			//Session session = Session.getInstance(props,null);
			// for prodection
//			props.put("mail.smtp.ssl.enable", "false");
//			props.put("mail.smtp.auth", "false");
			
			Session session = Session.getInstance(props,//getDefaultInstance(props,
					 new javax.mail.Authenticator() {
					  protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(UserName,pass);
					   }
					});

			CommonMessage.debugMsg(attachmentFile+" JKKLK  before the mime message..Send method..  " +mailsubject+"  " +sendTo+" "+mailtext+"  "+ content);

			
			MimeMessage msg = new MimeMessage(session);			
			msg.setSubject(mailsubject);			
			msg.setFrom(new InternetAddress(UserName));
			msg.addRecipients(Message.RecipientType.TO,sendTo );//new InternetAddress(toaddress));
		
			CommonMessage.debugMsg("   Above the file attachment   ");

			FileDataSource  fileDs = null;//new FileDataSource( attachmentFile);
			
			MimeBodyPart msgBody = new MimeBodyPart();		     
		    msgBody.setContent(mailtext,"text/html");
		    
		    MimeBodyPart msgAttach = new MimeBodyPart();
		    
//			for (int i=0; i<attachmentFile.size();i++) {
//				String attachFile = attachmentFile.get(i);
//				if( UIUtils.isValidKeyId(attachFile )){
//					
//					  fileDs = new FileDataSource( attachFile);
//					 msgAttach.setDataHandler(new DataHandler(fileDs));
//					    msgAttach.setFileName(fileDs.getName());
//				    
//				}
//			}
				
CommonMessage.debugMsg("  msgAttach  msgAttach  "+msgAttach.getFileName());
		    Multipart multipartMsg = new MimeMultipart();
		    
		    if(msgAttach.getFileName()==null ||(msgAttach.getFileName()).equals(null)){
		    	CommonMessage.debugMsg( " in side if");
		    multipartMsg.addBodyPart(msgBody);
		    }
		    else{
		    	CommonMessage.debugMsg( " in side Else");
		    	CommonMessage.debugMsg("msgAttach   "+msgAttach.getFileName());
			    multipartMsg.addBodyPart(msgBody);
			    multipartMsg.addBodyPart(msgAttach);
		    }
		    msg.setContent(multipartMsg);  	      
		 
		   
		    CommonMessage.debugMsg("before sending thle mail ");
		   // if(fileDs.getName()==null || fileDs.getName()=="null"){
		    Transport.send(msg);
		    
		    CommonMessage.debugMsg("After sending the mail");

			      
		}
	catch(Exception e){
		e.printStackTrace();
		throw e;
	}
		
	}	
	
	
	public void send(String sendTo, String copyTo, String subject, String content,List<String > attachmentFile) throws Exception
	{

		CommonMessage.debugMsg("Initializing Notes Mail Client...Send method..  " +content.substring(0,4));

	//	public void sendMailWithAttachment(String from,InternetAddress[] toAdress,String subject,String text,String fileName) throws MessagingException{
		try{	
			//CommonMessage.debugMsg("send Mail Call");
			String fileName1=null;
			fileName1="E:/cosykart.pdf";
			/*
			if (CommonFunctions.isValidKeyId(to[])){
				this.toaddress=to;
			} */
			String mailtext="";
			String mailsubject=subject;

			if(content != null && content.startsWith("http")){ 
				 mailtext = "<html><body>"
				            + "Before Clicking The Link Please Find the Attached file.<br>"
				            + "If you want to Approve then only Click on Link<br><br>"
				            + content
				            + "</body></html>";
			}
			else{
				
			
//					mailtext="\r\n Dear Sir,</Br>";
//					mailtext=" Following Why Why Is created in Perfex System </BR>";
//					mailtext+=content.replace("</BR>", "     \r\n");
				mailtext="<br> Dear Sir,<br>";
				//mailtext=" Following Why Why Is created in Perfex System <br>";
				mailtext+=content.replace("</BR>", "     <br>");
				 mailtext = "<html><body> <br> Dear Sir,<br>"
				            + content.replace("\r\n", "<br>").replace("\n", "<br>")
				            + "</body></html>";
				CommonMessage.debugMsg(" Mail Contents    "+mailtext);
				
				
				
		}
			Properties props = new Properties();
			
			props.put("mail.smtp.host", dominoServerIP);
			props.put("mail.smtp.port", portNo);
			// for development
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			props.put("mail.smtp.auth", "true");
			
			//Session session = Session.getInstance(props,null);
			// for prodection
//			props.put("mail.smtp.ssl.enable", "false");
//			props.put("mail.smtp.auth", "false");
			
			Session session = Session.getInstance(props,//getDefaultInstance(props,
					 new javax.mail.Authenticator() {
					  protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(UserName,pass);
					   }
					});

			CommonMessage.debugMsg(attachmentFile+" JKKLK  before the mime message..Send method..  " +mailsubject+"  " +sendTo+" "+mailtext+"  "+ content);

			
			MimeMessage msg = new MimeMessage(session);			
			msg.setSubject(mailsubject);			
			msg.setFrom(new InternetAddress(UserName));
			msg.addRecipients(Message.RecipientType.TO,sendTo );//new InternetAddress(toaddress));
		
			CommonMessage.debugMsg("   Above the file attachment   ");

			FileDataSource  fileDs = null;//new FileDataSource( attachmentFile);
			
			MimeBodyPart msgBody = new MimeBodyPart();		     
		    msgBody.setContent(mailtext,"text/html");
		    
		    MimeBodyPart msgAttach = new MimeBodyPart();
		    
			for (int i=0; i<attachmentFile.size();i++) {
				String attachFile = attachmentFile.get(i);
				if( UIUtils.isValidKeyId(attachFile )){
					
					  fileDs = new FileDataSource( attachFile);
					 msgAttach.setDataHandler(new DataHandler(fileDs));
					    msgAttach.setFileName(fileDs.getName());
				    
				}
			}
				
CommonMessage.debugMsg("  msgAttach  msgAttach  "+msgAttach.getFileName());
		    Multipart multipartMsg = new MimeMultipart();
		    
		    if(msgAttach.getFileName()==null ||(msgAttach.getFileName()).equals(null)){
		    	CommonMessage.debugMsg( " in side if");
		    multipartMsg.addBodyPart(msgBody);
		    }
		    else{
		    	CommonMessage.debugMsg( " in side Else");
		    	CommonMessage.debugMsg("msgAttach   "+msgAttach.getFileName());
			    multipartMsg.addBodyPart(msgBody);
			    multipartMsg.addBodyPart(msgAttach);
		    }
		    msg.setContent(multipartMsg);  	      
		 
		   
		    CommonMessage.debugMsg("before sending thle mail ");
		   // if(fileDs.getName()==null || fileDs.getName()=="null"){
		    Transport.send(msg);
		    
		    CommonMessage.debugMsg("After sending the mail");

			      
		}
	catch(Exception e){
		e.printStackTrace();
		throw e;
	}
		
	}	
	
	
	/*public void send(String sendTo, String copyTo, String subject, String content,Workbook attachmentFile) throws Exception
	{

		CommonMessage.debugMsg("Initializing Notes Mail Client...Send method..  " +content);

	//	public void sendMailWithAttachment(String from,InternetAddress[] toAdress,String subject,String text,String fileName) throws MessagingException{
		try{	
			//CommonMessage.debugMsg("send Mail Call");
			String fileName1=null;
			fileName1="E:/cosykart.pdf";
			
			if (CommonFunctions.isValidKeyId(to[])){
				this.toaddress=to;
			} 
			String mailsubject=subject;
			String mailtext=content;		
			Properties props = new Properties();
			
			props.put("mail.smtp.host", dominoServerIP);
			props.put("mail.smtp.port", portNo);
		
			Session session = Session.getInstance(props,null);

			CommonMessage.debugMsg(attachmentFile+"before the mime message..Send method.." +mailsubject+"  " +sendTo+" "+mailtext+"  "+ content);

			
			MimeMessage msg = new MimeMessage(session);			
			msg.setSubject(mailsubject);			
			msg.setFrom(new InternetAddress(UserName));
			msg.addRecipients(Message.RecipientType.TO,sendTo );//new InternetAddress(toaddress));
		
			CommonMessage.debugMsg("Above the file attachment");

			FileDataSource  fileDs = null;//new FileDataSource( attachmentFile);
			
			MimeBodyPart msgBody = new MimeBodyPart();		     
		    msgBody.setContent(mailtext,"text/html");
		    
		    MimeBodyPart msgAttach = new MimeBodyPart();
		    
			for (int i=0; i<attachmentFile.size();i++) {
				String attachFile = attachmentFile.get(i);
				if( UIUtils.isValidKeyId(attachFile )){
					
					  fileDs = new FileDataSource( attachFile);
					 msgAttach.setDataHandler(new DataHandler(fileDs));
					    msgAttach.setFileName(fileDs.getName());
				    
				}
			}
				
CommonMessage.debugMsg("msgAttach   "+msgAttach.getFileName());
		    Multipart multipartMsg = new MimeMultipart();
		    
		    if(msgAttach.getFileName()==null ||(msgAttach.getFileName()).equals(null)){
		    	CommonMessage.debugMsg( " in side if");
		    multipartMsg.addBodyPart(msgBody);
		    }
		    else{
		    	CommonMessage.debugMsg( " in side Else");
		    	CommonMessage.debugMsg("msgAttach   "+msgAttach.getFileName());
			    multipartMsg.addBodyPart(msgBody);
			    multipartMsg.addBodyPart(msgAttach);
		    }
		    msg.setContent(multipartMsg);  	      
		 
		   
		    CommonMessage.debugMsg("before sending thle mail ");
		   // if(fileDs.getName()==null || fileDs.getName()=="null"){
		    Transport.send(msg);
		    
		    CommonMessage.debugMsg("After sending the mail");

			      
		}
	catch(Exception e){
		e.printStackTrace();
		throw e;
	}
		
	}
	
	*/
	
}
		
		
		/*
		//String host="ndls35", user="", pwd="";
		try
		{
			//CommonMessage.debugMsg(" path " +  System.getProperty("java.library.path"));
			MailLog.info(" Before sinitThread " );
			NotesThread.sinitThread();
			Session session = null;
			/*if( "TEST1".equalsIgnoreCase(subject))	
				s = NotesFactory.createSessionWithFullAccess();
			else if(  "TEST2".equalsIgnoreCase(subject))
			*/	
		/*	MailLog.info(" Creating Notes factory Session  " );
			session = NotesFactory.createSessionWithFullAccess(dominoPassword);
			/*else if( "TEST3".equalsIgnoreCase(subject)  ){
				s = NotesFactory.createSessionWithFullAccess();
				s.createRegistration().switchToID(dominoUsername, dominoPassword); 
			}
			else if( "TEST4".equalsIgnoreCase(subject)  ){
				s =  NotesFactory.createSession(); 
				 
			}*/
			
			//CommonMessage.debugMsg("USername: "+s.getUserName());
			//CommonMessage.debugMsg("HTTP URL: "+s.getHttpURL());
			//CommonMessage.debugMsg("Name: "+s.getServerName());
			//CommonMessage.debugMsg("URL: "+s.getURL());
			//To bypass Readers fields restrictions
			//Session s = NotesFactory.createSessionWithFullAccess();
			
			//String p = s.getPlatform();
		/*	MailLog.info(" getting Domino database..  " + dominoMailbox );
			
			Database database = session.getDatabase(dominoServer, dominoMailbox);
			
			
			//View view = database.getView("($Inbox)");
			MailLog.info(" creating document.... " );
			Document doc = database.createDocument();
			
			MailLog.info("  document created! " );
			//RichTextParagraphStyle stream = s.createRichTextParagraphStyle();
			//AgentContext agentContext = session.getAgentContext();
		
			doc.setSaveMessageOnSend(true);
			MailLog.info("  set save flag true " );
			// doc.replaceItemValue("Subject", "Attachment");

			if( UIUtils.isValidKeyId(attachmentFile )){
				MailLog.info(" Attaching.. " + attachmentFile);
			    RichTextItem attachedfile = doc.createRichTextItem("Attachment");
				//RichTextItem attachedfile = (RichTextItem) doc.createRichTextItem("body");
			    attachedfile.embedObject(EmbeddedObject.EMBED_ATTACHMENT, null, attachmentFile, "attachments");
			}
			
			//changed on 30-apr-2015 by babu
			for (int i=0; i<attachmentFile.size();i++) {
				String attachFile = attachmentFile.get(i);
				if( UIUtils.isValidKeyId(attachFile )){
					CommonMessage.debugMsg("attachFile="+i+"="+attachFile);
					MailLog.info(" Attaching.. " + attachFile);
				    RichTextItem attachedfile = doc.createRichTextItem("Attachment");
					//RichTextItem attachedfile = (RichTextItem) doc.createRichTextItem("body");
				    attachedfile.embedObject(EmbeddedObject.EMBED_ATTACHMENT, null, attachFile, "attachments");
				}
			}
			
			//doc.createRichTextItem("C:\\lotus\\notes\\data\\names.nsf");
		
			StringBuilder bodyContent = new StringBuilder();
			bodyContent.append("<table> <tr> <td><strong>Your User ID has been created for login to the Call Volume Services Application !!</strong></td>");
			bodyContent.append("<table> <tr> <td><strong>Your User ID has been created for login to the Call Volume Services Application !!</strong></td>");
			bodyContent.append("</tr>");
			bodyContent.append("<tr><td>Below are the details:</td></tr>");
			bodyContent.append("<br>");
			bodyContent.append("<tr><td>----------------------------------------------------------------------------------------------------------</td></tr><br>");
			bodyContent.append("<tr>");
			bodyContent.append("<td><strong>User ID: </strong></td></tr>");
			bodyContent.append("<br>");
			bodyContent.append("<tr><td><strong>One Time Password: </strong></td></tr>");
			bodyContent.append("<br>");
			/*bodyContent.append("<tr><td><strong>Region: </strong>" +region+"</td></tr>");
			bodyContent.append("<br>");
			bodyContent.append("<tr><td><strong>Hub: </strong>"+hubName+"</td></tr>");
			bodyContent.append("<br>");
			bodyContent.append("<tr><td>Please Login in the application and change your password</td></tr>");
			bodyContent.append("<br>");
			bodyContent.append("<tr><td><strong>Link to Application: http://localhost:8080/abcdef/</strong></td></tr></table>");
			bodyContent.append("<br><br>");
			bodyContent.append("Regards <br> Admin Team, abcdf.");
			
			doc.appendItemValue("\n\n");
			//CommonMessage.debugMsg("  attaching content  " );
			doc.appendItemValue("Body", content);
			MailLog.info(" attaching subject " );
			
			doc.appendItemValue("Subject", subject);
			MailLog.info("  attaching sendTo  "  + sendTo );
			//doc.appendItemValue("Body", "Today " + new Date());
			boolean isMultiple = false;
			Vector<String> vecSendObj  = null;
			
			if( sendTo.indexOf(",") > 0 || sendTo.indexOf(";") > 0){
				vecSendObj =  new Vector<String>();
				String [] sendToArr = null; 
				if( sendTo.indexOf(";")>0){
					sendToArr = sendTo.split(";");
				}
				else if( sendTo.indexOf(",")>0){
					sendToArr = sendTo.split(",");
				}
				
				for(String to:sendToArr){
					if(isValidEmail( to) )
						vecSendObj.addElement(to);
				}
				doc.appendItemValue("SentTo" , vecSendObj);
				isMultiple = true;
			}
			else if( isValidEmail(sendTo))
				doc.appendItemValue("SentTo", sendTo);
			
			Vector<String> vecObj = null;
			MailLog.info("  attaching ccTo  " );
			if( copyTo != null && ! copyTo.isEmpty())
			{
				vecObj = new Vector<String>();
				String [] copyToArr = null; 
				if( copyTo.indexOf(";")>0){
					copyToArr = copyTo.split(";");
				}
				else if( copyTo.indexOf(",")>0){
					copyToArr = copyTo.split(",");
				}
				else{ 
					copyToArr = new String [ 1 ];
					copyToArr[0] =  copyTo;
				}
				for(String cc:copyToArr){
					if( isValidEmail(cc))
						vecObj.addElement(cc);
				}
				doc.appendItemValue("CopyTo" , vecObj);
				
			}	
			MailLog.info("sending mail.. to " + sendTo);
			if( ! isMultiple && isValidEmail(sendTo) )
				doc.send(sendTo);
			else if( isMultiple )
				doc.send(vecSendObj);
			
			MailLog.info("Mail sent Successfully!!!");
			
			doc.save();
			doc.recycle();
			database.recycle();
			session.recycle();
			//CommonMessage.debugMsg("Platform = " + p);
		}
		catch(NotesException e)
		{
			MailLog.error(" errorr while sending ... NotesException" );
			MailLog.error(" Notes Error 1 :  " + e);
			MailLog.error(" Notes Error 2 :  " + e.id);
			MailLog.error(" Notes Error 3 :  " + e.text);
			//CommonMessage.debugMsg(" Notes Error 4:  " + e.getMessage());
			//e.printStackTrace();
			throw e;
		}
		catch(Exception e)
		{
			MailLog.error(" errorr while sending ... Exception" );
			MailLog.error(" errorr Msg ... "+e.getMessage() );
			
			throw e;
		}
		
		finally{
			
			NotesThread.stermThread();
		}
	}
	
	public boolean isValidEmail(String emailId){
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return emailId.matches(EMAIL_REGEX);
		
	}
}
*/