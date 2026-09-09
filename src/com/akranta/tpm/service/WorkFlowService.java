package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.WorkFlowmst;

public interface WorkFlowService {
	
public void WorkFlowServiceImplJwt(String JwtToken);

 public List<String[]> getAllresource(CommonFilter commonFilter) throws Exception;

 public	WorkFlowmst create(WorkFlowmst newGenT1Workflowmst,WorkFlowmst existworkflowmst) throws Exception;

 public WorkFlowmst update(WorkFlowmst GenT1Workflowmst,WorkFlowmst existworkflowmst)throws Exception;

 public List<String[]> getMasterGrid(CommonFilter commonFilter)throws Exception;

 public WorkFlowmst select(String keyid) throws Exception;

 public WorkFlowmst delete(WorkFlowmst genT1Workflowmst)throws Exception;

 public WorkFlowmst Deletelist(String keyid)throws Exception;

 public List<String[]> getDetail(CommonFilter commonFilter) throws Exception;

 public List<ComboBox> getRoleComboList(ComboFilter roleFilterComboFilter)throws Exception ;	

 public List<String[]> getWorkFlowTransData(GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception;
 
 public List<String[]> getdmcWorkFlowTransData(GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception;
 
 public void saveGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo
		 , String lastLevel, String nextRoleName, String nextRoleId ) throws Exception;
 
 public void savedmcGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo
		 , String lastLevel, String nextRoleName, String nextRoleId ) throws Exception;
 
}
