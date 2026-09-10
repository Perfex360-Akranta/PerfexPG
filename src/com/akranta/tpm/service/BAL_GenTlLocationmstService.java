package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlLocationmstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.BAL_GenTlLocationmst;

public interface BAL_GenTlLocationmstService {
	public BAL_GenTlLocationmst create(BAL_GenTlLocationmst newGenTlLocationmst,BAL_GenTlLocationmst oldGenTlLocationmst,  GenTlLocationmstBean genTlLocationmstBean ) throws ValidationExceptions, Exception;
	public BAL_GenTlLocationmst update(BAL_GenTlLocationmst newGenTlLocationmst,BAL_GenTlLocationmst oldGenTlLocationmst,  GenTlLocationmstBean genTlLocationmstBean )  throws Exception;
	public BAL_GenTlLocationmst delete(BAL_GenTlLocationmst genTlLocationmst) throws Exception;
	//public List<ComboBox> getGenTlLocationmstcombo(String condSql)  throws Exception;
	public BAL_GenTlLocationmst select(String keyid) throws Exception;

}
