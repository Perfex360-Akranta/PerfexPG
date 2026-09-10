package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_WomTlManpowercostplan;

public interface BAL_WomTlManpowercostplanDao {

	public abstract BAL_WomTlManpowercostplan create(BAL_WomTlManpowercostplan womTlManpowercostplan) throws Exception;
	public abstract BAL_WomTlManpowercostplan update(BAL_WomTlManpowercostplan womTlManpowercostplan) throws Exception;
	public abstract BAL_WomTlManpowercostplan delete(BAL_WomTlManpowercostplan womTlManpowercostplan) throws Exception;
	
	public abstract BAL_WomTlManpowercostplan select(String keyid) throws Exception;
	
	public abstract List<String []> getEmpCost(String EmpId) throws Exception;
	public abstract List<String []> getContractorCost(String EmpId) throws Exception;

}

