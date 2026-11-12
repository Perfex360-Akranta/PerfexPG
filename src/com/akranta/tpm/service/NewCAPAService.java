package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlCapa;
import com.akranta.tpm.model.QtmTlCapaActionmst;
import com.akranta.tpm.model.QtmTlCapaWorkflow;

import net.sf.json.JSONObject;

public interface NewCAPAService {
  	public List<ComboBox> getDefect(ComboFilter comboFilter,String flid) throws Exception;

	public QtmTlCapa create(QtmTlCapa newQtmTlCapa, QtmTlCapa existQtmTlCapa) throws Exception;

	public QtmTlCapa update(QtmTlCapa newQtmTlCapa, QtmTlCapa existQtmTlCapa) throws Exception;

	public List<String[]> getDmtLeaderApproval(CommonFilter commonFilter, String capaId) throws Exception;

	public QtmTlCapaWorkflow createWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow,
			 String capaId) throws Exception;

	public List<String[]> getCAPARelatedData(CommonFilter commonFilter) throws Exception ;

	public QtmTlCapa getCapaData(String capaId) throws Exception;

	public QtmTlCapaWorkflow getComplaintApproval(QtmTlCapaWorkflow qtmTlCapaWorkflow,
			QtmTlCapaWorkflow existQtmTlCapaWorkflow, String keyid, String empId, String status, String date,
			String remarks, String capaId) throws Exception;

	public List<QtmTlCapaActionmst> createCAPA(List<QtmTlCapaActionmst> qtmTlCapaActionmst,
			String createdBy,String CapaMasterKeyid) throws Exception;

	public QtmTlCapaActionmst getCapaActionPlanSave(QtmTlCapaActionmst qtmTlCapaActionmst,
			QtmTlCapaActionmst existQtmTlCapaActionmst, String keyid, String preventiveAction, String rootCause,
			String targetDate, String status, String mType, String defect,String CreatedBy, String correctiveAction, String responsibility) throws Exception;

	public List<String[]> getCapaDetails(CommonFilter commonFilter) throws Exception;

	public QtmTlCapaActionmst getCapaActionPlanUpdate(QtmTlCapaActionmst qtmTlCapaActionmst,
			QtmTlCapaActionmst existQtmTlCapaActionmst, String keyid, String preventiveAction, String rootCause,
			String targetDate, String status, String mType, String defect, String createdBy, String correctiveAction,
			String responsibility,String CAPA) throws Exception;

	public String getTargetFlid(String targetJH) throws Exception;

	public List<ComboBox> getDefectComboList(ComboFilter combofilter, String flid) throws Exception;

	public List<String[]> getCAPAEmpMailIds(String complaintNo) throws Exception;

	public QtmTlCapaWorkflow UpdateWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow, String capaId, String targetDmtLeader, String status) throws Exception;

	
	// -- Vignesh
	
	public  Workbook getExcelreport(CommonFilter commonFilter,JSONObject colModel, String format) throws Exception;
	public void NewCAPAServiceImplJwt(String JwtToken);

	// elumalai new remove button
	QtmTlCapaActionmst deleteCapaActionPlanDetail(QtmTlCapaActionmst qtmTlCapaActionmst) throws Exception;
	
}
