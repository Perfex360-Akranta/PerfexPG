package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlNearmissreportmstnew;

import net.sf.json.JSONObject;


public interface GenTlNearmissreportmstnewDao {

	public abstract GenTlNearmissreportmstnew create(GenTlNearmissreportmstnew genTlNearmissreportmstnew) throws Exception;
	public abstract GenTlNearmissreportmstnew update(GenTlNearmissreportmstnew genTlNearmissreportmstnew) throws Exception;
	public abstract GenTlNearmissreportmstnew delete(GenTlNearmissreportmstnew genTlNearmissreportmstnew) throws Exception;
	public abstract List<String[]> getNearDetailsList(CommonFilter commonFilter, String formType) throws Exception;
	public abstract String UpdateCloseDocument(String detailkeyid,String responsevalue, String targetdate, String verifystatus, String remarks) throws BusinessApplicationExceptions, Exception;
	public abstract String UpdateVerify(String detailkeyid,String responsevalue, String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception;
	public abstract String UpdateResponse(String detailkeyid,String responsevalue, String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception;
	public abstract String updateActionCLosure(String detailkeyid,String responsevalue, String targetdate, String reviseddate,String status, String correctiveaction,String remarks) throws BusinessApplicationExceptions, Exception;
	public abstract List<String[]> getElementId(String loginflid,String loginlevel, String loginElementid, String empId) throws NoDataFoundException, Exception;
	public abstract List<String[]> getSwitchUserDetail(String empId) throws NoDataFoundException, Exception;
	//public abstract List<String[]> getNearMissEmpMailIds(String rolekeyid,String flid) throws NoDataFoundException, Exception;
	public abstract String getUpdateInvest(String repsonse,String targetdate, String probable, String invest, String recomd, String keyid, String chko, String chks, String oa, String os, String remarks, String empid) throws BusinessApplicationExceptions, Exception;
	public abstract List<String[]> getNearMiss(String masterKeyid) throws Exception;
	public abstract List<String[]> getNearAct(String masterKeyid) throws Exception;
	public abstract Workbook getNearMissExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,String formtype) throws IOException, SQLException, Exception;
	public abstract List<String[]> getNearMissEmpMailIds(String type, String flid, String location) throws NoDataFoundException, Exception;
	
	public List<String[]> getfillnewnearmissdata(String keyid, String flid)throws Exception;

	public List<String[]> getfillnewunsafeactdata(String keyid, String flid)throws Exception;


	public List<String[]> getfillnewunsafeconditiondata(String keyid, String flid)throws Exception;
	public abstract List<String[]> getNearMissReleatedFileManager(String momKeyId) throws Exception;
	public abstract Workbook getNearMissViewExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String formtype) throws Exception;
	public abstract List<String[]> getNearDetailsListView(CommonFilter commonFilter, String formType) throws Exception;



}

