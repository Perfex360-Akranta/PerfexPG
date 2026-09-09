package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;

public interface AdmTlAlertsDao {
	public abstract List<String[]> getAlertGrid()throws Exception;
	public abstract List<String[]> getAlertResGrid(String Name,GridParams gridParams)throws Exception;
	Workbook getAlertsExcel(JSONObject colmodel, String format,GridParams gridParams,String keyId) throws Exception;
	public abstract List<String[]> getReminderData(String loggeduser,GridParams gridParams,CommonFilter commonFilter)throws Exception;
	public abstract Workbook getReminderExcel(JSONObject colmodel,GridParams gridParams,String format, CommonFilter commonFilter,String loggedUser)throws Exception;
	public List<String[]> getApprovalData(String loggeduser, CommonFilter commonFilter) throws Exception;
	
	public void AdmTlAlertsDaoImplJwt(String JwtToken);
}
