package com.akranta.tpm.dao;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
//import com.akranta.tpm.bean.FieldObservBean;
import com.akranta.tpm.bean.GridParams;
//import com.akranta.tpm.model.BadgeTrackMst;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.FieldObservationDtl;
//import com.akranta.tpm.model.FieldObservationdesc;
//import com.akranta.tpm.model.FieldObservationmst;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlSusaAddBehaviormst;
import com.akranta.tpm.model.GenTlSusaParticipantmst;
import com.akranta.tpm.model.GenTlSusamstNew;
import com.akranta.tpm.model.HazopDtl;
import com.akranta.tpm.model.HazopMst;
import com.akranta.tpm.model.MOCPssrReccommend;
import com.akranta.tpm.model.MOCReccommendation;
import com.akranta.tpm.model.MocClosure;
import com.akranta.tpm.model.MocPssrdtl;
import com.akranta.tpm.model.MocPssrmst;
import com.akranta.tpm.model.MocRfQuestions;
import com.akranta.tpm.model.MocRfcBasismst;
import com.akranta.tpm.model.MocRfcmst;
import com.akranta.tpm.model.MocTeamConfigmst;
//import com.akranta.tpm.model.QCLoggingDtl;
//import com.akranta.tpm.model.QcLoggingmst;
//import com.akranta.tpm.model.Qclogdesc;
import com.akranta.tpm.model.WhatifDtl;
import com.akranta.tpm.model.WhatifMst;

import net.sf.json.JSONObject;

public interface MocDao {
   
	List<String[]> getQuestionaire(CommonFilter commonFilter) throws Exception;

	List<String[]> getPSSR(String unsafeact) throws Exception;
	public abstract String getProbablityVal(String prob)throws Exception;
	public abstract String getSeviorityVal(String sev)throws Exception;
	public abstract String getRiskLevel(String riskVal)throws Exception;

	List<String[]> getCRBasis(CommonFilter commonFilter) throws Exception;

	List<String[]> getBasisofChange( String MocKeyid) throws Exception;

	List<String[]> getMOCTeam(CommonFilter commonFilter) throws Exception;

	List<String[]> getMOCClosure(CommonFilter commonFilter) throws Exception;

	List<String[]> getMocRelatedData(CommonFilter commonFilter) throws Exception;
	public Workbook getMOCModificationExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;
	MocRfcmst createMOC(MocRfcmst mocRfcmst,MocRfcBasismst mocRfcBasismst,MocTeamConfigmst mocTeamConfigmst) throws Exception;

	List<MocRfcBasismst> createBasis(List<MocRfcBasismst> fillValuesMOCBasis, String createdBy, MocRfcmst mocRfcmst) throws Exception;

	//List<MocRfQuestions> save(List<MocRfQuestions> auditEnableList) throws Exception;

	//public List<MocRfQuestions> createQuestions(List<MocRfQuestions> fillValuesMOCQuestns, String createdBy) throws Exception;

	List<MocTeamConfigmst> createTeam(List<MocTeamConfigmst> fillValuesMOCTeam, String createdBy, MocRfcmst mocRfcmst) throws Exception;

	//public List<MocRfQuestions> createQuestions(List<MocRfQuestions> fillValuesMOCQuestns) throws Exception;



	public abstract MocRfcmst update(MocRfcmst mocRfcmst,String MocId,String Nature ,String Desc,String Detail, String Dmt,String Jh,String Initiator,String MOCTitle,String MocType) throws Exception;

	List<MocRfQuestions> createQuestionsNew(List<MocRfQuestions> fillValuesSessionEmployee,String MocKeyid) throws Exception;

	List<MocRfcBasismst> createBasis(List<MocRfcBasismst> fillValuesMOCBasis, String mocKeyid) throws Exception;

	List<String[]> getApprovalList(String mocKeyid) throws Exception;

	List<String[]> getMOCTeamSuccess(CommonFilter commonFilter, String mocKeyId) throws Exception;

