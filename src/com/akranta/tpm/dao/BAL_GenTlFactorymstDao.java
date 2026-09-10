package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlFactorymst;

public interface BAL_GenTlFactorymstDao {

	public abstract BAL_GenTlFactorymst create(BAL_GenTlFactorymst genTlFactorymst) throws Exception;
	public abstract BAL_GenTlFactorymst update(BAL_GenTlFactorymst genTlFactorymst) throws Exception;
	public abstract BAL_GenTlFactorymst delete(BAL_GenTlFactorymst genTlFactorymst) throws Exception;
	public abstract List<String[]> getGenTlFactorymst(CommonFilter commonFilter);
	public abstract BAL_GenTlFactorymst select(String keyid) throws Exception;
	public abstract List<ComboBox> getGenTlFactorymstcombo(ComboFilter comboFilter);

}

