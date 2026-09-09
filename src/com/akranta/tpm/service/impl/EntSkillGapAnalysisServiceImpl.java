package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.EntSkillGapAnalysisDao;
import com.akranta.tpm.dao.impl.EntSkillGapAnalysisDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.EntSkillGapAnalysisService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntSkillGapAnalysisServiceImpl implements EntSkillGapAnalysisService {

	EntSkillGapAnalysisDao entSkillGapAnalysisDao;
	public EntSkillGapAnalysisServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entSkillGapAnalysisDao=new EntSkillGapAnalysisDaoImpl(dbActionTemplate);
	}
	@Override
	public List<String[]> getSkillGapAnalysisGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return entSkillGapAnalysisDao.getSkillGapGrid(commonFilter);
	}
	@Override
	public  Workbook SkillGapExportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception
	{
		return entSkillGapAnalysisDao.getSkillGapExcel(colmodel,format,commonFilter);
	}
	
	public List<String[]> getEmpAssesmentTopicRatings(String assessmentId ,String fromSpoke) throws NoDataFoundException, Exception{
		CommonMessage.debugMsg("In service Impl");
		return entSkillGapAnalysisDao.getEmpAssesmentTopicRatings( assessmentId,fromSpoke); 
	}
	@Override
	public List<String[]> getRadarChartData(String key,String date,String empId) throws Exception {
		// TODO Auto-generated method stub
		return entSkillGapAnalysisDao.getRadarChart(key,date,empId);
	}

}
