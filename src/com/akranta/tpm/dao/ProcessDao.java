package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlCriticalprocessmst;

public interface ProcessDao {

	public abstract QtmTlCriticalprocessmst create(QtmTlCriticalprocessmst qtmTlCriticalprocessmst) throws Exception;
	public abstract QtmTlCriticalprocessmst update(QtmTlCriticalprocessmst qtmTlCriticalprocessmst) throws Exception;
	
	public abstract void delete(String masterId) throws Exception;
	
	public abstract void deleteDtl(String masterId) throws Exception;
	public QtmTlCriticalprocessmst select( String crppKeyid)throws Exception;
	public	List<String[]> getAllProcess(CommonFilter commonFilter) throws Exception;

		
	public List<String[]> FillControlData(String keyid) throws Exception;

	public Workbook getCriticalProcessExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter)throws Exception;

	public List<String[]> getFillMainGrid(CommonFilter commonFilter)throws Exception;

	public Workbook getprocessExcel(JSONObject colmodel, String format,	CommonFilter commonFilter)throws Exception;
	public abstract void ProcessDaoImplJwt(String jwtToken);

	
	

}
