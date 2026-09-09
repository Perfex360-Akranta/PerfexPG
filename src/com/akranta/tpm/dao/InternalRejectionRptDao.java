package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface InternalRejectionRptDao {
	
	List<String[]> getInternalRejection(CommonFilter commonFilter)throws Exception;

	Map<Integer, List<String[]>> getAllRejection(CommonFilter commonFilter)throws Exception;

	Workbook getAllExcelData(CommonFilter commonFilter, JSONObject colModel,String rptFormat) throws Exception;

	Map<Integer, List<String[]>> getExportExcel(String format,JSONObject tblJSONObj, CommonFilter commonFilter) throws Exception;

	List<String[]> getInternalRejectionRpt(CommonFilter commonFilter)throws Exception;
	
	public Workbook getAllExcelDataNew(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception ;
	
	public void InternalRejectionRptDaoImplJwt(String JwtToken);

}
