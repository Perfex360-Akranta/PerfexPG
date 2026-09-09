package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface OplCummulativeDao {

	List<String[]> getAlloplcumm(CommonFilter commonFilter) throws Exception;

	public Workbook getAlloplcummExl(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;
	List<String[]> getOplCountData(CommonFilter commonFilter)throws Exception;
	Workbook getOplCountExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
				String format)throws Exception;
	
	public abstract void OplCummulativeDaoImplJwt(String jwtToken);
	
}
