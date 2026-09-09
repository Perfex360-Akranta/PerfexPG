package com.akranta.tpm.dao;

import com.akranta.tpm.model.PcsTlProductionplan;

public interface PcsTlProductionplanDao {

	public abstract PcsTlProductionplan create(PcsTlProductionplan pcsTlProductionplan) throws Exception;
	public abstract PcsTlProductionplan update(PcsTlProductionplan pcsTlProductionplan) throws Exception;
	public abstract PcsTlProductionplan delete(PcsTlProductionplan pcsTlProductionplan) throws Exception;

}

