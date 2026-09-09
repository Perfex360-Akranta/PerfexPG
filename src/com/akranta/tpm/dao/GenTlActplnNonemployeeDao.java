package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActplnNonemployee;

public interface GenTlActplnNonemployeeDao {
	

	public abstract GenTlActplnNonemployee create(GenTlActplnNonemployee genTlActplnNonemployee) throws Exception;
	public abstract GenTlActplnNonemployee update(GenTlActplnNonemployee genTlActplnNonemployee) throws Exception;
	public abstract GenTlActplnNonemployee delete(GenTlActplnNonemployee genTlActplnNonemployee) throws Exception;
	public abstract List<String[]> getOthers(CommonFilter commonFilter)throws Exception;

}

