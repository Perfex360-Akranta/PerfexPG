package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlLocationmst;

public interface BAL_GenTlLocationmstDao {

	public abstract BAL_GenTlLocationmst create(BAL_GenTlLocationmst genTlLocationmst) throws Exception;
	public abstract BAL_GenTlLocationmst update(BAL_GenTlLocationmst genTlLocationmst) throws Exception;
	public abstract BAL_GenTlLocationmst delete(BAL_GenTlLocationmst genTlLocationmst) throws Exception;
	public abstract BAL_GenTlLocationmst select(String keyid)throws  Exception ;
	public abstract List<String[]> getGenTlLocationmst(CommonFilter commonFilter);

}

