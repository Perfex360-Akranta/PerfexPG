package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlSectionmstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlPbumst;
import com.akranta.tpm.model.GenTlSectionmst;

public interface GenTlSectionmstService {
	public GenTlSectionmst create(GenTlSectionmst newGenTlSectionmst,GenTlSectionmst oldGenTlSectionmst,  GenTlSectionmstBean genTlSectionmstBean ) throws ValidationExceptions, Exception;
	public GenTlSectionmst update(GenTlSectionmst newGenTlSectionmst,GenTlSectionmst oldGenTlSectionmst,  GenTlSectionmstBean genTlSectionmstBean )  throws Exception;
	public GenTlSectionmst delete(String delemode, GenTlSectionmst genTlSectionmst) throws ValidationExceptions,Exception;
	public List<ComboBox> getGenTlSectionmstcombo(String condSql)  throws Exception;
	public GenTlSectionmst select(String keyid) throws Exception;
	public GenTlSectionmst filldmtcontrol(String dMTkeyid) throws Exception;
	public String getfunctionalid(String id) throws SQLException;
}
