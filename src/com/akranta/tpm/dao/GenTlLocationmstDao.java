package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlLocationmst;

public interface GenTlLocationmstDao {

	public abstract GenTlLocationmst create(GenTlLocationmst genTlLocationmst) throws Exception;
	public abstract GenTlLocationmst update(GenTlLocationmst genTlLocationmst) throws Exception;
	public abstract GenTlLocationmst delete(GenTlLocationmst genTlLocationmst) throws Exception;
	public abstract GenTlLocationmst select(String keyid)throws  Exception ;
	public abstract List<String[]> getGenTlLocationmst(CommonFilter commonFilter);

}