   //List<String[]> getInitialApproval(String keyid, String empId, String status, String date, String remarks) throws Exception;

	MocTeamConfigmst getInitialApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
			String keyid, String empId,String status, String date, String remarks,String MocKeyid) throws Exception;

	List<String[]> getHazopApprovalList(String mocKeyid) throws Exception;

	List<String[]> getFinalApprovalList(String mocKeyid) throws Exception;

	MocTeamConfigmst getFinalApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
			String keyid, String empId, String status, String date, String remarks,String MocKeyId) throws Exception;

	MocTeamConfigmst getHazopApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
			String keyid, String empId, String status, String date, String remarks,String MocKeyId) throws Exception;
	public WhatifMst createWhatif(WhatifMst newWhatifMst,String Keyid) throws BusinessApplicationExceptions,ValidationException,Exception;
	public WhatifMst UpdateWhatif(WhatifMst newWhatifMst,String Keyid,String WhatifKeyId) throws BusinessApplicationExceptions,ValidationException,Exception;
	public void DeleteWhatifRow(String keyid)throws Exception;
	public List<String[]> getWhatIfList(CommonFilter commonFilter) throws Exception;
	public HazopMst createHazop(HazopMst newHazopMst,String Keyid) throws BusinessApplicationExceptions,ValidationException,Exception;
	public HazopMst UpdateHazop(HazopMst newHazopMst,String HazopKeyId) throws Exception;
	public void DeleteHazopRow(String keyid)throws Exception;
	public List<String[]> getHazopList(CommonFilter commonFilter) throws Exception;

	MocPssrmst createPssrCheckListC(MocPssrmst newwMocPssrmst, MocPssrmst existMocPssrmst,String Keyid) throws Exception;

	List<MocClosure> createClosureQuestionsNew(List<MocClosure> fillValuesClosure,String mocKeyidClosure) throws Exception;

	List<MocPssrdtl> createPssrCheckListdtl(List<MocPssrdtl> fillValuesPssrDetails, String createdBy,
			MocPssrmst newwMocPssrmst) throws Exception;
	/*List<MOCPssrReccommend> createMocReccommendation(List<MOCPssrReccommend> fillValuesReccommend,String Keyid) throws Exception;
	public List<MOCPssrReccommend> UpdateMocReccommendation(List<MOCPssrReccommend> mocReccommendList, String mocKeyidRec,String PsrrKeyId) throws Exception;
	*/
	public String getActionDKeyid(String Keyid) throws Exception;
    public String getPssrKeyid(String pssrKeyid) throws Exception;
	public abstract GenTlActionplanmst createActionPlan(GenTlActionplanmst newActionplanmst,GenTlActionplandtl newGenTlActionplandtl,String MocKeyidRec)throws Exception;

	List<String[]> getPssrReccommendation(CommonFilter commonFilter) throws Exception;
	public String getPssrCount(String mocKeyid) throws Exception;

	String getApprovalCount(String mockeyid) throws Exception;

	String getCompletedApprovalCount(String mockeyid) throws Exception;

	String getMaxGroupCount(String mockeyid) throws Exception;

	String getNextGroupCount(String mockeyid) throws Exception;

	String getHazopApprovalCount(String mockeyid) throws Exception;

	String getHazopCompletedApprovalCount(String mockeyid) throws Exception;

	String getHazopMaxGroupCount(String mockeyid) throws Exception;

	String getHazopNextGroupCount(String mockeyid) throws Exception;

	String getFinalApprovalCount(String mockeyid) throws Exception;

	String getFinalCompletedApprovalCount(String mockeyid) throws Exception;

	String getFinalMaxGroupCount(String mockeyid) throws Exception;

	String getFinalNextGroupCount(String mockeyid) throws Exception;

	List<String[]> getHazopReccommendation(CommonFilter commonFilter) throws Exception;

	String getMOCStatusCount(String mockeyid) throws Exception;

	List<MOCReccommendation> createReccommendation(List<MOCReccommendation> fillValuesReccommendation,
			String mocKeyidRec,String detailid) throws Exception;

	GenTlActionplanmst createActionPlanRec(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newActionplandtl,
			String mocKeyidRec, String detailid) throws Exception;

	/*MOCReccommendation getUpdateReccommendation(MOCReccommendation newMOCReccommendation,
			MOCReccommendation existMOCReccommendation, String recKeyid, String responsiblity, String actionPlanStatus,
			String targetDate, String mocKeyidRec) throws Exception;*/
	String getMocCompletionUpdate(String keyId) throws Exception;

	String getFinalMaxGroupCountStatus(String mockeyid) throws Exception;
	public String getnextApprovalKeyid(String mocKeyid) throws Exception;
	public String getHazopnextApprovalKeyid(String mocKeyid) throws Exception;
	public String getFinalApprovalKeyid(String mocKeyid) throws Exception;

	String getWhatifHazopCount(String mockeyid) throws Exception;

	MOCReccommendation UpdateReccommendation(String mocKeyidRec,
			String detailid, String targetDate, String responsiblity, String actionPlanStatus) throws Exception;

	MOCPssrReccommend UpdatePssrReccommendation(String mocKeyidRec,
			String psrrKeyId, String targetDate, String responsiblity,
			String actionPlanStatus) throws Exception;

	List<MOCPssrReccommend> createPSSRCheckReccommendation(List<MOCPssrReccommend> fillValuesPSSRCHECKReccommendation,
			String mocKeyidRec, String detailid) throws Exception;

	MOCPssrReccommend UpdatePSSRReccommendation(String mocKeyidRec, String detailid, String targetDate,
			String responsiblity, String actionPlanStatus, String reccommendation) throws Exception;

	String getClosureCount(String mockeyid) throws Exception;

	List<String[]> MOCWorkflow(String flid, String mocKeyid, String mOCDate, String jH, String dMT, String format,
			String path) throws Exception;

	String getDmtName(String dMTId) throws Exception;

	String getJHName(String jHId) throws Exception;

	List<String[]> RFC(String mocKeyid) throws Exception;
	List<String[]> MOCWorkflow(String mocKeyid) throws Exception;


	List<String[]> Question(String mocKeyid) throws Exception;

	List<String[]> WhatIf(String mocKeyid) throws Exception;

	List<String[]> Hazop(String mocKeyid) throws Exception;

	List<String[]> WHReccommend(String mocKeyid) throws Exception;

	List<String[]> PssrCheck(String mocKeyid) throws Exception;

	List<String[]> PssrReccommend(String mocKeyid) throws Exception;

	List<String[]> MOCClosure(String mocKeyid) throws Exception;

	List<String[]> InitialApprovals(String mocKeyid) throws Exception;

	List<String[]> HazopApprovals(String mocKeyid) throws Exception;

	List<String[]> FinalApprovals(String mocKeyid) throws Exception;

	String getPssrReccommendationCompleted(String mockeyid) throws Exception;

	String getPssrReccommendationCount(String mockeyid) throws Exception;

	List<String[]> getPSITeamMailIds(String mockeyid) throws Exception;

	 String getJHLeader(String sugflid) throws Exception;
	 String getDMTLeader(String sugflid)throws Exception;
	 String getProcess(String sugflid)throws Exception;
	 String getMech(String sugflid)throws Exception;
	 String getInstrument(String sugflid)  throws Exception;
	 String getCivil(String sugflid)throws Exception;
    	String getElect(String sugflid)throws Exception;
	 String getEHSHead(String sugflid)throws Exception;
	 String getEHSManager(String sugflid)throws Exception;
	 String getPbuHead(String sugflid)throws Exception;

	List<String[]> getRecipientId(String mocKeyid)throws Exception;



}
