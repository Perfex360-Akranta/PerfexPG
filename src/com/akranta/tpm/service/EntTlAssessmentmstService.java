package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.EntTlAssessmentmstBean;
import com.akranta.tpm.bean.MultiBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlAssessmentmst;
import com.akranta.tpm.model.EntTlAssessmentdtl;
import com.akranta.tpm.model.EntTlMultiskilldialymap;
import com.akranta.tpm.model.EntTlMultiskillempmap;
public interface EntTlAssessmentmstService 
{
	public EntTlAssessmentmst create(EntTlAssessmentmst newEntTlAssessmentmst,EntTlAssessmentmst existEntTlAssessmentmst,
			EntTlAssessmentmstBean EntTlAssessmentmstBean)throws Exception;
	public EntTlAssessmentmst update(EntTlAssessmentmst newEntTlAssessmentmst,EntTlAssessmentmst existEntTlAssessmentmst,
			EntTlAssessmentmstBean EntTlAssessmentmstBean)throws Exception;
	public EntTlAssessmentmst delete(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception;
	public EntTlAssessmentmst select(EntTlAssessmentmst EntTlAssessmentmst)throws Exception;
	public List<EntTlAssessmentdtl> selectAssessmentList(EntTlAssessmentdtl entTlAssessmentdtl)throws Exception;
	public List<String[]> getAssessmentList(EntTlAssessmentdtl EntTlAssessmentdtl) throws Exception;	
	public List<String[]> getMainGrid(CommonFilter commonFilter) throws Exception ;
	public List<String[]> getAll(CommonFilter commonFilter) throws Exception ;
	public int selectCount(CommonFilter commonFilter) throws Exception ;
	public List<String[]> getAssessmentGrid(CommonFilter commonFilter,String progId,String batchId,String evlType) throws Exception ;
	public int selectAssessmentCount(CommonFilter commonFilter,String progId,String batchId) throws Exception ;
	public List<ComboBox> getProgramComboList(CommonFilter commonFilter)throws Exception ;
	public List<ComboBox> getBatchComboList(CommonFilter commonFilter)throws Exception ;
	public List<ComboBox> getTopicComboList(CommonFilter commonFilter)throws Exception ;
	public List<ComboBox> getTraingingAreaComboList(CommonFilter commonFilter)throws Exception ;
	public String getEvaluationNo(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception ;
	public EntTlAssessmentmst getEvaluationLatest(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception ;
	public EntTlAssessmentdtl getAssessmentDetails(EntTlAssessmentdtl entTlAssessmentdtl)throws Exception ;
	public List<String[]> getCheckList(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception;
	public List<String[]> getAssessmentDetailsgridData(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception ;
	public List<String[]> getTrnAreaRoleEmpView(
			EntTlAssessmentmst entTlAssessmentmst)throws Exception ;
	public Workbook getTrnAreaRoleEmpexcel(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public Workbook getbatchProgexcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public Workbook getAssesmentListexcel(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public String selectTrainingAreaPath(EntTlAssessmentmst entTlAssessmentmst)throws Exception;
	public String getSkillRating(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception;
	public String getPreviousRating(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception;
	public String getMaxEvlDate(EntTlAssessmentmst entTlAssessmentmst);
	public String getMinEvlDate(EntTlAssessmentmst entTlAssessmentmst);
	public Boolean preEvlExists(EntTlAssessmentmst entTlAssessmentmst);
	public String getCutOff(String assEmpKeyId, String topicId,
			String assRoleKeyId, String evalType, String mode, String trarkey, String result, String asmdKeyId)throws Exception;
	public String getpreExist(String assEmpKeyId, String topicId, String evalType)throws Exception;
	public String getProgid(String batchId)throws Exception;
	public List<String[]> getMultiSkillfillgriddata(String flid)throws Exception;
	public List<String[]> getMultiSkillAssessmentfillgriddata()throws Exception;
	public List<String[]> getEmployeerole(CommonFilter commonFilter)throws Exception ;
	public EntTlMultiskillempmap create(
			List<EntTlMultiskillempmap> lstEntTlMultiskillempmap,
			EntTlMultiskillempmap existEntTlMultiskillempmap,
			MultiBean multiBean) throws Exception;
	public EntTlMultiskillempmap update(
			List<EntTlMultiskillempmap> lstEntTlMultiskillempmap,
			EntTlMultiskillempmap existEntTlMultiskillempmap,
			MultiBean multiBean) throws Exception;
	public EntTlMultiskillempmap deleteMultiSkill(
			EntTlMultiskillempmap newEntTlMultiskillempmap,
			EntTlMultiskillempmap existEntTlMultiskillempmap,
			MultiBean multiBean) throws Exception;
	public List<String[]> getemployeeMaster(CommonFilter commonFilter) throws Exception;
	public EntTlMultiskillempmap selectmaster(String flid) throws Exception;
	public List<String[]> getemployeeDate(CommonFilter commonFilter) throws Exception;
	public Workbook getmultiSkillExcel (JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	public EntTlMultiskilldialymap create(
			List<EntTlMultiskilldialymap> lstEntTlMultiskilldialymap,
			EntTlMultiskilldialymap existEntTlMultiskilldialymap,
			MultiBean multiBean) throws Exception;
	public List<String[]> getemployees(CommonFilter commonFilter) throws Exception ;
	public EntTlMultiskilldialymap selectFlid(String flid,String date) throws Exception;
	public Workbook getmultiSkillDate(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	public List<String[]> getemployeeReport(CommonFilter commonFilter) throws Exception;
	public Workbook getmultiSkillReport(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	

}
