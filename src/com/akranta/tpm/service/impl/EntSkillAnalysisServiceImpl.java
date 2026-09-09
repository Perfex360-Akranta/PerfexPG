package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

//import com.akranta.tpm.dao.CustomerComplaintStmtRptDao;
import com.akranta.tpm.dao.EntSkillAnalysisDao;
//import com.akranta.tpm.dao.EntTlTrainingHoursDao;
//import com.akranta.tpm.dao.impl.CustomerComplaintStmtRptDaoImpl;
import com.akranta.tpm.dao.impl.EntSkilAnalysisDaoImpl;
//import com.akranta.tpm.dao.impl.EntTlTrainingHoursDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.CommonFilterTraining;
//import com.akranta.tpm.service.CustomerComplaintStmtRptService;
import com.akranta.tpm.service.EntSkillAnalysisService;
//import com.akranta.tpm.service.EntTlTrainingHoursService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;



public  class EntSkillAnalysisServiceImpl implements  EntSkillAnalysisService {

private EntSkillAnalysisDao entSkillGapAnalysisDao;
	
	public EntSkillAnalysisServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entSkillGapAnalysisDao =  new EntSkilAnalysisDaoImpl(dbActionTemplate);
	}
	
	public List<String[]> getSkillGapAnalysis(CommonFilter commonFilter,CommonFilterTraining commonFilterTraining) throws Exception
	{
		CommonMessage.debugMsg("getTrainingHoursPgm");
		return this.entSkillGapAnalysisDao.getSkillGapAnalysis(commonFilter,commonFilterTraining);
	}
	public Workbook getSkillGapAnalysisExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format,CommonFilterTraining commonFilterTraining) throws Exception
	{
		CommonMessage.debugMsg("getTrainingHoursPgm");
		return this.entSkillGapAnalysisDao.getSkillGapAnalysisExportExcel(commonFilter,tblJSONObj,format,commonFilterTraining);
	}

	
}
