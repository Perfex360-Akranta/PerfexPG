package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.utils.Mail;


public interface MailService {
	
	public List<String> getMailInfo()throws Exception;

}
