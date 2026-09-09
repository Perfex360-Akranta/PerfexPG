package com.akranta.tpm.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
//import com.akranta.tpm.bean.FieldObservBean;
//import com.akranta.tpm.bean.QCLoggingBean;
//import com.akranta.tpm.model.BadgeTrackMst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.FieldObservationDtl;
//import com.akranta.tpm.model.FieldObservationdesc;
//import com.akranta.tpm.model.FieldObservationmst;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlNearmissreportmstnew;
import com.akranta.tpm.model.HazopDtl;
import com.akranta.tpm.model.HazopMst;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.model.MOCPssrReccommend;
import com.akranta.tpm.model.MOCReccommendation;
import com.akranta.tpm.model.MocClosure;
import com.akranta.tpm.model.MocPssrdtl;
import com.akranta.tpm.model.MocPssrmst;
import com.akranta.tpm.model.MocRfQuestions;
import com.akranta.tpm.model.MocRfcBasismst;
import com.akranta.tpm.model.MocRfcmst;
import com.akranta.tpm.model.MocRfcmstNew;
import com.akranta.tpm.model.MocTeamConfigmst;
import com.akranta.tpm.model.MocTeamConfigmstNew;
//import com.akranta.tpm.model.QCLoggingDtl;
//import com.akranta.tpm.model.QcLoggingmst;
//import com.akranta.tpm.model.Qclogdesc;
import com.akranta.tpm.model.WhatifDtl;
import com.akranta.tpm.model.WhatifMst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface MocNewService {
public List<String[]> getQuestionaire(CommonFilter commonFilter) throws Exception;
public List<String[]> getPSSR(String unsafeact) throws Exception;
public String getProbablityVal(String prob)throws Exception ;
public String getSeviorityVal(String sev)throws Exception ;
public String getRiskLevel(String riskVal)throws Exception;
public List<String[]> getCRBasis(CommonFilter commonFilter) throws Exception;
public List<String[]> getBasisofChange( String MocKeyid) throws Exception;
public List<String[]> getMOCTeam(CommonFilter commonFilter) throws Exception;
public List<String[]> getMOCClosure(CommonFilter commonFilter) throws Exception;
public List<String[]> getMocRelatedData(CommonFilter commonFilter) throws Exception;
public Workbook getMOCModificationExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws  Exception;
public List<ComboBox> getMocTypeComboList(ComboFilter combofilter) throws Exception;
public List<ComboBox> getMocNatureComboList(ComboFilter combofilter) throws Exception;
public MocRfcmstNew createMOC(MocRfcmstNew mocRfcmst, MocRfcmstNew existMocRfcmst,MocRfcBasismst mocRfcBasismst,MocTeamConfigmstNew mocTeamConfigmst) throws Exception;
public List<MocRfcBasismst> createMOCBasis(List<MocRfcBasismst> mocRfcBasismst, String createdBy,
		MocRfcmstNew mocRfcmst) throws Exception;
public MocRfcmstNew updateMOC(MocRfcmstNew mocRfcmst, MocRfcmstNew existMocRfcmst,String MocId, String Nature ,String Desc,String Detail, String Dmt,String Jh,String Initiator,String MOCTitle,String MocType,String noOfDays) throws Exception;
public List<MocRfcBasismst> updateMOCBasis(List<MocRfcBasismst> mocRfcBasismst, String createdBy,
		MocRfcmstNew mocRfcmst) throws Exception;


public List<MocTeamConfigmstNew> createMOCTeam(List<MocTeamConfigmstNew> mocTeamConfigmst,
		String createdBy, MocRfcmstNew mocRfcmst) throws Exception;
public MocRfcmstNew getGridMocData(String mocKeyid) throws Exception;
public List<MocRfQuestions> createMocRfQuestions(List<MocRfQuestions> employeeAddList,String MocKeyid) throws Exception;
public List<MocRfcBasismst> createBasis(List<MocRfcBasismst> employeeAddList, String mocKeyid) throws Exception;
public List<String[]> getApprovalList(String mocKeyid) throws Exception;
public List<String[]> getMOCTeamSuccess(CommonFilter commonFilter, String mocKeyId) throws Exception;
public List<ComboBox> getMocGuideWordComboList(ComboFilter combofilter) throws Exception;
//public List<String[]> getInitialApproval(String keyid, String empId, String status, String date, String remarks) throws Exception;
public MocTeamConfigmst getInitialApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
		String keyid, String empId,String status, String date, String remarks,String MocKeyid) throws Exception;
public List<String[]> getHazopApprovalList(String mocKeyid) throws Exception;
public List<String[]> getFinalApprovalList(String mocKeyid) throws Exception;
public MocTeamConfigmst getFinalApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
		String keyid, String empId, String status, String date, String remarks,String MocKeyId) throws Exception;
public MocTeamConfigmst getHazopApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
		String keyid, String empId, String status, String date, String remarks,String MocKeyId) throws Exception;
