package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.BAL_WomTlServicecostplan;

public interface BAL_WomTlServicecostplanDao {
	
	public abstract List<String []> getServiceCost(String ServiceId) throws Exception;

	public abstract BAL_WomTlServicecostplan create(BAL_WomTlServicecostplan womTlServicecostplan) throws Exception;
	public abstract BAL_WomTlServicecostplan update(BAL_WomTlServicecostplan womTlServicecostplan) throws Exception;
	public abstract BAL_WomTlServicecostplan delete(BAL_WomTlServicecostplan womTlServicecostplan) throws Exception;

}

