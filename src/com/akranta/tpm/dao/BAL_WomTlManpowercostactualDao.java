package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_WomTlManpowercostactual;

public interface BAL_WomTlManpowercostactualDao {

	public abstract BAL_WomTlManpowercostactual create(BAL_WomTlManpowercostactual womTlManpowercostactual) throws Exception;
	public abstract BAL_WomTlManpowercostactual update(BAL_WomTlManpowercostactual womTlManpowercostactual) throws Exception;
	public abstract BAL_WomTlManpowercostactual delete(BAL_WomTlManpowercostactual womTlManpowercostactual) throws Exception;
	
	 
	public List<String[]> getGridManPowerQry(String formName, String fromType,String woId ) throws Exception;
	
	public String getCheckManpowerExists(String formType, String woId ) throws Exception;


}

