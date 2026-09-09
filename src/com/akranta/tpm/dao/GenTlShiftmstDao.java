package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlShiftmst;

public interface GenTlShiftmstDao {

	public abstract GenTlShiftmst create(GenTlShiftmst genTlShiftmst) throws Exception;
	public abstract GenTlShiftmst update(GenTlShiftmst genTlShiftmst) throws Exception;
	public abstract GenTlShiftmst delete(GenTlShiftmst genTlShiftmst) throws Exception;
	public abstract GenTlShiftmst select(String keyid) throws Exception;
	public abstract List<String[]> getGenTlShiftmstShiftorder(ComboFilter comboFilter);
	List<String[]> getGenTlShiftmstShiftorder(CommonFilter commonFilter);


}

