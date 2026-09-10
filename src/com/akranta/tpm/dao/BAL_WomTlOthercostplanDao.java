package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_WomTlOthercostplan;

public interface BAL_WomTlOthercostplanDao {

	public abstract List<String []> getOtherCost(String EmpId) throws Exception;
	
	public abstract BAL_WomTlOthercostplan create(BAL_WomTlOthercostplan womTlOthercostplan) throws Exception;
	public abstract BAL_WomTlOthercostplan update(BAL_WomTlOthercostplan womTlOthercostplan) throws Exception;
	public abstract BAL_WomTlOthercostplan delete(BAL_WomTlOthercostplan womTlOthercostplan) throws Exception;

	public abstract Workbook getAllCostInfoExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format)throws Exception;
	

}

