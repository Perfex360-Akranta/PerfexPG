package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_BreakDownParetoDao {

	List<String[]> getAllbkdpareto(CommonFilter commonFilter) throws Exception;

	public Workbook getparetoExcel(CommonFilter commonFilter, JSONObject colmodel,String rptformat) throws Exception;

}
