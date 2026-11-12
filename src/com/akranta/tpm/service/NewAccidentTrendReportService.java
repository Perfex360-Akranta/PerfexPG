package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

import net.sf.json.JSONObject;

public interface NewAccidentTrendReportService {
public List<String []> getNewAccidentRpt(CommonFilter commonFilter)  throws Exception;
public Workbook NewAccidentTrendExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
public List<String[]> getNewHseAccTrendRptgraph(CommonFilter chrtCommonFilter, String keyId)throws Exception;
public List<String []> getNewBodyPartRpt(CommonFilter commonFilter)  throws Exception;
public List<String[]> getNewBodyPartRptGraph(CommonFilter chrtCommonFilter, String keyId)throws Exception;
public Workbook NewBodyPortExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
public List<String []> getModeWiseReport(CommonFilter commonFilter)  throws Exception;
public List<String[]> getModeWiseRptReportgraph(
		CommonFilter chrtCommonFilter, String rowId)throws Exception;
public Workbook ModeWiseReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
public List<String[]> getEmployeeIncident(CommonFilter commonFilter) throws Exception;
public Workbook EmpAccidentExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;

public void NewAccidentTrendReportServiceImplJwt(String JwtToken);
}
