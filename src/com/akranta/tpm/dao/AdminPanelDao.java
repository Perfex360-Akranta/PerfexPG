package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;


public interface AdminPanelDao {
	
	public List<String []> getJhAuditRpt(CommonFilter commonFilter) throws Exception ;
	public Workbook JhAuditActionReportExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format)throws Exception;
	public abstract List<String[]> getAttendancemonthwise(CommonFilter commonFilter, String flid)throws Exception;

	public abstract Workbook MomeetingMonthwiseExportExcel(
			CommonFilter commonFilter, JSONObject colmodel, String format)throws Exception;
	public List<String []> getAbnCumulative(CommonFilter commonFilter)  throws Exception;
	 List<String[]> getAllGraphicalSumm(CommonFilter commonFilter)throws Exception;	
		Workbook KaizenGraphicalSummExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public List<String[]> getEHSMetricsCountData(CommonFilter commonFilter) throws Exception;
	public List<String[]> getTransactionSummaryGridData(
			CommonFilter commonFilter) throws Exception;
	public Workbook getTransactionSummaryGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getAllAbnormalitySumm(CommonFilter commonFilter) throws Exception;
	public String getDmtFlid(String Flid)throws Exception;
	public List<String[]> getAttendancemonthwiseNewReport(
			CommonFilter commonFilter, String flid) throws Exception;
	public List<String[]> PendingAbnlist(CommonFilter commonFilter,
			GridParams gridParams,String Finance) throws Exception;
//	public List<String[]> getTeamPerCount(CommonFilter commonFilter, String flid) throws Exception;
	public List<String[]> getEmployeeList(String flid, String fileName) throws Exception;
	public List<String[]> getAbnTeamPerData(CommonFilter commonFilter)throws Exception;
	public List<String[]> getSuggTeamPerData(CommonFilter commonFilter)throws Exception;
	public String getTotalSuggestionCount(String empKeyId, String flid) throws Exception;
	public String getTotalAbnormalityCount(String empKeyId, String flid) throws Exception;
    public List<String[]> getTeamPerCount(CommonFilter commonFilter,String flid, String fileName) throws Exception;
    public Workbook getTeamPerformanceCountExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,String Flid) throws Exception;

}
