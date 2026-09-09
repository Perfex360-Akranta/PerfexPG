package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlAssessmentdtl;
import com.akranta.tpm.model.EntTlAssessmentmst;
import com.akranta.tpm.model.EntTlMultiskilldialymap;
import com.akranta.tpm.model.EntTlMultiskillempmap;

public interface EntTlAssessmentmstDao {

	public abstract EntTlAssessmentmst create(EntTlAssessmentmst entTlAssessmentmst) throws Exception;
	public abstract EntTlAssessmentmst update(EntTlAssessmentmst entTlAssessmentmst) throws Exception;
	public abstract EntTlAssessmentmst delete(EntTlAssessmentmst entTlAssessmentmst) throws Exception;
	public EntTlAssessmentmst select(EntTlAssessmentmst EntTlAssessmentmst) throws Exception ;
	public List<EntTlAssessmentdtl> selectAssessmentList(EntTlAssessmentdtl entTlAssessmentdtl)throws Exception;
	public List<String[]> getAssessmentList(EntTlAssessmentdtl entTlAssessmentdtl) throws Exception;
	public List<String[]> getMainGrid(CommonFilter commonFilter) throws Exception ;
	public List<String[]> getAll(CommonFilter commonFilter) throws Exception ;
	public List<String[]> getAssessmentGrid(CommonFilter commonFilter,String progId,String batchId,String evlType) throws Exception ;
	public int selectCount(CommonFilter commonFilter) throws Exception;
	public int selectAssessmentCount(CommonFilter commonFilter,String progId,String batchId) throws Exception ;
	public String getEvaluationNo(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception ;
	public EntTlAssessmentmst getEvaluationLatest(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception ;
	public EntTlAssessmentdtl getAssessmentDetails(EntTlAssessmentdtl entTlAssessmentdtl)throws Exception ;
	public List<String[]> getCheckList(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception;
	public List<String[]> getAssessmentDetailsgridData(EntTlAssessmentmst EntTlAssessmentmst)throws Exception ;
	public List<String[]> getTrnAreaRoleEmpView(EntTlAssessmentmst EntTlAssessmentmst)throws Exception ;
	public Workbook getTrnAreaRoleEmpexcel(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public Workbook getbatchProgexcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public Workbook getAssesmentListexcel(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public String selectTrainingAreaPath(EntTlAssessmentmst entTlAssessmentmst)throws Exception;
	public String getSkillRating(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception;
	public String getPreviousRating(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception;	
	public String getMaxEvlDate(EntTlAssessmentmst entTlAssessmentmst);
	public String getMinEvlDate(EntTlAssessmentmst entTlAssessmentmst);
	public Boolean preEvlExists(EntTlAssessmentmst entTlAssessmentmst);
	public abstract String getCutOff(String assEmpKeyId, String topicId,
			String assRoleKeyId, String evalType, String mode, String trarkey, String result, String asmdKeyId)throws Exception;
	public abstract void checkEvalDateEqCurrentDate(String asmmEvaluationType,
			String asmmEvaluationDate, String asmmEmpmKeyid)throws Exception;
	public abstract String getpreExist(String assEmpKeyId, String topicId, String evalType)throws Exception;
	public abstract String getProgid(String batchId)throws Exception;
	public abstract List<String[]> getMultiSkillfillgriddata(String flid)throws Exception;
	public abstract List<String[]> getMultiSkillAssessmentfillgriddata()throws Exception;
	public abstract List<String[]> getEmployeerole(CommonFilter commonFilter) throws Exception;
	public abstract EntTlMultiskillempmap create(
			EntTlMultiskillempmap newEntTlMultiskillempmap) throws Exception;
	public abstract EntTlMultiskillempmap create(
			List<EntTlMultiskillempmap> lstEntTlMultiskillempmap) throws Exception;
	public abstract EntTlMultiskillempmap deleteMultiSkill(
			EntTlMultiskillempmap newEntTlMultiskillempmap) throws Exception;
	public abstract List<String[]> getemployeeMaster(CommonFilter commonFilter) throws Exception;
	public abstract EntTlMultiskillempmap selectmaster(String flid) throws Exception;
	public abstract List<String[]> getemployeeDate(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getmultiSkillExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws  Exception;
	public abstract List<String[]> getemployees(CommonFilter commonFilter) throws Exception;
	public abstract EntTlMultiskilldialymap selectFlid(String flid,String date) throws Exception;
	public abstract Workbook getmultiSkillDate(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getemployeeReport(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getmultiSkillReport(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception;
}

