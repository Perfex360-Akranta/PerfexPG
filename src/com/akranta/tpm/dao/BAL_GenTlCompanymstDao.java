package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlCompanymst;

public interface BAL_GenTlCompanymstDao {

	public abstract BAL_GenTlCompanymst create(BAL_GenTlCompanymst genTlCompanymst) throws Exception;
	public abstract BAL_GenTlCompanymst update(BAL_GenTlCompanymst genTlCompanymst) throws Exception;
	public abstract BAL_GenTlCompanymst delete(BAL_GenTlCompanymst genTlCompanymst) throws Exception;
	public abstract List<String[]> getGenTlCompanymst(CommonFilter commonFilter);
	public abstract BAL_GenTlCompanymst select(String keyid) throws Exception;

}

