package com.akranta.tpm.service;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlCompanymstBean;

import com.akranta.tpm.model.BAL_GenTlCompanymst;

public interface BAL_GenTlCompanymstService 
{
	public BAL_GenTlCompanymst create(BAL_GenTlCompanymst newGenTlCompanymst,BAL_GenTlCompanymst oldGenTlCompanymst,  BAL_GenTlCompanymstBean genTlCompanymstBean ) throws ValidationExceptions, Exception;
	public BAL_GenTlCompanymst update(BAL_GenTlCompanymst newGenTlCompanymst,BAL_GenTlCompanymst oldGenTlCompanymst,  BAL_GenTlCompanymstBean genTlCompanymstBean )  throws Exception;
	public BAL_GenTlCompanymst delete(BAL_GenTlCompanymst genTlCompanymst) throws Exception;
	public BAL_GenTlCompanymst select(String keyid) throws Exception;
}