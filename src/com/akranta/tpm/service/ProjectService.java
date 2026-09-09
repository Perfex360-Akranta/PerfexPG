package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlDmcfipworkflow;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.KznTlDmcfipcreationmst;
import com.akranta.tpm.model.KznTlProjectChecklistLink;
import com.akranta.tpm.model.KznTlProjectKaizenLink;
import com.akranta.tpm.model.KznTlProjectKpiLink;
import com.akranta.tpm.model.KznTlProjectResourceLink;
import com.akranta.tpm.model.KznTlProjectcreationmst;
import com.akranta.tpm.model.KznTlProjectdmaicstatus;
import com.akranta.tpm.model.KznTlProjectmaicMileMst;

public interface ProjectService {

	public List<String[]> getResources(CommonFilter commonFilter) throws Exception;

	public List<String[]> getMilesStone(String keyid) throws Exception;

	public List<String[]> getKaizen(String masterId, String flid) throws Exception;
	public List<String[]> getDmaic(String projectId) throws Exception;
	public List<String[]> getApproval(CommonFilter commonFilter) throws Exception;
	public List<String[]> getKpi(String keyid) throws Exception;
	public List<String[]> getProject(CommonFilter commonFilter) throws Exception;
	
	public List<String[]> getProjectview(CommonFilter commonFilter) throws Exception;
	public List<String[]> getAuthorization(String type) throws Exception;
	public KznTlProjectcreationmst create(KznTlProjectcreationmst newKznTlProjectcreationmst,KznTlProjectcreationmst existKznTlProjectcreationmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public KznTlProjectcreationmst update(KznTlProjectcreationmst newKznTlProjectcreationmst,KznTlProjectcreationmst existKznTlProjectcreationmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public KznTlDmcfipcreationmst createdmc(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,KznTlDmcfipcreationmst existKznTlDmcfipcreationmst,GenTlDmcfipworkflow newGenTlDmcfipworkflow) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public KznTlDmcfipcreationmst updatedmc(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,KznTlDmcfipcreationmst existKznTlDmcfipcreationmst,GenTlDmcfipworkflow newGenTlDmcfipworkflow,GenTlDmcfipworkflow existGenTlDmcfipworkflow) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public KznTlProjectcreationmst getRecall(String keyid) throws Exception;
	public KznTlDmcfipcreationmst getdmcRecall(String keyid) throws Exception;
	public GenTlDmcfipworkflow getwrkflpRecall(String DfiwkeyId) throws Exception;
	public GenTlDmcfipworkflow getdmcwrkflpRecall(String keyId) throws Exception;
	public KznTlProjectcreationmst delete(KznTlProjectcreationmst newKznTlProjectcreationmst)throws Exception;
	public KznTlDmcfipcreationmst dmcdelete(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,GenTlDmcfipworkflow newGenTlDmcfipworkflow)throws Exception;
	public List<String[]> getListOfIndicators(CommonFilter commonFilter) throws Exception;
	public KznTlProjectKpiLink create(KznTlProjectKpiLink newKznTlProjectKpiLink,KznTlProjectKpiLink existKznTlProjectKpiLink) throws Exception;
	public KznTlProjectResourceLink create(KznTlProjectResourceLink newKznTlProjectResourceLink,KznTlProjectResourceLink existKznTlProjectResourceLink) throws Exception;
	public KznTlProjectResourceLink delete(KznTlProjectResourceLink newKznTlProjectResourceLink,KznTlProjectResourceLink existKznTlProjectResourceLink) throws Exception;
	public List<String[]> getAllMilestones(CommonFilter commonFilter) throws Exception;
	public KznTlProjectmaicMileMst create(KznTlProjectmaicMileMst newKznTlProjectmaicMileMst,KznTlProjectmaicMileMst existKznTlProjectmaicMileMst) throws Exception;
	public KznTlProjectmaicMileMst getRecallMile(String keyid) throws Exception;
	public KznTlProjectmaicMileMst delete(KznTlProjectmaicMileMst newKznTlProjectmaicMileMst) throws Exception;
	public void deleteMilestone(String keyid) throws Exception;
	public List<String[]> getListOfKaizen(CommonFilter commonFilter) throws Exception;
	public KznTlProjectKaizenLink create(KznTlProjectKaizenLink newKznTlProjectKaizenLink,KznTlProjectKaizenLink existKznTlProjectKaizenLink) throws Exception;
	public KznTlProjectResourceLink update(KznTlProjectResourceLink newKznTlProjectResourceLink,KznTlProjectResourceLink existKznTlProjectResourceLink) throws Exception;
	public KznTlProjectResourceLink getRecallResource(String keyid) throws Exception;
	public List<String[]> getAllHistory(String dtlId) throws Exception;
	public Workbook getProjectCreationExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;
	
	public Workbook getdmcProjectviewExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;
	public List<KznTlProjectdmaicstatus> create(List<KznTlProjectdmaicstatus> kznTlProjectdmaicstatusList,List<KznTlProjectdmaicstatus> existKznTlProjectdmaicstatusLst)throws Exception;
	public KznTlProjectdmaicstatus delete(KznTlProjectdmaicstatus newKznTlProjectdmaicstatus) throws ValidationExceptions,BusinessApplicationExceptions,Exception;
	public String getMileStoneStages(String kznKeyId)throws Exception;
    /**/
	public List<String[]> getChangeprolead(CommonFilter commonFilter) throws Exception;

	public List<String[]> getprojectchamp(String Keyid) throws Exception;

	public Workbook getChangeproleadExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;

	public String singleResponsiblty(String Projectkeyid, String responsevalue,String oldemployeeid) throws BusinessApplicationExceptions, Exception;
    /* */
	public List<String[]> getChangeproleader(CommonFilter commonFilter) throws Exception;

	public List<String[]> getprojectleader(String Keyid) throws Exception;

	public Workbook getChangeproleaderExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;

	public String singleleadResponsiblty(String Projectkeyid, String responsevalue,String oldemployeeid) throws BusinessApplicationExceptions, Exception;
    /* */
	public String getDefineStage(String hdnKzpmKeyid)throws Exception;

	public String getStage(String hdnKzpmKeyid)throws Exception;

	public String getWorkFlowStaus(String kznKeyId, String refType,
			String transCode,boolean isUpdate,String wfStatus)throws Exception;
	
	public String getdmcWorkFlowStaus(String kznKeyId, String refType,
			String transCode,boolean isUpdate,String wfStatus)throws Exception;

	public String getMaicStage(String kznKeyId,	String stage) throws Exception;

	public KznTlProjectResourceLink create(List<KznTlProjectResourceLink> kznTlProjectResourceLinkList)throws ValidationExceptions,BusinessApplicationExceptions,Exception;
	public KznTlProjectKpiLink createKpi(List<KznTlProjectKpiLink> kznTlProjectKpiLinkList)throws ValidationExceptions,BusinessApplicationExceptions,Exception;

	public List<String[]> getListNewResources(CommonFilter commonFilter)throws Exception;

	public KznTlProjectcreationmst getAllStageStatus(String kznKeyId) throws Exception;
	
	public String[] getdmcAllStageStatus(String kznKeyId) throws Exception;
	
	public String getDMTLeader(KznTlProjectcreationmst newKznTlProjectcreationmst)throws Exception;
	
	public String getdmcDMTLeader(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst)throws Exception;
	
	public List<String[]> getProjectCreationList(CommonFilter commonFilter)throws Exception;
	
	public List<String[]> getdmcProjectCreationList(CommonFilter commonFilter)throws Exception;

	public Workbook getprojectlistExcel(JSONObject colmodel, String format,	CommonFilter commonFilter)throws Exception;
	
	public Workbook getdmcprojectlistExcel(JSONObject colmodel, String format,	CommonFilter commonFilter)throws Exception;

	public List<ComboBox> getProjectMetricsKpiIndicator(ComboFilter comboFilter,String pillarCode,String flid) throws Exception ;
	
	public List<ComboBox> getdmcemployeecombo(ComboFilter comboFilter,String flid) throws Exception ;
	
	public List<ComboBox> getcombowave(String condSql, ComboFilter comboFilter)throws Exception;
	
	public List<String[]> getProjectCheckList(String stage,String projectId) throws Exception ;
	
	public void createCheckList(List<KznTlProjectChecklistLink> kznTlProjectChecklistLinkList,String createdBy) 	throws Exception;
	

	public List<String[]> getDmaiccunt(CommonFilter commonFilter) throws Exception;
	

	public List<String[]> getpiechart(CommonFilter chrtCommonFilter) throws Exception;

	public Workbook getdmaicExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception;
	public Workbook getDMCdmaicExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception;

	public List<String[]> getdmcDmaiccunt(CommonFilter commonFilter) throws Exception;

	public List<String[]> getdmcpiechart(CommonFilter chrtCommonFilter) throws Exception;

	public Workbook getdmcdmaicExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception;
	
	public List<String[]> getJhmemberaddresources(CommonFilter commonFilter1,
			String flid)throws Exception;

	public List<String[]> getdmcJhmemberaddresources(CommonFilter commonFilter1,
			String flid)throws Exception;
	
	public void DeleteJhMemberRecord(String keyid)throws Exception;

	public List<String[]> setInsertQuery(String rolename, String refId,
			String nxtrole, String trnscode, String lstlvl, String flId,
			String roleid, String status) throws Exception;

	public String InsertPbuHead(String kzpmKeyid, String mode, String flid) throws Exception;

	public List<String[]> getProjectnewview(CommonFilter commonFilter) throws Exception;
	public List<String[]> getFIpCount(CommonFilter commonFilter) throws Exception;
	public Workbook getFIpWaveCountExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception;
	   public List<String[]> getFIpcountGraph(CommonFilter commonFilter,String rowId)throws Exception;
	   public List<String[]> getFIpWaveBenefitCount(CommonFilter commonFilter) throws Exception;
	   public Workbook getFIpWaveCountExportToExcel(CommonFilter commonFilter1,JSONObject colmodel, String format
				) throws Exception;
	    public List<ComboBox> getJHKaizenBeltComboList(ComboFilter combofilter)throws Exception;

		public GenTlWorkflowInfo autoapproveDMAIC(GenTlWorkflowInfo genTlWorkflowInfo,
				GenTlWorkflowInfo existGenTlWorkflowInfo, String projectleader, String kKChampion, String fIPNO) throws Exception;

		public KznTlProjectcreationmst updateMAICStatus(KznTlProjectcreationmst kznTlProjectcreationmst, String stage)
				throws ValidationExceptions, BusinessApplicationExceptions, Exception;

		public String InsertPbuHeadClosure(String kzpmKeyid, String mode, String flid) throws Exception; 
		
		public void ProjectServiceImplJwt(String JwtToken);

}
