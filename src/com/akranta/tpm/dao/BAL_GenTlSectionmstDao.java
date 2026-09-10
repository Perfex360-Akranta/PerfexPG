package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlPbumst;
import com.akranta.tpm.model.BAL_GenTlSectionmst;

public interface BAL_GenTlSectionmstDao {

	public abstract BAL_GenTlSectionmst create(BAL_GenTlSectionmst genTlSectionmst) throws Exception;
	public abstract BAL_GenTlSectionmst update(BAL_GenTlSectionmst genTlSectionmst) throws Exception;
	public abstract BAL_GenTlSectionmst delete(String delemode,BAL_GenTlSectionmst genTlSectionmst) throws Exception;
	public abstract List<String[]> getGenTlSectionmst(CommonFilter commonFilter);
	public abstract BAL_GenTlSectionmst select(String keyid) throws Exception;
	public abstract BAL_GenTlSectionmst filldmtcontrol(String dMTkeyid) throws Exception;


}

