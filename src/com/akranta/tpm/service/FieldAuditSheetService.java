package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FieldAuditSheetmst;
import net.sf.json.JSONObject;

public interface FieldAuditSheetService{
	public List<String[]> FieldAuditSheetDetail(CommonFilter commonFilter) throws Exception;
	public FieldAuditSheetmst select(String keyid) throws Exception ;	    	
	public List<ComboBox> PPEType(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getServiceProvider(ComboFilter comboFilter) throws Exception;
	public FieldAuditSheetmst create(FieldAuditSheetmst newFieldAuditSheetmst,
			FieldAuditSheetmst existFieldAuditSheetmst) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public FieldAuditSheetmst update(FieldAuditSheetmst newFieldAuditSheetmst,
			FieldAuditSheetmst existFieldAuditSheetmst) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public List<String[]> getFieldAuditSheetList(CommonFilter commonFilter)throws Exception;
	public Workbook getFieldAuditModificationGridDataExportExcel(CommonFilter commonFilter,
			  JSONObject colmodel, String format) throws  Exception; 
	     public List<String[]> getflid(String originalid) throws Exception;
	     public List<String[]> getFieldAuditSheetReport(CommonFilter commonFilter)throws Exception;
	    public Workbook getFieldAuditSheetReportExcel(CommonFilter commonFilter,
	    				  JSONObject colmodel, String format) throws  Exception;  
	    public void FieldAuditSheetServiceImplJwt(String JwtToken);
}

