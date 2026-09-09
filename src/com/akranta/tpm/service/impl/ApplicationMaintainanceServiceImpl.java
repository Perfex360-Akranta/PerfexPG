package com.akranta.tpm.service.impl;

import java.util.List;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.ApplicationMaintainanceDao;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.impl.ApplicationMaintainanceDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.service.ApplicationMaintainanceService;
import com.akranta.tpm.service.api.ApplicationMaintananceServiceApi;
import com.akranta.tpm.service.api.SkillIndexServiceApi;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.utils.CommonMessage;
public class ApplicationMaintainanceServiceImpl implements ApplicationMaintainanceService{

	private ApplicationMaintainanceDao applicationMaintainanceDao =null;
	private Validations validations;
	private CommonFilterDao commonFilterDao;
	ApplicationMaintananceServiceApi serviceApi;
	public ApplicationMaintainanceServiceImpl(DBActionTemplate dbActionTemplate) throws Exception{
		applicationMaintainanceDao = new ApplicationMaintainanceDaoImpl(dbActionTemplate);
		commonFilterDao=new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void ApplicationMaintainanceServiceImplJwt(String JwtToken){
    	try{
    		applicationMaintainanceDao.ApplicationMaintainanceDaoImplJwt(JwtToken);
    		serviceApi = new ApplicationMaintananceServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	public List<String[]> getEmployeeLocation(CommonFilter commonFilter) throws Exception{
		 //return applicationMaintainanceDao.getEmployeeLocation(commonFilter);
		String EmpKey=commonFilter.getKey();
		String Location=commonFilter.getType();
		return serviceApi.getEmployeeLocation(EmpKey, Location);
	}
	public Object LocationTransfer(String empKeyid, String empLocation) throws Exception{
		 //return applicationMaintainanceDao.LocationTransfer(empKeyid, empLocation);
		return serviceApi.locationTransfer(empKeyid, empLocation);
	}
	public List<String[]> getEmployeeList(CommonFilter commonFilter) throws Exception{
		String empmKeyId = commonFilter.getKey();
		CommonMessage.debugMsg("key id "+empmKeyId);
		//return applicationMaintainanceDao.getEmployeeList(commonFilter);
		return serviceApi.getEmployeeList(empmKeyId);
	}

	public List<String[]> getEmpActiveData(GridParams gridParams,String location) throws Exception{
		 return applicationMaintainanceDao.getEmpActiveData(gridParams,location);
	}
    public void UpdateActive(String EmpKeyid,String ValidTill,String remarks,String EmpType) throws Exception{
    	 //applicationMaintainanceDao.UpdateActive(EmpKeyid, ValidTill, remarks,EmpType);
    	serviceApi.activateEmpUser(EmpKeyid, ValidTill, remarks);
    }
	public List<String[]> getKaizenData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
		 return applicationMaintainanceDao.getKaizenData(gridParams,commonFilter);
	}
	public void UpdateKaizenDate(String KaizenKeyid,String KaizenDate) throws Exception{
		 //applicationMaintainanceDao.UpdateKaizenDate(KaizenKeyid, KaizenDate);
		serviceApi.kaizenDateChange(KaizenKeyid, KaizenDate);
				
	}
	public List<String[]> getSusaData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
		return applicationMaintainanceDao.getSusaData(gridParams, commonFilter);
	}
	public void DeleteSusa(String SusaKeyid) throws Exception{
		  applicationMaintainanceDao.DeleteSusa(SusaKeyid);
	}
	public List<String[]> getTrainingData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
		return applicationMaintainanceDao.getTrainingData(gridParams, commonFilter);
	}
	public void DeleteTraining(String TrainingKeyid) throws Exception{
		//applicationMaintainanceDao.DeleteTraining(TrainingKeyid);
		serviceApi.deleteTrgCalendar(TrainingKeyid);
	}
	public List<String[]> getSuggestionData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
		return applicationMaintainanceDao.getSuggestionData(gridParams, commonFilter);
	}
	public String getDmtOriginalId(String Flid)throws Exception{
		//return applicationMaintainanceDao.getDmtOriginalId(Flid);
		return serviceApi.getFlid(Flid);
	}
	public String getJHOriginalId(String Flid)throws Exception{
		//return applicationMaintainanceDao.getJHOriginalId(Flid);
		return serviceApi.getFlid(Flid);
	}

	public void DeleteSuggestion(String SuggKeyid) throws Exception{
		  //applicationMaintainanceDao.DeleteSuggestion(SuggKeyid);
		serviceApi.deleteSuggestion(SuggKeyid);
	}
	public List<String[]> getKaizenDeleteData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
		  return applicationMaintainanceDao.getKaizenDeleteData(gridParams, commonFilter);
	}
	public void DeleteKaizen(String KaizenKeyid) throws Exception{
		  //applicationMaintainanceDao.DeleteKaizen(KaizenKeyid);
		serviceApi.deleteKaizen(KaizenKeyid);
	}
	public List<String[]> getWhyWhyDeleteData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
		  return applicationMaintainanceDao.getWhyWhyDeleteData(gridParams, commonFilter);
	}
	public void DeleteWhyWhy(String WhyWhyKeyid) throws Exception{
		  //applicationMaintainanceDao.DeleteWhyWhy(WhyWhyKeyid);
		serviceApi.deleteWhyWhy(WhyWhyKeyid);
	}
	public List<String[]> getLossDeleteData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
		return applicationMaintainanceDao.getLossDeleteData(gridParams, commonFilter);
	}
	public void DeleteLoss(String LossKeyid) throws Exception{
		 //applicationMaintainanceDao.DeleteLoss(LossKeyid);
		serviceApi.deleteLoss(LossKeyid);
	}
	@Override
	public List<String[]> getActionPlanDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return applicationMaintainanceDao.getActionPlanDeleteData(gridParams,commonFilter);
	}
	public void DeleteActionPlan(String ActionPlanKeyid) throws Exception{
		   //applicationMaintainanceDao.DeleteActionPlan(ActionPlanKeyid);
		serviceApi.deleteActionPlan(ActionPlanKeyid);
	}

	@Override
	public List<String[]> getNearMissDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return applicationMaintainanceDao.getNearMissDeleteData(gridParams,commonFilter);
	}
	public void DeleteNearMiss(String NearMissKeyid) throws Exception{
		
		applicationMaintainanceDao.DeleteNearMiss(NearMissKeyid);
	}
	public List<String[]> getAbnClosureData(GridParams gridParams, CommonFilter commonFilter) throws Exception{
		  return applicationMaintainanceDao.getAbnClosureData(gridParams, commonFilter);
	}
