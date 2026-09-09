package com.akranta.tpm.service;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlCompanymstBean;

import com.akranta.tpm.model.GenTlCompanymst;

public interface GenTlCompanymstService 
{
	public GenTlCompanymst create(GenTlCompanymst newGenTlCompanymst,GenTlCompanymst oldGenTlCompanymst,  GenTlCompanymstBean genTlCompanymstBean ) throws ValidationExceptions, Exception;
	public GenTlCompanymst update(GenTlCompanymst newGenTlCompanymst,GenTlCompanymst oldGenTlCompanymst,  GenTlCompanymstBean genTlCompanymstBean )  throws Exception;
	public GenTlCompanymst delete(GenTlCompanymst genTlCompanymst) throws Exception;
	public GenTlCompanymst select(String keyid) throws Exception;
}