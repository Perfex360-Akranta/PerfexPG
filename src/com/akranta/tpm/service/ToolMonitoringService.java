package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.TlmTlAlertBean;
import com.akranta.tpm.bean.ToolChangeDetailsBean;
//import com.akranta.tpm.bean.ToolMstFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.TlmTLAlertDetails;
import com.akranta.tpm.model.ToolChangeTlDetails;
//import com.akranta.tpm.model.ToolTlMst;

public interface ToolMonitoringService {
	public List<ComboBox> getToolList(String machId, ComboFilter comboFilter) throws Exception;
	public List<String[]> getStdTime(String toolId, ComboFilter comboFilter) throws Exception;
	public List<String[]> getEstSharp(String toolId, ComboFilter comboFilter) throws Exception;
	public List<String[]> getLastChangeDate(String toolid,String cellId,String machineId)throws Exception;
	public List<String[]> getProdLastChange(String toolid,String cellId,String machineId)throws Exception;
	public List<String[]> getAllDetailsList(CommonFilter commonFilter)throws Exception ;
	public List<String[]> getAllSumaryRpt(CommonFilter commonFilter)throws Exception ;	
	public ToolChangeTlDetails create(ToolChangeTlDetails newToolTlDtl,	ToolChangeTlDetails existToolTlDtl)throws Exception;
	public ToolChangeTlDetails update(ToolChangeTlDetails newToolTlDtl,	ToolChangeTlDetails existToolTlDtl,ToolChangeDetailsBean toolDtlBean)throws Exception;
	public ToolChangeTlDetails delete(ToolChangeTlDetails newToolTlDtl)throws Exception;
	public ToolChangeTlDetails getFillValue(String keyId) throws Exception;
	public List<String[]> getSumary(CommonFilter commonFilter, String type)throws Exception;
	public List<String[]> getSumaryGrid(CommonFilter commonFilter, String type, String alertType)throws Exception;
	public List<String[]> getStdLife(String toolId,String mchId)throws Exception;
	public List<ComboBox> getReasons(ComboFilter comboFilter) throws Exception;
	public ToolChangeTlDetails create(ToolChangeTlDetails newToolTlDtl,ToolChangeTlDetails existToolTlDtl, String alertkeyid)throws Exception;
	public String createSerialNo(List<TlmTLAlertDetails> tlmTLAlertDetailsList) throws Exception;
	public TlmTLAlertDetails createSerialNo(TlmTLAlertDetails newTlmTlAlertdetails,	TlmTLAlertDetails extlmTlAlertdetails,TlmTlAlertBean toolTlmTlAlertBean)throws Exception;
	public List<String[]> getSumaryDetails(CommonFilter commonFilter)throws Exception;
	public List<String[]> getSharpening(String toolId, String mchId)throws Exception;
	public Workbook getAllSumaryRptExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;

}
