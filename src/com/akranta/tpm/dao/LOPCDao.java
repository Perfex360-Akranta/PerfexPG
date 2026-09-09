package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.LopcEntryMst;

import net.sf.json.JSONObject;

public interface LOPCDao{
	public LopcEntryMst create(LopcEntryMst LopcEntryMst)throws Exception;
	public List<String[]> getLOPCModificationList(CommonFilter commonFilter)throws Exception;
	//public List<String[]> getLOPCActionPlanList(CommonFilter commonFilter) throws Exception;
	public BdmTlWwbladtl UpdateLOPCAClosure(BdmTlWwbladtl bdmTlWwbladtl, BdmTlWwbladtl existBdmTlWwbladtl, String keyid,
			String completedBy, String status, String completedDate, String correctiveaction, String remarks) throws Exception;
	public List<String[]> getLOPCView(CommonFilter commonFilter) throws Exception;
	public LopcEntryMst Update(LopcEntryMst lopcEntryMst, String lopcId)throws Exception;
	public Workbook getLopcExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)throws Exception;
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId)throws Exception;
	public Workbook getLopcModificationExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	 //public Workbook getLopcActionClosureExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	public Workbook getLopcActionClosureExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format, String employeeId) throws Exception;
	 public List<String[]> getLOPCActionPlanList(CommonFilter commonFilter, String employeeId) throws Exception;
	 public abstract void LOPCDaoImplJwt(String jwtToken);

}
