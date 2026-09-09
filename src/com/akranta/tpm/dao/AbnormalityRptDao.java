package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface AbnormalityRptDao {

	public List<String []> getAbnormality(CommonFilter commonFilter,String type) throws Exception ;
	public List<String []> getAgeAbnormalityDao(CommonFilter commonFilter) throws Exception ;
	public Workbook getAbnormalityReport(CommonFilter commonFilter,JSONObject colModel,String rptFormat,String type) throws Exception;
	public Workbook getAbnormalityAgeReport(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception;
	public List<String[]> getAbnTagTrend(CommonFilter commonFilter)throws Exception;
	public Workbook getAbnTagTrendExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format)throws Exception;
	public Workbook getExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception;
	public List<String[]> getAllAbnormalityDetails(CommonFilter commonFilter,
			String keyid, String columnId, String colIndexName) throws Exception;
	
	public List<String[]> getAbnSummaryGridData(CommonFilter commonFilter) throws Exception;
	public Workbook getAbnSummaryGridDataExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String rptFormat) throws Exception;
	public void AbnTlAbnormalityDaoImplJwt(String jwtToken);

}
