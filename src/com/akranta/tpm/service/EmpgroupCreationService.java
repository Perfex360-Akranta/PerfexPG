package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomGroupmst;

public interface EmpgroupCreationService {
	
	
	public GenTlMomGroupmst createRecord(GenTlMomGroupmst newgenTlMomGroupmst,GenTlMomGroupmst existgenTlMomGroupmst)throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public GenTlMomGroupmst updateRecord(GenTlMomGroupmst newgenTlMomGroupmst,GenTlMomGroupmst existgenTlMomGroupmst)throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public List<String[]> getempgroupCreationMstGridData(CommonFilter commonFilter,CommonParams commonparams)throws NoDataFoundException, Exception;
//	public List<String[]> getGroupGridData(String serivcemstkeyid,GridParams gridparams) throws Exception;
	public List<String[]> getGroupGridData(CommonFilter commonFilter,CommonParams commonparams) throws Exception;
	public List<String[]> getEmpgroupViewGridData(GridParams gridparams) throws Exception;
	public GenTlMomGroupmst getGridValues(String keyid)throws Exception;
	public GenTlMomGroupmst DeleteGroupRecord(GenTlMomGroupmst newGenTlMomGroupmst) throws BusinessApplicationExceptions,Exception;
	public GenTlMomGroupmst DeleteGroupMemberRecord(String keyid)throws Exception;
	public Workbook getempgroupDetailExcel(GridParams gridparams,JSONObject colModel, String format,String keyid) throws Exception;
	public Workbook getempgroupViewExcel(GridParams gridparams,
			JSONObject colModel, String format)throws Exception;
	public void EmpgroupCreationServiceImplJwt(String JwtToken);
	

}
