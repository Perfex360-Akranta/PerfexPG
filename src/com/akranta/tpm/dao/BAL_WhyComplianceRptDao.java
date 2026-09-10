package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_WhyComplianceRptDao {

	List<String[]> getAllyydrill(CommonFilter commonFilter) throws Exception;

	List<String[]> getAllyycelldrill(String colVal, CommonFilter commonFilter)throws Exception;

	List<String[]> getAllmonth(CommonFilter commonFilter)throws Exception;

	public Workbook getWhycompmonthExl(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;

	public Workbook getWhydetailExl(CommonFilter commonFilter, JSONObject colmodel,	String rptFormat)throws Exception;

	public Workbook getWhymonthExl(CommonFilter commonFilter, JSONObject colmodel,	String rptFormat)throws Exception;

}
