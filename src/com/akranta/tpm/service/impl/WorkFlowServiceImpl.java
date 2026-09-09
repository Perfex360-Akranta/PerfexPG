package com.akranta.tpm.service.impl;


import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.WorkFlowmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.WorkFlowmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.GenTlWorkflowdtl;
import com.akranta.tpm.model.WorkFlowmst;
import com.akranta.tpm.service.WorkFlowService;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.WorkFlowServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class WorkFlowServiceImpl implements WorkFlowService {
		
	private WorkFlowmstDao workflowmstdao;
	private CommonFilterDao commonFilterDao;
	private WorkFlowServiceApi workFlowServiceApi;
    
    private Validations validations ;
    
	public  WorkFlowServiceImpl(DBActionTemplate dbActionTemplate) {
		commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
		workflowmstdao=new WorkFlowmstDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	public void WorkFlowServiceImplJwt(String JwtToken){
    	try{
    		workflowmstdao.WorkFlowmstDaoImplJwt(JwtToken);
            workFlowServiceApi = new WorkFlowServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<String[]> getAllresource(CommonFilter commonFilter) throws Exception  {
		// TODO Auto-generated method stub
		return this.workflowmstdao.getAllResource(commonFilter);
	}

	@Override
	public List<String[]> getMasterGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.workflowmstdao.getMasterGrid(commonFilter);	
	}

	@Override
	public WorkFlowmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
//		return this. workflowmstdao.select(keyid);
		return workFlowServiceApi.getWorkflowMst(keyid);
	}

	@Override
	public WorkFlowmst create(WorkFlowmst genT1Workflowmst,WorkFlowmst existworkflowmst)throws Exception {
		// TODO Auto-generated method stub
	    
		CommonMessage.debugMsg("Inside Create");
		String validationsFor = "create";
		String xml = "WorkFlowmst";
		CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
		try{
			validations.validate(genT1Workflowmst, xml, validationsFor);
			fillValues(genT1Workflowmst, existworkflowmst);	
			CommonMessage.debugMsg("After Fill Values");
			//return workflowmstdao.create(genT1Workflowmst);
			return workFlowServiceApi.saveWorkFlow(genT1Workflowmst);
		}
		catch (ValidationExceptions e) {
				throw new ValidationExceptions(e.getMessage());
		}
	}
	
	@Override
	public WorkFlowmst update(WorkFlowmst genT1Workflowmst,	WorkFlowmst existworkflowmst)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside Create");
		String validationsFor = "create";
		String xml = "WorkFlowmst";
		CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
		try {			
			validations.validate(genT1Workflowmst, xml, validationsFor);					
			fillValues(genT1Workflowmst, existworkflowmst);		 
			CommonMessage.debugMsg("After Fill Values");
			//return workflowmstdao.update(genT1Workflowmst);
			return workFlowServiceApi.saveWorkFlow(genT1Workflowmst);
		} catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
	@Override
	public WorkFlowmst delete(WorkFlowmst genT1Workflowmst) throws Exception {
		// TODO Auto-generated method stub
//		return this.workflowmstdao.delete(genT1Workflowmst);
		return workFlowServiceApi.deleteWorkFlowMst(genT1Workflowmst);
	}

	@Override
	public WorkFlowmst Deletelist(String keyid) throws Exception {
		// TODO Auto-generated method stub
//		return this.workflowmstdao.delete(keyid);
		return workFlowServiceApi.delteWorkFlowDtl(keyid);
	}

	@Override
	public List<String[]> getDetail(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.workflowmstdao.getDetail(commonFilter);
	}
	
	private WorkFlowmst fillValues(WorkFlowmst GenT1Workflowmst, WorkFlowmst existworkflowmst) {		
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		GenT1Workflowmst.setWrkmActive("Y");
		GenT1Workflowmst.setWrkmCreatedon(dateTime);	
		GenT1Workflowmst.setWrkmModifiedon(dateTime);		
		
		if(GenT1Workflowmst.getWrkmKeyid() == null )
			 GenT1Workflowmst.setWrkmKeyid("{}");	
		
		if(GenT1Workflowmst.getWrkmNoofstage() == null )
			 GenT1Workflowmst.setWrkmNoofstage("{}");	
		
		if(GenT1Workflowmst.getWrkmName() == null )
			 GenT1Workflowmst.setWrkmName("{}");	
		
		if(GenT1Workflowmst.getWrkmTempfield1() == null )
			 GenT1Workflowmst.setWrkmTempfield1("-");
	
		if(GenT1Workflowmst.getWrkmTempfield2() == null )
			 GenT1Workflowmst.setWrkmTempfield2("-");
		
		if(GenT1Workflowmst.getWrkmTempfield3() == null )
			 GenT1Workflowmst.setWrkmTempfield3("-");
		
		if(GenT1Workflowmst.getWrkmTempfield4() == null )
			 GenT1Workflowmst.setWrkmTempfield4("-");
		
		if(GenT1Workflowmst.getWrkmActive() == null )
			 GenT1Workflowmst.setWrkmActive("Y");
		
		if(GenT1Workflowmst.getWrkmCreatedby() == null )
			 GenT1Workflowmst.setWrkmCreatedby("{}");				
		
		if(GenT1Workflowmst.getWrkmModifiedon() == null )
			 GenT1Workflowmst.setWrkmModifiedon("{}");
		
		GenT1Workflowmst.setWorkFlowDtls(fillValuesdtl (GenT1Workflowmst, existworkflowmst));
		
		return GenT1Workflowmst;		
		
	}
	
	private  List<GenTlWorkflowdtl> fillValuesdtl( WorkFlowmst genT1Workflowmst,WorkFlowmst existworkflowmst)  {
		// TODO Auto-generated method stub
		
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		List<GenTlWorkflowdtl> newGenTlWorkflowdtl=genT1Workflowmst.getWorkFlowDtls();				
		CommonMessage.debugMsg("start Of  fillValues newGenTlWorkflowdtl"+newGenTlWorkflowdtl.size());		
		for(int i=0 ;i<=newGenTlWorkflowdtl.size()-1;i++){		
			 newGenTlWorkflowdtl.get(i).setWrkdActive("Y");
			 newGenTlWorkflowdtl.get(i).setWrkdCreatedon(dateTime);			
			 newGenTlWorkflowdtl.get(i).setWrkdModifiedon(dateTime);			
			 newGenTlWorkflowdtl.get(i).setWrkdCreatedby(genT1Workflowmst.getWrkmCreatedby()); 
			 
			 if(newGenTlWorkflowdtl.get(i).getWrkdKeyid() == null )
					newGenTlWorkflowdtl.get(i).setWrkdKeyid("{}");	
			 
			 if(newGenTlWorkflowdtl.get(i).getWrkdWrkmKeyid() == null )
					newGenTlWorkflowdtl.get(i).setWrkdWrkmKeyid("{}");	
			
			 if(newGenTlWorkflowdtl.get(i).getWrkdStage() == null )
					newGenTlWorkflowdtl.get(i).setWrkdStage("{}");	
			 
			 if(newGenTlWorkflowdtl.get(i).getWrkdType() == null )
					newGenTlWorkflowdtl.get(i).setWrkdType("{}");
			 
			 if(newGenTlWorkflowdtl.get(i).getWrkdTempfield1() == null )
					newGenTlWorkflowdtl.get(i).setWrkdTempfield1("-");
			 
			 if(newGenTlWorkflowdtl.get(i).getWrkdTempfield2() == null )
					newGenTlWorkflowdtl.get(i).setWrkdTempfield2("-");
			 
			 if(newGenTlWorkflowdtl.get(i).getWrkdTempfield3() == null )
					newGenTlWorkflowdtl.get(i).setWrkdTempfield3("-");	
			 
			 if(newGenTlWorkflowdtl.get(i).getWrkdTempfield4() == null )
					newGenTlWorkflowdtl.get(i).setWrkdTempfield4("-");	 		
		}		
		return newGenTlWorkflowdtl;
		 
	}

	@Override
	public List<ComboBox> getRoleComboList(ComboFilter roleFilterComboFilter) throws Exception {
		// TODO Auto-generated method stub
		//roleFilterComboFilter.setCodeField("ROLE_KEYID");
		roleFilterComboFilter.setNameField("ROLE_NAME");
		roleFilterComboFilter.setIdField("ROLE_KEYID");
		roleFilterComboFilter.setTableName(TableNames.TBL_ADM_TL_ROLEMST);
		return commonFilterDao.fillComboValues(roleFilterComboFilter);
	}

	@Override
	public List<String[]> getWorkFlowTransData(
			GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception {
		return workflowmstdao.getWorkFlowTransData(genTlWorkflowInfo,transCode,flId,roleId);
	}
	@Override
	public List<String[]> getdmcWorkFlowTransData(
			GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception {
		return workflowmstdao.getdmcWorkFlowTransData(genTlWorkflowInfo,transCode,flId,roleId);
	}
	public void saveGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo
			, String lastLevel, String nextRoleName, String nextRoleId ) throws Exception{
		validations.validate(genTlWorkflowInfo, "gentlworkflowinfo", "create");
		fillGenTlWorkFlowInfoMst(genTlWorkflowInfo);
//		genTlWorkflowInfo.getWrinStatus();
//		workflowmstdao.saveGenTlWorkFlowInfoMst(genTlWorkflowInfo, lastLevel, nextRoleName, nextRoleId );
		workFlowServiceApi.saveApprovals(genTlWorkflowInfo, lastLevel, nextRoleName, nextRoleId);
	}
	public void savedmcGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo
			, String lastLevel, String nextRoleName, String nextRoleId ) throws Exception{
		validations.validate(genTlWorkflowInfo, "gentlworkflowinfo", "create");
		filldmcGenTlWorkFlowInfoMst(genTlWorkflowInfo);
		genTlWorkflowInfo.getWrinStatus();
		workflowmstdao.savedmcGenTlWorkFlowInfoMst(genTlWorkflowInfo
				, lastLevel, nextRoleName, nextRoleId );
	}
	private void fillGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo){
		genTlWorkflowInfo.setWrinTempfield2("-");
		genTlWorkflowInfo.setWrinTempfield3("-");
		genTlWorkflowInfo.setWrinTempfield4("-");
		genTlWorkflowInfo.setWrinTempfield5("-");
		if( ! UIUtils.isValidKeyId(genTlWorkflowInfo.getWrinRemarks()))
			genTlWorkflowInfo.setWrinRemarks("-");
		if( ! UIUtils.isValidKeyId(genTlWorkflowInfo.getWrinWrkdKeyid()))
			genTlWorkflowInfo.setWrinWrkdKeyid("{}");
		if( ! UIUtils.isValidKeyId(genTlWorkflowInfo.getWrinWrmlKeyid()))
			genTlWorkflowInfo.setWrinWrmlKeyid("{}");
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		genTlWorkflowInfo.setWrinDate(CommonFunctions.pg_getDateTimeFromDate(genTlWorkflowInfo.getWrinDate()));
		genTlWorkflowInfo.setWrinCreatedon(dateTime);
		genTlWorkflowInfo.setWrinModifiedon(dateTime);
	}
	private void filldmcGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo){

		genTlWorkflowInfo.setWrinTempfield2("-");
		genTlWorkflowInfo.setWrinTempfield3("-");
		genTlWorkflowInfo.setWrinTempfield4("-");
		genTlWorkflowInfo.setWrinTempfield5("-");
		
		String roleid=genTlWorkflowInfo.getWrinRoleId();
		if(roleid.equals("Project Leader")){
			//genTlWorkflowInfo.setWrinRefType("DMCFIP");
			genTlWorkflowInfo.setWrinWrkdKeyid("DMCPL");
			genTlWorkflowInfo.setWrinRoleId("AROL0059");
		}
		else if(roleid.equals("PBU Head")){
			//genTlWorkflowInfo.setWrinRefType("DMCFIP");
			genTlWorkflowInfo.setWrinWrkdKeyid("DMCPH");
			genTlWorkflowInfo.setWrinRoleId("AROL0002");
		}
		else if(roleid.equals("KK Champion")){
			//genTlWorkflowInfo.setWrinRefType("DMCFIP");
			genTlWorkflowInfo.setWrinWrkdKeyid("DMCFKK");
			genTlWorkflowInfo.setWrinRoleId("AROL0060");
		}
		else if(roleid.equals("Finance Head")){
			//genTlWorkflowInfo.setWrinRefType("DMCFIP");
			genTlWorkflowInfo.setWrinWrkdKeyid("DMCFFH");
			genTlWorkflowInfo.setWrinRoleId("AROL0061");
		}
		if( ! UIUtils.isValidKeyId(genTlWorkflowInfo.getWrinRemarks()))
			genTlWorkflowInfo.setWrinRemarks("-");
		//if( ! UIUtils.isValidKeyId(genTlWorkflowInfo.getWrinWrkdKeyid()))
			//genTlWorkflowInfo.setWrinWrkdKeyid("{}");
		if( ! UIUtils.isValidKeyId(genTlWorkflowInfo.getWrinWrmlKeyid()))
			genTlWorkflowInfo.setWrinWrmlKeyid("{}");
		String dateTime = CommonFunctions.dateTimeNow();		
		
		genTlWorkflowInfo.setWrinCreatedon(dateTime);
		genTlWorkflowInfo.setWrinModifiedon(dateTime);
	}
}
