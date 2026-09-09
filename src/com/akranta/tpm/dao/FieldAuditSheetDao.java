package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FieldAuditSheetmst;

import net.sf.json.JSONObject;

public interface FieldAuditSheetDao {    
	public List<String[]> FieldAuditSheetDetail(CommonFilter commonFilter) throws Exception;
	public FieldAuditSheetmst select(String keyid) throws Exception;		
	public abstract FieldAuditSheetmst create(FieldAuditSheetmst fieldAuditSheetmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public abstract FieldAuditSheetmst update(FieldAuditSheetmst fieldAuditSheetmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public List<String[]> getFieldAuditSheetList(CommonFilter commonFilter)throws Exception;
	  public Workbook getFieldAuditModificationGridDataExportExcel(CommonFilter commonFilter,
			  JSONObject colmodel, String format) throws  Exception;
	  public List<String[]> getflid(String originalid) throws Exception;
	     public List<String[]> getFieldAuditSheetReport(CommonFilter commonFilter)throws Exception;
	     public Workbook getFieldAuditSheetReportExcel(CommonFilter commonFilter,
				  JSONObject colmodel, String format) throws  Exception;
	     
	     public abstract void FieldAuditSheetDaoImplJwt(String jwtToken);
}
