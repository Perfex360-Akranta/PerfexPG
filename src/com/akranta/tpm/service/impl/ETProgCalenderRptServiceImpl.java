package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.ETProgCalenderRptDao;

import com.akranta.tpm.dao.impl.ETProgCalenderRptDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.ETProgCalenderRptService;

public class ETProgCalenderRptServiceImpl implements ETProgCalenderRptService {
	private ETProgCalenderRptDao eTProgCalenderRptDao;
	
	public ETProgCalenderRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		eTProgCalenderRptDao =  new ETProgCalenderRptDaoImpl(dbActionTemplate);
	}
	
	public void ETProgCalenderRptServiceImplJwt(String JwtToken){
    	try{
    		eTProgCalenderRptDao.ETProgCalenderRptDaoImplJwt(JwtToken);
    		//irServiceApi = new InternalRejectionSerivceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	

	public List<String[]> getTrnPrgCal(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.eTProgCalenderRptDao.getTrnPrgCal(commonFilter);
	}
	
	
	public Workbook etTrnPrgCalReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.eTProgCalenderRptDao.getTrnPrgCalReport(commonFilter,colmodel,rptFormat);
	}
	
	public List<String[]> getSkillAnalysis(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.eTProgCalenderRptDao.getSkillAnalysis(commonFilter);
		
	}
	public List<String[]> getBefAftSkill(CommonFilter commonFilter,String empId) throws Exception {
		// TODO Auto-generated method stub
		return this.eTProgCalenderRptDao.getBefAftSkill(commonFilter,empId);
		
	}
	public List<String[]> getEmpBefAftTopicRatings(String empId,String date,String fromSpoke) throws NoDataFoundException, Exception{
		return eTProgCalenderRptDao.getEmpBefAftTopicRatings( empId,date,fromSpoke); 
	}

	public Workbook etSkillAnalysisReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.eTProgCalenderRptDao.SkillAnalysisReport(commonFilter,colmodel,rptFormat);
	}
	public Workbook etBefAftReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.eTProgCalenderRptDao.etBefAftReportExportExcel(commonFilter,colmodel,rptFormat);
	}
	public List<String[]> getPlanVsCompCal(CommonFilter commonFilter, String custmrtype) throws Exception {
		// TODO Auto-generated method stub
		return this.eTProgCalenderRptDao.getPlanVsCompCal(commonFilter,custmrtype);
		
	}
	
	public Workbook etPlanVsCompReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat,String custmrtype) throws Exception{
		return this.eTProgCalenderRptDao.PlanVsCompReportExportExcel(commonFilter,colmodel,rptFormat,custmrtype);
	}
	
	
	@Override
	public List<String[]> getPlanVsCompReportgraph(CommonFilter commonFilter,String keyId, String custype)
	throws Exception {
		// TODO Auto-generated method stub
		return this.eTProgCalenderRptDao.PlanVsCompReportgraph(commonFilter,keyId,custype);
	}
	@Override
	public List<String[]> progCalPlanVsCompReportgraph(CommonFilter commonFilter,String keyId)
	throws Exception {
		// TODO Auto-generated method stub
		return this.eTProgCalenderRptDao.ProgCalPlanVsActReportgraph(commonFilter,keyId);
	}
	public List<String[]> getProgCalPlanVsCompCal(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.eTProgCalenderRptDao.getProgCalPlanVsCompCal(commonFilter);
		
	}
	public Workbook ProgCalPlanVsActReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.eTProgCalenderRptDao.ProgCalPlanVsActReportExportExcel(commonFilter,colmodel,rptFormat);
	}
	
	
	
}