public WhatifMst createWhatif(WhatifMst newWhatifMst,WhatifMst existWhatifMst,String Keyid) throws BusinessApplicationExceptions,ValidationException,Exception;
//public WhatifMst UpdateWhatif(WhatifMst newWhatifMst,WhatifMst existWhatifMst,String CreatedBy,String WhatifKeyId) throws Exception;
public void DeleteWhatifRow(String keyid)throws Exception;
public List<String[]> getWhatIfList(CommonFilter commonFilter) throws Exception;
public  List<ComboBox> getSeviorityComboList(ComboFilter  comboFilter) throws Exception;
public List<ComboBox> getRiskLevelComboList(ComboFilter sivFilterComboFilter)throws Exception;
public List<ComboBox> getProbablityComboList(ComboFilter probablityComboFilter) throws Exception;
public HazopMst createHazop(HazopMst newHazopMst,HazopMst existHazopMst,String Keyid) throws BusinessApplicationExceptions,ValidationException,Exception;
//public HazopMst UpdateHazop(HazopMst newHazopMst,HazopMst existHazopMst, String HazopKeyId,String CreatedBy) throws Exception;
public void DeleteHazopRow(String keyid)throws Exception;
// Vignesh 07sep2026
public void DeletePssrReccommendationRow(String keyid) throws Exception;
public String getPssrActionPlanKey(
        String mocKeyid,
        String psrrKeyid) throws Exception;

public List<String[]> getHazopList(CommonFilter commonFilter) throws Exception;
public List<MocClosure> createMocClosureQuestions(List<MocClosure> mocClosureList, String mocKeyidClosure) throws Exception;
public MocPssrmst createPssrCheckList(MocPssrmst newwMocPssrmst, MocPssrmst existMocPssrmst,String Keyid) throws Exception;
public List<MocPssrdtl> createPssrCheckListdtl(List<MocPssrdtl> mocPssrdtl, String createdBy, MocPssrmst newwMocPssrmst) throws Exception;
public String getActionDKeyid(String Keyid) throws Exception;
public String getPssrKeyid(String pssrKeyid) throws Exception;
public GenTlActionplanmst createActionPlan(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newActionplandtl,
		String MocKeyidRec) throws Exception;
public List<String[]> getPssrReccommendation(CommonFilter commonFilter) throws Exception;
public WhatifMst getWhatIfMstData(String whatifKey) throws Exception;
public MocPssrmst getPssrMstData(String pssrKey) throws Exception;
public HazopMst getHazopMstData(String hazopKey) throws Exception;
public KznTlKaizenbankmst getGridKaizenData(String suggestionId) throws Exception;
public String getPssrCount(String mocKeyid) throws Exception;
public String getApprovalCount(String mockeyid) throws Exception;
public String getCompletedApprovalCount(String mockeyid) throws Exception;
public String getMaxGroupCount(String mockeyid) throws Exception;
public String getNextGroupCount(String mockeyid) throws Exception;
public String getHazopApprovalCount(String mockeyid) throws Exception;
public String getHazopCompletedApprovalCount(String mockeyid) throws Exception;
public String getHazopMaxGroupCount(String mockeyid) throws Exception;
public String getHazopNextGroupCount(String mockeyid) throws Exception;
public String getFinalApprovalCount(String mockeyid) throws Exception;
public String getFinalCompletedApprovalCount(String mockeyid) throws Exception;
public String getFinalMaxGroupCount(String mockeyid) throws Exception;
public String getFinalNextGroupCount(String mockeyid) throws Exception;
public List<String[]> getHazopReccommendation(CommonFilter commonFilter) throws Exception;
public String getMOCStatusCount(String mockeyid) throws Exception;
public List<MOCReccommendation> createReccommendation(List<MOCReccommendation> mOCReccommendationList,
		String mocKeyidRec,String detailid) throws Exception;
public GenTlActionplanmst createActionPlan(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newActionplandtl,
		String mocKeyidRec, String detailid) throws Exception;
/*public MOCReccommendation getUpdateReccommendation(MOCReccommendation newMOCReccommendation,
		MOCReccommendation existMOCReccommendation, String recKeyid, String responsiblity, String actionPlanStatus,
		String targetDate, String mocKeyidRec) throws Exception;*/
public String getMocCompletionUpdate(String keyId) throws Exception;
public String getFinalMaxGroupCountStatus(String mockeyid) throws Exception;
public String getnextApprovalKeyid(String mocKeyid) throws Exception;
public String getHazopnextApprovalKeyid(String mocKeyid) throws Exception;
public String getFinalApprovalKeyid(String mocKeyid) throws Exception;
public String getWhatifHazopCount(String mockeyid) throws Exception ;
public MOCReccommendation UpdateReccommendation(String mocKeyidRec,
		String detailid, String targetDate, String actionPlanStatus,
		String responsiblity) throws Exception;
public MOCPssrReccommend UpdatePssrReccommendation(String mocKeyidRec,
		String psrrKeyId, String targetDate, String actionPlanStatus,
		String responsiblity) throws Exception;
