package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface SkillIndexReportDao {

	public List<String[]> getSkillIndex(CommonFilter commonFilter)throws Exception;
	public List<String[]> getTopicTask(CommonFilter commonFilter) throws Exception;
	public List<String[]> getTask(CommonFilter commonFilter) throws Exception;
	public List<String[]> getSkillIndexRadarChart(CommonFilter commonFilter) throws Exception;
	
	public Workbook skillIndexReportExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception ;
	public List<String[]> getskillIndexChart(CommonFilter commonFilter) throws Exception;
	
	//graph
	List<String[]> getavgSkillScoreGraph(CommonFilter commonFilter)throws Exception;
	Workbook avgSkillGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;
	public List<String[]> getTradeWiseSkillIndex(CommonFilter commonFilter) throws Exception;
	public  List<String[]> getTradeWiseColumnData(CommonFilter commonFilter) throws Exception;
	public String getFnlnDescription(String flid) throws Exception;
    Workbook tradewiseSkillGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception ;
    
    public void SkillIndexReportDaoImplJwt(String JwtToken); 
    

    
    public String callProcedureAverageSkillIndex(CommonFilter commonFilter);
	
	

}
