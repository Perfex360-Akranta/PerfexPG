package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlCompanymst;

public interface GenTlCompanymstDao {

	public abstract GenTlCompanymst create(GenTlCompanymst genTlCompanymst) throws Exception;
	public abstract GenTlCompanymst update(GenTlCompanymst genTlCompanymst) throws Exception;
	public abstract GenTlCompanymst delete(GenTlCompanymst genTlCompanymst) throws Exception;
	public abstract List<String[]> getGenTlCompanymst(CommonFilter commonFilter);
	public abstract GenTlCompanymst select(String keyid) throws Exception;

}