public List<MOCPssrReccommend> createPSSRCheckReccommendation(List<MOCPssrReccommend> mOCPssrReccommendList,
		String mocKeyidRec, String detailid) throws Exception;
public MOCPssrReccommend UpdatePSSRReccommendation(String mocKeyidRec, String detailid, String targetDate,
		String actionPlanStatus, String responsiblity, String reccommendation) throws Exception;
public String getClosureCount(String mockeyid) throws Exception;
public Workbook NewDashboardExcelView(String format, String path, String mocKeyid, String flid, String dMT, String jH, String currentDate, String nature, String type, String detail, String pCHange, String title, String initiator,String MOCDate,String WhatifFacility,String WhatifTeam,String WhatifDate, String suggestion, String suggestionId, String hazopFacility, String hazopTeam, String hazopNode, String hazopDesign, String hazopDate,String pidNo,String PssrFacility,String MOCDetails) throws Exception;
public String getDMTName(String dMTId) throws Exception;
public String getJHName(String jHId) throws Exception;
/*public String getPssrReccommendationCount(String mockeyid) throws Exception;
public String getPssrReccommendationCompleted(String mockeyid) throws Exception;*/
public List<String[]> getPSITeamMailIds(String mockeyid) throws Exception;
public String getPssrReccommendationCount(String mockeyid)  throws Exception;
public String getPssrReccommendationCompleted(String mockeyid)  throws Exception;
public String getJHLeader(String sugflid) throws Exception;
public String getDMTLeader(String sugflid)throws Exception;
public String getProcess(String sugflid)throws Exception;
public String getMech(String sugflid)throws Exception;
public String getInstrument(String sugflid) throws Exception;
public String getCivil(String sugflid)throws Exception;
public String getElect(String sugflid)throws Exception;
public String getEHSHead(String sugflid)throws Exception;
public String getEHSManager(String sugflid)throws Exception;
public String getPbuHead(String sugflid)throws Exception;
//mano
public String getPMMechChampion(String sugflid)throws Exception;
public String getPMEIChampion(String sugflid)throws Exception;
public String getPillarChampion(String sugflid)throws Exception;
public String getSafetymanager(String sugflid)throws Exception;
public List<MOCReccommendation> createReccommendationNew(List<MOCReccommendation> mOCReccommendationList,
		String mocKeyidRec, String detailid,WhatifMst whatifMs,String catogery) throws Exception;

// mano
Workbook MocExcelSheet(String format, String path, String mocKeyid,
		String flid, String DMT, String JH, String currentDate, String nature,
		String type, String detail, String pCHange, String title,
		String initiator, String MOCDate, String WhatifFacility,
		String WhatifTeam, String WhatifDate, String suggestion,
		String SuggestionId, String hazopFacility, String hazopTeam,
		String hazopNode, String hazopDesign, String hazopDate, String pidNo,
		String PssrFacility, String MOCDetails) throws Exception;

String getOthersNotes(String mocKeyid) throws Exception;
public void updateMocCapex(String mocKeyid, String capex, String modifiedBy) throws Exception;

public List<MOCReccommendation> createReccommendationNewHazop(List<MOCReccommendation> mOCReccommendationList,
		String mocKeyidRec, String detailid,HazopMst hazopmst,String catogery) throws Exception;


public String getMocBySuggestionId (String SuggestionId)throws Exception;


boolean isMocApprovalUser(String mocKeyid, String userid) throws Exception;

//New Addition of parameters - 21 May 2026
public WhatifMst UpdateWhatif(WhatifMst newWhatifMst,WhatifMst existWhatifMst,String CreatedBy,String WhatifKeyId,List<MOCReccommendation> mOCReccommendationList,String flid,String elementId) throws Exception;
//New Addition of parameters - 21 May 2026

//New Addition of parameters - 21 May 2026
public HazopMst UpdateHazop(HazopMst newHazopMst,HazopMst existHazopMst, String HazopKeyId,String CreatedBy,List<MOCReccommendation> mOCReccommendationList,String flid,String elementId) throws Exception;
//New Addition of parameters - 21 May 2026

List<String[]> getPillarChampionEmployeeList(String sugflid) throws Exception;

//Vignesh - 22 May for Excel
JSONArray readWhatIfExcelRows(String excelFileName) throws Exception;

JSONArray readHazopExcelRows(String excelFileName) throws Exception;

String Delete(String keyid) throws Exception;

public List<String[]> getInitialApprovedPrimaryRoleList(String mocKeyid) throws Exception;

public List<MocTeamConfigmstNew> updateMOCTeam(
	        List<MocTeamConfigmstNew> team,
	        String modifiedBy,
	        MocRfcmstNew mocRfcmst) throws Exception;
	

	public List<String[]> getMocTeamWorkflowRoleLock(String mocKeyid) throws Exception;

	public boolean isMocInitialApprovalCompleted(String mocKeyid) throws Exception;
	
public String getWhatifMstKeyid (String mocKeyid)throws Exception;
	
	public String getHazopMstKeyid (String mocKeyid)throws Exception;
	
	public String getPssrMstKeyid (String mocKeyid)throws Exception;

}

