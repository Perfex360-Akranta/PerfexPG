package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.TlmTlAlertBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.TlmTLAlertDetails;
import com.akranta.tpm.model.ToolChangeTlDetails;


public interface ToolMonitoringDao {

	public ToolChangeTlDetails create(ToolChangeTlDetails newToolTlDtl)throws Exception ;
	public List<String []> getStdTime(String toolId)throws Exception ;
	public List<String []> getStdLife(String toolId ,String mchId)throws Exception ;
	public List<String []> getAllDetailsList( CommonFilter commonFilter) throws Exception;
	public List<String []> getAllSumaryRpt( CommonFilter commonFilter) throws Exception;

	public ToolChangeTlDetails update(ToolChangeTlDetails newToolTlDtl) throws BusinessApplicationExceptions, Exception;

	public ToolChangeTlDetails delete(ToolChangeTlDetails newToolTlDtl) throws Exception ;
	public ToolChangeTlDetails getFillValue(String keyId)throws Exception;
	public List<String[]> getSumary(CommonFilter commonFilter, String type)throws Exception;
	public List<String[]> getSumaryGrid(CommonFilter commonFilter, String type, String alertType)throws Exception;
	public List<String[]> getEstSharp(String toolid)throws Exception;
	public List<String[]> getLastChangeDate(String toolid,String cellId,String machineId)throws Exception;
	public List<String[]> getProdLastChange(String toolid, String cellId,String machineId)throws Exception;
	public ToolChangeTlDetails create(ToolChangeTlDetails newToolTlDtl,String alertkeyid)throws Exception;
	public String updateSrNo(List<TlmTLAlertDetails> tlmTLAlertDetailsList)throws Exception;
	TlmTLAlertDetails updateSrNo(TlmTLAlertDetails newTlmTlAlertdetails)
			throws Exception;
	public List<String[]> getSumaryDetails(CommonFilter commonFilter)throws Exception;
	public List<String[]> getSharpening(String toolid, String mchId)throws Exception;
	public Workbook getAllSumaryRptExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception;
}
