package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface KaizenRewardReportService {

	List<String[]> getKaizenRewardGridData(CommonFilter commonFilter)throws IOException, SQLException, Exception;

	Workbook getKaizenRewardGridDataExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws IOException, SQLException;
	
	List<String[]> getKaizenPendingGridData(CommonFilter commonFilter)throws  Exception;

	Workbook getKaizenPendingGridDataExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format) throws Exception;

	List<String[]> getPendingKaizenReport(CommonFilter commonFilter) throws  Exception;

	Workbook getDtlKaizenPendingGridDataExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws  Exception;
	public void KaizenRewardReportServiceImplJwt(String JwtToken);
}
