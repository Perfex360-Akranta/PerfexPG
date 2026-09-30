package com.akranta.tpm.service.impl;

import java.io.IOException;

import jakarta.xml.soap.SOAPException;

import com.akranta.tpm.Exceptions.SAPExceptions;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.service.SOAPService;
import com.akranta.tpm.utils.SOAPClient;

public class SOAPServiceImpl implements SOAPService {

	SOAPClient soapClient; 
	public SOAPServiceImpl(DBActionTemplate dbActionTemplate ) throws SAPExceptions{
		soapClient = new SOAPClient(dbActionTemplate);
	}
	@Override
	public void sumbitToSAP(String transName, String transId)
			throws SAPExceptions, SOAPException, IOException {
		soapClient.sumbitToSAP(transName, transId);
		
	}

	public SOAPClient getSOAPClient(){
		//CommonFunctions.debugMsg(" ------------- soapClient " + soapClient);
		return soapClient;
	}
}
