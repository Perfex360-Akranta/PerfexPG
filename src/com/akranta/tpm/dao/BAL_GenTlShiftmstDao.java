package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlShiftmst;

public interface BAL_GenTlShiftmstDao {

	public abstract BAL_GenTlShiftmst create(BAL_GenTlShiftmst genTlShiftmst) throws Exception;
	public abstract BAL_GenTlShiftmst update(BAL_GenTlShiftmst genTlShiftmst) throws Exception;
	public abstract BAL_GenTlShiftmst delete(BAL_GenTlShiftmst genTlShiftmst) throws Exception;
	public abstract BAL_GenTlShiftmst select(String keyid) throws Exception;
	public abstract List<String[]> getGenTlShiftmstShiftorder(ComboFilter comboFilter);
	List<String[]> getGenTlShiftmstShiftorder(CommonFilter commonFilter);


}

