package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JhAuditCreationBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditdtl;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAuditparameter;
import com.akranta.tpm.model.JhaTlTemplatelevellink;
import com.akranta.tpm.model.JhaTlTemplatemchlink;

public interface JHAuditSheetCreationItcService 
{

	public List<String[]> getjhAuditSheetfillGrid(CommonFilter commonFilter) throws Exception;
	public List<String[]> getjhAuditGridSql(JhaTlAuditmst jhaTlAuditmst,String machineId,String jhamKeyID,String auditType,String jhstepid)throws Exception;
	public List<String[]> getgradeid(String point,String keyID)throws Exception;
	public List<String[]> getMinPoints(String parameter,String auditTeam)throws Exception;
	public JhaTlAuditmst select(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public JhaTlAuditmst create(JhaTlAuditmst newjhaTlAuditmst,JhaTlAuditmst oldjhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions, Exception;
	public JhaTlAuditdtl create(JhaTlAuditdtl newJhaTlAuditdtl,JhaTlAuditdtl oldJhaTlAuditdtl,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions, Exception;
	public JhaTlAuditmst update(JhaTlAuditmst newjhaTlAuditmst,JhaTlAuditmst oldjhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean)  throws Exception;
	public JhaTlAuditdtl update(JhaTlAuditdtl newJhaTlAuditdtl,JhaTlAuditdtl oldJhaTlAuditdtl,  JhAuditCreationBean jhAuditCreationBean)  throws Exception;
	public JhaTlAuditmst delete(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public Workbook jhAuditSheetExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	public List<ComboBox> getJhStepComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public String getjhStepkeyId(String flId) throws Exception;
	public JhaTlTemplatelevellink getAuditLevel(String templateId,String flId, String jhStepId) throws Exception;
	public JhaTlAuditmst getExistingjhmKeyid(String templateId,String flId, String date, String auditType, String stepId) throws Exception;
	
	public String getMinMarks(String auditLevel,String auditTemplate) throws Exception;
	public String getjhStepIdforFlid(String flid) throws Exception;
	public String getjhauditLevelCountforFlid(String flid) throws Exception;
	public Workbook getjhAuditMultiExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public List<String[]> getjhAuditParamterGrid(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getJhTemplateComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getJhAppLevelComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<String[]> getjhAuditStepGrid(CommonFilter commonFilter)throws Exception;
	public List<String[]> getjhAuditEquipmentGrid(CommonFilter commonFilter)throws Exception;
	public List<String[]> getjhAuditLevelGrid(CommonFilter commonFilter)throws Exception;
	public JhaTlAuditparameter recallValues(JhaTlAuditparameter jhaTlAuditparameter)throws Exception;
	public JhaTlAuditparameter create(JhaTlAuditparameter newJhaTlAuditparameter,JhaTlAuditparameter existJhaTlAuditparameter)throws ValidationExceptions,BusinessApplicationExceptions,Exception;
	public void deleteParameter(String parameterId)throws Exception;
	public JhaTlAuditparameter delete(JhaTlAuditparameter newJhaTlAuditparameter)throws ValidationExceptions,BusinessApplicationExceptions,Exception;
	public List<String[]> getDMTMultiLevelAuditGrid(CommonFilter commonFilter)throws Exception;
	public JhaTlTemplatelevellink getAppLvel(JhaTlTemplatelevellink jhaTlTemplatelevellink)throws Exception;	
	public String getAuditLevelCurrent(CommonFilter commonFilter) throws Exception ;
	public String getSelectCnt(JhaTlAuditmst jhaTlAuditmst) throws Exception;
	public List<String[]> getAuditReportGrid(CommonFilter commonFilter)throws Exception;
	public Workbook getjhAuditMultiGridExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public String getJhLeader(String flid)throws Exception;
	public List<String[]> getlastauditgrid(CommonFilter commonFilter) throws Exception;
	
	public void JHAuditSheetCreationItcServiceImplJwt(String JwtToken);
}
