package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlCapa;
import com.akranta.tpm.model.QtmTlCapaActionmst;
import com.akranta.tpm.model.QtmTlCapaWorkflow;

import net.sf.json.JSONObject;

public interface NewCAPADao{

	QtmTlCapa create(QtmTlCapa newQtmTlCapa) throws Exception;

	List<String[]> getDmtLeaderApproval(CommonFilter commonFilter, String capaId) throws Exception;

	QtmTlCapaWorkflow create(QtmTlCapaWorkflow qtmTlCapaWorkflow, String capaId) throws Exception;

	List<String[]> getCAPARelatedData(CommonFilter commonFilter) throws Exception;

	QtmTlCapaWorkflow getComplaintApproval(QtmTlCapaWorkflow qtmTlCapaWorkflow,
			QtmTlCapaWorkflow existQtmTlCapaWorkflow, String keyid, String empId, String status, String date,
			String remarks, String capaId) throws Exception;

	List<QtmTlCapaActionmst> createCAPA(List<QtmTlCapaActionmst> qtmTlCapaActionmst, String createdBy,
			String capaMasterKeyid) throws Exception;

	QtmTlCapaActionmst createCAPA(QtmTlCapaActionmst fillValuesCAPActionPlan, String createdBy, String keyid,
			String preventiveAction, String rootCause, String targetDate, String status, String mType, String defect) throws Exception;

	List<String[]> getCapaDetails(CommonFilter commonFilter) throws Exception;

	QtmTlCapaActionmst getCapaActionPlanUpdate(QtmTlCapaActionmst qtmTlCapaActionmst,
			QtmTlCapaActionmst existQtmTlCapaActionmst, String keyid, String preventiveAction, String rootCause,
			String targetDate, String status, String mType, String defect, String correctiveAction,
			String responsibility,String CAPA) throws Exception;

	String getTargetFlid(String targetJH) throws Exception;

	List<String[]> getCAPAEmpMailIds(String complaintNo) throws Exception;

	QtmTlCapa update(QtmTlCapa newQtmTlCapa) throws Exception;

	QtmTlCapaWorkflow UpdateWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow, String capaId, String targetDmtLeader, String status) throws Exception;

	
	// -- Vignesh
	
	public  Workbook getExcelreport(CommonFilter commonFilter,JSONObject colModel, String format) throws Exception;
	
	public abstract void NewCAPADaoImplJwt(String jwtToken);
	
	// -- Hari - 4 March
	public QtmTlCapaActionmst deleteCapaActionPlanDetail(QtmTlCapaActionmst qtmTlCapaActionmst) throws Exception;
	
}
