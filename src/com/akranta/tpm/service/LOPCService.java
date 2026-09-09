package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.LopcEntryMst;

import net.sf.json.JSONObject;

public interface LOPCService {
	public List<ComboBox> getLOPCCategory(ComboFilter comboFilter) throws Exception;
	public LopcEntryMst create(LopcEntryMst LopcEntryMst,LopcEntryMst existLopcEntryMst)throws Exception;
	//public LopcEntryMst update(LopcEntryMst LopcEntryMst,LopcEntryMst existLopcEntryMst)throws Exception;
	public List<String[]> getLOPCModificationList(CommonFilter commonFilter)throws Exception;
	public LopcEntryMst getLOPCData(String tmpFromRow)throws Exception;
	public List<String[]> getElementId(String loginflid, String keyId, String format, String empId)throws Exception;
	public List getLOPCView(CommonFilter commonFilter) throws Exception;
	//public List getLOPCActionPlanList(CommonFilter commonFilter)throws Exception;
	public Workbook getLopcExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	public LopcEntryMst update(LopcEntryMst lopcEntryMst, LopcEntryMst existLopcEntryMst, String lopcId) throws Exception;
	public BdmTlWwbladtl UpdateLOPCAClosure(BdmTlWwbladtl bdmTlWwbladtl, BdmTlWwbladtl existBdmTlWwbladtl, String keyid,
			String completedBy, String status, String completedDate, String correctiveaction, String remarks) throws Exception;
	public Workbook getLopcModificationExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	//public Workbook getLopcActionClosureExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	public Workbook getLopcActionClosureExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format, String employeeId) throws Exception;
	public List<String[]> getLOPCActionPlanList(CommonFilter commonFilter, String employeeId) throws Exception;
	public void LOPCServiceImplJwt(String JwtToken);
	

}
