package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_OeearprqrDao {

	List<String[]> getAllOeearprqr(CommonFilter commonFilter)throws Exception;

	public Workbook getAllOeearprqrExl(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;

	List<String[]> getAllChartType(CommonFilter commonFilter)throws Exception;
	
	public void BAL_OeearprqrDaoImplJwt(String JwtToken);


}
