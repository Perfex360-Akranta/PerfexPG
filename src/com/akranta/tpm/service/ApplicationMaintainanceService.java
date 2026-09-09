package com.akranta.tpm.service;

import java.util.List;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;

public interface ApplicationMaintainanceService {
	List<String[]> getEmployeeLocation(CommonFilter commonFilter) throws Exception;
	Object LocationTransfer(String empKeyid, String empLocation) throws Exception;
	public List<String[]> getEmployeeList(CommonFilter commonFilter) throws Exception;
	public List<String[]> getEmpActiveData(GridParams gridParams,String location) throws Exception;
    public void UpdateActive(String EmpKeyid,String ValidTill,String remarks,String EmpType) throws Exception;
	public List<String[]> getKaizenData(GridParams gridParams,CommonFilter commonFilter) throws Exception;
	public void UpdateKaizenDate(String KaizenKeyid,String KaizenDate) throws Exception;
	public List<String[]> getSusaData(GridParams gridParams,CommonFilter commonFilter) throws Exception;
	public void DeleteSusa(String SusaKeyid) throws Exception;
	public List<String[]> getTrainingData(GridParams gridParams,CommonFilter commonFilter) throws Exception;
	public void DeleteTraining(String TrainingKeyid) throws Exception;
	public List<String[]> getSuggestionData(GridParams gridParams,CommonFilter commonFilter) throws Exception;
	public String getDmtOriginalId(String Flid)throws Exception;
	public String getJHOriginalId(String Flid)throws Exception;
	public void DeleteSuggestion(String SuggKeyid) throws Exception;
	public List<String[]> getKaizenDeleteData(GridParams gridParams,CommonFilter commonFilter) throws Exception;
	public void DeleteKaizen(String KaizenKeyid) throws Exception;
	public List<String[]> getWhyWhyDeleteData(GridParams gridParams,CommonFilter commonFilter) throws Exception;
	public void DeleteWhyWhy(String WhyWhyKeyid) throws Exception;
	public List<String[]> getLossDeleteData(GridParams gridParams,CommonFilter commonFilter) throws Exception;
	public void DeleteLoss(String LossKeyid) throws Exception;
	public List<String[]> getActionPlanDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception;
	public void DeleteActionPlan(String ActionPlanKeyid) throws Exception;
	public List<String[]> getNearMissDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception;
	public void DeleteNearMiss(String NearMissKeyid) throws Exception;
	public List<String[]> getAbnClosureData(GridParams gridParams, CommonFilter commonFilter) throws Exception;
    //public void AbnClosure(String AbnmKeyid,String Status,String CounterMeasure,String CompletedDate,String CompletedBy) throws Exception;
    public void AbnClosure(List<AbnTlAbnormality> abnList) throws Exception;
	public List<String[]> getActionPlanClosureData(GridParams gridParams, CommonFilter commonFilter) throws Exception;
    public void ActionPlanClosure(String ActionPlanId,String DetailId,String Status,String CompletedOn,String CompletedBy,String CounterMeasure) throws Exception;
	public List<String[]> getFIProjectData(GridParams gridParams, CommonFilter commonFilter) throws Exception;
	public void UpdateFIProjectDate(String FIProjectId,String EndDate) throws Exception;
	public List<String[]> getAppMaintGrid(GridParams gridParams, CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getAdminMenu(ComboFilter comboFilter) throws Exception;
	public List<String[]> getEmployeeRole(GridParams gridParams, CommonFilter commonFilter) throws Exception;
	public List<String[]> getRoleList(GridParams gridParams, CommonFilter commonFilter) throws Exception;
	public List<String[]>  getlevelrole(String flId) throws Exception;
	void UserInActive(String empKeyid, String validTill, String remarks)throws Exception;
	public List<String[]> getAbnDeleteData(GridParams gridParams, CommonFilter commonFilter)throws Exception;
	public void deleteAbnormality(String abnKeyid)throws Exception;
	List<String[]> getUSerList(CommonFilter commonFilter)throws Exception;
	public void AupUSerInActive(String empIds)throws Exception;
	
	public void ApplicationMaintainanceServiceImplJwt(String JwtToken);

	List<String[]> getKaizenApprovalDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception;
	
	List<ComboBox> getKaizenNoCombo(ComboFilter comboFilter) throws Exception;

	String[] getKaizenBenefitTypeAndFlid(String kznKeyid) throws Exception;
		
	String getEmpRoleIds(String empId) throws Exception;

	public List<String[]> getWorkFlowTransData(GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception;
		
	String getWorkflowRefRoleId(String empId,String flId,String transCode) throws Exception;

	void revokeKaizenApprovalWorkflow(String refId,List<String> wrinKeyIds) throws Exception;
}
