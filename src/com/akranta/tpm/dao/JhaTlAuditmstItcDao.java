package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAuditparameter;
import com.akranta.tpm.model.JhaTlTemplatelevellink;

public interface JhaTlAuditmstItcDao {
	public abstract JhaTlAuditmst create(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public abstract JhaTlAuditmst update(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public abstract JhaTlAuditmst delete(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public List<String[]> getjhAuditGridSql(JhaTlAuditmst jhaTlAuditmst,String flId,String jhamKeyID,String auditType,String jhstepid)throws Exception;
	public List<String[]> getgradeid(String point,String keyId)throws Exception;
	public List<String[]> getMinPoints(String parameter,String auditTeam)throws Exception;
	public abstract JhaTlAuditmst select(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public String getjhStepid(String flId) throws Exception;
	public JhaTlTemplatelevellink getAuditLevel(String templateId,String flId, String jhStepId) throws Exception;
	public JhaTlAuditmst getExistingjhmKeyid(String templateId,String flId, String date, String auditType, String stepId) throws Exception;
	
	public String getMinMarks(String auditLevel,String auditTemplate) throws Exception;
	public String getjhStepIdforFlid(String flid) throws Exception;
	public String getjhauditLevelCountforFlid(String flid) throws Exception;
	public abstract Workbook getjhAuditMultiExportExcel(CommonFilter commonFilter,JSONObject colmodel,String rptFormat)throws Exception;
	public abstract List<String[]> getjhAuditParamterGrid(CommonFilter commonFilter);
	public abstract List<String[]> getjhAuditStepGrid(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getjhAuditEquipmentGrid(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getjhAuditLevelGrid(CommonFilter commonFilter)throws Exception;
	public abstract JhaTlAuditparameter recallValues(JhaTlAuditparameter jhaTlAuditparameter)throws Exception;
	public abstract JhaTlAuditparameter deleteAuditPatameter(JhaTlAuditparameter newJhaTlAuditparameter)throws ValidationExceptions,BusinessApplicationExceptions,Exception;
	public abstract List<String[]> getDMTMultiLevelAuditGrid(CommonFilter commonFilter)throws Exception;
	public abstract JhaTlTemplatelevellink getAppLvel(JhaTlTemplatelevellink jhaTlTemplatelevellink)throws Exception;
	public String getAuditLevelCurrent(CommonFilter commonFilter) throws Exception ;
	public String getSelectCnt(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public abstract List<String[]> getAuditReportGrid(CommonFilter commonFilter)throws Exception;
	public abstract Workbook jhAuditSheetExportExcel(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat)throws Exception;
	public abstract List<String[]> getjhAuditSheetfillGrid(CommonFilter commonFilter)throws Exception;
	public abstract Workbook getjhAuditMultiGridExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)throws Exception;
	public abstract String getJhLeader(String flid)throws Exception;
	public abstract JhaTlAuditmst createJhDmt(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public abstract JhaTlAuditmst updateJhDmt(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public abstract List<String[]> getjhAuditUploadfillGrid(CommonFilter commonFilter)throws Exception;
	public abstract Workbook jhAuditUploadExportExcel(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat)throws Exception;
	public abstract List<String[]> getlastauditgrid(CommonFilter commonFilter) throws Exception ;
	public abstract void JhaTlAuditmstItcDaoImplJwt(String jwtToken);
}

