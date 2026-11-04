package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_PMActivitySummaryRptDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.BAL_PMActivitySummaryRptDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_PMActivitySummaryRptService;

import com.akranta.tpm.utils.CommonFunctions;

public class BAL_PMActivitySummaryRptServiceImpl implements
		BAL_PMActivitySummaryRptService {
	private BAL_PMActivitySummaryRptDao pmActivitySummaryRptDao;
public BAL_PMActivitySummaryRptServiceImpl(DBActionTemplate dbActionTemplate){
	pmActivitySummaryRptDao =new BAL_PMActivitySummaryRptDaoImpl(dbActionTemplate);
	}

public void BAL_PMActivitySummaryRptServiceImplJwt(String JwtToken){
	try{
		pmActivitySummaryRptDao.BAL_PMActivitySummaryRptDaoImplJwt(JwtToken);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
    // TODO Auto-generated constructor stub
}
	@Override
	public List<String[]> getSummary(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return pmActivitySummaryRptDao.getSummary(commonFilter);
	}
	
	@Override
	public List<String[]> getPMComplianceSummary(CommonFilter commonFilter,String jobType) throws Exception {
		// TODO Auto-generated method stub
		return pmActivitySummaryRptDao.getPMComplianceSummary(commonFilter,jobType);
	}
	@Override
	public Workbook PMCompSummaryReportExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format,String jobType) throws Exception{
		// TODO Auto-generated method stub
		return pmActivitySummaryRptDao.PMCompSummaryReportExportExcel(commonFilter,	tableModel, format,jobType) ;
	}
	public Workbook PMCompSummaryExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception{
		// TODO Auto-generated method stub
		return pmActivitySummaryRptDao.PMCompSummaryExportExcel(commonFilter,	tableModel, format) ;
	}
	@Override
	public String getReportName(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return pmActivitySummaryRptDao.getReportName(commonFilter);
	}
	@Override
	public List<String[]> getActivityGraph(CommonFilter chrtCommonFilter,String jobType)throws Exception
	{
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("inside the service method");
		return pmActivitySummaryRptDao.getActivityGraph(chrtCommonFilter,jobType);
	}
	@Override
	public List<String[]> PMplanvsactualSummaryReport(CommonFilter commonFilter)
			throws Exception {
		
		return pmActivitySummaryRptDao.PMplanvsactualSummaryReport(commonFilter);
	}
	@Override   
	public Workbook PMplanvsactualSummaryReportExportExcel(
			CommonFilter commonFilter, JSONObject tableModel, String format)
			throws Exception {
		// TODO Auto-generated method stub
		return pmActivitySummaryRptDao.PMplanvsactualSummaryReportExportExcel(commonFilter,  tableModel,  format);
	}
	@Override
	public List<String[]> getPlanVsActualActivityGraph(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return pmActivitySummaryRptDao.getPlanVsActualActivityGraph(commonFilter);
	}
	@Override
	public Workbook PMTimeSummaryExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return pmActivitySummaryRptDao.PMTimeSummaryExportExcel(commonFilter,	tblJSONObj, format);
	}
	@Override
	public List<String[]> getTimeSummary(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return pmActivitySummaryRptDao.getTimeSummary(commonFilter);
	}
}
