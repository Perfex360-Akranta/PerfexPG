package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.BAL_WomTlSparecostplan;

public interface BAL_WomTlSparecostplanDao {

	public abstract List<String []> getSpareCost(String SpareId) throws Exception;
	
	public abstract BAL_WomTlSparecostplan create(BAL_WomTlSparecostplan womTlSparecostplan) throws Exception;
	public abstract BAL_WomTlSparecostplan update(BAL_WomTlSparecostplan womTlSparecostplan) throws Exception;
	public abstract BAL_WomTlSparecostplan delete(BAL_WomTlSparecostplan womTlSparecostplan) throws Exception;

}

