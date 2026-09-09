package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlPbumst;
import com.akranta.tpm.model.GenTlSectionmst;

public interface GenTlSectionmstDao {

	public abstract GenTlSectionmst create(GenTlSectionmst genTlSectionmst) throws Exception;
	public abstract GenTlSectionmst update(GenTlSectionmst genTlSectionmst) throws Exception;
	public abstract GenTlSectionmst delete(String delemode,GenTlSectionmst genTlSectionmst) throws Exception;
	public abstract List<String[]> getGenTlSectionmst(CommonFilter commonFilter);
	public abstract GenTlSectionmst select(String keyid) throws Exception;
	public abstract GenTlSectionmst filldmtcontrol(String dMTkeyid) throws Exception;


}

