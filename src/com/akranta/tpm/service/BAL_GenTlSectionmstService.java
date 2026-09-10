package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlSectionmstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.BAL_GenTlPbumst;
import com.akranta.tpm.model.BAL_GenTlSectionmst;

public interface BAL_GenTlSectionmstService {
	public BAL_GenTlSectionmst create(BAL_GenTlSectionmst newGenTlSectionmst,BAL_GenTlSectionmst oldGenTlSectionmst,  BAL_GenTlSectionmstBean genTlSectionmstBean ) throws ValidationExceptions, Exception;
	public BAL_GenTlSectionmst update(BAL_GenTlSectionmst newGenTlSectionmst,BAL_GenTlSectionmst oldGenTlSectionmst,  BAL_GenTlSectionmstBean genTlSectionmstBean )  throws Exception;
	public BAL_GenTlSectionmst delete(String delemode, BAL_GenTlSectionmst genTlSectionmst) throws ValidationExceptions,Exception;
	public List<ComboBox> getGenTlSectionmstcombo(String condSql)  throws Exception;
	public BAL_GenTlSectionmst select(String keyid) throws Exception;
	public BAL_GenTlSectionmst filldmtcontrol(String dMTkeyid) throws Exception;
}
