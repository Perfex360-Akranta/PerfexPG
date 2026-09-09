package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.SkillIndexReportDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.SkillIndexReportDaoImpl;
//import com.akranta.tpm.dao.impl.SkillIndexReportDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.SkillIndexReportService;
import com.akranta.tpm.service.api.SkillIndexServiceApi;
import com.akranta.tpm.utils.CommonMessage;
public class SkillIndexReportServiceImpl implements SkillIndexReportService{

private SkillIndexReportDao skillIndexReportDao;

SkillIndexServiceApi serviceApi;
	
	public SkillIndexReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		skillIndexReportDao =  new SkillIndexReportDaoImpl(dbActionTemplate);
	}	
	
	
	public void SkillIndexReportServiceImplJwt(String JwtToken){
    	try{
    		skillIndexReportDao.SkillIndexReportDaoImplJwt(JwtToken);
    		serviceApi = new SkillIndexServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	
	@Override
	public String callProcedureAverageSkillIndex(CommonFilter commonFilter) {
		return skillIndexReportDao.callProcedureAverageSkillIndex(commonFilter);
		
	}

	
	public List<String[]> getSkillIndex(CommonFilter commonFilter) throws Exception {

		return this.skillIndexReportDao.getSkillIndex( commonFilter);
	}
	public List<String[]> getTopicTask(CommonFilter commonFilter) throws Exception {
		return this.skillIndexReportDao.getTopicTask( commonFilter);
	}

	
	public List<String[]> getTask(CommonFilter commonFilter) throws Exception {
		return this.skillIndexReportDao.getTask( commonFilter);
	}

	@Override
	public List<String[]> getSkillIndexRadarChart(CommonFilter commonFilter) throws Exception 
	{
		String flid = commonFilter.getFlid();
		String fromDate = commonFilter.getFromDate();
		String emLinkIds = commonFilter.getEmmLinkKeyId();
		String uniqPosId = commonFilter.getUniquePos();
		//return this.skillIndexReportDao.getSkillIndexRadarChart( commonFilter);
		return serviceApi.getSkillIndexRadarChart(fromDate, flid, uniqPosId, emLinkIds);
	}
	
	public Workbook skillIndexReportExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		return this.skillIndexReportDao.skillIndexReportExportExcel( commonFilter, tblJSONObj, format);		
	}

	@Override
	public List<String[]> getskillIndexChart(CommonFilter commonFilter) throws Exception {
		return this.skillIndexReportDao.getskillIndexChart( commonFilter);
	}

	//graph
	
	@Override
	public List<String[]> getavgSkillScoreGraph(CommonFilter commonFilter)throws Exception {
		return this.skillIndexReportDao.getavgSkillScoreGraph(commonFilter);
	}

	@Override
	public Workbook avgSkillGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception 
	{
		CommonMessage.debugMsg("Entered in to service impl JH WISE");
		return this.skillIndexReportDao.avgSkillGraphExportExcel(commonFilter,colModel,rptFormat);
	}
	@Override
	public List<String[]> getTradeWiseSkillIndex(CommonFilter commonFilter)
			throws Exception {
		
		return this.skillIndexReportDao.getTradeWiseSkillIndex(commonFilter);
	}
	public String getFnlnDescription(String flid) throws Exception{
		return this.skillIndexReportDao.getFnlnDescription(flid);
	}

	@Override
	public  List<String[]> getTradeWiseColumnData(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		 return this.skillIndexReportDao.getTradeWiseColumnData(commonFilter);
	}
	@Override
	public Workbook tradewiseSkillGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.skillIndexReportDao.tradewiseSkillGraphExportExcel(commonFilter,colModel,rptFormat);
	}
	
	
}
