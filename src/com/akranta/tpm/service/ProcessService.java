package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.CriticalProcessNew;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlCriticalprocessmst;

public interface ProcessService {

	public	List<String[]> getAllProcess(CommonFilter commonFilter) throws Exception;

	
	public QtmTlCriticalprocessmst create(QtmTlCriticalprocessmst newQtmTlCriticalprocessmst,
			   QtmTlCriticalprocessmst existQtmTlCriticalprocessmst,CriticalProcessNew criticalProcessNew)
	       throws Exception;

	public QtmTlCriticalprocessmst update(QtmTlCriticalprocessmst newQtmTlCriticalprocessmst,
			   QtmTlCriticalprocessmst existQtmTlCriticalprocessmst,CriticalProcessNew criticalProcessNew)
	       throws Exception;

	public QtmTlCriticalprocessmst select(String crppKeyid)throws Exception;
	
	public void delete(String crppKeyid)throws Exception;
	public void deleteDtl(String crpdKeyid)throws Exception;
	
	public List<String[]> FillControlData(String keyid)throws Exception;

	public Workbook getCriticalProcessExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter)throws Exception;

	public List<String[]> getFillMainGrid(CommonFilter commonFilter)throws Exception;

	public Workbook getprocessExcel(JSONObject colmodel, String format,	CommonFilter commonFilter)throws Exception;
	
	public void ProcessServiceImplJwt(String JwtToken);

	
	
	

}
