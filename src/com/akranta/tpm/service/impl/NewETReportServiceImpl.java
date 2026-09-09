package com.akranta.tpm.service.impl;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalquad;
import com.akranta.tpm.dao.NewETReportDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
//import com.akranta.tpm.dao.impl.ETProgCalenderRptDaoImpl;
import com.akranta.tpm.dao.impl.NewETReportDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.NewETReportService;
import com.akranta.tpm.service.api.NewTrainingcalendarServiceApi;
import com.akranta.tpm.service.api.QuadrantAssessmentServiceApi;

import net.sf.json.JSONObject;
public class NewETReportServiceImpl implements  NewETReportService{
private	NewETReportDao newETReportDao;
private QuadrantAssessmentServiceApi quadrantAssessmentServiceApi;	
public NewETReportServiceImpl(DBActionTemplate dbActionTemplate)
{
	newETReportDao =  new NewETReportDaoImpl(dbActionTemplate);
}

public void NewETReportServiceImplJwt(String JwtToken){
    try{
   // 	oplTlMstDao.OplTlMstDaoImplJwt(JwtToken);   // dao side
        // (Optional) if you want service-level direct access
    	quadrantAssessmentServiceApi = new QuadrantAssessmentServiceApi(JwtToken);
    	newETReportDao.NewETReportDaoImplJwt(JwtToken);
    //	newTrgServApi = new NewTrainingcalendarServiceApi(JwtToken);
    } catch(Exception e){
        e.printStackTrace();
    }
}
  
	public List<String[]> getPlanVsCompCal(CommonFilter commonFilter, String custmrtype) throws Exception {
		// TODO Auto-generated method stub
		return newETReportDao.getPlanVsCompCal(commonFilter,custmrtype);
		
	}
	public List<String[]> getPlanVsCompReportgraph(CommonFilter commonFilter,String keyId, String custype) throws Exception{
		// TODO Auto-generated method stub
		return this.newETReportDao.PlanVsCompReportgraph(commonFilter,keyId,custype);
	}
	public Workbook etPlanVsCompReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat,String custmrtype) throws Exception{
		return this.newETReportDao.PlanVsCompReportExportExcel(commonFilter,colmodel,rptFormat,custmrtype);
	}
	
	public List<String[]> getavgSkillScoreGraph(CommonFilter commonFilter)throws Exception {
		return this.newETReportDao.getavgSkillScoreGraph(commonFilter);
	}
	public Workbook avgSkillGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.newETReportDao.avgSkillGraphExportExcel(commonFilter,colModel,rptFormat);
	}
	public List<String[]> getAllPerPerson(CommonFilter commonFilter)throws Exception {
		return this.newETReportDao.getAllPerPerson(commonFilter);
	}
	public Workbook perPersonKaizenExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.newETReportDao.perPersonKaizenExportExcel(commonFilter,colModel,rptFormat);
	}
	


	@Override
	public List<String[]> getAllNosPerPerson(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.getAllNosPerPerson(commonFilter);
	}


	@Override
	public Workbook NosperPersonKaizenExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.NosperPersonKaizenExportExcel(commonFilter,colModel,rptFormat);
	}


	@Override
	public List<String[]> getEmployeeLevel(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.getEmployeeLevel(commonFilter);
	}


	@Override
	public List<String[]> getBatchComplnDatas(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.getBatchComplnDatas(commonFilter);
	}
//

	@Override
	public List<String[]> getSkillGapReportGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.getSkillGapReportGrid(commonFilter);
	}


	@Override
	public Workbook getSkillGapReportExcel(JSONObject colmodel, String format, CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.getSkillGapReportExcel(colmodel,format,commonFilter);
	}


	@Override
	public List<String[]> FillControlData(String keyid, String type) throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.FillControlData(keyid,type);
	}


	@Override
	public EntTlTragcalquad createAssmLevel(String UpdateList,String userid) throws Exception {
		// TODO Auto-generated method stub
	//	return this.newETReportDao.createAssmLevel(UpdateList,userid);
		return quadrantAssessmentServiceApi.insertRecord(UpdateList, userid);
	}


	@Override
	public Workbook getnomitnExportToExcel(JSONObject colmodel, String format, CommonFilter commonFilter1)
			throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.getnomitnExportToExcel(colmodel,format,commonFilter1);
	}


	@Override
	public List<String[]> getnomitnrpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.getnomitnrpt(commonFilter);
	}


	@Override
	public Workbook assemeExcel(CommonFilter commonFilter1, JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.assemeExcel(colmodel,format,commonFilter1);
	}


	@Override
	public List<String[]> getfourquadrant(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return newETReportDao.getfourquadrant(commonFilter);
	}


	@Override
	public String getfunctionaldata(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return newETReportDao.getfunctionaldata(commonFilter);
	}


	@Override
	public Workbook FourExcelReport(CommonFilter commonFilter, JSONObject tblJSONObj, String format,String imagepath) throws Exception {
		// TODO Auto-generated method stub
		return newETReportDao.FourExcelReport(commonFilter,tblJSONObj,format,imagepath);
	}

	@Override
	public List<String[]> getTrainingSummRpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.newETReportDao.getTrainingSummRpt(commonFilter);
	}
	public Workbook getTrainingSummaryExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws  Exception {
		// TODO Auto-generated method stub
		return newETReportDao.getTrainingSummaryExcel(colmodel,format, commonFilter);
		}	
}
