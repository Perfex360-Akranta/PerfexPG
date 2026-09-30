package com.akranta.tpm.service;

import java.io.IOException;

import jakarta.xml.soap.SOAPException;

import com.akranta.tpm.Exceptions.SAPExceptions;
import com.akranta.tpm.utils.SOAPClient;

public interface SOAPService {
	public void sumbitToSAP(String transName, String transId ) throws SAPExceptions, SOAPException, IOException;
	public SOAPClient getSOAPClient();
}
