package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JhAuditCreationBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAuditparameter;

import net.sf.json.JSONObject;

public interface JhDmtAuditUploadService {
	public JhaTlAuditmst create(JhaTlAuditmst newjhaTlAuditmst,JhaTlAuditmst oldjhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions, Exception;
	public JhaTlAuditmst update(JhaTlAuditmst newjhaTlAuditmst,JhaTlAuditmst oldjhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions, Exception;
	public JhaTlAuditmst select(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public JhaTlAuditparameter recallValues(JhaTlAuditparameter jhaTlAuditparameter)throws Exception;
    public List<String[]> getjhAuditUploadfillGrid(CommonFilter commonFilter) throws Exception;
    public String getMinMarks(String auditLevel,String auditTemplate) throws Exception;
    public List<String[]> getMinPoints(String parameter,String auditTeam)throws Exception;
    public String getJhLeader(String flid)throws Exception;
	public Workbook jhAuditUploadExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	
	public void JhDmtAuditUploadServiceImplJwt(String JwtToken);
}
