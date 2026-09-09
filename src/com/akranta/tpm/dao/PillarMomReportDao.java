package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

import net.sf.json.JSONObject;

public interface PillarMomReportDao {

	Workbook getPillarMomAttdExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format);

	List<String[]> getPillarMomAttd(CommonFilter commonFilter) throws Exception;
	
	
	public void PillarMomReportDaoImplJwt(String JwtToken); 

}
