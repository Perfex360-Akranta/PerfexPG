package com.akranta.tpm.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface InternalRejectionRptService {
	
	List<String[]> getInternalRejectionRpt(CommonFilter commonFilter)throws Exception;

	Map<Integer, List<String[]>> getAllData(CommonFilter commonFilter)throws Exception;

	Workbook getAllExcelData(CommonFilter commonFilter, JSONObject tblJSONObj,String format) throws Exception;

	Workbook getExportExcel(String format, JSONObject tblJSONObj,CommonFilter commonFilter)throws Exception;

	List<String[]> getInternalRejectionRptnew(CommonFilter commonFilter)throws Exception;
	
	public void InternalRejectionRptServiceImplJwt(String JwtToken);


}
