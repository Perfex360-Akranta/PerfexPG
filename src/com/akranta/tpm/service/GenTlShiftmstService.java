package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlShiftmstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;

import com.akranta.tpm.model.GenTlShiftmst;

public interface GenTlShiftmstService {

	public GenTlShiftmst create(GenTlShiftmst newGenTlShiftmst,GenTlShiftmst oldGenTlShiftmst,  GenTlShiftmstBean genTlShiftmstBean ) throws ValidationExceptions, Exception;
	public GenTlShiftmst update(GenTlShiftmst newGenTlShiftmst,GenTlShiftmst oldGenTlShiftmst,  GenTlShiftmstBean genTlShiftmstBean )  throws Exception;
	public GenTlShiftmst delete(GenTlShiftmst genTlShiftmst) throws Exception;
	public GenTlShiftmst select(String keyid) throws Exception;
	//public List<ComboBox> getGenTlShiftmstShiftorder(String string) throws Exception;
	public List<ComboBox> getdepartmentcombo(ComboFilter comboFilter) throws Exception;

	


}
