package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.MailDao;
import com.akranta.tpm.dao.impl.MailDaoImpl;
import com.akranta.tpm.service.MailService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Mail;

public class MailServiceImpl implements MailService{
	private String username;
	private String password;	
	private String host;
	private String port;
	private String fromAddress;
	MailDao mailDao;
	Mail mail = null;
	public MailServiceImpl(DBActionTemplate dbActionTemplate) throws Exception{
		
		mailDao = new MailDaoImpl(dbActionTemplate);
		List<String[]> mailDetails = new ArrayList<String[]>();
		 mailDao.getMailInfo(mail);
		CommonMessage.debugMsg("Constructor : "+mailDetails.get(0)[0]);
		if(mailDetails.size()>0)
		{
			this.username= mailDetails.get(0)[0];
			this.password= mailDetails.get(1)[0];			
			this.host=mailDetails.get(2)[0];
			this.port=mailDetails.get(3)[0];	
			this.fromAddress = mailDetails.get(4)[0];	
		}
	}
	public List<String> getMailInfo()throws Exception
	{
		
		List<String> mailInfo = new ArrayList<String>();
		mailInfo.add(username);
		mailInfo.add(password);
		mailInfo.add(host);
		mailInfo.add(port);
		mailInfo.add(fromAddress);
		
		return mailInfo;
	}

}
