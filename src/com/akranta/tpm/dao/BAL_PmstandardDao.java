package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_PmstandardDao {

	public List<String []> getPmstandard(CommonFilter commonFilter) throws Exception ;

	public Workbook PMStandardReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public Workbook PMStandardReportExportToExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;

	public List<String[]> getPmstandardRptTemp(CommonFilter commonFilter) throws Exception;

	public List<String[]> getPmstandardAssemblyRptTemp(CommonFilter commonFilter) throws Exception;
	
	public void BAL_PmstandardDaoImplJwt(String JwtToken);

}
