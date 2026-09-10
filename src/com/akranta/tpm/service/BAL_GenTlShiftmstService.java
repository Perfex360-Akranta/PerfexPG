package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlShiftmstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;

import com.akranta.tpm.model.BAL_GenTlShiftmst;

public interface BAL_GenTlShiftmstService {

	public BAL_GenTlShiftmst create(BAL_GenTlShiftmst newGenTlShiftmst,BAL_GenTlShiftmst oldGenTlShiftmst,  BAL_GenTlShiftmstBean genTlShiftmstBean ) throws ValidationExceptions, Exception;
	public BAL_GenTlShiftmst update(BAL_GenTlShiftmst newGenTlShiftmst,BAL_GenTlShiftmst oldGenTlShiftmst,  BAL_GenTlShiftmstBean genTlShiftmstBean )  throws Exception;
	public BAL_GenTlShiftmst delete(BAL_GenTlShiftmst genTlShiftmst) throws Exception;
	public BAL_GenTlShiftmst select(String keyid) throws Exception;
	//public List<ComboBox> getGenTlShiftmstShiftorder(String string) throws Exception;
	public List<ComboBox> getdepartmentcombo(ComboFilter comboFilter) throws Exception;

	


}