//    public void AbnClosure(String AbnmKeyid,String Status,String CounterMeasure,String CompletedDate,String CompletedBy) throws Exception{
//    	 //applicationMaintainanceDao.AbnClosure(AbnmKeyid, Status, CounterMeasure, CompletedDate, CompletedBy);
//    	serviceApi.abnormalityClosure(AbnmKeyid, Status, CounterMeasure, CompletedDate, CompletedBy);
//    }
    
    public void AbnClosure(List<AbnTlAbnormality> abnClosureList) throws Exception {
	    serviceApi.abnormalityClosure(abnClosureList);
	}

    
	public List<String[]> getActionPlanClosureData(GridParams gridParams, CommonFilter commonFilter) throws Exception{
		  return applicationMaintainanceDao.getActionPlanClosureData(gridParams, commonFilter);
	}
    public void ActionPlanClosure(String ActionPlanId,String DetailId,String Status,String CompletedOn,String CompletedBy,String CounterMeasure) throws Exception{
    	  //applicationMaintainanceDao.ActionPlanClosure(ActionPlanId,DetailId,Status, CompletedOn, CompletedBy, CounterMeasure);
    	serviceApi.actionPlanClosure(ActionPlanId, DetailId, Status, CompletedOn, CompletedBy, CounterMeasure);
    }
	public List<String[]> getFIProjectData(GridParams gridParams, CommonFilter commonFilter) throws Exception{
		   return applicationMaintainanceDao.getFIProjectData(gridParams, commonFilter);
	}
	public void UpdateFIProjectDate(String FIProjectId,String EndDate) throws Exception{
		  // applicationMaintainanceDao.UpdateFIProjectDate(FIProjectId, EndDate);
		serviceApi.fipDateChange(FIProjectId, EndDate);
	}
	public List<String[]> getAppMaintGrid(GridParams gridParams, CommonFilter commonFilter) throws Exception{
		  return applicationMaintainanceDao.getAppMaintGrid(gridParams, commonFilter);
	}
	public List<ComboBox> getAdminMenu(ComboFilter comboFilter) throws Exception{
		comboFilter.setNameField("ADMM_NAME");
		comboFilter.setIdField("ADMM_KEYID");
		comboFilter.setTableName("ADM_TL_ADMINMENUMST");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<String[]> getEmployeeRole(GridParams gridParams, CommonFilter commonFilter) throws Exception{
		  return applicationMaintainanceDao.getEmployeeRole(gridParams, commonFilter);
	}
	public List<String[]> getRoleList(GridParams gridParams, CommonFilter commonFilter) throws Exception{
		 return applicationMaintainanceDao.getRoleList(gridParams, commonFilter);
	}
	public List<String[]>  getlevelrole(String flId) throws Exception{
	      return applicationMaintainanceDao.getlevelrole(flId);
	}
	@Override
	public    void UserInActive(String empKeyid, String validTill, String remarks) throws Exception {
		// TODO Auto-generated method stub
		
		// applicationMaintainanceDao.UserInActive(empKeyid,  validTill,  remarks);
		serviceApi.updateUserEmployee(empKeyid, remarks);
	}
	@Override
	public List<String[]> getAbnDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return applicationMaintainanceDao.getAbnDeleteData( gridParams,  commonFilter);
	}
	@Override
	public void deleteAbnormality(String abnKeyid) throws Exception {
		// TODO Auto-generated method stub
		// applicationMaintainanceDao.deleteAbnormality(abnKeyid);
		serviceApi.deleteAbnormality(abnKeyid);
	}
	@Override
	public List<String[]> getUSerList(CommonFilter commonFilter) throws Exception {
		
		return applicationMaintainanceDao.getUSerList(commonFilter);
	}
	@Override
	public void AupUSerInActive(String empIds) throws Exception {
		applicationMaintainanceDao.AupUSerInActive(empIds);
		
	}
	
	@Override
	public List<ComboBox> getKaizenNoCombo(ComboFilter comboFilter) throws Exception {
		return applicationMaintainanceDao.getKaizenNoCombo(comboFilter);
	}
	


@Override
	public String[] getKaizenBenefitTypeAndFlid(String kznKeyid) throws Exception {
		return applicationMaintainanceDao.getKaizenBenefitTypeAndFlid(kznKeyid);
	}
	
@Override
	public List<String[]> getKaizenApprovalDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception {
		return applicationMaintainanceDao.getKaizenApprovalDeleteData(gridParams, commonFilter);
	}
	
@Override
	public String getEmpRoleIds(String empId) throws Exception {
		return applicationMaintainanceDao.getEmpRoleIds(empId);
	}	

@Override
	public List<String[]> getWorkFlowTransData(
			GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception {
		return applicationMaintainanceDao.getWorkFlowTransData(genTlWorkflowInfo,transCode,flId,roleId);
	}
	
@Override
	public String getWorkflowRefRoleId(String empId,String flId,String transCode) throws Exception {

	    return applicationMaintainanceDao.getWorkflowRefRoleId(empId,flId,transCode);
	}
	
@Override
	public void revokeKaizenApprovalWorkflow(String refId,List<String> wrinKeyIds) throws Exception {

	    applicationMaintainanceDao.revokeKaizenApprovalWorkflow(refId, wrinKeyIds);
	}	
	
}
