package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.BAL_WomTlUtilitycostplan;

public interface BAL_WomTlUtilitycostplanDao {

	public abstract List<String []> getUtilityCost(String UtilityId, String effDate) throws Exception;
	
	public abstract BAL_WomTlUtilitycostplan create(BAL_WomTlUtilitycostplan womTlUtilitycostplan) throws Exception;
	public abstract BAL_WomTlUtilitycostplan update(BAL_WomTlUtilitycostplan womTlUtilitycostplan) throws Exception;
	public abstract BAL_WomTlUtilitycostplan delete(BAL_WomTlUtilitycostplan womTlUtilitycostplan) throws Exception;

}

