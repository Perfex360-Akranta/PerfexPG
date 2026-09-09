package com.akranta.tpm.service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlNearmissreportdtlnew;
import com.akranta.tpm.model.GenTlNearmissreportmstnew;

import net.sf.json.JSONObject;


public interface NewNearMissService {

	
	
	public GenTlNearmissreportmstnew create(GenTlNearmissreportmstnew genTlNearmissreportmst,GenTlNearmissreportmstnew existGenTlNearmissreportmst)throws Exception;
	public GenTlNearmissreportmstnew update(GenTlNearmissreportmstnew existGenTlNearmissreportmst,GenTlNearmissreportmstnew existGenTlNearmissreportmst2)throws Exception;
	public GenTlNearmissreportmstnew delete(GenTlNearmissreportmstnew oldGenTlNearmissreportmst)throws Exception;
	public List<String[]> getNearDetailsList(CommonFilter commonFilter,String formType) throws Exception;
	public String UpdateClose(String detailkeyid, String responsevalue,String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception;
	public String UpdateVerify(String detailkeyid, String responsevalue,String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception;
	public String UpdateResponse(String detailkeyid, String responsevalue,String targetdate, String verifystatus, String remarks) throws BusinessApplicationExceptions, Exception;
	public String updateActionCLosure(String detailkeyid, String responsevalue,String targetdate, String reviseddate, String status,String correctiveaction,String remarks) throws BusinessApplicationExceptions, Exception;
	public List<String[]> getElementId(String loginflid, String loginlevel,String loginElementid, String empId) throws NoDataFoundException, Exception;
	public List<String[]> getSwitchUserDetail(String empId) throws NoDataFoundException, Exception;
	//public List<String[]> getNearMissEmpMailIds(String type, String flid, String glbLocation) throws NoDataFoundException, Exception;
	public String UpdateInvest(String repsonse, String targetdate,String probable, String invest, String recomd, String keyid, String chko, String chks, String oa, String os, String remarks, String empId) throws BusinessApplicationExceptions, Exception;
	public GenTlNearmissreportmstnew getGridData(String keyid) throws NoDataFoundException, SQLException, Exception;
	public List<String[]> getActionPlankeyid(String keyid) throws NoDataFoundException, Exception;
	public List<String[]> getNearMiss(String masterKeyid) throws Exception;
	public List<String[]> getNearAct(String masterKeyid) throws Exception;

	public GenTlNearmissreportdtlnew createdtl(GenTlNearmissreportdtlnew newGenTlNearmissreportdtl,
			GenTlNearmissreportmstnew genTlNearmissreportmstnew) throws Exception;
	public String getDetailid(String nmrnKeyid) throws SQLException;
	public GenTlNearmissreportdtlnew Updatedtl(GenTlNearmissreportdtlnew newGenTlNearmissreportdtlact,
			GenTlNearmissreportmstnew genTlNearmissreportmstnew)throws Exception;
	public String getNearActid(String detailkeyid) throws SQLException;
	public String getNearCondid(String detailkeyid)throws SQLException;
	public Workbook NearMissExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,String formtype) throws IOException, SQLException, Exception;
	public String getActid(String nmrnKeyid, String string) throws SQLException;
	public String getCondid(String nmrnKeyid, String string)throws SQLException;
	public String getNearMissActUpdate(String keyid, String string, String act)throws SQLException, BusinessApplicationExceptions, Exception;
	public String getNearMissConUpdate(String keyid, String string, String con)throws SQLException, BusinessApplicationExceptions, Exception;
	List<String[]> getNearMissEmpMailIds(String type, String flid,String Location) throws NoDataFoundException, Exception;
     public Workbook newnearmissExcelView(String keyid, String flid, String path)throws Exception;
	public List<String[]> getNearMissReleatedFileManager(String momKeyId) throws Exception;
	public Workbook NearMissViewExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String formtype) throws Exception;
	public List<String[]> getNearDetailsListView(CommonFilter commonFilter, String formType) throws Exception;



}
