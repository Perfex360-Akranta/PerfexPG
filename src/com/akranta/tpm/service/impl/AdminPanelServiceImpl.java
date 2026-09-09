package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DashboardDispbean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.AdmTlDashboadUserrightsDao;
import com.akranta.tpm.dao.AdminPanelDao;
import com.akranta.tpm.dao.DashboardDao;
import com.akranta.tpm.dao.impl.AdmTlDashboadUserrightsDaoImpl;
import com.akranta.tpm.dao.impl.AdminPanelDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.DashboardDaoImpl;
import com.akranta.tpm.model.AdmTlDashboadUserrights;
import com.akranta.tpm.model.AdmTlScrollmsgmst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMessageboard;
import com.akranta.tpm.model.JhaTlFiveSAudit;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.service.AdminPanelService;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class AdminPanelServiceImpl implements AdminPanelService {
	
	private AdminPanelDao adminPanelDao =null;
	private AdmTlDashboadUserrightsDao admtlDashboadUserrightsDao=null;
	private Validations validations;
	public AdminPanelServiceImpl(DBActionTemplate dbActionTemplate) throws Exception{
		adminPanelDao = new AdminPanelDaoImpl(dbActionTemplate); 
		admtlDashboadUserrightsDao=new AdmTlDashboadUserrightsDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	public List<String[]> getJhAuditRpt(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.adminPanelDao.getJhAuditRpt(commonFilter);
	}


	@Override
	public Workbook JhAuditActionReportExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return this.adminPanelDao.JhAuditActionReportExportExcel(commonFilter,tblJSONObj,format);
	}

	
	@Override
	public List<String[]> getAttendancemonthwise(CommonFilter commonFilter,
			String flid) throws Exception {
		// TODO Auto-generated method stub
		return adminPanelDao.getAttendancemonthwise(commonFilter,flid);
	}	
	@Override
	public Workbook MomeetingMonthwiseExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		return this.adminPanelDao.MomeetingMonthwiseExportExcel(commonFilter, colmodel,format);
	}
	public List<String []> getAbnCumulative(CommonFilter commonFilter)  throws Exception{
		return this.adminPanelDao.getAbnCumulative(commonFilter);
	}
	public List<String[]> getAllGraphicalSumm(CommonFilter commonFilter)throws Exception {		
		return this.adminPanelDao.getAllGraphicalSumm(commonFilter);
		
	}
	public Workbook KaizenGraphicalSummExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
		return this.adminPanelDao.KaizenGraphicalSummExportExcel(commonFilter,colModel,rptFormat);
	}
	@Override
	public List<String[]> getEHSMetricsCountData(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.adminPanelDao.getEHSMetricsCountData(commonFilter);
	}
	@Override
	public List<String[]> getTransactionSummaryGridData(
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.adminPanelDao.getTransactionSummaryGridData(commonFilter);
	}
	@Override
	public Workbook getTransactionSummaryGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		// TODO Auto-generated method stub
		return this.adminPanelDao.getTransactionSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);
	}
	@Override
	public List<String[]> getAllAbnormalitySumm(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.adminPanelDao.getAllAbnormalitySumm(commonFilter);
	}
	public String getDmtFlid(String Flid)throws Exception{
	    return this.adminPanelDao.getDmtFlid(Flid);	
	}
	@Override
	public List<String[]> getAttendancemonthwiseNewReport(
			CommonFilter commonFilter, String flid) throws Exception {
		// TODO Auto-generated method stub
		return this.adminPanelDao.getAttendancemonthwiseNewReport(commonFilter,flid);
	}
	@Override
	public List<String[]> PendingAbnlist(CommonFilter commonFilter,
			GridParams gridParams,String Finance) throws Exception {
		// TODO Auto-generated method stub
		return  this.adminPanelDao.PendingAbnlist(commonFilter,gridParams,Finance);
	}
	
	/*public List<String[]> getTeamPerCount(CommonFilter commonFilter,String flid) throws Exception{
    	return adminPanelDao.getTeamPerCount(commonFilter, flid);
    }*/
	
	@Override
	public List<String[]> getEmployeeList(String flid,String fileName) throws Exception {
		// TODO Auto-generated method stub
		return  this.adminPanelDao.getEmployeeList(flid, fileName);
	}
	public List<String[]> getAbnTeamPerData(CommonFilter commonFilter)throws Exception{
	   return this.adminPanelDao.getAbnTeamPerData(commonFilter);
	}
	public List<String[]> getSuggTeamPerData(CommonFilter commonFilter)throws Exception{
		return adminPanelDao.getSuggTeamPerData(commonFilter);
	}
	@Override
	public  String getTotalSuggestionCount(String empKeyId, String flid) throws Exception {
		// TODO Auto-generated method stub
		return adminPanelDao.getTotalSuggestionCount(empKeyId,flid);
	}
	public String getTotalAbnormalityCount(String empKeyId, String flid) throws Exception{
		return adminPanelDao.getTotalAbnormalityCount(empKeyId,flid);
	}
    public List<String[]> getTeamPerCount(CommonFilter commonFilter,String flid, String fileName) throws Exception{
    	return adminPanelDao.getTeamPerCount(commonFilter, flid, fileName);
    }
    public Workbook getTeamPerformanceCountExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,String Flid) throws Exception{
    	return adminPanelDao.getTeamPerformanceCountExportExcel(commonFilter, tblJSONObj, format, Flid);
    }

}