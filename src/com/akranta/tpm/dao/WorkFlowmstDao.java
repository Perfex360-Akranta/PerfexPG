package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.GenTlWorkflowdtl;
import com.akranta.tpm.model.WorkFlowmst;

public interface WorkFlowmstDao {

	public abstract void WorkFlowmstDaoImplJwt(String jwtToken);
	public abstract List<String[]> getAllResource(CommonFilter commonFilter) throws Exception;

	public abstract WorkFlowmst create(WorkFlowmst newGenT1workflowmst) throws Exception;

	public abstract WorkFlowmst update(WorkFlowmst GenT1workflowmst) throws Exception;

	public abstract List<String[]> getMasterGrid(CommonFilter commonFilter) throws Exception; /*for main grid */

	public abstract WorkFlowmst select(String keyid)throws Exception ;

	public abstract WorkFlowmst delete(WorkFlowmst workflowmst) throws Exception;

	public abstract void Deletelist(String keyid) throws Exception;

	public abstract WorkFlowmst delete(String keyid)throws Exception;

	public abstract List<String[]> getDetail(CommonFilter commonFilter) throws Exception;
	

	public List<String[]> getWorkFlowTransData(GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception ;
	
	public List<String[]> getdmcWorkFlowTransData(GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception ;
	
	public void saveGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo
			, String lastLevel, String nextRoleName, String nextRoleId ) throws Exception;
	
	public void savedmcGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo
			, String lastLevel, String nextRoleName, String nextRoleId ) throws Exception;
}
