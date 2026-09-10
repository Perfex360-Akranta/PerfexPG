
package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlFactorymstBean;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.BAL_GenTlFactorymst;


public interface BAL_GenTlFactorymstService {
	public BAL_GenTlFactorymst create(BAL_GenTlFactorymst newGenTlFactorymst,BAL_GenTlFactorymst oldGenTlFactorymst,  BAL_GenTlFactorymstBean genTlFactorymstBean ) throws ValidationExceptions, Exception;
	public BAL_GenTlFactorymst update(BAL_GenTlFactorymst newGenTlFactorymst,BAL_GenTlFactorymst oldGenTlFactorymst,  BAL_GenTlFactorymstBean genTlFactorymstBean )  throws Exception;
	public BAL_GenTlFactorymst delete(BAL_GenTlFactorymst genTlFactorymst) throws Exception;
	public List<ComboBox> getGenTlFactorymstcombo(String compId)  throws Exception;
	public BAL_GenTlFactorymst select(String keyid) throws Exception;

}