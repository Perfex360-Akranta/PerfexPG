package com.akranta.tpm.dao;

import java.util.List; 

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_LossStratificationDao {

	List<String[]> getAllLossStratification(CommonFilter commonFilter) throws Exception;

	List<String[]> getAllLossStratificationSubGrid(CommonFilter commonFilter)throws Exception;

	List<String[]> getAllLossGraph(CommonFilter commonFilter)throws Exception;

	public Workbook getAllLossExl(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;

	public Workbook getAllLossExlShift(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;

	List<String[]> getAllLossStratificationShift(CommonFilter commonFilter) throws Exception;

	List<String[]> getAllLossStratificationSubGridShift(CommonFilter commonFilter) throws Exception;
	
	public void BAL_LossStratificationDaoImplJwt(String JwtToken);


}
